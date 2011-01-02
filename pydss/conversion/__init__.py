# Copyright (C) 2010 Richard Lincoln
#
# This library is free software; you can redistribute it and/or
# modify it under the terms of the GNU Lesser General Public
# License as published by the Free Software Foundation; either
# version 2.1 of the License, or (at your option) any later version.
#
# This library is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
# Lesser General Public License for more details.
#
# You should have received a copy of the GNU Lesser General Public
# License along with this library; if not, write to the Free Software
# Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA, USA

from pydss.conversion.PowerConversionElement import PowerConversionElement
from pydss.conversion.CurrentSource import CurrentSource
from pydss.conversion.Equivalent import Equivalent
from pydss.conversion.Generator import Generator
from pydss.conversion.Load import Load
from pydss.conversion.VoltageSource import VoltageSource
from pydss.conversion.Storage import Storage

class sequenceType(str):
    """Values are: Positive, Zero, None
    """
    pass

class generatorModel(str):
    """Values are: Constant_kW, Constant_Y, Constant_kW_and_kV, Const_kW_Fixed_Q, Const_kW_Fixed_Q_Const_X, User_model, Const_kW_KVAr_Limited_I
    """
    pass

class dispatchType(str):
    """Values are: LoadMode, PriceMode
    """
    pass

class generatorStatus(str):
    """Values are: Variable, Fixed
    """
    pass

class loadModel(str):
    """Values are: PQ, Const_Y, Motor, Linear_P_Quad_Q, Const_I, Const_P_Fixed_Q, Const_P_Fixed_Z
    """
    pass

class loadStatus(str):
    """Values are: Variable, Fixed, Exempt
    """
    pass

class loadSpecType(str):
    """Values are: kW_PF, kW_kVar, kVA_PF
    """
    pass

class specType(str):
    """Values are: MVASc, ISc, Z1Z0
    """
    pass

class storageState(str):
    """Values are: Idling, Charging, Discharging
    """
    pass
