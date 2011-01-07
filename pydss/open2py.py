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

class open2py(object):

    header = """from pydss import *

executive = Executive()
"""

    _obj = ""
    _lbl = ""
    _ckt = ""
    _pydir = ""
    _dssdir = ""

    def __init__(self, dssfile, pydir):
        self._parse(dssfile, pydir)


    def _parse(self, dssfile, pydir):
        self._pydir = pydir
        self._dssdir = os.path.dirname(dssfile)
        s = ""

        if not os.path.exists(dssfile):
            root, _ = os.path.splitext(dssfile)
            dssfile = root + ".DSS" # try uppercase extension

        fname = os.path.basename(dssfile)
        logger.info("Converting OpenDSS file [%s]." % fname)

        fd = None
        try:
            fd = open(dssfile, "rb")
        except:
            logger.error("Error opening %s." % fname)
        finally:
            if fd is not None:
                s = self._parse_dssfile(fd)
                fd.close()


        pyfname, _ = os.path.splitext(fname)
        pyfile = os.path.join(pydir, pyfname + ".py")

        logger.info("Writing PyDSS file [%s]." % pyfname)

        pyfd = None
        try:
            pyfd = open(pyfile, "wb")
        except:
            logger.error("Error opening %s." % pyfname)
        finally:
            if pyfd is not None:
                pyfd.write(s)
                pyfd.close()


    def _parse_dssfile(self, fd):
        fd.seek(0)
        s = self.header
        for line in fd:
            words = line.split()

#            print "LN:", len(line), words

            if len(words) == 0:
                s += "\n"
            else:
                cmd = line.split()[0].lower()
                if line.startswith("!"):
                    s += "#" + line[1:]
                elif line.startswith("//"):
                    s += "#" + line[2:]
                elif cmd == "clear":
                    s += "del executive.circuits[:]"
                    s += "\n"
                elif cmd == "new":
                    s += self._new(line)
                    s += "\n"
                elif line.startswith("~"):
                    s += self._tilde(line)
                elif (cmd == "redirect") or (cmd == "compile"):
                    s += self._redirect(line)
                    s += "\n"
                else:
                    s += "## " + line

        return s


    def _new(self, line):
        parts = line.split()
        arg1 = parts[1].split("=")
        obj = arg1[-1]
        if "." not in obj:
            name = obj
            obj = self._obj
        else:
            obj, name = obj.split(".")

        obj = clsmap[obj.lower()]

        lbl = "%s%s" % (obj.lower(), name)

        if len(parts) > 2:
            kwargs = []
            for part in parts[2:]:
                if "=" in part:
                    key, value = part.split("=")
                    kwargs.append("%s=%s" % (key, self._quote(value)))
                else:
                    kwargs.append(self._quote(part))

            s = "%s = %s(name='%s', %s)" % (lbl, obj, name, ", ".join(kwargs))
        else:
            s = "%s = %s(name='%s')" % (lbl, obj, name)

        if obj.lower() == "circuit":
            self._ckt = self._lbl = lbl
            s += "\nexecutive.circuits.append(%s)" % (self._lbl)
        else:
            self._lbl = lbl
            s += "\n%s.add(%s)" % (self._ckt, self._lbl)

        return s


    def _tilde(self, line):
        s = ""
        cmt = ""
        parts = line[1:].split()
        c = 0
        for part in parts:

            print "PART:", part
            if "=" in part:
                key, value = part.split("=")
            else:
                value = part
                key = ""

            if value == "!":
                cmt = "# %s\n" % " ".join(parts[c + 1:])
                break

            key = key.replace("%", "pct")

            s += "%s.%s = %s\n" % (self._lbl, key, self._quote(value))
            c += 1

        ss = "%s%s" % (cmt, s)

        return ss


    def _quote(self, s):
        if s and s[0] not in num:
            return "'%s'" % s
        else:
            return s


    def _redirect(self, line):
        parts = line.split()
        dssfile = parts[1]

        if "/" not in dssfile: # relative path
            dssfile = os.path.join(self._dssdir, dssfile)

        self.header = ""
        self._parse(dssfile, self._pydir)

        return "execfile('%s')" % dssfile


num = ['0','1','2','3','4','5','6','7','8','9','.']

clsmap = {
    "circuit": "Circuit",
    "capacitorcontrol": "CapacitorControl",
    "generatordispatcher": "GeneratorDispatcher",
    "recloser": "Recloser",
    "regulatorcontrol": "RegulatorControl",
    "regcontrol": "RegulatorControl",
    "relay": "Relay",
    "storagecontroller": "StorageController",
    "switchcontrol": "SwitchControl",
    "currentsource": "CurrentSource",
    "equivalent": "Equivalent",
    "generator": "Generator",
    "load": "Load",
    "voltagesource": "VoltageSource",
    "storage": "Storage",
    "capacitor": "Capacitor",
    "fault": "Fault",
    "fuse": "Fuse",
    "line": "Line",
    "reactor": "Reactor",
    "transformer": "Transformer",
    "growthshape": "GrowthShape",
    "linecode": "LineCode",
    "linegeometry": "LineGeometry",
    "linespacing": "LineSpacing",
    "loadshape": "LoadShape",
    "spectrum": "Spectrum",
    "timecurrentcurve": "TimeCurrentCurve",
    "wiredata": "WireData",
    "transformercode": "TransformerCode",
    "energymeter": "EnergyMeter",
    "monitor": "Monitor",
    "sensor": "Sensor"
}


def main(args):
    if len(args) != 2:
        sys.exit(1)
    else:
        dssfile = args[0]
        pydir = args[1]

    open2py(dssfile, pydir)


if __name__ == "__main__":
    #main(sys.argv)
    logging.basicConfig(level=logging.INFO)

    open2py(
        "/home/rwl/tmp/OpenDSS/IEEETestCases/13Bus/IEEE13Nodeckt.dss",
        "/home/rwl/java/jacobi/Electrickery/example/13Bus"
    )
