# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.
""" An Operational Restriction is typically applied against an Asset Model, often provided by a manufacturer.  The utility should be aware of this for each Asset of the model.
"""


# <<< imports
# @generated
# >>> imports

class OperationalRestrictionVersion(object):
 
    version = ''

 
    date = ''

    # <<< operational_restriction_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'OperationalRestrictionVersion' instance.
        """
        self.version = version
        self.date = date

        super(OperationalRestrictionVersion, self).__init__(**kw_args)
    # >>> operational_restriction_version


# <<< operationalrestriction
# @generated
# >>> operationalrestriction
