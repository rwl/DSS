# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.
""" A crew is the collection of People, Vehicles, and Equipment that can be assigned to perform specific work tasks.  A crew belongs to a specific organization and has crew capability and shift pattern.  Each crew can work in one or more zones.
"""

from iec61968.core2.collections import Collection
from  import 
from  import 

# <<< imports
# @generated
# >>> imports

class Crew(Collection):
    """ A crew is a collection of people (ErpPersons) with specific skills, tools, and vehicles.
    """
    outage_steps = []

    route = None

    vehicles = []

    tools = []

    organisations = []

    capabilities = []

    equipment_assets = []

    shift_patterns = []

    locations = []

    # <<< crew
    # @generated
    def __init__(self, outage_steps=[], route=None, vehicles=[], tools=[], organisations=[], capabilities=[], equipment_assets=[], shift_patterns=[], locations=[], **kw_args):
        """ Initialises a new 'Crew' instance.
        """
        self.outage_steps = outage_steps
        self.route = route
        self.vehicles = vehicles
        self.tools = tools
        self.organisations = organisations
        self.capabilities = capabilities
        self.equipment_assets = equipment_assets
        self.shift_patterns = shift_patterns
        self.locations = locations

        super(Crew, self).__init__(**kw_args)
    # >>> crew


class Capability():
    """ Capabilities of a crew.
    """
    # Date and time capability became effective. 
    effective_date = ''

    # Date and time capability expires. 
    expiration_date = ''

    # Capability performance factor. 
    performance_factor = ''

    work_tasks = []

    crew = None

    crafts = []

    # <<< capability
    # @generated
    def __init__(self, effective_date='', expiration_date='', performance_factor='', work_tasks=[], crew=None, crafts=[], **kw_args):
        """ Initialises a new 'Capability' instance.
        """
        self.effective_date = effective_date
        self.expiration_date = expiration_date
        self.performance_factor = performance_factor
        self.work_tasks = work_tasks
        self.crew = crew
        self.crafts = crafts

        super(Capability, self).__init__(**kw_args)
    # >>> capability


class ShiftPattern():
    """ The patterns of shifts worked by people or crews.
    """
    # Type of assignement intended to be worked on this shift, for example, temporary, standard, etc. 
    assignment_type = ''

    # The number of cycles for a temporary shift. 
    number_cycles = ''

    # Date and time shift pattern became effective. 
    effective_date = ''

    # Date and time shift pattern expires. 
    expiration_date = ''

    crews = []

    # <<< shift_pattern
    # @generated
    def __init__(self, assignment_type='', number_cycles='', effective_date='', expiration_date='', crews=[], **kw_args):
        """ Initialises a new 'ShiftPattern' instance.
        """
        self.assignment_type = assignment_type
        self.number_cycles = number_cycles
        self.effective_date = effective_date
        self.expiration_date = expiration_date
        self.crews = crews

        super(ShiftPattern, self).__init__(**kw_args)
    # >>> shift_pattern


class CrewsVersion(object):
 
    version = ''

 
    date = ''

    # <<< crews_version
    # @generated
    def __init__(self, version='', date='', **kw_args):
        """ Initialises a new 'CrewsVersion' instance.
        """
        self.version = version
        self.date = date

        super(CrewsVersion, self).__init__(**kw_args)
    # >>> crews_version


# <<< crew
# @generated
# >>> crew
