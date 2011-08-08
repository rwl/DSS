package com.epri.dss.common.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.epri.dss.common.Bus;
import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.DSSClass;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.control.RegControlObj;
import com.epri.dss.conversion.Generator;
import com.epri.dss.conversion.GeneratorObj;
import com.epri.dss.conversion.LoadObj;
import com.epri.dss.conversion.PCElement;
import com.epri.dss.delivery.PDElement;
import com.epri.dss.delivery.TransformerObj;
import com.epri.dss.delivery.impl.LineImpl;
import com.epri.dss.general.LineGeometry;
import com.epri.dss.general.LineGeometryObj;
import com.epri.dss.meter.EnergyMeter;
import com.epri.dss.meter.EnergyMeterObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.CktTree;
import com.epri.dss.shared.CktTreeNode;
import com.epri.dss.shared.Polar;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.CktTreeImpl;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.ComplexUtil;
import com.epri.dss.shared.impl.LineUnits;
import com.epri.dss.shared.impl.MathUtil;

public abstract class ShowResults {

	private static final char TABCHAR = '\u0009';

	private static int MaxBusNameLength = 12;
	private static int MaxDeviceNameLength = 30;

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

				break;
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

				break;
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
				break;
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

				break;
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
				break;
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
		String FromBus;
		FileWriter FWriter;
		PrintWriter F;
		Complex[] cBuffer;
		int NCond, Nterm, i, j, k;
//		CktElement pElem;
//		PDElement PDElem;
//		PDElement PCElem;
		Complex Volts;
		Complex S, Saccum;
		int nref;
		Complex[] Vph = new Complex[3];
		Complex[] V012 = new Complex[3];
		Complex[] Iph = new Complex[3];
		Complex[] I012 = new Complex[3];

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		setMaxDeviceNameLength();
		setMaxBusNameLength();

		try {
			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			/* Allocate c_Buffer big enough for largest circuit element */
			cBuffer = new Complex[Utilities.getMaxCktElementSize()];

			switch (ShowOptionCode) {
			case 0:
				/* Sequence Currents */
				F.println();
				F.println("SYMMETRICAL COMPONENT POWERS BY CIRCUIT ELEMENT (first 3 phases)                                     Excess Power");
				F.println();
				switch (opt) {
				case 1:
					F.println(Utilities.pad("Element", MaxDeviceNameLength + 2) + " Term    P1(MW)   Q1(Mvar)       P2         Q2      P0      Q0       P_Norm      Q_Norm     P_Emerg    Q_Emerg");
					break;
				default:
					F.println(Utilities.pad("Element", MaxDeviceNameLength + 2) + " Term    P1(kW)   Q1(kvar)       P2         Q2      P0      Q0       P_Norm      Q_Norm     P_Emerg    Q_Emerg");
					break;
				}
				F.println();

				// Sources first
				for (CktElement pElem : ckt.getSources()) {
					if (pElem.isEnabled()) {
						NCond = pElem.getNConds();
						Nterm = pElem.getNTerms();
						pElem.getCurrents(cBuffer);

						for (j = 0; j < Nterm; j++) {
							F.print(Utilities.pad( Utilities.fullName(pElem), MaxDeviceNameLength + 2) + j);
							for (i = 0; i < Math.min(3, pElem.getNPhases()); i++) {
								k = (j - 1) * NCond + i;
								nref = pElem.getNodeRef()[k];
								Volts = ckt.getSolution().getNodeV()[nref];
								Iph[i] = cBuffer[k];
								Vph[i] = Volts;
							}
							if (pElem.getNPhases() >= 3) {
								MathUtil.phase2SymComp(Iph, I012);
								MathUtil.phase2SymComp(Vph, V012);
							} else {
								// Handle single phase and pos seq models
								V012[0] = Complex.ZERO;
								I012[0] = Complex.ZERO;
								V012[2] = Complex.ZERO;
								I012[2] = Complex.ZERO;
								if (ckt.isPositiveSequence()) {
									V012[1] = Vph[0];
									I012[1] = Iph[0];
								} else {
									V012[1] = Complex.ZERO;
									I012[1] = Complex.ZERO;
								}
							}

							S = V012[1].multiply( I012[1].conjugate() );
							if (opt == 1) S = S.multiply(0.001);
							F.print(S.getReal() * 0.003);
							F.print(S.getImaginary() * 0.003);
							S = V012[2].multiply( I012[2].conjugate() );
							if (opt == 1) S = S.multiply(0.001);
							F.print(S.getReal() * 0.003);
							F.print(S.getImaginary() * 0.003);
							S = V012[0].multiply( I012[0].conjugate() );
							if (opt == 1) S = S.multiply(0.001);
							F.print(S.getReal() * 0.003);
							F.print(S.getImaginary() * 0.003);
							F.println();
						}
					}
				}

				// PD elements next
				for (PDElement PDElem : ckt.getPDElements()) {
					if (PDElem.isEnabled()) {
						NCond = PDElem.getNConds();
						Nterm = PDElem.getNTerms();
						PDElem.getCurrents(cBuffer);

						for (j = 0; j < Nterm; j++) {
							F.print(Utilities.pad( Utilities.fullName(PDElem), MaxDeviceNameLength + 2) + j);
							for (i = 0; i < Math.min(3, PDElem.getNPhases()); i++) {
								k = (j - 1) * NCond + i;
								nref = PDElem.getNodeRef()[k];
								Volts = ckt.getSolution().getNodeV()[nref];
								Iph[i] = cBuffer[k];
								Vph[i] = Volts;
							}
							if (PDElem.getNPhases() >= 3) {
								MathUtil.phase2SymComp(Iph, I012);
								MathUtil.phase2SymComp(Vph, V012);
							} else {  // Handle single phase and pos seq models
								V012[0] = Complex.ZERO;
								I012[0] = Complex.ZERO;
								V012[1] = Complex.ZERO;
								I012[1] = Complex.ZERO;
								if (ckt.isPositiveSequence()) {
									V012[1] = Vph[0];
									I012[1] = Iph[0];
								} else {
									V012[1] = Complex.ZERO;
									I012[1] = Complex.ZERO;
								}
							}

							S = V012[1].multiply( I012[1].conjugate() );
							if (opt == 1) S = S.multiply(0.001);
							F.print(S.getReal() * 0.003);
							F.print(S.getImaginary() * 0.003);
							S = V012[2].multiply( I012[2].conjugate() );
							if (opt == 1) S = S.multiply(0.001);
							F.print(S.getReal() * 0.003);
							F.print(S.getImaginary() * 0.003);
							S = V012[0].multiply( I012[0].conjugate() );
							if (opt == 1) S = S.multiply(0.001);
							F.print(S.getReal() * 0.003);
							F.print(S.getImaginary() * 0.003);

							if (j == 0) {  // TODO Check zero based indexing
								S = PDElem.getExcessKVANorm(0);
								if (opt == 1) S = S.multiply(0.001);
								F.print(S.getReal());
								F.print(S.getImaginary());
								S = PDElem.getExcessKVAEmerg(1);  // TODO Check zero based indexing
								if (opt == 1) S = S.multiply(0.001);
								F.print(S.getReal());
								F.print(S.getImaginary());
							}
							F.println();

						}
					}
				}

				// PC elements next
				for (PCElement PCElem : ckt.getPCElements()) {
					if (PCElem.isEnabled()) {
						NCond = PCElem.getNConds();
						Nterm = PCElem.getNTerms();
						PCElem.getCurrents(cBuffer);

						for (j = 0; j < Nterm; j++) {
							F.print(Utilities.pad( Utilities.fullName(PCElem), MaxDeviceNameLength + 2) + j);
							for (i = 0; i < Math.min(3, PCElem.getNPhases()); i++) {
								k = (j - 1) * NCond + i;  // TODO Check zero based indexing
								nref = PCElem.getNodeRef()[k];
								Volts = ckt.getSolution().getNodeV()[nref] ;
								Iph[i] = cBuffer[k];
								Vph[i] = Volts;
							}

							if (PCElem.getNPhases() >= 3) {
								MathUtil.phase2SymComp(Iph, I012);
								MathUtil.phase2SymComp(Vph, V012);
							} else {  // Handle single phase and pos seq models
								V012[0] = Complex.ZERO;
								I012[0] = Complex.ZERO;
								V012[2] = Complex.ZERO;
								I012[3] = Complex.ZERO;
								if (ckt.isPositiveSequence()) {
									V012[1] = Vph[0];
									I012[1] = Iph[0];
								} else {
									V012[1] = Complex.ZERO;
									I012[2] = Complex.ZERO;
								}
							}

							S = V012[1].multiply( I012[1].conjugate() );
							if (opt == 1) S = S.multiply(0.001);
							F.print(S.getReal() * 0.003);
							F.print(S.getImaginary() * 0.003);
							S = V012[2].multiply( I012[2].conjugate() );
							if (opt == 1) S = S.multiply(0.001);
							F.print(S.getReal() * 0.003);
							F.print(S.getImaginary() * 0.003);
							S = V012[0].multiply( I012[0].conjugate() );
							if (opt == 1) S = S.multiply(0.001);
							F.print(S.getReal() * 0.003);
							F.print(S.getImaginary() * 0.003);

							F.println();

						}
					}
				}
				break;
			case 1:  /* Branch Powers */
				F.println();
				F.println("CIRCUIT ELEMENT POWER FLOW");
				F.println();
				F.println("(Power Flow into element from indicated Bus)");
				F.println();
				F.println("Power Delivery Elements");
				F.println();
				switch (opt) {
				case 1:
					F.println(Utilities.pad("  Bus", MaxBusNameLength) + " Phase     MW     +j   Mvar         MVA         PF");
					break;
				default:
					F.println(Utilities.pad("  Bus", MaxBusNameLength) + " Phase     kW     +j   kvar         kVA         PF");
					break;
				}
				F.println();


				for (CktElement pElem : ckt.getSources()) {
					if (pElem.isEnabled()) {
						NCond = pElem.getNConds();
						Nterm = pElem.getNTerms();
						pElem.getCurrents(cBuffer);
						k = -1;
						FromBus = Utilities.pad( Utilities.stripExtension(pElem.getFirstBus()), MaxBusNameLength );
						F.println("ELEMENT = " + Utilities.fullName(pElem));
						for (j = 0; j < Nterm; j++) {
							Saccum = Complex.ZERO;
							for (i = 0; i < NCond; i++) {
								k++;
								nref = pElem.getNodeRef()[k];
								Volts = ckt.getSolution().getNodeV()[nref];
								S = Volts.multiply( cBuffer[k].conjugate() );
								if (/* (pElem.getNPhases() == 1) and */ ckt.isPositiveSequence())
									S = S.multiply(3.0);
								if (opt == 1) S = S.multiply(0.001);
								Saccum = Saccum.add(S);
								F.print(FromBus + "  " + Utilities.getNodeNum(pElem.getNodeRef()[k]) + "    " + S.getReal() / 1000.0 + " +j " + S.getImaginary() / 1000.0);
								F.println("   " + S.abs() / 1000.0 + "     " + Utilities.powerFactor(S));
							}
							F.print(Utilities.padDots("   TERMINAL TOTAL", MaxBusNameLength + 10) + Saccum.getReal() / 1000.0 + " +j " + Saccum.getImaginary() / 1000.0);
							F.println("   " + Saccum.abs() / 1000.0 + "     " + Utilities.powerFactor(Saccum));
							FromBus = Utilities.pad( Utilities.stripExtension(pElem.getNextBus()), MaxBusNameLength );
						}
					}
					F.println();
				}


				// PD elements next
				for (CktElement pElem : ckt.getPDElements()) {
					if (pElem.isEnabled()) {
						NCond = pElem.getNConds();
						Nterm = pElem.getNTerms();
						pElem.getCurrents(cBuffer);
						k = 0;
						FromBus = Utilities.pad( Utilities.stripExtension(pElem.getFirstBus()), MaxBusNameLength );
						F.println("ELEMENT = " + Utilities.fullName(pElem));
						for (j = 0; j < Nterm; j++) {
							Saccum = Complex.ZERO;
							for (i = 0; i < NCond; i++) {
								k++;
								nref = pElem.getNodeRef()[k];
								Volts = ckt.getSolution().getNodeV()[nref];
								S = Volts.multiply(cBuffer[k].conjugate());
								if (/* (pElem.getNPhases() == 1) and */ ckt.isPositiveSequence())
									S = S.multiply(3.0);
								if (opt == 1) S = S.multiply(0.001);
								Saccum = Saccum.add(S);
								F.print(FromBus + "  " + Utilities.getNodeNum(pElem.getNodeRef()[k]) + "    " + S.getReal() / 1000.0 + " +j " + S.getImaginary() / 1000.0);
								F.println("   " + S.abs() / 1000.0 + "     " + Utilities.powerFactor(S));
							}
							F.print(Utilities.padDots("   TERMINAL TOTAL", MaxBusNameLength + 10) + Saccum.getReal() / 1000.0 + " +j " + Saccum.getImaginary() / 1000.0);
							F.println("   " + Saccum.abs() / 1000.0 + "     " + Utilities.powerFactor(Saccum));
							FromBus = Utilities.pad( Utilities.stripExtension(pElem.getNextBus()), MaxBusNameLength );
						}
					}
					F.println();
				}

				F.println("= = = = = = = = = = = = = = = = = = =  = = = = = = = = = = =  = =");
				F.println();
				F.println("Power Conversion Elements");
				F.println();
				switch (opt) {
				case 1:
					F.println(Utilities.pad("  Bus", MaxBusNameLength) + " Phase     MW   +j  Mvar         MVA         PF");
					break;
				default:
					F.println(Utilities.pad("  Bus", MaxBusNameLength) + " Phase     kW   +j  kvar         kVA         PF");
					break;
				}
				F.println();

				// PC elements next
				for (CktElement pElem : ckt.getPCElements()) {
					if (pElem.isEnabled()) {
						NCond = pElem.getNConds();
						Nterm = pElem.getNTerms();
						pElem.getCurrents(cBuffer);
						k = -1;
						FromBus = Utilities.pad( Utilities.stripExtension(pElem.getFirstBus()), MaxBusNameLength );
						F.println("ELEMENT = " + Utilities.fullName(pElem));
						for (j = 0; j < Nterm; j++) {
							Saccum = Complex.ZERO;
							for (i = 0; i < NCond; i++) {
								k++;
								nref = pElem.getNodeRef()[k];
								Volts = ckt.getSolution().getNodeV()[nref];
								S = Volts.multiply(cBuffer[k].conjugate());
								if (/* (pElem.getNPhases() == 1) and */ ckt.isPositiveSequence())
									S = S.multiply(3.0);
								if (opt == 1) S = S.multiply(0.001);
								Saccum = Saccum.add(S);
								F.print(FromBus + "  " + Utilities.getNodeNum(pElem.getNodeRef()[k]) + "    " + S.getReal() / 1000.0 + " +j " + S.getImaginary() / 1000.0);
								F.println("   " + S.abs() / 1000.0 + "     " + Utilities.powerFactor(S));
							}
							F.print(Utilities.padDots("   TERMINAL TOTAL", MaxBusNameLength + 10) + Saccum.getReal() / 1000.0 + " +j " + Saccum.getImaginary() / 1000.0);
							F.println("   " + Saccum.abs() / 1000.0 + "     " + Utilities.powerFactor(Saccum));
							FromBus = Utilities.pad( Utilities.stripExtension(pElem.getNextBus()), MaxBusNameLength );
						}
					}
					F.println();
				}
				break;
			}

			F.println();
			S = ckt.getLosses().multiply(0.001);
			if (opt == 1) S = S.multiply(0.001);
			F.println("Total Circuit Losses = " + S.getReal() + " +j " + S.getImaginary());

			cBuffer = null;

			F.close();
			FWriter.close();

			Utilities.fireOffEditor(FileNm);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/**
	 * Check all terminals of cktelement to see if bus connected to busreference.
	 */
	private static boolean checkBusReference(CktElement cktElem, int BusReference, int TerminalIndex) {

		for (int i = 0; i < cktElem.getNTerms(); i++)
			if (cktElem.getTerminals()[i].BusRef == BusReference) {
				TerminalIndex = i;
				return true;
			}

		return false;
	}

	private static void writeTerminalPowerSeq(PrintWriter F, CktElement cktElem, int j, int opt) {
		int i, k, NCond, nref;
		Complex Volts, S;
		Complex[] Vph = new Complex[3];
		Complex[] V012 = new Complex[3];
		Complex[] Iph = new Complex[3];
		Complex[] I012 = new Complex[3];
		Complex[] cBuffer;  // Allocate to max total conductors

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			/* Allocate cBuffer big enough for this circuit element */
			cBuffer = new Complex[cktElem.getYorder()];

			NCond = cktElem.getNConds();
			cktElem.getCurrents(cBuffer);
			F.print(Utilities.pad( Utilities.fullName(cktElem), MaxDeviceNameLength + 2) + j);
			for (i = 0; i < Math.min(cktElem.getNPhases(), 3); i++) {
				k = (j - 1) * NCond + i;  // TODO Check zero based indexing
				nref = cktElem.getNodeRef()[k];
				Volts = ckt.getSolution().getNodeV()[nref];
				Iph[i] = cBuffer[k];
				Vph[i] = Volts;
			}

			if (cktElem.getNPhases() >= 3) {
				MathUtil.phase2SymComp(Iph, I012);
				MathUtil.phase2SymComp(Vph, V012);
			} else {  // Handle single phase and pos seq models
				V012[0] = Complex.ZERO;
				I012[0] = Complex.ZERO;
				V012[2] = Complex.ZERO;
				I012[2] = Complex.ZERO;
				if (ckt.isPositiveSequence()) {
					V012[1] = Vph[0];
					I012[1] = Iph[0];
				} else {
					V012[1] = Complex.ZERO;
					I012[1] = Complex.ZERO;
				}
			}

			// Pos Seq or Single Phase
			switch (cktElem.getNPhases()) {
			case 1:
				S = Vph[0].multiply( Iph[0].conjugate() );
				break;
			case 2:
				S = Vph[0].multiply( Iph[0].conjugate() ).add(Vph[1].multiply( Iph[2].conjugate() ));
				break;
			default:
				S = V012[1].multiply( I012[1].conjugate() );
				break;
			}

			if (opt == 1) S = S.multiply(0.001);
			F.print(S.getReal() * 0.003);
			F.print(S.getImaginary() * 0.003);
			S = V012[2].multiply( I012[2].conjugate() );
			if (opt == 1) S = S.multiply(0.001);
			F.print(S.getReal() * 0.003);
			F.print(S.getImaginary() * 0.003);
			S = V012[0].multiply( I012[0].conjugate() );
			if (opt == 1) S = S.multiply(0.001);
			F.print(S.getReal() * 0.003);
			F.print(S.getImaginary() * 0.003);

			F.println();
		} finally  {
			cBuffer = null;
		}
	}

	private static void writeTerminalPower(PrintWriter F, CktElement CktElem, int jTerm, int opt) {
		int i, k, NCond, nref;
		Complex Volts, S;
		Complex Saccum;
		Complex[] cBuffer;  // Allocate to max total conductors
		String FromBus;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			cBuffer = new Complex[CktElem.getYorder()];

			NCond = CktElem.getNConds();
			CktElem.getCurrents(cBuffer);
			FromBus = Utilities.pad(Utilities.stripExtension(CktElem.getBus(jTerm)), 12);
			F.println("ELEMENT = " + Utilities.pad(Utilities.fullName(CktElem), MaxDeviceNameLength + 2));

			Saccum = Complex.ZERO;
			for (i = 0; i < NCond; i++) {
				k = (jTerm - 1) * NCond + i;  // TODO Check zero based indexing
				nref = CktElem.getNodeRef()[k];
				Volts = ckt.getSolution().getNodeV()[nref];
				S = Volts.multiply( cBuffer[k].conjugate() );
				if (/* (CktElem.getNPhases() == 1) and */ ckt.isPositiveSequence())
					S = S.multiply(3.0);
				if (opt == 1) S = S.multiply(0.001);
				Saccum = Saccum.add(S);
				F.printf("%s %4d %10.5g +j %10.5g    %10.5g    %8.4f",
						FromBus, Utilities.getNodeNum(CktElem.getNodeRef()[k]), S.getReal() / 1000.0, S.getImaginary() / 1000.0,
						S.abs() / 1000.0 , Utilities.powerFactor(S));
				F.println();
			}
			F.printf(" TERMINAL TOTAL   %10.5g +j %10.5g    %10.5g    %8.4f",
					Saccum.getReal() / 1000.0, Saccum.getImaginary() / 1000.0, Saccum.abs() / 1000.0,
					Utilities.powerFactor(Saccum));
			F.println();
		} finally {
			cBuffer = null;
		}
	}

	/**
	 * Report power flow around a specified bus.
	 *
	 * opt = 0: kVA
	 * opt = 1: MVA
	 */
	public static void showBusPowers(String FileNm, String BusName, int opt, int ShowOptionCode) {
		FileWriter FWriter;
		PrintWriter F;
		int j = 0, Ncond, Nterm;
//		CktElement pElem;
//		PDElement PDElem;
//		PCElement PCElem;
		double I0 = 0, I1 = 0, I2 = 0, Cmax = 0;
		Complex[] cBuffer;  // Allocate to max total conductors
		int BusReference;
		int jTerm = 0;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		setMaxDeviceNameLength();

		/* Get bus reference */
		BusReference = ckt.getBusList().find(BusName);
		if (BusReference == 0) {
			DSSGlobals.getInstance().doSimpleMsg("Bus \""+BusName+"\" not found.", 219);
			return;
		}
		try {
			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			cBuffer = new Complex[Utilities.getMaxCktElementSize()];

			switch (ShowOptionCode) {
			case 0:
				/* Write Bus Voltage */

				F.println();
				F.println("  Bus   Mag:    V1 (kV)  p.u.    V2 (kV)  %V2/V1  V0 (kV)  %V0/V1");
				F.println();

				writeSeqVoltages(F, BusReference, false);

				/* Sequence Currents */
				F.println();
				F.println("SYMMETRICAL COMPONENT CURRENTS BY CIRCUIT ELEMENT (first 3 phases)");
				F.println();
				F.println("Element                      Term      I1         I2         %I2/I1    I0         %I0/I1   %Normal %Emergency");
				F.println();

				// Sources first
				for (CktElement pElem : ckt.getSources()) {
					if (pElem.isEnabled())
						if (checkBusReference(pElem, BusReference, j)) {

							/* Use j set by checkBusReference */
							Ncond = pElem.getNConds();
							Nterm = pElem.getNTerms();
							pElem.getCurrents(cBuffer);

							for (j = 0; j < Nterm; j++) {
								getI0I1I2(I0, I1, I2, Cmax, pElem.getNPhases(), (j - 1) * Ncond, cBuffer);
								writeSeqCurrents(F, Utilities.padDots(Utilities.fullName(pElem), MaxDeviceNameLength + 2), I0, I1, I2, Cmax, 0.0, 0.0, j, pElem.getDSSObjType());
							}
						}
				}

				// PD elements next
				for (PDElement PDElem : ckt.getPDElements()) {
					if (PDElem.isEnabled())
						if (checkBusReference(PDElem, BusReference, j)) {  // Is this connected to the bus
							/* Use j set by CheckBusReference */
							Ncond = PDElem.getNConds();
							Nterm = PDElem.getNTerms();
							PDElem.getCurrents(cBuffer);

							for (j = 0; j < Nterm; j++) {
								getI0I1I2(I0, I1, I2, Cmax, PDElem.getNPhases(), (j - 1) * Ncond, cBuffer);
								writeSeqCurrents(F, Utilities.padDots(Utilities.fullName(PDElem), MaxDeviceNameLength + 2), I0, I1, I2, Cmax, 0.0, 0.0, j, PDElem.getDSSObjType());
							}
						}
				}

				// PC elements next
				for (PCElement PCElem : ckt.getPCElements()) {
					if (PCElem.isEnabled())
						if (checkBusReference(PCElem, BusReference, j)) {
							Ncond = PCElem.getNConds();
							Nterm = PCElem.getNTerms();
							PCElem.getCurrents(cBuffer);

							for (j = 0; j < Nterm; j++) {
								getI0I1I2(I0, I1, I2, Cmax, PCElem.getNPhases(), (j - 1) * Ncond, cBuffer);
								writeSeqCurrents(F, Utilities.padDots(Utilities.fullName(PCElem), MaxDeviceNameLength + 2), I0, I1, I2, Cmax, 0.0, 0.0, j, PCElem.getDSSObjType());
							}
						}
				}

				/* Sequence Powers */
				F.println();
				F.println("SYMMETRICAL COMPONENT POWERS BY CIRCUIT ELEMENT (first 3 phases)");
				F.println();
				switch (opt) {
				case 1:
					F.println("Element                      Term    P1(MW)   Q1(Mvar)       P2         Q2      P0      Q0   ");
					break;
				default:
					F.println("Element                      Term    P1(kW)   Q1(kvar)         P2         Q2      P0      Q0  ");
					break;
				}
				F.println();

				// Sources first
				for (CktElement pElem : ckt.getSources()) {
					if (pElem.isEnabled())
						if (checkBusReference(pElem, BusReference, j)) {
							/* Use j set by checkBusReference */
							writeTerminalPowerSeq(F, pElem, j, opt);
						}
				}

				// PD elements next
				for (PDElement PDElem : ckt.getPDElements()) {
					if (PDElem.isEnabled())
						if (checkBusReference(PDElem, BusReference, j)) {  // Is this connected to the bus
							writeTerminalPowerSeq(F, PDElem, j, opt);
						}
				}

				// PC elements next
				for (PCElement PCElem : ckt.getPCElements()) {
					if (PCElem.isEnabled())
						if (checkBusReference(PCElem, BusReference, j)) {
							writeTerminalPowerSeq(F, PCElem, j, opt);
						}
				}

				break;
			case 1:
				/* Write Bus Voltage */
				F.println();
				F.println("  Bus   (node ref)  Node       V (kV)    Angle    p.u.   Base kV");
				F.println();
				writeBusVoltages(F, BusReference, false);

				/* Element Currents */
				F.println();
				F.println("CIRCUIT ELEMENT CURRENTS");
				F.println();
				F.println("(Currents into element from indicated bus)");
				F.println();
				F.println("Power Delivery Elements");
				F.println();
				F.println("  Bus         Phase    Magnitude, A     Angle");
				F.println();

				// Sources first
				for (CktElement pElem : ckt.getSources()) {
					if (pElem.isEnabled())
						if (checkBusReference(pElem, BusReference, j)) {
							writeTerminalCurrents(F, pElem, false);
							F.println();
						}
				}

				// PD elements first
				for (CktElement pElem : ckt.getPDElements()) {
					if (pElem.isEnabled())
						if (checkBusReference(pElem, BusReference, j)) {
							writeTerminalCurrents(F, pElem, true);
							F.println();
						}
				}

				F.println("= = = = = = = = = = = = = = = = = = =  = = = = = = = = = = =  = =");
				F.println();
				F.println("Power Conversion Elements");
				F.println();
				F.println("  Bus         Phase    Magnitude, A     Angle");
				F.println();

				// PC elements next
				for (CktElement pElem : ckt.getPCElements()) {
					if (pElem.isEnabled())
						if (checkBusReference(pElem, BusReference, j)) {
							writeTerminalCurrents(F, pElem, false);
							F.println();
						}
				}

				/* Branch Powers */
				F.println();
				F.println("CIRCUIT ELEMENT POWER FLOW");
				F.println();
				F.println("(Power Flow into element from indicated Bus)");
				F.println();
				switch (opt) {
				case 1:
					F.println("  Bus       Phase     MW     +j   Mvar           MVA           PF");
					break;
				default:
					F.println("  Bus       Phase     kW     +j   kvar           kVA           PF");
					break;
				}
				F.println();

				// Sources first
				for (CktElement pElem : ckt.getSources()) {
					if (pElem.isEnabled())
						if (checkBusReference(pElem, BusReference, j)) {
							writeTerminalPower(F, pElem, j, opt);
							F.println();
						}
				}

				// PD elements first
				for (CktElement pElem : ckt.getPDElements()) {
					if (pElem.isEnabled())
						if (checkBusReference(pElem, BusReference, jTerm)) {
							writeTerminalPower(F, pElem, jTerm, opt);
							/* Get the other bus for the report */
							if (jTerm == 1) {
								jTerm = 2;
							} else {
								jTerm = 1;  // may sometimes give wrong terminal if more than 2 terminals
							}
							writeTerminalPower(F, pElem, jTerm, opt);
							F.println();
						}
				}

				F.println("= = = = = = = = = = = = = = = = = = =  = = = = = = = = = = =  = =");
				F.println();
				F.println("Power Conversion Elements");
				F.println();
				switch (opt) {
				case 1:
					F.println("  Bus         Phase     MW   +j  Mvar         MVA         PF");
				default:
					F.println("  Bus         Phase     kW   +j  kvar         kVA         PF");
				}
				F.println();

				// PC elements next
				for (PCElement pElem : ckt.getPCElements()) {
					if (pElem.isEnabled())
						if (checkBusReference(pElem, BusReference, jTerm)) {
							writeTerminalPower(F, pElem, jTerm, opt);
							F.println();
						}
				}
				break;
			}

			cBuffer = null;

			F.close();
			FWriter.close();

			Utilities.fireOffEditor(FileNm);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void showFaultStudy(String FileNm) {
		int i, iBus, iphs;
		CMatrix YFault, ZFault;
		Complex[] Vfault;  /* Big temp array */
		FileWriter FWriter;
		PrintWriter F;
		Complex GFault, IFault;
		double Vphs;
		double CurrMag;
		Bus bus;

		setMaxBusNameLength();

		try {
			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
			SolutionObj sol = ckt.getSolution();

			/* Set source voltage injection currents */

			/* All Phase Faults */
			F.println("FAULT STUDY REPORT");
			F.println();
			F.println("ALL-Node Fault Currents");
			F.println();
			F.println(Utilities.pad("Bus", MaxBusNameLength) + "       Node 1  X/R        Node 2  X/R        Node 3  X/R   ...  (Amps)");
			F.println();
			for (iBus = 0; iBus < ckt.getNumBuses(); iBus++) {
				/* Bus Norton Equivalent Current, Isc has been previously computed */
				bus = ckt.getBuses()[iBus];
				F.print(Utilities.pad(Utilities.encloseQuotes(ckt.getBusList().get(iBus)) + ",", MaxBusNameLength + 2));
				for (i = 0; i < bus.getNumNodesThisBus(); i++) {
					CurrMag = bus.getBusCurrent()[i].abs();
					if (i > 1)
						F.print(", ");
						F.print(CurrMag);
						if (CurrMag > 0.0) {
							F.print(", " + MathUtil.getXR( bus.getVBus()[i].divide( bus.getBusCurrent()[i] ) ));
						} else {
							F.print(",   N/A");
						}
				}
				F.println();
			}

			F.println();

			/* One Phase Faults */
			F.println();
			F.println("ONE-Node to ground Faults");
			F.println();
			F.println("                                      pu Node Voltages (L-N Volts if no base)");
			F.println(Utilities.pad("Bus", MaxBusNameLength) + "   Node      Amps         Node 1     Node 2     Node 3    ...");
			F.println();

			/* Solve for Fault Injection Currents */
			for (iBus = 0; iBus < ckt.getNumBuses(); iBus++) {
				/* Bus Norton Equivalent Current, Isc has been previously computed */
				bus = ckt.getBuses()[iBus];
				ZFault = new CMatrixImpl(bus.getNumNodesThisBus());
				ZFault.copyFrom(bus.getZsc());

				for (iphs = 0; iphs < bus.getNumNodesThisBus(); iphs++) {
					IFault = bus.getVBus()[iphs].divide( bus.getZsc().getElement(iphs, iphs) );
					F.print(Utilities.pad( Utilities.encloseQuotes(ckt.getBusList().get(iBus)), MaxBusNameLength + 2) + iphs + IFault.abs() + "   ");
					for (i = 0; i < bus.getNumNodesThisBus(); i++) {
						Vphs = bus.getVBus()[i].subtract( bus.getZsc().getElement(i, iphs).multiply(IFault) ).abs();
						if (bus.getkVBase() > 0.0) {
							Vphs = 0.001 * Vphs / bus.getkVBase();
							F.print(" " + Vphs);
						} else {
							F.print(" " + Vphs);
						}
					}
					F.println();
				}
				/* Now, Stuff it in the Css Array where it belongs */

				ZFault = null;
			}

			/* Node-Node Faults */
			F.println();
			F.println("Adjacent Node-Node Faults");
			F.println();
			F.println("                                        pu Node Voltages (L-N Volts if no base)");
			F.println("Bus          Node-Node      Amps        Node 1     Node 2     Node 3    ...");
			F.println();

			/* Solve for Fault Injection Currents */
			for (iBus = 0; iBus < ckt.getNumBuses(); iBus++) {
				/* Bus Norton Equivalent Current, Isc has been previously computed */
				bus = ckt.getBuses()[iBus];
				YFault = new CMatrixImpl(bus.getNumNodesThisBus());
				Vfault = new Complex[bus.getNumNodesThisBus()];

				GFault = new Complex(10000.0, 0.0);

				for (iphs = 0; iphs < bus.getNumNodesThisBus(); iphs++) {
					YFault.copyFrom(bus.getYsc());
					YFault.addElement(iphs, iphs, GFault);
					YFault.addElement(iphs + 1, iphs + 1, GFault);
					YFault.addElemSym(iphs, iphs + 1, GFault.negate());

					/* Solve for Injection Currents */
					YFault.invert();
					YFault.MVMult(Vfault, bus.getBusCurrent());  /* Gets voltage appearing at fault */

					F.print(Utilities.pad(Utilities.encloseQuotes(ckt.getBusList().get(iBus)), MaxBusNameLength + 2) + iphs + (iphs + 1) + Vfault[iphs].subtract(Vfault[iphs + 1]).multiply(GFault).abs() + "   ");
					for (i = 0; i < bus.getNumNodesThisBus(); i++) {
						Vphs = Vfault[i].abs();
						if (bus.getkVBase() > 0.0) {
							Vphs = 0.001 * Vphs / bus.getkVBase();
							F.print(" " + Vphs);
						} else {
							F.print(" " + Vphs);
						}
					}
					F.println();

				}
			}
			/* Now, Stuff it in the Css Array where it belongs */
			Vfault = null;
			YFault = null;

			F.close();
			FWriter.close();

			Utilities.fireOffEditor(FileNm);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	private static void writeElementRecord(PrintWriter F, CktElement pElem) {
		int Nterm, j;
		String BusName;

		Nterm = pElem.getNTerms();
		BusName = Utilities.pad(Utilities.stripExtension(pElem.getFirstBus()), MaxBusNameLength);
		F.print(Utilities.pad(Utilities.fullName(pElem), MaxDeviceNameLength + 2) + " ");
		for (j = 0; j < Nterm; j++) {
			F.print(BusName + " ");
			BusName = Utilities.pad(Utilities.stripExtension(pElem.getNextBus()), MaxBusNameLength);
		}
		F.println();
	}

	/**
	 * Show elements and bus connections.
	 */
	public static void showElements(String FileNm, String ClassName) {
		FileWriter FWriter;
		PrintWriter F;
		FileWriter FDisabledWriter;
		PrintWriter FDisabled;
		int i;
		String DisabledFileNm;

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		setMaxBusNameLength();
		setMaxDeviceNameLength();

		try {
			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			DisabledFileNm = Utilities.stripExtension(FileNm) + "_Disabled.txt";
			FDisabledWriter = new FileWriter(DisabledFileNm);
			FDisabled = new PrintWriter(FWriter);

			if (ClassName.length() > 0) {
				// Just give a list of active elements of a particular class
				if (DSSClassDefs.setObjectClass(ClassName)) {
					F.println("All Elements in Class \"" + ClassName + "\"");
					F.println();
					FDisabled.println("All DISABLED Elements in Class \"" + ClassName + "\"");
					FDisabled.println();
					Globals.setActiveDSSClass( Globals.getDSSClassList().get(Globals.getLastClassReferenced()) );
					for (i = 0; i < Globals.getActiveDSSClass().getElementCount(); i++) {
						Globals.getActiveDSSClass().setActive(i);
						if ((Globals.getActiveDSSClass().getDSSClassType() & DSSClassDefs.BASECLASSMASK) > 0) {
							if (((CktElement) Globals.getActiveDSSObject()).isEnabled()) {
								F.println(Globals.getActiveDSSObject().getName());
							} else {
								FDisabled.println(Globals.getActiveDSSObject().getName());
							}
						} else {
							F.println(Globals.getActiveDSSObject().getName());  // non cktelements
						}
					}
				}
			} else {
				// Default - Just do PD and PC Element in active circuit

				F.println();
				F.println("Elements in Active Circuit: " + ckt.getName());
				F.println();
				F.println("Power Delivery Elements");
				F.println();
				F.println(Utilities.pad("Element", MaxDeviceNameLength + 2) + Utilities.pad(" Bus1", MaxBusNameLength) + Utilities.pad(" Bus2", MaxBusNameLength) + Utilities.pad(" Bus3", MaxBusNameLength) + " ...");
				F.println();


				FDisabled.println();
				FDisabled.println("DISABLED Elements in Active Circuit: " + ckt.getName());
				FDisabled.println();
				FDisabled.println("DISABLED Power Delivery Elements");
				FDisabled.println();
				FDisabled.println(Utilities.pad("DISABLED Element", MaxDeviceNameLength + 2) + Utilities.pad(" Bus1", MaxBusNameLength) + Utilities.pad(" Bus2", MaxBusNameLength) + Utilities.pad(" Bus3", MaxBusNameLength) + " ...");
				FDisabled.println();

				// PD elements first
				for (CktElement pElem : ckt.getPDElements()) {
					if (pElem.isEnabled()) {
						writeElementRecord(F, pElem);
					} else {
						writeElementRecord(FDisabled, pElem);
					}
				}

				F.println();
				F.println("Power Conversion Elements");
				F.println();
				F.println(Utilities.pad("Element", MaxDeviceNameLength + 2) + Utilities.pad(" Bus1", MaxBusNameLength) + Utilities.pad(" Bus2", MaxBusNameLength) + Utilities.pad(" Bus3", MaxBusNameLength) + " ...");
				F.println();

				FDisabled.println();
				FDisabled.println("DISABLED Power Conversion Elements");
				FDisabled.println();
				FDisabled.println(Utilities.pad("DISABLED Element", MaxDeviceNameLength + 2) + Utilities.pad(" Bus1", MaxBusNameLength) + Utilities.pad(" Bus2", MaxBusNameLength) + Utilities.pad(" Bus3", MaxBusNameLength) + " ...");
				FDisabled.println();

				// PC elements  next
				for (CktElement pElem : ckt.getPCElements()) {
					if (pElem.isEnabled()) {
						writeElementRecord(F, pElem);
					} else {
						writeElementRecord(FDisabled, pElem);
					}
				}
			}

			F.close();
			FWriter.close();
			FDisabled.close();
			FDisabledWriter.close();

			Utilities.fireOffEditor(DisabledFileNm);
			Utilities.fireOffEditor(FileNm);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/**
	 * Show bus names and nodes in uses.
	 */
	public static void showBuses(String FileNm) {
		FileWriter FWriter;
		PrintWriter F;
		int i, j;
		Bus pBus;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			setMaxBusNameLength();
			MaxBusNameLength += 2;

			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			F.println();
			F.println("BUSES AND NODES IN ACTIVE CIRCUIT: " + ckt.getName());
			F.println();
			F.println(Utilities.pad("     ", MaxBusNameLength) + "                         Coord                        Number of     Nodes ");
			F.println(Utilities.pad("  Bus", MaxBusNameLength) + "    Base kV             (x, y)            Keep?       Nodes      connected ...");
			F.println();
			for (i = 0; i < ckt.getNumBuses(); i++) {
				F.print(Utilities.pad(Utilities.encloseQuotes(ckt.getBusList().get(i)), MaxBusNameLength) + " ");
				pBus = ckt.getBuses()[i];
				if (pBus.getkVBase() > 0.0) {
					F.print(pBus.getkVBase() * DSSGlobals.SQRT3);
				} else {
					F.print("   NA ");
				}
				F.print("          (");
				if (pBus.isCoordDefined()) {
					F.printf(" %-13.11g, %-13.11g)", pBus.getX(), pBus.getY());
				} else {
					F.print("           NA,            NA )");
				}
				if (pBus.isKeep()) {
					F.print("     Yes  ");
				} else {
					F.print("     No  ");
				}
				F.print("     ");
				F.print(pBus.getNumNodesThisBus());
				F.print("       ");
				for (j = 0; j < pBus.getNumNodesThisBus(); j++) {
					F.print(pBus.getNum(j) + " ");
				}
				F.println();
			}

			F.close();
			FWriter.close();

			Utilities.fireOffEditor(FileNm);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/**
	 * Show values of meter elements.
	 */
	public static void showMeters(String FileNm) {
		FileWriter FWriter;
		PrintWriter F;
		int i, j;
		EnergyMeter MeterClass;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			F.println();
			F.println("ENERGY METER VALUES");
			F.println();
			F.println("Registers:");
			MeterClass = (EnergyMeter) DSSClassDefs.getDSSClass("Energymeter");
			if (MeterClass == null)
				return;
			if (MeterClass.getElementCount() == 0) {
				F.println("No Energymeter Elements Defined.");
			} else {
				EnergyMeterObj pMeter = ckt.getEnergyMeters().get(0);  // write registernames for first meter only
				for (i = 0; i < EnergyMeter.NumEMRegisters; i++)
					F.println("Reg " + String.valueOf(i) + " = " + pMeter.getRegisterNames()[i]);
				F.println();

				if (pMeter != null) {
					F.print("Meter        ");
					for (i = 0; i < EnergyMeter.NumEMRegisters; i++)
						F.print(Utilities.pad("   Reg " + String.valueOf(i), 11));
					F.println();
					F.println();
				}

				for (EnergyMeterObj pElem : ckt.getEnergyMeters()) {
					if (pElem != null) {
						if (pElem.isEnabled()) {
							F.print(Utilities.pad(pElem.getName(), 12));
							for (j = 0; j < EnergyMeter.NumEMRegisters; j++)
								F.print(pElem.getRegisters()[j] + " ");
						}
					}
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

	/**
	 * Show values of generator meter elements
	 */
	public static void showGenMeters(String FileNm) {
		FileWriter FWriter;
		PrintWriter F;
		int i, j;
		Generator GeneratorClass;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			F.println();
			F.println("GENERATOR ENERGY METER VALUES");
			F.println();

			GeneratorObj pGen = ckt.getGenerators().get(0);
			if (pGen != null) {
				GeneratorClass = (Generator) pGen.getParentClass();
				F.print("Generator          ");
				for (i = 0; i < Generator.NumGenRegisters; i++)
					F.print(Utilities.pad(GeneratorClass.getRegisterNames()[i], 11));
				F.println();
				F.println();
			}

			for (GeneratorObj pElem : ckt.getGenerators()) {
				if (pElem != null) {
					if (pElem.isEnabled()) {
						F.print(Utilities.pad(pElem.getName(), 12));
						for (j = 0; j < Generator.NumGenRegisters; j++) {
							F.print(pElem.getRegisters()[j] + " ");
						}
					}
				}
				F.println();
			}

			F.close();
			FWriter.close();

			Utilities.fireOffEditor(FileNm);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/**
	 * Assumes 0 is 1.0 per unit tap.
	 */
	private static int tapPosition(TransformerObj Transformer, int iWind) {
		return (int) Math.round((Transformer.getPresentTap(iWind) - 1.0) / Transformer.getTapIncrement(iWind));
	}

	public static void showRegulatorTaps(String FileNm) {
		FileWriter FWriter;
		PrintWriter F;
//		RegControlObj pReg;
		int iWind;

		try {
			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			F.println();
			F.println("CONTROLLED TRANSFORMER TAP SETTINGS");
			F.println();
			F.println("Name            Tap      Min       Max     Step  Position");
			F.println();

			Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

			for (RegControlObj pReg : ckt.getRegControls()) {
				TransformerObj t = pReg.getTransformer();
				iWind = pReg.getWinding();
				F.print(Utilities.pad(t.getName(), 12) + " ");
				F.printf("%8.5f %8.5f %8.5f %8.5f     %d", t.getPresentTap(iWind), t.getMinTap(iWind), t.getMaxTap(iWind), t.getTapIncrement(iWind), tapPosition(pReg.getTransformer(), iWind));
				F.println();
			};

			F.close();
			FWriter.close();

			Utilities.fireOffEditor(FileNm);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void showMeterZone(String FileNm) {
		FileWriter FWriter;
		PrintWriter F;
		int i;
		EnergyMeterObj pMtr;
		EnergyMeter pMtrClass;
		PDElement PDElem;
		LoadObj LoadElem;
		String ParamName;
		String Param;

		// Singletons
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		try {
			FileNm = Utilities.stripExtension(FileNm);
			ParamName = parser.getNextParam();
			Param = parser.makeString();

			FileNm = FileNm+"_"+Param+".txt";

			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			Globals.setGlobalResult(FileNm);

			pMtrClass = (EnergyMeter) Globals.getDSSClassList().get(Globals.getClassNames().find("energymeter"));

			if (Param.length() > 0) {
				pMtr = (EnergyMeterObj) pMtrClass.find(Param);  // FIXME make generic
				if (pMtr == null) {
					Globals.doSimpleMsg("EnergyMeter \"" + Param + "\" not found.", 220);
				} else {
					if (pMtr.getBranchList() != null) {
						F.println("Branches and Load in Zone for EnergyMeter " + Param);
						F.println();

						PDElem = (PDElement) pMtr.getBranchList().getFirst();
						while (PDElem != null) {
							for (i = 0; i < pMtr.getBranchList().getLevel(); i++)
								F.print(TABCHAR);
							//F.print(pMtr.getBranchList().getLevel() +" ");
							F.print(PDElem.getParentClass().getName() + "." + PDElem.getName());
							CktTreeNode pb = pMtr.getBranchList().getPresentBranch();
							if (pb.isIsParallel())
								F.print("(PARALLEL:" + ((CktElement) pb.getLoopLineObj()).getName()+")");
							if (pb.isIsLoopedHere())
								F.print("(LOOP:" + ((CktElement) pb.getLoopLineObj()).getParentClass().getName()+"."+((CktElement) pb.getLoopLineObj()).getName()+")");

							if (PDElem.getSensorObj() != null) {
								F.printf(" (Sensor: %s.%s) ", PDElem.getSensorObj().getParentClass().getName(), PDElem.getSensorObj().getName());
							} else {
								F.print(" (Sensor: NIL)");
							}
							F.println();
							LoadElem = (LoadObj) pMtr.getBranchList().getFirstObject();
							while (LoadElem != null) {
								for (i = 0; i < pMtr.getBranchList().getLevel() + 1; i++)
									F.print(TABCHAR);
								F.print(LoadElem.getParentClass().getName() + "." + LoadElem.getName());
								if (LoadElem.getSensorObj() != null) {
									F.printf(" (Sensor: %s.%s) ", LoadElem.getSensorObj().getParentClass().getName(), LoadElem.getSensorObj().getName());
								} else {
									F.print(" (Sensor: NIL)");
								}
								F.println();
								LoadElem = (LoadObj) pMtr.getBranchList().getNextObject();
							}
							PDElem = (PDElement) pMtr.getBranchList().GoForward();
						}
					}
				}
			} else {
				Globals.doSimpleMsg("Meter Name Not Specified."+ DSSGlobals.CRLF + parser.getCmdString(), 221);
			}

			F.close();
			FWriter.close();
		} catch (IOException e) {
			// TODO: handle exception
		} finally {
			ParamName = parser.getNextParam();
			Param = parser.makeString();

			switch (Param.length()) {
			case 0:
				Utilities.fireOffEditor(FileNm);
				break;
			default:
				Globals.getDSSForms().showTreeView(FileNm);
				break;
			}
		}
	}

	public static void showOverloads(String FileNm) {
		FileWriter FWriter;
		PrintWriter F;
		Complex[] cBuffer;  // Allocate to max total conductors
		int NCond, i, j, k;
//		PDElement PDElem;
		Complex[] Iph = new Complex[3];
		Complex[] I012 = new Complex[3];
		double I0, I1, I2, Cmag, Cmax;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		setMaxDeviceNameLength();

		try {
			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			/* Allocate c_Buffer big enough for largest circuit element */
			cBuffer = new Complex[Utilities.getMaxCktElementSize()];

			/* Sequence Currents */
			F.println();
			F.println("Power Delivery Element Overload Report");
			F.println();
			F.println("SYMMETRICAL COMPONENT CURRENTS BY CIRCUIT ELEMENT ");
			F.println();
			F.println("Element                      Term    I1      I2    %I2/I1    I0    %I0/I1 %Normal   %Emergency");
			F.println();

			// PD elements
			for (PDElement PDElem : ckt.getPDElements()) {
				if (PDElem.isEnabled())
					if ((DSSClassDefs.CLASSMASK & PDElem.getDSSObjType()) != DSSClassDefs.CAP_ELEMENT) {  // Ignore capacitors
						NCond = PDElem.getNConds();
						PDElem.getCurrents(cBuffer);

						for (j = 0; j < 1; j++) {  // Check only terminal 1 for overloads
							if (PDElem.getNPhases() >= 3) {
								Cmax = 0.0;
								for (i = 0; i < 3; i++) {
									k = (j - 1) * NCond + i;
									Iph[i] = cBuffer[k];
									Cmag = Iph[i].abs();
									if (Cmag > Cmax)
										Cmax = Cmag;
								}
								MathUtil.phase2SymComp(Iph, I012);
								I0 = I012[0].abs();
								I1 = I012[1].abs();
								I2 = I012[2].abs();
							} else {
								I0 = 0.0;
								I1 = cBuffer[1 + (j - 1) * NCond].abs();
								I2 = 0.0;
								Cmax = I1;
							}

							if ((PDElem.getNormAmps() > 0.0) || (PDElem.getEmergAmps() > 0.0)) {
								if ((Cmax > PDElem.getNormAmps()) || (Cmax > PDElem.getEmergAmps())) {
									F.print(Utilities.pad(Utilities.fullName(PDElem), MaxDeviceNameLength + 2) + j);
									F.print(I1);
									F.print(I2);
									if (I1 > 0.0) {
										F.print(100.0 * I2 / I1);
									} else {
										F.print("     0.0");
									}
									F.print(I0);
									if (I1 > 0.0) {
										F.print(100.0 * I0 / I1);
									} else {
										F.print("     0.0");
									}
									if (PDElem.getNormAmps() > 0.0) {
										F.print(Cmax / PDElem.getNormAmps() * 100.0);
									} else {
										F.print("     0.0");
									}
									if (PDElem.getEmergAmps() > 0.0) {
										F.print(Cmax / PDElem.getEmergAmps() * 100.0);
									} else {
										F.print("     0.0");
									}
									F.println();
								}
							}
						}
					}
			}

			F.close();
			FWriter.close();

			Utilities.fireOffEditor(FileNm);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void showUnserved(String FileNm, boolean UE_Only) {
		FileWriter FWriter;
		PrintWriter F;
//		LoadObj PLoad;
		boolean DoIt;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			F.println();
			F.println("UNSERVED  LOAD  REPORT");
			F.println();
			F.println("Load Element        Bus        Load kW  EEN Factor  UE Factor");
			F.println();

			// Load
			for (LoadObj pLoad : ckt.getLoads()) {
				if (pLoad.isEnabled()) {
					DoIt = false;
					if (UE_Only) {
						if (pLoad.getUnserved())
							DoIt = true;
					} else {
						if (pLoad.getExceedsNormal())
							DoIt = true;
					}

					if (DoIt) {
						F.print(Utilities.pad(pLoad.getName(), 20));
						F.print(Utilities.pad(pLoad.getBus(1), 10));
						F.print(pLoad.getkWBase());
						F.print(pLoad.getEEN_Factor());
						F.print(pLoad.getUE_Factor());
						F.println();
					}
				}
			}

			F.close();
			FWriter.close();

			Utilities.fireOffEditor(FileNm);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void showLosses(String FileNm) {
		FileWriter FWriter;
		PrintWriter F;
//		PDElement PDElem;
//		PCElement PCElem;

		Complex kLosses,
			TotalLosses,
			LineLosses,
			TransLosses,
			TermPower,
			LoadPower;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		setMaxDeviceNameLength();

		try {
			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			/* Sequence Currents */
			F.println();
			F.println("LOSSES REPORT");
			F.println();
			F.println("Power Delivery Element Loss Report");
			F.println();
			F.println("Element                  kW Losses    % of Power   kvar Losses");
			F.println();

			TotalLosses = Complex.ZERO;
			LineLosses  = Complex.ZERO;
			TransLosses = Complex.ZERO;

			// PD elements
			for (PDElement PDElem : ckt.getPDElements()) {
				if (PDElem.isEnabled()) {
					/*if ((DSSClassDefs.CLASSMASK & PDElem.getDSSObjType()) != DSSClassDefs.CAP_ELEMENT) {*/    // Ignore capacitors
					kLosses = PDElem.getLosses().multiply(0.001);   // kW Losses in element
					TotalLosses = TotalLosses.add(kLosses);
					TermPower = PDElem.getPower(1).multiply(0.001);  // Terminal 1 power  TODO Check zero based indexing

					if ((DSSClassDefs.CLASSMASK & PDElem.getDSSObjType()) == DSSClassDefs.XFMR_ELEMENT)
						TransLosses = TransLosses.add(kLosses);
					if ((DSSClassDefs.CLASSMASK & PDElem.getDSSObjType()) == DSSClassDefs.LINE_ELEMENT)
						LineLosses = LineLosses.add(kLosses);

					F.print(Utilities.pad(Utilities.fullName(PDElem), MaxDeviceNameLength + 2));
					F.printf("%10.5f, ", kLosses.getReal());
					if ((TermPower.getReal() > 0.0) && (kLosses.getReal() > 0.0009)) {
						F.print(kLosses.getReal() / Math.abs(TermPower.getReal()) * 100.0);
					} else {
						F.print(Complex.ZERO.getReal());
					}
					F.printf("     %.6g", kLosses.getImaginary());
					F.println();
				}
			}

			F.println();
			F.println(Utilities.pad("LINE LOSSES=", 30) + LineLosses.getReal() + " kW");
			F.println(Utilities.pad("TRANSFORMER LOSSES=", 30) + TransLosses.getReal() + " kW");
			F.println();
			F.println(Utilities.pad("TOTAL LOSSES=", 30) + TotalLosses.getReal() + " kW");

			LoadPower = Complex.ZERO;
			// Sum the total load kW being served in the Ckt Model
			for (PCElement PCElem : ckt.getLoads()) {
				if (PCElem.isEnabled()) {
					LoadPower = LoadPower.add(PCElem.getPower(1));
				}
			}
			LoadPower = LoadPower.multiply(0.001);

			F.println();
			F.println(Utilities.pad("TOTAL LOAD POWER = ", 30) + Math.abs(LoadPower.getReal()) + " kW");
			F.print(Utilities.pad("Percent Losses for Circuit = ", 30));
			if (LoadPower.getReal() != 0.0)
				F.println(Math.abs(TotalLosses.getReal() / LoadPower.getReal()) * 100.0 + " %");

			F.close();
			FWriter.close();

			Utilities.fireOffEditor(FileNm);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void showVariables(String FileNm) {
		FileWriter FWriter;
		PrintWriter F;
		int i;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			/* Sequence Currents */
			F.println();
			F.println("VARIABLES REPORT");
			F.println();
			F.println("Present values of all variables in PC Elements in the circuit.");
			F.println();

			for (PCElement PCElem : ckt.getPCElements()) {
				if (PCElem.isEnabled() && (PCElem.numVariables() > 0)) {
					F.println("ELEMENT: " + PCElem.getParentClass().getName() + "." + PCElem.getName());
					F.println("No. of variables: " + PCElem.numVariables());
					for (i = 0; i < PCElem.numVariables(); i++)
						F.println("  " + PCElem.variableName(i) + " = " + String.format("%-.6g", PCElem.getVariable(i)));
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

	/**
	 * Show isolated buses/branches in present circuit.
	 */
	public static void showIsolated(String FileNm) {
		CktTree Branch_List,
				SubArea;  // Pointers to all circuit elements

		FileWriter FWriter;
		PrintWriter F;
		CktElement TestElem, TestBranch, pElem;

		int i, j;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		// Make sure bus list is built
		if (ckt.isBusNameRedefined()) ckt.reProcessBusDefs();
		/* Initialize all circuit elements to not checked */
		for (CktElement TestElement : ckt.getCktElements()) {
			TestElement.setChecked(false);
			for (i = 0; i < TestElement.getNTerms(); i++) {
				TestElement.getTerminals()[i].setChecked(false);
			}
		}

		// Initialize the checked flag for all buses
		for (j = 0; j < ckt.getNumBuses(); j++)
			ckt.getBuses()[j].setBusChecked(false);

		// Get Started at main voltage source
		TestElem = ckt.getSources().get(0);
		Branch_List = CktTreeImpl.getIsolatedSubArea(TestElem);

		/* Show Report of Elements connected and not connected */
		try {
			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			F.println();
			F.println("ISOLATED CIRCUIT ELEMENT REPORT");
			F.println();
			F.println();
			F.println("***  THE FOLLOWING BUSES HAVE NO CONNECTION TO THE SOURCE ***");
			F.println();

			for (j = 0; j < ckt.getNumBuses(); j++) {
				if (!ckt.getBuses()[j].isBusChecked())
					F.println(Utilities.encloseQuotes(ckt.getBusList().get(j)));
			}

			F.println();
			F.println("***********  THE FOLLOWING SUB NETWORKS ARE ISOLATED ************");
			F.println();

			for (CktElement TestElement : ckt.getCktElements()) {
				if (TestElement.isEnabled())
					if (!TestElement.isChecked())
						if ((TestElement.getDSSObjType() & DSSClassDefs.BASECLASSMASK) == DSSClassDefs.PD_ELEMENT) {
							SubArea = CktTreeImpl.getIsolatedSubArea(TestElement);
							F.println("*** START SUBAREA ***");

							TestBranch = (CktElement) SubArea.getFirst();  // TODO Implement
							while (TestBranch != null) {
								F.println("(" + SubArea.getLevel() + ") " + TestBranch.getParentClass().getName() + "." + TestBranch.getName());
								pElem = (CktElement) SubArea.getFirstObject();
								while (pElem != null) {
									F.println("[SHUNT], " + pElem.getParentClass().getName() + "." + pElem.getName());
									pElem = (CktElement) SubArea.getNextObject();
								}
								TestBranch = (CktElement) SubArea.GoForward();
							}
							SubArea = null;
							F.println();
						}
			}

			F.println();
			F.println("***********  THE FOLLOWING ENABLED ELEMENTS ARE ISOLATED ************");
			F.println();

			/* Mark all controls, energy meters and monitors as checked so they don"t show up */

			for (i = 0; i < ckt.getDSSControls().size(); i++)
				((CktElement) ckt.getDSSControls().get(i)).setChecked(true);
			for (i = 0; i < ckt.getMeterElements().size(); i++)
				((CktElement) ckt.getMeterElements().get(i)).setChecked(true);

			for (CktElement TestElement : ckt.getCktElements()) {
				if (TestElement.isEnabled()) {
					if (!TestElement.isChecked()) {
						F.print("\"" + TestElement.getParentClass().getName() + "." + TestElement.getName() + "\"");
						F.print("  Buses:");
						for (j = 0; j < TestElement.getNTerms(); j++)
							F.print("  \"" + TestElement.getBus(j) + "\"");
						F.println();
					}
				}
			}

			F.println();
			F.println("***  THE FOLLOWING BUSES ARE NOT CONNECTED TO ANY POWER DELIVERY ELEMENT ***");
			F.println();

			for (j = 0; j < ckt.getNumBuses(); j++) {
				if (!ckt.getBuses()[j].isBusChecked())
					F.println(Utilities.encloseQuotes(ckt.getBusList().get(j)));
			}

			F.println();
			F.println("***********  CONNECTED CIRCUIT ELEMENT TREE ************");
			F.println();
			F.println("(Lexical Level) Element name");
			F.println();

			TestBranch = (CktElement) Branch_List.getFirst();  // FIXME Make generic
			while (TestBranch != null) {
				F.println("(" + Branch_List.getLevel() + ") " + TestBranch.getParentClass().getName() + "." + TestBranch.getName());
				TestElem = (CktElement) Branch_List.getFirstObject();
				while (TestElem != null) {
					F.println("[SHUNT], " + TestElem.getParentClass().getName() + "." + TestElem.getName());
					TestElem = (CktElement) Branch_List.getNextObject();
				}
				TestBranch = (CktElement) Branch_List.GoForward();
			}

			Branch_List = null;
			F.close();
			FWriter.close();

			Utilities.fireOffEditor(FileNm);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void showRatings(String FileNm) {
		FileWriter FWriter;
		PrintWriter F;
//		PDElement PDElem;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			F.println("Power Delivery Elements Normal and Emergency (max) Ratings");
			F.println();

			for (PDElement PDElem : ckt.getPDElements()) {
				F.print("\"" + PDElem.getParentClass().getName() + "." + PDElem.getName() + "\" normamps=");
				F.printf("%-.4g,  %-.4g  !Amps", PDElem.getNormAmps(), PDElem.getEmergAmps());
				F.println();

			}
			F.close();
			FWriter.close();

			Utilities.fireOffEditor(FileNm);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/**
	 * Show loops and paralleled branches in meter zones.
	 */
	public static void showLoops(String FileNm) {
		FileWriter FWriter;
		PrintWriter F;
		PDElement PDElem;
		int hMeter;
		EnergyMeterObj pMtr;

		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			F.println("Loops and Paralleled Lines in all EnergyMeter Zones");
			F.println();

			hMeter = Globals.getEnergyMeterClass().getFirst();

			while (hMeter > 0) {

				pMtr = (EnergyMeterObj) Globals.getActiveDSSObject();

				if (pMtr.getBranchList() != null) {

					PDElem = (PDElement) pMtr.getBranchList().getFirst();
					while (PDElem != null) {

						CktTreeNode pb = pMtr.getBranchList().getPresentBranch();
						if (pb.isIsParallel())
							F.println("(" + pMtr.getName() + ") " + PDElem.getParentClass().getName() + "." + PDElem.getName() +": PARALLEL WITH " + ((CktElement) pb.getLoopLineObj()).getParentClass().getName() + "." + ((CktElement) pb.getLoopLineObj()).getName());
						if (pb.isIsLoopedHere())
							F.println("(" + pMtr.getName() + ") " + PDElem.getParentClass().getName() + "." + PDElem.getName() + ": LOOPED TO     " + ((CktElement) pb.getLoopLineObj()).getParentClass().getName() + "." + ((CktElement) pb.getLoopLineObj()).getName());

						PDElem = (PDElement) pMtr.getBranchList().GoForward();
					}
				}

				hMeter = Globals.getEnergyMeterClass().getNext();
			}
			F.close();
			FWriter.close();

			Utilities.fireOffEditor(FileNm);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	private static void topoLevelTabs(PrintWriter F, int nLevel) {
		int nTabs, i;

		nTabs = 30;
		if (nLevel < nTabs) nTabs = nLevel;
		for (i = 0; i < nTabs; i++)
			F.print(TABCHAR);
		if (nLevel > nTabs)
			F.printf("(* %d *)", nLevel);
	}

	public static void showTopology(String FileRoot) {
		FileWriter FWriter;
		PrintWriter F;
		FileWriter FTreeWriter;
		PrintWriter FTree;
		String FileNm, TreeNm;
		PDElement PDElem;
		LoadObj LoadElem;
		CktTree topo;
		int nLoops, nParallel, nLevels, nIsolated, nSwitches;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			FileNm = FileRoot + "TopoSumm.Txt";
			TreeNm = FileRoot + "TopoTree.Txt";

			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);
			F.println("Topology analysis for switch control algorithms");
			F.println();

			FTreeWriter = new FileWriter(TreeNm);
			FTree = new PrintWriter(FTreeWriter);
			FTree.println("Branches and Loads in Circuit " + ckt.getName());
			FTree.println();

			topo = ckt.getTopology();
			nLoops = 0;
			nParallel = 0;
			nLevels = 0;
			nIsolated = 0;
			nSwitches = 0;

			if (topo != null) {
				PDElem = (PDElement) topo.getFirst();
				if (topo.getLevel() > nLevels)
					nLevels = topo.getLevel();
				topoLevelTabs(FTree, topo.getLevel());
				FTree.print(PDElem.getParentClass().getName() + "." + PDElem.getName());
				CktTreeNode pb = topo.getPresentBranch();
				if (pb.isIsParallel()) {
					nParallel += 1;
					FTree.print("(PARALLEL:" + ((CktElement) pb.getLoopLineObj()).getName() + ")");
				}
				if (pb.isIsLoopedHere()) {
					nLoops++;
					FTree.print("(LOOP:" + ((CktElement) pb.getLoopLineObj()).getParentClass().getName()
					+"."+((CktElement) pb.getLoopLineObj()).getName()+")");
				}
				if (PDElem.hasSensorObj()) {
					FTree.printf(" (Sensor: %s.%s) ",
							PDElem.getSensorObj().getParentClass().getName(), PDElem.getSensorObj().getName());
				}
				if (PDElem.hasControl()) {
					FTree.printf(" (Control: %s.%s) ",
							PDElem.getControlElement().getParentClass().getName(), PDElem.getControlElement().getName());
					if ((PDElem.getControlElement().getDSSObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.SWT_CONTROL)
						nSwitches++;
				}
				if (PDElem.hasEnergyMeter())
					FTree.printf(" (Meter: %s) ", PDElem.getMeterObj().getName());
				FTree.println();

				LoadElem = (LoadObj) topo.getFirstObject();
				while (LoadElem != null) {
					topoLevelTabs(FTree, topo.getLevel() + 1);
					FTree.print(LoadElem.getParentClass().getName() + "." + LoadElem.getName());
					if (LoadElem.hasSensorObj())
						FTree.printf(" (Sensor: %s.%s) ",
								LoadElem.getSensorObj().getParentClass().getName(), LoadElem.getSensorObj().getName());
					if (LoadElem.hasControl()) {
						FTree.printf(" (Control: %s.%s) ",
								LoadElem.getControlElement().getParentClass().getName(), LoadElem.getControlElement().getName());
						if ((LoadElem.getControlElement().getDSSObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.SWT_CONTROL)
							nSwitches++;
					}
					if (LoadElem.hasEnergyMeter()) {
						FTree.printf(" (Meter: %s) ", LoadElem.getMeterObj().getName());
						FTree.println();
						LoadElem = (LoadObj) topo.getNextObject();
					}

					PDElem = (PDElement) topo.GoForward();
				}
			}

			for (PDElement PDElemt : ckt.getPDElements()) {
				if (PDElemt.isIsolated()) {
					FTree.printf("Isolated: %s.%s", PDElemt.getParentClass().getName(), PDElemt.getName());
					if (PDElemt.hasSensorObj()) {
						FTree.printf(" (Sensor: %s.%s) ",
								PDElemt.getSensorObj().getParentClass().getName(), PDElemt.getSensorObj().getName());
					}
					if (PDElemt.hasControl()) {
						FTree.printf(" (Control: %s.%s) ",
								PDElemt.getControlElement().getParentClass().getName(), PDElemt.getControlElement().getName());

						if ((PDElemt.getControlElement().getDSSObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.SWT_CONTROL)
							nSwitches++;
					}
					if (PDElemt.hasEnergyMeter()) {
						FTree.printf(" (Meter: %s) ", PDElemt.getMeterObj().getName());
						FTree.println();
						nIsolated += 1;
					}
				}
			}

			nLoops = nLoops / 2;  // TODO, see if parallel lines also counted twice
			F.println(String.format("%d Levels Deep", nLevels));
			F.println(String.format("%d Loops", nLoops));
			F.println(String.format("%d Parallel PD elements", nParallel));
			F.println(String.format("%d Isolated PD components", nIsolated));
			F.println(String.format("%d Controlled Switches", nSwitches));

			F.close();
			FWriter.close();
			FTree.close();
			FTreeWriter.close();

			Utilities.fireOffEditor(FileNm);
			DSSGlobals.getInstance().getDSSForms().showTreeView(TreeNm);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void showLineConstants(String FileNm, double Freq, int Units, double Rho) {
		FileWriter FWriter;
		PrintWriter F;
		FileWriter F2Writer;
		PrintWriter F2;
		int p;
		LineGeometryObj pElem;
		CMatrix Z, YC;
		int i, j;
		double w;
		Complex Zs, Zm,	Z1, Z0;
		double CS, CM;
		double C1, C0;
		Complex YCM;
		double XCM;
		double CCM;  // Common mode capacitance
		String LineCodesFileNm;

		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			F.println("LINE CONSTANTS");
			F.println(String.format("Frequency = %.6g Hz, Earth resistivity = %.6g ohm-m", Freq, Rho));
			F.println("Earth Model = " + Utilities.getEarthModel(Globals.getDefaultEarthModel()));
			F.println();

			LineCodesFileNm = "LineConstantsCode.dss";
			F2Writer = new FileWriter(LineCodesFileNm);
			F2 = new PrintWriter(F2Writer);

			F2.println("!--- OpenDSS Linecodes file generated from Show LINECONSTANTS command");
			F2.println(String.format("!--- Frequency = %.6g Hz, Earth resistivity = %.6g ohm-m", Freq, Rho));
			F2.println("!--- Earth Model = " + Utilities.getEarthModel(Globals.getDefaultEarthModel()));

			LineImpl.setLineGeometryClass( (LineGeometry) Globals.getDSSClassList().get(Globals.getClassNames().find("LineGeometry")) );
			Z = null;
			YC = null;

			Globals.setActiveEarthModel(Globals.getDefaultEarthModel());

			p = LineImpl.getLineGeometryClass().getFirst();
			while (p > 0) {
				pElem = (LineGeometryObj) LineImpl.getLineGeometryClass().getActiveObj();
				Z = null;
				YC = null;

				try {
					// Get impedances per unit length
					pElem.setRhoEarth(Rho);
					Z  = pElem.getZmatrix(Freq, 1.0, Units);
					YC = pElem.getYCmatrix(Freq, 1.0, Units);
				} catch (Exception e) {
					Globals.doSimpleMsg("Error computing line constants for LineGeometry." + pElem.getName() +
							"; Error message: " + e.getMessage(), 9934);
				}

				F.println();
				F.println("--------------------------------------------------");
				F.println("Geometry Code = " + pElem.getName());
				F.println();
				F.println("R MATRIX, ohms per " + LineUnits.lineUnitsStr(Units));
				for (i = 0; i < Z.getNOrder(); i++) {
					for (j = 0; j < i; j++)
						F.printf("%.6g, ", Z.getElement(i, j).getReal());
					F.println();
				}

				F.println();
				F.println("jX MATRIX, ohms per " + LineUnits.lineUnitsStr(Units));
				for (i = 0; i < Z.getNOrder(); i++) {
					for (j = 0; j < i; j++)
						F.printf("%.6g, ", Z.getElement(i, j).getImaginary());
					F.println();
				}

				F.println();
				F.println("Susceptance (jB) MATRIX, S per " + LineUnits.lineUnitsStr(Units));
				for (i = 0; i < YC.getNOrder(); i++) {
					for (j = 0; j < i; j++)
						F.printf("%.6g, ", YC.getElement(i, j).getImaginary());
					F.println();
				}

				w = Freq * DSSGlobals.TwoPi / 1.e3;
				F.println();
				F.println("L MATRIX, mH per " + LineUnits.lineUnitsStr(Units));
				for (i = 0; i < Z.getNOrder(); i++) {
					for (j = 0; j < i; j++)
						F.printf("%.6g, ", Z.getElement(i, j).getImaginary() / w);
					F.println();
				}

				w = Freq * DSSGlobals.TwoPi / 1.e9;
				F.println();
				F.println("C MATRIX, nF per " + LineUnits.lineUnitsStr(Units));
				for (i = 0; i < YC.getNOrder(); i++) {
					for (j = 0; j < i; j++)
						F.printf("%.6g, ", YC.getElement(i, j).getImaginary() / w);
					F.println();
				}

				/* Write DSS LineCode record */
				//F.println();
				//F.println(,"-------------------------------------------------------------------");
				//F.println(,"-------------------DSS Linecode Definition-------------------------");
				//F.println(,"-------------------------------------------------------------------");
				F2.println();

				F2.println(String.format("New Linecode.%s nphases=%d  Units=%s", pElem.getName(), Z.getNOrder(), LineUnits.lineUnitsStr(Units)));

				F2.print("~ Rmatrix=[");
				for (i = 0; i < Z.getNOrder(); i++) {
					for (j = 0; j < i; j++)
						F2.printf("%.6g  ", Z.getElement(i, j).getReal());
					if (i < Z.getNOrder()) F2.print("|");
				}
				F2.println("]");

				F2.print("~ Xmatrix=[");
				for (i = 0; i < Z.getNOrder(); i++) {
					for (j = 0; j < i; j++)
						F2.printf("%.6g  ", Z.getElement(i, j).getImaginary());
					if (i < Z.getNOrder()) F2.print("|");
				}
				F2.println("]");

				w = Freq * DSSGlobals.TwoPi /1.e9;
				F2.print("~ Cmatrix=[");
				for (i = 0; i < YC.getNOrder(); i++) {
					for (j = 0; j < i; j++)
						F2.printf("%.6g  ", YC.getElement(i, j).getImaginary() / w);
					if (i < YC.getNOrder()) F2.print("|");
				}
				F2.println("]");

				/* Add pos- and zero-sequence approximation here
				 * Kron reduce to 3 phases first
				 * Average diagonals and off-diagonals
				 */

				Zs = Complex.ZERO;
				Zm = Complex.ZERO;
				CS = 0.0;
				CM = 0.0;

				if (Z.getNOrder() == 3) {
					F.println();
					F.println("-------------------------------------------------------------------");
					F.println("-------------------Equiv Symmetrical Component --------------------");
					F.println("-------------------------------------------------------------------");
					F.println();
					for (i = 0; i < 3; i++)
						Zs = Zs.add( Z.getElement(i, i) );
					for (i = 0; i < 3; i++)
						for (j = 0; j < i-1; j++)  // TODO Check zero based indexing
							Zm = Zm.add( Z.getElement(i, j) );

					Z1 = Zs.subtract(Zm).divide(3.0);
					Z0 = Zm.multiply(2.0).add(Zs).divide(3.0);
					w = Freq * DSSGlobals.TwoPi / 1000.0;
					F.println();
					F.println("Z1, ohms per " + LineUnits.lineUnitsStr(Units) + String.format(" = %.6g + j %.6g (L1 = %.6g mH) ", Z1.getReal(), Z1.getImaginary(), Z1.getImaginary() / w));
					F.println("Z0, ohms per " + LineUnits.lineUnitsStr(Units) + String.format(" = %.6g + j %.6g (L0 = %.6g mH) ", Z0.getReal(), Z0.getImaginary(), Z0.getImaginary() / w));
					F.println();

					/* Compute Common Mode Series Impedance */
					Z.invert();
					YCM = Complex.ZERO;
					for (i = 0; i < 3; i++)
						for (j = 0; j < 3; j++)
							YCM = YCM.add(Z.getElement(i, j));
					XCM = YCM.invert().getImaginary();

					w = Freq * DSSGlobals.TwoPi /1.e9;
					/* Capacitance */
					for (i = 0; i < 3; i++)
						CS = CS + YC.getElement(i, i).getImaginary();
					for (i = 0; i < 3; i++)
						for (j = 0; j < i - 1; j++)
							CM = CM + YC.getElement(i, j).getImaginary();

					C1 = (CS - CM) / 3.0 / w;   // nF
					C0 = (CS + 2.0 * CM) / 3.0 / w;

					/* Compute Common Mode Shunt Capacitance */
					YCM = Complex.ZERO;
					for (i = 0; i < 3; i++)  // Add up all elements of Z inverse
						for (j = 0; j < 3; j++)
							YCM = YCM.add(YC.getElement(i, j));
					CCM = YCM.getImaginary() / w;

					F.println("C1, nF per " + LineUnits.lineUnitsStr(Units) + String.format(" = %.6g", C1));
					F.println("C0, nF per " + LineUnits.lineUnitsStr(Units) + String.format(" = %.6g", C0));
					F.println();

					w = Freq * DSSGlobals.TwoPi;
					F.println("Surge Impedance:");
					F.println(String.format("  Positive sequence = %.6g ohms", Math.sqrt(Z1.getImaginary() / w / (C1 * 1.0e-9))));
					F.println(String.format("  Zero sequence     = %.6g ohms", Math.sqrt(Z0.getImaginary() / w / (C0 * 1.0e-9))));
					F.println(String.format("  Common Mode       = %.6g ohms", Math.sqrt(XCM / w / (CCM * 1.0e-9))));
					F.println();

					F.println("Propagation Velocity (Percent of speed of light):");
					F.println(String.format("  Positive sequence = %.6g ", 1.0 / (Math.sqrt(Z1.getImaginary() / w * (C1 * 1.0e-9))) / 299792458.0 / LineUnits.toPerMeter(Units) * 100.0));
					F.println(String.format("  Zero sequence     = %.6g ", 1.0 / (Math.sqrt(Z0.getImaginary() / w * (C0 * 1.0e-9))) / 299792458.0 / LineUnits.toPerMeter(Units) * 100.0));
					F.println();
				}

				p = LineImpl.getLineGeometryClass().getNext();
			}
			F.close();
			FWriter.close();
			F2.close();
			F2Writer.close();

			Utilities.fireOffEditor(FileNm);
			Utilities.fireOffEditor(LineCodesFileNm);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void showYPrim(String FileNm) {
		FileWriter FWriter;
		PrintWriter F;
		Complex[] cValues;
		int i, j;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		if (ckt != null) {
			if (ckt.getActiveCktElement() != null) {
				try {
					FWriter = new FileWriter(FileNm);
					F = new PrintWriter(FWriter);

					CktElement ace = ckt.getActiveCktElement();

					F.println("Yprim of active circuit element: " + ace.getParentClass().getName() + "." + ace.getName());
					F.println();

					cValues = ace.getYPrimValues(DSSGlobals.ALL_YPRIM);
					if (cValues != null) {
						F.println();
						F.println("G matrix (conductance), S");
						F.println();

						for (i = 0; i < ace.getYorder(); i++) {
							for (j = 0; j < i; j++)
								F.printf("%13.10g ", cValues[i + (j - 1) * ace.getYorder()].getReal());
							F.println();
						}

						F.println();
						F.println("jB matrix (Susceptance), S") ;
						F.println();

						for (i = 0; i < ace.getYorder(); i++) {
							for (j = 0; j < i; j++)
								F.printf("%13.10g ", cValues[i + (j - 1) * ace.getYorder()].getImaginary());
							F.println();
						}
					} else {
						F.println("Yprim matrix is nil");
					}
					F.close();
					FWriter.close();

					Utilities.fireOffEditor(FileNm);
				} catch (IOException e) {
					// TODO: handle exception
				}
			}
		}
	}

	/**
	 * Shows how to retrieve the system Y in triplet form.
	 */
	public static void showY(String FileNm) {
		FileWriter FWriter;
		PrintWriter F;
		CMatrix hY;
		long nNZ, nBus;
		long i, row, col;
		double re, im;
		long[] ColIdx, RowIdx;
		Complex[] cVals;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		if (ckt == null)
			return;

		hY = ckt.getSolution().getY();
		if (hY == null) {
			DSSGlobals.getInstance().doSimpleMsg("Y Matrix not Built.", 222);
			return;
		}

		// print lower triangle of G and B using new functions
		// this compresses the entries if necessary - no extra work if already solved
//		KLU.factorSparseMatrix(hY);
//		KLU.getNNZ(hY, nNZ);
//		KLU.getSize(hY, nBus);  // we should already know this

		try {
//			ColIdx = new long[nNZ];
//			RowIdx = new long[nNZ];
//			cVals = new Complex[nNZ];
//			KLU.getTripletMatrix(hY, nNZ, RowIdx[0], ColIdx[0], cVals[0]);

			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			F.println("System Y Matrix (Lower Triangle by Columns)");
			F.println();
			F.println("  Row  Col               G               B");
			F.println();

			// shows how to easily traverse the triplet format
//			for (i = 0; i < nNZ - 1; i++) {  // TODO Check zero based indexing
//				col = ColIdx[i] + 1;
//				row = RowIdx[i] + 1;
//				if (row >= col) {
//					re = cVals[i].getReal();
//					im = cVals[i].getImaginary();
//					F.println(String.format("[%4d,%4d] = %13.10g + j%13.10g", row, col, re, im));
//				}
//			}
			F.close();
			FWriter.close();

			Utilities.fireOffEditor(FileNm);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/**
	 * Summary and tree-view to separate files.
	 */
	public static void showNodeCurrentSum(String FileNm) {
		FileWriter FWriter;
		PrintWriter F;
		int i, j;
		int nRef;
		String BName;

//		CktElement pCktElement;
		double[] MaxNodeCurrent = new double[100];
		Complex Ctemp;
		String pctError;
		double dTemp;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		MaxNodeCurrent = null;
		try {
			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			// Zero out the nodal current array
			for (i = 0; i < ckt.getNumNodes(); i++)
				sol.getCurrents()[i] = Complex.ZERO;
			// Make temp storage for max current at node
			MaxNodeCurrent = new double[ckt.getNumNodes() + 1];
			for (i = 0; i < ckt.getNumNodes(); i++)
				MaxNodeCurrent[i] = 0.0;
			// Now Sum in each device current, keep track of the largest current at a node.
			for (CktElement pCktElement : ckt.getCktElements()) {
				if (pCktElement.isEnabled()) {
					pCktElement.computeIterminal();
					for (i = 0; i < pCktElement.getYorder(); i++) {
						Ctemp =  pCktElement.getIterminal()[i];
						nRef  =  pCktElement.getNodeRef()[i];
						sol.getCurrents()[nRef] = sol.getCurrents()[nRef].add(Ctemp);  // Noderef = 0 is OK  TODO Check
						if (Ctemp.abs() > MaxNodeCurrent[nRef])
							MaxNodeCurrent[nRef] = Ctemp.abs();
					}
				}
			}

			// Now write report

			setMaxBusNameLength();
			MaxBusNameLength = MaxBusNameLength + 2;
			F.println();
			F.println("Node Current Mismatch Report");
			F.println();
			F.println();
			F.println(Utilities.pad("Bus,", MaxBusNameLength) + " Node, \"Current Sum (A)" + "%error" + "Max Current (A)\"");

			// Ground Bus
			nRef = 0;
			dTemp = sol.getCurrents()[nRef].abs();
			if ((MaxNodeCurrent[nRef] == 0.0) || (MaxNodeCurrent[nRef] == dTemp)) {
				pctError = String.format("%10.1f", 0.0);
			} else {
				pctError = String.format("%10.6f", dTemp / MaxNodeCurrent[nRef] * 100.0);
			}
			BName = Utilities.pad("\"System Ground\"", MaxBusNameLength);
			F.println(String.format("%s, %2d, %10.5f,       %s, %10.5f", BName, nRef, dTemp, pctError, MaxNodeCurrent[nRef]));

			for (i = 0; i < ckt.getNumBuses(); i++) {
				for (j = 0; j < ckt.getBuses()[i].getNumNodesThisBus(); j++) {
					nRef = ckt.getBuses()[i].getRef(j);
					dTemp = sol.getCurrents()[nRef].abs();
					if ((MaxNodeCurrent[nRef] == 0.0) || (MaxNodeCurrent[nRef] == dTemp)) {
						pctError = String.format("%10.1f", 0.0);
					} else {
						pctError = String.format("%10.6f", dTemp / MaxNodeCurrent[nRef] * 100.0);
					}
					if (j == 0) {
						BName = Utilities.padDots(Utilities.encloseQuotes(ckt.getBusList().get(i)), MaxBusNameLength);
					} else {
						BName = Utilities.pad("\"   -\"", MaxBusNameLength);
					}
					F.println(String.format("%s, %2d, %10.5f,       %s, %10.5f", BName, ckt.getBuses()[i].getNum(j), dTemp, pctError, MaxNodeCurrent[nRef]));
				}
			}
			F.close();
			FWriter.close();

			Utilities.fireOffEditor(FileNm);

			MaxNodeCurrent = null;
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void showkVBaseMismatch(String FileNm) {
		FileWriter FWriter;
		PrintWriter F;

//		LoadObj pLoad;
//		GeneratorObj pGen;
		Bus pBus;
		double BuskV;
		String BusName;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			/* Check Loads */
			if (ckt.getLoads().size() > 0) {
				F.println();
				F.println("!!!  LOAD VOLTAGE BASE MISMATCHES");
				F.println();
			}

			for (LoadObj pLoad : ckt.getLoads()) {
				/* Find Bus To Which Load Connected */
				pBus = ckt.getBuses()[ pLoad.getTerminals()[0].BusRef ];
				BusName = ckt.getBusList().get( pLoad.getTerminals()[0].BusRef );
				if (pBus.getkVBase() != 0.0) {
					if ((pLoad.getNPhases() == 1) && (pLoad.getConnection() == 0)) {
						if (Math.abs(pLoad.getkVLoadBase() - pBus.getkVBase()) > 0.10 * pBus.getkVBase()) {
							F.println(String.format("!!!!! Voltage Base Mismatch, Load.%s.kV=%.6g, Bus %s LN kvBase = %.6g", pLoad.getName(), pLoad.getkVLoadBase(), pLoad.getBus(1), pBus.getkVBase()));
							F.println(String.format("!setkvbase %s kVLN=%.6g", BusName, pLoad.getkVLoadBase()));
							F.println(String.format("!Load.%s.kV=%.6g", pLoad.getName(), pBus.getkVBase()));
						}
					} else {
						BuskV = pBus.getkVBase() * DSSGlobals.SQRT3;
						if (Math.abs(pLoad.getkVLoadBase() - BuskV) > 0.10 * BuskV) {
							F.println(String.format("!!!!! Voltage Base Mismatch, Load.%s.kV=%.6g, Bus %s kvBase = %.6g", pLoad.getName(), pLoad.getkVLoadBase(), pLoad.getBus(1), BuskV));
							F.println(String.format("!setkvbase %s kVLL=%.6g", BusName, pLoad.getkVLoadBase()));
							F.println(String.format("!Load.%s.kV=%.6g", pLoad.getName(), BuskV));
						}
					}
				}
			}


			/* Check Generators */

			if (ckt.getGenerators().size() > 0) {
				F.println();
				F.println("!!!  GENERATOR VOLTAGE BASE MISMATCHES");
				F.println();
			}

			for (GeneratorObj pGen : ckt.getGenerators()) {
				/* Find Bus To Which Generator Connected */
				pBus = ckt.getBuses()[ pGen.getTerminals()[0].BusRef ];
				BusName = ckt.getBusList().get( pGen.getTerminals()[0].BusRef );
				if (pBus.getkVBase() != 0.0) {
					if ((pGen.getNPhases() == 1) && (pGen.getConnection() == 0)) {
						if (Math.abs(pGen.getGenVars().kVGeneratorBase - pBus.getkVBase()) > 0.10 * pBus.getkVBase()) {
							F.println(String.format("!!! Voltage Base Mismatch, Generator.%s.kV=%.6g, Bus %s LN kvBase = %.6g", pGen.getName(), pGen.getGenVars().kVGeneratorBase, pGen.getBus(1), pBus.getkVBase()));
							F.println(String.format("!setkvbase %s kVLN=%.6g", BusName, pGen.getGenVars().kVGeneratorBase));
							F.println(String.format("!Generator.%s.kV=%.6g", pGen.getName(), pBus.getkVBase()));
						}
					} else {
						BuskV = pBus.getkVBase() * DSSGlobals.SQRT3;
						if (Math.abs(pGen.getGenVars().kVGeneratorBase - BuskV) > 0.10 * BuskV) {
							F.println(String.format("!!! Voltage Base Mismatch, Generator.%s.kV=%.6g, Bus %s kvBase = %.6g", pGen.getName(), pGen.getGenVars().kVGeneratorBase, pGen.getBus(1), BuskV));
							F.println(String.format("!setkvbase %s kVLL=%.6g", BusName, pGen.getGenVars().kVGeneratorBase));
							F.println(String.format("!Generator.%s.kV=%.6g", pGen.getName(), BuskV));
						}
					}
				}
			}
			F.close();
			FWriter.close();

			Utilities.fireOffEditor(FileNm);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void showDeltaV(String FileNm) {
		FileWriter FWriter;
		PrintWriter F;
//		CktElement pElem;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			FWriter = new FileWriter(FileNm);
			F = new PrintWriter(FWriter);

			setMaxDeviceNameLength();

			F.println();
			F.println("VOLTAGES ACROSS CIRCUIT ELEMENTS WITH 2 TERMINALS");
			F.println();
			F.println("Source Elements");
			F.println();
			F.println(Utilities.pad("Element,", MaxDeviceNameLength) + " Conductor,     Volts,   Percent,           kVBase,  Angle");
			F.println();

			// Sources first
			for (CktElement pElem : ckt.getSources()) {
				if (pElem.isEnabled() && (pElem.getNTerms() == 2)) {
					writeElementDeltaVoltages(F, pElem);
					F.println();
				}
			}

			F.println();
			F.println("Power Delivery Elements");
			F.println();
			F.println(Utilities.pad("Element,", MaxDeviceNameLength) + " Conductor,     Volts,   Percent,           kVBase,  Angle");
			F.println();


			// PD elements next
			for (CktElement pElem : ckt.getPDElements()) {
				if (pElem.isEnabled() && (pElem.getNTerms() == 2)) {
					writeElementDeltaVoltages(F, pElem);
					F.println();
				}
			}

			F.println("= = = = = = = = = = = = = = = = = = =  = = = = = = = = = = =  = =");
			F.println();
			F.println("Power Conversion Elements");
			F.println();
			F.println(Utilities.pad("Element,", MaxDeviceNameLength) + " Conductor,     Volts,   Percent,           kVBase,  Angle");
			F.println();

			// PC elements next
			for (CktElement pElem : ckt.getPCElements()) {
				if (pElem.isEnabled() && (pElem.getNTerms() == 2)) {
					writeElementDeltaVoltages(F, pElem);
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

}
