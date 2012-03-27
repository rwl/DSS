/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.common;

import java.util.ArrayList;

import com.ncond.dss.control.CapControl;
import com.ncond.dss.control.GenDispatcher;
import com.ncond.dss.control.Recloser;
import com.ncond.dss.control.RegControl;
import com.ncond.dss.control.Relay;
import com.ncond.dss.control.StorageController;
import com.ncond.dss.control.SwtControl;
import com.ncond.dss.control.VVControl;
import com.ncond.dss.conversion.Generator;
import com.ncond.dss.conversion.ISource;
import com.ncond.dss.conversion.Load;
import com.ncond.dss.conversion.PVSystem;
import com.ncond.dss.conversion.Storage;
import com.ncond.dss.conversion.VSource;
import com.ncond.dss.delivery.Capacitor;
import com.ncond.dss.delivery.Fault;
import com.ncond.dss.delivery.Fuse;
import com.ncond.dss.delivery.Line;
import com.ncond.dss.delivery.Reactor;
import com.ncond.dss.delivery.Transformer;
import com.ncond.dss.general.CNData;
import com.ncond.dss.general.DSSObject;
import com.ncond.dss.general.GrowthShape;
import com.ncond.dss.general.LineCode;
import com.ncond.dss.general.LineGeometry;
import com.ncond.dss.general.LineSpacing;
import com.ncond.dss.general.LoadShape;
import com.ncond.dss.general.PriceShape;
import com.ncond.dss.general.Spectrum;
import com.ncond.dss.general.TCC_Curve;
import com.ncond.dss.general.TSData;
import com.ncond.dss.general.TempShape;
import com.ncond.dss.general.WireData;
import com.ncond.dss.general.XYCurve;
import com.ncond.dss.general.XfmrCode;
import com.ncond.dss.meter.EnergyMeter;
import com.ncond.dss.meter.Monitor;
import com.ncond.dss.meter.Sensor;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.HashList;

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
	public static final int RELAY_CONTROL    = 14 * 8;
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
	public static final int VV_CONTROL       = 25 * 8;


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

		DSS.classNames = new HashList(25);  // makes 5 sub lists
		DSS.DSSClassList = new ArrayList<DSSClass>(10);  // 10 is initial size and increment
		DSSClass.DSSClasses = new DSSClasses();  // class to handle defining DSS classes

		/* General DSS objects, not circuit elements */
		DSS.DSSObjs = new ArrayList<DSSObject>(25);  // 25 is initial size and increment

		/* Instantiate all intrinsic object classes */

		/* Generic object classes first in case others refer to them */
		DSSClass.DSSClasses.setNew( new Solution() );
		DSS.solutionClass = DSS.activeDSSClass;  // this is a special class
		DSSClass.DSSClasses.setNew( new LineCode() );
		DSS.loadShapeClass = new LoadShape();
		DSSClass.DSSClasses.setNew( DSS.loadShapeClass );

		DSS.TShapeClass = new TempShape();
		DSSClass.DSSClasses.setNew( DSS.TShapeClass );
		DSS.priceShapeClass = new PriceShape();
		DSSClass.DSSClasses.setNew( DSS.priceShapeClass );
		DSS.XYCurveClass = new XYCurve();
		DSSClass.DSSClasses.setNew( DSS.XYCurveClass );

		DSS.growthShapeClass = new GrowthShape();
		DSSClass.DSSClasses.setNew( DSS.growthShapeClass );
		DSS.TCC_CurveClass = new TCC_Curve();
		DSSClass.DSSClasses.setNew( DSS.TCC_CurveClass );
		DSS.spectrumClass = new Spectrum();
		DSSClass.DSSClasses.setNew( DSS.spectrumClass );
		DSS.wireDataClass = new WireData();
		DSSClass.DSSClasses.setNew( DSS.wireDataClass );

		DSS.CNDataClass = new CNData();
		DSSClass.DSSClasses.setNew( DSS.CNDataClass );
		DSS.TSDataClass = new TSData();
		DSSClass.DSSClasses.setNew( DSS.TSDataClass );

		DSSClass.DSSClasses.setNew( new LineGeometry() );
		DSS.lineSpacingClass = new LineSpacing();
		DSSClass.DSSClasses.setNew( DSS.lineSpacingClass );
		DSSClass.DSSClasses.setNew( new XfmrCode() );

		/* Circuit element classes */
		DSSClass.DSSClasses.setNew( new Line() );
		DSSClass.DSSClasses.setNew( new VSource() );
		DSSClass.DSSClasses.setNew( new ISource() );
		DSSClass.DSSClasses.setNew( new Load() );
		DSSClass.DSSClasses.setNew( new Transformer() );
		DSSClass.DSSClasses.setNew( new RegControl() );
		DSSClass.DSSClasses.setNew( new Capacitor() );
		DSSClass.DSSClasses.setNew( new Reactor() );
		DSSClass.DSSClasses.setNew( new CapControl() );
		DSSClass.DSSClasses.setNew( new Fault() );
		DSSClass.DSSClasses.setNew( new Generator() );
		DSSClass.DSSClasses.setNew( new GenDispatcher() );
		DSS.storageClass = new Storage();
		DSSClass.DSSClasses.setNew( DSS.storageClass );
		DSSClass.DSSClasses.setNew( new StorageController() );
		DSSClass.DSSClasses.setNew( new Relay() );
		DSSClass.DSSClasses.setNew( new Recloser() );
		DSSClass.DSSClasses.setNew( new Fuse() );
		//Globals.setFeederClass(new FeederImpl());
		//DSSClassImpl.DSSClasses.setNew( Globals.getFeederClass() );
		DSSClass.DSSClasses.setNew( new SwtControl() );
		DSS.PVSystemClass = new PVSystem();
		DSSClass.DSSClasses.setNew( DSS.PVSystemClass );

		DSS.VVControlClass = new VVControl();
		DSSClass.DSSClasses.setNew ( DSS.VVControlClass );

		DSS.monitorClass = new Monitor();  // have to do this after Generator
		DSSClass.DSSClasses.setNew( DSS.monitorClass );
		DSS.energyMeterClass = new EnergyMeter();  // have to do this after Generator
		DSSClass.DSSClasses.setNew( DSS.energyMeterClass );
		DSS.sensorClass = new Sensor();  // create state estimation sensors
		DSSClass.DSSClasses.setNew( DSS.sensorClass );


		/* Create classes for custom implementations */
		//MyClassDefs.createMyDSSClasses();

		numIntrinsicClasses = DSS.DSSClassList.size();
		numUserClasses = 0;

		/* Add user-defined objects */

	}

	public static void disposeDSSClasses() {
		DSSObject obj;
		String traceName = "";
		String successFree = "";

		try {
			successFree = "First Object";
			for (int i = 0; i < DSS.DSSObjs.size(); i++) {
				obj = DSS.DSSObjs.get(i);
				traceName = obj.getParentClass().getClassName() + "." + obj.getName();
				successFree = traceName;
			}
			traceName = "(DSSObjs Class)";
			DSS.DSSObjs = null;
		} catch (Exception e) {
			DSS.doSimpleMsg("Exception disposing of DSS obj \""+traceName+"\". "+DSS.CRLF+
					"Last successful dispose was for object \"" + successFree + "\" " +DSS.CRLF+
					e.getMessage(), 901);
		}

		try {
			for (int i = 0; i < DSS.DSSClassList.size(); i++)
				DSS.DSSClassList.set(i, null);
			traceName = "(DSS Class List)";
			DSS.DSSClassList = null;
			traceName = "(DSS Classes)";
			DSSClass.DSSClasses = null;
			traceName = "(ClassNames)";
			DSS.classNames = null;
		} catch (Exception e) {
			DSS.doSimpleMsg("Exception disposing of DSS class\"" + traceName +
					"\". " + DSS.CRLF + e.getMessage(), 902);
		}
	}

	/**
	 * Set lastClassReferenced variable by class name.
	 */
	public static boolean setObjectClass(String objType) {
		int classRef = DSS.classNames.find(objType);

		switch (classRef) {
		case 0:
			DSS.doSimpleMsg("Error: Object class \"" + objType + "\" not found." +
					DSS.CRLF + Parser.getInstance().getCommand(), 903);
			return false;
		default:
			DSS.lastClassReferenced = classRef;
			break;
		}

		return true;
	}

	public static DSSClass getDSSClass(String className) {
		return DSS.DSSClassList.get( DSS.classNames.find(className.toLowerCase()) );
	}

//	public static void addUserClass() {
//		throw new UnsupportedOperationException();
//	}
//
//	public static void loadUserClasses() {
//		throw new UnsupportedOperationException();
//	}

}
