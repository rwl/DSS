from pydss import *

executive = Executive()
del executive.circuits[:]

#
# This script is based on a script developed by Tennessee Tech Univ students
# Tyler Patton, Jon Wood, and David Woods, April 2009
#

circuitIEEE13Nodeckt = Circuit(name='IEEE13Nodeckt')
executive.circuits.append(circuitIEEE13Nodeckt)
circuitIEEE13Nodeckt.basekv = 115
circuitIEEE13Nodeckt.pu = 1.00
circuitIEEE13Nodeckt.phases = 3
circuitIEEE13Nodeckt.bus1 = 'SourceBus'
# advance angle 30 deg so result agree with published angle
circuitIEEE13Nodeckt.Angle = 30
# stiffen the source to approximate inf source
circuitIEEE13Nodeckt.MVAsc3 = 20000
circuitIEEE13Nodeckt.MVASC1 = 21000



#SUB TRANSFORMER DEFINITION 
# Although this data was given, it does not appear to be used in the test case results
# The published test case starts at 1.0 per unit at Bus 650. To make this happen, we will change the impedance
# on the transformer to something tiny by dividing by 1000 using the DSS in-line RPN math
transformerSub = Transformer(name='Sub', Phases=3, Windings=2, XHL='(8', 1000, '/)')
circuitIEEE13Nodeckt.add(transformerSub)
transformerSub.wdg = 1
transformerSub.bus = 'SourceBus'
transformerSub.conn = 'delta'
transformerSub.kv = 115
transformerSub.kva = 5000
transformerSub.pctr = '(.5'
transformerSub. = 1000
transformerSub. = '/)'
transformerSub.XHT = 4
transformerSub.wdg = 2
transformerSub.bus = 650
transformerSub.conn = 'wye'
transformerSub.kv = 4.16
transformerSub.kva = 5000
transformerSub.pctr = '(.5'
transformerSub. = 1000
transformerSub. = '/)'
transformerSub.XLT = 4

# FEEDER 1-PHASE VOLTAGE REGULATORS
# Define low-impedance 2-wdg transformer

transformerReg1 = Transformer(name='Reg1', phases=1, XHL=0.01, kVAs='[1666', 1666])
circuitIEEE13Nodeckt.add(transformerReg1)
transformerReg1.Buses = '[650.1'
transformerReg1. = 'RG60.1]'
transformerReg1.kVs = '[2.4'
transformerReg1. = 2.4]
transformerReg1.pctLoadLoss = 0.01
regulatorcontrolReg1 = RegulatorControl(name='Reg1', transformer='Reg1', winding=2, vreg=122, band=2, ptratio=20, ctprim=700, R=3, X=9)
circuitIEEE13Nodeckt.add(regulatorcontrolReg1)

transformerReg2 = Transformer(name='Reg2', phases=1, XHL=0.01, kVAs='[1666', 1666])
circuitIEEE13Nodeckt.add(transformerReg2)
transformerReg2.Buses = '[650.2'
transformerReg2. = 'RG60.2]'
transformerReg2.kVs = '[2.4'
transformerReg2. = 2.4]
transformerReg2.pctLoadLoss = 0.01
regulatorcontrolReg2 = RegulatorControl(name='Reg2', transformer='Reg2', winding=2, vreg=122, band=2, ptratio=20, ctprim=700, R=3, X=9)
circuitIEEE13Nodeckt.add(regulatorcontrolReg2)

transformerReg3 = Transformer(name='Reg3', phases=1, XHL=0.01, kVAs='[1666', 1666])
circuitIEEE13Nodeckt.add(transformerReg3)
transformerReg3.Buses = '[650.3'
transformerReg3. = 'RG60.3]'
transformerReg3.kVs = '[2.4'
transformerReg3. = 2.4]
transformerReg3.pctLoadLoss = 0.01
regulatorcontrolReg3 = RegulatorControl(name='Reg3', transformer='Reg3', winding=2, vreg=122, band=2, ptratio=20, ctprim=700, R=3, X=9)
circuitIEEE13Nodeckt.add(regulatorcontrolReg3)


#TRANSFORMER DEFINITION 
transformerXFM1 = Transformer(name='XFM1', Phases=3, Windings=2, XHL=2)
circuitIEEE13Nodeckt.add(transformerXFM1)
transformerXFM1.wdg = 1
transformerXFM1.bus = 633
transformerXFM1.conn = 'Wye'
transformerXFM1.kv = 4.16
transformerXFM1.kva = 500
transformerXFM1.pctr = .55
transformerXFM1.XHT = 1
transformerXFM1.wdg = 2
transformerXFM1.bus = 634
transformerXFM1.conn = 'Wye'
transformerXFM1.kv = 0.480
transformerXFM1.kva = 500
transformerXFM1.pctr = .55
transformerXFM1.XLT = 1


#LINE CODES
execfile('/home/rwl/tmp/OpenDSS/IEEETestCases/13Bus/IEEELineCodes.dss')
# these are local matrix line codes
linecodemtx601 = LineCode(name='mtx601', nphases=3, BaseFreq=60)
circuitIEEE13Nodeckt.add(linecodemtx601)
linecodemtx601. = 'rmatrix'
linecodemtx601. = 
linecodemtx601. = '(0.3465'
linecodemtx601. = '|'
linecodemtx601. = 0.1535
linecodemtx601. = 0.3375
linecodemtx601. = '|'
linecodemtx601. = 0.1580
linecodemtx601. = 0.1560
linecodemtx601. = 0.3414
linecodemtx601. = ')'
linecodemtx601. = 'xmatrix'
linecodemtx601. = 
linecodemtx601. = '(1.0179'
linecodemtx601. = '|'
linecodemtx601. = 0.3849
linecodemtx601. = 1.0478
linecodemtx601. = '|'
linecodemtx601. = 0.4236
linecodemtx601. = 0.5017
linecodemtx601. = 1.0348
linecodemtx601. = ')'
linecodemtx601.units = 'mi'
linecodemtx602 = LineCode(name='mtx602', nphases=3, BaseFreq=60)
circuitIEEE13Nodeckt.add(linecodemtx602)
linecodemtx602. = 'rmatrix'
linecodemtx602. = 
linecodemtx602. = '(0.7526'
linecodemtx602. = '|'
linecodemtx602. = 0.1535
linecodemtx602. = 0.7475
linecodemtx602. = '|'
linecodemtx602. = 0.1560
linecodemtx602. = 0.1580
linecodemtx602. = 0.7436
linecodemtx602. = ')'
linecodemtx602. = 'xmatrix'
linecodemtx602. = 
linecodemtx602. = '(1.1814'
linecodemtx602. = '|'
linecodemtx602. = 0.3849
linecodemtx602. = 1.1983
linecodemtx602. = '|'
linecodemtx602. = 0.5017
linecodemtx602. = 0.4236
linecodemtx602. = 1.2112
linecodemtx602. = ')'
linecodemtx602.units = 'mi'
linecodemtx603 = LineCode(name='mtx603', nphases=2, BaseFreq=60)
circuitIEEE13Nodeckt.add(linecodemtx603)
linecodemtx603. = 'rmatrix'
linecodemtx603. = 
linecodemtx603. = '(1.3238'
linecodemtx603. = '|'
linecodemtx603. = 0.2066
linecodemtx603. = 1.3294
linecodemtx603. = ')'
linecodemtx603. = 'xmatrix'
linecodemtx603. = 
linecodemtx603. = '(1.3569'
linecodemtx603. = '|'
linecodemtx603. = 0.4591
linecodemtx603. = 1.3471
linecodemtx603. = ')'
linecodemtx603.units = 'mi'
linecodemtx604 = LineCode(name='mtx604', nphases=2, BaseFreq=60)
circuitIEEE13Nodeckt.add(linecodemtx604)
linecodemtx604. = 'rmatrix'
linecodemtx604. = 
linecodemtx604. = '(1.3238'
linecodemtx604. = '|'
linecodemtx604. = 0.2066
linecodemtx604. = 1.3294
linecodemtx604. = ')'
linecodemtx604. = 'xmatrix'
linecodemtx604. = 
linecodemtx604. = '(1.3569'
linecodemtx604. = '|'
linecodemtx604. = 0.4591
linecodemtx604. = 1.3471
linecodemtx604. = ')'
linecodemtx604.units = 'mi'
linecodemtx605 = LineCode(name='mtx605', nphases=1, BaseFreq=60)
circuitIEEE13Nodeckt.add(linecodemtx605)
linecodemtx605. = 'rmatrix'
linecodemtx605. = 
linecodemtx605. = '(1.3292'
linecodemtx605. = ')'
linecodemtx605. = 'xmatrix'
linecodemtx605. = 
linecodemtx605. = '(1.3475'
linecodemtx605. = ')'
linecodemtx605.units = 'mi'
linecodemtx606 = LineCode(name='mtx606', nphases=3, BaseFreq=60)
circuitIEEE13Nodeckt.add(linecodemtx606)
linecodemtx606. = 'rmatrix'
linecodemtx606. = 
linecodemtx606. = '(0.7982'
linecodemtx606. = '|'
linecodemtx606. = 0.3192
linecodemtx606. = 0.7891
linecodemtx606. = '|'
linecodemtx606. = 0.2849
linecodemtx606. = 0.3192
linecodemtx606. = 0.7982
linecodemtx606. = ')'
linecodemtx606. = 'xmatrix'
linecodemtx606. = 
linecodemtx606. = '(0.4463'
linecodemtx606. = '|'
linecodemtx606. = 0.0328
linecodemtx606. = 0.4041
linecodemtx606. = '|'
linecodemtx606. = '-0.0143'
linecodemtx606. = 0.0328
linecodemtx606. = 0.4463
linecodemtx606. = ')'
linecodemtx606.units = 'mi'
linecodemtx607 = LineCode(name='mtx607', nphases=1, BaseFreq=60)
circuitIEEE13Nodeckt.add(linecodemtx607)
linecodemtx607. = 'rmatrix'
linecodemtx607. = 
linecodemtx607. = '(1.3425'
linecodemtx607. = ')'
linecodemtx607. = 'xmatrix'
linecodemtx607. = 
linecodemtx607. = '(0.5124'
linecodemtx607. = ')'
linecodemtx607.units = 'mi'


#LOAD DEFINITIONS 
load671 = Load(name='671', Bus1=671.1.2.3, Phases=3, Conn='Delta', Model=1, kV=4.16, kW=1155, kvar=660)
circuitIEEE13Nodeckt.add(load671)
load634a = Load(name='634a', Bus1=634.1, Phases=1, Conn='Wye', Model=1, kV=0.277, kW=160, kvar=110)
circuitIEEE13Nodeckt.add(load634a)
load634b = Load(name='634b', Bus1=634.2, Phases=1, Conn='Wye', Model=1, kV=0.277, kW=120, kvar=90)
circuitIEEE13Nodeckt.add(load634b)
load634c = Load(name='634c', Bus1=634.3, Phases=1, Conn='Wye', Model=1, kV=0.277, kW=120, kvar=90)
circuitIEEE13Nodeckt.add(load634c)
load645 = Load(name='645', Bus1=645.2, Phases=1, Conn='Wye', Model=1, kV=2.4, kW=170, kvar=125)
circuitIEEE13Nodeckt.add(load645)
load646 = Load(name='646', Bus1=646.2.3, Phases=1, Conn='Delta', Model=2, kV=4.16, kW=230, kvar=132)
circuitIEEE13Nodeckt.add(load646)
load692 = Load(name='692', Bus1=692.3.1, Phases=1, Conn='Delta', Model=5, kV=4.16, kW=170, kvar=151)
circuitIEEE13Nodeckt.add(load692)
load675a = Load(name='675a', Bus1=675.1, Phases=1, Conn='Wye', Model=1, kV=2.4, kW=485, kvar=190)
circuitIEEE13Nodeckt.add(load675a)
load675b = Load(name='675b', Bus1=675.2, Phases=1, Conn='Wye', Model=1, kV=2.4, kW=68, kvar=60)
circuitIEEE13Nodeckt.add(load675b)
load675c = Load(name='675c', Bus1=675.3, Phases=1, Conn='Wye', Model=1, kV=2.4, kW=290, kvar=212)
circuitIEEE13Nodeckt.add(load675c)
load611 = Load(name='611', Bus1=611.3, Phases=1, Conn='Wye', Model=5, kV=2.4, kW=170, kvar=80)
circuitIEEE13Nodeckt.add(load611)
load652 = Load(name='652', Bus1=652.1, Phases=1, Conn='Wye', Model=2, kV=2.4, kW=128, kvar=86)
circuitIEEE13Nodeckt.add(load652)
load670a = Load(name='670a', Bus1=670.1, Phases=1, Conn='Wye', Model=1, kV=2.4, kW=17, kvar=10)
circuitIEEE13Nodeckt.add(load670a)
load670b = Load(name='670b', Bus1=670.2, Phases=1, Conn='Wye', Model=1, kV=2.4, kW=66, kvar=38)
circuitIEEE13Nodeckt.add(load670b)
load670c = Load(name='670c', Bus1=670.3, Phases=1, Conn='Wye', Model=1, kV=2.4, kW=117, kvar=68)
circuitIEEE13Nodeckt.add(load670c)

#CAPACITOR DEFINITIONS
capacitorCap1 = Capacitor(name='Cap1', Bus1=675, phases=3, kVAR=600, kV=4.16)
circuitIEEE13Nodeckt.add(capacitorCap1)
capacitorCap2 = Capacitor(name='Cap2', Bus1=611.3, phases=1, kVAR=100, kV=2.4)
circuitIEEE13Nodeckt.add(capacitorCap2)

#Bus 670 is the concentrated point load of the distributed load on line 632 to 671 located at 1/3 the distance from node 632

#LINE DEFINITIONS 
line650632 = Line(name='650632', Phases=3, Bus1='RG60.1.2.3', Bus2=632.1.2.3, LineCode='mtx601', Length=2000, units='ft')
circuitIEEE13Nodeckt.add(line650632)
line632670 = Line(name='632670', Phases=3, Bus1=632.1.2.3, Bus2=670.1.2.3, LineCode='mtx601', Length=667, units='ft')
circuitIEEE13Nodeckt.add(line632670)
line670671 = Line(name='670671', Phases=3, Bus1=670.1.2.3, Bus2=671.1.2.3, LineCode='mtx601', Length=1333, units='ft')
circuitIEEE13Nodeckt.add(line670671)
line671680 = Line(name='671680', Phases=3, Bus1=671.1.2.3, Bus2=680.1.2.3, LineCode='mtx601', Length=1000, units='ft')
circuitIEEE13Nodeckt.add(line671680)
line632633 = Line(name='632633', Phases=3, Bus1=632.1.2.3, Bus2=633.1.2.3, LineCode='mtx602', Length=500, units='ft')
circuitIEEE13Nodeckt.add(line632633)
line632645 = Line(name='632645', Phases=2, Bus1=632.3.2, Bus2=645.3.2, LineCode='mtx603', Length=500, units='ft')
circuitIEEE13Nodeckt.add(line632645)
line645646 = Line(name='645646', Phases=2, Bus1=645.3.2, Bus2=646.3.2, LineCode='mtx603', Length=300, units='ft')
circuitIEEE13Nodeckt.add(line645646)
line692675 = Line(name='692675', Phases=3, Bus1=692.1.2.3, Bus2=675.1.2.3, LineCode='mtx606', Length=500, units='ft')
circuitIEEE13Nodeckt.add(line692675)
line671684 = Line(name='671684', Phases=2, Bus1=671.1.3, Bus2=684.1.3, LineCode='mtx604', Length=300, units='ft')
circuitIEEE13Nodeckt.add(line671684)
line684611 = Line(name='684611', Phases=1, Bus1=684.3, Bus2=611.3, LineCode='mtx605', Length=300, units='ft')
circuitIEEE13Nodeckt.add(line684611)
line684652 = Line(name='684652', Phases=1, Bus1=684.1, Bus2=652.1, LineCode='mtx607', Length=800, units='ft')
circuitIEEE13Nodeckt.add(line684652)


#SWITCH DEFINITIONS 
line671692 = Line(name='671692', Phases=3, Bus1=671, Bus2=692, Switch='y', r1=1e-4, r0=1e-4, x1=0.000, x0=0.000, c1=0.000, c0=0.000)
circuitIEEE13Nodeckt.add(line671692)

## Set Voltagebases=[115, 4.16, .48]
## calcv
## Solve
## BusCoords IEEE13Node_BusXY.csv

#---------------------------------------------------------------------------------------------------------------------------------------------------
#----------------Show some Results -----------------------------------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------------------------------------------------


# Show Voltages LN Nodes
# Show Currents Elem
# Show Powers kVA Elem
# Show Losses
# Show Taps

#---------------------------------------------------------------------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------------------------------------------------
# Alternate Solution Script
# To force the taps to be same as published results, set the transformer taps manually and disable the controls
#---------------------------------------------------------------------------------------------------------------------------------------------------

## Transformer.Reg1.Taps=[1.0 1.0625]
## Transformer.Reg2.Taps=[1.0 1.0500]
## Transformer.Reg3.Taps=[1.0 1.06875]
## Set Controlmode=OFF

## Solve