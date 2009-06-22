# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61968.common import Document

# <<< imports
# @generated
# >>> imports

class Work(Document):
    """ Document used to request, initiate, track and record work. This is synonymous with Work Breakdown Structure (WBS), which is traversed through the (currently informative) recursive association of Work. Note that the work name is equal to the WBS name, which is given in the inherited 'name' attribute.
    """
    # Kind of work. Values are: "disconnect", "service", "other", "maintenance", "meter", "inspection", "reconnect", "construction"
    kind = 'disconnect'

    # Priority of work. 
    priority = ''

    # Date and time work was requested. 
    request_date_time = ''

    work_billing_info = None

    project = None

    designs = []

    # All the customers for which this work is performed.
    customers = []

    work_flow_steps = []

    business_case = None

    work_cost_details = []

    request = None

    work_tasks = []

    erp_project_accounting = None

    # <<< work
    # @generated
    def __init__(self, kind='disconnect', priority='', request_date_time='', work_billing_info=None, project=None, designs=[], customers=[], work_flow_steps=[], business_case=None, work_cost_details=[], request=None, work_tasks=[], erp_project_accounting=None, **kw_args):
        """ Initialises a new 'Work' instance.
        """
        self.kind = kind
        self.priority = priority
        self.request_date_time = request_date_time
        self.work_billing_info = work_billing_info
        self.project = project
        self.designs = designs
        self.customers = customers
        self.work_flow_steps = work_flow_steps
        self.business_case = business_case
        self.work_cost_details = work_cost_details
        self.request = request
        self.work_tasks = work_tasks
        self.erp_project_accounting = erp_project_accounting

        super(Work, self).__init__(**kw_args)
    # >>> work


# <<< work
# @generated
# >>> work
