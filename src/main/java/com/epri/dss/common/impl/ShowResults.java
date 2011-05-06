package com.epri.dss.common.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.epri.dss.common.Bus;
import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.DSSClass;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.conversion.PCElement;
import com.epri.dss.delivery.PDElement;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.Polar;
import com.epri.dss.shared.impl.CMatrixImpl;
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
				default:
					F.println(Utilities.pad("Element", MaxDeviceNameLength + 2) + " Term    P1(kW)   Q1(kvar)       P2         Q2      P0      Q0       P_Norm      Q_Norm     P_Emerg    Q_Emerg");
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
				default:
					F.println(Utilities.pad("  Bus", MaxBusNameLength) + " Phase     kW     +j   kvar         kVA         PF");
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
				default:
					F.println(Utilities.pad("  Bus", MaxBusNameLength) + " Phase     kW   +j  kvar         kVA         PF");
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
			case 2:
				S = Vph[0].multiply( Iph[0].conjugate() ).add(Vph[1].multiply( Iph[2].conjugate() ));
			default:
				S = V012[1].multiply( I012[1].conjugate() );
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
				default:
					F.println("Element                      Term    P1(kW)   Q1(kvar)         P2         Q2      P0      Q0  ");
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
				default:
					F.println("  Bus       Phase     kW     +j   kvar           kVA           PF");
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
