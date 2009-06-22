# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14 import Root

# <<< imports
# @generated
# >>> imports

class IEC61968CIMVersion(Root):
    """ IEC 61968 version number assigned to this UML model.
    """
    # Form is IEC61968CIMXXvYY where XX is the major CIM package version and the YY is the minor version.  For example IEC61968CIM10v17. 
    version = ''

    # Form is YYYY-MM-DD for example for January 5, 2009 it is 2009-01-05. 
    date = ''

    # <<< iec61968_cimversion
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'IEC61968CIMVersion' instance.
        """
        self.version = version
        self.date = date

        super(IEC61968CIMVersion, self).__init__(**kw_args)
    # >>> iec61968_cimversion


# <<< iec61968
# @generated
# >>> iec61968
