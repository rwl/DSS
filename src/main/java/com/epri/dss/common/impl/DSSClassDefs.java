package com.epri.dss.common.impl;

import java.util.ArrayList;

import com.epri.dss.common.DSSClass;
import com.epri.dss.control.impl.CapControlImpl;
import com.epri.dss.control.impl.GenDispatcherImpl;
import com.epri.dss.control.impl.RecloserImpl;
import com.epri.dss.control.impl.RegControlImpl;
import com.epri.dss.control.impl.RelayImpl;
import com.epri.dss.control.impl.StorageControllerImpl;
import com.epri.dss.control.impl.SwtControlImpl;
import com.epri.dss.conversion.impl.GICLineImpl;
import com.epri.dss.conversion.impl.GeneratorImpl;
import com.epri.dss.conversion.impl.ISourceImpl;
import com.epri.dss.conversion.impl.LoadImpl;
import com.epri.dss.conversion.impl.PVSystemImpl;
import com.epri.dss.conversion.impl.StorageImpl;
import com.epri.dss.conversion.impl.VSourceImpl;
import com.epri.dss.delivery.impl.CapacitorImpl;
import com.epri.dss.delivery.impl.FaultImpl;
import com.epri.dss.delivery.impl.FuseImpl;
import com.epri.dss.delivery.impl.GICTransformerImpl;
import com.epri.dss.delivery.impl.LineImpl;
import com.epri.dss.delivery.impl.ReactorImpl;
import com.epri.dss.delivery.impl.TransformerImpl;
import com.epri.dss.general.DSSObject;
import com.epri.dss.general.impl.CNDataImpl;
import com.epri.dss.general.impl.GrowthShapeImpl;
import com.epri.dss.general.impl.LineCodeImpl;
import com.epri.dss.general.impl.LineGeometryImpl;
import com.epri.dss.general.impl.LineSpacingImpl;
import com.epri.dss.general.impl.LoadShapeImpl;
import com.epri.dss.general.impl.PriceShapeImpl;
import com.epri.dss.general.impl.SpectrumImpl;
import com.epri.dss.general.impl.TCC_CurveImpl;
import com.epri.dss.general.impl.TSDataImpl;
import com.epri.dss.general.impl.TShapeImpl;
import com.epri.dss.general.impl.WireDataImpl;
import com.epri.dss.general.impl.XYCurveImpl;
import com.epri.dss.general.impl.XfmrCodeImpl;
import com.epri.dss.meter.impl.EnergyMeterImpl;
import com.epri.dss.meter.impl.MonitorImpl;
import com.epri.dss.meter.impl.SensorImpl;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.HashListImpl;

public class DSSClassDefs {

	public static final int BASECLASSMASK = 0x00000007;
	public static final int CLASSMASK = 0xFFFFFFF8;

	/* Basic element types */
	public static final int NON_PCPD_ELEM = 1;  // a circuit element we don't want enumerated in PD and PC Elements
	public static final int PD_ELEMENT    = 2;
	public static final int PC_ELEMENT    = 3;
	public static final int CTRL_ELEMENT  = 4;
	public static final int METER_ELEMENT = 5;
	public static final int HIDDEN_ELEMENT= 6;

	/* Specific element types */
	public static final int MON_ELEMENT  =  1 * 8;
	public static final int DSS_OBJECT   =  2 * 8;  // just a general DSS object, accessible to all circuits
	public static final int SOURCE       =  3 * 8;
	public static final int XFMR_ELEMENT =  4 * 8;
	public static final int SUBSTATION   =  5 * 8;  // not used
	public static final int LINE_ELEMENT =  6 * 8;
	public static final int LOAD_ELEMENT =  7 * 8;
	public static final int FAULTOBJECT  =  8 * 8;
	public static final int ENERGY_METER =  9 * 8;
	public static final int GEN_ELEMENT  = 10 * 8;
	public static final int CAP_CONTROL  = 11 * 8;
	public static final int REG_CONTROL  = 12 * 8;
	public static final int CAP_ELEMENT  = 13 * 8;
	public static final int RELAY_CONTROL = 14 * 8;
	public static final int RECLOSER_CONTROL = 15 * 8;
	public static final int FUSE_CONTROL     = 16 * 8;
	public static final int REACTOR_ELEMENT  = 17 * 8;
	public static final int FEEDER_ELEMENT   = 18 * 8;
	public static final int GEN_CONTROL      = 19 * 8;
	public static final int SENSOR_ELEMENT   = 20 * 8;
	public static final int STORAGE_ELEMENT  = 21 * 8;
	public static final int STORAGE_CONTROL  = 22 * 8;
	public static final int SWT_CONTROL      = 23 * 8;
	public static final int PVSYSTEM_ELEMENT = 24 * 8;
	public static final int GIC_TRANSFORMER  = 25 * 8;  // special models for GIC studies
	public static final int GIC_LINE         = 26 * 8;


	private static int numIntrinsicClasses;
	private static int numUserClasses;

	public static int getNumIntrinsicClasses() {
		return numIntrinsicClasses;
	}

	public static void setNumIntrinsicClasses(int num) {
		numIntrinsicClasses = num;
	}

	public static int getNumUserClasses() {
		return numUserClasses;
	}

	public static void setNumUserClasses(int num) {
		numUserClasses = num;
	}

	public static void createDSSClasses() {
		DSSGlobals globals = DSSGlobals.getInstance();

		globals.setClassNames(new HashListImpl(25));  // makes 5 sub lists
		globals.setDSSClassList(new ArrayList<DSSClass>(10));  // 10 is initial size and increment
		DSSClassImpl.setDSSClasses(new DSSClassesImpl());  // class to handle defining DSS classes

		/* General DSS objects, not circuit elements */
		globals.setDSSObjs(new ArrayList<DSSObject>(25));  // 25 is initial size and increment

		/* Instantiate all intrinsic object classes */

		/* Generic object classes first in case others refer to them */
		DSSClassImpl.getDSSClasses().setNew( new SolutionImpl() );
		globals.setSolutionClass(globals.getActiveDSSClass());  // this is a special class
		DSSClassImpl.getDSSClasses().setNew( new LineCodeImpl() );
		globals.setLoadShapeClass(new LoadShapeImpl());
		DSSClassImpl.getDSSClasses().setNew( globals.getLoadShapeClass() );

		globals.setTShapeClass( new TShapeImpl() );
		DSSClassImpl.getDSSClasses().setNew( globals.getTShapeClass() );
		globals.setPriceShapeClass( new PriceShapeImpl() );
		DSSClassImpl.getDSSClasses().setNew( globals.getPriceShapeClass() );
		globals.setXYCurveClass( new XYCurveImpl() );
		DSSClassImpl.getDSSClasses().setNew( globals.getXYCurveClass() );

		globals.setGrowthShapeClass(new GrowthShapeImpl());
		DSSClassImpl.getDSSClasses().setNew( globals.getGrowthShapeClass() );
		globals.setTCC_CurveClass(new TCC_CurveImpl());
		DSSClassImpl.getDSSClasses().setNew( globals.getTCC_CurveClass() );
		globals.setSpectrumClass(new SpectrumImpl());
		DSSClassImpl.getDSSClasses().setNew( globals.getSpectrumClass() );
		globals.setWireDataClass(new WireDataImpl());
		DSSClassImpl.getDSSClasses().setNew( globals.getWireDataClass() );

		globals.setCNDataClass( new CNDataImpl() );
		DSSClassImpl.getDSSClasses().setNew( globals.getCNDataClass() );
		globals.setTSDataClass( new TSDataImpl() );
		DSSClassImpl.getDSSClasses().setNew( globals.getTSDataClass() );

		DSSClassImpl.getDSSClasses().setNew( new LineGeometryImpl() );
		globals.setLineSpacingClass(new LineSpacingImpl());
		DSSClassImpl.getDSSClasses().setNew( globals.getLineSpacingClass() );
		DSSClassImpl.getDSSClasses().setNew( new XfmrCodeImpl() );

		/* Circuit element classes */
		DSSClassImpl.getDSSClasses().setNew( new LineImpl() );
		DSSClassImpl.getDSSClasses().setNew( new VSourceImpl() );
		DSSClassImpl.getDSSClasses().setNew( new ISourceImpl() );
		DSSClassImpl.getDSSClasses().setNew( new LoadImpl() );
		DSSClassImpl.getDSSClasses().setNew( new TransformerImpl() );
		DSSClassImpl.getDSSClasses().setNew( new RegControlImpl() );
		DSSClassImpl.getDSSClasses().setNew( new CapacitorImpl() );
		DSSClassImpl.getDSSClasses().setNew( new ReactorImpl() );
		DSSClassImpl.getDSSClasses().setNew( new CapControlImpl() );
		DSSClassImpl.getDSSClasses().setNew( new FaultImpl() );
		DSSClassImpl.getDSSClasses().setNew( new GeneratorImpl() );
		DSSClassImpl.getDSSClasses().setNew( new GenDispatcherImpl() );
		globals.setStorageClass(new StorageImpl());
		DSSClassImpl.getDSSClasses().setNew( globals.getStorageClass() );
		DSSClassImpl.getDSSClasses().setNew( new StorageControllerImpl() );
		DSSClassImpl.getDSSClasses().setNew( new RelayImpl() );
		DSSClassImpl.getDSSClasses().setNew( new RecloserImpl() );
		DSSClassImpl.getDSSClasses().setNew( new FuseImpl() );
//		Globals.setFeederClass(new FeederImpl());
//		DSSClassImpl.getDSSClasses().setNew( Globals.getFeederClass() );
		DSSClassImpl.getDSSClasses().setNew( new SwtControlImpl() );
		globals.setPVSystemClass( new PVSystemImpl() );
		DSSClassImpl.getDSSClasses().setNew( globals.getPVSystemClass() );

		globals.setMonitorClass(new MonitorImpl() );       // Have to do this after Generator
		DSSClassImpl.getDSSClasses().setNew( globals.getMonitorClass() );
		globals.setEnergyMeterClass(new EnergyMeterImpl());  // Have to do this after Generator
		DSSClassImpl.getDSSClasses().setNew( globals.getEnergyMeterClass() );
		globals.setSensorClass(new SensorImpl());      // Create state estimation sensors
		DSSClassImpl.getDSSClasses().setNew( globals.getSensorClass() );

		DSSClassImpl.getDSSClasses().setNew( new GICTransformerImpl() );
		DSSClassImpl.getDSSClasses().setNew( new GICLineImpl() );


		/* Create classes for custom implementations */
//		MyClassDefs.createMyDSSClasses();

		numIntrinsicClasses = globals.getDSSClassList().size();
		numUserClasses = 0;

		/* Add user-defined objects */

	}

	public static void disposeDSSClasses() {
		DSSObject DSSObj;
		String traceName = "";
		String successFree = "";

		DSSGlobals globals = DSSGlobals.getInstance();

		try {
			successFree = "First Object";
			for (int i = 0; i < globals.getDSSObjs().size(); i++) {
				DSSObj    = globals.getDSSObjs().get(i);
				traceName = DSSObj.getParentClass().getName() + "." + DSSObj.getName();
				DSSObj = null;
				successFree = traceName;
			}
			traceName = "(DSSObjs Class)";
			globals.setDSSObjs(null);
		} catch (Exception e) {
			globals.doSimpleMsg("Exception disposing of DSS obj \""+traceName+"\". "+DSSGlobals.CRLF+
					"Last successful dispose was for object \"" + successFree + "\" " +DSSGlobals.CRLF+
					e.getMessage(), 901);
		}

		try {
			for (int i = 0; i < globals.getDSSClassList().size(); i++)
				globals.getDSSClassList().set(i, null);
			traceName = "(DSS Class List)";
			globals.setDSSClassList(null);
			traceName = "(DSS Classes)";
			DSSClassImpl.setDSSClasses(null);
			traceName = "(ClassNames)";
			globals.setClassNames(null);
		} catch (Exception e) {
			globals.doSimpleMsg("Exception disposing of DSS class\""+traceName+"\". "+DSSGlobals.CRLF + e.getMessage(), 902);
		}
	}

	public static void addUserClass() {
		throw new UnsupportedOperationException();
	}

	public static void loadUserClasses() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Set lastClassReferenced variable by class name.
	 */
	public static boolean setObjectClass(String objType) {
		DSSGlobals globals = DSSGlobals.getInstance();

		int ClassRef = globals.getClassNames().find(objType);

		switch (ClassRef) {
		case 0:
			globals.doSimpleMsg("Error: Object class \"" + objType + "\" not found."+ DSSGlobals.CRLF + Parser.getInstance().getCmdString(), 903);
			return false;
		default:
			globals.setLastClassReferenced(ClassRef);
			break;
		}

		return true;
	}

	public static DSSClass getDSSClass(String className) {
		DSSGlobals globals = DSSGlobals.getInstance();

		return globals.getDSSClassList().get( globals.getClassNames().find(className.toLowerCase()) );
	}

}
