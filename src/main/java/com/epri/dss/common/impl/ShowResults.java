package com.epri.dss.common.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.DSSClass;
import com.epri.dss.conversion.PCElement;
import com.epri.dss.delivery.FaultObj;
import com.epri.dss.delivery.PDElement;
import com.epri.dss.shared.Polar;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.ComplexUtil;
import com.epri.dss.shared.impl.MathUtil;

public abstract class ShowResults {

	private static final char TABCHAR = '\u0009';

	private static int MaxBusNameLength;
	private static int MaxDeviceNameLength;

	private static void setMaxBusNameLength() {
		MaxBusNameLength = 4;
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		for (int i = 0; i < ckt.getNumBuses(); i++)
			MaxBusNameLength = Math.max(MaxBusNameLength, ckt.getBusList().get(i).length());
	}

	private static void setMaxDeviceNameLength() {
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
		FileWriter FWriter;
		PrintWriter F;
		int i;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			setMaxBusNameLength();

			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			switch (ShowOptionCode) {
			case 0:
				F.println();
				if (LL) {
					F.println("SYMMETRICAL COMPONENT PHASE-PHASE VOLTAGES BY BUS (for 3-phase buses)");
				} else {
					F.println("SYMMETRICAL COMPONENT VOLTAGES BY BUS (for 3-phase buses)");
				}
				F.println();
				F.println(Utilities.pad("Bus", MaxBusNameLength) + "  Mag:   V1 (kV)    p.u.     V2 (kV)   %V2/V1    V0 (kV)    %V0/V1");
				F.println();
				for (i = 0; i < ckt.getNumBuses(); i++)
					writeSeqVoltages(F, i, LL);

			case 1:
				F.println();
				if (LL) {
					F.println("PHASE-PHASE VOLTAGES BY BUS & NODE");
				} else {
					F.println("NODE-GROUND VOLTAGES BY BUS & NODE");
				}
				F.println();
				F.println(Utilities.pad("Bus", MaxBusNameLength) + " Node    V (kV)    Angle      p.u.   Base kV");
				F.println();
				for (i = 0; i < ckt.getNumBuses(); i++)
					writeBusVoltages(F, i, LL);

			case 2:
				F.println();
				F.println("NODE-GROUND VOLTAGES BY CIRCUIT ELEMENT");
				F.println();
				F.println("Power Delivery Elements");
				F.println();
				F.println(Utilities.pad("Bus", MaxBusNameLength) + " (node ref)  Phase    Magnitude, kV (pu)    Angle");
				F.println();


				// Sources first
				for (CktElement pElem : ckt.getSources()) {
					if (pElem.isEnabled())
						writeElementVoltages(F, pElem, LL);
					F.println();
				}

				// PD elements first
				for (CktElement pElem : ckt.getPDElements()) {
					if (pElem.isEnabled())
						writeElementVoltages(F, pElem, LL);
					F.println();
				}

				F.println("= = = = = = = = = = = = = = = = = = =  = = = = = = = = = = =  = =");
				F.println();
				F.println("Power Conversion Elements");
				F.println();
				F.println(Utilities.pad("Bus", MaxBusNameLength) + " (node ref)  Phase    Magnitude, kV (pu)    Angle");
				F.println();

				// PC elements next
				for (CktElement pElem : ckt.getPCElements()) {
					if (pElem.isEnabled())
						writeElementVoltages(F, pElem, LL);
					F.println();
				}
			}

			F.close();
			FWriter.close();

			Utilities.fireOffEditor(FileNm);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	private static void getI0I1I2(double I0, double I1, double I2, double Cmax, int Nphases, int koffset, Complex[] cBuffer) {
		double Cmag;
		int i;
		Complex[] Iph  = new Complex[3];
		Complex[] I012 = new Complex[3];

		if (Nphases >= 3) {
			Cmax = 0.0;
			for (i = 0; i < 3; i++) {
				Iph[i] = cBuffer[koffset + i];
				Cmag = Iph[i].abs();
				if (Cmag > Cmax) Cmax = Cmag;
			}
			MathUtil.phase2SymComp(Iph, I012);
			I0 = I012[0].abs();
			I1 = I012[1].abs();
			I2 = I012[2].abs();
		} else {
			I0 = 0.0;
			I1 = cBuffer[1 + koffset].abs();
			I2 = 0.0;
			Cmax = I1;
		}
	}

	private static void writeSeqCurrents(PrintWriter F, final String PaddedBrName,
			double I0, double I1, double I2, double Cmax, double NormAmps, double EmergAmps,
			int j, int DSSObjType) {

		double Inormal, Iemerg, I2I1, I0I1;
		String Name;

		Inormal = 0.0;
		Iemerg = 0.0;
		if (j == 0) {  // TODO Check zero based indexing
			Name = PaddedBrName;
		} else {
			Name = Utilities.pad("   -", PaddedBrName.length());
		}

		if (I1 > 0.0) {
			I2I1 = 100.0 * I2 / I1;
		} else {
			I2I1 = 0.0;
		}

		if (I1 > 0.0) {
			I0I1 = 100.0 * I0 / I1;
		} else {
			I0I1 = 0.0;
		}
		if (((DSSClassDefs.CLASSMASK & DSSObjType) != DSSClassDefs.CAP_ELEMENT) && (j == 0)) {  // TODO Check zero based indexing
			// only write overloads for non-capacitors and terminal 1
			if (NormAmps > 0.0) Inormal = Cmax / NormAmps * 100.0;
			if (EmergAmps > 0.0) Iemerg = Cmax / EmergAmps * 100.0;
		}

		F.printf("%s %3d  %10.5g   %10.5g %8.2f  %10.5g %8.2f  %8.2f %8.2f",
				Name, j, I1, I2, I2I1, I0, I0I1, Inormal, Iemerg);
		F.println();
	}

	private static void writeTerminalCurrents(PrintWriter F, CktElement pElem, boolean ShowResidual) {
		int j, i, k, NCond, NTerm;
		Complex[] cBuffer;
		String FromBus;
		Complex Ctotal;
		Polar ResidPolar;

		NCond = pElem.getNConds();
		NTerm = pElem.getNTerms();

		cBuffer = new Complex[NCond * NTerm];
		pElem.getCurrents(cBuffer);
		k = -1;
		FromBus = Utilities.pad( Utilities.stripExtension(pElem.getFirstBus()), MaxBusNameLength );
		F.println("ELEMENT = " + Utilities.fullName(pElem));
		for (j = 0; j < NTerm; j++) {
			Ctotal = Complex.ZERO;
			for (i = 0; i < NCond; i++) {
				k++;
				if (ShowResidual)
					Ctotal = Ctotal.add(cBuffer[k]);
				F.printf("%s  %4d    %13.5g /_ %6.1f", FromBus, Utilities.getNodeNum(pElem.getNodeRef()[k]), cBuffer[k].abs(), cBuffer[k].degArg());
				F.println();
			}
			if (ShowResidual && (pElem.getNPhases() > 1)) {
				ResidPolar = ComplexUtil.complexToPolarDeg(Ctotal.negate());
				F.printf("%s Resid    %13.5g /_ %6.1f", FromBus, ResidPolar.getMag(), ResidPolar.getAng());
				F.println();
			}
			if (j < NTerm) F.println("------------");  // TODO Check zero based indexing
			FromBus = Utilities.pad( Utilities.stripExtension(pElem.getNextBus()), MaxBusNameLength );
		}
		F.println();
	}


	public static void showCurrents(String FileNm, boolean ShowResidual, int ShowOptionCode) {
		FileWriter FWriter;
		PrintWriter F;
		Complex[] cBuffer;
		int NCond, NTerm, j;
		double I0 = 0, I1 = 0, I2 = 0, Cmax = 0;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		setMaxDeviceNameLength();
		setMaxBusNameLength();

		try {
			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			switch (ShowOptionCode) {
			case 0:  /* Sequence Currents */

				F.println();
				F.println("SYMMETRICAL COMPONENT CURRENTS BY CIRCUIT ELEMENT (first 3 phases)");
				F.println();
				F.println(Utilities.pad("Element", MaxDeviceNameLength + 2) + " Term      I1         I2         %I2/I1    I0         %I0/I1   %Normal %Emergency");
				F.println();

				// Sources first
				for (CktElement pElem : ckt.getSources()) {
					if (pElem.isEnabled()) {
						NCond = pElem.getNConds();
						NTerm = pElem.getNTerms();
						cBuffer = new Complex[NCond * NTerm];
						pElem.getCurrents(cBuffer);

						for (j = 0; j < NTerm; j++) {
							getI0I1I2(I0, I1, I2, Cmax, pElem.getNPhases(), (j - 1) * NCond, cBuffer);  // TODO Check pass be value
							writeSeqCurrents(F, Utilities.padDots( Utilities.fullName(pElem), MaxDeviceNameLength + 2 ), I0, I1, I2, Cmax, 0.0, 0.0, j, pElem.getDSSObjType());
						}
						cBuffer = null;
					}
				}

				// PD elements next
				for (PDElement PDElem : ckt.getPDElements()) {
					if (PDElem.isEnabled()) {
						NCond = PDElem.getNConds();
						NTerm = PDElem.getNTerms();
						cBuffer = new Complex[NCond * NTerm];
						PDElem.getCurrents(cBuffer);

						for (j = 0; j < NTerm; j++) {
							getI0I1I2(I0, I1, I2, Cmax, PDElem.getNPhases(), (j - 1) * NCond, cBuffer);
							writeSeqCurrents(F, Utilities.padDots( Utilities.fullName(PDElem), MaxDeviceNameLength + 2 ), I0, I1, I2, Cmax, PDElem.getNormAmps(), PDElem.getEmergAmps(), j, PDElem.getDSSObjType());
						}
						cBuffer = null;
					}
				}

				// PC elements next
				for (PCElement PCElem : ckt.getPCElements()) {
					if (PCElem.isEnabled()) {
						NCond = PCElem.getNConds();
						NTerm = PCElem.getNTerms();
						cBuffer = new Complex[NCond * NTerm];
						PCElem.getCurrents(cBuffer);

						for (j = 0; j < NTerm; j++) {
							getI0I1I2(I0, I1, I2, Cmax, PCElem.getNPhases(), (j - 1) * NCond, cBuffer);
							writeSeqCurrents(F, Utilities.padDots( Utilities.fullName(PCElem), MaxDeviceNameLength + 2 ), I0, I1, I2, Cmax, 0.0, 0.0, j, PCElem.getDSSObjType());
						}
						cBuffer = null;
					}
				}

				// Faults next
				for (CktElement pElem : ckt.getFaults()) {
					if (pElem.isEnabled()) {
						NCond = pElem.getNConds();
						NTerm = pElem.getNTerms();
						cBuffer = new Complex[NCond * NTerm];
						pElem.getCurrents(cBuffer);

						for (j = 0; j < NTerm; j++) {
							getI0I1I2(I0, I1, I2, Cmax, pElem.getNPhases(), (j - 1) * NCond, cBuffer);
							writeSeqCurrents(F, Utilities.padDots( Utilities.fullName(pElem), MaxDeviceNameLength + 2 ), I0, I1, I2, Cmax, 0.0, 0.0, j, pElem.getDSSObjType());
						}
						cBuffer = null;
					}
				}

			case 1:  // Element branch Currents

				F.println();
				F.println("CIRCUIT ELEMENT CURRENTS");
				F.println();
				F.println("(Currents into element from indicated bus)");
				F.println();
				F.println("Power Delivery Elements");
				F.println();
				F.println(Utilities.pad("  Bus", MaxBusNameLength) + " Phase    Magnitude, A     Angle");
				F.println();

				// Sources first
				for (CktElement pElem : ckt.getSources())
					if (pElem.isEnabled())
						writeTerminalCurrents(F, pElem, false);

				// PD elements next
				for (CktElement pElem : ckt.getPDElements())
					if (pElem.isEnabled())
						writeTerminalCurrents(F, pElem, ShowResidual);

				// Faults
				for (CktElement pElem : ckt.getFaults())
					if (pElem.isEnabled())
						writeTerminalCurrents(F, pElem, false);


				F.println("= = = = = = = = = = = = = = = = = = =  = = = = = = = = = = =  = =");
				F.println();
				F.println("Power Conversion Elements");
				F.println();
				F.println(Utilities.pad("  Bus", MaxBusNameLength) + " Phase    Magnitude, A     Angle");
				F.println();

				// PC elements next
				for (CktElement pElem : ckt.getPCElements())
					if (pElem.isEnabled())
						writeTerminalCurrents(F, pElem, false);
			}
			F.close();
			FWriter.close();

			Utilities.fireOffEditor(FileNm);
		} catch (IOException e) {
			DSSGlobals.getInstance().doSimpleMsg("Exception raised in ShowCurrents: " + e.getMessage(), 2190);
		}
	}

	/**
	 * opt = 0: kVA
	 * opt = 1: MVA
	 */
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
