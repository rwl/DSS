# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import IdentifiedObject
from cim14.iec61970.core import IrregularIntervalSchedule

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61970.Outage"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61970.Outage"

class SwitchingOperation(IdentifiedObject):
    """ A SwitchingOperation is used to define individual switch operations for an OutageSchedule. This OutageSchedule may be associated with another item of Substation such as a Transformer, Line, or Generator; or with the Switch itself as a PowerSystemResource. A Switch may be referenced by many OutageSchedules.
    """
    # <<< switching_operation
    # @generated
    def __init__(self, new_state='open', operation_time='', outage_schedule=None, switches=None, **kw_args):
        """ Initialises a new 'SwitchingOperation' instance.
        """
        # The switch position that shall result from this SwitchingOperation Values are: "open", "close"
        self.new_state = new_state
        # Time of operation in same units as OutageSchedule.xAxixUnits. 
        self.operation_time = operation_time
        
        self._outage_schedule = None
        self.outage_schedule = outage_schedule
        self._switches = []
        if switches is None:
            self.switches = []
        else:
            self.switches = switches

        super(SwitchingOperation, self).__init__(**kw_args)
    # >>> switching_operation
        
    # <<< outage_schedule
    # @generated
    def get_outage_schedule(self):
        """ An OutageSchedule may operate many switches.
        """
        return self._outage_schedule

    def set_outage_schedule(self, value):
        if self._outage_schedule is not None:
            filtered = [x for x in self.outage_schedule.switching_operations if x != self]
            self._outage_schedule._switching_operations = filtered
            
        self._outage_schedule = value
        if self._outage_schedule is not None:
            if self not in self._outage_schedule._switching_operations:
                self._outage_schedule._switching_operations.append(self)

    outage_schedule = property(get_outage_schedule, set_outage_schedule)
    # >>> outage_schedule

    # <<< switches
    # @generated
    def get_switches(self):
        """ A switch may be operated by many schedules.
        """
        return self._switches

    def set_switches(self, value):
        for p in self.switches:
            filtered = [q for q in p.switching_operations if q != self]
            p._switching_operations = filtered
        for r in value:
            if self not in r._switching_operations:
                r._switching_operations.append(self)
        self._switches = value
            
    switches = property(get_switches, set_switches)
    
    def add_switches(self, *switches):
        for obj in switches:
            if self not in obj._switching_operations:
                obj._switching_operations.append(self)
            self._switches.append(obj)
        
    def remove_switches(self, *switches):
        for obj in switches:
            if self in obj._switching_operations:
                obj._switching_operations.remove(self)
            self._switches.remove(obj)
    # >>> switches



class OutageSchedule(IrregularIntervalSchedule):
    """ The period of time that a piece of equipment is out of service, for example, for maintenance or testing; including the equipment's active power rating while under maintenance. The X-axis represents absolute time and the Y-axis represents the equipment's available rating while out of service.
    """
    # <<< outage_schedule
    # @generated
    def __init__(self, planned_outage=None, power_system_resource=None, switching_operations=None, **kw_args):
        """ Initialises a new 'OutageSchedule' instance.
        """
        
        self._planned_outage = None
        self.planned_outage = planned_outage
        self._power_system_resource = None
        self.power_system_resource = power_system_resource
        self._switching_operations = []
        if switching_operations is None:
            self.switching_operations = []
        else:
            self.switching_operations = switching_operations

        super(OutageSchedule, self).__init__(**kw_args)
    # >>> outage_schedule
        
    # <<< planned_outage
    # @generated
    def get_planned_outage(self):
        """ 
        """
        return self._planned_outage

    def set_planned_outage(self, value):
        if self._planned_outage is not None:
            filtered = [x for x in self.planned_outage.outage_schedules if x != self]
            self._planned_outage._outage_schedules = filtered
            
        self._planned_outage = value
        if self._planned_outage is not None:
            if self not in self._planned_outage._outage_schedules:
                self._planned_outage._outage_schedules.append(self)

    planned_outage = property(get_planned_outage, set_planned_outage)
    # >>> planned_outage

    # <<< power_system_resource
    # @generated
    def get_power_system_resource(self):
        """ A power system resource may have an outage schedule
        """
        return self._power_system_resource

    def set_power_system_resource(self, value):
        if self._power_system_resource is not None:
            self._power_system_resource._outage_schedule = None
            
        self._power_system_resource = value
        if self._power_system_resource is not None:
            self._power_system_resource._outage_schedule = self
            
    power_system_resource = property(get_power_system_resource, set_power_system_resource)
    # >>> power_system_resource

    # <<< switching_operations
    # @generated
    def get_switching_operations(self):
        """ An OutageSchedule may operate many switches.
        """
        return self._switching_operations

    def set_switching_operations(self, value):
        for x in self._switching_operations:
            x.outage_schedule = None
        for y in value:
            y.outage_schedule = self
        self._switching_operations = value
            
    switching_operations = property(get_switching_operations, set_switching_operations)
    
    def add_switching_operations(self, *switching_operations):
        for obj in switching_operations:
            obj._outage_schedule = self
            if obj not in self._switching_operations:
                self._switching_operations.append(obj)
        
    def remove_switching_operations(self, *switching_operations):
        for obj in switching_operations:
            obj._outage_schedule = None
            self._switching_operations.remove(obj)
    # >>> switching_operations



class ClearanceTag(IdentifiedObject):
    """ A clearance tag that is used to authorize and schedule work on conducting equipment in the field. Tagged equipment is not available for commercial service.
    """
    # <<< clearance_tag
    # @generated
    def __init__(self, work_start_time='', phase_check_req_flag=False, deenergize_req_flag=False, work_end_time='', tag_issue_time='', work_description='', ground_req_flag=False, authority_name='', conducting_equipment=None, safety_documents=None, clearance_tag_type=None, **kw_args):
        """ Initialises a new 'ClearanceTag' instance.
        """
        # The time at which the clearance tag is scheduled to be set. 
        self.work_start_time = work_start_time
        # Set true if equipment phasing must be checked 
        self.phase_check_req_flag = phase_check_req_flag
        # Set true if equipment must be deenergized 
        self.deenergize_req_flag = deenergize_req_flag
        # The time at which the clearance tag is scheduled to be removed 
        self.work_end_time = work_end_time
        # The time at which the clearance tag was issued 
        self.tag_issue_time = tag_issue_time
        # Description of the work to be performed 
        self.work_description = work_description
        # Set true if equipment must be grounded 
        self.ground_req_flag = ground_req_flag
        # The name of the person who is authorized to issue the tag 
        self.authority_name = authority_name
        
        self._conducting_equipment = None
        self.conducting_equipment = conducting_equipment
        self._safety_documents = []
        if safety_documents is None:
            self.safety_documents = []
        else:
            self.safety_documents = safety_documents
        self._clearance_tag_type = None
        self.clearance_tag_type = clearance_tag_type

        super(ClearanceTag, self).__init__(**kw_args)
    # >>> clearance_tag
        
    # <<< conducting_equipment
    # @generated
    def get_conducting_equipment(self):
        """ Conducting equipment may have multiple clearance tags for authorized field work
        """
        return self._conducting_equipment

    def set_conducting_equipment(self, value):
        if self._conducting_equipment is not None:
            filtered = [x for x in self.conducting_equipment.clearance_tags if x != self]
            self._conducting_equipment._clearance_tags = filtered
            
        self._conducting_equipment = value
        if self._conducting_equipment is not None:
            if self not in self._conducting_equipment._clearance_tags:
                self._conducting_equipment._clearance_tags.append(self)

    conducting_equipment = property(get_conducting_equipment, set_conducting_equipment)
    # >>> conducting_equipment

    # <<< safety_documents
    # @generated
    def get_safety_documents(self):
        """ 
        """
        return self._safety_documents

    def set_safety_documents(self, value):
        for p in self.safety_documents:
            filtered = [q for q in p.clearance_tags if q != self]
            p._clearance_tags = filtered
        for r in value:
            if self not in r._clearance_tags:
                r._clearance_tags.append(self)
        self._safety_documents = value
            
    safety_documents = property(get_safety_documents, set_safety_documents)
    
    def add_safety_documents(self, *safety_documents):
        for obj in safety_documents:
            if self not in obj._clearance_tags:
                obj._clearance_tags.append(self)
            self._safety_documents.append(obj)
        
    def remove_safety_documents(self, *safety_documents):
        for obj in safety_documents:
            if self in obj._clearance_tags:
                obj._clearance_tags.remove(self)
            self._safety_documents.remove(obj)
    # >>> safety_documents

    # <<< clearance_tag_type
    # @generated
    def get_clearance_tag_type(self):
        """ The type of tag, depending on the purpose of the work to be performed and/or the type of supervisory control allowed.
        """
        return self._clearance_tag_type

    def set_clearance_tag_type(self, value):
        if self._clearance_tag_type is not None:
            filtered = [x for x in self.clearance_tag_type.clearance_tags if x != self]
            self._clearance_tag_type._clearance_tags = filtered
            
        self._clearance_tag_type = value
        if self._clearance_tag_type is not None:
            if self not in self._clearance_tag_type._clearance_tags:
                self._clearance_tag_type._clearance_tags.append(self)

    clearance_tag_type = property(get_clearance_tag_type, set_clearance_tag_type)
    # >>> clearance_tag_type



class ClearanceTagType(IdentifiedObject):
    """ Type of ClearanceTag. Could indicate the type of work to be performed and/or the type of supervisory control.
    """
    # <<< clearance_tag_type
    # @generated
    def __init__(self, clearance_tags=None, **kw_args):
        """ Initialises a new 'ClearanceTagType' instance.
        """
        
        self._clearance_tags = []
        if clearance_tags is None:
            self.clearance_tags = []
        else:
            self.clearance_tags = clearance_tags

        super(ClearanceTagType, self).__init__(**kw_args)
    # >>> clearance_tag_type
        
    # <<< clearance_tags
    # @generated
    def get_clearance_tags(self):
        """ The ClearanceTags currently being defined for this type.
        """
        return self._clearance_tags

    def set_clearance_tags(self, value):
        for x in self._clearance_tags:
            x.clearance_tag_type = None
        for y in value:
            y.clearance_tag_type = self
        self._clearance_tags = value
            
    clearance_tags = property(get_clearance_tags, set_clearance_tags)
    
    def add_clearance_tags(self, *clearance_tags):
        for obj in clearance_tags:
            obj._clearance_tag_type = self
            if obj not in self._clearance_tags:
                self._clearance_tags.append(obj)
        
    def remove_clearance_tags(self, *clearance_tags):
        for obj in clearance_tags:
            obj._clearance_tag_type = None
            self._clearance_tags.remove(obj)
    # >>> clearance_tags



# <<< outage
# @generated
# >>> outage
