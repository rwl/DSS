# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from  import 
from iec61968.core2.identifiedobjectinheritance import Role
from iec61968.documentation.documentinheritance import Document
from  import 

# <<< imports
# @generated
# >>> imports

class Organisation():
    """  This class is used to identify organisations that might have roles as utilities, contractors, suppliers, manufacturers, customers, etc. Organisations may also have parent-child relationships to identify departments within an organisation, or parent company relationships.
    """
    #  Designated code for organisation. 
    organisation_code = ''

    # Status of this organisation (e.g., planned, open, closed, other). 
    current_status = ''

    # Date status of association was entered. 
    status_date = ''

    # The type of organisation.  Examples include utility customer, utility enterprise, division, business unit, department, energry service supplier, energy service scheduler, independent system operator (ISO), regional transmission operator (RTO), manufacturer, contractor, governmental agency, tax authority, supplier, etc. Further information is available through the Standard Industrial Code (SIC) and marketRole.   
    organisation_type = ''

    # Flag is true if organisation is a cost center. 
    cost_center_flag = ''

    # Flag is true if organisation is a profit center.l 
    profit_center_flag = ''

    # The operational mode of the organisation.  Some utilities use text to describe various modes like nominal, emergency, storm, other.  Other utilities use severity ratings where, for example, 0 is a nominal condition and 5 is the most severe condition.  This information is often required for outage reporting purposes. 
    mode = ''

    # Role of market participant.  Examples include one or more of the following: Energy Service Consumer, Generator Owner, Generator Operator, Transmission Service Provider, Transmission Owner, Transmission Operator, Distribution Provider, Load Serving Entitie, Purchasing-Selling Entitie, Reliability Authority, Planning Authority, Balancing Authority, Interchange Authority, Transmission Planner, Resource Planner, Standards Developer, Compliance Monitor, and Not Applicable.  
    market_role = ''

    # This field indicates whether or not the organisation has requested that their contact information not be shared with other organisations for purposes of solicitation, often referred to as 'opting out.'   false  = okay to solicit; true  = opted out. 
    opt_out = ''

    # Unique identifier for organisation relative to it's governing authority, for example a federal tax identifier. 
    government_id = ''

    # Unique identifier for a given organisation (business).  In the USA, this is a 'Dunns' or D&B number. This identifier is typically in addition to the identifiers that organizations assign (on an internal basis) to each of their locations.  Note that a unique identifier can be set up for each location of an organisation.  This requirement is supported through the recursive Organisation-Organisation relationship, where each child Organisation can have a specified physical location.  
    industry_id = ''

    assets = []

    power_system_resources = []

    customer_data = None

    customer_lists = []

    activity_records = []

    property = []

    parent_organisation = []

    child_organisation = []

    erp_persons = []

    documents = []

    electronic_addresses = []

    locations = []

    erp_telephone_numbers = []

    change_items = []

    requests = []

    crews = []

    violation_limits = []

    registered_resources = []

    # <<< organisation
    # @generated
    def __init__(self, organisation_code='', current_status='', status_date='', organisation_type='', cost_center_flag='', profit_center_flag='', mode='', market_role='', opt_out='', government_id='', industry_id='', assets=[], power_system_resources=[], customer_data=None, customer_lists=[], activity_records=[], property=[], parent_organisation=[], child_organisation=[], erp_persons=[], documents=[], electronic_addresses=[], locations=[], erp_telephone_numbers=[], change_items=[], requests=[], crews=[], violation_limits=[], registered_resources=[], **kw_args):
        """ Initialises a new 'Organisation' instance.
        """
        self.organisation_code = organisation_code
        self.current_status = current_status
        self.status_date = status_date
        self.organisation_type = organisation_type
        self.cost_center_flag = cost_center_flag
        self.profit_center_flag = profit_center_flag
        self.mode = mode
        self.market_role = market_role
        self.opt_out = opt_out
        self.government_id = government_id
        self.industry_id = industry_id
        self.assets = assets
        self.power_system_resources = power_system_resources
        self.customer_data = customer_data
        self.customer_lists = customer_lists
        self.activity_records = activity_records
        self.property = property
        self.parent_organisation = parent_organisation
        self.child_organisation = child_organisation
        self.erp_persons = erp_persons
        self.documents = documents
        self.electronic_addresses = electronic_addresses
        self.locations = locations
        self.erp_telephone_numbers = erp_telephone_numbers
        self.change_items = change_items
        self.requests = requests
        self.crews = crews
        self.violation_limits = violation_limits
        self.registered_resources = registered_resources

        super(Organisation, self).__init__(**kw_args)
    # >>> organisation


class OrgErpPersonRole(Role):
    """ Roles played between Persons and Organisations.
    """
    # Identifiers of the person held by an organisation, such as a government agency (federal, state, province, city, county), financial institutions, etc. 
    client_id = ''

    # <<< org_erp_person_role
    # @generated
    def __init__(self, client_id='', **kw_args):
        """ Initialises a new 'OrgErpPersonRole' instance.
        """
        self.client_id = client_id

        super(OrgErpPersonRole, self).__init__(**kw_args)
    # >>> org_erp_person_role


class DocErpPersonRole(Role):
    """ Roles played between Persons and Documents.
    """
    pass
    # <<< doc_erp_person_role
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'DocErpPersonRole' instance.
        """

        super(DocErpPersonRole, self).__init__(**kw_args)
    # >>> doc_erp_person_role


class OrgOrgRole(Role):
    """ Roles played between Organisations and other Organisations.   This includes role ups for ogranisations, cost centers, profit centers, regulatory reporting, etc.   Note that the parent and child relationship is indicated by the name on each end of the association.
    """
    # Identifiers of the organisation held by another organisation, such as a government agency (federal, state, province, city, county), financial institution (Dun and Bradstreet), etc. 
    client_id = ''

    # <<< org_org_role
    # @generated
    def __init__(self, client_id='', **kw_args):
        """ Initialises a new 'OrgOrgRole' instance.
        """
        self.client_id = client_id

        super(OrgOrgRole, self).__init__(**kw_args)
    # >>> org_org_role


class DocOrgRole(Role):
    """ Roles played between Organisations and Documents.   
    """
    pass
    # <<< doc_org_role
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'DocOrgRole' instance.
        """

        super(DocOrgRole, self).__init__(**kw_args)
    # >>> doc_org_role


class Skill(Document):
    """ Proficiency level of a craft, which is required to operate or maintain a particular type of asset and/or perform certain types of work. 
    """
    #  Type of certification for a Craft such as Master, Standard, Apprentice, etc. 
    skill = ''

    #  Date of certification. 
    date_certified = ''

    # Date and time skill became effective. 
    effective_date = ''

    # Date and time skill certification expires. 
    expiration_date = ''

    # The status at the time of 'statusDateTime'.  Refer to instances of associated ActivityRecords for prior states. 
    status = ''

    # Date and time for which 'status' applies. 
    status_date_time = ''

    # Information about the current 'status'. 
    status_remarks = ''

    erp_person = None

    qualification_requirements = []

    crafts = []

    # <<< skill
    # @generated
    def __init__(self, skill='', date_certified='', effective_date='', expiration_date='', status='', status_date_time='', status_remarks='', erp_person=None, qualification_requirements=[], crafts=[], **kw_args):
        """ Initialises a new 'Skill' instance.
        """
        self.skill = skill
        self.date_certified = date_certified
        self.effective_date = effective_date
        self.expiration_date = expiration_date
        self.status = status
        self.status_date_time = status_date_time
        self.status_remarks = status_remarks
        self.erp_person = erp_person
        self.qualification_requirements = qualification_requirements
        self.crafts = crafts

        super(Skill, self).__init__(**kw_args)
    # >>> skill


class Agreement(Document):
    """ A formal agreement between two parties defining the terms and conditions for a set of services. The specifics of the services are, in turn, defined via one or more service agreements.
    """
    #  Date and time agreement goes into effect. 
    start_date_time = ''

    #  Date and time agreement is terminated. 
    end_date_time = ''

    # Status of the agreement. 
    agreement_status = ''

    # The category of agreement. 
    type_agreement = ''

    # Data agreement was consumated amdong associated ErpPersons and/or ErpOrganisations. 
    sign_date = ''

    # <<< agreement
    # @generated
    def __init__(self, start_date_time='', end_date_time='', agreement_status='', type_agreement='', sign_date='', **kw_args):
        """ Initialises a new 'Agreement' instance.
        """
        self.start_date_time = start_date_time
        self.end_date_time = end_date_time
        self.agreement_status = agreement_status
        self.type_agreement = type_agreement
        self.sign_date = sign_date

        super(Agreement, self).__init__(**kw_args)
    # >>> agreement


class DocDocRole(Role):
    """ Roles played between Documents and other Documents.
    """
    pass
    # <<< doc_doc_role
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'DocDocRole' instance.
        """

        super(DocDocRole, self).__init__(**kw_args)
    # >>> doc_doc_role


class DocLocRole(Role):
    """ Roles played between Documents and Locations.  For example, as ErpAddress is a type of Location and WorkBilling is a type of Document, a role is the address for which to mail the invoice.  As a TroubleTicket is a type of Document, one instance of Location may be the address for which the trouble is reported.
    """
    pass
    # <<< doc_loc_role
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'DocLocRole' instance.
        """

        super(DocLocRole, self).__init__(**kw_args)
    # >>> doc_loc_role


class Craft():
    """ Capability of a person or a crew.  Examples of capability include overhead electric, underground electric, high pressure gas, etc.  Capability ensures necessary knowledged and skills in one or more crafts before being allowed to perform certain types of work.
    """
    skills = []

    erp_persons = []

    capabilities = []

    # <<< craft
    # @generated
    def __init__(self, skills=[], erp_persons=[], capabilities=[], **kw_args):
        """ Initialises a new 'Craft' instance.
        """
        self.skills = skills
        self.erp_persons = erp_persons
        self.capabilities = capabilities

        super(Craft, self).__init__(**kw_args)
    # >>> craft


class OrgLocationRole(Role):
    """ Roles played between Organisations and Locations, for example a service territory or school district.  Note that roles dealing with use of a specific piece of property should be defined based on the relationship between OccupationsOfProperty and Location.
    """
    pass
    # <<< org_location_role
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'OrgLocationRole' instance.
        """

        super(OrgLocationRole, self).__init__(**kw_args)
    # >>> org_location_role


class Assignment(Document):
    """ An assignment is given to an ErpPerson, Crew, Organisation, Equipment Item, Tool, etc. and may be used to perform Work, WorkTasks, Procedures, etc.  TimeSchedules may be set up directly for Assignments or indirectly via the associated WorkTask.  Note that these associations are all inherited through the recursive relationship on Document.
    """
    # Date and time assignment became effective. 
    effective_date = ''

    # Date and time assignment expires. 
    expiration_date = ''

    # <<< assignment
    # @generated
    def __init__(self, effective_date='', expiration_date='', **kw_args):
        """ Initialises a new 'Assignment' instance.
        """
        self.effective_date = effective_date
        self.expiration_date = expiration_date

        super(Assignment, self).__init__(**kw_args)
    # >>> assignment


class TopLevelVersion(object):
 
    version = ''

 
    date = ''

    # <<< top_level_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'TopLevelVersion' instance.
        """
        self.version = version
        self.date = date

        super(TopLevelVersion, self).__init__(**kw_args)
    # >>> top_level_version


# <<< toplevel
# @generated
# >>> toplevel
