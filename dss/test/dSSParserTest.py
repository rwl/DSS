# Copyright (C) 2010 Richard Lincoln <r.w.lincoln@gmail.com>
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

import unittest

from dss.executive.Executive import Executive

class DSSParserTest(unittest.TestCase):

    def setUp(self):
        self.executive = Executive()


    def testNewCommand(self):
        cmd = "new object=circuit.cktA loadMultiplier=1.1"
        cmd = "new circuit.cktA foo loadMultiplier=1.1 genMultiplier=0.9"
        self.executive.command = cmd
#        self.assertEqual(len(self.executive.circuits), 1)


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
