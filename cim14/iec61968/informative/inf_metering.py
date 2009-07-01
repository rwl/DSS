# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

from cim14.iec61970.core import IdentifiedObject
from cim14.iec61968.metering import DeviceFunction

# <<< imports
# @generated
# >>> imports

ns_prefix = "cim.IEC61968.Informative.InfMetering"

ns_uri = "http://iec.ch/TC57/2009/CIM-schema-cim14#IEC61968.Informative.InfMetering"

class ComPort(IdentifiedObject):
    """ Port information used for communication connectivity purposes. The 'port' names the physical association to another device from the perspective of the parent asset. For example, a communications module may need to list the meters which it can talk to as meter serial number '123' is on 'port 0', S/N '456' is on port '1', etc. A meter or load control device may need to know that a hot-water heater load is on 'port 0', and an air-conditioner on 'port 1'.
    """
    pass
    # <<< com_port
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'ComPort' instance.
        """

        super(ComPort, self).__init__(**kw_args)
    # >>> com_port


class MeteringFunctionConfiguration(IdentifiedObject):
    """ The configuration of data for a given meter function.
    """
    def get_electric_metering_functions(self):
        """ All electric metering functions with this configuration.
        """
        return self._electric_metering_functions

    def set_electric_metering_functions(self, value):
        for x in self._electric_metering_functions:
            x._metering_function_configuration = None
        for y in value:
            y._metering_function_configuration = self
        self._electric_metering_functions = value
            
    electric_metering_functions = property(get_electric_metering_functions, set_electric_metering_functions)
    
    def add_electric_metering_functions(self, *electric_metering_functions):
        for obj in electric_metering_functions:
            obj._metering_function_configuration = self
            self._electric_metering_functions.append(obj)
        
    def remove_electric_metering_functions(self, *electric_metering_functions):
        for obj in electric_metering_functions:
            obj._metering_function_configuration = None
            self._electric_metering_functions.remove(obj)

    # <<< metering_function_configuration
    # @generated
    def __init__(self, electric_metering_functions=[], **kw_args):
        """ Initialises a new 'MeteringFunctionConfiguration' instance.
        """
        self._electric_metering_functions = []
        self.electric_metering_functions = electric_metering_functions

        super(MeteringFunctionConfiguration, self).__init__(**kw_args)
    # >>> metering_function_configuration


class WaterMeteringFunction(DeviceFunction):
    """ Functionality performed by a water meter. It's entirely possible that the metering system would carry information to/from water meters even though it was built primarily to carry the higher-value electric meter data.
    """
    pass
    # <<< water_metering_function
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'WaterMeteringFunction' instance.
        """

        super(WaterMeteringFunction, self).__init__(**kw_args)
    # >>> water_metering_function


class GasMeteringFunction(DeviceFunction):
    """ Functionality performed by a gas meter. It's entirely possible that the metering system would carry information to/from gas meters even though it was built primarily to carry the higher-value electric meter data.
    """
    pass
    # <<< gas_metering_function
    # @generated
    def __init__(self, **kw_args):
        """ Initialises a new 'GasMeteringFunction' instance.
        """

        super(GasMeteringFunction, self).__init__(**kw_args)
    # >>> gas_metering_function


# <<< inf_metering
# @generated
# >>> inf_metering
