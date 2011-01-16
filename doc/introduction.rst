Introduction
------------

The Distribution System Simulator (DSS) is a comprehensive electrical system
simulation tool for electric utility distribution systems.

The program supports all rms steady-state (i.e., frequency domain) analyses
commonly performed for utility distribution systems.  In addition, it supports
many new types of analyses that are designed to meet future needs. Many of the
features found in the program were originally intended to support distributed
generation analysis needs. Other features support energy efficiency analysis
of power delivery and harmonics analysis.

The OpenDSS program has been used for:
 * Distribution Planning and Analysis
 * General Multi-phase AC Circuit Analysis
 * Analysis of Distributed Generation Interconnections
 * Annual Load and Generation Simulations
 * Wind Plant Simulations
 * Analysis of Unusual Transformer Configurations
 * Harmonics and Interharmonics analysis
 * Neutral-to-earth Voltage Simulations
 * Development of IEEE Test feeder cases
 * And more...

The program has several built-in solution modes, including:
 * Snapshot Power Flow
 * Daily Power Flow
 * Yearly Power Flow
 * Harmonics
 * Dynamics
 * Faultstudy
 * And others...

The DSS is a general-purpose frequency-domain simulation engine that has
special features for creating models of electric power distribution systems
and performing many types of analysis related to distribution planning and
power quality. It does not perform electromagnetic transients (time domain)
simulations; all types of analysis are currently in the frequency domain
(i.e., sinusoidal steady state, but not limited to 60 Hz).

Each element of the system is represented by a "primitive" nodal admittance
(Y) matrix. This is generally straightforward, although it can be tricky for
some power system elements such as transformers. Each primitive Y is then
coalesced into one large system Y matrix, and the system of equations
representing the distribution system is solved using sparse matrix solvers.
One trick is that nonlinear behaviors of some devices (e.g., some load models)
are modeled by current source injections, which some refer to as "compensation"
currents. That is, the current predicted from the linear portion of the model
that resides in the system Y matrix is compensated by an external injection to
iteratively obtain the correct current. This is a common technique for
representing loads in distribution system analysis tools. One advantage of this
method is that it allows for quite flexible load models, which is important for
performing some types of analysis such as done in energy efficiency studies.

The program’s heritage is closer to a harmonic flow analysis program, or even
a dynamics program, than a typical power flow program. This may seem a strange
place to start designing a tool that will be used a great deal for power flow
studies, but it gives the tool great modeling flexibility particularly for
accommodating all sorts of load models and unusual circuit configurations.

