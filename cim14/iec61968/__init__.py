# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14 import Element

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61968"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61968"

class IEC61968CIMVersion(Element):
    """ IEC 61968 version number assigned to this UML model.
    """
    # <<< iec61968_cimversion
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'IEC61968CIMVersion' instance.
        """
        # Form is IEC61968CIMXXvYY where XX is the major CIM package version and the YY is the minor version.  For example IEC61968CIM10v17. 
        self.version = version
        # Form is YYYY-MM-DD for example for January 5, 2009 it is 2009-01-05. 
        self.date = date
        

        super(IEC61968CIMVersion, self).__init__(**kw_args)
    # >>> iec61968_cimversion
        


# <<< iec61968
# @generated
# >>> iec61968
