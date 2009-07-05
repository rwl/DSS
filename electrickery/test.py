# Copyright (C) 2009 Richard W. Lincoln
# All rights reserved.

""" Defines test cases.
"""

import unittest
import pickle
from cim14.iec61970.core import BaseVoltage, Terminal, ConductingEquipment
from cim14.iec61970.wires import Switch, RatioTapChanger, \
    TransformerWinding, PowerTransformer
from conversion import VSource, ISource, Generator, Load
from circuit import Circuit
from reader import read_cim
from cim2dot import cim2dot

#INSTANCE_FILE = "./data/EDF_AIGUE_v1.xml"
#INSTANCE_FILE = "./data/EDF_RURAL_4PS_V1.xml"
INSTANCE_FILE = "./data/10_Bus.xml"

#NS_CIM = "http://iec.ch/TC57/2009/CIM-schema-cim14#"
from cim14 import ns_uri as NS_CIM
PICKLE_FILE = "./data/cdpsm.pkl"


class CIMTestCase(unittest.TestCase):
    """ Defines a test case for the Common Information Model.
    """

    def setUp(self):
        """ The test runner will execute this method prior to each test.
        """
        pass
    
    
    def test_attributes(self):
        """ Test assignment of attribute values.
        """
        bv = BaseVoltage()
        sw = Switch()
        
        # Test default value of a 'java.lang.String'.
        self.assertEqual(type(bv.name), str)
        # Test default value of a 'double'.
        self.assertEqual(type(bv.nominal_voltage), float)
        # Test default value of a 'int'.
        self.assertEqual(type(sw.switch_on_count), int)
        # Test default value of a 'boolean'.
        self.assertEqual(type(sw.normal_open), bool)
        # Test default value of a 'java.util.Date'.
        self.assertEqual(type(sw.switch_on_date), str)
        
        phases = ["abn", "bc", "acn", "bn", "ac", "abc", "an", "ab", "c", "b",
            "abcn", "a", "cn", "n", "bcn"]
        
        ce = ConductingEquipment(equivalent=True)
        
        # Test setting super class attribute in constructor.
        self.assertTrue(ce.equivalent)
        # Test enumeration value.
        self.assertTrue(ce.phases in phases)
        
        # Ensure instance attributes not class attributes.
        ce2 = ConductingEquipment()
        self.assertFalse(ce2.equivalent)
        

    def test_one_to_one_associations(self):
        """ Test one-to-one associations between model elements.
        """
        tw = TransformerWinding()
        tc = RatioTapChanger(transformer_winding=tw)
        
        self.assertEqual(tw.ratio_tap_changer, tc)
        
        tc2 = RatioTapChanger()
        tc2.transformer_winding = tw
        
        self.assertEqual(tw.ratio_tap_changer, tc2)
        

    def test_one_to_many_associations(self):
        """ Test one-to-many associations between model elements.
        """
        pt1 = PowerTransformer()
        tw1 = TransformerWinding(power_transformer=pt1)
        tw2 = TransformerWinding()
        tw2.power_transformer = pt1
        
        self.assertTrue(tw1.power_transformer == pt1)
        self.assertTrue(tw1 in pt1.transformer_windings)
        self.assertTrue(tw2 in pt1.transformer_windings)
        
        # Test moving.
        pt2 = PowerTransformer()
        tw1.power_transformer = None
        tw2.power_transformer = pt2
        
        self.assertTrue(tw1 not in pt1.transformer_windings)
        self.assertTrue(tw2 not in pt1.transformer_windings)
        self.assertTrue(tw2 in pt2.transformer_windings)
        
        # Ensure instance attributes not class attributes.
        pt3 = PowerTransformer()
        self.assertTrue(tw1 not in pt3.transformer_windings)
        self.assertTrue(tw2 not in pt3.transformer_windings)
        
        # Test singleton reference.
        tw1.power_transformer = pt2
        tw1.set_power_transformer(pt2)
        tw1s = [tw for tw in pt2.transformer_windings if tw == tw1]
        self.assertTrue(len(tw1s) == 1)
        

    def test_many_to_one_associations(self):
        """ Test many-to-one associations between model elements.
        """
        tw1 = TransformerWinding()
        tw2 = TransformerWinding()
        pt1 = PowerTransformer(transformer_windings=[tw1])
        pt1.add_transformer_windings(tw2)
        
        # Ensure instance attributes not class attributes.
        pt2 = PowerTransformer()
        self.assertTrue(tw1 not in pt2.transformer_windings)
        self.assertTrue(tw2 not in pt2.transformer_windings)
        
        # Test properties.
        self.assertTrue(tw1 in pt1.transformer_windings)
        self.assertTrue(tw2 in pt1.transformer_windings)
        self.assertTrue(tw1.power_transformer == pt1)
        self.assertTrue(tw2.power_transformer == pt1)
        
        # Test removal.
        pt1.remove_transformer_windings(tw1)
        self.assertTrue(tw1 not in pt1.transformer_windings)
        self.assertTrue(tw1.power_transformer == None)
        
        # Test setting.
        tw3 = TransformerWinding()
        pt2.transformer_windings = [tw1, tw2, tw3]
        
        self.assertTrue(tw1 in pt2.transformer_windings)
        self.assertTrue(tw2 in pt2.transformer_windings)
        self.assertTrue(tw3 in pt2.transformer_windings)
        self.assertTrue(tw2 not in pt1.transformer_windings)
        self.assertTrue(tw1.power_transformer == pt2)
        self.assertTrue(tw2.power_transformer == pt2)
        self.assertTrue(tw3.power_transformer == pt2)
        


#class CircuitTestCase(unittest.TestCase):
#    """ Defines a test case for the circuit.
#    """
#    circuit = None
#
#    def setUp(self):
#        """ The test runner will execute this method prior to each test.
#        """
#        circuit = Circuit()
#        circuit.elements = read_cim(INSTANCE_FILE, NS_CIM)
#        fd = open(PICKLE_FILE, "wb")
#        pickle.dump(circuit, fd)
#        fd.close()
#
##        try:
##            fd = open(PICKLE_FILE, "rb")
##            self.circuit = pickle.load(fd)
##        finally:
##            fd.close()
#
#        self.circuit = circuit
#
#
#    def test_topological_analysis(self):
#        """ Test topological analysis.
#        """
#        circuit = self.circuit
#        circuit.perform_topological_analysis()
#
#
#    def test_dot(self):
#        """ Test Dot language representation.
#        """
#        dot = cim2dot(self.circuit)
#        print dot


#class SourceTestCase(unittest.TestCase):
#    """ Defines a test case for the voltage and current sources.
#    """
#    circuit = None
#
#    def setUp(self):
#        """ The test runner will execute this method prior to each test.
#        """
#        self.circuit = circuit = Circuit()
#
#        v_base = BaseVoltage(nominal_voltage=12.47)
#        circuit.add(v_base, uid="v_base1")
#
#        t1 = Terminal()
#
#        v_source = VSource(phases="ABCN", base_voltage=v_base)
#        v_source.terminals.append(t1)
#        circuit.add(v_source, uid="v_source1")
#
#
#    def test_voltage_source(self):
#        """ Test operation of a voltage source.
#        """
#        v_source = self.circuit.elements["v_source1"]
#        v_source.recalc_element_data()
#        y_prim = v_source.calc_y_prim()
#
#
#    def test_current_source(self):
#        """ Test operation of a current source.
#        """
#        v_base = self.circuit.elements["v_base1"]
#        i_source = ISource(base_voltage=v_base)
#        i_source.terminals.append(Terminal())
#        self.circuit.add(i_source, uid="i_source1")
#
#        i_source.calc_y_prim()
#
#
#class GeneratorTestCase(unittest.TestCase):
#    """ Defines a test case for Generator.
#    """
#    circuit = None
#
#    def setUp(self):
#        """ The test runner will execute this method prior to each test.
#        """
#        self.circuit = circuit = Circuit()
#
#        v_base = BaseVoltage(nominal_voltage=12.47)
#        circuit.add(v_base, uid="v_base1")
#
#        generator = Generator(base_voltage=v_base)
#        self.circuit.add(generator, uid="generator1")
#
#
#    def test_connection(self):
#        """ Test the number of conductors.
#        """
#        generator = self.circuit.elements["generator1"]
#        generator.interpret_connection("wye")
#
#
#    def test_nominal_generation(self):
#        """ Test setting the nominal generation.
#        """
#        generator = self.circuit.elements["generator1"]
#        generator.set_nominal_generation()
#
#
#    def test_y_prim(self):
#        """ Test calculation of the primitive admittance matrix.
#        """
#        generator = self.circuit.elements["generator1"]
#        generator.recalc_element_data()
#        generator.calc_y_prim()


if __name__ == "__main__":
    unittest.main()
