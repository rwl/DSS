package com.epri.dss.common.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.epri.dss.common.Bus;
import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.DSSClass;
import com.epri.dss.common.Solution;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.conversion.Generator;
import com.epri.dss.conversion.GeneratorObj;
import com.epri.dss.conversion.LoadObj;
import com.epri.dss.conversion.PCElement;
import com.epri.dss.delivery.PDElement;
import com.epri.dss.general.LineCode;
import com.epri.dss.general.LineGeometry;
import com.epri.dss.general.NamedObject;
import com.epri.dss.general.WireData;
import com.epri.dss.general.XfmrCode;
import com.epri.dss.meter.EnergyMeter;
import com.epri.dss.meter.EnergyMeterObj;
import com.epri.dss.meter.SensorObj;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.CMatrixImpl;
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
						NodeIdx = bus.findIdx(jj);  // try to find nodes in order
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
		Complex[] cBuffer;  // allocate to max total conductors

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);

			/* Sequence currents */
			FPrinter.println("Element, Terminal,  I1, %Normal, %Emergency, I2, %I2/I1, I0, %I0/I1, Iresidual, %NEMA");

			/* Allocate cBuffer big enough for largest circuit element */
			cBuffer = new Complex[Utilities.getMaxCktElementSize()];

			// sources first
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

			// faults next
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

			/* Branch currents */
			FPrinter.print("Element");
			for (i = 0; i < MaxTerm; i++) {
				for (j = 0; j < MaxCond; j++)
					FPrinter.printf(", I%d_%d, Ang%d_%d", i, j, i, j);
				FPrinter.printf(", Iresid%d, AngResid%d", i, i);
			}
			FPrinter.println();

			// sources first
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

			// faults
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
		Complex S;
		String Separator = ", ";

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);

			switch (opt) {
			case 1:
				FPrinter.println("Element, Terminal, P(MW), Q(Mvar), P_Normal, Q_Normal, P_Emergency, Q_Emergency");
				break;
			default:
				FPrinter.println("Element, Terminal, P(kW), Q(kvar),  P_Normal, Q_Normal, P_Emergency, Q_Emergency");
				break;
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
		FileWriter F;
		PrintWriter FPrinter;
		double[] S_total = new double[2];
		double[] S_Load = new double[2];
		double[] S_NoLoad = new double[2];

		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);

			FPrinter.println("Element,  Total(W), Total(var),  I2R(W), I2X(var), No-load(W), No-load(var)");
			for (PDElement PDElem : Globals.getActiveCircuit().getPDElements()) {
				if (PDElem.isEnabled()) {
					PDElem.getLosses(S_total, S_Load, S_NoLoad);
					FPrinter.printf("%s.%s, %.7g, %.7g, %.7g, %.7g, %.7g, %.7g", PDElem.getParentClass().getName(), PDElem.getName(), S_total[0], S_total[1], S_Load[0], S_Load[1], S_NoLoad[0], S_NoLoad[1]);
					FPrinter.println();
				}
			}

			Globals.setGlobalResult(FileNm);

			FPrinter.close();
			F.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/**
	 * Export powers by phase.
	 *
	 * opt = 0: kVA
	 * opt = 1: MVA
	 */
	public static void exportPbyphase(String FileNm, int opt) {
		FileWriter F;
		PrintWriter FPrinter;
		int i;
		Complex S;

		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);

			switch (opt) {
			case 1:
				FPrinter.println("Element, NumTerminals, NumConductors, NumPhases, MW1, Mvar1, MW2, Mvar2, MW3, Mvar3, ... ");
				break;
			default:
				FPrinter.println("Element, NumTerminals, NumConductors, NumPhases, kW1, kvar1, kW2, kvar2, kW3, kvar3, ... ");
				break;
			}

			// PD elements first
			for (PDElement PDElem : Globals.getActiveCircuit().getPDElements()) {
				if (PDElem.isEnabled()) {
					PDElem.computeIterminal();
					PDElem.computeVterminal();
					FPrinter.printf("\"%s.%s\", %d, %d, %d", PDElem.getDSSClassName(), PDElem.getName(), PDElem.getNTerms(), PDElem.getNConds(), PDElem.getNPhases());
					for (i = 0; i < PDElem.getYorder(); i++) {
						S = PDElem.getVterminal()[i].multiply( PDElem.getIterminal()[i].conjugate() ).multiply(0.001);
						if (opt == 1) S = S.multiply(0.001);  // convert to MVA
						FPrinter.printf(", %10.3f, %10.3f", S.getReal(), S.getImaginary());
					}
					FPrinter.println();
				}
			}

			// PC elements next
			for (PCElement PCElem : Globals.getActiveCircuit().getPCElements()) {
				if (PCElem.isEnabled()) {
					PCElem.computeIterminal();
					PCElem.computeVterminal();
					FPrinter.printf("\"%s.%s\", %d, %d, %d", PCElem.getDSSClassName(), PCElem.getName(), PCElem.getNTerms(), PCElem.getNConds(), PCElem.getNPhases());
					for (i = 0; i < PCElem.getYorder(); i++) {
						S = PCElem.getVterminal()[i].multiply( PCElem.getIterminal()[i].conjugate() ).multiply(0.001);
						if (opt == 1) S = S.multiply(0.001);  // convert to MVA
						FPrinter.printf(", %10.3f, %10.3f", S.getReal(), S.getImaginary());
					}
					FPrinter.println();
				}
			}

			Globals.setGlobalResult(FileNm);

			FPrinter.close();
			F.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/**
	 * opt = 0: kVA
	 * opt = 1: MVA
	 */
	public static void exportSeqPowers(String FileNm, int opt) {
		FileWriter F;
		PrintWriter FPrinter;
		Complex[] cBuffer;
		int NCond, Nterm, i, j, k;
		Complex Volts;
		Complex S;
		int nref;
		Complex[] Vph = new Complex[3];
		Complex[] V012 = new Complex[3];
		Complex[] Iph = new Complex[3];
		Complex[] I012 = new Complex[3];
		String Separator = ", ";

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);

			cBuffer = new Complex[Utilities.getMaxCktElementSize()];

			switch (opt) {
			case 1:
				FPrinter.println("Element, Terminal, P1(MW), Q1(Mvar), P2, Q2, P0, Q0, P_Normal, Q_Normal, P_Emergency, Q_Emergency");
				break;
			default:
				FPrinter.println("Element, Terminal, P1(kW), Q1(kvar), P2, Q2, P0, Q0, P_Normal, Q_Normal, P_Emergency, Q_Emergency");
				break;
			}

			// PD elements first
			for (PDElement PDElem : ckt.getPDElements()) {
				if (PDElem.isEnabled()) {
					NCond = PDElem.getNConds();
					Nterm = PDElem.getNTerms();
					PDElem.getCurrents(cBuffer);

					for (j = 0; j < Nterm; j++) {
						FPrinter.print(Utilities.pad("\""+PDElem.getDSSClassName() + "." + PDElem.getName()+"\"", 24) + Separator + j);
						for (i = 0; i < PDElem.getNPhases(); i++) {
							k = (j - 1) * NCond + i;
							nref = PDElem.getNodeRef()[k];
							Volts = ckt.getSolution().getNodeV()[nref];
							Iph[i] = cBuffer[k];
							Vph[i] = Volts;
						}
						if (PDElem.getNPhases() >= 3) {
							MathUtil.phase2SymComp(Iph, I012);
							MathUtil.phase2SymComp(Vph, V012);
						} else {
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
						FPrinter.print(Separator + S.getReal() * 0.003);
						FPrinter.print(Separator + S.getImaginary() * 0.003);

						S = V012[2].multiply( I012[2].conjugate() );
						if (opt == 1) S = S.multiply(0.001);
						FPrinter.print(Separator + S.getReal() * 0.003);
						FPrinter.print(Separator + S.getImaginary() * 0.003);

						S = V012[0].multiply( I012[0].conjugate() );
						if (opt == 1) S = S.multiply(0.001);
						FPrinter.print(Separator + S.getReal() * 0.003);
						FPrinter.print(Separator + S.getImaginary() * 0.003);

						if (j == 0) {
							S = PDElem.getExcessKVANorm(0);
							if (opt == 1) S = S.multiply(0.001);
							FPrinter.print(Separator + Math.abs(S.getReal()));
							FPrinter.print(Separator + Math.abs(S.getImaginary()));
							S = PDElem.getExcessKVAEmerg(0);
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
					NCond = PCElem.getNConds();
					Nterm = PCElem.getNTerms();
					PCElem.getCurrents(cBuffer);

					for (j = 0; j < Nterm; j++) {
						FPrinter.print(Utilities.pad("\""+PCElem.getDSSClassName() + "." + PCElem.getName()+"\"", 24) + Separator + j);
						for (i = 0; i < PCElem.getNPhases(); i++) {
							k = (j - 1) * NCond + i;
							nref = PCElem.getNodeRef()[k];
							Volts = ckt.getSolution().getNodeV()[nref];
							Iph[i] = cBuffer[k];
							Vph[i] = Volts;
						}
						if (PCElem.getNPhases() >= 3) {
							MathUtil.phase2SymComp(Iph, I012);
							MathUtil.phase2SymComp(Vph, V012);
						} else {
							V012[0] = Complex.ZERO;
							I012[0] = Complex.ZERO;
							V012[2] = Complex.ZERO;
							I012[2] = Complex.ZERO;
							if (ckt.isPositiveSequence()) {
								V012[1] = Vph[0];
								I012[2] = Iph[0];
							} else {
								V012[1] = Complex.ZERO;
								I012[1] = Complex.ZERO;
							}
						}

						S = V012[1].multiply( I012[1].conjugate() );
						if (opt == 1) S = S.multiply(0.001);
						FPrinter.print(Separator + S.getReal() * 0.003);
						FPrinter.print(Separator + S.getImaginary() * 0.003);

						S = V012[2].multiply( I012[2].conjugate() );
						if (opt == 1) S = S.multiply(0.001);
						FPrinter.print(Separator + S.getReal() * 0.003);
						FPrinter.print(Separator + S.getImaginary() * 0.003);

						S = V012[0].multiply( I012[0].conjugate() );
						if (opt == 1) S = S.multiply(0.001);
						FPrinter.print(Separator + S.getReal() * 0.003);
						FPrinter.print(Separator + S.getImaginary() * 0.003);

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

	public static void exportFaultStudy(String FileNm) {
		int i, iBus, iphs;
		CMatrix YFault;
		Complex[] Vfault;  // big temp array
		FileWriter F;
		PrintWriter FPrinter;
		Complex GFault;
		final String Separator = ", ";
		double MaxCurr, CurrMag;
		Bus bus;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);

			/* Set source voltage injection currents */

			/* All phase faults */
			FPrinter.println("Bus,  3-Phase,  1-Phase,  L-L");
			for (iBus = 0; iBus < ckt.getNumBuses(); iBus++) {
				/* Bus Norton equivalent current, Isc has been previously computed */
				bus = ckt.getBuses()[iBus];
				FPrinter.print(Utilities.pad(ckt.getBusList().get(iBus), 12));
				MaxCurr = 0.0;
				for (i = 0; i < bus.getNumNodesThisBus(); i++) {
					if (MaxCurr < bus.getBusCurrent()[i].abs())
						MaxCurr = bus.getBusCurrent()[i].abs();
				}
				FPrinter.print(Separator + MaxCurr);

				/* One phase faults */

				/* Solve for fault injection currents */

				YFault = new CMatrixImpl(bus.getNumNodesThisBus());
				Vfault = new Complex[bus.getNumNodesThisBus()];

				/* Build YscTemp */

				GFault = new Complex(10000.0, 0.0);

				MaxCurr = 0.0;

				for (iphs = 0; iphs < bus.getNumNodesThisBus(); iphs++) {
					YFault.copyFrom(bus.getYsc());
					YFault.addElement(iphs, iphs, GFault);

					/* Solve for injection currents */
					YFault.invert();
					YFault.MVMult(Vfault, bus.getBusCurrent());  /* Gets voltage appearing at fault */

					CurrMag = Vfault[iphs].multiply(GFault).abs();
					if (CurrMag > MaxCurr) MaxCurr = CurrMag;

				}
				/* Now, put it in the Css array where it belongs */
				FPrinter.print(Separator + MaxCurr);

				Vfault = null;
				YFault = null;

				/* Node-node faults */

				/* Bus Norton equivalent current, Isc has been previously computed */

				YFault = new CMatrixImpl(bus.getNumNodesThisBus());
				Vfault = new Complex[bus.getNumNodesThisBus()];

				GFault = new Complex(10000.0, 0.0);

				MaxCurr = 0.0;

				for (iphs = 0; iphs < bus.getNumNodesThisBus(); iphs++) {
					YFault.copyFrom(bus.getYsc());
					YFault.addElement(iphs, iphs, GFault);
					YFault.addElement(iphs + 1, iphs + 1, GFault);
					YFault.addElemSym(iphs, iphs + 1, GFault.negate());

					/* Solve for injection currents */
					YFault.invert();
					YFault.MVMult(Vfault, bus.getBusCurrent());  /* Gets voltage appearing at fault */

					CurrMag = Vfault[iphs].subtract( Vfault[iphs + 1] ).multiply(GFault).abs();
					if (CurrMag > MaxCurr) MaxCurr = CurrMag;
				}
				/* Now, put it in the Css array where it belongs */

				FPrinter.print(Separator + MaxCurr);

				Vfault = null;
				YFault = null;

				FPrinter.println();
			}

			DSSGlobals.getInstance().setGlobalResult(FileNm);

			FPrinter.close();
			F.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	private static void zeroTempXArray(double[] TempX) {
		for (int ii = 0; ii < 3; ii++)
			TempX[ii] = 0;
	}
	public static void exportEstimation(String FileNm) {
		FileWriter F;
		PrintWriter FPrinter;
		int i;
		double[] TempX = new double[3];  // temp number buffer

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);

			/* Do the energy meters first */
			FPrinter.println("\"Energy Meters\" ");
			FPrinter.println("\"energyMeter\", \"I1 Target\", \"I2 Target\", \"I3 Target\", \"I1 Calc\", \"I2 Calc\", \"I3 Calc\", \"I1 %Err\", \"I2 %Err\", \"I3 %Err\""/*, \"I1 Factor\", \"I2 Factor\", \"I3 Factor\""*/);

			for (EnergyMeterObj pEnergyMeterObj : ckt.getEnergyMeters()) {
				if (pEnergyMeterObj.isEnabled()) {
					FPrinter.printf("\"Energymeter.%s\"", pEnergyMeterObj.getName());
					/* Sensor currents (target) */
					zeroTempXArray(TempX);
					for (i = 0; i < pEnergyMeterObj.getNPhases(); i++)
						TempX[i] = pEnergyMeterObj.getSensorCurrent()[i];
					for (i = 0; i < 3; i++)
						FPrinter.printf(", %.6g", TempX[i]);
					/* Calculated currents */
					zeroTempXArray(TempX);
					for (i = 0; i < pEnergyMeterObj.getNPhases(); i++)
						TempX[i] = pEnergyMeterObj.getCalculatedCurrent()[i].abs();
					for (i = 0; i < 3; i++)
						FPrinter.printf(", %.6g", TempX[i]);
					/* Percent error */
					for (i = 0; i < pEnergyMeterObj.getNPhases(); i++)
						TempX[i] = (1.0 - TempX[i] / Math.max(0.001, pEnergyMeterObj.getSensorCurrent()[i])) * 100.0;
					for (i = 0; i < 3; i++)
						FPrinter.printf(", %.6g", TempX[i]);

					/****  Not all that useful
					// allocation factors
					zeroTempXArray(TempX);
					for (i = 0; i < pEnergyMeterObj.getNPhases(); i++)
						TempX[i] = pEnergyMeterObj.getPhsAllocationFactor()[i];
					for (i = 0; i < 3; i++)
						FPrinter.printf(" %.6g,", TempX[i]);
					*****/

					FPrinter.println();
				}
			}

			/* Do the sensors next */
			FPrinter.println();
			FPrinter.println("\"Sensors\" ");
			FPrinter.print("\"Sensor\", \"I1 Target\", \"I2 Target\", \"I3 Target\", \"I1 Calc\", \"I2 Calc\", \"I3 Calc\", \"I1 %Err\", \"I2 %Err\", \"I3 %Err\",");
			FPrinter.println(" \"V1 Target\", \"V2 Target\", \"V3 Target\", \"V1 Calc\", \"V2 Calc\", \"V3 Calc\", \"V1 %Err\", \"V2 %Err\", \"V3 %Err\", \"WLS Voltage Err\", \"WLS Current Err\"");

			for (SensorObj pSensorObj : ckt.getSensors()) {
				if (pSensorObj.isEnabled()) {
					FPrinter.printf("\"Sensor.%s\"", pSensorObj.getName());
					/* Sensor currents (target) */
					zeroTempXArray(TempX);
					for (i = 0; i < pSensorObj.getNPhases(); i++)
						TempX[i] = pSensorObj.getSensorCurrent()[i];
					for (i = 0; i < 3; i++)
						FPrinter.printf(", %.6g", TempX[i]);
					/* Calculated currents */
					zeroTempXArray(TempX);
					for (i = 0; i < pSensorObj.getNPhases(); i++)
						TempX[i] = pSensorObj.getCalculatedCurrent()[i].abs();
					for (i = 0; i < 3; i++)
						FPrinter.printf(", %.6g", TempX[i]);
					/* Percent error */
					for (i = 0; i < pSensorObj.getNPhases(); i++)
						TempX[i] = (1.0 - TempX[i] / Math.max(0.001, pSensorObj.getSensorCurrent()[i])) * 100.0;
					for (i = 0; i < 3; i++)
						FPrinter.printf(", %.6g", TempX[i]);
					/* Sensor voltage (target) */
					zeroTempXArray(TempX);
					for (i = 0; i < pSensorObj.getNPhases(); i++)
						TempX[i] = pSensorObj.getSensorVoltage()[i];
					for (i = 0; i < 3; i++)
						FPrinter.printf(", %.6g", TempX[i]);
					/* Calculated voltage */
					zeroTempXArray(TempX);
					for (i = 0; i < pSensorObj.getNPhases(); i++)
						TempX[i] = pSensorObj.getCalculatedVoltage()[i].abs();
					for (i = 0; i < 3; i++)
						FPrinter.printf(", %.6g", TempX[i]);
					/* Percent error */
					for (i = 0; i < pSensorObj.getNPhases(); i++)
						TempX[i] = (1.0 - TempX[i] / Math.max(0.001, pSensorObj.getSensorVoltage()[i])) * 100.0;
					for (i = 0; i < 3; i++)
						FPrinter.printf(", %.6g", TempX[i]);
					/* WLS errors */
					zeroTempXArray(TempX);
					FPrinter.printf(", %.6g, %.6g", pSensorObj.getWLSVoltageError(), pSensorObj.getWLSCurrentError());

					FPrinter.println();
				}
			}

			DSSGlobals.getInstance().setGlobalResult(FileNm);

			FPrinter.close();
			F.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	private static void writeMultipleMeterFiles() {
		FileWriter F;
		PrintWriter FPrinter;
		int i, j;
		EnergyMeter MeterClass;
		String FileNm;
		final String Separator = ", ";

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		MeterClass = ((EnergyMeter) DSSClassDefs.getDSSClass("Energymeter"));
		if (MeterClass == null) return;

		for (EnergyMeterObj pElem : ckt.getEnergyMeters()) {
			if (pElem.isEnabled()) {
				try {
					FileNm = Globals.getDSSDataDirectory() + "EXP_MTR_"+pElem.getName()+".csv";

					if (!new File(FileNm).exists()) {
						F = new FileWriter(FileNm);
						FPrinter = new PrintWriter(F);

						/* Write new header */
						FPrinter.print("Year, LDCurve, Hour, Meter");
						for (i = 0; i < EnergyMeter.NumEMRegisters; i++)
							FPrinter.write(Separator + "\""+ pElem.getRegisterNames()[i]+"\"");
						FPrinter.println();

						FPrinter.close();
						F.close();
					}

					F = new FileWriter(FileNm, true);  // append
					FPrinter = new PrintWriter(F);

					FPrinter.print(ckt.getSolution().getYear() + Separator);
					FPrinter.print(ckt.getLoadDurCurve() + Separator);
					FPrinter.print(ckt.getSolution().getIntHour() + Separator);
					FPrinter.print(Utilities.pad("\""+pElem.getName()+"\"", 14));
					for (j = 0; j < EnergyMeter.NumEMRegisters; j++)
						FPrinter.print(Separator + pElem.getRegisters()[j]);
					FPrinter.println();

					Globals.setGlobalResult(FileNm);

					FPrinter.close();
					F.close();
				} catch (IOException e) {
					// TODO: handle exception
				}
			}
		}
	}

	private static void writeSingleMeterFile(String FileNm) {
		FileWriter F;
		PrintWriter FPrinter;
		int i, j;
//		String TestStr;
		final String Separator = ", ";
		boolean ReWriteFile;

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		try {
			if (new File(FileNm).exists()) {
				// See if it has already been written on
//				F = new FileWriter(FileNm);
//				FPrinter = new PrintWriter(F);
//
//				IF  Not EOF(F)
//				THEN Begin
//					Read(F, TestStr);
//					{See if it likely that the file is OK}
//					IF  CompareText(Copy(TestStr,1,4), 'Year')=0
//					THEN RewriteFile = FALSE       // Assume the file is OK
//					ELSE RewriteFile = TRUE;
//				End
//				ELSE RewriteFile = TRUE;
//
//				CloseFile(F);

				ReWriteFile = false;  // FIXME See if it likely that the file is OK
			} else {
				ReWriteFile = true;
			}

			/* Either open or append the file */
			if (ReWriteFile) {
				F = new FileWriter(FileNm);
				FPrinter = new PrintWriter(F);
				/* Write New Header */
				EnergyMeterObj pElem = ckt.getEnergyMeters().get(0);
				FPrinter.print("Year, LDCurve, Hour, Meter");
				for (i = 0; i < EnergyMeter.NumEMRegisters; i++)
					FPrinter.print(Separator + "\""+ pElem.getRegisterNames()[i]+"\"");
				FPrinter.println();
			} else {
				F = new FileWriter(FileNm, true);
				FPrinter = new PrintWriter(F);
			}

			for (EnergyMeterObj pElem : ckt.getEnergyMeters()) {
				if (pElem.isEnabled()) {
					FPrinter.print(ckt.getSolution().getYear() + Separator);
					FPrinter.print(ckt.getLoadDurCurve() + Separator);
					FPrinter.print(ckt.getSolution().getIntHour() + Separator);
					FPrinter.print(Utilities.pad("\""+pElem.getName()+"\"", 14));
					for (j = 0; j < EnergyMeter.NumEMRegisters; j++)
						FPrinter.printf(Separator + pElem.getRegisters()[j]);
					FPrinter.println();
				}
			}

			Globals.setGlobalResult(FileNm);

			FPrinter.close();
			F.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/**
	 * Export values of meter elements.
	 * These records are appended to an existing file so a running account is
	 * kept for some kinds of simulations.
	 * If switch /m is specified, a separate file is created for each meter
	 * using the meter's name.
	 */
	public static void exportMeters(String FileNm) {
		if (FileNm.substring(0, 2).toLowerCase() == "/m") {
			writeMultipleMeterFiles();
		} else {
			writeSingleMeterFile(FileNm);
		}
	}

	private static void writeMultipleGenMeterFiles() {
		FileWriter F;
		PrintWriter FPrinter;
		int i, j;
		Generator GeneratorClass;
		String FileNm;
		final String Separator = ", ";

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		GeneratorClass = (Generator) DSSClassDefs.getDSSClass("generator");
		if (GeneratorClass == null)
			return;

		for (GeneratorObj pElem : ckt.getGenerators()) {
			if (pElem.isEnabled()) {
				try {
					FileNm = Globals.getDSSDataDirectory() + "EXP_GEN_" + pElem.getName() + ".csv";

					if (!new File(FileNm).exists()) {
						F = new FileWriter(FileNm);
						FPrinter = new PrintWriter(F);
						/* Write new header */
						FPrinter.print("Year, LDCurve, Hour, Generator");
						for (i = 0; i < Generator.NumGenRegisters; i++)
							FPrinter.print(Separator + "\"" + GeneratorClass.getRegisterNames()[i]+"\"");
						FPrinter.println();

						FPrinter.close();
						F.close();
					}

					F = new FileWriter(FileNm, true);  // append
					FPrinter = new PrintWriter(F);
					FPrinter.print(ckt.getSolution().getYear() + Separator);
					FPrinter.print(ckt.getLoadDurCurve() + Separator);
					FPrinter.print(ckt.getSolution().getIntHour() + Separator);
					FPrinter.print(Utilities.pad("\""+pElem.getName()+"\"", 14));
					for (j = 0; j < Generator.NumGenRegisters; j++)
						FPrinter.print(Separator + pElem.getRegisters()[j]);
					FPrinter.println();

					Globals.setGlobalResult(FileNm);

					FPrinter.close();
					F.close();
				} catch (IOException e) {
					// TODO: handle exception
				}
			}
		}
	}

	private static void writeSingleGenMeterFile(String FileNm) {
		FileWriter F;
		PrintWriter FPrinter;
		int i, j;
		Generator GeneratorClass;
//		String TestStr;
		final String Separator = ", ";
		boolean ReWriteFile;

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		GeneratorClass = (Generator) DSSClassDefs.getDSSClass("generator");
		if (GeneratorClass == null)
			return;

		try {
			if (new File(FileNm).exists()) {
				// See if it has already been written on
//				F = new FileWriter(FileNm);
//				FPrinter = new PrintWriter(F);
//
//				IF  Not EOF(F)
//				THEN Begin
//					Read(F, TestStr);
//					{See if it likely that the file is OK}
//					IF  CompareText(Copy(TestStr,1,4), 'Year')=0
//					THEN RewriteFile = FALSE       // Assume the file is OK
//					ELSE RewriteFile = TRUE;
//				End
//				ELSE RewriteFile = TRUE;
//
//				CloseFile(F);

				ReWriteFile = false;  // FIXME See if it likely that the file is OK
			} else {
				ReWriteFile = true;
			}

			/* Either open or append the file */
			if (ReWriteFile) {
				F = new FileWriter(FileNm);
				FPrinter = new PrintWriter(F);
				/* Write new header */
				FPrinter.print("Year, LDCurve, Hour, Generator");
				for (i = 0; i < Generator.NumGenRegisters; i++)
					FPrinter.print(Separator + "\""+ GeneratorClass.getRegisterNames()[i]+"\"");
				FPrinter.println();
			} else {
				F = new FileWriter(FileNm, true);  // append
				FPrinter = new PrintWriter(F);
			}

			for (GeneratorObj pElem : ckt.getGenerators()) {
				if (pElem.isEnabled()) {
					FPrinter.print(ckt.getSolution().getYear() + Separator);
					FPrinter.print(ckt.getLoadDurCurve() + Separator);
					FPrinter.print(ckt.getSolution().getIntHour() + Separator);
					FPrinter.print(Utilities.pad("\""+pElem.getName()+"\"", 14));
					for (j = 0; j < Generator.NumGenRegisters; j++)
						FPrinter.print(Separator + pElem.getRegisters()[j]);
					FPrinter.println();
				}
			}

			Globals.setGlobalResult(FileNm);

			FPrinter.close();
			F.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/**
	 * Export values of generator meter elements.
	 * If switch /m is specified, a separate file is created for each
	 * generator using the generator's name.
	 */
	public static void exportGenMeters(String FileNm) {
		if (FileNm.substring(0, 2).toLowerCase() == "/m") {
			writeMultipleGenMeterFiles();
		} else {
			writeSingleGenMeterFile(FileNm);
		}
	}

	/**
	 * Export loads to view present allocation.
	 */
	public static void exportLoads(String FileNm) {
		FileWriter F;
		PrintWriter FPrinter;
		final String Separator = ", ";

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		try {
			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);
			/* Write header */
			FPrinter.println("Load, Connected KVA, Allocation Factor, Phases, kW, kvar, PF, Model");

			for (LoadObj pElem : ckt.getLoads()) {
				if (pElem.isEnabled()) {
					FPrinter.print(pElem.getName());
					FPrinter.print(Separator + pElem.getConnectedkVA());
					FPrinter.print(Separator + pElem.getkVAAllocationFactor());
					FPrinter.print(Separator + pElem.getNPhases());
					FPrinter.print(Separator + pElem.getkWBase());
					FPrinter.print(Separator + pElem.getKvarBase());
					FPrinter.print(Separator + pElem.getPFNominal());
					FPrinter.print(Separator + pElem.getLoadModel());
				}
				FPrinter.println();
			}

			Globals.setGlobalResult(FileNm);

			FPrinter.close();
			F.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/**
	 * Similar to export currents except does only max of the phases and
	 * compares that to the NormAmps and EmergAmps rating.
	 */
	public static void exportCapacity(String FileNm) {
		FileWriter F;
		PrintWriter FPrinter;
		Complex[] cBuffer;

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		try {
			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);

			cBuffer = new Complex[Utilities.getMaxCktElementSize()];

			FPrinter.println("Name, Imax, %normal, %emergency, kW, kvar, NumCustomers, TotalCustomers, NumPhases, kVBase");

			// PD elements only
			for (PDElement pElem : ckt.getPDElements()) {
				if (pElem.isEnabled()) {
					pElem.getCurrents(cBuffer);
					calcAndWriteMaxCurrents(FPrinter, pElem, cBuffer);
				}
			}

			Globals.setGlobalResult(FileNm);

			FPrinter.close();
			F.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void exportOverloads(String FileNm) {
		FileWriter F;
		PrintWriter FPrinter;
		Complex[] cBuffer;  // allocate to max total conductors
		int NCond, i, j;
		Complex[] Iph = new Complex[3];
		Complex[] I012 = new Complex[3];
		double I0, I1, I2;
		double iNormal, iEmerg, Cmax;
		final String Separator = ", ";

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		try {
			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);

			/* Allocate cBuffer big enough for largest circuit element */
			cBuffer = new Complex[Utilities.getMaxCktElementSize()];

			/* Sequence currents */
			FPrinter.println("Element, Terminal,  I1, %Normal, %Emergency, I2, %I2/I1, I0, %I0/I1");

			// PD elements only
			for (PDElement PDElem : ckt.getPDElements()) {
				if (PDElem.isEnabled()) {
					if ((DSSClassDefs.CLASSMASK & PDElem.getDSSObjType()) != DSSClassDefs.CAP_ELEMENT) {  // ignore caps
						NCond = PDElem.getNConds();
						PDElem.getCurrents(cBuffer);

						for (j = 0; j < 1; j++) {  // only for terminal 1
							Cmax = 0.0;
							for (i = 0; i < Math.min(PDElem.getNPhases(), 3); i++) {  // check only first 3 phases
								Iph[i] = cBuffer[(j - 1) * NCond + i];
								Cmax = Math.max(Cmax, Iph[i].abs());
							}
							if (PDElem.getNPhases() >= 3) {
								// Report symmetrical component currents for
								MathUtil.phase2SymComp(Iph, I012);
								I0 = I012[0].abs();  // Get abs values to report
								I1 = I012[1].abs();
								I2 = I012[2].abs();
							} else {
								// Other than 3-phase
								I0 = 0.0;
								I1 = Iph[0].abs();  // Ambiguous: Report only first phase
								I2 = 0.0;
								Cmax = I1;
							}

							if ((PDElem.getNormAmps() > 0.0) || (PDElem.getEmergAmps() > 0.0)) {
								if ((Cmax > PDElem.getNormAmps()) || (Cmax > PDElem.getEmergAmps())) {
									FPrinter.print(Utilities.pad("\""+PDElem.getDSSClassName() + "." + PDElem.getName()+"\"", 22) + Separator + j);
									FPrinter.print(Separator + I1);
									if (j == 0) {  // Only for 1st Terminal
										iNormal = PDElem.getNormAmps();
										if (iNormal > 0.0) {
											FPrinter.print(Separator + Cmax / iNormal * 100.0);
										} else {
											FPrinter.print(Separator + "     0.0");
										}
										iEmerg = PDElem.getEmergAmps();
										if (iEmerg > 0.0) {
											FPrinter.print(Separator + Cmax / iEmerg * 100.0);
										} else {
											FPrinter.print(Separator + "     0.0");
										}
									} else {
										FPrinter.print(Separator + "       0" + Separator + "       0");
									}
									FPrinter.print(Separator + I2);
									if (I1 > 0.0) {
										FPrinter.print(Separator + 100.0 * I2 / I1);
									} else {
										FPrinter.print(Separator + "0.0");
									}
									FPrinter.print(Separator + I0);
									if (I1 > 0.0) {
										FPrinter.print(Separator + 100.0 * I0 / I1);
									} else {
										FPrinter.print(Separator + "0.0");
									}
									FPrinter.println();
								}
							}
						}
					}
				}
			}

			Globals.setGlobalResult(FileNm);

			FPrinter.close();
			F.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void exportUnserved(String FileNm, boolean UE_Only) {
		FileWriter F;
		PrintWriter FPrinter;
		boolean DoIt;

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		try {
			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);

			FPrinter.println("Load, Bus, kW, EEN_Factor,  UE_Factor");

			// Load
			for (LoadObj pLoad : ckt.getLoads()) {
				if (pLoad.isEnabled()) {
					DoIt = false;
					if (UE_Only) {
						if (pLoad.getUnserved()) DoIt = true;
					} else {
						if (pLoad.getExceedsNormal()) DoIt = true;
					}

					if (DoIt) {
						FPrinter.print(pLoad.getName() + ", ");
						FPrinter.print(pLoad.getBus(1) + ", ");  // TODO Check zero based indexing
						FPrinter.print(pLoad.getkWBase() + ", ");
						FPrinter.print(pLoad.getEEN_Factor() + ", ");
						FPrinter.print(pLoad.getUE_Factor());
						FPrinter.println();
					}
				}
			}

			Globals.setGlobalResult(FileNm);

			FPrinter.close();
			F.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/**
	 * Exports YPrim matrices for all circuit elements.
	 */
	public static void exportYprim(String FileNm) {
		FileWriter F;
		PrintWriter FPrinter;
		int i, j, k;
		Complex[] cValues;
		CktElement cktElem;

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		if (ckt == null) return;

		try {
			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);

			for (k = 0; k < ckt.getNumDevices(); k++) {
				ckt.setActiveCktElement( ckt.getCktElements().get(k) );
				if (ckt.getActiveCktElement().isEnabled()) {
					if ((ckt.getActiveCktElement() instanceof PDElement) || (ckt.getActiveCktElement() instanceof PCElement)) {
						cktElem = ckt.getActiveCktElement();
						FPrinter.println(cktElem.getParentClass().getName() + "." + cktElem.getName());
						cValues = cktElem.getYPrimValues(DSSGlobals.ALL_YPRIM);
						for (i = 0; i < cktElem.getYorder(); i++) {
							for (j = 0; j < cktElem.getYorder(); j++)
								FPrinter.printf("%-13.10g, %-13.10g, ", cValues[i + (j - 1) * cktElem.getYorder()].getReal(), cValues[i + (j - 1) * cktElem.getYorder()].getImaginary());
							FPrinter.println();
						}
					}
				}
			}

			Globals.setGlobalResult(FileNm);

			FPrinter.close();
			F.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/**
	 * Exports system Y matrix in node order.
	 */
	public static void exportY(String FileNm) {
		FileWriter F;
		PrintWriter FPrinter;
		long i, j, p;
		CMatrix Y;
		long nBus = 0, nNZ = 0;
		long[] ColPtr, RowIdx;
		Complex[] cVals;
		double re, im;

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		if (ckt == null) return;
		Y = ckt.getSolution().getY();
		if (Y == null) {
			Globals.doSimpleMsg("Y Matrix not Built.", 222);
			return;
		}
		// this compresses the entries if necessary - no extra work if already solved
//		KLU.factorSparseMatrix(Y);
//		KLU.getNNZ(Y, nNZ);
//		KLU.getSize(Y, nBus);  // we should already know this

		try {
			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);

			ColPtr = new long[(int) (nBus + 1)];
			RowIdx = new long[(int) nNZ];
			cVals  = new Complex[(int) nNZ];
//			KLU.getCompressedMatrix(Y, nBus + 1, nNZ, ColPtr[0], RowIdx[0], cVals[0]);

			/* Write out fully qualified bus names */

			FPrinter.printf("%d, ", ckt.getNumNodes());
			FPrinter.println();
			/*for (i = 0; i < ckt.getNumNodes(); i++) {
				j = ckt.getMapNodeToBus()[i].BusRef;
				FPrinter.printf("%s.%-d, +j,", ckt.getBusList().get(j), ckt.getMapNodeToBus()[i].NodeNum);
			}
			FPrinter.println();*/

			for (i = 0; i < ckt.getNumNodes(); i++) {
				j =  ckt.getMapNodeToBus()[(int) i].BusRef;
				FPrinter.printf("%s.%-d, ", ckt.getBusList().get((int) j), ckt.getMapNodeToBus()[(int) i].NodeNum);
				for (j = 0; j < ckt.getNumNodes(); j++) {
					re = 0.0;
					im = 0.0;
					// search for a non-zero element [i, j]
					for (p = ColPtr[(int) (j - 1)]; p < ColPtr[(int) j] - 1; p++) {
						if (RowIdx[(int) p] + 1 == i) {
							re = cVals[(int) p].getReal();
							im = cVals[(int) p].getImaginary();
						}
					}
					FPrinter.printf("%-13.10g, +j %-13.10g,", re, im);
				}
				FPrinter.println();
			}

			Globals.setGlobalResult(FileNm);

			FPrinter.close();
			F.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/**
	 * Export symmetrical component impedances at each bus.
	 */
	public static void exportSeqZ(String FileNm) {
		FileWriter F;
		PrintWriter FPrinter;
		int i;
		Complex Z1, Z0;
		double X1R1, X0R0;

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		try {
			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);

			FPrinter.println("Bus,  NumNodes, R1, X1, R0, X0, Z1, Z0, \"X1/R1\", \"X0/R0\"");

			for (i = 0; i < ckt.getNumBuses(); i++) {
				Z1 = ckt.getBuses()[i].getZsc1();
				Z0 = ckt.getBuses()[i].getZsc0();
				if (Z1.getReal() != 0.0) {
					X1R1 = Z1.getImaginary() / Z1.getReal();
				} else {
					X1R1 = 1000.0;
				}
				if (Z0.getReal() != 0.0) {
					X0R0 = Z0.getImaginary() / Z0.getReal();
				} else {
					X0R0 = 1000.0;
				}

				FPrinter.printf("\"%s\", %d, %10.6g, %10.6g, %10.6g, %10.6g, %10.6g, %10.6g, %8.4g, %8.4g",
						ckt.getBusList().get(i), ckt.getBuses()[i].getNumNodesThisBus(),
						Z1.getReal(), Z1.getImaginary(), Z0.getReal(), Z0.getImaginary(), Z1.abs(), Z0.abs(), X1R1, X0R0);
				FPrinter.println();
			}

			Globals.setGlobalResult(FileNm);

			FPrinter.close();
			F.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void exportUUIDs(String FileNm) {
		FileWriter F;
		PrintWriter FPrinter;
		LineCode clsCode;
		LineGeometry clsGeom;
		WireData clsWire;
		XfmrCode clsXfmr;
		NamedObject pName;

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		try {
			clsCode = (LineCode)     Globals.getDSSClassList().get(Globals.getClassNames().find("linecode"));
			clsWire = (WireData)     Globals.getDSSClassList().get(Globals.getClassNames().find("wiredata"));
			clsGeom = (LineGeometry) Globals.getDSSClassList().get(Globals.getClassNames().find("linegeometry"));
			clsXfmr = (XfmrCode)     Globals.getDSSClassList().get(Globals.getClassNames().find("xfmrcode"));

			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);

			FPrinter.printf("%s.%s %s", ckt.getDSSClassName(), ckt.getLocalName(), ckt.getID());
			FPrinter.println();

			for (NamedObject pNamed : ckt.getCktElements()) {
				FPrinter.printf("%s.%s %s", pNamed.getDSSClassName(), pNamed.getLocalName(), pNamed.getID());
				FPrinter.println();
			}

			pName = (NamedObject) clsCode.getElementList().getFirst();  // FIXME Make generic
			while (pName != null) {
				FPrinter.printf("%s.%s %s", pName.getDSSClassName(), pName.getLocalName(), pName.getID());
				FPrinter.println();
				pName = (NamedObject) clsCode.getElementList().getNext();
			}

			pName = (NamedObject) clsWire.getElementList().getFirst();  // FIXME Make generic
			while (pName != null) {
				FPrinter.printf("%s.%s %s", pName.getDSSClassName(), pName.getLocalName(), pName.getID());
				FPrinter.println();
				pName = (NamedObject) clsWire.getElementList().getNext();
			}

			pName = (NamedObject) clsGeom.getElementList().getFirst();  // FIXME Make generic
			while (pName != null) {
				FPrinter.printf("%s.%s %s", pName.getDSSClassName(), pName.getLocalName(), pName.getID());
				FPrinter.println();
				pName = (NamedObject) clsGeom.getElementList().getNext();
			}

			pName = (NamedObject) clsXfmr.getElementList().getFirst();  // FIXME Make generic
			while (pName != null) {
				FPrinter.printf("%s.%s %s", pName.getDSSClassName(), pName.getLocalName(), pName.getID());
				FPrinter.println();
				pName = (NamedObject) clsXfmr.getElementList().getNext();
			}

			FPrinter.close();
			F.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void exportCounts(String FileNm) {
		FileWriter F;
		PrintWriter FPrinter;

		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);

			FPrinter.println("Format: DSS Class Name = Instance Count");
			FPrinter.println();
			for (DSSClass cls : Globals.getDSSClassList()) {
				FPrinter.printf("%s = %d", cls.getName(), cls.getElementCount());
				FPrinter.println();
			}

			FPrinter.close();
			F.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void exportSummary(String FileNm) {
		FileWriter F;
		PrintWriter FPrinter;
		Complex cPower, cLosses;

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		try {
			if (new File(FileNm).exists()) {
				F = new FileWriter(FileNm, true);  // append
				FPrinter = new PrintWriter(F);
			} else {
				F = new FileWriter(FileNm);
				FPrinter = new PrintWriter(F);
				// Create and write the header
				FPrinter.print("DateTime, CaseName, ");
				FPrinter.print("Status, Mode, Number, LoadMult, NumDevices, NumBuses, NumNodes");
				FPrinter.print(", Iterations, ControlMode, ControlIterations");
				FPrinter.print(", MostIterationsDone");
				if (ckt != null) {
					if (ckt.isSolved() && !ckt.isBusNameRedefined()) {
						FPrinter.print(", Year, Hour, MaxPuVoltage, MinPuVoltage, TotalMW, TotalMvar");
						FPrinter.print(", kWLosses, pctLosses, kvarLosses, Frequency");
					}
				}
				FPrinter.println();
			}

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			FPrinter.printf("\"%s\", ", sdf.format( cal.getTime() ));
			if (ckt != null) {
				FPrinter.printf("%s, ", ckt.getCaseName());
			} else {
				FPrinter.print("NONE, ");
			}

			if (ckt.isSolved()) {
				FPrinter.print("SOLVED");
			} else {
				FPrinter.print("UnSolved");
			}

			FPrinter.printf(", %s",    Utilities.getSolutionModeID());
			FPrinter.printf(", %d",    ckt.getSolution().getNumberOfTimes());
			FPrinter.printf(", %8.3f", ckt.getLoadMultiplier());
			FPrinter.printf(", %d",    ckt.getNumDevices());
			FPrinter.printf(", %d",    ckt.getNumBuses());
			FPrinter.printf(", %d",    ckt.getNumNodes());
			FPrinter.printf(", %d",    ckt.getSolution().getIteration());
			FPrinter.printf(", %s",    Utilities.getControlModeID());
			FPrinter.printf(", %d",    ckt.getSolution().getControlIteration());
			FPrinter.printf(", %d",    ckt.getSolution().getMostIterationsDone());
			if (ckt != null) {
				if (ckt.isSolved() && !ckt.isBusNameRedefined()) {
					FPrinter.printf(", %d",    ckt.getSolution().getYear());
					FPrinter.printf(", %d",    ckt.getSolution().getIntHour());
					FPrinter.printf(", %-.5g", Utilities.getMaxPUVoltage());
					FPrinter.printf(", %-.5g", Utilities.getMinPUVoltage(true));
					cPower = Utilities.getTotalPowerFromSources().multiply(0.000001);  // MVA
					FPrinter.printf(", %-.6g", cPower.getReal());
					FPrinter.printf(", %-.6g", cPower.getImaginary());
					cLosses = ckt.getLosses().multiply(0.000001);
					if (cPower.getReal() != 0.0) {
						FPrinter.printf(", %-.6g, %-.4g", cLosses.getReal(), cLosses.getReal() / cPower.getReal() * 100.0);
					} else {
						FPrinter.printf("Total Active Losses:   ****** MW, (**** %%)");
					}
					FPrinter.printf(", %-.6g", cLosses.getImaginary());
					FPrinter.printf(", %-g", ckt.getSolution().getFrequency());
				}
			}

			FPrinter.println();

			Globals.setGlobalResult(FileNm);

			FPrinter.close();
			F.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/**
	 * Export bus x, y coordinates.
	 */
	public static void exportBusCoords(String FileNm) {
		FileWriter F;
		PrintWriter FPrinter;
		int i;

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		try {
			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);

			for (i = 0; i < ckt.getNumBuses(); i++) {
				if (ckt.getBuses()[i].isCoordDefined()) {
					FPrinter.printf("%s, %-13.11g, %-13.11g", Utilities.checkForBlanks(ckt.getBusList().get(i)), ckt.getBuses()[i].getX(), ckt.getBuses()[i].getY());
					FPrinter.println();
				}
			}

			Globals.setGlobalResult(FileNm);

			FPrinter.close();
			F.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void exportCDPSM(String fileName, CIMProfileChoice Profile) {
		// TODO Auto-generated method stub

	}

	private static void writeNewLine(PrintWriter F,
			final String CktElementName, double DistFromMeter1, double puV1, double DistFromMeter2, double puV2,
			int ColorCode, int Thickness, int LineType,
			int MarkCenter,
			int CenterMarkerCode, int NodeMarkerCode, int NodeMarkerWidth) {

		F.printf("%s, %.6g, %.6g, %.6g, %.6g,", CktElementName, DistFromMeter1, puV1, DistFromMeter2, puV2);
		F.printf("%d, %d, %d, ", ColorCode, Thickness, LineType);
		F.printf("%d, ", MarkCenter);
		F.printf("%d, %d, %d", CenterMarkerCode,  NodeMarkerCode, NodeMarkerWidth);
		F.println();
	}

	public static void exportProfile(String FileNm, int PhasesToPlot) {
		int iEnergyMeter;
		EnergyMeterObj ActiveEnergyMeter;
		CktElement PresentCktElement;
		Bus Bus1, Bus2;
		double puV1 = 0, puV2 = 0;
		int iphs;
		int iphs2;
		String S;
		FileWriter F;
		PrintWriter FPrinter;
		int Linetype = 0;

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		try {
			F = new FileWriter(FileNm);
			FPrinter = new PrintWriter(F);

			FPrinter.print("Name, Distance1, puV1, Distance2, puV2, Color, Thickness, Linetype, Markcenter, Centercode, NodeCode, NodeWidth,");

			/* New graph created before this routine is entered */
			switch (PhasesToPlot) {
			case DSSGlobals.PROFILELL:
				S  = "L-L Voltage Profile";
				break;
			case DSSGlobals.PROFILELLALL:
				S  = "L-L Voltage Profile";
				break;
			case DSSGlobals.PROFILELLPRI:
				S  = "L-L Voltage Profile";
				break;
			default:
				S  = "L-N Voltage Profile";
				break;
			}

			FPrinter.println("Title=" + S + ", Distance in km");

			iEnergyMeter = Globals.getEnergyMeterClass().getFirst();
			while (iEnergyMeter >= 0) {

				ActiveEnergyMeter = (EnergyMeterObj) Globals.getEnergyMeterClass().getActiveObj();
				/* Go down each branch list and draw a line */
				PresentCktElement = (CktElement) ActiveEnergyMeter.getBranchList().getFirst();
				while (PresentCktElement != null) {
					if (Utilities.isLineElement(PresentCktElement)) {
						Bus1 = ckt.getBuses()[PresentCktElement.getTerminals()[0].BusRef];
						Bus2 = ckt.getBuses()[PresentCktElement.getTerminals()[1].BusRef];
						/* Now determin which phase to plot */
						if ((Bus1.getkVBase() > 0.0) && (Bus2.getkVBase() > 0.0)) {
							switch (PhasesToPlot) {
							/* 3ph only */
							case DSSGlobals.PROFILE3PH:
								if ((PresentCktElement.getNPhases() >= 3) && (Bus1.getkVBase() > 1.0))
									for (iphs = 0; iphs < 3; iphs++) {
										puV1 = ckt.getSolution().getNodeV()[Bus1.getRef(Bus1.findIdx(iphs))].abs() / Bus1.getkVBase() / 1000.0;
										puV2 = ckt.getSolution().getNodeV()[Bus2.getRef(Bus2.findIdx(iphs))].abs() / Bus2.getkVBase() / 1000.0;
										writeNewLine(FPrinter, PresentCktElement.getName(), Bus1.getDistFromMeter(), puV1, Bus2.getDistFromMeter(), puV2,
												iphs, 2, 0, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth());
									}
								break;
							/* Plot all phases present (between 1 and 3) */
							case DSSGlobals.PROFILEALL:
								for (iphs = 0; iphs < 3; iphs++)
									if ((Bus1.findIdx(iphs) >= 0) && (Bus2.findIdx(iphs) >= 0)) {
										if (Bus1.getkVBase() < 1.0) {
											Linetype = 2;
										} else {
											Linetype = 0;
										}
										puV1 = ckt.getSolution().getNodeV()[Bus1.getRef(Bus1.findIdx(iphs))].abs() / Bus1.getkVBase() / 1000.0;
										puV2 = ckt.getSolution().getNodeV()[Bus2.getRef(Bus2.findIdx(iphs))].abs() / Bus2.getkVBase() / 1000.0;
										writeNewLine(FPrinter, PresentCktElement.getName(), Bus1.getDistFromMeter(), puV1, Bus2.getDistFromMeter(), puV2,
												iphs, 2, Linetype, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth());
									}
								break;
							/* Plot all phases present (between 1 and 3) for Primary only */
							case DSSGlobals.PROFILEALLPRI:
								if (Bus1.getkVBase() > 1.0)
									for (iphs = 0; iphs < 3; iphs++)
										if ((Bus1.findIdx(iphs) >= 0) && (Bus2.findIdx(iphs) >= 0)) {
											if (Bus1.getkVBase() < 1.0) {
												Linetype = 2;
											} else {
												Linetype = 0;
											}
											puV1 = ckt.getSolution().getNodeV()[Bus1.getRef(Bus1.findIdx(iphs))].abs() / Bus1.getkVBase() / 1000.0;
		                                    puV2 = ckt.getSolution().getNodeV()[Bus2.getRef(Bus2.findIdx(iphs))].abs() / Bus2.getkVBase() / 1000.0;
		                                    writeNewLine(FPrinter, PresentCktElement.getName(), Bus1.getDistFromMeter(), puV1, Bus2.getDistFromMeter(), puV2,
		                                    		iphs, 2, Linetype, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth());
										}
								break;
							case DSSGlobals.PROFILELL:
								if (PresentCktElement.getNPhases() >= 3)
									for (iphs = 0; iphs < 3; iphs++) {
										iphs2 = iphs + 1;
										if (iphs2 >= 3) iphs2 = 1;  // TODO Check zero based indexing
										if ((Bus1.findIdx(iphs) >= 0) && (Bus2.findIdx(iphs) >= 0) &&
												(Bus1.findIdx(iphs2) >= 0) && (Bus2.findIdx(iphs2) >= 0)) {
											if (Bus1.getkVBase() < 1.0) {
												Linetype = 2;
											} else {
												Linetype = 0;
											}
											SolutionObj sol = ckt.getSolution();
											puV1 = sol.getNodeV()[Bus1.getRef(Bus1.findIdx(iphs))].subtract( sol.getNodeV()[Bus1.getRef(Bus1.findIdx(iphs2))] ).abs() / Bus1.getkVBase() / 1732.0;
											puV2 = sol.getNodeV()[Bus2.getRef(Bus2.findIdx(iphs))].subtract( sol.getNodeV()[Bus2.getRef(Bus2.findIdx(iphs2))] ).abs() / Bus2.getkVBase() / 1732.0;
										}
										writeNewLine(FPrinter, PresentCktElement.getName(), Bus1.getDistFromMeter(), puV1, Bus2.getDistFromMeter(), puV2,
												iphs, 2, Linetype, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth());
									}
								break;
							case DSSGlobals.PROFILELLALL:
								for (iphs = 0; iphs < 3; iphs++) {
									iphs2 = iphs + 1;
									if (iphs2 >= 3) iphs2 = 0;  // TODO Check zero based indexing
									if ((Bus1.findIdx(iphs) >= 0) && (Bus2.findIdx(iphs) >= 0) &&
											(Bus1.findIdx(iphs2) >= 0) && (Bus2.findIdx(iphs2) >= 0)) {
										if (Bus1.getkVBase() < 1.0) {
											Linetype = 2;
										} else {
											Linetype = 0;
										}
										SolutionObj sol = ckt.getSolution();
										puV1 = sol.getNodeV()[Bus1.getRef(Bus1.findIdx(iphs))].subtract( sol.getNodeV()[Bus1.getRef(Bus1.findIdx(iphs2))] ).abs() / Bus1.getkVBase() / 1732.0;
										puV2 = sol.getNodeV()[Bus2.getRef(Bus2.findIdx(iphs))].subtract( sol.getNodeV()[Bus2.getRef(Bus2.findIdx(iphs2))] ).abs() / Bus2.getkVBase() / 1732.0;
									}
									writeNewLine(FPrinter, PresentCktElement.getName(), Bus1.getDistFromMeter(), puV1, Bus2.getDistFromMeter(), puV2,
											iphs, 2, Linetype, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth());
								}
								break;
							case DSSGlobals.PROFILELLPRI:
								if (Bus1.getkVBase() > 1.0)
									for (iphs = 0; iphs < 3; iphs++) {
										iphs2 = iphs + 1;
										if (iphs2 >= 3) iphs2 = 0;  // TODO Check zero based indexing
										if ((Bus1.findIdx(iphs) >= 0) && (Bus2.findIdx(iphs) >= 0) &&
												(Bus1.findIdx(iphs2) >= 0) && (Bus2.findIdx(iphs2) >= 0)) {
											if (Bus1.getkVBase() < 1.0) {
												Linetype = 2;
											} else {
												Linetype = 0;
											}
											SolutionObj sol = ckt.getSolution();
											puV1 = sol.getNodeV()[Bus1.getRef(Bus1.findIdx(iphs))].subtract( sol.getNodeV()[Bus1.getRef(Bus1.findIdx(iphs2))] ).abs() / Bus1.getkVBase() / 1732.0;
											puV2 = sol.getNodeV()[Bus2.getRef(Bus2.findIdx(iphs))].subtract( sol.getNodeV()[Bus2.getRef(Bus2.findIdx(iphs2))] ).abs() / Bus2.getkVBase() / 1732.0;
										}
										writeNewLine(FPrinter, PresentCktElement.getName(), Bus1.getDistFromMeter(), puV1, Bus2.getDistFromMeter(), puV2,
												iphs, 2, Linetype, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth());
									}
								break;
							default:  // plot just the selected phase
								iphs = PhasesToPlot;
								if ((Bus1.findIdx(iphs) > 0) && (Bus2.findIdx(iphs) > 0)) {
									if (Bus1.getkVBase() < 1.0) {
										Linetype = 2;
									} else {
										Linetype = 0;
									}
									puV1 = ckt.getSolution().getNodeV()[Bus1.getRef(Bus1.findIdx(iphs))].abs() / Bus1.getkVBase() / 1000.0;
									puV2 = ckt.getSolution().getNodeV()[Bus2.getRef(Bus2.findIdx(iphs))].abs() / Bus2.getkVBase() / 1000.0;
									writeNewLine(FPrinter, PresentCktElement.getName(), Bus1.getDistFromMeter(), puV1, Bus2.getDistFromMeter(), puV2,
											iphs, 2, Linetype, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth());
								}
								break;
							}
						}
					}
					PresentCktElement = (CktElement) ActiveEnergyMeter.getBranchList().GoForward();
				}
				iEnergyMeter = Globals.getEnergyMeterClass().getNext();
			}

			Globals.setGlobalResult(FileNm);

			F.close();
			FPrinter.close();
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
