# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

""" Defines test cases.
"""

import unittest
from cim.core import BaseVoltage, Terminal
from conversion import VSource, ISource, Generator, Load
from circuit import Circuit

class SourceTestCase(unittest.TestCase):
    """ Defines a test case for the voltage and current sources.
    """
    circuit = None

    def setUp(self):
        """ The test runner will execute this method prior to each test.
        """
        self.circuit = circuit = Circuit()

        v_base = BaseVoltage(nominal_voltage=12.47)
        circuit.add(v_base, uid="v_base1")

        t1 = Terminal()

        v_source = VSource(phases="ABCN", base_voltage=v_base)
        v_source.terminals.append(t1)
        circuit.add(v_source, uid="v_source1")


    def test_voltage_source(self):
        """ Test operation of a voltage source.
        """
        v_source = self.circuit.elements["v_source1"]
        v_source.recalc_element_data()
        y_prim = v_source.calc_y_prim()


    def test_current_source(self):
        """ Test operation of a current source.
        """
        v_base = self.circuit.elements["v_base1"]
        i_source = ISource(base_voltage=v_base)
        i_source.terminals.append(Terminal())
        self.circuit.add(i_source, uid="i_source1")

        i_source.calc_y_prim()


class GeneratorTestCase(unittest.TestCase):
    """ Defines a test case for Generator.
    """
    circuit = None

    def setUp(self):
        """ The test runner will execute this method prior to each test.
        """
        self.circuit = circuit = Circuit()

        v_base = BaseVoltage(nominal_voltage=12.47)
        circuit.add(v_base, uid="v_base1")

        generator = Generator(base_voltage=v_base)
        self.circuit.add(generator, uid="generator1")


    def test_connection(self):
        """ Test the number of conductors.
        """
        generator = self.circuit.elements["generator1"]
        generator.interpret_connection("wye")


    def test_nominal_generation(self):
        """ Test setting the nominal generation.
        """
        generator = self.circuit.elements["generator1"]
        generator.set_nominal_generation()


    def test_y_prim(self):
        """ Test calculation of the primitive admittance matrix.
        """
        generator = self.circuit.elements["generator1"]
        generator.recalc_element_data()
        generator.calc_y_prim()


if __name__ == "__main__":
    unittest.main()
