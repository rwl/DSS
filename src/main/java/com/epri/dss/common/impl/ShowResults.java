package com.epri.dss.common.impl;

import java.io.PrintWriter;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.DSSClass;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.MathUtil;

public abstract class ShowResults {

	private static final char TABCHAR = '\u0009';

	private static int MaxBusNameLength;
	private static int MaxDeviceNameLength;

	private static int getMaxBusNameLength() {
		return MaxBusNameLength;
	}

	private static void setMaxBusNameLength(int maxBusNameLength) {
		MaxBusNameLength = 4;
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		for (int i = 0; i < ckt.getNumBuses(); i++)
			MaxBusNameLength = Math.max(MaxBusNameLength, ckt.getBusList().get(i).length());
	}

	private static int getMaxDeviceNameLength() {
		return MaxDeviceNameLength;
	}

	private static void setMaxDeviceNameLength(int maxDeviceNameLength) {
		String DevName, DevClassName;

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		MaxDeviceNameLength = 0;
		for (int i = 0; i < ckt.getNumDevices(); i++) {
			DevName = ckt.getDeviceList().get(i);
			DevClassName = ((DSSClass) Globals.getDSSClassList().get( ckt.getDeviceRef()[i].CktElementClass )).getName();
			MaxDeviceNameLength = Math.max(MaxDeviceNameLength, DevName.length() + DevClassName.length() + 1);
		}
	}

	private static void writeSeqVoltages(PrintWriter F, int i, boolean LL) {
		int j, k;
		Complex[] Vph = new Complex[3];
		Complex[] VLL = new Complex[3];
		Complex[] V012 = new Complex[3];
		double V0, V1, V2, Vpu, V2V1, V0V1;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		if (ckt.getBuses()[i].getNumNodesThisBus() >= 3) {

			for (j = 0; j < 3; j++)
				Vph[j] = ckt.getSolution().getNodeV()[ ckt.getBuses()[i].getRef(j) ];

			if (LL) {
				for (j = 0; j < 3; j++) {
					k = j + 1;
					if (k > 3) k = 1;  // TODO Check zero based indexing
					VLL[j] = Vph[j].subtract(Vph[k]);
				}
				MathUtil.phase2SymComp(VLL, V012);
			} else {
				MathUtil.phase2SymComp(Vph, V012);
			}

			V0 = V012[0].abs();
			V1 = V012[1].abs();
			V2 = V012[2].abs();
		} else {
			Vph[0] = ckt.getSolution().getNodeV()[ ckt.getBuses()[i].getRef(0) ];
			V0 = 0.0;
			V1 = Vph[0].abs();  // Use first phase value for non-three phase buses
			V2 = 0.0;
		}

		V1 = V1 / 1000.0;  /* Convert to kV */
		V2 = V2 / 1000.0;
		V0 = V0 / 1000.0;

		// Calc per unit value
		if (ckt.getBuses()[i].getkVBase() != 0.0) {
			Vpu = V1 / ckt.getBuses()[i].getkVBase();
		} else {
			Vpu = 0.0;
		}
		if (LL) Vpu = Vpu / DSSGlobals.SQRT3;

		if (V1 > 0.0) {
			V2V1 = 100.0 * V2 / V1;
			V0V1 = 100.0 * V0 / V1;
		} else {
			V2V1 = 0.0;
			V0V1 = 0.0;
		}

		F.printf("%s %9.4g  %9.4g  %9.4g  %9.4g %9.4g %9.4g", Utilities.pad(ckt.getBusList().get(i), MaxBusNameLength), V1, Vpu, V2, V2V1, V0, V0V1);
		F.println();
	}

	private static void writeBusVoltages(PrintWriter F, int i, boolean LL) {
		int nref, j, k;
		Complex Volts;
		double Vmag, Vpu;
		String Bname;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		for (j = 0; j < ckt.getBuses()[i].getNumNodesThisBus(); j++) {
			nref = ckt.getBuses()[i].getRef(j);
			Volts = ckt.getSolution().getNodeV()[nref];

			if (LL && (j < 4)) {  // TODO Check zero based indexing
				// Convert to Line-Line assuming no more than 3 phases
				k = j + 1;
				if (k > 3) k = 1;  // TODO Check zero based indexing
				if (k <= ckt.getBuses()[i].getNumNodesThisBus()) {
					nref = ckt.getBuses()[i].getRef(k);
					Volts = Volts.subtract( ckt.getSolution().getNodeV()[nref] );
				}
			}
			Vmag = Volts.abs() * 0.001;
			if (ckt.getBuses()[i].getkVBase() != 0.0) {
				Vpu = Vmag / ckt.getBuses()[i].getkVBase();
			} else {
				Vpu = 0.0;
			}
			if (LL) Vpu = Vpu / DSSGlobals.SQRT3;
			if (j == 0) {  // TODO Check zero based indexing
				Bname = Utilities.padDots(ckt.getBusList().get(i), MaxBusNameLength);
			} else {
				Bname = Utilities.pad("   -", MaxBusNameLength);
			}
			F.printf("%s %2d %10.5g /_ %6.1f %9.5g %9.3f", Bname, ckt.getBuses()[i].getNum(j), Vmag, Volts.degArg(), Vpu, ckt.getBuses()[i].getkVBase() * DSSGlobals.SQRT3);
			F.println();
		}
	}

	private static void writeElementVoltages(PrintWriter F, CktElement pElem, boolean LL) {
		int NCond, Nterm, i,j,k, nref, bref;
		String BusName;
		Complex Volts;
		double Vpu, Vmag;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		NCond = pElem.getNConds();
		Nterm = pElem.getNTerms();
		k = -1;
		BusName = Utilities.pad( Utilities.stripExtension(pElem.getFirstBus()), MaxBusNameLength );
		F.println("ELEMENT = \"" + pElem.getDSSClassName() + "." + pElem.getName() + "\"");
		for (j = 0; j < Nterm; j++) {
			for (i = 0; i < NCond; i++) {
				k++;
				nref = pElem.getNodeRef()[k];
				Volts = ckt.getSolution().getNodeV()[nref];
				Vmag  = Volts.abs() * 0.001;
				if (nref == 0) {
					Vpu = 0.0;
				} else {
					bref = ckt.getMapNodeToBus()[nref].BusRef;
					if (ckt.getBuses()[bref].getkVBase() != 0.0) {
						Vpu = Vmag / ckt.getBuses()[bref].getkVBase();
					} else {
						Vpu = 0.0;
					}
				}
				if (LL) Vpu = Vpu / DSSGlobals.SQRT3;
				F.printf("%s  (%3d) %4d    %13.5g (%8.4g) /_ %6.1f", BusName, nref, i,Vmag, Vpu, Volts.degArg());
				F.println();
			}
			if (j < Nterm)
				F.println("------------");
			BusName = Utilities.pad( Utilities.stripExtension(pElem.getNextBus()), MaxBusNameLength );
		}
	}

	private static void writeElementDeltaVoltages(PrintWriter F, CktElement pElem) {
		int NCond, Node1, Node2, Bus1, Bus2, i;
		double Vmag;
		Complex Volts1, Volts2;
		String ElemName;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		NCond = pElem.getNConds();

		ElemName = Utilities.pad(pElem.getDSSClassName() + "." + pElem.getName(), MaxDeviceNameLength);
		for (i = 0; i < NCond; i++) {
			Node1 = pElem.getNodeRef()[i];
			Node2 = pElem.getNodeRef()[i + NCond];
			if (Node1 > 0) {
				Bus1  = ckt.getMapNodeToBus()[Node1].BusRef;
			} else {
				Bus1 = 0;
			}
			if (Node2 > 0) {
				Bus2 = ckt.getMapNodeToBus()[Node2].BusRef;
			} else {
				Bus2 = 0;
			}
			if ((Bus1 > 0) && (Bus2 > 0)) {  // TODO Check zero based indexing
				Volts1 = ckt.getSolution().getNodeV()[Node1];  // OK if Node1 or Node2 = 0
				Volts2 = ckt.getSolution().getNodeV()[Node2];
				Volts1 = Volts1.subtract(Volts2);  // diff voltage

				if (ckt.getBuses()[Bus1].getkVBase() != ckt.getBuses()[Bus2].getkVBase()) {
					Vmag = 0.0;
				} else {
					if (ckt.getBuses()[Bus1].getkVBase() > 0.0) {
						Vmag = Volts1.abs() / (1000.0 * ckt.getBuses()[Bus1].getkVBase()) * 100.0;
					} else {
						Vmag = 0.0;
					}
				}
				F.printf("%s,  %4d,    %12.5g, %12.5g, %12.5g, %6.1f", ElemName, i, Volts1.abs(), Vmag, ckt.getBuses()[Bus1].getkVBase(), Volts1.degArg());
				F.println();
			}
		}
	}

	/**
	 * Show bus voltages by circuit element terminal.
	 */
	public static void showVoltages(String FileNm, boolean LL, int ShowOptionCode) {

	}

	public static void showCurrents(String FileNm, boolean ShowResidual, int ShowOptionCode) {

	}

	public static void showPowers(String FileNm, int opt, int ShowOptionCode) {

	}

	public static void showBusPowers(String FileNm, String BusName, int opt, int ShowOptionCode) {

	}

	public static void showFaultStudy(String FileNm) {

	}

	public static void showElements(String FileNm, String ClassName) {

	}

	public static void showBuses(String FileNm) {

	}

	public static void showMeters(String FileNm) {

	}

	public static void showGenMeters(String FileNm) {

	}

	public static void showMeterZone(String FileNm) {

	}

	public static void showLosses(String FileNm) {

	}

	public static void showRegulatorTaps(String FileNm) {

	}

	public static void showOverloads(String FileNm) {

	}

	public static void showUnserved(String FileNm, boolean UE_Only) {

	}

	public static void showVariables(String FileNm) {

	}

	public static void showIsolated(String FileNm) {

	}

	public static void showRatings(String FileNm) {

	}

	public static void showLoops(String FileNm) {

	}

	public static void showLineConstants(String FileNm, double Freq, int Units, double Rho) {

	}

	public static void showYPrim(String FileNm) {

	}

	public static void showY(String FileNm) {

	}

	public static void showTopology(String FileRoot) {

	}

	/* Summary and tree-view to separate files */
	public static void showNodeCurrentSum(String FileNm) {

	}

	public static void showkVBaseMismatch(String FileNm) {

	}

	public static void showDeltaV(String FileNm) {

	}

}
