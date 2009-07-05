# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14 import Element

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61970.Core"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61970.Core"

class RegularTimePoint(Element):
    """ TimePoints for a schedule where the time between the points is constant.
    """
    # <<< regular_time_point
    # @generated
    def __init__(self, value1=0.0, sequence_number=0, value2=0.0, interval_schedule=None, **kw_args):
        """ Initialises a new 'RegularTimePoint' instance.
        """
        # The first value at the time. The meaning of the value is defined by the class inhering the RegularIntervalSchedule. 
        self.value1 = value1
        # The position of the RegularTimePoint in the sequence. Note that time points don't have to be sequential, i.e. time points may be omitted. The actual time for a RegularTimePoint is computed by multiplying the RegularIntervalSchedule.timeStep with the RegularTimePoint.sequenceNumber and add the BasicIntervalSchedule.startTime. 
        self.sequence_number = sequence_number
        # The second value at the time. The meaning of the value is defined by the class inhering the RegularIntervalSchedule. 
        self.value2 = value2
        
        self._interval_schedule = None
        self.interval_schedule = interval_schedule

        super(RegularTimePoint, self).__init__(**kw_args)
    # >>> regular_time_point
        
    # <<< interval_schedule
    # @generated
    def get_interval_schedule(self):
        """ A RegularTimePoint belongs to a RegularIntervalSchedule.
        """
        return self._interval_schedule

    def set_interval_schedule(self, value):
        if self._interval_schedule is not None:
            filtered = [x for x in self.interval_schedule.time_points if x != self]
            self._interval_schedule._time_points = filtered
            
        self._interval_schedule = value
        if self._interval_schedule is not None:
            if self not in self._interval_schedule._time_points:
                self._interval_schedule._time_points.append(self)

    interval_schedule = property(get_interval_schedule, set_interval_schedule)
    # >>> interval_schedule



class OperatingShare(Element):
    """ Specifies the contract relationship between a PowerSystemResource and a contract participant.
    """
    # <<< operating_share
    # @generated
    def __init__(self, percentage=0.0, power_system_resource=None, operating_participant=None, **kw_args):
        """ Initialises a new 'OperatingShare' instance.
        """
        # Percentage ownership for this device.   The percentage indicates the percentage ownership of the PSROwner for the PowerSystemResource.  The total percentage ownership for a PowerSystemResource should add to 100%. 
        self.percentage = percentage
        
        self._power_system_resource = None
        self.power_system_resource = power_system_resource
        self._operating_participant = None
        self.operating_participant = operating_participant

        super(OperatingShare, self).__init__(**kw_args)
    # >>> operating_share
        
    # <<< power_system_resource
    # @generated
    def get_power_system_resource(self):
        """ The PowerSystemResource to which the attribues apply.   The percentage ownership of all owners of a PowerSystemResource should add to 100%.
        """
        return self._power_system_resource

    def set_power_system_resource(self, value):
        if self._power_system_resource is not None:
            filtered = [x for x in self.power_system_resource.operating_share if x != self]
            self._power_system_resource._operating_share = filtered
            
        self._power_system_resource = value
        if self._power_system_resource is not None:
            if self not in self._power_system_resource._operating_share:
                self._power_system_resource._operating_share.append(self)

    power_system_resource = property(get_power_system_resource, set_power_system_resource)
    # >>> power_system_resource

    # <<< operating_participant
    # @generated
    def get_operating_participant(self):
        """ The linkage to a owners  and its linkage attributes like percentage ownership.   The ownership percentage should add to 100% for all owners of a PowerSystemResource, but a PSROwner may own any percentage of any number of PowerSystemResource objects.
        """
        return self._operating_participant

    def set_operating_participant(self, value):
        if self._operating_participant is not None:
            filtered = [x for x in self.operating_participant.operating_share if x != self]
            self._operating_participant._operating_share = filtered
            
        self._operating_participant = value
        if self._operating_participant is not None:
            if self not in self._operating_participant._operating_share:
                self._operating_participant._operating_share.append(self)

    operating_participant = property(get_operating_participant, set_operating_participant)
    # >>> operating_participant



class IdentifiedObject(Element):
    """ This is a root class to provide common naming attributes for all classes needing naming attributes
    """
    # <<< identified_object
    # @generated
    def __init__(self, m_rid='', name='', path_name='', description='', local_name='', alias_name='', modeling_authority_set=None, **kw_args):
        """ Initialises a new 'IdentifiedObject' instance.
        """
        # A Model Authority issues mRIDs. Given that each Model Authority has a unique id and this id is part of the mRID, then the mRID is globally unique. 
        self.m_rid = m_rid
        # The name is a free text human readable name of the object. It may be non unique and may not correlate to a naming hierarchy. 
        self.name = name
        # The pathname is a system unique name composed from all IdentifiedObject.localNames in a naming hierarchy path from the object to the root. 
        self.path_name = path_name
        # The description is a free human readable text describing or naming the object. It may be non unique and may not correlate to a naming hierarchy. 
        self.description = description
        # The localName is a human readable name of the object. It is only used with objects organized in a naming hierarchy. The simplest naming hierarchy has just one parent (the root) giving a flat naming hierarchy. However, the naming hierarchy usually has several levels, e.g. Substation, VoltageLevel, Equipment etc. Children of the same parent have names that are unique among them. If the uniqueness requirement cannot be met IdentifiedObject.localName shall not be used, use IdentifiedObject.name  instead. 
        self.local_name = local_name
        # The aliasName is free text human readable name of the object alternative to IdentifiedObject.name. It may be non unique and may not correlate to a naming hierarchy. 
        self.alias_name = alias_name
        
        self._modeling_authority_set = None
        self.modeling_authority_set = modeling_authority_set

        super(IdentifiedObject, self).__init__(**kw_args)
    # >>> identified_object
        
    # <<< modeling_authority_set
    # @generated
    def get_modeling_authority_set(self):
        """ An IdentifiedObject belongs to a Modeling Authority Set for purposes of defining a group of data maintained by the same Modeling Authority.
        """
        return self._modeling_authority_set

    def set_modeling_authority_set(self, value):
        if self._modeling_authority_set is not None:
            filtered = [x for x in self.modeling_authority_set.identified_objects if x != self]
            self._modeling_authority_set._identified_objects = filtered
            
        self._modeling_authority_set = value
        if self._modeling_authority_set is not None:
            if self not in self._modeling_authority_set._identified_objects:
                self._modeling_authority_set._identified_objects.append(self)

    modeling_authority_set = property(get_modeling_authority_set, set_modeling_authority_set)
    # >>> modeling_authority_set



class IrregularTimePoint(Element):
    """ TimePoints for a schedule where the time between the points varies.
    """
    # <<< irregular_time_point
    # @generated
    def __init__(self, value2=0.0, time=0.0, value1=0.0, interval_schedule=None, **kw_args):
        """ Initialises a new 'IrregularTimePoint' instance.
        """
        # The second value at the time. The meaning of the value is defined by the class inhering the IrregularIntervalSchedule. 
        self.value2 = value2
        # The time is relative the BasicTimeSchedule.startTime. 
        self.time = time
        # The first value at the time. The meaning of the value is defined by the class inhering the IrregularIntervalSchedule. 
        self.value1 = value1
        
        self._interval_schedule = None
        self.interval_schedule = interval_schedule

        super(IrregularTimePoint, self).__init__(**kw_args)
    # >>> irregular_time_point
        
    # <<< interval_schedule
    # @generated
    def get_interval_schedule(self):
        """ An IrregularTimePoint belongs to an IrregularIntervalSchedule.
        """
        return self._interval_schedule

    def set_interval_schedule(self, value):
        if self._interval_schedule is not None:
            filtered = [x for x in self.interval_schedule.time_points if x != self]
            self._interval_schedule._time_points = filtered
            
        self._interval_schedule = value
        if self._interval_schedule is not None:
            if self not in self._interval_schedule._time_points:
                self._interval_schedule._time_points.append(self)

    interval_schedule = property(get_interval_schedule, set_interval_schedule)
    # >>> interval_schedule



class CurveData(Element):
    """ Data point values for defining a curve or schedule
    """
    # <<< curve_data
    # @generated
    def __init__(self, y2value=0.0, xvalue=0.0, y1value=0.0, curve=None, **kw_args):
        """ Initialises a new 'CurveData' instance.
        """
        # The data value of the second Y-axis variable (if present), depending on the Y-axis units 
        self.y2value = y2value
        # The data value of the X-axis variable,  depending on the X-axis units 
        self.xvalue = xvalue
        # The data value of the  first Y-axis variable, depending on the Y-axis units 
        self.y1value = y1value
        
        self._curve = None
        self.curve = curve

        super(CurveData, self).__init__(**kw_args)
    # >>> curve_data
        
    # <<< curve
    # @generated
    def get_curve(self):
        """ The Curve defined by this CurveData.
        """
        return self._curve

    def set_curve(self, value):
        if self._curve is not None:
            filtered = [x for x in self.curve.curve_datas if x != self]
            self._curve._curve_datas = filtered
            
        self._curve = value
        if self._curve is not None:
            if self not in self._curve._curve_datas:
                self._curve._curve_datas.append(self)

    curve = property(get_curve, set_curve)
    # >>> curve



class PowerSystemResource(IdentifiedObject):
    """ A power system resource can be an item of equipment such as a Switch, an EquipmentContainer containing many individual items of equipment such as a  Substation, or an organisational entity such as Company or SubControlArea.  This provides for the nesting of collections of PowerSystemResources within other PowerSystemResources. For example, a Switch could be a member of a Substation and a Substation could be a member of a division of a Company.
    """
    # <<< power_system_resource
    # @generated
    def __init__(self, activity_records=None, outage_schedule=None, schedule_steps=None, reporting_group=None, circuit_sections=None, measurements=None, network_data_sets=None, operating_share=None, psr_lists=None, safety_documents=None, circuits=None, psrtype=None, psrstatus=None, asset_roles=None, document_roles=None, erp_organisation_roles=None, change_items=None, location_roles=None, **kw_args):
        """ Initialises a new 'PowerSystemResource' instance.
        """
        
        self._activity_records = []
        if activity_records is None:
            self.activity_records = []
        else:
            self.activity_records = activity_records
        self._outage_schedule = None
        self.outage_schedule = outage_schedule
        self._schedule_steps = []
        if schedule_steps is None:
            self.schedule_steps = []
        else:
            self.schedule_steps = schedule_steps
        self._reporting_group = []
        if reporting_group is None:
            self.reporting_group = []
        else:
            self.reporting_group = reporting_group
        self._circuit_sections = []
        if circuit_sections is None:
            self.circuit_sections = []
        else:
            self.circuit_sections = circuit_sections
        self._measurements = []
        if measurements is None:
            self.measurements = []
        else:
            self.measurements = measurements
        self._network_data_sets = []
        if network_data_sets is None:
            self.network_data_sets = []
        else:
            self.network_data_sets = network_data_sets
        self._operating_share = []
        if operating_share is None:
            self.operating_share = []
        else:
            self.operating_share = operating_share
        self._psr_lists = []
        if psr_lists is None:
            self.psr_lists = []
        else:
            self.psr_lists = psr_lists
        self._safety_documents = []
        if safety_documents is None:
            self.safety_documents = []
        else:
            self.safety_documents = safety_documents
        self._circuits = []
        if circuits is None:
            self.circuits = []
        else:
            self.circuits = circuits
        self._psrtype = None
        self.psrtype = psrtype
        self._psrstatus = None
        self.psrstatus = psrstatus
        self._asset_roles = []
        if asset_roles is None:
            self.asset_roles = []
        else:
            self.asset_roles = asset_roles
        self._document_roles = []
        if document_roles is None:
            self.document_roles = []
        else:
            self.document_roles = document_roles
        self._erp_organisation_roles = []
        if erp_organisation_roles is None:
            self.erp_organisation_roles = []
        else:
            self.erp_organisation_roles = erp_organisation_roles
        self._change_items = []
        if change_items is None:
            self.change_items = []
        else:
            self.change_items = change_items
        self._location_roles = []
        if location_roles is None:
            self.location_roles = []
        else:
            self.location_roles = location_roles

        super(PowerSystemResource, self).__init__(**kw_args)
    # >>> power_system_resource
        
    # <<< activity_records
    # @generated
    def get_activity_records(self):
        """ 
        """
        return self._activity_records

    def set_activity_records(self, value):
        for p in self._activity_records:
            filtered = [q for q in p.power_system_resources if q != self]
            self._activity_records._power_system_resources = filtered
        for r in value:
            if self not in r._power_system_resources:
                r._power_system_resources.append(self)
        self._activity_records = value
            
    activity_records = property(get_activity_records, set_activity_records)
    
    def add_activity_records(self, *activity_records):
        for obj in activity_records:
            if self not in obj._power_system_resources:
                obj._power_system_resources.append(self)
            self._activity_records.append(obj)
        
    def remove_activity_records(self, *activity_records):
        for obj in activity_records:
            if self in obj._power_system_resources:
                obj._power_system_resources.remove(self)
            self._activity_records.remove(obj)
    # >>> activity_records

    # <<< outage_schedule
    # @generated
    def get_outage_schedule(self):
        """ A power system resource may have an outage schedule
        """
        return self._outage_schedule

    def set_outage_schedule(self, value):
        if self._outage_schedule is not None:
            self._outage_schedule._power_system_resource = None
            
        self._outage_schedule = value
        if self._outage_schedule is not None:
            self._outage_schedule._power_system_resource = self
            
    outage_schedule = property(get_outage_schedule, set_outage_schedule)
    # >>> outage_schedule

    # <<< schedule_steps
    # @generated
    def get_schedule_steps(self):
        """ 
        """
        return self._schedule_steps

    def set_schedule_steps(self, value):
        for p in self._schedule_steps:
            filtered = [q for q in p.power_system_resources if q != self]
            self._schedule_steps._power_system_resources = filtered
        for r in value:
            if self not in r._power_system_resources:
                r._power_system_resources.append(self)
        self._schedule_steps = value
            
    schedule_steps = property(get_schedule_steps, set_schedule_steps)
    
    def add_schedule_steps(self, *schedule_steps):
        for obj in schedule_steps:
            if self not in obj._power_system_resources:
                obj._power_system_resources.append(self)
            self._schedule_steps.append(obj)
        
    def remove_schedule_steps(self, *schedule_steps):
        for obj in schedule_steps:
            if self in obj._power_system_resources:
                obj._power_system_resources.remove(self)
            self._schedule_steps.remove(obj)
    # >>> schedule_steps

    # <<< reporting_group
    # @generated
    def get_reporting_group(self):
        """ Reporting groups to which this PSR belongs.
        """
        return self._reporting_group

    def set_reporting_group(self, value):
        for p in self._reporting_group:
            filtered = [q for q in p.power_system_resource if q != self]
            self._reporting_group._power_system_resource = filtered
        for r in value:
            if self not in r._power_system_resource:
                r._power_system_resource.append(self)
        self._reporting_group = value
            
    reporting_group = property(get_reporting_group, set_reporting_group)
    
    def add_reporting_group(self, *reporting_group):
        for obj in reporting_group:
            if self not in obj._power_system_resource:
                obj._power_system_resource.append(self)
            self._reporting_group.append(obj)
        
    def remove_reporting_group(self, *reporting_group):
        for obj in reporting_group:
            if self in obj._power_system_resource:
                obj._power_system_resource.remove(self)
            self._reporting_group.remove(obj)
    # >>> reporting_group

    # <<< circuit_sections
    # @generated
    def get_circuit_sections(self):
        """ 
        """
        return self._circuit_sections

    def set_circuit_sections(self, value):
        for p in self._circuit_sections:
            filtered = [q for q in p.power_system_resources if q != self]
            self._circuit_sections._power_system_resources = filtered
        for r in value:
            if self not in r._power_system_resources:
                r._power_system_resources.append(self)
        self._circuit_sections = value
            
    circuit_sections = property(get_circuit_sections, set_circuit_sections)
    
    def add_circuit_sections(self, *circuit_sections):
        for obj in circuit_sections:
            if self not in obj._power_system_resources:
                obj._power_system_resources.append(self)
            self._circuit_sections.append(obj)
        
    def remove_circuit_sections(self, *circuit_sections):
        for obj in circuit_sections:
            if self in obj._power_system_resources:
                obj._power_system_resources.remove(self)
            self._circuit_sections.remove(obj)
    # >>> circuit_sections

    # <<< measurements
    # @generated
    def get_measurements(self):
        """ The Measurements that are included in the naming hierarchy where the PSR is the containing object
        """
        return self._measurements

    def set_measurements(self, value):
        for x in self._measurements:
            x.power_system_resource = None
        for y in value:
            y.power_system_resource = self
        self._measurements = value
            
    measurements = property(get_measurements, set_measurements)
    
    def add_measurements(self, *measurements):
        for obj in measurements:
            obj._power_system_resource = self
            if obj not in self._measurements:
                self._measurements.append(obj)
        
    def remove_measurements(self, *measurements):
        for obj in measurements:
            obj._power_system_resource = None
            self._measurements.remove(obj)
    # >>> measurements

    # <<< network_data_sets
    # @generated
    def get_network_data_sets(self):
        """ 
        """
        return self._network_data_sets

    def set_network_data_sets(self, value):
        for p in self._network_data_sets:
            filtered = [q for q in p.power_system_resources if q != self]
            self._network_data_sets._power_system_resources = filtered
        for r in value:
            if self not in r._power_system_resources:
                r._power_system_resources.append(self)
        self._network_data_sets = value
            
    network_data_sets = property(get_network_data_sets, set_network_data_sets)
    
    def add_network_data_sets(self, *network_data_sets):
        for obj in network_data_sets:
            if self not in obj._power_system_resources:
                obj._power_system_resources.append(self)
            self._network_data_sets.append(obj)
        
    def remove_network_data_sets(self, *network_data_sets):
        for obj in network_data_sets:
            if self in obj._power_system_resources:
                obj._power_system_resources.remove(self)
            self._network_data_sets.remove(obj)
    # >>> network_data_sets

    # <<< operating_share
    # @generated
    def get_operating_share(self):
        """ The linkage to any number of operating share objects.
        """
        return self._operating_share

    def set_operating_share(self, value):
        for x in self._operating_share:
            x.power_system_resource = None
        for y in value:
            y.power_system_resource = self
        self._operating_share = value
            
    operating_share = property(get_operating_share, set_operating_share)
    
    def add_operating_share(self, *operating_share):
        for obj in operating_share:
            obj._power_system_resource = self
            if obj not in self._operating_share:
                self._operating_share.append(obj)
        
    def remove_operating_share(self, *operating_share):
        for obj in operating_share:
            obj._power_system_resource = None
            self._operating_share.remove(obj)
    # >>> operating_share

    # <<< psr_lists
    # @generated
    def get_psr_lists(self):
        """ 
        """
        return self._psr_lists

    def set_psr_lists(self, value):
        for p in self._psr_lists:
            filtered = [q for q in p.power_system_resources if q != self]
            self._psr_lists._power_system_resources = filtered
        for r in value:
            if self not in r._power_system_resources:
                r._power_system_resources.append(self)
        self._psr_lists = value
            
    psr_lists = property(get_psr_lists, set_psr_lists)
    
    def add_psr_lists(self, *psr_lists):
        for obj in psr_lists:
            if self not in obj._power_system_resources:
                obj._power_system_resources.append(self)
            self._psr_lists.append(obj)
        
    def remove_psr_lists(self, *psr_lists):
        for obj in psr_lists:
            if self in obj._power_system_resources:
                obj._power_system_resources.remove(self)
            self._psr_lists.remove(obj)
    # >>> psr_lists

    # <<< safety_documents
    # @generated
    def get_safety_documents(self):
        """ 
        """
        return self._safety_documents

    def set_safety_documents(self, value):
        for x in self._safety_documents:
            x.power_system_resource = None
        for y in value:
            y.power_system_resource = self
        self._safety_documents = value
            
    safety_documents = property(get_safety_documents, set_safety_documents)
    
    def add_safety_documents(self, *safety_documents):
        for obj in safety_documents:
            obj._power_system_resource = self
            if obj not in self._safety_documents:
                self._safety_documents.append(obj)
        
    def remove_safety_documents(self, *safety_documents):
        for obj in safety_documents:
            obj._power_system_resource = None
            self._safety_documents.remove(obj)
    # >>> safety_documents

    # <<< circuits
    # @generated
    def get_circuits(self):
        """ 
        """
        return self._circuits

    def set_circuits(self, value):
        for p in self._circuits:
            filtered = [q for q in p.power_system_resources if q != self]
            self._circuits._power_system_resources = filtered
        for r in value:
            if self not in r._power_system_resources:
                r._power_system_resources.append(self)
        self._circuits = value
            
    circuits = property(get_circuits, set_circuits)
    
    def add_circuits(self, *circuits):
        for obj in circuits:
            if self not in obj._power_system_resources:
                obj._power_system_resources.append(self)
            self._circuits.append(obj)
        
    def remove_circuits(self, *circuits):
        for obj in circuits:
            if self in obj._power_system_resources:
                obj._power_system_resources.remove(self)
            self._circuits.remove(obj)
    # >>> circuits

    # <<< psrtype
    # @generated
    def get_psrtype(self):
        """ PSRType (custom classification) for this PowerSystemResource.
        """
        return self._psrtype

    def set_psrtype(self, value):
        if self._psrtype is not None:
            filtered = [x for x in self.psrtype.power_system_resources if x != self]
            self._psrtype._power_system_resources = filtered
            
        self._psrtype = value
        if self._psrtype is not None:
            if self not in self._psrtype._power_system_resources:
                self._psrtype._power_system_resources.append(self)

    psrtype = property(get_psrtype, set_psrtype)
    # >>> psrtype

    # <<< psrstatus
    # @generated
    def get_psrstatus(self):
        """ 
        """
        return self._psrstatus

    def set_psrstatus(self, value):
        if self._psrstatus is not None:
            self._psrstatus._power_system_resource = None
            
        self._psrstatus = value
        if self._psrstatus is not None:
            self._psrstatus._power_system_resource = self
            
    psrstatus = property(get_psrstatus, set_psrstatus)
    # >>> psrstatus

    # <<< asset_roles
    # @generated
    def get_asset_roles(self):
        """ 
        """
        return self._asset_roles

    def set_asset_roles(self, value):
        for x in self._asset_roles:
            x.power_system_resource = None
        for y in value:
            y.power_system_resource = self
        self._asset_roles = value
            
    asset_roles = property(get_asset_roles, set_asset_roles)
    
    def add_asset_roles(self, *asset_roles):
        for obj in asset_roles:
            obj._power_system_resource = self
            if obj not in self._asset_roles:
                self._asset_roles.append(obj)
        
    def remove_asset_roles(self, *asset_roles):
        for obj in asset_roles:
            obj._power_system_resource = None
            self._asset_roles.remove(obj)
    # >>> asset_roles

    # <<< document_roles
    # @generated
    def get_document_roles(self):
        """ 
        """
        return self._document_roles

    def set_document_roles(self, value):
        for x in self._document_roles:
            x.power_system_resource = None
        for y in value:
            y.power_system_resource = self
        self._document_roles = value
            
    document_roles = property(get_document_roles, set_document_roles)
    
    def add_document_roles(self, *document_roles):
        for obj in document_roles:
            obj._power_system_resource = self
            if obj not in self._document_roles:
                self._document_roles.append(obj)
        
    def remove_document_roles(self, *document_roles):
        for obj in document_roles:
            obj._power_system_resource = None
            self._document_roles.remove(obj)
    # >>> document_roles

    # <<< erp_organisation_roles
    # @generated
    def get_erp_organisation_roles(self):
        """ 
        """
        return self._erp_organisation_roles

    def set_erp_organisation_roles(self, value):
        for x in self._erp_organisation_roles:
            x.power_system_resource = None
        for y in value:
            y.power_system_resource = self
        self._erp_organisation_roles = value
            
    erp_organisation_roles = property(get_erp_organisation_roles, set_erp_organisation_roles)
    
    def add_erp_organisation_roles(self, *erp_organisation_roles):
        for obj in erp_organisation_roles:
            obj._power_system_resource = self
            if obj not in self._erp_organisation_roles:
                self._erp_organisation_roles.append(obj)
        
    def remove_erp_organisation_roles(self, *erp_organisation_roles):
        for obj in erp_organisation_roles:
            obj._power_system_resource = None
            self._erp_organisation_roles.remove(obj)
    # >>> erp_organisation_roles

    # <<< change_items
    # @generated
    def get_change_items(self):
        """ 
        """
        return self._change_items

    def set_change_items(self, value):
        for x in self._change_items:
            x.power_system_resource = None
        for y in value:
            y.power_system_resource = self
        self._change_items = value
            
    change_items = property(get_change_items, set_change_items)
    
    def add_change_items(self, *change_items):
        for obj in change_items:
            obj._power_system_resource = self
            if obj not in self._change_items:
                self._change_items.append(obj)
        
    def remove_change_items(self, *change_items):
        for obj in change_items:
            obj._power_system_resource = None
            self._change_items.remove(obj)
    # >>> change_items

    # <<< location_roles
    # @generated
    def get_location_roles(self):
        """ 
        """
        return self._location_roles

    def set_location_roles(self, value):
        for x in self._location_roles:
            x.power_system_resource = None
        for y in value:
            y.power_system_resource = self
        self._location_roles = value
            
    location_roles = property(get_location_roles, set_location_roles)
    
    def add_location_roles(self, *location_roles):
        for obj in location_roles:
            obj._power_system_resource = self
            if obj not in self._location_roles:
                self._location_roles.append(obj)
        
    def remove_location_roles(self, *location_roles):
        for obj in location_roles:
            obj._power_system_resource = None
            self._location_roles.remove(obj)
    # >>> location_roles



class PsrList(IdentifiedObject):
    """ Arbitrary list of PowerSystemResources. Can be used for various purposes, including grouping for report generation.
    """
    # <<< psr_list
    # @generated
    def __init__(self, type_psrlist='', power_system_resources=None, **kw_args):
        """ Initialises a new 'PsrList' instance.
        """
        # Type of power system resources in this list. 
        self.type_psrlist = type_psrlist
        
        self._power_system_resources = []
        if power_system_resources is None:
            self.power_system_resources = []
        else:
            self.power_system_resources = power_system_resources

        super(PsrList, self).__init__(**kw_args)
    # >>> psr_list
        
    # <<< power_system_resources
    # @generated
    def get_power_system_resources(self):
        """ 
        """
        return self._power_system_resources

    def set_power_system_resources(self, value):
        for p in self._power_system_resources:
            filtered = [q for q in p.psr_lists if q != self]
            self._power_system_resources._psr_lists = filtered
        for r in value:
            if self not in r._psr_lists:
                r._psr_lists.append(self)
        self._power_system_resources = value
            
    power_system_resources = property(get_power_system_resources, set_power_system_resources)
    
    def add_power_system_resources(self, *power_system_resources):
        for obj in power_system_resources:
            if self not in obj._psr_lists:
                obj._psr_lists.append(self)
            self._power_system_resources.append(obj)
        
    def remove_power_system_resources(self, *power_system_resources):
        for obj in power_system_resources:
            if self in obj._psr_lists:
                obj._psr_lists.remove(self)
            self._power_system_resources.remove(obj)
    # >>> power_system_resources



class OperatingParticipant(IdentifiedObject):
    """ An operator of multiple PowerSystemResource objects. Note multple OperatingParticipants may operate the same PowerSystemResource object.   This can be used for modeling jointly owned units where each owner operates as a contractual share.
    """
    # <<< operating_participant
    # @generated
    def __init__(self, operating_share=None, **kw_args):
        """ Initialises a new 'OperatingParticipant' instance.
        """
        
        self._operating_share = []
        if operating_share is None:
            self.operating_share = []
        else:
            self.operating_share = operating_share

        super(OperatingParticipant, self).__init__(**kw_args)
    # >>> operating_participant
        
    # <<< operating_share
    # @generated
    def get_operating_share(self):
        """ The operating shares of an operating participant.   An operating participant can be reused for any number of PSR's.
        """
        return self._operating_share

    def set_operating_share(self, value):
        for x in self._operating_share:
            x.operating_participant = None
        for y in value:
            y.operating_participant = self
        self._operating_share = value
            
    operating_share = property(get_operating_share, set_operating_share)
    
    def add_operating_share(self, *operating_share):
        for obj in operating_share:
            obj._operating_participant = self
            if obj not in self._operating_share:
                self._operating_share.append(obj)
        
    def remove_operating_share(self, *operating_share):
        for obj in operating_share:
            obj._operating_participant = None
            self._operating_share.remove(obj)
    # >>> operating_share



class ConnectivityNodeContainer(PowerSystemResource):
    """ A base class for all objects that may contain ConnectivityNodes or TopologicalNodes.
    """
    # <<< connectivity_node_container
    # @generated
    def __init__(self, connectivity_nodes=None, topological_node=None, **kw_args):
        """ Initialises a new 'ConnectivityNodeContainer' instance.
        """
        
        self._connectivity_nodes = []
        if connectivity_nodes is None:
            self.connectivity_nodes = []
        else:
            self.connectivity_nodes = connectivity_nodes
        self._topological_node = []
        if topological_node is None:
            self.topological_node = []
        else:
            self.topological_node = topological_node

        super(ConnectivityNodeContainer, self).__init__(**kw_args)
    # >>> connectivity_node_container
        
    # <<< connectivity_nodes
    # @generated
    def get_connectivity_nodes(self):
        """ Connectivity nodes contained by this container.
        """
        return self._connectivity_nodes

    def set_connectivity_nodes(self, value):
        for x in self._connectivity_nodes:
            x.connectivity_node_container = None
        for y in value:
            y.connectivity_node_container = self
        self._connectivity_nodes = value
            
    connectivity_nodes = property(get_connectivity_nodes, set_connectivity_nodes)
    
    def add_connectivity_nodes(self, *connectivity_nodes):
        for obj in connectivity_nodes:
            obj._connectivity_node_container = self
            if obj not in self._connectivity_nodes:
                self._connectivity_nodes.append(obj)
        
    def remove_connectivity_nodes(self, *connectivity_nodes):
        for obj in connectivity_nodes:
            obj._connectivity_node_container = None
            self._connectivity_nodes.remove(obj)
    # >>> connectivity_nodes

    # <<< topological_node
    # @generated
    def get_topological_node(self):
        """ The topological nodes which belong to this connectivity node container.
        """
        return self._topological_node

    def set_topological_node(self, value):
        for x in self._topological_node:
            x.connectivity_node_container = None
        for y in value:
            y.connectivity_node_container = self
        self._topological_node = value
            
    topological_node = property(get_topological_node, set_topological_node)
    
    def add_topological_node(self, *topological_node):
        for obj in topological_node:
            obj._connectivity_node_container = self
            if obj not in self._topological_node:
                self._topological_node.append(obj)
        
    def remove_topological_node(self, *topological_node):
        for obj in topological_node:
            obj._connectivity_node_container = None
            self._topological_node.remove(obj)
    # >>> topological_node



class ModelingAuthoritySet(IdentifiedObject):
    """ A Modeling Authority Set is a group of objects in a network model where the data is supplied and maintained by the same Modeling Authority.
    """
    # <<< modeling_authority_set
    # @generated
    def __init__(self, identified_objects=None, modeling_authority=None, **kw_args):
        """ Initialises a new 'ModelingAuthoritySet' instance.
        """
        
        self._identified_objects = []
        if identified_objects is None:
            self.identified_objects = []
        else:
            self.identified_objects = identified_objects
        self._modeling_authority = None
        self.modeling_authority = modeling_authority

        super(ModelingAuthoritySet, self).__init__(**kw_args)
    # >>> modeling_authority_set
        
    # <<< identified_objects
    # @generated
    def get_identified_objects(self):
        """ An IdentifiedObject belongs to a Modeling Authority Set for purposes of defining a group of data maintained by the same Modeling Authority.
        """
        return self._identified_objects

    def set_identified_objects(self, value):
        for x in self._identified_objects:
            x.modeling_authority_set = None
        for y in value:
            y.modeling_authority_set = self
        self._identified_objects = value
            
    identified_objects = property(get_identified_objects, set_identified_objects)
    
    def add_identified_objects(self, *identified_objects):
        for obj in identified_objects:
            obj._modeling_authority_set = self
            if obj not in self._identified_objects:
                self._identified_objects.append(obj)
        
    def remove_identified_objects(self, *identified_objects):
        for obj in identified_objects:
            obj._modeling_authority_set = None
            self._identified_objects.remove(obj)
    # >>> identified_objects

    # <<< modeling_authority
    # @generated
    def get_modeling_authority(self):
        """ A Modeling Authority set supplies and maintains the data for the objects in a Modeling Authority Set.
        """
        return self._modeling_authority

    def set_modeling_authority(self, value):
        if self._modeling_authority is not None:
            filtered = [x for x in self.modeling_authority.modeling_authority_sets if x != self]
            self._modeling_authority._modeling_authority_sets = filtered
            
        self._modeling_authority = value
        if self._modeling_authority is not None:
            if self not in self._modeling_authority._modeling_authority_sets:
                self._modeling_authority._modeling_authority_sets.append(self)

    modeling_authority = property(get_modeling_authority, set_modeling_authority)
    # >>> modeling_authority



class GeographicalRegion(IdentifiedObject):
    """ A geographical region of a power system network model.
    """
    # <<< geographical_region
    # @generated
    def __init__(self, regions=None, **kw_args):
        """ Initialises a new 'GeographicalRegion' instance.
        """
        
        self._regions = []
        if regions is None:
            self.regions = []
        else:
            self.regions = regions

        super(GeographicalRegion, self).__init__(**kw_args)
    # >>> geographical_region
        
    # <<< regions
    # @generated
    def get_regions(self):
        """ The association is used in the naming hierarchy.
        """
        return self._regions

    def set_regions(self, value):
        for x in self._regions:
            x.region = None
        for y in value:
            y.region = self
        self._regions = value
            
    regions = property(get_regions, set_regions)
    
    def add_regions(self, *regions):
        for obj in regions:
            obj._region = self
            if obj not in self._regions:
                self._regions.append(obj)
        
    def remove_regions(self, *regions):
        for obj in regions:
            obj._region = None
            self._regions.remove(obj)
    # >>> regions



class Unit(IdentifiedObject):
    """ Quantity being measured. The Unit.name shall be unique among all specified quantities and describe the quantity. The Unit.aliasName is meant to be used for localization.
    """
    # <<< unit
    # @generated
    def __init__(self, protection_equipments=None, measurements=None, controls=None, **kw_args):
        """ Initialises a new 'Unit' instance.
        """
        
        self._protection_equipments = []
        if protection_equipments is None:
            self.protection_equipments = []
        else:
            self.protection_equipments = protection_equipments
        self._measurements = []
        if measurements is None:
            self.measurements = []
        else:
            self.measurements = measurements
        self._controls = []
        if controls is None:
            self.controls = []
        else:
            self.controls = controls

        super(Unit, self).__init__(**kw_args)
    # >>> unit
        
    # <<< protection_equipments
    # @generated
    def get_protection_equipments(self):
        """ The Protection Equipments having the Unit.
        """
        return self._protection_equipments

    def set_protection_equipments(self, value):
        for x in self._protection_equipments:
            x.unit = None
        for y in value:
            y.unit = self
        self._protection_equipments = value
            
    protection_equipments = property(get_protection_equipments, set_protection_equipments)
    
    def add_protection_equipments(self, *protection_equipments):
        for obj in protection_equipments:
            obj._unit = self
            if obj not in self._protection_equipments:
                self._protection_equipments.append(obj)
        
    def remove_protection_equipments(self, *protection_equipments):
        for obj in protection_equipments:
            obj._unit = None
            self._protection_equipments.remove(obj)
    # >>> protection_equipments

    # <<< measurements
    # @generated
    def get_measurements(self):
        """ The Measurements having the Unit
        """
        return self._measurements

    def set_measurements(self, value):
        for x in self._measurements:
            x.unit = None
        for y in value:
            y.unit = self
        self._measurements = value
            
    measurements = property(get_measurements, set_measurements)
    
    def add_measurements(self, *measurements):
        for obj in measurements:
            obj._unit = self
            if obj not in self._measurements:
                self._measurements.append(obj)
        
    def remove_measurements(self, *measurements):
        for obj in measurements:
            obj._unit = None
            self._measurements.remove(obj)
    # >>> measurements

    # <<< controls
    # @generated
    def get_controls(self):
        """ The Controls having the Unit.
        """
        return self._controls

    def set_controls(self, value):
        for x in self._controls:
            x.unit = None
        for y in value:
            y.unit = self
        self._controls = value
            
    controls = property(get_controls, set_controls)
    
    def add_controls(self, *controls):
        for obj in controls:
            obj._unit = self
            if obj not in self._controls:
                self._controls.append(obj)
        
    def remove_controls(self, *controls):
        for obj in controls:
            obj._unit = None
            self._controls.remove(obj)
    # >>> controls



class BasicIntervalSchedule(IdentifiedObject):
    """ Schedule of values at points in time.
    """
    # <<< basic_interval_schedule
    # @generated
    def __init__(self, value2_unit='_c', value1_unit='_c', value2_multiplier='micro', value1_multiplier='micro', start_time='', **kw_args):
        """ Initialises a new 'BasicIntervalSchedule' instance.
        """
        # Value2 units of measure. Values are: "_c", "m2", "hz-1", "rad", "vah", "v_var", "ohm", "w_hz", "s", "v", "n", "none", "varh", "hz", "s-1", "h", "a", "h", "w_s", "deg", "var", "f", "m3", "j", "s", "wh", "w", "va", "min", "j_s", "g", "m", "kg_j", "pa"
        self.value2_unit = value2_unit
        # Value1 units of measure. Values are: "_c", "m2", "hz-1", "rad", "vah", "v_var", "ohm", "w_hz", "s", "v", "n", "none", "varh", "hz", "s-1", "h", "a", "h", "w_s", "deg", "var", "f", "m3", "j", "s", "wh", "w", "va", "min", "j_s", "g", "m", "kg_j", "pa"
        self.value1_unit = value1_unit
        # Multiplier for value2. Values are: "micro", "none", "c", "n", "m", "t", "g", "m", "p", "k", "d"
        self.value2_multiplier = value2_multiplier
        # Multiplier for value1. Values are: "micro", "none", "c", "n", "m", "t", "g", "m", "p", "k", "d"
        self.value1_multiplier = value1_multiplier
        # The time for the first time point. 
        self.start_time = start_time
        

        super(BasicIntervalSchedule, self).__init__(**kw_args)
    # >>> basic_interval_schedule
        


class PSRType(IdentifiedObject):
    """ Classifying instances of the same class, e.g. overhead and underground ACLineSegments. This classification mechanism is intended to provide flexibility outside the scope of this standard, i.e. provide customisation that is non standard.
    """
    # <<< psrtype
    # @generated
    def __init__(self, power_system_resources=None, **kw_args):
        """ Initialises a new 'PSRType' instance.
        """
        
        self._power_system_resources = []
        if power_system_resources is None:
            self.power_system_resources = []
        else:
            self.power_system_resources = power_system_resources

        super(PSRType, self).__init__(**kw_args)
    # >>> psrtype
        
    # <<< power_system_resources
    # @generated
    def get_power_system_resources(self):
        """ Power system resources classified with this PSRType.
        """
        return self._power_system_resources

    def set_power_system_resources(self, value):
        for x in self._power_system_resources:
            x.psrtype = None
        for y in value:
            y.psrtype = self
        self._power_system_resources = value
            
    power_system_resources = property(get_power_system_resources, set_power_system_resources)
    
    def add_power_system_resources(self, *power_system_resources):
        for obj in power_system_resources:
            obj._psrtype = self
            if obj not in self._power_system_resources:
                self._power_system_resources.append(obj)
        
    def remove_power_system_resources(self, *power_system_resources):
        for obj in power_system_resources:
            obj._psrtype = None
            self._power_system_resources.remove(obj)
    # >>> power_system_resources



class EquipmentContainer(ConnectivityNodeContainer):
    """ A modeling construct to provide a root class for all Equipment classes
    """
    # <<< equipment_container
    # @generated
    def __init__(self, equipments=None, **kw_args):
        """ Initialises a new 'EquipmentContainer' instance.
        """
        
        self._equipments = []
        if equipments is None:
            self.equipments = []
        else:
            self.equipments = equipments

        super(EquipmentContainer, self).__init__(**kw_args)
    # >>> equipment_container
        
    # <<< equipments
    # @generated
    def get_equipments(self):
        """ The association is used in the naming hierarchy.
        """
        return self._equipments

    def set_equipments(self, value):
        for x in self._equipments:
            x.equipment_container = None
        for y in value:
            y.equipment_container = self
        self._equipments = value
            
    equipments = property(get_equipments, set_equipments)
    
    def add_equipments(self, *equipments):
        for obj in equipments:
            obj._equipment_container = self
            if obj not in self._equipments:
                self._equipments.append(obj)
        
    def remove_equipments(self, *equipments):
        for obj in equipments:
            obj._equipment_container = None
            self._equipments.remove(obj)
    # >>> equipments



class Equipment(PowerSystemResource):
    """ The parts of a power system that are physical devices, electronic or mechanical
    """
    # <<< equipment
    # @generated
    def __init__(self, normal_ily_in_service=False, equivalent=False, contingency_equipment=None, customer_agreements=None, operational_limit_set=None, equipment_container=None, **kw_args):
        """ Initialises a new 'Equipment' instance.
        """
        # The equipment is normally in service. 
        self.normal_ily_in_service = normal_ily_in_service
        # Indicates if the equipment is real equipment (false) or an equivalent. 
        self.equivalent = equivalent
        
        self._contingency_equipment = []
        if contingency_equipment is None:
            self.contingency_equipment = []
        else:
            self.contingency_equipment = contingency_equipment
        self._customer_agreements = []
        if customer_agreements is None:
            self.customer_agreements = []
        else:
            self.customer_agreements = customer_agreements
        self._operational_limit_set = []
        if operational_limit_set is None:
            self.operational_limit_set = []
        else:
            self.operational_limit_set = operational_limit_set
        self._equipment_container = None
        self.equipment_container = equipment_container

        super(Equipment, self).__init__(**kw_args)
    # >>> equipment
        
    # <<< contingency_equipment
    # @generated
    def get_contingency_equipment(self):
        """ The contingency element associated with the equipment.
        """
        return self._contingency_equipment

    def set_contingency_equipment(self, value):
        for x in self._contingency_equipment:
            x.equipment = None
        for y in value:
            y.equipment = self
        self._contingency_equipment = value
            
    contingency_equipment = property(get_contingency_equipment, set_contingency_equipment)
    
    def add_contingency_equipment(self, *contingency_equipment):
        for obj in contingency_equipment:
            obj._equipment = self
            if obj not in self._contingency_equipment:
                self._contingency_equipment.append(obj)
        
    def remove_contingency_equipment(self, *contingency_equipment):
        for obj in contingency_equipment:
            obj._equipment = None
            self._contingency_equipment.remove(obj)
    # >>> contingency_equipment

    # <<< customer_agreements
    # @generated
    def get_customer_agreements(self):
        """ 
        """
        return self._customer_agreements

    def set_customer_agreements(self, value):
        for p in self._customer_agreements:
            filtered = [q for q in p.equipments if q != self]
            self._customer_agreements._equipments = filtered
        for r in value:
            if self not in r._equipments:
                r._equipments.append(self)
        self._customer_agreements = value
            
    customer_agreements = property(get_customer_agreements, set_customer_agreements)
    
    def add_customer_agreements(self, *customer_agreements):
        for obj in customer_agreements:
            if self not in obj._equipments:
                obj._equipments.append(self)
            self._customer_agreements.append(obj)
        
    def remove_customer_agreements(self, *customer_agreements):
        for obj in customer_agreements:
            if self in obj._equipments:
                obj._equipments.remove(self)
            self._customer_agreements.remove(obj)
    # >>> customer_agreements

    # <<< operational_limit_set
    # @generated
    def get_operational_limit_set(self):
        """ The equipment limit sets associated with the equipment.
        """
        return self._operational_limit_set

    def set_operational_limit_set(self, value):
        for x in self._operational_limit_set:
            x.equipment = None
        for y in value:
            y.equipment = self
        self._operational_limit_set = value
            
    operational_limit_set = property(get_operational_limit_set, set_operational_limit_set)
    
    def add_operational_limit_set(self, *operational_limit_set):
        for obj in operational_limit_set:
            obj._equipment = self
            if obj not in self._operational_limit_set:
                self._operational_limit_set.append(obj)
        
    def remove_operational_limit_set(self, *operational_limit_set):
        for obj in operational_limit_set:
            obj._equipment = None
            self._operational_limit_set.remove(obj)
    # >>> operational_limit_set

    # <<< equipment_container
    # @generated
    def get_equipment_container(self):
        """ The association is used in the naming hierarchy.
        """
        return self._equipment_container

    def set_equipment_container(self, value):
        if self._equipment_container is not None:
            filtered = [x for x in self.equipment_container.equipments if x != self]
            self._equipment_container._equipments = filtered
            
        self._equipment_container = value
        if self._equipment_container is not None:
            if self not in self._equipment_container._equipments:
                self._equipment_container._equipments.append(self)

    equipment_container = property(get_equipment_container, set_equipment_container)
    # >>> equipment_container



class ReportingGroup(IdentifiedObject):
    """ A reporting group is used for various ad-hoc groupings used for reporting.
    """
    # <<< reporting_group
    # @generated
    def __init__(self, bus_name_marker=None, reporting_super_group=None, topological_node=None, power_system_resource=None, **kw_args):
        """ Initialises a new 'ReportingGroup' instance.
        """
        
        self._bus_name_marker = []
        if bus_name_marker is None:
            self.bus_name_marker = []
        else:
            self.bus_name_marker = bus_name_marker
        self._reporting_super_group = None
        self.reporting_super_group = reporting_super_group
        self._topological_node = []
        if topological_node is None:
            self.topological_node = []
        else:
            self.topological_node = topological_node
        self._power_system_resource = []
        if power_system_resource is None:
            self.power_system_resource = []
        else:
            self.power_system_resource = power_system_resource

        super(ReportingGroup, self).__init__(**kw_args)
    # >>> reporting_group
        
    # <<< bus_name_marker
    # @generated
    def get_bus_name_marker(self):
        """ The BusNameMarkers that belong to this reporting group.
        """
        return self._bus_name_marker

    def set_bus_name_marker(self, value):
        for x in self._bus_name_marker:
            x.reporting_group = None
        for y in value:
            y.reporting_group = self
        self._bus_name_marker = value
            
    bus_name_marker = property(get_bus_name_marker, set_bus_name_marker)
    
    def add_bus_name_marker(self, *bus_name_marker):
        for obj in bus_name_marker:
            obj._reporting_group = self
            if obj not in self._bus_name_marker:
                self._bus_name_marker.append(obj)
        
    def remove_bus_name_marker(self, *bus_name_marker):
        for obj in bus_name_marker:
            obj._reporting_group = None
            self._bus_name_marker.remove(obj)
    # >>> bus_name_marker

    # <<< reporting_super_group
    # @generated
    def get_reporting_super_group(self):
        """ Reporting super group to which this reporting group belongs.
        """
        return self._reporting_super_group

    def set_reporting_super_group(self, value):
        if self._reporting_super_group is not None:
            filtered = [x for x in self.reporting_super_group.reporting_group if x != self]
            self._reporting_super_group._reporting_group = filtered
            
        self._reporting_super_group = value
        if self._reporting_super_group is not None:
            if self not in self._reporting_super_group._reporting_group:
                self._reporting_super_group._reporting_group.append(self)

    reporting_super_group = property(get_reporting_super_group, set_reporting_super_group)
    # >>> reporting_super_group

    # <<< topological_node
    # @generated
    def get_topological_node(self):
        """ The topological nodes that belong to the reporting group.
        """
        return self._topological_node

    def set_topological_node(self, value):
        for x in self._topological_node:
            x.reporting_group = None
        for y in value:
            y.reporting_group = self
        self._topological_node = value
            
    topological_node = property(get_topological_node, set_topological_node)
    
    def add_topological_node(self, *topological_node):
        for obj in topological_node:
            obj._reporting_group = self
            if obj not in self._topological_node:
                self._topological_node.append(obj)
        
    def remove_topological_node(self, *topological_node):
        for obj in topological_node:
            obj._reporting_group = None
            self._topological_node.remove(obj)
    # >>> topological_node

    # <<< power_system_resource
    # @generated
    def get_power_system_resource(self):
        """ PSR's which belong to this reporting group.
        """
        return self._power_system_resource

    def set_power_system_resource(self, value):
        for p in self._power_system_resource:
            filtered = [q for q in p.reporting_group if q != self]
            self._power_system_resource._reporting_group = filtered
        for r in value:
            if self not in r._reporting_group:
                r._reporting_group.append(self)
        self._power_system_resource = value
            
    power_system_resource = property(get_power_system_resource, set_power_system_resource)
    
    def add_power_system_resource(self, *power_system_resource):
        for obj in power_system_resource:
            if self not in obj._reporting_group:
                obj._reporting_group.append(self)
            self._power_system_resource.append(obj)
        
    def remove_power_system_resource(self, *power_system_resource):
        for obj in power_system_resource:
            if self in obj._reporting_group:
                obj._reporting_group.remove(self)
            self._power_system_resource.remove(obj)
    # >>> power_system_resource



class ReportingSuperGroup(IdentifiedObject):
    """ A reporting super group, groups reporting groups for a higher level report.
    """
    # <<< reporting_super_group
    # @generated
    def __init__(self, reporting_group=None, **kw_args):
        """ Initialises a new 'ReportingSuperGroup' instance.
        """
        
        self._reporting_group = []
        if reporting_group is None:
            self.reporting_group = []
        else:
            self.reporting_group = reporting_group

        super(ReportingSuperGroup, self).__init__(**kw_args)
    # >>> reporting_super_group
        
    # <<< reporting_group
    # @generated
    def get_reporting_group(self):
        """ Reporting groups that are grouped under this group group.
        """
        return self._reporting_group

    def set_reporting_group(self, value):
        for x in self._reporting_group:
            x.reporting_super_group = None
        for y in value:
            y.reporting_super_group = self
        self._reporting_group = value
            
    reporting_group = property(get_reporting_group, set_reporting_group)
    
    def add_reporting_group(self, *reporting_group):
        for obj in reporting_group:
            obj._reporting_super_group = self
            if obj not in self._reporting_group:
                self._reporting_group.append(obj)
        
    def remove_reporting_group(self, *reporting_group):
        for obj in reporting_group:
            obj._reporting_super_group = None
            self._reporting_group.remove(obj)
    # >>> reporting_group



class SubGeographicalRegion(IdentifiedObject):
    """ A subset of a geographical region of a power system network model.
    """
    # <<< sub_geographical_region
    # @generated
    def __init__(self, substations=None, region=None, lines=None, **kw_args):
        """ Initialises a new 'SubGeographicalRegion' instance.
        """
        
        self._substations = []
        if substations is None:
            self.substations = []
        else:
            self.substations = substations
        self._region = None
        self.region = region
        self._lines = []
        if lines is None:
            self.lines = []
        else:
            self.lines = lines

        super(SubGeographicalRegion, self).__init__(**kw_args)
    # >>> sub_geographical_region
        
    # <<< substations
    # @generated
    def get_substations(self):
        """ The association is used in the naming hierarchy.
        """
        return self._substations

    def set_substations(self, value):
        for x in self._substations:
            x.region = None
        for y in value:
            y.region = self
        self._substations = value
            
    substations = property(get_substations, set_substations)
    
    def add_substations(self, *substations):
        for obj in substations:
            obj._region = self
            if obj not in self._substations:
                self._substations.append(obj)
        
    def remove_substations(self, *substations):
        for obj in substations:
            obj._region = None
            self._substations.remove(obj)
    # >>> substations

    # <<< region
    # @generated
    def get_region(self):
        """ The association is used in the naming hierarchy.
        """
        return self._region

    def set_region(self, value):
        if self._region is not None:
            filtered = [x for x in self.region.regions if x != self]
            self._region._regions = filtered
            
        self._region = value
        if self._region is not None:
            if self not in self._region._regions:
                self._region._regions.append(self)

    region = property(get_region, set_region)
    # >>> region

    # <<< lines
    # @generated
    def get_lines(self):
        """ A Line can be contained by a SubGeographical Region.
        """
        return self._lines

    def set_lines(self, value):
        for x in self._lines:
            x.region = None
        for y in value:
            y.region = self
        self._lines = value
            
    lines = property(get_lines, set_lines)
    
    def add_lines(self, *lines):
        for obj in lines:
            obj._region = self
            if obj not in self._lines:
                self._lines.append(obj)
        
    def remove_lines(self, *lines):
        for obj in lines:
            obj._region = None
            self._lines.remove(obj)
    # >>> lines



class ModelingAuthority(IdentifiedObject):
    """ A Modeling Authority is an entity responsible for supplying and maintaining the data defining a specific set of objects in a network model.
    """
    # <<< modeling_authority
    # @generated
    def __init__(self, modeling_authority_sets=None, **kw_args):
        """ Initialises a new 'ModelingAuthority' instance.
        """
        
        self._modeling_authority_sets = []
        if modeling_authority_sets is None:
            self.modeling_authority_sets = []
        else:
            self.modeling_authority_sets = modeling_authority_sets

        super(ModelingAuthority, self).__init__(**kw_args)
    # >>> modeling_authority
        
    # <<< modeling_authority_sets
    # @generated
    def get_modeling_authority_sets(self):
        """ A Modeling Authority set supplies and maintains the data for the objects in a Modeling Authority Set.
        """
        return self._modeling_authority_sets

    def set_modeling_authority_sets(self, value):
        for x in self._modeling_authority_sets:
            x.modeling_authority = None
        for y in value:
            y.modeling_authority = self
        self._modeling_authority_sets = value
            
    modeling_authority_sets = property(get_modeling_authority_sets, set_modeling_authority_sets)
    
    def add_modeling_authority_sets(self, *modeling_authority_sets):
        for obj in modeling_authority_sets:
            obj._modeling_authority = self
            if obj not in self._modeling_authority_sets:
                self._modeling_authority_sets.append(obj)
        
    def remove_modeling_authority_sets(self, *modeling_authority_sets):
        for obj in modeling_authority_sets:
            obj._modeling_authority = None
            self._modeling_authority_sets.remove(obj)
    # >>> modeling_authority_sets



class Curve(IdentifiedObject):
    """ Relationship between an independent variable (X-axis) and one or two dependent  variables (Y1-axis and Y2-axis). Curves can also serve as schedules.
    """
    # <<< curve
    # @generated
    def __init__(self, y2_multiplier='micro', y2_unit='_c', y1_multiplier='micro', y1_unit='_c', curve_style='straight_line_yvalues', x_multiplier='micro', x_unit='_c', curve_datas=None, **kw_args):
        """ Initialises a new 'Curve' instance.
        """
        # Multiplier for Y2-axis. Values are: "micro", "none", "c", "n", "m", "t", "g", "m", "p", "k", "d"
        self.y2_multiplier = y2_multiplier
        # The Y2-axis units of measure. Values are: "_c", "m2", "hz-1", "rad", "vah", "v_var", "ohm", "w_hz", "s", "v", "n", "none", "varh", "hz", "s-1", "h", "a", "h", "w_s", "deg", "var", "f", "m3", "j", "s", "wh", "w", "va", "min", "j_s", "g", "m", "kg_j", "pa"
        self.y2_unit = y2_unit
        # Multiplier for Y1-axis Values are: "micro", "none", "c", "n", "m", "t", "g", "m", "p", "k", "d"
        self.y1_multiplier = y1_multiplier
        # The Y1-axis units of measure. Values are: "_c", "m2", "hz-1", "rad", "vah", "v_var", "ohm", "w_hz", "s", "v", "n", "none", "varh", "hz", "s-1", "h", "a", "h", "w_s", "deg", "var", "f", "m3", "j", "s", "wh", "w", "va", "min", "j_s", "g", "m", "kg_j", "pa"
        self.y1_unit = y1_unit
        # The style or shape of the curve. Values are: "straight_line_yvalues", "constant_yvalue", "formula", "ramp_yvalue"
        self.curve_style = curve_style
        # Multiplier for X-axis. Values are: "micro", "none", "c", "n", "m", "t", "g", "m", "p", "k", "d"
        self.x_multiplier = x_multiplier
        # The X-axis units of measure. Values are: "_c", "m2", "hz-1", "rad", "vah", "v_var", "ohm", "w_hz", "s", "v", "n", "none", "varh", "hz", "s-1", "h", "a", "h", "w_s", "deg", "var", "f", "m3", "j", "s", "wh", "w", "va", "min", "j_s", "g", "m", "kg_j", "pa"
        self.x_unit = x_unit
        
        self._curve_datas = []
        if curve_datas is None:
            self.curve_datas = []
        else:
            self.curve_datas = curve_datas

        super(Curve, self).__init__(**kw_args)
    # >>> curve
        
    # <<< curve_datas
    # @generated
    def get_curve_datas(self):
        """ The point data values that define a curve
        """
        return self._curve_datas

    def set_curve_datas(self, value):
        for x in self._curve_datas:
            x.curve = None
        for y in value:
            y.curve = self
        self._curve_datas = value
            
    curve_datas = property(get_curve_datas, set_curve_datas)
    
    def add_curve_datas(self, *curve_datas):
        for obj in curve_datas:
            obj._curve = self
            if obj not in self._curve_datas:
                self._curve_datas.append(obj)
        
    def remove_curve_datas(self, *curve_datas):
        for obj in curve_datas:
            obj._curve = None
            self._curve_datas.remove(obj)
    # >>> curve_datas



class BaseVoltage(IdentifiedObject):
    """ Collection of BaseVoltages which is used to verify that the BusbarSection.BaseVoltage and other voltage attributes in the CIM are given a value existing in the collection.
    """
    # <<< base_voltage
    # @generated
    def __init__(self, is_dc=False, nominal_voltage=0.0, voltage_level=None, conducting_equipment=None, **kw_args):
        """ Initialises a new 'BaseVoltage' instance.
        """
        # If true, this is a direct current base voltage and items assigned to this base voltage are also associated with a direct current capabilities.   False indicates alternating current. 
        self.is_dc = is_dc
        # The PowerSystemResource's base voltage. 
        self.nominal_voltage = nominal_voltage
        
        self._voltage_level = []
        if voltage_level is None:
            self.voltage_level = []
        else:
            self.voltage_level = voltage_level
        self._conducting_equipment = []
        if conducting_equipment is None:
            self.conducting_equipment = []
        else:
            self.conducting_equipment = conducting_equipment

        super(BaseVoltage, self).__init__(**kw_args)
    # >>> base_voltage
        
    # <<< voltage_level
    # @generated
    def get_voltage_level(self):
        """ The VoltageLevels having this BaseVoltage.
        """
        return self._voltage_level

    def set_voltage_level(self, value):
        for x in self._voltage_level:
            x.base_voltage = None
        for y in value:
            y.base_voltage = self
        self._voltage_level = value
            
    voltage_level = property(get_voltage_level, set_voltage_level)
    
    def add_voltage_level(self, *voltage_level):
        for obj in voltage_level:
            obj._base_voltage = self
            if obj not in self._voltage_level:
                self._voltage_level.append(obj)
        
    def remove_voltage_level(self, *voltage_level):
        for obj in voltage_level:
            obj._base_voltage = None
            self._voltage_level.remove(obj)
    # >>> voltage_level

    # <<< conducting_equipment
    # @generated
    def get_conducting_equipment(self):
        """ Use association to ConductingEquipment only when there is no VoltageLevel container used.
        """
        return self._conducting_equipment

    def set_conducting_equipment(self, value):
        for x in self._conducting_equipment:
            x.base_voltage = None
        for y in value:
            y.base_voltage = self
        self._conducting_equipment = value
            
    conducting_equipment = property(get_conducting_equipment, set_conducting_equipment)
    
    def add_conducting_equipment(self, *conducting_equipment):
        for obj in conducting_equipment:
            obj._base_voltage = self
            if obj not in self._conducting_equipment:
                self._conducting_equipment.append(obj)
        
    def remove_conducting_equipment(self, *conducting_equipment):
        for obj in conducting_equipment:
            obj._base_voltage = None
            self._conducting_equipment.remove(obj)
    # >>> conducting_equipment



class Bay(EquipmentContainer):
    """ A collection of power system resources (within a given substation) including conducting equipment, protection relays, measurements, and telemetry.
    """
    # <<< bay
    # @generated
    def __init__(self, bay_power_meas_flag=False, bus_bar_configuration='double_bus', breaker_configuration='single_breaker', bay_energy_meas_flag=False, substation=None, voltage_level=None, **kw_args):
        """ Initialises a new 'Bay' instance.
        """
        # Indicates the presence/absence of active/reactive power measurements. 
        self.bay_power_meas_flag = bay_power_meas_flag
        # Bus bar configuration. Values are: "double_bus", "main_with_transfer", "single_bus", "ring_bus"
        self.bus_bar_configuration = bus_bar_configuration
        # Breaker configuration. Values are: "single_breaker", "no_breaker", "breaker_and_ahalf", "double_breaker"
        self.breaker_configuration = breaker_configuration
        # Indicates the presence/absence of energy measurements. 
        self.bay_energy_meas_flag = bay_energy_meas_flag
        
        self._substation = None
        self.substation = substation
        self._voltage_level = None
        self.voltage_level = voltage_level

        super(Bay, self).__init__(**kw_args)
    # >>> bay
        
    # <<< substation
    # @generated
    def get_substation(self):
        """ The association is used in the naming hierarchy.
        """
        return self._substation

    def set_substation(self, value):
        if self._substation is not None:
            filtered = [x for x in self.substation.bays if x != self]
            self._substation._bays = filtered
            
        self._substation = value
        if self._substation is not None:
            if self not in self._substation._bays:
                self._substation._bays.append(self)

    substation = property(get_substation, set_substation)
    # >>> substation

    # <<< voltage_level
    # @generated
    def get_voltage_level(self):
        """ The association is used in the naming hierarchy.
        """
        return self._voltage_level

    def set_voltage_level(self, value):
        if self._voltage_level is not None:
            filtered = [x for x in self.voltage_level.bays if x != self]
            self._voltage_level._bays = filtered
            
        self._voltage_level = value
        if self._voltage_level is not None:
            if self not in self._voltage_level._bays:
                self._voltage_level._bays.append(self)

    voltage_level = property(get_voltage_level, set_voltage_level)
    # >>> voltage_level



class RegularIntervalSchedule(BasicIntervalSchedule):
    """ The schedule has TimePoints where the time between them is constant.
    """
    # <<< regular_interval_schedule
    # @generated
    def __init__(self, end_time='', time_step=0.0, time_points=None, **kw_args):
        """ Initialises a new 'RegularIntervalSchedule' instance.
        """
        # The time for the last time point. 
        self.end_time = end_time
        # The time between each pair of subsequent RegularTimePoints. 
        self.time_step = time_step
        
        self._time_points = []
        if time_points is None:
            self.time_points = []
        else:
            self.time_points = time_points

        super(RegularIntervalSchedule, self).__init__(**kw_args)
    # >>> regular_interval_schedule
        
    # <<< time_points
    # @generated
    def get_time_points(self):
        """ The point data values that define a curve
        """
        return self._time_points

    def set_time_points(self, value):
        for x in self._time_points:
            x.interval_schedule = None
        for y in value:
            y.interval_schedule = self
        self._time_points = value
            
    time_points = property(get_time_points, set_time_points)
    
    def add_time_points(self, *time_points):
        for obj in time_points:
            obj._interval_schedule = self
            if obj not in self._time_points:
                self._time_points.append(obj)
        
    def remove_time_points(self, *time_points):
        for obj in time_points:
            obj._interval_schedule = None
            self._time_points.remove(obj)
    # >>> time_points



class BasePower(IdentifiedObject):
    """ The BasePower class defines the base power used in the per unit calculations.
    """
    # <<< base_power
    # @generated
    def __init__(self, base_power=0.0, **kw_args):
        """ Initialises a new 'BasePower' instance.
        """
        # Definition of base power. 
        self.base_power = base_power
        

        super(BasePower, self).__init__(**kw_args)
    # >>> base_power
        


class Terminal(IdentifiedObject):
    """ An electrical connection point to a piece of conducting equipment. Terminals are connected at physical connection points called 'connectivity nodes'.
    """
    # <<< terminal
    # @generated
    def __init__(self, connected=False, measurements=None, terminal_constraints=None, has_second_mutual_coupling=None, operational_limit_set=None, tie_flow=None, sv_power_flow=None, regulating_control=None, conducting_equipment=None, connectivity_node=None, has_first_mutual_coupling=None, topological_node=None, branch_group_terminal=None, bushing_asset=None, **kw_args):
        """ Initialises a new 'Terminal' instance.
        """
        # The terminal connection status.   True implies the terminal is connected, and false implies the terminal is not connected. This is the result of topoplogical processing of a detailed Connectivity node and Switch model whether present in the model or not.   A terminal that is not connected cannot support a current flow.   A terminal that is connected may have flow.  In general a multi-terminal device may simultaneously have connected and disconnected terminals.  No other aspect of the algorithm 
        self.connected = connected
        
        self._measurements = []
        if measurements is None:
            self.measurements = []
        else:
            self.measurements = measurements
        self._terminal_constraints = []
        if terminal_constraints is None:
            self.terminal_constraints = []
        else:
            self.terminal_constraints = terminal_constraints
        self._has_second_mutual_coupling = []
        if has_second_mutual_coupling is None:
            self.has_second_mutual_coupling = []
        else:
            self.has_second_mutual_coupling = has_second_mutual_coupling
        self._operational_limit_set = []
        if operational_limit_set is None:
            self.operational_limit_set = []
        else:
            self.operational_limit_set = operational_limit_set
        self._tie_flow = []
        if tie_flow is None:
            self.tie_flow = []
        else:
            self.tie_flow = tie_flow
        self._sv_power_flow = None
        self.sv_power_flow = sv_power_flow
        self._regulating_control = []
        if regulating_control is None:
            self.regulating_control = []
        else:
            self.regulating_control = regulating_control
        self._conducting_equipment = None
        self.conducting_equipment = conducting_equipment
        self._connectivity_node = None
        self.connectivity_node = connectivity_node
        self._has_first_mutual_coupling = []
        if has_first_mutual_coupling is None:
            self.has_first_mutual_coupling = []
        else:
            self.has_first_mutual_coupling = has_first_mutual_coupling
        self._topological_node = None
        self.topological_node = topological_node
        self._branch_group_terminal = []
        if branch_group_terminal is None:
            self.branch_group_terminal = []
        else:
            self.branch_group_terminal = branch_group_terminal
        self._bushing_asset = None
        self.bushing_asset = bushing_asset

        super(Terminal, self).__init__(**kw_args)
    # >>> terminal
        
    # <<< measurements
    # @generated
    def get_measurements(self):
        """ One or more measurements may be associated with a terminal in the network. Measurement-Terminal defines where the measurement is placed in the network topology. Some Measurements represent quantities related to a particular sensor position, e.g. a voltage transformer (PT) at a busbar or a current transformer (CT) at the bar between a breaker and an isolator. The sensing position is captured by the Measurement - Terminal association that makes it possible to place the sensing position at a  well defined place. The place is defined by the connection of the Terminal to ConductingEquipment.
        """
        return self._measurements

    def set_measurements(self, value):
        for x in self._measurements:
            x.terminal = None
        for y in value:
            y.terminal = self
        self._measurements = value
            
    measurements = property(get_measurements, set_measurements)
    
    def add_measurements(self, *measurements):
        for obj in measurements:
            obj._terminal = self
            if obj not in self._measurements:
                self._measurements.append(obj)
        
    def remove_measurements(self, *measurements):
        for obj in measurements:
            obj._terminal = None
            self._measurements.remove(obj)
    # >>> measurements

    # <<< terminal_constraints
    # @generated
    def get_terminal_constraints(self):
        """ 
        """
        return self._terminal_constraints

    def set_terminal_constraints(self, value):
        for x in self._terminal_constraints:
            x.terminal = None
        for y in value:
            y.terminal = self
        self._terminal_constraints = value
            
    terminal_constraints = property(get_terminal_constraints, set_terminal_constraints)
    
    def add_terminal_constraints(self, *terminal_constraints):
        for obj in terminal_constraints:
            obj._terminal = self
            if obj not in self._terminal_constraints:
                self._terminal_constraints.append(obj)
        
    def remove_terminal_constraints(self, *terminal_constraints):
        for obj in terminal_constraints:
            obj._terminal = None
            self._terminal_constraints.remove(obj)
    # >>> terminal_constraints

    # <<< has_second_mutual_coupling
    # @generated
    def get_has_second_mutual_coupling(self):
        """ Mutual couplings with the branch associated as the first branch.
        """
        return self._has_second_mutual_coupling

    def set_has_second_mutual_coupling(self, value):
        for x in self._has_second_mutual_coupling:
            x.second_terminal = None
        for y in value:
            y.second_terminal = self
        self._has_second_mutual_coupling = value
            
    has_second_mutual_coupling = property(get_has_second_mutual_coupling, set_has_second_mutual_coupling)
    
    def add_has_second_mutual_coupling(self, *has_second_mutual_coupling):
        for obj in has_second_mutual_coupling:
            obj._second_terminal = self
            if obj not in self._has_second_mutual_coupling:
                self._has_second_mutual_coupling.append(obj)
        
    def remove_has_second_mutual_coupling(self, *has_second_mutual_coupling):
        for obj in has_second_mutual_coupling:
            obj._second_terminal = None
            self._has_second_mutual_coupling.remove(obj)
    # >>> has_second_mutual_coupling

    # <<< operational_limit_set
    # @generated
    def get_operational_limit_set(self):
        """ The operatinal limits sets that applie specifically to this terminal.  Other operational limits sets may apply to this terminal through the association to Equipment.
        """
        return self._operational_limit_set

    def set_operational_limit_set(self, value):
        for x in self._operational_limit_set:
            x.terminal = None
        for y in value:
            y.terminal = self
        self._operational_limit_set = value
            
    operational_limit_set = property(get_operational_limit_set, set_operational_limit_set)
    
    def add_operational_limit_set(self, *operational_limit_set):
        for obj in operational_limit_set:
            obj._terminal = self
            if obj not in self._operational_limit_set:
                self._operational_limit_set.append(obj)
        
    def remove_operational_limit_set(self, *operational_limit_set):
        for obj in operational_limit_set:
            obj._terminal = None
            self._operational_limit_set.remove(obj)
    # >>> operational_limit_set

    # <<< tie_flow
    # @generated
    def get_tie_flow(self):
        """ The control area tie flows to which this terminal associates.
        """
        return self._tie_flow

    def set_tie_flow(self, value):
        for x in self._tie_flow:
            x.terminal = None
        for y in value:
            y.terminal = self
        self._tie_flow = value
            
    tie_flow = property(get_tie_flow, set_tie_flow)
    
    def add_tie_flow(self, *tie_flow):
        for obj in tie_flow:
            obj._terminal = self
            if obj not in self._tie_flow:
                self._tie_flow.append(obj)
        
    def remove_tie_flow(self, *tie_flow):
        for obj in tie_flow:
            obj._terminal = None
            self._tie_flow.remove(obj)
    # >>> tie_flow

    # <<< sv_power_flow
    # @generated
    def get_sv_power_flow(self):
        """ The power flow state associated with the terminal.
        """
        return self._sv_power_flow

    def set_sv_power_flow(self, value):
        if self._sv_power_flow is not None:
            self._sv_power_flow._terminal = None
            
        self._sv_power_flow = value
        if self._sv_power_flow is not None:
            self._sv_power_flow._terminal = self
            
    sv_power_flow = property(get_sv_power_flow, set_sv_power_flow)
    # >>> sv_power_flow

    # <<< regulating_control
    # @generated
    def get_regulating_control(self):
        """ The terminal is regulated by a control.
        """
        return self._regulating_control

    def set_regulating_control(self, value):
        for x in self._regulating_control:
            x.terminal = None
        for y in value:
            y.terminal = self
        self._regulating_control = value
            
    regulating_control = property(get_regulating_control, set_regulating_control)
    
    def add_regulating_control(self, *regulating_control):
        for obj in regulating_control:
            obj._terminal = self
            if obj not in self._regulating_control:
                self._regulating_control.append(obj)
        
    def remove_regulating_control(self, *regulating_control):
        for obj in regulating_control:
            obj._terminal = None
            self._regulating_control.remove(obj)
    # >>> regulating_control

    # <<< conducting_equipment
    # @generated
    def get_conducting_equipment(self):
        """ ConductingEquipment has 1 or 2 terminals that may be connected to other ConductingEquipment terminals via ConnectivityNodes
        """
        return self._conducting_equipment

    def set_conducting_equipment(self, value):
        if self._conducting_equipment is not None:
            filtered = [x for x in self.conducting_equipment.terminals if x != self]
            self._conducting_equipment._terminals = filtered
            
        self._conducting_equipment = value
        if self._conducting_equipment is not None:
            if self not in self._conducting_equipment._terminals:
                self._conducting_equipment._terminals.append(self)

    conducting_equipment = property(get_conducting_equipment, set_conducting_equipment)
    # >>> conducting_equipment

    # <<< connectivity_node
    # @generated
    def get_connectivity_node(self):
        """ Terminals interconnect with zero impedance at a node.  Measurements on a node apply to all of its terminals.
        """
        return self._connectivity_node

    def set_connectivity_node(self, value):
        if self._connectivity_node is not None:
            filtered = [x for x in self.connectivity_node.terminals if x != self]
            self._connectivity_node._terminals = filtered
            
        self._connectivity_node = value
        if self._connectivity_node is not None:
            if self not in self._connectivity_node._terminals:
                self._connectivity_node._terminals.append(self)

    connectivity_node = property(get_connectivity_node, set_connectivity_node)
    # >>> connectivity_node

    # <<< has_first_mutual_coupling
    # @generated
    def get_has_first_mutual_coupling(self):
        """ Mutual couplings associated with the branch as the first branch.
        """
        return self._has_first_mutual_coupling

    def set_has_first_mutual_coupling(self, value):
        for x in self._has_first_mutual_coupling:
            x.first_terminal = None
        for y in value:
            y.first_terminal = self
        self._has_first_mutual_coupling = value
            
    has_first_mutual_coupling = property(get_has_first_mutual_coupling, set_has_first_mutual_coupling)
    
    def add_has_first_mutual_coupling(self, *has_first_mutual_coupling):
        for obj in has_first_mutual_coupling:
            obj._first_terminal = self
            if obj not in self._has_first_mutual_coupling:
                self._has_first_mutual_coupling.append(obj)
        
    def remove_has_first_mutual_coupling(self, *has_first_mutual_coupling):
        for obj in has_first_mutual_coupling:
            obj._first_terminal = None
            self._has_first_mutual_coupling.remove(obj)
    # >>> has_first_mutual_coupling

    # <<< topological_node
    # @generated
    def get_topological_node(self):
        """ The topological node associated with the terminal.   This can be used as an alternative to the connectivity node path to topological node, thus making it unneccesary to model connedtivity nodes in some cases.   Note that the if connectivity nodes are in the model, this association would proably not be used.
        """
        return self._topological_node

    def set_topological_node(self, value):
        if self._topological_node is not None:
            filtered = [x for x in self.topological_node.terminal if x != self]
            self._topological_node._terminal = filtered
            
        self._topological_node = value
        if self._topological_node is not None:
            if self not in self._topological_node._terminal:
                self._topological_node._terminal.append(self)

    topological_node = property(get_topological_node, set_topological_node)
    # >>> topological_node

    # <<< branch_group_terminal
    # @generated
    def get_branch_group_terminal(self):
        """ The directed branch group terminals for which the terminal is monitored.
        """
        return self._branch_group_terminal

    def set_branch_group_terminal(self, value):
        for x in self._branch_group_terminal:
            x.terminal = None
        for y in value:
            y.terminal = self
        self._branch_group_terminal = value
            
    branch_group_terminal = property(get_branch_group_terminal, set_branch_group_terminal)
    
    def add_branch_group_terminal(self, *branch_group_terminal):
        for obj in branch_group_terminal:
            obj._terminal = self
            if obj not in self._branch_group_terminal:
                self._branch_group_terminal.append(obj)
        
    def remove_branch_group_terminal(self, *branch_group_terminal):
        for obj in branch_group_terminal:
            obj._terminal = None
            self._branch_group_terminal.remove(obj)
    # >>> branch_group_terminal

    # <<< bushing_asset
    # @generated
    def get_bushing_asset(self):
        """ 
        """
        return self._bushing_asset

    def set_bushing_asset(self, value):
        if self._bushing_asset is not None:
            self._bushing_asset._terminal = None
            
        self._bushing_asset = value
        if self._bushing_asset is not None:
            self._bushing_asset._terminal = self
            
    bushing_asset = property(get_bushing_asset, set_bushing_asset)
    # >>> bushing_asset



class Substation(EquipmentContainer):
    """ A collection of equipment for purposes other than generation or utilization, through which electric energy in bulk is passed for the purposes of switching or modifying its characteristics.
    """
    # <<< substation
    # @generated
    def __init__(self, region=None, voltage_levels=None, substation_asset=None, bays=None, **kw_args):
        """ Initialises a new 'Substation' instance.
        """
        
        self._region = None
        self.region = region
        self._voltage_levels = []
        if voltage_levels is None:
            self.voltage_levels = []
        else:
            self.voltage_levels = voltage_levels
        self._substation_asset = None
        self.substation_asset = substation_asset
        self._bays = []
        if bays is None:
            self.bays = []
        else:
            self.bays = bays

        super(Substation, self).__init__(**kw_args)
    # >>> substation
        
    # <<< region
    # @generated
    def get_region(self):
        """ The association is used in the naming hierarchy.
        """
        return self._region

    def set_region(self, value):
        if self._region is not None:
            filtered = [x for x in self.region.substations if x != self]
            self._region._substations = filtered
            
        self._region = value
        if self._region is not None:
            if self not in self._region._substations:
                self._region._substations.append(self)

    region = property(get_region, set_region)
    # >>> region

    # <<< voltage_levels
    # @generated
    def get_voltage_levels(self):
        """ The association is used in the naming hierarchy.
        """
        return self._voltage_levels

    def set_voltage_levels(self, value):
        for x in self._voltage_levels:
            x.substation = None
        for y in value:
            y.substation = self
        self._voltage_levels = value
            
    voltage_levels = property(get_voltage_levels, set_voltage_levels)
    
    def add_voltage_levels(self, *voltage_levels):
        for obj in voltage_levels:
            obj._substation = self
            if obj not in self._voltage_levels:
                self._voltage_levels.append(obj)
        
    def remove_voltage_levels(self, *voltage_levels):
        for obj in voltage_levels:
            obj._substation = None
            self._voltage_levels.remove(obj)
    # >>> voltage_levels

    # <<< substation_asset
    # @generated
    def get_substation_asset(self):
        """ 
        """
        return self._substation_asset

    def set_substation_asset(self, value):
        if self._substation_asset is not None:
            self._substation_asset._substation = None
            
        self._substation_asset = value
        if self._substation_asset is not None:
            self._substation_asset._substation = self
            
    substation_asset = property(get_substation_asset, set_substation_asset)
    # >>> substation_asset

    # <<< bays
    # @generated
    def get_bays(self):
        """ The association is used in the naming hierarchy.
        """
        return self._bays

    def set_bays(self, value):
        for x in self._bays:
            x.substation = None
        for y in value:
            y.substation = self
        self._bays = value
            
    bays = property(get_bays, set_bays)
    
    def add_bays(self, *bays):
        for obj in bays:
            obj._substation = self
            if obj not in self._bays:
                self._bays.append(obj)
        
    def remove_bays(self, *bays):
        for obj in bays:
            obj._substation = None
            self._bays.remove(obj)
    # >>> bays



class VoltageLevel(EquipmentContainer):
    """ A collection of equipment at one common system voltage forming a switchgear. The equipment typically consist of breakers, busbars, instrumentation, control, regulation and protection devices as well as assemblies of all these.
    """
    # <<< voltage_level
    # @generated
    def __init__(self, low_voltage_limit=0.0, high_voltage_limit=0.0, bays=None, base_voltage=None, substation=None, **kw_args):
        """ Initialises a new 'VoltageLevel' instance.
        """
        # The bus bar's low voltage limit 
        self.low_voltage_limit = low_voltage_limit
        # The bus bar's high voltage limit 
        self.high_voltage_limit = high_voltage_limit
        
        self._bays = []
        if bays is None:
            self.bays = []
        else:
            self.bays = bays
        self._base_voltage = None
        self.base_voltage = base_voltage
        self._substation = None
        self.substation = substation

        super(VoltageLevel, self).__init__(**kw_args)
    # >>> voltage_level
        
    # <<< bays
    # @generated
    def get_bays(self):
        """ The association is used in the naming hierarchy.
        """
        return self._bays

    def set_bays(self, value):
        for x in self._bays:
            x.voltage_level = None
        for y in value:
            y.voltage_level = self
        self._bays = value
            
    bays = property(get_bays, set_bays)
    
    def add_bays(self, *bays):
        for obj in bays:
            obj._voltage_level = self
            if obj not in self._bays:
                self._bays.append(obj)
        
    def remove_bays(self, *bays):
        for obj in bays:
            obj._voltage_level = None
            self._bays.remove(obj)
    # >>> bays

    # <<< base_voltage
    # @generated
    def get_base_voltage(self):
        """ The base voltage used for all equipment within the VoltageLevel.
        """
        return self._base_voltage

    def set_base_voltage(self, value):
        if self._base_voltage is not None:
            filtered = [x for x in self.base_voltage.voltage_level if x != self]
            self._base_voltage._voltage_level = filtered
            
        self._base_voltage = value
        if self._base_voltage is not None:
            if self not in self._base_voltage._voltage_level:
                self._base_voltage._voltage_level.append(self)

    base_voltage = property(get_base_voltage, set_base_voltage)
    # >>> base_voltage

    # <<< substation
    # @generated
    def get_substation(self):
        """ The association is used in the naming hierarchy.
        """
        return self._substation

    def set_substation(self, value):
        if self._substation is not None:
            filtered = [x for x in self.substation.voltage_levels if x != self]
            self._substation._voltage_levels = filtered
            
        self._substation = value
        if self._substation is not None:
            if self not in self._substation._voltage_levels:
                self._substation._voltage_levels.append(self)

    substation = property(get_substation, set_substation)
    # >>> substation



class IrregularIntervalSchedule(BasicIntervalSchedule):
    """ The schedule has TimePoints where the time between them varies.
    """
    # <<< irregular_interval_schedule
    # @generated
    def __init__(self, time_points=None, **kw_args):
        """ Initialises a new 'IrregularIntervalSchedule' instance.
        """
        
        self._time_points = []
        if time_points is None:
            self.time_points = []
        else:
            self.time_points = time_points

        super(IrregularIntervalSchedule, self).__init__(**kw_args)
    # >>> irregular_interval_schedule
        
    # <<< time_points
    # @generated
    def get_time_points(self):
        """ The point data values that define a curve
        """
        return self._time_points

    def set_time_points(self, value):
        for x in self._time_points:
            x.interval_schedule = None
        for y in value:
            y.interval_schedule = self
        self._time_points = value
            
    time_points = property(get_time_points, set_time_points)
    
    def add_time_points(self, *time_points):
        for obj in time_points:
            obj._interval_schedule = self
            if obj not in self._time_points:
                self._time_points.append(obj)
        
    def remove_time_points(self, *time_points):
        for obj in time_points:
            obj._interval_schedule = None
            self._time_points.remove(obj)
    # >>> time_points



class ConductingEquipment(Equipment):
    """ The parts of the power system that are designed to carry current or that are conductively connected therewith. ConductingEquipment is contained within an EquipmentContainer that may be a Substation, or a VoltageLevel or a Bay within a Substation.
    """
    # <<< conducting_equipment
    # @generated
    def __init__(self, phases='abn', electrical_assets=None, protection_equipments=None, outage_step_roles=None, sv_status=None, clearance_tags=None, base_voltage=None, terminals=None, **kw_args):
        """ Initialises a new 'ConductingEquipment' instance.
        """
        # Describes the phases carried by a conducting equipment. Values are: "abn", "bc", "acn", "bn", "ac", "abc", "an", "ab", "c", "b", "abcn", "a", "cn", "n", "bcn"
        self.phases = phases
        
        self._electrical_assets = []
        if electrical_assets is None:
            self.electrical_assets = []
        else:
            self.electrical_assets = electrical_assets
        self._protection_equipments = []
        if protection_equipments is None:
            self.protection_equipments = []
        else:
            self.protection_equipments = protection_equipments
        self._outage_step_roles = []
        if outage_step_roles is None:
            self.outage_step_roles = []
        else:
            self.outage_step_roles = outage_step_roles
        self._sv_status = None
        self.sv_status = sv_status
        self._clearance_tags = []
        if clearance_tags is None:
            self.clearance_tags = []
        else:
            self.clearance_tags = clearance_tags
        self._base_voltage = None
        self.base_voltage = base_voltage
        self._terminals = []
        if terminals is None:
            self.terminals = []
        else:
            self.terminals = terminals

        super(ConductingEquipment, self).__init__(**kw_args)
    # >>> conducting_equipment
        
    # <<< electrical_assets
    # @generated
    def get_electrical_assets(self):
        """ 
        """
        return self._electrical_assets

    def set_electrical_assets(self, value):
        for x in self._electrical_assets:
            x.conducting_equipment = None
        for y in value:
            y.conducting_equipment = self
        self._electrical_assets = value
            
    electrical_assets = property(get_electrical_assets, set_electrical_assets)
    
    def add_electrical_assets(self, *electrical_assets):
        for obj in electrical_assets:
            obj._conducting_equipment = self
            if obj not in self._electrical_assets:
                self._electrical_assets.append(obj)
        
    def remove_electrical_assets(self, *electrical_assets):
        for obj in electrical_assets:
            obj._conducting_equipment = None
            self._electrical_assets.remove(obj)
    # >>> electrical_assets

    # <<< protection_equipments
    # @generated
    def get_protection_equipments(self):
        """ Protection equipment may be used to protect specific Conducting Equipment. Multiple equipment may be protected or monitored by multiple protection equipment.
        """
        return self._protection_equipments

    def set_protection_equipments(self, value):
        for p in self._protection_equipments:
            filtered = [q for q in p.conducting_equipments if q != self]
            self._protection_equipments._conducting_equipments = filtered
        for r in value:
            if self not in r._conducting_equipments:
                r._conducting_equipments.append(self)
        self._protection_equipments = value
            
    protection_equipments = property(get_protection_equipments, set_protection_equipments)
    
    def add_protection_equipments(self, *protection_equipments):
        for obj in protection_equipments:
            if self not in obj._conducting_equipments:
                obj._conducting_equipments.append(self)
            self._protection_equipments.append(obj)
        
    def remove_protection_equipments(self, *protection_equipments):
        for obj in protection_equipments:
            if self in obj._conducting_equipments:
                obj._conducting_equipments.remove(self)
            self._protection_equipments.remove(obj)
    # >>> protection_equipments

    # <<< outage_step_roles
    # @generated
    def get_outage_step_roles(self):
        """ 
        """
        return self._outage_step_roles

    def set_outage_step_roles(self, value):
        for x in self._outage_step_roles:
            x.conducting_equipment = None
        for y in value:
            y.conducting_equipment = self
        self._outage_step_roles = value
            
    outage_step_roles = property(get_outage_step_roles, set_outage_step_roles)
    
    def add_outage_step_roles(self, *outage_step_roles):
        for obj in outage_step_roles:
            obj._conducting_equipment = self
            if obj not in self._outage_step_roles:
                self._outage_step_roles.append(obj)
        
    def remove_outage_step_roles(self, *outage_step_roles):
        for obj in outage_step_roles:
            obj._conducting_equipment = None
            self._outage_step_roles.remove(obj)
    # >>> outage_step_roles

    # <<< sv_status
    # @generated
    def get_sv_status(self):
        """ The status state associated with the conducting equipment.
        """
        return self._sv_status

    def set_sv_status(self, value):
        if self._sv_status is not None:
            self._sv_status._conducting_equipment = None
            
        self._sv_status = value
        if self._sv_status is not None:
            self._sv_status._conducting_equipment = self
            
    sv_status = property(get_sv_status, set_sv_status)
    # >>> sv_status

    # <<< clearance_tags
    # @generated
    def get_clearance_tags(self):
        """ Conducting equipment may have multiple clearance tags for authorized field work
        """
        return self._clearance_tags

    def set_clearance_tags(self, value):
        for x in self._clearance_tags:
            x.conducting_equipment = None
        for y in value:
            y.conducting_equipment = self
        self._clearance_tags = value
            
    clearance_tags = property(get_clearance_tags, set_clearance_tags)
    
    def add_clearance_tags(self, *clearance_tags):
        for obj in clearance_tags:
            obj._conducting_equipment = self
            if obj not in self._clearance_tags:
                self._clearance_tags.append(obj)
        
    def remove_clearance_tags(self, *clearance_tags):
        for obj in clearance_tags:
            obj._conducting_equipment = None
            self._clearance_tags.remove(obj)
    # >>> clearance_tags

    # <<< base_voltage
    # @generated
    def get_base_voltage(self):
        """ Use association to ConductingEquipment only when there is no VoltageLevel container used.
        """
        return self._base_voltage

    def set_base_voltage(self, value):
        if self._base_voltage is not None:
            filtered = [x for x in self.base_voltage.conducting_equipment if x != self]
            self._base_voltage._conducting_equipment = filtered
            
        self._base_voltage = value
        if self._base_voltage is not None:
            if self not in self._base_voltage._conducting_equipment:
                self._base_voltage._conducting_equipment.append(self)

    base_voltage = property(get_base_voltage, set_base_voltage)
    # >>> base_voltage

    # <<< terminals
    # @generated
    def get_terminals(self):
        """ ConductingEquipment has 1 or 2 terminals that may be connected to other ConductingEquipment terminals via ConnectivityNodes
        """
        return self._terminals

    def set_terminals(self, value):
        for x in self._terminals:
            x.conducting_equipment = None
        for y in value:
            y.conducting_equipment = self
        self._terminals = value
            
    terminals = property(get_terminals, set_terminals)
    
    def add_terminals(self, *terminals):
        for obj in terminals:
            obj._conducting_equipment = self
            if obj not in self._terminals:
                self._terminals.append(obj)
        
    def remove_terminals(self, *terminals):
        for obj in terminals:
            obj._conducting_equipment = None
            self._terminals.remove(obj)
    # >>> terminals



# <<< core
# @generated
# >>> core
