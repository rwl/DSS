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
import com.epri.dss.conversion.impl.GeneratorImpl;
import com.epri.dss.conversion.impl.ISourceImpl;
import com.epri.dss.conversion.impl.LoadImpl;
import com.epri.dss.conversion.impl.StorageImpl;
import com.epri.dss.conversion.impl.VSourceImpl;
import com.epri.dss.delivery.impl.CapacitorImpl;
import com.epri.dss.delivery.impl.FaultImpl;
import com.epri.dss.delivery.impl.FuseImpl;
import com.epri.dss.delivery.impl.LineImpl;
import com.epri.dss.delivery.impl.ReactorImpl;
import com.epri.dss.delivery.impl.TransformerImpl;
import com.epri.dss.general.DSSObject;
import com.epri.dss.general.impl.GrowthShapeImpl;
import com.epri.dss.general.impl.LineGeometryImpl;
import com.epri.dss.general.impl.LineSpacingImpl;
import com.epri.dss.general.impl.LoadShapeImpl;
import com.epri.dss.general.impl.SpectrumImpl;
import com.epri.dss.general.impl.TCC_CurveImpl;
import com.epri.dss.general.impl.WireDataImpl;
import com.epri.dss.general.impl.XfmrCodeImpl;
import com.epri.dss.meter.impl.EnergyMeterImpl;
import com.epri.dss.meter.impl.SensorImpl;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.HashListImpl;

public class DSSClassDefs {

	public static final int BASECLASSMASK = 0x00000007;
	public static final int CLASSMASK = 0xFFFFFFF8;

	/* Basic element types */
	public static final int NON_PCPD_ELEM = 1;  // A circuit Element we don't want enumerated in PD and PC Elements
	public static final int PD_ELEMENT    = 2;
	public static final int PC_ELEMENT    = 3;
	public static final int CTRL_ELEMENT  = 4;
	public static final int METER_ELEMENT = 5;

	/* Specific element Types */
	public static final int MON_ELEMENT  =  1 * 8;
	public static final int DSS_OBJECT   =  2 * 8;   // Just a general DSS object, accessible to all circuits
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
	
	private static int NumIntrinsicClasses;
	private static int NumUserClasses;
	
	public static int getNumIntrinsicClasses() {
		return NumIntrinsicClasses;
	}

	public static void setNumIntrinsicClasses(int numIntrinsicClasses) {
		NumIntrinsicClasses = numIntrinsicClasses;
	}

	public static int getNumUserClasses() {
		return NumUserClasses;
	}

	public static void setNumUserClasses(int numUserClasses) {
		NumUserClasses = numUserClasses;
	}

	public static void createDSSClasses() {
		DSSGlobals Globals = DSSGlobals.getInstance();
	
		Globals.setClassNames(new HashListImpl(25));   // Makes 5 sub lists
		Globals.setDSSClassList(new ArrayList<DSSClass>(10));  // 10 is initial size and increment
		DSSClass.DSSClasses = new DSSClassesImpl();  // class to handle junk for defining DSS classes

		/* General DSS objects, not circuit elements */
		Globals.setDSSObjs(new ArrayList<DSSObject>(25));  // 25 is initial size and increment

		/* Instantiate all intrinsic object classes */

		/* Generic Object classes first in case others refer to them */
		DSSClass.DSSClasses.add(new SolutionImpl());
		Globals.setSolutionClass(Globals.getActiveDSSClass());     // this is a special class
		DSSClass.DSSClasses.add(new LineCodeImpl());
		Globals.setLoadShapeClass(new LoadShapeImpl());
		DSSClass.DSSClasses.add(Globals.getLoadShapeClass());
		Globals.setGrowthShapeClass(new GrowthShapeImpl());
		DSSClass.DSSClasses.add(Globals.getGrowthShapeClass());
		Globals.setTCC_CurveClass(new TCC_CurveImpl());
		DSSClass.DSSClasses.add(Globals.getTCC_CurveClass());
		Globals.setSpectrumClass(new SpectrumImpl());
		DSSClass.DSSClasses.add(Globals.getSpectrumClass());
		Globals.setWireDataClass(new WireDataImpl());
		DSSClass.DSSClasses.add(Globals.getWireDataClass());
		DSSClass.DSSClasses.add(new LineGeometryImpl());
		Globals.setLineSpacingClass(new LineSpacingImpl());
		DSSClass.DSSClasses.add(Globals.getLineSpacingClass());
		DSSClass.DSSClasses.add(new XfmrCodeImpl());

		/* Circuit Element Classes */
		DSSClass.DSSClasses.add(new LineImpl());
		DSSClass.DSSClasses.add(new VSourceImpl());
		DSSClass.DSSClasses.add(new ISourceImpl());
		DSSClass.DSSClasses.add(new LoadImpl());
		DSSClass.DSSClasses.add(new TransformerImpl());
		DSSClass.DSSClasses.add(new RegControlImpl());
		DSSClass.DSSClasses.add(new CapacitorImpl());
		DSSClass.DSSClasses.add(new ReactorImpl());
		DSSClass.DSSClasses.add(new CapControlImpl());
		DSSClass.DSSClasses.add(new FaultImpl());
		DSSClass.DSSClasses.add(new GeneratorImpl());
		DSSClass.DSSClasses.add(new GenDispatcherImpl());
		Globals.setStorageClass(new StorageImpl());
		DSSClass.DSSClasses.add(Globals.getStorageClass());
		DSSClass.DSSClasses.add(new StorageControllerImpl());
		DSSClass.DSSClasses.add(new RelayImpl());
		DSSClass.DSSClasses.add(new RecloserImpl());
		DSSClass.DSSClasses.add(new FuseImpl());
		Globals.setFeederClass(new FeederImpl());
		DSSClass.DSSClasses.add(Globals.getFeederClass());
		DSSClass.DSSClasses.add(new SwtControlImpl());

		Globals.setMonitorClass(new DSSMonitorImpl());       // Have to do this after Generator
		DSSClass.DSSClasses.add(Globals.getMonitorClass());
		Globals.setEnergyMeterClass(new EnergyMeterImpl());  // Have to do this after Generator
		DSSClass.DSSClasses.add(Globals.getEnergyMeterClass());
		Globals.setSensorClass(new SensorImpl());      // Create state estimation sensors
		DSSClass.DSSClasses.add(Globals.getSensorClass());


		/* Create Classes for custom implementations */
		MyClassDefs.createMyDSSClasses();	

		NumIntrinsicClasses = Globals.getDSSClassList().size();
		NumUserClasses = 0;

		/* Add user-defined objects */

	}
	
	public static void disposeDSSClasses() {
		DSSObject DSSObj;
		String TraceName;
		String SuccessFree;

		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			SuccessFree = "First Object";
			for (int i = 0; i < Globals.getDSSObjs().size(); i++) {
				DSSObj    = Globals.getDSSObjs().get(i);
				TraceName = DSSObj.getParentClass().getName() + "." + DSSObj.getName();
				DSSObj = null;
				SuccessFree = TraceName;
			}
			TraceName = "(DSSObjs Class)";
			Globals.setDSSObjs(null);
		} catch (Exception e) {
			Globals.doSimpleMsg("Exception disposing of DSS Obj \""+TraceName+"\". "+DSSGlobals.CRLF+
					"Last Successful dispose was for object \"" + SuccessFree + "\" " +DSSGlobals.CRLF+
					e.getMessage(), 901);
		}

		try {
			for (int i = 0; i < Globals.getDSSClassList().size(); i++) 
				Globals.getDSSClassList().set(i, null);
			TraceName = "(DSS Class List)";
			Globals.setDSSClassList(null);
			TraceName = "(DSS Classes)";
			DSSClass.DSSClasses = null;
			TraceName = "(ClassNames)";
			Globals.setClassNames(null);
		} catch (Exception e) {
			Globals.doSimpleMsg("Exception disposing of DSS Class\""+TraceName+"\". "+DSSGlobals.CRLF + e.getMessage(), 902);
		}
	}
	
	public static void addUserClass() {
		throw new UnsupportedOperationException();
	}
	
	public static void loadUserClasses() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * set LastClassReferenced variable by class name.
	 */
	public static boolean setObjectClass(String ObjType) {
		
		DSSGlobals Globals = DSSGlobals.getInstance();
		
		int ClassRef = Globals.getClassNames().find(ObjType);

		switch (ClassRef) {
		case 0:
			Globals.doSimpleMsg("Error! Object Class \"" + ObjType + "\" not found."+ DSSGlobals.CRLF + Parser.getInstance().getCmdString(), 903);
			return false;
		default:
			Globals.setLastClassReferenced(ClassRef);
		}

		return true;
	}
	
	public static DSSClass getDSSClass(String ClassName) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		
		return Globals.getDSSClassList().get( Globals.getClassNames().find(ClassName.toLowerCase()) );
	}

}
