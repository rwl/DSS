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

from dss.conversion.PowerConversionElement import PowerConversionElement

class Generator(PowerConversionElement):
    """The generator is essentially a negative load that can be dispatched.
    If the dispatch value (DispValue) is 0, the generator always follows the
    appropriate dispatch curve, which are simply load curves. If DispValue>0
    then the generator only comes on when the global circuit load multiplier
    exceeds DispValue.  When the generator is on, it always follows the
    dispatch curve appropriate for the type of solution being performed.  If
    you want to model a generator that is fully on whenever it is dispatched
    on, simply designate 'Status=Fixed'.  The default is 'Status=Variable'
    (i.e., it follows a dispatch curve.  You could also define a dispatch curve
    that is always 1.0.  Generators have their own energy meters that record:
        1. Total kwh
        2. Total kvarh
        3. Max kW
        4. Max kVA
        5. Hours in operation
        6. Price * kwH
    Generator meters reset with the circuit energy meters and take a sample
    with the circuit energy meters as well. The Energy meters also used
    trapezoidal integration so that they are compatible with Load-Duration
    simulations.  Generator models are:
        1. Constant P, Q  (* dispatch curve, if appropriate).
        2. Constant Z  (For simple solution)
        3. Constant P, |V|  like a standard power flow  [not implemented]
        4. Constant P, Fixed Q  (vars)
        5. Constant P, Fixed Q  (reactance)
    Most of the time you will use #1 for planning studies.  The default is for
    the generator to be a current injection source.  Thus, its primitive Y
    matrix contains only the impedance that might exist from the neutral of a
    wye-connected generator to ground.  However, if the generator model is
    switched to Admittance from PowerFlow (see Set Mode command), the generator
    is converted to an admittance and included in the system Y matrix.
    Generators are assumed balanced for the number of phases specified.
    If you would like unbalanced generators, enter separate single-phase
    generators.
    """

    def __init__(self, bus1='', kV=0.0, kW=0.0, pf=0.0, kVAr=0.0,
            model="Constant_kW", vMinPU=0.0, vMaxPU=0.0, yearly='', daily='',
            duty='', dispMode="LoadMode", dispValue=0.0, conn="Wye", rNeut=0.0,
            xNeut=0.0, status="Variable", cls=0, vPU=0.0, vTarget=0.0,
            maxKVAr=0.0, minKVAr=0.0, pvFactor=0.0, forceOn=False, kVA=0.0,
            MVA=0.0, xD=0.0, xDp=0.0, xDpp=0.0, h=0.0, d=0.0, userModel='',
            userData='', shaftModel='', shaftData='', debugTrace=False,
            genOn=False, shapeFactor="", forcedOn=False, fixed=False, yEq="",
            yEq95="", yEq105="", vBase=0.0, vBase95=0.0, vBase105=0.0,
            varBase=0.0, varMin=0.0, varMax=0.0, deltaQMax=0.0, dQdV=0.0,
            dQdVSaved=0.0, yQFixed=0.0, circuit=None, *args, **kw_args):
        """Initialises a new 'Generator' instance.
        """
        #: Bus to which the Generator is connected.  May include specific node
        #  specification.
        self.bus1 = bus1

        #: Nominal rated (1.0 per unit) voltage, kV, for Generator. For 2-
        #  and 3-phase Generators, specify phase-phase kV. Otherwise, specify
        #  actual kV across each branch of the Generator. If wye (star),
        #  specify phase-neutral kV.  If delta or phase-phase connected,
        #  specify phase-phase kV.
        self.kV = kV

        #: Total base kW for the Generator.  A positive value denotes power
        #  coming OUT of the element, which is the opposite of a load. This
        #  value is modified depending on the dispatch mode.  Unaffected by
        #  the global load multiplier and growth curves.  If you want there
        #  to be more generation, you must add more generators or change this
        #  value.
        self.kW = kW

        #: Generator power factor. Default is 0.80. Enter negative for leading
        #  powerfactor (when kW and kvar have opposite signs.) A positive power
        #  factor for a generator signifies that the generator produces vars as
        #  is typical for a synchronous generator.  Induction machines would be
        #  specified with a negative power factor.
        self.pf = pf

        #: Specify the base kvar.  Alternative to specifying the power factor.
        #  Side effect: the power factor value is altered to agree based on
        #  present value of kW.
        self.kVAr = kVAr

        #: Integer code for the model to use for generation variation with
        #  voltage. Valid values are:
        #      1:Generator injects a constant kW at specified power factor.
        #      2:Generator is modeled as a constant admittance.
        #      3:Const kW, constant kV. Somewhat like a conventional
        #      transmission power flow P-V generator.
        #      4:Const kW, Fixed Q (Q never varies)
        #      5:Const kW, Fixed Q(as a constant reactance)
        #      6:Compute load injection from User-written Model.(see usage of
        #        Xd,Xdp)
        #      7:Constant kW, kvar, but current limited below Vminpu
        #  Values are: "Constant_kW", "Constant_Y", "Constant_kW_and_kV",
        #  "Const_kW_Fixed_Q", "Const_kW_Fixed_Q_Const_X", "User_model",
        #  "Const_kW_KVAr_Limited_I"
        self.model = model

        #: Minimum per unit voltage for which the Model is assumed to apply.
        #  Below this value, the load model reverts to a constant impedance
        #  model.
        self.vMinPU = vMinPU

        #: Maximum per unit voltage for which the Model is assumed to apply.
        #  Above this value, the load model reverts to a constant impedance
        #  model.
        self.vMaxPU = vMaxPU

        #: Dispatch shape to use for yearly simulations.  Must be previously
        #  defined as a Loadshape object. If this is not specified, the daily
        #  dispatch shape is repeated. If the generator is assumed to be ON
        #  continuously, specify this value as FIXED, or designate a curve that
        #  is 1.0 per unit at all times. Nominally for 8760 simulations.  If
        #  there are fewer points in the designated shape than the number of
        #  points in the solution, the curve is repeated.
        self.yearly = yearly

        #: Dispatch shape to use for daily simulations.  Must be previously
        #  defined as a Loadshape object of 24 hrs, typically.  If generator
        #  is assumed to be ON continuously, specify this value as FIXED, or
        #  designate a Loadshape object that is 1.0 perunit for all hours.
        self.daily = daily

        #: Load shape to use for duty cycle dispatch simulations such as for
        #  wind generation. Must be previously defined as a Loadshape object.
        #  Typically would have time intervals less than 1 hr -- perhaps, in
        #  seconds.  Designate the number of points to solve using the Set
        #  Number=xxxx command.  If there are fewer points in the actual shape,
        #  the shape is assumed to repeat.
        self.duty = duty

        #: In default mode, gen is either always on or follows dispatch curve
        #  as specified.  Otherwise, the gen comes on when either the global
        #  default load level or the price level exceeds the dispatch value.
        #  Values are: "LoadMode", "PriceMode"
        self.dispMode = dispMode

        #: If = 0.0 Then Generator follow dispatch curves, if any.  If > 0
        #  Then Generator is ON only when either the price signal exceeds this
        #  value or the load multiplier (set loadmult=) times the default
        #  yearly growth factor exceeds this value.  Then the generator follows
        #  dispatch curves, if any (see also Status).
        self.dispValue = dispValue

        #: Connection type. Values are: "Wye", "LN", "Delta", "LL"
        self.conn = conn

        #: Removed due to causing confusion - Add neutral impedance externally.
        self.rNeut = rNeut

        #: Removed due to causing confusion - Add neutral impedance externally.
        self.xNeut = xNeut

        #: If Fixed, then dispatch multipliers do not apply. The generator is
        #  always at full power when it is ON. Default is Variable (follows
        #  curves). Values are: "Variable", "Fixed"
        self.status = status

        #: An arbitrary integer number representing the class of Generator so
        #  that Generator values may be segregated by class.
        self.cls = cls

        #: Per Unit voltage set point for Model = 3  (typical power flow
        #  model).
        self.vPU = vPU

        #: Target voltage for generator with voltage control.
        self.vTarget = vTarget

        #: Maximum kvar limit for Model = 3.  Defaults to twice the specified
        #  load kvar. Always reset this if you change PF or kvar properties.
        self.maxKVAr = maxKVAr

        #: Minimum kvar limit for Model = 3. Enter a negative number if
        #  generator can absorb vars.  Defaults to negative of Maxkvar.
        #  Always reset this if you change PF or kvar properties.
        self.minKVAr = minKVAr

        #: Deceleration factor for P-V generator model (Model=3).  Default
        #  is 0.1.  If the circuit converges easily, you may want to use a
        #  higher number such as 1.0. Use a lower number if solution diverges.
        #  Use Debugtrace=yes to create a file that will trace the convergence
        #  of a generator model.
        self.pvFactor = pvFactor

        #: Forces generator ON despite requirements of other dispatch modes.
        #  Stays ON until this property is set to NO, or an internal algorithm
        #  cancels the forced ON state.
        self.forceOn = forceOn

        #: kVA rating of electrical machine. Defaults to 1.2* kW if not
        #  specified.  Applied to machine or inverter definition for Dynamics
        #  mode solutions.
        self.kVA = kVA

        #: MVA rating of electrical machine.  Alternative to using kVA.
        self.MVA = MVA

        #: Per unit synchronous reactance of machine. Presently used only for
        #  Thevinen impedance for power flow calcs of user models (model=6).
        #  Typically use a value 0.4 to 1.0. Default is 1.0.
        self.xD = xD

        #: Per unit transient reactance of the machine.  Used for Dynamics mode
        #  and Fault studies.  Default is 0.27.  For user models, this value is
        #  used for the Thevinen/Norton impedance for Dynamics Mode.
        self.xDp = xDp

        #: Per unit subtransient reactance of the machine.  Used for Harmonics.
        #  Default is 0.20.
        self.xDpp = xDpp

        #: Per unit mass constant of the machine.  MW-sec/MVA.
        self.h = h

        #: Damping constant.  Usual range is 0 to 4. Default is 1.0.  Adjust
        #  to get damping
        self.d = d

        #: Name of DLL containing user-written model, which computes the
        #  terminal currents for Dynamics studies, overriding the default
        #  model.  Set to 'none' to negate previous setting.
        self.userModel = userModel

        #: String (in quotes or parentheses) that gets passed to user-written
        #  model for defining the data required for that model.
        self.userData = userData

        #: Name of user-written DLL containing a Shaft model, which models the
        #  prime mover and determines the power on the shaft for Dynamics
        #  studies.  Models additional mass elements other than the single-mass
        #  model in the DSS default model. Set to 'none' to negate previous
        #  setting.
        self.shaftModel = shaftModel

        #: String (in quotes or parentheses) that gets passed to user-written
        #  shaft dynamic model for defining the data for that model.
        self.shaftData = shaftData

        #: Turn this on to capture the progress of the generator model for each
        #  iteration.  Creates a separate file for each generator named
        #  'GEN_name.CSV'.
        self.debugTrace = debugTrace

        #: Indicates whether generator is currently on.
        self.genOn = genOn

        self.shapeFactor = shapeFactor

        self.forcedOn = forcedOn

        #: Should generator always be at base value.
        self.fixed = fixed

        #: Y at nominal.
        self.yEq = yEq

        #: Y at 95%.
        self.yEq95 = yEq95

        #: Y at 105%.
        self.yEq105 = yEq105

        #: Base volts suitable for computing currents.
        self.vBase = vBase

        self.vBase95 = vBase95

        self.vBase105 = vBase105

        self.varBase = varBase

        self.varMin = varMin

        self.varMax = varMax

        #: Maximum allowable var change on model=3 per iteration.
        self.deltaQMax = deltaQMax

        self.dQdV = dQdV

        self.dQdVSaved = dQdVSaved

        #: Fixed value of Y for type 7 load.
        self.yQFixed = yQFixed

        self._circuit = None
        self.circuit = circuit

        super(Generator, self).__init__(*args, **kw_args)

    def getcircuit(self):

        return self._circuit

    def setcircuit(self, value):
        if self._circuit is not None:
            filtered = [x for x in self.circuit.generators if x != self]
            self._circuit._generators = filtered

        self._circuit = value
        if self._circuit is not None:
            if self not in self._circuit._generators:
                self._circuit._generators.append(self)

    circuit = property(getcircuit, setcircuit)

