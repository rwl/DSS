# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.
""" The Documentation Package contains sub-packages (themes) for real world objects that are largely document oriented (e.g., Work Order, Trouble Ticket, Safety Document, etc.). 
"""


# <<< imports
# @generated
# >>> imports

class DocumentationVersion(object):
 
    version = ''

 
    date = ''

    # <<< documentation_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'DocumentationVersion' instance.
        """
        self.version = version
        self.date = date

        super(DocumentationVersion, self).__init__(**kw_args)
    # >>> documentation_version


# <<< documentation
# @generated
# >>> documentation
