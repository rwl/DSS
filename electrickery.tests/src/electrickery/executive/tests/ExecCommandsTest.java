/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.executive.tests;

import electrickery.executive.ExecCommands;
import electrickery.executive.ExecutiveFactory;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Exec Commands</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link electrickery.executive.ExecCommands#create() <em>Create</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#edit() <em>Edit</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#more() <em>More</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#select(java.lang.String, int) <em>Select</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#save(org.eclipse.emf.ecore.EClass, java.lang.String) <em>Save</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#show(java.lang.String) <em>Show</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#solve() <em>Solve</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#enable() <em>Enable</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#disable() <em>Disable</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#plot() <em>Plot</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#reset(electrickery.executive.resetType) <em>Reset</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#compile(java.lang.String) <em>Compile</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#setValue() <em>Set Value</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#dump(org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EObject, boolean) <em>Dump</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#open(org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EClass, int) <em>Open</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#close(org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EClass, int) <em>Close</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#redirect(java.lang.String) <em>Redirect</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#help() <em>Help</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#quit() <em>Quit</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#what(org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EStructuralFeature) <em>What</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#next(electrickery.executive.nextType) <em>Next</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#panel() <em>Panel</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#sample() <em>Sample</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#clear() <em>Clear</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#about() <em>About</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#calcVoltageBases() <em>Calc Voltage Bases</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#setKvBase(org.eclipse.emf.ecore.EClass, float, float) <em>Set Kv Base</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#buildY() <em>Build Y</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#getValue() <em>Get Value</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#initialise() <em>Initialise</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#export(electrickery.executive.exportType, java.lang.String) <em>Export</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#fileEdit(java.lang.String) <em>File Edit</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#voltages() <em>Voltages</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#currents() <em>Currents</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#powers() <em>Powers</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#seqVoltages() <em>Seq Voltages</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#seqCurrents() <em>Seq Currents</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#seqPower() <em>Seq Power</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#losses() <em>Losses</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#phaseLosses() <em>Phase Losses</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#cktLosses() <em>Ckt Losses</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#allocateLoads() <em>Allocate Loads</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#formEdit(org.eclipse.emf.ecore.EObject) <em>Form Edit</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#totals() <em>Totals</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#capacity() <em>Capacity</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#classes() <em>Classes</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#userClasses() <em>User Classes</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#Zsc() <em>Zsc</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#Zsc10() <em>Zsc10</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#ZscRefresh() <em>Zsc Refresh</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#Ysc() <em>Ysc</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#puVoltages() <em>Pu Voltages</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#varValues() <em>Var Values</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#varNames() <em>Var Names</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#busCoords(java.lang.String) <em>Bus Coords</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#makeBusList() <em>Make Bus List</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#makePosSequence() <em>Make Pos Sequence</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#reduce(electrickery.executive.reduceType) <em>Reduce</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#interpolate() <em>Interpolate</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#alignFile(java.lang.String) <em>Align File</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#top() <em>Top</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#rotate() <em>Rotate</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#vDiff() <em>VDiff</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#summary() <em>Summary</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#distribute(electrickery.executive.distributionType) <em>Distribute</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#diPlot() <em>Di Plot</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#compareCases() <em>Compare Cases</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#yearlyCurves() <em>Yearly Curves</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#visualise() <em>Visualise</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#closeDI() <em>Close DI</em>}</li>
 *   <li>{@link electrickery.executive.ExecCommands#estimate() <em>Estimate</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ExecCommandsTest extends TestCase {

	/**
	 * The fixture for this Exec Commands test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExecCommands fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ExecCommandsTest.class);
	}

	/**
	 * Constructs a new Exec Commands test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecCommandsTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Exec Commands test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(ExecCommands fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Exec Commands test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExecCommands getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ExecutiveFactory.eINSTANCE.createExecCommands());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#create() <em>Create</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#create()
	 * @generated
	 */
	public void testCreate() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#edit() <em>Edit</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#edit()
	 * @generated
	 */
	public void testEdit() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#more() <em>More</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#more()
	 * @generated
	 */
	public void testMore() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#select(java.lang.String, int) <em>Select</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#select(java.lang.String, int)
	 * @generated
	 */
	public void testSelect__String_int() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#save(org.eclipse.emf.ecore.EClass, java.lang.String) <em>Save</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#save(org.eclipse.emf.ecore.EClass, java.lang.String)
	 * @generated
	 */
	public void testSave__EClass_String() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#show(java.lang.String) <em>Show</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#show(java.lang.String)
	 * @generated
	 */
	public void testShow__String() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#solve() <em>Solve</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#solve()
	 * @generated
	 */
	public void testSolve() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#enable() <em>Enable</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#enable()
	 * @generated
	 */
	public void testEnable() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#disable() <em>Disable</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#disable()
	 * @generated
	 */
	public void testDisable() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#plot() <em>Plot</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#plot()
	 * @generated
	 */
	public void testPlot() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#reset(electrickery.executive.resetType) <em>Reset</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#reset(electrickery.executive.resetType)
	 * @generated
	 */
	public void testReset__resetType() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#compile(java.lang.String) <em>Compile</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#compile(java.lang.String)
	 * @generated
	 */
	public void testCompile__String() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#setValue() <em>Set Value</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#setValue()
	 * @generated
	 */
	public void testSetValue() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#dump(org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EObject, boolean) <em>Dump</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#dump(org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EObject, boolean)
	 * @generated
	 */
	public void testDump__EClass_EObject_boolean() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#open(org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EClass, int) <em>Open</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#open(org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EClass, int)
	 * @generated
	 */
	public void testOpen__EClass_EClass_int() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#close(org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EClass, int) <em>Close</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#close(org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EClass, int)
	 * @generated
	 */
	public void testClose__EClass_EClass_int() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#redirect(java.lang.String) <em>Redirect</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#redirect(java.lang.String)
	 * @generated
	 */
	public void testRedirect__String() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#help() <em>Help</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#help()
	 * @generated
	 */
	public void testHelp() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#quit() <em>Quit</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#quit()
	 * @generated
	 */
	public void testQuit() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#what(org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EStructuralFeature) <em>What</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#what(org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EStructuralFeature)
	 * @generated
	 */
	public void testWhat__EClass_EStructuralFeature() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#next(electrickery.executive.nextType) <em>Next</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#next(electrickery.executive.nextType)
	 * @generated
	 */
	public void testNext__nextType() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#panel() <em>Panel</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#panel()
	 * @generated
	 */
	public void testPanel() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#sample() <em>Sample</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#sample()
	 * @generated
	 */
	public void testSample() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#clear() <em>Clear</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#clear()
	 * @generated
	 */
	public void testClear() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#about() <em>About</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#about()
	 * @generated
	 */
	public void testAbout() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#calcVoltageBases() <em>Calc Voltage Bases</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#calcVoltageBases()
	 * @generated
	 */
	public void testCalcVoltageBases() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#setKvBase(org.eclipse.emf.ecore.EClass, float, float) <em>Set Kv Base</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#setKvBase(org.eclipse.emf.ecore.EClass, float, float)
	 * @generated
	 */
	public void testSetKvBase__EClass_float_float() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#buildY() <em>Build Y</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#buildY()
	 * @generated
	 */
	public void testBuildY() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#getValue() <em>Get Value</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#getValue()
	 * @generated
	 */
	public void testGetValue() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#initialise() <em>Initialise</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#initialise()
	 * @generated
	 */
	public void testInitialise() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#export(electrickery.executive.exportType, java.lang.String) <em>Export</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#export(electrickery.executive.exportType, java.lang.String)
	 * @generated
	 */
	public void testExport__exportType_String() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#fileEdit(java.lang.String) <em>File Edit</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#fileEdit(java.lang.String)
	 * @generated
	 */
	public void testFileEdit__String() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#voltages() <em>Voltages</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#voltages()
	 * @generated
	 */
	public void testVoltages() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#currents() <em>Currents</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#currents()
	 * @generated
	 */
	public void testCurrents() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#powers() <em>Powers</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#powers()
	 * @generated
	 */
	public void testPowers() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#seqVoltages() <em>Seq Voltages</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#seqVoltages()
	 * @generated
	 */
	public void testSeqVoltages() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#seqCurrents() <em>Seq Currents</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#seqCurrents()
	 * @generated
	 */
	public void testSeqCurrents() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#seqPower() <em>Seq Power</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#seqPower()
	 * @generated
	 */
	public void testSeqPower() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#losses() <em>Losses</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#losses()
	 * @generated
	 */
	public void testLosses() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#phaseLosses() <em>Phase Losses</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#phaseLosses()
	 * @generated
	 */
	public void testPhaseLosses() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#cktLosses() <em>Ckt Losses</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#cktLosses()
	 * @generated
	 */
	public void testCktLosses() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#allocateLoads() <em>Allocate Loads</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#allocateLoads()
	 * @generated
	 */
	public void testAllocateLoads() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#formEdit(org.eclipse.emf.ecore.EObject) <em>Form Edit</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#formEdit(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public void testFormEdit__EObject() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#totals() <em>Totals</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#totals()
	 * @generated
	 */
	public void testTotals() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#capacity() <em>Capacity</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#capacity()
	 * @generated
	 */
	public void testCapacity() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#classes() <em>Classes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#classes()
	 * @generated
	 */
	public void testClasses() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#userClasses() <em>User Classes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#userClasses()
	 * @generated
	 */
	public void testUserClasses() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#Zsc() <em>Zsc</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#Zsc()
	 * @generated
	 */
	public void testZsc() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#Zsc10() <em>Zsc10</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#Zsc10()
	 * @generated
	 */
	public void testZsc10() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#ZscRefresh() <em>Zsc Refresh</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#ZscRefresh()
	 * @generated
	 */
	public void testZscRefresh() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#Ysc() <em>Ysc</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#Ysc()
	 * @generated
	 */
	public void testYsc() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#puVoltages() <em>Pu Voltages</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#puVoltages()
	 * @generated
	 */
	public void testPuVoltages() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#varValues() <em>Var Values</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#varValues()
	 * @generated
	 */
	public void testVarValues() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#varNames() <em>Var Names</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#varNames()
	 * @generated
	 */
	public void testVarNames() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#busCoords(java.lang.String) <em>Bus Coords</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#busCoords(java.lang.String)
	 * @generated
	 */
	public void testBusCoords__String() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#makeBusList() <em>Make Bus List</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#makeBusList()
	 * @generated
	 */
	public void testMakeBusList() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#makePosSequence() <em>Make Pos Sequence</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#makePosSequence()
	 * @generated
	 */
	public void testMakePosSequence() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#reduce(electrickery.executive.reduceType) <em>Reduce</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#reduce(electrickery.executive.reduceType)
	 * @generated
	 */
	public void testReduce__reduceType() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#interpolate() <em>Interpolate</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#interpolate()
	 * @generated
	 */
	public void testInterpolate() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#alignFile(java.lang.String) <em>Align File</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#alignFile(java.lang.String)
	 * @generated
	 */
	public void testAlignFile__String() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#top() <em>Top</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#top()
	 * @generated
	 */
	public void testTop() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#rotate() <em>Rotate</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#rotate()
	 * @generated
	 */
	public void testRotate() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#vDiff() <em>VDiff</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#vDiff()
	 * @generated
	 */
	public void testVDiff() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#summary() <em>Summary</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#summary()
	 * @generated
	 */
	public void testSummary() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#distribute(electrickery.executive.distributionType) <em>Distribute</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#distribute(electrickery.executive.distributionType)
	 * @generated
	 */
	public void testDistribute__distributionType() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#diPlot() <em>Di Plot</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#diPlot()
	 * @generated
	 */
	public void testDiPlot() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#compareCases() <em>Compare Cases</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#compareCases()
	 * @generated
	 */
	public void testCompareCases() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#yearlyCurves() <em>Yearly Curves</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#yearlyCurves()
	 * @generated
	 */
	public void testYearlyCurves() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#visualise() <em>Visualise</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#visualise()
	 * @generated
	 */
	public void testVisualise() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#closeDI() <em>Close DI</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#closeDI()
	 * @generated
	 */
	public void testCloseDI() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link electrickery.executive.ExecCommands#estimate() <em>Estimate</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.ExecCommands#estimate()
	 * @generated
	 */
	public void testEstimate() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

} //ExecCommandsTest
