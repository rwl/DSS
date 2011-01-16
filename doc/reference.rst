Reference Guide
---------------

Installation
============

Summary of Simulation Capabilities
==================================

The present version of the DSS is capable of performing the following
analyses/simulations:

Power Flow
++++++++++

While the power flow problem is probably the most common problem solved with
the program, the DSS is not best characterized as a power flow program.
Its heritage is from general-purpose power system harmonics analysis tools.
Thus, it functions differently than most power flow tools. This heritage also
gives it some unique and powerful capabilities. The program was originally
designed to perform nearly all aspects of distribution planning for distributed
generation (DG), which includes harmonics analysis. It is relatively easy to
make a harmonics analysis program solve a power flow, while it is quite
difficult to make a power flow program perform harmonics analysis. To learn
more about how the algorithm works for the power flow problem, see
"Putting It All Together" below.

The DSS is designed to perform a basic distribution-style power flow in which
the bulk power system is the dominant source of energy. However, it differs
from the traditional radial circuit solvers in that it solves networked
(meshed) systems as easily as radial systems.  It is intended to be used for
distribution companies that may also have transmission or subtransmission
systems. Therefore, it can also be used to solve small- to medium-sized
networks with a transmission-style power flow.

The circuit model employed can be either a full multi-phase model or a
positive-sequence model.  The default is a full multi-phase model. For capacity
studies, the latter is sufficient and will execute much faster. Due to the
complex multi-phase models that may be created with myriad unbalances, the user
is presently required to create positive-sequence models outside the DSS by
defining a single-phase model of the circuit.  However, by setting the proper
flag, all power reports will report 3-phase quantities.

The power flow executes in numerous solution modes including the standard
single Snapshot mode, Daily mode, Dutycycle Mode, Monte Carlo mode, and several
modes where the load varies as a function of time. The time can be any
arbitrary time period.  Commonly, for planning purposes it will be a 24-hour
day, a month, or a year.

When a power flow is completed, the losses, voltages, flows, and other
information are available for the total system, each component, and certain
defined areas.  For each instant in time, the losses are reported as kW losses,
for example. Energy meter models may be used to integrate the power over a time
interval.

The power flow can be computed for both radial distribution (MV) circuits and
network (meshed) systems.  While the accuracy of some algorithms, such as the
calculation of expected unserved energy, may depend on part of the circuit
model being radial, the power flow solution is general.  It works best on
systems that have at least one stiff source.

The two basic power flow solution types are:

 1 Iterative power flow
 2 Direct solution

For the iterative power flow, loads and distributed generators are treated as
injection sources.  In the Direct solution, they are included as admittances in
the system admittance matrix, which is then solved directly without iterating. 
Either of these two types of solutions may be used for any of the several
solution modes by setting the global LoadModel property to "Admittance" or
"Powerflow".

There are two iterative power flow algorithms currently employed:

 1 "Normal" current injection mode
 2 "Newton" mode

The Normal mode is faster, but the Newton mode is more robust for circuit that
are difficult to solve.  The default is Normal. The Normal mode method is a
simple fixed-point iterative method and works for well for nearly all
distribution systems with a stiff bulk power source. It is the preferred method
for yearly simulations due to its speed.

Typically, power flow calculations will use an iterative solution with
non-linear load models, and fault studies will use a direct solution with
linear load models.


Fault Studies
+++++++++++++

The DSS will perform fault (short-circuit) studies in several ways:

 * A conventional fault study for all buses ("Set Mode=Faultstudy"), reporting
   currents and voltages on all phases for all types of faults:  All-phase
   fault, SLG faults on each phase, L-L  and L-L-G faults.  Since transformers
   will be represented in actual winding configuration, this is an excellent
   circuit model debugging tool as well as a tool for setting relays and sizing
   fuses.

 * A single snapshot fault.  User places a fault on the system at a particular
   bus, defining the type of fault and the value of the fault resistance. A
   fault is a circuit element just like any other and can be manipulated the
   same way.

 * Applying faults randomly. (Monte Carlo fault study mode  -- solution
   mode= "MF"). User defines Fault objects at locations where faults are
   desired. This is useful for such analyses as examining what voltages are
   observed at a DG site for various faults on the utility system.


Harmonic Flow Analysis
++++++++++++++++++++++

The DSS is a general-purpose frequency domain circuit solver. Therefore,
harmonic flow analysis is quite natural and one of the easiest things to do
with the program. The user defines various harmonic spectra to represent
harmonic sources of interest. The spectra are connected to Load, Generator,
voltage source, and current source objects as desired. There are reasonable
default spectra for these elements.

A snapshot power flow is first performed to initialize the problem. The
solution must converge before proceeding. If convergence is difficult to
achieve, a direct solution is generally sufficient to initialize the harmonic
solution. Harmonic sources are then initialized to appropriate magnitudes and
phases angles to match the solution.

Once a solution is achieved, the user simply issues the command::

  Solve mode=harmonics

The DSS then solves for each frequency presently defined in any of the circuit
elements. Monitors are placed around the circuit to capture the results.

Frequency sweeps are performed similarly. The user defines spectra containing
values for the frequencies (expressed as harmonics of the fundamental) of
interest and assigns them to appropriate voltage or current sources. These
sources may be defined to perform the sweeps in three different ways:

 1 Positive Sequence: Phasors in 3-phase sources maintain a positive sequence
   relationship at all frequencies. That is, all three voltages and currents
   are equal in magnitude and displaced by 120 degrees.
 2 Zero Sequence: All three voltages or currents are equal in magnitude and in
   phase.
 3 No sequence: Phasors are initialized with the power flow solution and are
   permitted to rotate independently with frequency. If they are in a positive
   sequence relationship at fundamental frequency, they will be in a negative
   sequence relationship at the 2nd harmonic, and a zero sequence relationship
   at the 3rd harmonic, etc. In between integer harmonics, the phasors will be
   somewhere in between.


Dynamics
++++++++

The DSS can perform basic machine dynamics simulations. The original intent was
to provide sufficient modeling capability to evaluate DG interconnections. The
built-in Generator model has a simple single-mass model that is adequate for
many DG studies for common distribution system fault conditions.

An induction machine model was developed and used to help develop the IEEE Test
Feeder benchmark dealing with large induction generation on distribution
systems.


Load Parametric Variation
+++++++++++++++++++++++++

The capabilities for doing parametric evaluation are provided for a variety of
variables.  Certain variables will be allowed to vary according to a function
(e.g., load growth) or vary randomly for Monte Carlo and statistical studies. 
See the Set Mode command documentation for descriptions of the Monte Carlo
solution modes.


Usage
=====

Communication to the DSS is fundamentally accomplished through text strings
passed to the DSS command processor.


Overall Circuit Model Concept
=============================

The DSS consists of a model of the electrical system in the rms steady state,
overlaid with a communications network that interconnects controls on power
delivery elements and on power conversion elements.


Bus and Terminal Models
=======================

Bus Definition
++++++++++++++

A bus is a circuit element having [1..N] nodes. Buses are the connection point
for all other circuit elements.

The main electrical property of a Bus is voltage.  Each node has a voltage with
respect to the zero voltage reference (remote ground).  There is a nodal
admittance equation written for every node (i.e., the current is summed at each
node).

Terminal Definition
+++++++++++++++++++

Each electrical element in the power system has one or more terminals. Each
terminal has one or more conductors.  Each conductor conceptually contains a
disconnect switch and a TCC (fuse) curve. The conductors are numbered
[1, 2, 3, ...].

If the terminal is connected to an N-phase device, the first N conductors are
assumed to correspond to the phases, in order.  The remaining conductors may be
neutrals or other non-power conductors.

The DSS bus is a connecting place with 1 or more nodes for connecting
individual phases and other conductors from the terminals of both power
delivery elements and power conversion elements.

Buses are named with null-terminated strings of arbitrary length.

Node 0 of each bus is implicitly connected to the voltage reference (i.e., the
node's voltage is always zero and is never explicitly included in the Y
matrix).
