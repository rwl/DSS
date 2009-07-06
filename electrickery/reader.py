# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

""" Looks like we got ourselves a reader.
"""

import re
import sys
import gzip
import bz2
import zipfile
import logging

from os.path import basename, exists, splitext

import rdfxml

from module_dict import klass_module_map

from cim14 import ns_uri

logging.basicConfig(stream=sys.stdout, level=logging.DEBUG,
    format="%(levelname)s: %(message)s")
logger = logging.getLogger(__name__)


def splitURI(uri):
    """ Splits the fragment from an URI and returns a tuple.

        For example:
            <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>
        returns:
            ('http://www.w3.org/1999/02/22-rdf-syntax-ns#', 'type')

            http://www.github.com/rwl/pylon
        returns:
            (http://www.github.com/rwl/pylon, "")
    """
    if (uri[0] == "<") and (uri[-1] == ">"):
        uri = uri[1:-1]

    head, sep, tail = uri.rpartition("#")
    if head and sep:
        logger.debug("Partitioned URI: '%s', '%s'." % (head + sep, tail))
        return (head + sep, tail)
    else:
        logger.debug("URI [%s] has no end fragment." % uri)
        return (tail, "")


class _CamelToLowercaseUnderscore(object):
    """ Simple functor class to convert names from CamelCase to
        lowercase underscore separated names.
    """
    def __call__(self, name):
        s = ""
        for i, c in enumerate(name):
            if i > 0:
                next = "a"
                if i < len(name) - 1:
                    next = name[i + 1]
                prev = name[i - 1]

                if c.isupper() and (prev != "_") and (not prev.isupper()):
                    s += "_"
            s += c.lower()

        return s


class CIMAttributeSink(object):
    """ Uses triples from the RDF parser to populate a dictionary that maps
        rdf:IDs to objects.  The objects are instantiated and their attributes
        are set, but any references are not.  This is done with a second pass
        using a CIMReferenceSink that is passed this sink.
    """

    def __init__(self, ns_cim):
        self.uri_object_map = {}
        self.ns_cim = rdfxml.Namespace(ns_cim)

        # Convertor from camel case to lowercase underscore separated.
        self.camel_to_lcu = _CamelToLowercaseUnderscore()


    def triple(self, sub, pred, obj):
        """ Handles triples from the RDF parser.
        """
        logger.debug("Processing triple [%s %s %s]." % (sub, pred, obj))

        ns_sub,  frag_sub  = splitURI(sub)
        ns_pred, frag_pred = splitURI(pred)
        ns_obj,  frag_obj  = splitURI(obj)

        # Instantiate an object if the predicate is an RDF type and the object
        # is in the CIM namespace.
        if (ns_pred == rdfxml.rdf) and (frag_pred == "type") and \
            (ns_obj == self.ns_cim):

            cls_name = frag_obj
            uri      = frag_sub

            if klass_module_map.has_key(cls_name):
                # It is fairly cheap to import an already imported module.
                module_name = klass_module_map[cls_name]
                
                logger.debug("Class [%s] located [%s]." %
                    (cls_name, module_name))
                
                exec "import %s" % module_name
                element = eval("%s.%s(uri=uri)" % (module_name, cls_name))

                if hasattr(element, "m_rid"):
                    element.m_rid = uri
            else:
                logger.error("Module for '%s' not located." % cls_name)

            # Map the element according to its URI.
            self.uri_object_map[uri] = element

        # If the predicate is in the CIM namespace the triple is specifying
        # an attribute or a reference.
        elif ns_pred == self.ns_cim:
            # The URI of the object with the attribute being set.
            uri = frag_sub
            # Strip the double quotes that rdfxml.py adds to literals.
            value = ns_obj.strip('"')

            # Split the class name and the attribute name.
            class_name, camel_attr_name = frag_pred.rsplit(".", 1)
            attr_name = self.camel_to_lcu(camel_attr_name)
#            logger.debug("Attempting to set '%s' for '%s' to '%s'." %
#                (attr_name, class_name, value))

            # Retrieve the object from the URI map.
            if self.uri_object_map.has_key(uri):
                element = self.uri_object_map[uri]
            else:
                logger.error("Element [%s] not found." % uri)
                return

            if not hasattr(element, attr_name):
                logger.error("Element [%s] has no attribute: %s" %
                    (element.__class__.__name__, attr_name))
                return
            
            default = getattr(element, attr_name)
            if default is not None:
                # Coerce the parsed value according to the type of the default.
                default_type = type(default)
                value = default_type(value)
            else:
                logger.debug("Default value of 'None'.")
                return

            # TODO: Handle enumerations where the 'object' in the triple is the
            # URL for the data type and the value must be split of the end of
            # the fragment.
            # value = frag_obj.rsplit(".", 1)[1]

            logger.debug("Setting '%s' attribute '%s' to: %s %s" %
                (element.__class__.__name__, attr_name, str(value),
                 type(value)))

            setattr(element, attr_name, value)


class CIMReferenceSink(object):
    """ Handles setting the references for a CIM.
    """

    # The sink used in the first pass.
    attr_sink = None

    def __init__(self, attr_sink):
        assert isinstance(attr_sink, CIMAttributeSink)

        self.attr_sink = attr_sink
        self.ns_cim    = self.attr_sink.ns_cim

        # Convertor from camel case to lowercase underscore separated.
        self.camel_to_lcu = _CamelToLowercaseUnderscore()


    def triple(self, sub, pred, obj):
        """ Handles triples from the RDF parser.
        """
        ns_pred, frag_pred = splitURI(pred)
        ns_obj,  obj_uri   = splitURI(obj)

        # If the predicate is in the CIM namespace, the triple is specifying
        # an attribute or a reference.
        if ns_pred == self.ns_cim:
            ns_sub, uri_subject = splitURI(sub)

            # Get the map of URIs to model elements for the first pass sink.
            uri_map = self.attr_sink.uri_object_map
            # Try to get the object with the reference being set.
            if uri_map.has_key(uri_subject):
                sub_obj = self.attr_sink.uri_object_map[uri_subject]
            else:
                logger.error("Referencing object [%s] not found." %
                    uri_subject)
                return

            # Split the predicate fragment into class name and attribute name.
            class_name, camel_ref_name = frag_pred.rsplit(".", 1)
            ref_name = self.camel_to_lcu(camel_ref_name)
            # Assert that the object from the dictionary has the same type as
            # that specified in the predicate.
#            assert sub_obj.__class__.__name__ == class_name

#            logger.debug("Trying to set the '%s' reference of '%s' to: '%s'" %
#                (ref_name, class_name, ref_name))

            # Get the attribute object so the type can be determined.
#            trait = sub_obj.trait( ref_name )
            if not hasattr(object, ref_name):
                logger.error("Object [%s] has no reference: %s" %
                    (sub_obj.__class__.__name__, ref_name))
                return

            # Set reference traits.
#            if trait.is_trait_type( Instance ):

            # Try to get the object being referenced.
            if uri_map.has_key(obj_uri):
                ref_obj = self.attr_sink.uri_object_map[obj_uri]
            else:
                logger.error("Referenced object [%s] not found." % obj_uri)
                return


            logger.debug("Setting the '%s' reference of '%s' to: %s" %
                (ref_name, sub_obj, ref_obj))

            setattr(sub_obj, ref_name, ref_obj)

            # One to many and many to many references (List(Instance)).
#            elif trait.is_trait_type( List ) and \
#                trait.inner_traits[0].is_trait_type( Instance ):
#
#                logger.warning("Skipping multiplicity-many reference.")


class CIMReader(object):
    """ Reads CIM RDF/XML data files and returns a dictionary that maps
        unique resource identifiers to CIM object instances.
    """

    def __init__(self, ns_cim=ns_uri, pwd=None):
        """ Initialises a new CIMReader instance.
        """
        # CIM XML namespace.
        self.ns_cim = ns_cim
        # Password used for encrypted zip files.
        self.pwd = pwd


    def __call__(self, filename):
        """ Parses an RDF/XML file and returns a model containing CIM elements.
        """
        # Path to the file containing CIM RDF/XML data.
#        filename = filename or self.filename
        assert exists(filename)

        # Split the extension from the pathname
        root, extension = splitext(filename)

        if isinstance(filename, file):
            s = filename.read()

        if extension in [".xml"]:
            fd = None
            try:
                fd = open(filename, "rb")
                s = fd.read()
            except:
                logger.error("Error reading XML data file.")
            finally:
                if fd is not None:
                    fd.close()

        elif zipfile.is_zipfile(filename):
            zipdatafile = None
            try:
                zipdatafile = zipfile.ZipFile(filename)
                member_names = zipdatafile.namelist()
                if member_names:
                    member_name = member_names[0]
                else:
                    logger.error("Zip file contains no members.")
                    return None

                # FIXME: Perhaps extract to a temporary directory.
                zipextdatafile = zipdatafile.open(member_name, "rb", pwd)
                s = zipextdatafile.read()
                zipextdatafile.close()
            except:
                logger.error("Error reading Zip file.")
            finally:
                if zipdatafile is not None:
                    zipdatafile.close()

        elif extension in [".gz"]:
            fd = None
            try:
                fd = gzip.open(filename, "rb")
                s = f.read()
            except:
                logger.error("Error reading gzip archive.")
            finally:
                if fd is not None:
                    fd.close()

        elif extension == ".bz2":
            bz2datafile = None
            try:
                bz2file = bz2.BZ2File( filename )
                s = bz2file.read()
            except:
                logger.error("Error reading bzip2 archive.")
            finally:
                if bz2datafile is not None:
                    bz2datafile.close()

        # Instantiate CIM objects and set their attributes.
        attr_sink = CIMAttributeSink(self.ns_cim)
        logger.debug("Parsing objects and attributes in: %s" % filename)
        rdfxml.parseRDF(s, base=filename, sink=attr_sink)

        # Second pass to set references.
        ref_sink = CIMReferenceSink(attr_sink)
        logger.debug("Starting second pass to set references.")
#        rdfxml.parseRDF(s, base=filename, sink=ref_sink)

        # Return a map of unique resource identifiers to CIM objects.
        return attr_sink.uri_object_map
