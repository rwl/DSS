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

import sys
import os
import logging

logger = logging.getLogger(__name__)

HEADER = """from pydss.common import *
from pydss.control import *
from pydss.conversion import *
from pydss.delivery import *
from pydss.executive import *
from pydss.general import *
from pydss.meter import *

executive = Executive()
"""

ACTIVE_OBJ = ""

def open2py(dssfile):
    if isinstance(dssfile, basestring):
        fname = os.path.basename(dssfile)
        logger.info("Converting OpenDSS file [%s]." % fname)

        fd = None
        try:
            fd = open(dssfile, "rb")
        except:
            logger.error("Error opening %s." % fname)
            return None
        finally:
            if fd is not None:
                s = _parse_dssfile(fd)
                fd.close()
    else:
        s = _parse_dssfile(dssfile)

    return s


def _parse_dssfile(fd):
    fd.seek(0)
    s = HEADER
    for line in fd:
#        print "LN:", len(line), line
        if len(line) == 2: # \n
            continue
        cmd = line.split()[0].lower()
        if line.startswith("!"):
            s += "#" + line[1:]
        elif line.startswith("//"):
            s += "#" + line[2:]
        elif cmd == "clear":
            s += "del executive.circuits[:]"
            s += "\n"
        elif cmd == "new":
            s += _new(line)
            s += "\n"
        else:
            s += "## " + line

    return s


def _new(line):
    parts = line.split()
    arg1 = parts[1].split("=")
    obj = arg1[-1]
    if "." not in obj:
        name = obj
        obj = ACTIVE_OBJ
    else:
        obj, name = obj.split(".")

    label = "%s%s" % (obj.lower(), name)
    s = "%s = %s(name='%s')" % (label, obj, name)

    return s


def main(args):

    if len(args) != 1:
        sys.exit(1)
    else:
        dssfile = args[0]

    open2py(dssfile)


if __name__ == "__main__":
    #main(sys.argv)
    logging.basicConfig(level=logging.INFO)
    print open2py("/home/rwl/tmp/OpenDSS/IEEETestCases/13Bus/IEEE13Nodeckt.dss")
