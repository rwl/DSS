# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.
""" General package for document inheritance.
"""

from  import 

# <<< imports
# @generated
# >>> imports

class Document():
    """  Parent class for definition of document subclasses. Documents are units of information exchange. Documents will frequently contain references to other objects, such as assets, people and power system resources.
    """
    #  Type of document. 
    doc_type = ''

    # Time and date the document was last modified. Documents may potentially be modified many times during their lifetime. 
    last_modified = ''

    #  Comments associateed with document. 
    comments = ''

    #  Status of document. 
    doc_status = ''

    # Date and time for which 'docStatus' applies. 
    doc_status_date = ''

    #  Revision number for the document. 
    revision_number = ''

    # Main subject for this type of document.  Sometimes referred to as a document subtype. 
    subject = ''

    #  Title of document. 
    doc_title = ''

    # Date and time that this document was created. 
    created_date_time = ''

    power_system_resources = []

    assets = []

    collections = []

    measurements = []

    scheduled_events = []

    activity_records = []

    erp_persons = []

    organisations = []

    from_documents = []

    to_documents = []

    locations = []

    electronic_address = None

    change_items = []

    schedule_parameters = []

    # <<< document
    # @generated
    def __init__(self, doc_type='', last_modified='', comments='', doc_status='', doc_status_date='', revision_number='', subject='', doc_title='', created_date_time='', power_system_resources=[], assets=[], collections=[], measurements=[], scheduled_events=[], activity_records=[], erp_persons=[], organisations=[], from_documents=[], to_documents=[], locations=[], electronic_address=None, change_items=[], schedule_parameters=[], **kw_args):
        """ Initialises a new 'Document' instance.
        """
        self.doc_type = doc_type
        self.last_modified = last_modified
        self.comments = comments
        self.doc_status = doc_status
        self.doc_status_date = doc_status_date
        self.revision_number = revision_number
        self.subject = subject
        self.doc_title = doc_title
        self.created_date_time = created_date_time
        self.power_system_resources = power_system_resources
        self.assets = assets
        self.collections = collections
        self.measurements = measurements
        self.scheduled_events = scheduled_events
        self.activity_records = activity_records
        self.erp_persons = erp_persons
        self.organisations = organisations
        self.from_documents = from_documents
        self.to_documents = to_documents
        self.locations = locations
        self.electronic_address = electronic_address
        self.change_items = change_items
        self.schedule_parameters = schedule_parameters

        super(Document, self).__init__(**kw_args)
    # >>> document


class DocumentationIheritanceVersion(object):
 
    version = ''

 
    date = ''

    # <<< documentation_iheritance_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'DocumentationIheritanceVersion' instance.
        """
        self.version = version
        self.date = date

        super(DocumentationIheritanceVersion, self).__init__(**kw_args)
    # >>> documentation_iheritance_version


# <<< documentinheritance
# @generated
# >>> documentinheritance
