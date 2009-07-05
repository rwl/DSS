# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import IdentifiedObject
from cim14.iec61968.common import Document
from cim14 import Element

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61968.Informative.InfCommon"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61968.Informative.InfCommon"

class ScheduledEvent(IdentifiedObject):
    """ Signifies an event to trigger one or more activities, such as reading a meter, recalculating a bill, requesting work, when generating units must be scheduled for maintenance, when a transformer is scheduled to be refurbished, etc.
    """
    # <<< scheduled_event
    # @generated
    def __init__(self, duration=0.0, category='', status=None, activity_record=None, time_point=None, assets=None, schedule_parameter_info=None, document=None, **kw_args):
        """ Initialises a new 'ScheduledEvent' instance.
        """
        # Duration of the scheduled event, for example, the time to ramp between values. 
        self.duration = duration
        # Category of scheduled event. 
        self.category = category
        
        self.status = status
        self._activity_record = None
        self.activity_record = activity_record
        self._time_point = None
        self.time_point = time_point
        self._assets = []
        if assets is None:
            self.assets = []
        else:
            self.assets = assets
        self._schedule_parameter_info = None
        self.schedule_parameter_info = schedule_parameter_info
        self._document = None
        self.document = document

        super(ScheduledEvent, self).__init__(**kw_args)
    # >>> scheduled_event
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< activity_record
    # @generated
    def get_activity_record(self):
        """ 
        """
        return self._activity_record

    def set_activity_record(self, value):
        if self._activity_record is not None:
            self._activity_record._scheduled_event = None
            
        self._activity_record = value
        if self._activity_record is not None:
            self._activity_record._scheduled_event = self
            
    activity_record = property(get_activity_record, set_activity_record)
    # >>> activity_record

    # <<< time_point
    # @generated
    def get_time_point(self):
        """ 
        """
        return self._time_point

    def set_time_point(self, value):
        if self._time_point is not None:
            filtered = [x for x in self.time_point.scheduled_events if x != self]
            self._time_point._scheduled_events = filtered
            
        self._time_point = value
        if self._time_point is not None:
            if self not in self._time_point._scheduled_events:
                self._time_point._scheduled_events.append(self)

    time_point = property(get_time_point, set_time_point)
    # >>> time_point

    # <<< assets
    # @generated
    def get_assets(self):
        """ 
        """
        return self._assets

    def set_assets(self, value):
        for p in self._assets:
            filtered = [q for q in p.scheduled_events if q != self]
            self._assets._scheduled_events = filtered
        for r in value:
            if self not in r._scheduled_events:
                r._scheduled_events.append(self)
        self._assets = value
            
    assets = property(get_assets, set_assets)
    
    def add_assets(self, *assets):
        for obj in assets:
            if self not in obj._scheduled_events:
                obj._scheduled_events.append(self)
            self._assets.append(obj)
        
    def remove_assets(self, *assets):
        for obj in assets:
            if self in obj._scheduled_events:
                obj._scheduled_events.remove(self)
            self._assets.remove(obj)
    # >>> assets

    # <<< schedule_parameter_info
    # @generated
    def get_schedule_parameter_info(self):
        """ 
        """
        return self._schedule_parameter_info

    def set_schedule_parameter_info(self, value):
        if self._schedule_parameter_info is not None:
            filtered = [x for x in self.schedule_parameter_info.scheduled_events if x != self]
            self._schedule_parameter_info._scheduled_events = filtered
            
        self._schedule_parameter_info = value
        if self._schedule_parameter_info is not None:
            if self not in self._schedule_parameter_info._scheduled_events:
                self._schedule_parameter_info._scheduled_events.append(self)

    schedule_parameter_info = property(get_schedule_parameter_info, set_schedule_parameter_info)
    # >>> schedule_parameter_info

    # <<< document
    # @generated
    def get_document(self):
        """ 
        """
        return self._document

    def set_document(self, value):
        if self._document is not None:
            filtered = [x for x in self.document.scheduled_events if x != self]
            self._document._scheduled_events = filtered
            
        self._document = value
        if self._document is not None:
            if self not in self._document._scheduled_events:
                self._document._scheduled_events.append(self)

    document = property(get_document, set_document)
    # >>> document



class ChangeItem(IdentifiedObject):
    """ Description for a single change within an ordered list of changes.
    """
    # <<< change_item
    # @generated
    def __init__(self, kind='modify', sequence_number=0, status=None, power_system_resource=None, measurement=None, document=None, change_set=None, network_data_set=None, gml_selector=None, location=None, erp_person=None, asset=None, organisation=None, gml_observation=None, **kw_args):
        """ Initialises a new 'ChangeItem' instance.
        """
        # Kind of change for the associated object. Values are: "modify", "add", "delete"
        self.kind = kind
        # Relative order of this ChangeItem in an ordered sequence of changes. 
        self.sequence_number = sequence_number
        
        self.status = status
        self._power_system_resource = None
        self.power_system_resource = power_system_resource
        self._measurement = None
        self.measurement = measurement
        self._document = None
        self.document = document
        self._change_set = None
        self.change_set = change_set
        self._network_data_set = None
        self.network_data_set = network_data_set
        self._gml_selector = None
        self.gml_selector = gml_selector
        self._location = None
        self.location = location
        self._erp_person = None
        self.erp_person = erp_person
        self._asset = None
        self.asset = asset
        self._organisation = None
        self.organisation = organisation
        self._gml_observation = None
        self.gml_observation = gml_observation

        super(ChangeItem, self).__init__(**kw_args)
    # >>> change_item
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< power_system_resource
    # @generated
    def get_power_system_resource(self):
        """ 
        """
        return self._power_system_resource

    def set_power_system_resource(self, value):
        if self._power_system_resource is not None:
            filtered = [x for x in self.power_system_resource.change_items if x != self]
            self._power_system_resource._change_items = filtered
            
        self._power_system_resource = value
        if self._power_system_resource is not None:
            if self not in self._power_system_resource._change_items:
                self._power_system_resource._change_items.append(self)

    power_system_resource = property(get_power_system_resource, set_power_system_resource)
    # >>> power_system_resource

    # <<< measurement
    # @generated
    def get_measurement(self):
        """ 
        """
        return self._measurement

    def set_measurement(self, value):
        if self._measurement is not None:
            filtered = [x for x in self.measurement.change_items if x != self]
            self._measurement._change_items = filtered
            
        self._measurement = value
        if self._measurement is not None:
            if self not in self._measurement._change_items:
                self._measurement._change_items.append(self)

    measurement = property(get_measurement, set_measurement)
    # >>> measurement

    # <<< document
    # @generated
    def get_document(self):
        """ 
        """
        return self._document

    def set_document(self, value):
        if self._document is not None:
            filtered = [x for x in self.document.change_items if x != self]
            self._document._change_items = filtered
            
        self._document = value
        if self._document is not None:
            if self not in self._document._change_items:
                self._document._change_items.append(self)

    document = property(get_document, set_document)
    # >>> document

    # <<< change_set
    # @generated
    def get_change_set(self):
        """ 
        """
        return self._change_set

    def set_change_set(self, value):
        if self._change_set is not None:
            filtered = [x for x in self.change_set.change_items if x != self]
            self._change_set._change_items = filtered
            
        self._change_set = value
        if self._change_set is not None:
            if self not in self._change_set._change_items:
                self._change_set._change_items.append(self)

    change_set = property(get_change_set, set_change_set)
    # >>> change_set

    # <<< network_data_set
    # @generated
    def get_network_data_set(self):
        """ 
        """
        return self._network_data_set

    def set_network_data_set(self, value):
        if self._network_data_set is not None:
            filtered = [x for x in self.network_data_set.change_items if x != self]
            self._network_data_set._change_items = filtered
            
        self._network_data_set = value
        if self._network_data_set is not None:
            if self not in self._network_data_set._change_items:
                self._network_data_set._change_items.append(self)

    network_data_set = property(get_network_data_set, set_network_data_set)
    # >>> network_data_set

    # <<< gml_selector
    # @generated
    def get_gml_selector(self):
        """ 
        """
        return self._gml_selector

    def set_gml_selector(self, value):
        if self._gml_selector is not None:
            filtered = [x for x in self.gml_selector.change_items if x != self]
            self._gml_selector._change_items = filtered
            
        self._gml_selector = value
        if self._gml_selector is not None:
            if self not in self._gml_selector._change_items:
                self._gml_selector._change_items.append(self)

    gml_selector = property(get_gml_selector, set_gml_selector)
    # >>> gml_selector

    # <<< location
    # @generated
    def get_location(self):
        """ 
        """
        return self._location

    def set_location(self, value):
        if self._location is not None:
            filtered = [x for x in self.location.change_items if x != self]
            self._location._change_items = filtered
            
        self._location = value
        if self._location is not None:
            if self not in self._location._change_items:
                self._location._change_items.append(self)

    location = property(get_location, set_location)
    # >>> location

    # <<< erp_person
    # @generated
    def get_erp_person(self):
        """ 
        """
        return self._erp_person

    def set_erp_person(self, value):
        if self._erp_person is not None:
            filtered = [x for x in self.erp_person.change_items if x != self]
            self._erp_person._change_items = filtered
            
        self._erp_person = value
        if self._erp_person is not None:
            if self not in self._erp_person._change_items:
                self._erp_person._change_items.append(self)

    erp_person = property(get_erp_person, set_erp_person)
    # >>> erp_person

    # <<< asset
    # @generated
    def get_asset(self):
        """ 
        """
        return self._asset

    def set_asset(self, value):
        if self._asset is not None:
            filtered = [x for x in self.asset.change_items if x != self]
            self._asset._change_items = filtered
            
        self._asset = value
        if self._asset is not None:
            if self not in self._asset._change_items:
                self._asset._change_items.append(self)

    asset = property(get_asset, set_asset)
    # >>> asset

    # <<< organisation
    # @generated
    def get_organisation(self):
        """ 
        """
        return self._organisation

    def set_organisation(self, value):
        if self._organisation is not None:
            filtered = [x for x in self.organisation.change_items if x != self]
            self._organisation._change_items = filtered
            
        self._organisation = value
        if self._organisation is not None:
            if self not in self._organisation._change_items:
                self._organisation._change_items.append(self)

    organisation = property(get_organisation, set_organisation)
    # >>> organisation

    # <<< gml_observation
    # @generated
    def get_gml_observation(self):
        """ 
        """
        return self._gml_observation

    def set_gml_observation(self, value):
        if self._gml_observation is not None:
            filtered = [x for x in self.gml_observation.change_items if x != self]
            self._gml_observation._change_items = filtered
            
        self._gml_observation = value
        if self._gml_observation is not None:
            if self not in self._gml_observation._change_items:
                self._gml_observation._change_items.append(self)

    gml_observation = property(get_gml_observation, set_gml_observation)
    # >>> gml_observation



class Role(IdentifiedObject):
    """ Enumeration of potential roles that might be played by one object relative to another.
    """
    # <<< role
    # @generated
    def __init__(self, category='', status=None, **kw_args):
        """ Initialises a new 'Role' instance.
        """
        # Category of role. 
        self.category = category
        
        self.status = status

        super(Role, self).__init__(**kw_args)
    # >>> role
        
    # <<< status
    # @generated
    status = None
    # >>> status



class Craft(IdentifiedObject):
    """ Craft of a person or a crew. Examples include overhead electric, underground electric, high pressure gas, etc. This ensures necessary knowledge and skills before being allowed to perform certain types of work.
    """
    # <<< craft
    # @generated
    def __init__(self, category='', status=None, erp_persons=None, capabilities=None, skills=None, **kw_args):
        """ Initialises a new 'Craft' instance.
        """
        # Category by utility's work mangement standards and practices. 
        self.category = category
        
        self.status = status
        self._erp_persons = []
        if erp_persons is None:
            self.erp_persons = []
        else:
            self.erp_persons = erp_persons
        self._capabilities = []
        if capabilities is None:
            self.capabilities = []
        else:
            self.capabilities = capabilities
        self._skills = []
        if skills is None:
            self.skills = []
        else:
            self.skills = skills

        super(Craft, self).__init__(**kw_args)
    # >>> craft
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< erp_persons
    # @generated
    def get_erp_persons(self):
        """ 
        """
        return self._erp_persons

    def set_erp_persons(self, value):
        for p in self._erp_persons:
            filtered = [q for q in p.crafts if q != self]
            self._erp_persons._crafts = filtered
        for r in value:
            if self not in r._crafts:
                r._crafts.append(self)
        self._erp_persons = value
            
    erp_persons = property(get_erp_persons, set_erp_persons)
    
    def add_erp_persons(self, *erp_persons):
        for obj in erp_persons:
            if self not in obj._crafts:
                obj._crafts.append(self)
            self._erp_persons.append(obj)
        
    def remove_erp_persons(self, *erp_persons):
        for obj in erp_persons:
            if self in obj._crafts:
                obj._crafts.remove(self)
            self._erp_persons.remove(obj)
    # >>> erp_persons

    # <<< capabilities
    # @generated
    def get_capabilities(self):
        """ 
        """
        return self._capabilities

    def set_capabilities(self, value):
        for p in self._capabilities:
            filtered = [q for q in p.crafts if q != self]
            self._capabilities._crafts = filtered
        for r in value:
            if self not in r._crafts:
                r._crafts.append(self)
        self._capabilities = value
            
    capabilities = property(get_capabilities, set_capabilities)
    
    def add_capabilities(self, *capabilities):
        for obj in capabilities:
            if self not in obj._crafts:
                obj._crafts.append(self)
            self._capabilities.append(obj)
        
    def remove_capabilities(self, *capabilities):
        for obj in capabilities:
            if self in obj._crafts:
                obj._crafts.remove(self)
            self._capabilities.remove(obj)
    # >>> capabilities

    # <<< skills
    # @generated
    def get_skills(self):
        """ 
        """
        return self._skills

    def set_skills(self, value):
        for p in self._skills:
            filtered = [q for q in p.crafts if q != self]
            self._skills._crafts = filtered
        for r in value:
            if self not in r._crafts:
                r._crafts.append(self)
        self._skills = value
            
    skills = property(get_skills, set_skills)
    
    def add_skills(self, *skills):
        for obj in skills:
            if self not in obj._crafts:
                obj._crafts.append(self)
            self._skills.append(obj)
        
    def remove_skills(self, *skills):
        for obj in skills:
            if self in obj._crafts:
                obj._crafts.remove(self)
            self._skills.remove(obj)
    # >>> skills



class ScheduleParameterInfo(IdentifiedObject):
    """ Schedule parameters for an activity that is to occur, is occurring, or has completed.
    """
    # <<< schedule_parameter_info
    # @generated
    def __init__(self, estimated_window='', status=None, requested_window=None, scheduled_events=None, for_inspection_data_set=None, documents=None, **kw_args):
        """ Initialises a new 'ScheduleParameterInfo' instance.
        """
        # Estimated date and time for activity execution (with earliest possibility of activity initiation and latest possibility of activity completion). 
        self.estimated_window = estimated_window
        
        self.status = status
        self.requested_window = requested_window
        self._scheduled_events = []
        if scheduled_events is None:
            self.scheduled_events = []
        else:
            self.scheduled_events = scheduled_events
        self._for_inspection_data_set = None
        self.for_inspection_data_set = for_inspection_data_set
        self._documents = []
        if documents is None:
            self.documents = []
        else:
            self.documents = documents

        super(ScheduleParameterInfo, self).__init__(**kw_args)
    # >>> schedule_parameter_info
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< requested_window
    # @generated
    # Requested date and time interval for activity execution.
    requested_window = None
    # >>> requested_window

    # <<< scheduled_events
    # @generated
    def get_scheduled_events(self):
        """ 
        """
        return self._scheduled_events

    def set_scheduled_events(self, value):
        for x in self._scheduled_events:
            x.schedule_parameter_info = None
        for y in value:
            y.schedule_parameter_info = self
        self._scheduled_events = value
            
    scheduled_events = property(get_scheduled_events, set_scheduled_events)
    
    def add_scheduled_events(self, *scheduled_events):
        for obj in scheduled_events:
            obj._schedule_parameter_info = self
            if obj not in self._scheduled_events:
                self._scheduled_events.append(obj)
        
    def remove_scheduled_events(self, *scheduled_events):
        for obj in scheduled_events:
            obj._schedule_parameter_info = None
            self._scheduled_events.remove(obj)
    # >>> scheduled_events

    # <<< for_inspection_data_set
    # @generated
    def get_for_inspection_data_set(self):
        """ 
        """
        return self._for_inspection_data_set

    def set_for_inspection_data_set(self, value):
        if self._for_inspection_data_set is not None:
            filtered = [x for x in self.for_inspection_data_set.according_to_schedules if x != self]
            self._for_inspection_data_set._according_to_schedules = filtered
            
        self._for_inspection_data_set = value
        if self._for_inspection_data_set is not None:
            if self not in self._for_inspection_data_set._according_to_schedules:
                self._for_inspection_data_set._according_to_schedules.append(self)

    for_inspection_data_set = property(get_for_inspection_data_set, set_for_inspection_data_set)
    # >>> for_inspection_data_set

    # <<< documents
    # @generated
    def get_documents(self):
        """ 
        """
        return self._documents

    def set_documents(self, value):
        for p in self._documents:
            filtered = [q for q in p.schedule_parameter_infos if q != self]
            self._documents._schedule_parameter_infos = filtered
        for r in value:
            if self not in r._schedule_parameter_infos:
                r._schedule_parameter_infos.append(self)
        self._documents = value
            
    documents = property(get_documents, set_documents)
    
    def add_documents(self, *documents):
        for obj in documents:
            if self not in obj._schedule_parameter_infos:
                obj._schedule_parameter_infos.append(self)
            self._documents.append(obj)
        
    def remove_documents(self, *documents):
        for obj in documents:
            if self in obj._schedule_parameter_infos:
                obj._schedule_parameter_infos.remove(self)
            self._documents.remove(obj)
    # >>> documents



class Diagram(Document):
    """ GML and/or other means are used for rendering objects on various types of displays(geographic, schematic, other) and maps associated with various coordinate systems.
    """
    # <<< diagram
    # @generated
    def __init__(self, kind='geographic', gml_coordinate_system=None, gml_diagram_objects=None, design_locations=None, **kw_args):
        """ Initialises a new 'Diagram' instance.
        """
        # Kind of this diagram. Values are: "geographic", "design_sketch", "schematic", "other", "internal_view"
        self.kind = kind
        
        self._gml_coordinate_system = None
        self.gml_coordinate_system = gml_coordinate_system
        self._gml_diagram_objects = []
        if gml_diagram_objects is None:
            self.gml_diagram_objects = []
        else:
            self.gml_diagram_objects = gml_diagram_objects
        self._design_locations = []
        if design_locations is None:
            self.design_locations = []
        else:
            self.design_locations = design_locations

        super(Diagram, self).__init__(**kw_args)
    # >>> diagram
        
    # <<< gml_coordinate_system
    # @generated
    def get_gml_coordinate_system(self):
        """ 
        """
        return self._gml_coordinate_system

    def set_gml_coordinate_system(self, value):
        if self._gml_coordinate_system is not None:
            filtered = [x for x in self.gml_coordinate_system.diagrams if x != self]
            self._gml_coordinate_system._diagrams = filtered
            
        self._gml_coordinate_system = value
        if self._gml_coordinate_system is not None:
            if self not in self._gml_coordinate_system._diagrams:
                self._gml_coordinate_system._diagrams.append(self)

    gml_coordinate_system = property(get_gml_coordinate_system, set_gml_coordinate_system)
    # >>> gml_coordinate_system

    # <<< gml_diagram_objects
    # @generated
    def get_gml_diagram_objects(self):
        """ 
        """
        return self._gml_diagram_objects

    def set_gml_diagram_objects(self, value):
        for p in self._gml_diagram_objects:
            filtered = [q for q in p.diagrams if q != self]
            self._gml_diagram_objects._diagrams = filtered
        for r in value:
            if self not in r._diagrams:
                r._diagrams.append(self)
        self._gml_diagram_objects = value
            
    gml_diagram_objects = property(get_gml_diagram_objects, set_gml_diagram_objects)
    
    def add_gml_diagram_objects(self, *gml_diagram_objects):
        for obj in gml_diagram_objects:
            if self not in obj._diagrams:
                obj._diagrams.append(self)
            self._gml_diagram_objects.append(obj)
        
    def remove_gml_diagram_objects(self, *gml_diagram_objects):
        for obj in gml_diagram_objects:
            if self in obj._diagrams:
                obj._diagrams.remove(self)
            self._gml_diagram_objects.remove(obj)
    # >>> gml_diagram_objects

    # <<< design_locations
    # @generated
    def get_design_locations(self):
        """ 
        """
        return self._design_locations

    def set_design_locations(self, value):
        for p in self._design_locations:
            filtered = [q for q in p.diagrams if q != self]
            self._design_locations._diagrams = filtered
        for r in value:
            if self not in r._diagrams:
                r._diagrams.append(self)
        self._design_locations = value
            
    design_locations = property(get_design_locations, set_design_locations)
    
    def add_design_locations(self, *design_locations):
        for obj in design_locations:
            if self not in obj._diagrams:
                obj._diagrams.append(self)
            self._design_locations.append(obj)
        
    def remove_design_locations(self, *design_locations):
        for obj in design_locations:
            if self in obj._diagrams:
                obj._diagrams.remove(self)
            self._design_locations.remove(obj)
    # >>> design_locations



class BankAccount(Document):
    """ Bank account.
    """
    # <<< bank_account
    # @generated
    def __init__(self, account_number='', bank=None, service_supplier=None, bank_statements=None, **kw_args):
        """ Initialises a new 'BankAccount' instance.
        """
        # Account reference number. 
        self.account_number = account_number
        
        self._bank = None
        self.bank = bank
        self._service_supplier = None
        self.service_supplier = service_supplier
        self._bank_statements = []
        if bank_statements is None:
            self.bank_statements = []
        else:
            self.bank_statements = bank_statements

        super(BankAccount, self).__init__(**kw_args)
    # >>> bank_account
        
    # <<< bank
    # @generated
    def get_bank(self):
        """ Bank that provides this BankAccount.
        """
        return self._bank

    def set_bank(self, value):
        if self._bank is not None:
            filtered = [x for x in self.bank.bank_accounts if x != self]
            self._bank._bank_accounts = filtered
            
        self._bank = value
        if self._bank is not None:
            if self not in self._bank._bank_accounts:
                self._bank._bank_accounts.append(self)

    bank = property(get_bank, set_bank)
    # >>> bank

    # <<< service_supplier
    # @generated
    def get_service_supplier(self):
        """ ServiceSupplier that is owner of this BankAccount.
        """
        return self._service_supplier

    def set_service_supplier(self, value):
        if self._service_supplier is not None:
            filtered = [x for x in self.service_supplier.bank_accounts if x != self]
            self._service_supplier._bank_accounts = filtered
            
        self._service_supplier = value
        if self._service_supplier is not None:
            if self not in self._service_supplier._bank_accounts:
                self._service_supplier._bank_accounts.append(self)

    service_supplier = property(get_service_supplier, set_service_supplier)
    # >>> service_supplier

    # <<< bank_statements
    # @generated
    def get_bank_statements(self):
        """ All bank statements generated from this bank account.
        """
        return self._bank_statements

    def set_bank_statements(self, value):
        for x in self._bank_statements:
            x.bank_account = None
        for y in value:
            y.bank_account = self
        self._bank_statements = value
            
    bank_statements = property(get_bank_statements, set_bank_statements)
    
    def add_bank_statements(self, *bank_statements):
        for obj in bank_statements:
            obj._bank_account = self
            if obj not in self._bank_statements:
                self._bank_statements.append(obj)
        
    def remove_bank_statements(self, *bank_statements):
        for obj in bank_statements:
            obj._bank_account = None
            self._bank_statements.remove(obj)
    # >>> bank_statements



class BusinessRole(IdentifiedObject):
    """ A business role that this organisation plays. A single organisation typically performs many functions, each one described as a role.
    """
    # <<< business_role
    # @generated
    def __init__(self, category='', status=None, organisations=None, **kw_args):
        """ Initialises a new 'BusinessRole' instance.
        """
        # Category by utility's corporate standards and practices. 
        self.category = category
        
        self.status = status
        self._organisations = []
        if organisations is None:
            self.organisations = []
        else:
            self.organisations = organisations

        super(BusinessRole, self).__init__(**kw_args)
    # >>> business_role
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< organisations
    # @generated
    def get_organisations(self):
        """ 
        """
        return self._organisations

    def set_organisations(self, value):
        for p in self._organisations:
            filtered = [q for q in p.business_roles if q != self]
            self._organisations._business_roles = filtered
        for r in value:
            if self not in r._business_roles:
                r._business_roles.append(self)
        self._organisations = value
            
    organisations = property(get_organisations, set_organisations)
    
    def add_organisations(self, *organisations):
        for obj in organisations:
            if self not in obj._business_roles:
                obj._business_roles.append(self)
            self._organisations.append(obj)
        
    def remove_organisations(self, *organisations):
        for obj in organisations:
            if self in obj._business_roles:
                obj._business_roles.remove(self)
            self._organisations.remove(obj)
    # >>> organisations



class Skill(Document):
    """ Proficiency level of a craft, which is required to operate or maintain a particular type of asset and/or perform certain types of work.
    """
    # <<< skill
    # @generated
    def __init__(self, expiration_date_time='', effective_date_time='', level='other', certified_date='', crafts=None, qualification_requirements=None, erp_person=None, **kw_args):
        """ Initialises a new 'Skill' instance.
        """
        # Date and time skill certification expires. 
        self.expiration_date_time = expiration_date_time
        # Date and time skill became effective. 
        self.effective_date_time = effective_date_time
        # Level of skill for a Craft. Values are: "other", "apprentice", "standard", "master"
        self.level = level
        # Date of certification. 
        self.certified_date = certified_date
        
        self._crafts = []
        if crafts is None:
            self.crafts = []
        else:
            self.crafts = crafts
        self._qualification_requirements = []
        if qualification_requirements is None:
            self.qualification_requirements = []
        else:
            self.qualification_requirements = qualification_requirements
        self._erp_person = None
        self.erp_person = erp_person

        super(Skill, self).__init__(**kw_args)
    # >>> skill
        
    # <<< crafts
    # @generated
    def get_crafts(self):
        """ 
        """
        return self._crafts

    def set_crafts(self, value):
        for p in self._crafts:
            filtered = [q for q in p.skills if q != self]
            self._crafts._skills = filtered
        for r in value:
            if self not in r._skills:
                r._skills.append(self)
        self._crafts = value
            
    crafts = property(get_crafts, set_crafts)
    
    def add_crafts(self, *crafts):
        for obj in crafts:
            if self not in obj._skills:
                obj._skills.append(self)
            self._crafts.append(obj)
        
    def remove_crafts(self, *crafts):
        for obj in crafts:
            if self in obj._skills:
                obj._skills.remove(self)
            self._crafts.remove(obj)
    # >>> crafts

    # <<< qualification_requirements
    # @generated
    def get_qualification_requirements(self):
        """ 
        """
        return self._qualification_requirements

    def set_qualification_requirements(self, value):
        for p in self._qualification_requirements:
            filtered = [q for q in p.skills if q != self]
            self._qualification_requirements._skills = filtered
        for r in value:
            if self not in r._skills:
                r._skills.append(self)
        self._qualification_requirements = value
            
    qualification_requirements = property(get_qualification_requirements, set_qualification_requirements)
    
    def add_qualification_requirements(self, *qualification_requirements):
        for obj in qualification_requirements:
            if self not in obj._skills:
                obj._skills.append(self)
            self._qualification_requirements.append(obj)
        
    def remove_qualification_requirements(self, *qualification_requirements):
        for obj in qualification_requirements:
            if self in obj._skills:
                obj._skills.remove(self)
            self._qualification_requirements.remove(obj)
    # >>> qualification_requirements

    # <<< erp_person
    # @generated
    def get_erp_person(self):
        """ 
        """
        return self._erp_person

    def set_erp_person(self, value):
        if self._erp_person is not None:
            filtered = [x for x in self.erp_person.skills if x != self]
            self._erp_person._skills = filtered
            
        self._erp_person = value
        if self._erp_person is not None:
            if self not in self._erp_person._skills:
                self._erp_person._skills.append(self)

    erp_person = property(get_erp_person, set_erp_person)
    # >>> erp_person



class BusinessPlan(Document):
    """ A BusinessPlan is an organized sequence of predetermined actions required to complete a future organizational objective. It is a type of document that typically references a schedule, physical and/or logical resources (assets and/or PowerSystemResources), locations, etc.
    """
    pass
    # <<< business_plan
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'BusinessPlan' instance.
        """
        

        super(BusinessPlan, self).__init__(**kw_args)
    # >>> business_plan
        


class MarketRole(IdentifiedObject):
    """ Role an organisation plays in a market. Examples include one or more of values defined in MarketRoleKind.
    """
    # <<< market_role
    # @generated
    def __init__(self, kind='energy_service_consumer', status=None, organisations=None, **kw_args):
        """ Initialises a new 'MarketRole' instance.
        """
        # Kind of role an organisation plays in a market. Values are: "energy_service_consumer", "transmission_service_provider", "transmission_owner", "interchange_authority", "transmission_operator", "transmission_planner", "standards_developer", "planning_authority", "load_serving_entity", "competitive_retailer", "compliance_monitor", "resource_planner", "generator_owner", "distribution_provider", "reliability_authority", "balancing_authority", "other", "purchasing_selling_entity", "generator_operator"
        self.kind = kind
        
        self.status = status
        self._organisations = []
        if organisations is None:
            self.organisations = []
        else:
            self.organisations = organisations

        super(MarketRole, self).__init__(**kw_args)
    # >>> market_role
        
    # <<< status
    # @generated
    status = None
    # >>> status

    # <<< organisations
    # @generated
    def get_organisations(self):
        """ 
        """
        return self._organisations

    def set_organisations(self, value):
        for p in self._organisations:
            filtered = [q for q in p.market_roles if q != self]
            self._organisations._market_roles = filtered
        for r in value:
            if self not in r._market_roles:
                r._market_roles.append(self)
        self._organisations = value
            
    organisations = property(get_organisations, set_organisations)
    
    def add_organisations(self, *organisations):
        for obj in organisations:
            if self not in obj._market_roles:
                obj._market_roles.append(self)
            self._organisations.append(obj)
        
    def remove_organisations(self, *organisations):
        for obj in organisations:
            if self in obj._market_roles:
                obj._market_roles.remove(self)
            self._organisations.remove(obj)
    # >>> organisations



class Ratio(Element):
    """ Fraction specified explicitly with a numerator and denominator, which can be used to calculate the quotient.
    """
    # <<< ratio
    # @generated
    def __init__(self, numerator=0.0, denominator=0.0, **kw_args):
        """ Initialises a new 'Ratio' instance.
        """
        # The part of a fraction that is above the line and signifies the number to be divided by the denominator. 
        self.numerator = numerator
        # The part of a fraction that is below the line and that functions as the divisor of the numerator. 
        self.denominator = denominator
        

        super(Ratio, self).__init__(**kw_args)
    # >>> ratio
        


class DocDocRole(Role):
    """ Roles played between Documents and other Documents.
    """
    # <<< doc_doc_role
    # @generated
    def __init__(self, to_document=None, from_document=None, **kw_args):
        """ Initialises a new 'DocDocRole' instance.
        """
        
        self._to_document = None
        self.to_document = to_document
        self._from_document = None
        self.from_document = from_document

        super(DocDocRole, self).__init__(**kw_args)
    # >>> doc_doc_role
        
    # <<< to_document
    # @generated
    def get_to_document(self):
        """ 
        """
        return self._to_document

    def set_to_document(self, value):
        if self._to_document is not None:
            filtered = [x for x in self.to_document.from_document_roles if x != self]
            self._to_document._from_document_roles = filtered
            
        self._to_document = value
        if self._to_document is not None:
            if self not in self._to_document._from_document_roles:
                self._to_document._from_document_roles.append(self)

    to_document = property(get_to_document, set_to_document)
    # >>> to_document

    # <<< from_document
    # @generated
    def get_from_document(self):
        """ 
        """
        return self._from_document

    def set_from_document(self, value):
        if self._from_document is not None:
            filtered = [x for x in self.from_document.to_document_roles if x != self]
            self._from_document._to_document_roles = filtered
            
        self._from_document = value
        if self._from_document is not None:
            if self not in self._from_document._to_document_roles:
                self._from_document._to_document_roles.append(self)

    from_document = property(get_from_document, set_from_document)
    # >>> from_document



class DocPsrRole(Role):
    """ Potential roles that might played by a document relative to a type of PowerSystemResource.
    """
    # <<< doc_psr_role
    # @generated
    def __init__(self, document=None, power_system_resource=None, **kw_args):
        """ Initialises a new 'DocPsrRole' instance.
        """
        
        self._document = None
        self.document = document
        self._power_system_resource = None
        self.power_system_resource = power_system_resource

        super(DocPsrRole, self).__init__(**kw_args)
    # >>> doc_psr_role
        
    # <<< document
    # @generated
    def get_document(self):
        """ 
        """
        return self._document

    def set_document(self, value):
        if self._document is not None:
            filtered = [x for x in self.document.power_system_resource_roles if x != self]
            self._document._power_system_resource_roles = filtered
            
        self._document = value
        if self._document is not None:
            if self not in self._document._power_system_resource_roles:
                self._document._power_system_resource_roles.append(self)

    document = property(get_document, set_document)
    # >>> document

    # <<< power_system_resource
    # @generated
    def get_power_system_resource(self):
        """ 
        """
        return self._power_system_resource

    def set_power_system_resource(self, value):
        if self._power_system_resource is not None:
            filtered = [x for x in self.power_system_resource.document_roles if x != self]
            self._power_system_resource._document_roles = filtered
            
        self._power_system_resource = value
        if self._power_system_resource is not None:
            if self not in self._power_system_resource._document_roles:
                self._power_system_resource._document_roles.append(self)

    power_system_resource = property(get_power_system_resource, set_power_system_resource)
    # >>> power_system_resource



class Map(Diagram):
    """ A type of diagram that is usually printed on paper. It normally depicts part of the earth's surface, showing utility assets, right of ways, topological data, coordinates, grids, etc. Maps vary depending on whether they are used for dispatch, design, schematic, etc.
    """
    # <<< map
    # @generated
    def __init__(self, page_number=0, map_loc_grid='', **kw_args):
        """ Initialises a new 'Map' instance.
        """
        # Page number for particular set of maps specified by 'category'. 
        self.page_number = page_number
        # Map grid number. 
        self.map_loc_grid = map_loc_grid
        

        super(Map, self).__init__(**kw_args)
    # >>> map
        


# <<< inf_common
# @generated
# >>> inf_common
