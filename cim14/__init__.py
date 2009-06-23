# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.


# <<< imports
# @generated
# >>> imports

ns_prefix = "cim"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#"

class Element(object):
 
    uri = ''

    model = None

    # <<< element
    # @generated
    def __init__(self, uri='', model=None, **kw_args):
        """ Initialises a new 'Element' instance.
        """
        self.uri = uri
        self.model = model

        super(Element, self).__init__(**kw_args)
    # >>> element


class Model(object):
    elements = []

    # <<< model
    # @generated
    def __init__(self, elements=[], **kw_args):
        """ Initialises a new 'Model' instance.
        """
        self.elements = elements

        super(Model, self).__init__(**kw_args)
    # >>> model


class CombinedVersion(Element):
    """ The combined version denotes the versions of the subpackages that have been combined into the total CIIMmodel. This is a convenience instead of having to look at each subpackage.
    """
    # Form is IEC61970CIMXXvYY_IEC61968CIMXXvYY_combined where XX is the major CIM package version and the YY is the minor version, and different packages could have different major and minor versions.   For example IEC61970CIM13v18_IEC61968CIM10v16_combined.  Additional packages might be added in the future. 
    version = ''

    # Form is YYYY-MM-DD for example for January 5, 2009 it is 2009-01-05. 
    date = ''

    # <<< combined_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'CombinedVersion' instance.
        """
        self.version = version
        self.date = date

        super(CombinedVersion, self).__init__(**kw_args)
    # >>> combined_version


# <<< cim14
# @generated
# >>> cim14
