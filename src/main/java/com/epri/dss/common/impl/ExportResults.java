package com.epri.dss.common.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.epri.dss.common.Bus;
import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.conversion.PCElement;
import com.epri.dss.delivery.PDElement;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.MathUtil;

public class ExportResults {

	private ExportResults() {
	}

	/**
	 * Export symmetrical component bus voltages.
	 */
	public static void exportSeqVoltages(String FileNm) {

		FileWriter F;
		PrintWriter FPrinter;
		int i, j;
		int nref;
		Complex[] Vph = new Complex[3];
		Complex[] V012 = new Complex[3];

		double V0, V1, V2, Vpu, V2V1, V0V1;
		Complex Vresidual;
		double V_NEMA;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		try {
			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);

			FPrinter.println("Bus,  V1,  p.u.,Base kV, V2, %V2/V1, V0, %V0/V1, Vresidual, %NEMA");
			for (i = 0; i < ckt.getNumBuses(); i++) {

				if (ckt.getBuses()[i].getNumNodesThisBus() < 3) {
					V0 = 0.0;
					V2 = 0.0;
					V_NEMA = 0.0;
					if ((ckt.getBuses()[i].getNumNodesThisBus() == 1) && ckt.isPositiveSequence()) {
						// first node
						nref = ckt.getBuses()[i].getRef(0);  // TODO Check zero based indexing
						Vph[0] = ckt.getSolution().getNodeV()[nref];
						V1 = Vph[0].abs();
					} else {
						V1 = 0.0;
					}
				} else {
					Bus bus = ckt.getBuses()[i];
					for (j = 1; j < 4; j++) {
						// first nodes named  1, 2, 3  TODO Check zero based indexing
						Vph[j] = sol.getNodeV()[ bus.getRef(bus.findIdx(j)) ];
					}

					MathUtil.phase2SymComp(Vph, V012);

					V0 = V012[0].abs();
					V1 = V012[1].abs();
					V2 = V012[2].abs();

					V_NEMA = MathUtil.pctNemaUnbalance(Vph);
				}

				if (ckt.getBuses()[i].getkVBase() != 0.0) {
					Vpu = 0.001 * V1 / ckt.getBuses()[i].getkVBase();
				} else {
					Vpu = 0.0;
				}

				if (V1 > 0.0) {
					V2V1 = 100.0 * V2 / V1;
					V0V1 = 100.0 * V0 / V1;
				} else {
					V2V1 = 0.0;
					V0V1 = 0.0;
				}

				Vresidual = Complex.ZERO;
				for (j = 0; j < ckt.getBuses()[i].getNumNodesThisBus(); j++)
					Vresidual = Vresidual.add( sol.getNodeV()[ ckt.getBuses()[i].getRef(j) ]);

				FPrinter.printf("\"%s\", %10.6g, %9.5g, %8.2f, %10.6g, %8.4g, %10.6g, %8.4g, %10.6g, %8.4g",
						ckt.getBusList().get(i), V1, Vpu, (ckt.getBuses()[i].getkVBase() * DSSGlobals.SQRT3), V2, V2V1, V0, V0V1, Vresidual.abs(), V_NEMA);

			}

			DSSGlobals.getInstance().setGlobalResult(FileNm);

			FPrinter.close();
			F.close();
		} catch (IOException e) {
			// FIXME: handle exception
		}
	}

	/**
	 * Export symmetrical component bus voltages.
	 */
	public static void exportVoltages(String FileNm) {
		int MaxNumNodes;
		FileWriter F;
		PrintWriter FPrinter;
		int i, j, jj;
		String BusName;
		Complex Volts;
		int nref;
		int NodeIdx = 0;
		double Vmag, Vpu;
		Bus bus;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		/* Find max nodes at a bus */
		MaxNumNodes = 0;
		for (i = 0; i < ckt.getNumBuses(); i++)
			MaxNumNodes = Math.max(MaxNumNodes, ckt.getBuses()[i].getNumNodesThisBus());

		try {
			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);

			FPrinter.print("Bus, BasekV");
			for (i = 0; i < MaxNumNodes; i++)
				FPrinter.printf(", Node%d, Magnitude%d, Angle%d, pu%d", i, i, i, i);
			FPrinter.println();

			for (i = 0; i < ckt.getNumBuses(); i++) {
				BusName = ckt.getBusList().get(i);
				FPrinter.printf("\"%s\", %.5g", BusName, ckt.getBuses()[i].getkVBase() * DSSGlobals.SQRT3);

				jj = 0;
				bus = ckt.getBuses()[i];
				for (j = 0; j < bus.getNumNodesThisBus(); j++) {
					while (NodeIdx <= 0) {  // TODO Check zero based indexing
						NodeIdx = bus.findIdx(jj);     // Try to find nodes in order
						jj += 1;
					}
					nref = bus.getRef(NodeIdx);
					Volts = sol.getNodeV()[nref];
					Vmag = Volts.abs();
					if (bus.getkVBase() != 0.0) {
						Vpu = 0.001 * Vmag / bus.getkVBase();
					} else {
						Vpu = 0.0;
					}

					FPrinter.printf(", %d, %10.6g, %6.1f, %9.5g",
							bus.getNum(NodeIdx), Vmag, Volts.degArg(), Vpu);
				}
				/* Zero fill row */
				for (j = ckt.getBuses()[i].getNumNodesThisBus(); j < MaxNumNodes + 1; j++)  // TODO Check zero based indexing
					FPrinter.print(", 0, 0, 0, 0");
				FPrinter.println();
			}

			DSSGlobals.getInstance().setGlobalResult(FileNm);

			FPrinter.close();
			F.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	private static void calcAndWriteSeqCurrents(PrintWriter FPrinter, int j, CktElement pElem, Complex[] cBuffer, boolean DoRatings) {

		double I0, I1, I2, I2I1, I0I1, iNormal, iEmerg;
		int i, k, NCond;
		Complex[] Iph = new Complex[3];
		Complex[] I012 = new Complex[3];
		Complex Iresidual;
		double I_NEMA;

		NCond = pElem.getNConds();
		if (pElem.getNPhases() >= 3) {

			for (i = 0; i < 3; i++) {  // TODO Check one based indexing
				k = (j - 1) * NCond + i;
				Iph[i] = cBuffer[k];
			}

			MathUtil.phase2SymComp(Iph, I012);
			I0 = I012[0].abs();
			I1 = I012[1].abs();
			I2 = I012[2].abs();

			I_NEMA = MathUtil.pctNemaUnbalance(Iph);

		} else {
			I0 = 0.0;
			I1 = 0.0;
			I2 = 0.0;
			I_NEMA = 0.0;
			if (DSSGlobals.getInstance().getActiveCircuit().isPositiveSequence())
				I1 = Iph[0].abs();  // use phase 1 only
		}

		if (I1 > 0.0) {
			I2I1 = 100.0 * I2/I1;
			I0I1 = 100.0 * I0/I1;
		} else {
			I2I1 = 0.0;
			I0I1 = 0.0;
		}

		if (DoRatings && (j == 0)) {  // Only for 1st Terminal   TODO Check zero based indexing
			iNormal = ((PDElement) pElem).getNormAmps();
			if (iNormal > 0.0) iNormal = I1 / iNormal * 100.0;
			iEmerg = ((PDElement) pElem).getEmergAmps();
			if (iEmerg > 0.0) iEmerg = I1 / iEmerg * 100.0;
		} else {
			iNormal = 0.0;
			iEmerg = 0.0;
		}

		Iresidual = Complex.ZERO;
		for (i = 0; i < NCond; i++)
			Iresidual = Iresidual.add(cBuffer[i]);

		FPrinter.printf("\"%s\", %3d, %10.6g, %8.4g, %8.4g, %10.6g, %8.4g, %10.6g, %8.4g, %10.6g, %8.4g",
			(pElem.getDSSClassName() + "." + pElem.getName()), j, I1, iNormal, iEmerg, I2, I2I1, I0, I0I1, Iresidual.abs(), I_NEMA);
	}

	public static void exportSeqCurrents(String FileNm) {
		FileWriter F;
		PrintWriter FPrinter;
		int j;
//		CktElement pElem;
//		PDElement PDElem;
//		PCElement PCElem;
		Complex[] cBuffer;  // Allocate to max total conductors

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);

			/* Sequence Currents */
			FPrinter.println("Element, Terminal,  I1, %Normal, %Emergency, I2, %I2/I1, I0, %I0/I1, Iresidual, %NEMA");

			/* Allocate cBuffer big enough for largest circuit element */
			cBuffer = new Complex[Utilities.getMaxCktElementSize()];

			// Sources first
			for (CktElement pElem : ckt.getSources()) {
				if (pElem.isEnabled()) {
					pElem.getCurrents(cBuffer);
					for (j = 0; j < pElem.getNTerms(); j++)
						calcAndWriteSeqCurrents(FPrinter, j, pElem, cBuffer, false);
				}
			}


			// PD elements next
			for (PDElement PDElem : ckt.getPDElements()) {
				if (PDElem.isEnabled())
					PDElem.getCurrents(cBuffer);
				for (j = 0; j < PDElem.getNTerms(); j++)
					calcAndWriteSeqCurrents(FPrinter, j, PDElem, cBuffer, true);
			}

			// PC elements next
			for (PCElement PCElem : ckt.getPCElements()) {
				if (PCElem.isEnabled()) {
					PCElem.getCurrents(cBuffer);
					for (j = 0; j < PCElem.getNTerms(); j++)
						calcAndWriteSeqCurrents(FPrinter, j, PCElem, cBuffer, false);
				}
			}

			// Faults next
			for (CktElement pElem : ckt.getFaults()) {
				if (pElem.isEnabled())
					pElem.getCurrents(cBuffer);
				for (j = 0; j < pElem.getNTerms(); j++)
					calcAndWriteSeqCurrents(FPrinter, j, pElem, cBuffer, false);
			}

			DSSGlobals.getInstance().setGlobalResult(FileNm);

			FPrinter.close();
			F.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	private static void calcAndWriteCurrents(PrintWriter FPrinter, CktElement pElem, Complex[] cBuffer, int CondWidth, int TermWidth) {
		int i, j, k;
		Complex Iresid;

		k = 0;
		FPrinter.printf("%s", pElem.getDSSClassName()+"."+pElem.getName());
		for (j = 0; j < pElem.getNTerms(); j++) {
			Iresid = Complex.ZERO;
			for (i = 0; i < pElem.getNConds(); i++) {
				k += 1;
				FPrinter.printf(", %10.6g, %8.2f", cBuffer[k].abs(), cBuffer[k].degArg());
				Iresid = Iresid.add(cBuffer[k]);
			}
			for (i = pElem.getNConds(); i < CondWidth; i++)  // TODO Check zero based indexing
				FPrinter.printf(", %10.6g, %8.2f", 0.0, 0.0);
			FPrinter.printf(", %10.6g, %8.2f", Iresid.abs(), Iresid.degArg());
		}

		/* Filler if no. terms less than termwidth */
		for (j = pElem.getNTerms(); j < TermWidth; j++)
			for (i = 0; i < CondWidth + 1; i++)
				FPrinter.printf(", %10.6g, %8.2f", 0.0, 0.0);
		FPrinter.println();
	}

	private static void calcAndWriteMaxCurrents(PrintWriter FPrinter, PDElement pElem, Complex[] cBuffer) {
		int i;
		double Currmag, MaxCurrent;
		Complex LocalPower;

		FPrinter.printf("%s.%s", pElem.getDSSClassName(), pElem.getName());
		MaxCurrent = 0.0;
		for (i = 0; i < pElem.getNPhases(); i++) {
			Currmag = cBuffer[i].abs();
			if (Currmag  > MaxCurrent)
				MaxCurrent = Currmag;
		}

		LocalPower = pElem.getPower(0).multiply(0.001);  // TODO Check zero based indexing
		if ((pElem.getNormAmps() == 0.0) || (pElem.getEmergAmps() == 0.0)) {
			FPrinter.printf(", %10.6g, %8.2f, %8.2f", MaxCurrent, 0.0, 0.0);
		} else {
			FPrinter.printf(", %10.6g, %8.2f, %8.2f", MaxCurrent, MaxCurrent / pElem.getNormAmps() * 100.0, MaxCurrent / pElem.getEmergAmps() * 100.0);
		}
		FPrinter.printf(", %10.6g, %10.6g, %d, %d, %d", LocalPower.getReal(), LocalPower.getImaginary(), pElem.getNumCustomers(), pElem.getTotalCustomers(), pElem.getNPhases());
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		FPrinter.printf(", %-.3g ", ckt.getBuses()[ ckt.getMapNodeToBus()[pElem.getNodeRef()[0]].BusRef ].getkVBase());
		FPrinter.println();
	}

	public static void exportCurrents(String FileNm) {
		FileWriter F;
		PrintWriter FPrinter;
		Complex[] cBuffer;
//		CktElement pElem;
		int MaxCond, MaxTerm;
		int i, j;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);

			cBuffer = new Complex[Utilities.getMaxCktElementSize()];

			/* Calculate the width of the file */
			MaxCond = 1;
			MaxTerm = 2;
			for (CktElement pElem : ckt.getCktElements()) {
				if (pElem.getNTerms() > MaxTerm) MaxTerm = pElem.getNTerms();
				if (pElem.getNConds() > MaxCond) MaxCond = pElem.getNConds();
			}

			/* Branch Currents */
			FPrinter.print("Element");
			for (i = 0; i < MaxTerm; i++) {
				for (j = 0; j < MaxCond; j++)
					FPrinter.printf(", I%d_%d, Ang%d_%d", i, j, i, j);
				FPrinter.printf(", Iresid%d, AngResid%d", i, i);
			}
			FPrinter.println();

			// Sources first
			for (CktElement pElem : ckt.getSources()) {
				if (pElem.isEnabled()) {
					pElem.getCurrents(cBuffer);
					calcAndWriteCurrents(FPrinter, pElem, cBuffer, MaxCond, MaxTerm);
				}
			}

			// PD elements next
			for (CktElement pElem : ckt.getPDElements()) {
				if (pElem.isEnabled()) {
					pElem.getCurrents(cBuffer);
					calcAndWriteCurrents(FPrinter, pElem, cBuffer, MaxCond, MaxTerm);
				}
			}

			// Faults
			for (CktElement pElem : ckt.getFaults()) {
				if (pElem.isEnabled()) {
					pElem.getCurrents(cBuffer);
					calcAndWriteCurrents(FPrinter, pElem, cBuffer, MaxCond, MaxTerm);
				}
			}

			// PC elements next
			for (CktElement pElem : ckt.getPCElements()) {
				if (pElem.isEnabled()) {
					pElem.getCurrents(cBuffer);
					calcAndWriteCurrents(FPrinter, pElem, cBuffer, MaxCond, MaxTerm);
				}
			}

			DSSGlobals.getInstance().setGlobalResult(FileNm);

			FPrinter.close();
			F.close();
		} catch (IOException e) {
			cBuffer = null;
			// TODO: handle exception
		}
	}

	/**
	 * Opt = 0: kVA
	 * Opt = 1: MVA
	 */
	public static void exportPowers(String FileNm, int opt) {
		FileWriter F;
		PrintWriter FPrinter;
		int Nterm, j;
//		PDElement PDElem;
//		PCElement PCElem;
		Complex S;
		String Separator = ", ";

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);

			switch (opt) {
			case 1:
				FPrinter.println("Element, Terminal, P(MW), Q(Mvar), P_Normal, Q_Normal, P_Emergency, Q_Emergency");
			default:
				FPrinter.println("Element, Terminal, P(kW), Q(kvar),  P_Normal, Q_Normal, P_Emergency, Q_Emergency");
			}

			// PD elements first
			for (PDElement PDElem : ckt.getPDElements()) {
				if (PDElem.isEnabled()) {
					Nterm = PDElem.getNTerms();

					for (j = 0; j < Nterm; j++) {
						FPrinter.print(Utilities.pad("\""+PDElem.getDSSClassName() + "." + PDElem.getName()+"\"", 24) + Separator + j);

						S = PDElem.getPower(j);
						if (opt == 1) S = S.multiply(0.001);
						FPrinter.print(Separator + S.getReal() * 0.001);
						FPrinter.print(Separator + S.getImaginary() * 0.001);
						if (j == 0) {
							S = PDElem.getExcessKVANorm(0);  // TODO Check zero based indexing
							if (opt == 1) S = S.multiply(0.001);
							FPrinter.print(Separator + Math.abs(S.getReal()));
							FPrinter.print(Separator + Math.abs(S.getImaginary()));
							S = PDElem.getExcessKVAEmerg(0);  // TODO Check zero based indexing
							if (opt == 1) S = S.multiply(0.001);
							FPrinter.print(Separator + Math.abs(S.getReal()));
							FPrinter.print(Separator + Math.abs(S.getImaginary()));
						}
						FPrinter.println();
					}
				}
			}

				// PC elements next
			for (PCElement PCElem : ckt.getPCElements()) {
				if (PCElem.isEnabled()) {
					Nterm = PCElem.getNTerms();

					for (j = 0; j < Nterm; j++) {
						FPrinter.print(Utilities.pad("\""+PCElem.getDSSClassName() + "." + PCElem.getName()+"\"", 24) + Separator + j);
						S = PCElem.getPower(j);
						if (opt == 1) S = S.multiply(0.001);
						FPrinter.print(Separator + S.getReal() * 0.001);
						FPrinter.print(Separator + S.getImaginary() * 0.001);
						FPrinter.println();
					}
				}
			}

			DSSGlobals.getInstance().setGlobalResult(FileNm);

			FPrinter.close();
			F.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void exportLosses(String FileNm) {

	}

	public static void exportEstimation(String FileNm) {

	}

	public static void exportPbyphase(String FileNm, int opt) {

	}

	public static void exportSeqPowers(String FileNm, int opt) {

	}

	public static void exportFaultStudy(String FileNm) {

	}

	public static void exportMeters(String FileNm) {

	}

	public static void exportGenMeters(String FileNm) {

	}

	public static void exportLoads(String FileNm) {

	}

	public static void exportCapacity(String FileNm) {

	}

	public static void exportOverloads(String FileNm) {

	}

	public static void exportUnserved(String FileNm, boolean UE_Only) {

	}

	public static void exportYprim(String FileNm) {

	}

	public static void exportY(String FileNm) {

	}

	public static void exportSeqZ(String FileNm) {

	}

	public static void exportBusCoords(String FileNm) {

	}

	public static void exportUUIDs(String FileNm) {

	}

	public static void exportCounts(String FileNm) {

	}

	public static void exportSummary(String FileNm) {

	}

	public static void exportCDPSM_UnBal(String fileName) {
		// TODO Auto-generated method stub

	}

	public static void exportCDPSM_UnBal(String fileName, boolean b) {
		// TODO Auto-generated method stub

	}

	public static void exportCDPSM_Bal(String fileName) {
		// TODO Auto-generated method stub

	}

}
