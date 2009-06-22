# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from  import 

# <<< imports
# @generated
# >>> imports

class Role():
    """ This is an enumeration of potential roles that might played by one object relative to another.
    """
    #  Priveleges associated with this role. 
    privilege = ''

    # Type of role. 
    role_type = ''

    # Status of this role. 
    status = ''

    # Date status of role was entered. 
    status_date = ''

    # <<< role
    # @generated
    def __init__(self, privilege='', role_type='', status='', status_date='', **kw_args):
        """ Initialises a new 'Role' instance.
        """
        self.privilege = privilege
        self.role_type = role_type
        self.status = status
        self.status_date = status_date

        super(Role, self).__init__(**kw_args)
    # >>> role


class IdentifiedObjectInheritanceVersion(object):
 
    version = ''

 
    date = ''

    # <<< identified_object_inheritance_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'IdentifiedObjectInheritanceVersion' instance.
        """
        self.version = version
        self.date = date

        super(IdentifiedObjectInheritanceVersion, self).__init__(**kw_args)
    # >>> identified_object_inheritance_version


# <<< identifiedobjectinheritance
# @generated
# >>> identifiedobjectinheritance
