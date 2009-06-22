# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.
""" This package is used to define asset-level models for objects. Assets may be comprised of other assets and may have relationships to other assets. Assets also have owners and values. Assets may also have a relationship to a PowerSystemResource in the Wires model.
"""


# <<< imports
# @generated
# >>> imports

class AssetsVersion(object):
 
    version = ''

 
    date = ''

    # <<< assets_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'AssetsVersion' instance.
        """
        self.version = version
        self.date = date

        super(AssetsVersion, self).__init__(**kw_args)
    # >>> assets_version


# <<< assets
# @generated
# >>> assets
