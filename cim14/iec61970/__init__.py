# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14 import Root

# <<< imports
# @generated
# >>> imports

class IEC61970CIMVersion(Root):
    """ This is the IEC 61970 CIM version number assigned to this UML model file.
    """
    # Form is YYYY-MM-DD for example for January 5, 2009 it is 2009-01-05. 
    date = ''

    # Form is IEC61970CIMXXvYY where XX is the major CIM package version and the YY is the minor version.   For ecample IEC61970CIM13v18. 
    version = ''

    # <<< iec61970_cimversion
    # @generated
    def __init__(self, date='', version='', **kw_args):
        """ Initialises a new 'IEC61970CIMVersion' instance.
        """
        self.date = date
        self.version = version

        super(IEC61970CIMVersion, self).__init__(**kw_args)
    # >>> iec61970_cimversion


# <<< iec61970
# @generated
# >>> iec61970
