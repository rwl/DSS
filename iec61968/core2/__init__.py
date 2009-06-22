# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.
""" The Core2 Package contains core functions needed for distribution management.  These core extensions were not sufficiently mature to standardize at the time IEC61970-301 was released as a draft standard, and therefore Core2 is being released as part of IEC 61968-11.  As is the case of the Core Package, these classes and relationships are intended to be used throughout the CIM.  
"""


# <<< imports
# @generated
# >>> imports

class Core2Version(object):
 
    version = ''

 
    date = ''

    # <<< core2_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'Core2Version' instance.
        """
        self.version = version
        self.date = date

        super(Core2Version, self).__init__(**kw_args)
    # >>> core2_version


# <<< core2
# @generated
# >>> core2
