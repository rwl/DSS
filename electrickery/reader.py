# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

""" Looks like we got ourselves a reader.
"""

import sys
import gzip
import bz2
import zipfile
import logging

from os.path import basename, exists, splitext

import rdfxml

cim_packages = ["cim.core", "cim.domain", "cim.topology",
    "cim.generation.production", "cim.load_model", "cim.wires",
    "cim.protection", "cim.meas", "cim.generation.generation_dynamics",
    "cim.contingency", "cim.control_area",  "cim.equivalents",
    "cim.operational_limits", "cim.outage", "cim.scada", "cim"]

for pkg in cim_packages:
    exec "import %s" % pkg # Import all of the CIM packages.

logging.basicConfig(stream=sys.stdout, level=logging.ERROR,
    format="%(levelname)s: %(message)s)")
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
#        logger.debug("Partitioned URI: '%s', '%s'." % (head + sep, tail))
        return (head + sep, tail)
    else:
#        logger.debug("URI [%s] has no end fragment." % uri)
        return (tail, "")


class CIMAttributeSink(object):
    """ Uses triples from the RDF parser to populate a dictionary that maps
        rdf:IDs to objects.  The objects are instantiated and their attributes
        are set, but any references are not.  This is done with a second pass
        using a CIMReferenceSink that is passed this sink.
    """

    def __init__(self, ns_cim):
        self.uri_object_map = {}
        self.ns_cim = rdfxml.Namespace(ns_cim)


    def triple(self, sub, pred, obj):
        """ Handles triples from the RDF parser.
        """
        logger.debug("Processing triple [%s %s %s]." % (sub, pred, obj))

        ns_sub,  frag_sub  = splitURI(sub)
        ns_pred, frag_pred = splitURI(pred)
        ns_obj,  frag_obj  = splitURI(obj)

        # Instantiate an object if the predicate is an RDF type and the object
        # is in the CIM namespace.
        if (ns_pred == RDFXML.rdf) and (frag_pred == "type") and \
            (ns_obj == self.ns_cim):

            cls_name = frag_obj
            uri      = frag_sub

            for pkg in cim_packages:
                if hasattr(eval(pkg), cls_name):
                    klass = eval( "%s.%s" % (pkg, cls_name) )
                    logger.debug("Class [%s] and package [%s] located." %
                        (cls_name, pkg))
                    break
            else:
                logger.error("Class [%s] could not be located." % cls_name)
                return

            # Instantiate the CIM element and map it according to its URI.
            self.uri_object_map[uri] = klass(URI=uri)

        # If the predicate is in the CIM namespace the triple is specifying
        # an attribute or a reference.
        elif ns_pred == self.ns_cim:
            # The URI of the object with the attribute being set.
            uri   = frag_sub
            # Strip the double quotes that RDFXML.py adds to literals.
            value = ns_obj.strip('"')

            # Split the class name and the attribute name.
            class_name, attr_name = frag_pred.rsplit(".", 1)
#            logger.debug("Attempting to set '%s' for '%s' to '%s'." %
#                (attr_name, class_name, value))

            # Retrieve the object from the URI map.
            if self.uri_object_map.has_key(uri):
                element = self.uri_object_map[uri]
            else:
                logger.error("Object [%s] not found." % uri)
                return

            # Get the trait definition and check that it is defined.
            trait = element.trait( attr_name )
            if trait is None:
                logger.error("Object [%s] has no attribute: %s" %
                    (element.__class__.__name__, attr_name))
                return

            # Coerce the value type.
            if trait.is_trait_type( Instance ):
                # See 'CIMReferenceSink' for reference setting.
                return

            elif trait.is_trait_type( List ):
                if trait.inner_traits[0].is_trait_type( Instance ):
                    # Multiplicity many references set on second pass.
                    return
                else:
                    value = list( frag_obj )

            if trait.is_trait_type( Int ):
                value = int( value )

            elif trait.is_trait_type( Float ):
                value = float( value )

            elif trait.is_trait_type( Bool ):
                value = bool( value )

            elif trait.is_trait_type( Enum ):
                # The 'object' in an Enum triple is the URL for the data type
                # and the value must be split of the end of the fragment.
                value = frag_obj.rsplit(".", 1)[1]

            else:
                value = value

            logger.debug("Setting '%s' attribute '%s' to: %s" %
                (element.__class__.__name__, attr_name, value))

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
            class_name, ref_name = frag_pred.rsplit(".", 1)
            # Assert that the object from the dictionary has the same type as
            # that specified in the predicate.
#            assert sub_obj.__class__.__name__ == class_name

#            logger.debug("Trying to set the '%s' reference of '%s' to: '%s'" %
#                (ref_name, class_name, ref_name))

            # Get the attribute object so the type can be determined.
            trait = sub_obj.trait( ref_name )
            if trait is None:
                logger.error("Object [%s] has no reference: %s" %
                    (sub_obj.__class__.__name__, ref_name))
                return

            # Set reference traits.
            if trait.is_trait_type( Instance ):
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
            elif trait.is_trait_type( List ) and \
                trait.inner_traits[0].is_trait_type( Instance ):

                logger.warning("Skipping multiplicity-many reference.")


class CIMReader(object):
    """ Reads CIM RDF/XML data files and returns a dictionary that maps
        unique resource identifiers to CIM object instances.
    """
    # Path to the file containing CIM RDF/XML data.
    filename = ""

    # CIM XML namespace.
    ns_cim = ""

    def __init__(self, filename,
            ns_cim="http://iec.ch/TC57/2009/CIM-schema-cim14#"):

        self.filename = filename
        self.ns_cim = ns_cim


    def parse_file(self, filename=None, pwd=None):
        """ Parses an RDF/XML file and returns a model containing CIM elements.

            pwd is the password used for encrypted zip files.
        """
        filename = filename or self.filename
        assert exists(filename)

        # Split the extension from the pathname
        root, extension = splitext( filename )

        if isinstance(filename, file):
            s = filename.read()

        if extension == ".xml":
            fd = None
            try:
                fd = open(filename, "rb")
                s = fd.read()
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
                    print "Zip file contains no members."
                    return

                # FIXME: Perhaps extract to a temporary directory.
                zipextdatafile = zipdatafile.open( member_name, "rb", pwd )
                s = zipextdatafile.read()
                zipextdatafile.close()
            finally:
                if zipdatafile is not None:
                    zipdatafile.close()

        elif extension == ".gz":
            fd = None
            try:
                fd = gzip.open(filename, "rb")
                s = f.read()
            finally:
                if fd is not None:
                    fd.close()

        elif extension == ".bz2":
            bz2datafile = None
            try:
                bz2file = bz2.BZ2File( filename )
                s = bz2file.read()
            finally:
                if bz2datafile is not None:
                    bz2datafile.close()

        # Instantiate CIM objects and set their attributes.
        attr_sink = CIMAttributeSink(self.ns_cim)
        logger.debug("Parsing objects and attributes in: %s" % filename)
#        RDFXML.parseURI(filename, sink=attr_sink)
        RDFXML.parseRDF(s, base=filename, sink=attr_sink)

        # Second pass to set references.
        ref_sink = CIMReferenceSink(attr_sink)
        logger.debug("Starting second pass to set references.")
#        RDFXML.parseURI(filename, sink=ref_sink)
        RDFXML.parseRDF(s, base=filename, sink=ref_sink)

        # Return a map of unique resource identifiers to CIM objects.
        return attr_sink.uri_object_map


def read_cim(filename):
    """ Function for import of CIM RDF/XML data files given the file path.
    """
    return CIMReader(filename).parse_file()
