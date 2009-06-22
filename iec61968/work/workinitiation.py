# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from iec61968.documentation.documentinheritance import Document
from  import 

# <<< imports
# @generated
# >>> imports

class Work(Document):
    """  Work is a document which is used to request, initiate, track and record work.   This is synonymous with Work Breakdown Structure (WBS), which is traversed through the recursive association of Work.   Note that the work name is equal to the WBS name, which is given in the inherited 'name' attribute.  At the sub-work level (through recursion), the inherited 'type' attribute indicates whether the work is capital, operations and maintenance, or other.  
    """
    #  Priority of the work. 
    work_priority = ''

    # Date and time work was requested. 
    request_date_time = ''

    # Type of work: construction, inspection, maintenance, service, misc.  
    type_work = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'statusDateTime' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # Reason code or explanation for why this went to the current status. 
    status_reason = ''

    work_cost_detail = []

    designs = []

    erp_project_accounting = None

    project = None

    customer_data = []

    business_case = None

    request = None

    work_flow_step = []

    work_billing_info = None

    work_tasks = []

    # <<< work
    # @generated
    def __init__(self, work_priority='', request_date_time='', type_work='', status='', status_date_time='', status_remarks='', status_reason='', work_cost_detail=[], designs=[], erp_project_accounting=None, project=None, customer_data=[], business_case=None, request=None, work_flow_step=[], work_billing_info=None, work_tasks=[], **kw_args):
        """ Initialises a new 'Work' instance.
        """
        self.work_priority = work_priority
        self.request_date_time = request_date_time
        self.type_work = type_work
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.status_reason = status_reason
        self.work_cost_detail = work_cost_detail
        self.designs = designs
        self.erp_project_accounting = erp_project_accounting
        self.project = project
        self.customer_data = customer_data
        self.business_case = business_case
        self.request = request
        self.work_flow_step = work_flow_step
        self.work_billing_info = work_billing_info
        self.work_tasks = work_tasks

        super(Work, self).__init__(**kw_args)
    # >>> work


class Project(Document):
    """ A collection of related work.  For construction projects and maintenance projects,  multiple phases may be performed.  
    """
    # Overall project budget. 
    budget = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    work = []

    erp_project_accounting = None

    parent_project = None

    sub_project = []

    requests = []

    business_case = None

    # <<< project
    # @generated
    def __init__(self, budget='', status='', status_date_time='', status_remarks='', work=[], erp_project_accounting=None, parent_project=None, sub_project=[], requests=[], business_case=None, **kw_args):
        """ Initialises a new 'Project' instance.
        """
        self.budget = budget
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.work = work
        self.erp_project_accounting = erp_project_accounting
        self.parent_project = parent_project
        self.sub_project = sub_project
        self.requests = requests
        self.business_case = business_case

        super(Project, self).__init__(**kw_args)
    # >>> project


class BusinessCase(Document):
    """ Business justification for capital expenditures, usually addressing operations and maintenance costs as well.
    """
    # A codified representation of the business case (i.e., codes for highway relocation, replace substation transformers, etc.). 
    code = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # The type of business case.  
    type_bc = ''

    work = []

    projects = []

    # <<< business_case
    # @generated
    def __init__(self, code='', status='', status_date_time='', status_remarks='', type_bc='', work=[], projects=[], **kw_args):
        """ Initialises a new 'BusinessCase' instance.
        """
        self.code = code
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.type_bc = type_bc
        self.work = work
        self.projects = projects

        super(BusinessCase, self).__init__(**kw_args)
    # >>> business_case


class Request(Document):
    """ A request for work, service or project.
    """
    # The type of request. 
    request_type = ''

    # The priority of this request.  
    request_priority = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    # Reason code or explanation for why this went to the current status. 
    status_reason = ''

    # The corporate code for this request. 
    request_code = ''

    # Based on the current 'status', the action that is needed before this Request can transition to the desired state, such as initiating the requested Work.  For example,  missing or additionally needed information may be required from the requesting organisation before a Work Design may be created.  
    action_needed = ''

    work = []

    projects = []

    erp_quote_line_item = None

    organisation = None

    # <<< request
    # @generated
    def __init__(self, request_type='', request_priority='', status='', status_date_time='', status_remarks='', status_reason='', request_code='', action_needed='', work=[], projects=[], erp_quote_line_item=None, organisation=None, **kw_args):
        """ Initialises a new 'Request' instance.
        """
        self.request_type = request_type
        self.request_priority = request_priority
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.status_reason = status_reason
        self.request_code = request_code
        self.action_needed = action_needed
        self.work = work
        self.projects = projects
        self.erp_quote_line_item = erp_quote_line_item
        self.organisation = organisation

        super(Request, self).__init__(**kw_args)
    # >>> request


class WorkFlowStep():
    """ A pre-defined set of work steps for a given type of work.  A WorkFlow 
    """
    # The sequence number is used to defined dependencies of each work flow step, which is for the instance of WorkTask associated with a given instance of WorkFlow. 
    sequence = ''

    work = None

    work_tasks = []

    # <<< work_flow_step
    # @generated
    def __init__(self, sequence='', work=None, work_tasks=[], **kw_args):
        """ Initialises a new 'WorkFlowStep' instance.
        """
        self.sequence = sequence
        self.work = work
        self.work_tasks = work_tasks

        super(WorkFlowStep, self).__init__(**kw_args)
    # >>> work_flow_step


class WorkInitiationVersion(object):
 
    version = ''

 
    date = ''

    # <<< work_initiation_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'WorkInitiationVersion' instance.
        """
        self.version = version
        self.date = date

        super(WorkInitiationVersion, self).__init__(**kw_args)
    # >>> work_initiation_version


class InfoQuestion(Document):
    """ Questions and answers associated with a type of document for purposes of clarification.  Questions may be predefined or ad hoc.  
    """
    # The category of the question.  
    question_category = ''

    # The question code.  If blank, refer to questText for the text of the question.  
    question_code = ''

    # For non-coded questions, the question is provided here.  
    question_text = ''

    # Answer to question.  
    answer = ''

    # Remarks to qualify the question in this situation.  
    question_remarks = ''

    # Remarks to qualify the answer.  
    answer_remarks = ''

    # The date and time the quesiton was answered.  
    answer_date_time = ''

    # <<< info_question
    # @generated
    def __init__(self, question_category='', question_code='', question_text='', answer='', question_remarks='', answer_remarks='', answer_date_time='', **kw_args):
        """ Initialises a new 'InfoQuestion' instance.
        """
        self.question_category = question_category
        self.question_code = question_code
        self.question_text = question_text
        self.answer = answer
        self.question_remarks = question_remarks
        self.answer_remarks = answer_remarks
        self.answer_date_time = answer_date_time

        super(InfoQuestion, self).__init__(**kw_args)
    # >>> info_question


# <<< workinitiation
# @generated
# >>> workinitiation
