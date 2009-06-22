# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.
""" Work Packages covers all types of work, including insepction, maintenance, repair, restoration, and construction.   It covers the full the full life cycle including request, initiate, track and record work. Standardized designs (compatible units) are used where possible.
"""


# <<< imports
# @generated
# >>> imports

class WorkVersion(object):
 
    version = ''

 
    date = ''

    # <<< work_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'WorkVersion' instance.
        """
        self.version = version
        self.date = date

        super(WorkVersion, self).__init__(**kw_args)
    # >>> work_version


# <<< work
# @generated
# >>> work
