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

class NewCommandTest(unittest.TestCase):

    def setUp(self):
        self.executive = Executive()


    def testSimple(self):
        cmd = "new object=circuit.cktA"

        self.executive.command = cmd

        self.assertEqual(len(self.executive.circuits), 1)
        self.assertEqual(self.executive.activeCircuit.name, "cktA")


    def testPositional(self):
        cmd = "new circuit.cktA 6.0"

        self.executive.command = cmd

        self.assertEqual(len(self.executive.circuits), 1)
        ckt = self.executive.activeCircuit
        self.assertEqual(ckt.name, "cktA")
#        self.assertEqual(ckt.generatorDispatchReference, 6.0)


#    def testNamed(self):
#        cmd = "new circuit.cktA loadMultiplier=1.1 genMultiplier=0.9"
#
#        self.executive.command = cmd
#
#        self.assertEqual(len(self.executive.circuits), 1)
#        self.assertEqual(self.executive.activeCircuit.name, "cktA")
#        self.assertEqual(self.executive.activeCircuit.loadMultiplier, 1.1)
#        self.assertEqual(self.executive.activeCircuit.genMultiplier, 0.9)


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
