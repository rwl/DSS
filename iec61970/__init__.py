# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.
""" Defines IEC standard 61970.
"""


# <<< imports
# @generated
# >>> imports

class Element(object):
    """ Base class for Common Information Model elements.
    """
    # Model that the element is a part of.
    model = None

    # <<< element
    # @generated
    def __init__(self, model=None, **kw_args):
        """ Initialises a new 'Element' instance.
        """
        self.model = model

        super(Element, self).__init__(**kw_args)
    # >>> element


class Model(object):
    """ Defines a container of model elements conforming to IEC standard 61970.
    """
    # Map of URI to model element. 
    elements = {}

    # <<< model
    # @generated
    def __init__(self, elements={}, **kw_args):
        """ Initialises a new 'Model' instance.
        """
        self.elements = elements

        super(Model, self).__init__(**kw_args)
    # >>> model


class IEC61970CIMVersion(Element):
    """ This is the IEC 61970 CIM version number assigned to this UML model file.
    """
    # Form is IEC61970CIMXXvYY where XX is the major CIM package version and the YY is the minor version.   For ecample IEC61970CIM13v18. 
    version = ''

    # Form is YYYY-MM-DD for example for January 5, 2009 it is 2009-01-05. 
    date = ''

    # <<< iec61970_cimversion
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'IEC61970CIMVersion' instance.
        """
        self.version = version
        self.date = date

        super(IEC61970CIMVersion, self).__init__(**kw_args)
    # >>> iec61970_cimversion


# <<< iec61970
# @generated
# >>> iec61970
