# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14 import Root

# <<< imports
# @generated
# >>> imports

class PackageDependenciesCIMVeresion(Root):
    """ The version of dependencies description among top level subpackages of the combined CIM model.  This is not the same as the combined packages version.
    """
    # The version of the main subpackages of the combined CIM model.  The format is simply an integer.  The version (and date) initial values should be updated any time the dependencies in the model change and require an actual change to the diagrams within this package. 
    vesion = ''

    # Date of last change to the main package dependencies in format YYYY-MM-DD.   This is updated when the version attribute is updated. 
    date = ''

    # <<< package_dependencies_cimveresion
    # @generated
    def __init__(self, vesion='', date='', **kw_args):
        """ Initialises a new 'PackageDependenciesCIMVeresion' instance.
        """
        self.vesion = vesion
        self.date = date

        super(PackageDependenciesCIMVeresion, self).__init__(**kw_args)
    # >>> package_dependencies_cimveresion


# <<< package_dependencies
# @generated
# >>> package_dependencies
