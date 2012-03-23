Distribution System Simulator
=============================

The DSS is a comprehensive electrical system simulation tool for electric
utility distribution systems.  DSS is designed to support most types of
power distribution planning analysis associated with the interconnection
of distributed generation (DG) to utility systems.  It also supports many
other types of frequency-domain circuit simulations commonly performed on
utility electric power distribution systems.  It represents unbalanced
conditions, stochastic processes, and other aspects of electrical power
distribution systems and equipment in far greater detail than many other
tools, including commercial products.

Getting Started
---------------

The DSS shell interface can be downloaded as an archive from here:

 * https://github.com/rwl/DSS/downloads

Unpack the archive and execute either the batch file or the shell script,
dependng on your system. Type `help` at the dss prompt for a description
of each the available commands. Type `help &lt;command&gt;` for a more
detailed description of an individual command.

Build and Install
-----------------

The latest DSS source code may be obtained by cloning the
[GitHub](https://github.com/rwl/DSS) repository:

> $ git clone git://github.com/rwl/DSS.git

To build an archive of the DSS shell interface change into the source
directory and run [Apache Maven](http://maven.apache.org/):

> $ cd DSS/  
> $ mvn package

The archive is created in the `bootstrap/target/` directory. To install
the DSS modules to your local Maven repository run:

> $ mvn clean install

Project files for [Eclipse](http://eclipse.org/) can be generated with
the command:

> mvn eclipse:eclipse

The projects may then be imported into Eclipse via
File->Import...->"Existing Projects into Workspace". To use
[m2e](http://www.eclipse.org/m2e/), right-click on a project in the
Package Explorer view and select Configure->"Convert to Maven project".

License and Copyright
---------------------

Copyright (C) 2008-2012 Electric Power Research Institute, Inc.  
Copyright (C) 2009-2012 Richard Lincoln
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the Electric Power Research Institute, Inc.,
      Richard Lincoln, nor the names of its contributors may be used to
      endorse or promote products derived from this software without specific
      prior written permission.

> THIS SOFTWARE IS PROVIDED BY Electric Power Research Institute, Inc. and
Richard Lincoln, "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL Electric Power
Research Institute, Inc. or Richard Lincoln, BE LIABLE FOR ANY DIRECT,
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

[![Build Status](https://secure.travis-ci.org/rwl/DSS.png)](http://travis-ci.org/rwl/DSS)

