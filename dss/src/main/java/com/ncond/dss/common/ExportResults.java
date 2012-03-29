/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.types.CIMProfileChoice;
import com.ncond.dss.common.types.ProfilePlot;
import com.ncond.dss.common.types.YPrimType;
import com.ncond.dss.conversion.Generator;
import com.ncond.dss.conversion.GeneratorObj;
import com.ncond.dss.conversion.LoadObj;
import com.ncond.dss.conversion.PCElement;
import com.ncond.dss.delivery.PDElement;
import com.ncond.dss.general.LineCode;
import com.ncond.dss.general.LineGeometry;
import com.ncond.dss.general.NamedObject;
import com.ncond.dss.general.WireData;
import com.ncond.dss.general.XfmrCode;
import com.ncond.dss.meter.EnergyMeter;
import com.ncond.dss.meter.EnergyMeterObj;
import com.ncond.dss.meter.SensorObj;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.ComplexUtil;
import com.ncond.dss.shared.MathUtil;

public class ExportResults {

	private ExportResults() {}

	/**
	 * Export symmetrical component bus voltages.
	 */
	public static void exportSeqVoltages(String fileName) {
		FileWriter fw;
		PrintWriter pw;
		int i, j;
		int nref;
		Bus bus;
		Complex[] Vph = new Complex[3];
		Complex[] V012 = new Complex[3];

		double V0, V1, V2, Vpu, V2V1, V0V1;
		Complex Vresidual;
		double V_NEMA;

		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			pw.println("Bus,  V1,  p.u.,Base kV, V2, %V2/V1, V0, %V0/V1, Vresidual, %NEMA");
			for (i = 0; i < ckt.getNumBuses(); i++) {
				bus = ckt.getBus(i);

				if (bus.getNumNodesThisBus() < 3) {
					V0 = 0.0;
					V2 = 0.0;
					V_NEMA = 0.0;
					if ((bus.getNumNodesThisBus() == 1) && ckt.isPositiveSequence()) {
						nref = bus.getRef(0);  // first node
						Vph[0] = ckt.getSolution().getNodeV(nref);
						V1 = Vph[0].abs();
					} else {
						V1 = 0.0;
					}
				} else {
					for (j = 0; j < 3; j++) {
						// first nodes named 1, 2, 3
						Vph[j] = sol.getNodeV(bus.getRef(bus.findIdx(j + 1)));
					}

					MathUtil.phase2SymComp(Vph, V012);

					V0 = V012[0].abs();
					V1 = V012[1].abs();
					V2 = V012[2].abs();

					V_NEMA = MathUtil.pctNemaUnbalance(Vph);
				}

				if (bus.getKVBase() != 0.0) {
					Vpu = 0.001 * V1 / bus.getKVBase();
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
				for (j = 0; j < bus.getNumNodesThisBus(); j++) {
					Vresidual = Vresidual.add( sol.getNodeV(bus.getRef(j) - 1) );
				}

				pw.printf("\"%s\", %10.6g, %9.5g, %8.2f, %10.6g, %8.4g, %10.6g, %8.4g, %10.6g, %8.4g",
						ckt.getBusList().get(i).toUpperCase(),
						V1, Vpu,
						(bus.getKVBase() * DSS.SQRT3),
						V2, V2V1, V0, V0V1, Vresidual.abs(), V_NEMA);
			}
			DSS.globalResult = fileName;

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting sequence voltages: " + e.getMessage(), -1);
		}
	}

	/**
	 * Export symmetrical component bus voltages.
	 */
	public static void exportVoltages(String fileName) {
		FileWriter fw;
		PrintWriter pw;

		int maxNumNodes;
		int i, j, jj;
		String busName;
		Complex volts;
		int nref;
		int nodeIdx = -1;
		double Vmag, Vpu;
		Bus bus;

		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		/* Find max nodes at a bus */
		maxNumNodes = 0;
		for (i = 0; i < ckt.getNumBuses(); i++) {
			bus = ckt.getBus(i);
			maxNumNodes = Math.max(maxNumNodes, bus.getNumNodesThisBus());
		}

		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			pw.print("Bus, BasekV");
			for (i = 1; i <= maxNumNodes; i++)
				pw.printf(", Node%d, Magnitude%d, Angle%d, pu%d", i, i, i, i);
			pw.println();

			for (i = 0; i < ckt.getNumBuses(); i++) {
				bus = ckt.getBus(i);
				busName = ckt.getBusList().get(i);
				pw.printf("\"%s\", %.5g", busName.toUpperCase(), bus.getKVBase() * DSS.SQRT3);

				jj = 1;
				for (j = 0; j < bus.getNumNodesThisBus(); j++) {
					while (nodeIdx < 0) {
						nodeIdx = bus.findIdx(jj);  // try to find nodes in order
						jj += 1;
					}
					nref = bus.getRef(nodeIdx);
					volts = sol.getNodeV(nref);
					Vmag = volts.abs();
					if (bus.getKVBase() != 0.0) {
						Vpu = 0.001 * Vmag / bus.getKVBase();
					} else {
						Vpu = 0.0;
					}

					pw.printf(", %d, %10.6g, %6.1f, %9.5g",
							bus.getNum(nodeIdx), Vmag, ComplexUtil.degArg(volts), Vpu);
				}
				for (j = bus.getNumNodesThisBus(); j < maxNumNodes; j++)
					pw.printf(", %d, %10.6g, %6.1f, %9.5g", 0, 0, 0, 0);  // zero fill row
				pw.println();
			}

			DSS.globalResult = fileName;

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting voltages: " + e.getMessage(), -1);
		}
	}

	private static void calcAndWriteSeqCurrents(PrintWriter pw, int iTerm, CktElement pElem,
			Complex[] cBuffer, boolean doRatings) {

		double I0, I1, I2, I2I1, I0I1, Inormal, Iemerg;
		int i, k, nCond;
		Complex[] Iph = new Complex[3];
		Complex[] I012 = new Complex[3];
		Complex Iresidual;
		double I_NEMA;

		nCond = pElem.getNumConds();
		if (pElem.getNumPhases() >= 3) {
			for (i = 0; i < 3; i++) {
				k = iTerm * nCond + i;
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
			if (DSS.activeCircuit.isPositiveSequence())
				I1 = Iph[0].abs();  // use phase 1 only
		}

		if (I1 > 0.0) {
			I2I1 = 100.0 * I2/I1;
			I0I1 = 100.0 * I0/I1;
		} else {
			I2I1 = 0.0;
			I0I1 = 0.0;
		}

		if (doRatings && (iTerm == 0)) {  // only for 1st terminal
			Inormal = ((PDElement) pElem).getNormAmps();
			if (Inormal > 0.0)
				Inormal = I1 / Inormal * 100.0;
			Iemerg = ((PDElement) pElem).getEmergAmps();
			if (Iemerg > 0.0)
				Iemerg = I1 / Iemerg * 100.0;
		} else {
			Inormal = 0.0;
			Iemerg = 0.0;
		}

		Iresidual = Complex.ZERO;
		for (i = 0; i < nCond; i++)
			Iresidual = Iresidual.add(cBuffer[i]);

		pw.printf("\"%s\", %3d, %10.6g, %8.4g, %8.4g, %10.6g, %8.4g, %10.6g, %8.4g, %10.6g, %8.4g",
			pElem.getDSSClassName() + "." + pElem.getName().toUpperCase(), iTerm + 1,
			I1, Inormal, Iemerg, I2, I2I1, I0, I0I1, Iresidual.abs(), I_NEMA);
	}

	public static void exportSeqCurrents(String fileName) {
		FileWriter fw;
		PrintWriter pw;
		int j;
		Complex[] cBuffer;  // allocate to max total conductors

		Circuit ckt = DSS.activeCircuit;

		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			/* Sequence currents */
			pw.println("Element, Terminal,  I1, %%Normal, %%Emergency, I2, %%I2/I1, I0, %I0/I1, Iresidual, %%NEMA");

			/* Allocate cBuffer big enough for largest circuit element */
			cBuffer = new Complex[Util.getMaxCktElementSize()];

			// sources first
			for (CktElement pElem : ckt.getSources()) {
				if (pElem.isEnabled()) {
					pElem.getCurrents(cBuffer);
					for (j = 0; j < pElem.getNumTerms(); j++)
						calcAndWriteSeqCurrents(pw, j, pElem, cBuffer, false);
				}
			}


			// PD elements next
			for (PDElement PDElem : ckt.getPDElements()) {
				if (PDElem.isEnabled()) {
					PDElem.getCurrents(cBuffer);
					for (j = 0; j < PDElem.getNumTerms(); j++)
						calcAndWriteSeqCurrents(pw, j, PDElem, cBuffer, true);
				}
			}

			// PC elements next
			for (PCElement PCElem : ckt.getPCElements()) {
				if (PCElem.isEnabled()) {
					PCElem.getCurrents(cBuffer);
					for (j = 0; j < PCElem.getNumTerms(); j++)
						calcAndWriteSeqCurrents(pw, j, PCElem, cBuffer, false);
				}
			}

			// faults next
			for (CktElement pElem : ckt.getFaults()) {
				if (pElem.isEnabled()) {
					pElem.getCurrents(cBuffer);
					for (j = 0; j < pElem.getNumTerms(); j++)
						calcAndWriteSeqCurrents(pw, j, pElem, cBuffer, false);
				}
			}

			DSS.globalResult = fileName;

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting sequence currents: " + e.getMessage(), -1);
		}
	}

	private static void calcAndWriteCurrents(PrintWriter pw, CktElement pElem,
			Complex[] cBuffer, int condWidth, int termWidth) {
		int i, j, k;
		Complex Iresid, c;

		k = -1;
		pw.printf("%s.%s", pElem.getDSSClassName(), pElem.getName().toUpperCase());
		for (j = 0; j < pElem.getNumTerms(); j++) {
			Iresid = Complex.ZERO;
			for (i = 0; i < pElem.getNumConds(); i++) {
				k += 1;
				c = cBuffer[k];
				pw.printf(", %10.6g, %8.2f", c.abs(), ComplexUtil.degArg(c));
				Iresid = Iresid.add(c);
			}
			for (i = pElem.getNumConds(); i < condWidth; i++)
				pw.printf(", %10.6g, %8.2f", 0.0, 0.0);
			pw.printf(", %10.6g, %8.2f", Iresid.abs(), ComplexUtil.degArg(Iresid));
		}

		/* Filler if no. terms less than termwidth */
		for (j = pElem.getNumTerms(); j < termWidth; j++)
			for (i = 0; i < condWidth + 1; i++)
				pw.printf(", %10.6g, %8.2f", 0.0, 0.0);
		pw.println();
	}

	private static void calcAndWriteMaxCurrents(PrintWriter pw, PDElement pElem, Complex[] cBuffer) {
		int i;
		double currMag, maxCurrent;
		Complex localPower;
		Circuit ckt = DSS.activeCircuit;

		pw.printf("%s.%s", pElem.getDSSClassName(), pElem.getName().toUpperCase());
		maxCurrent = 0.0;
		for (i = 0; i < pElem.getNumPhases(); i++) {
			currMag = cBuffer[i].abs();
			if (currMag > maxCurrent) maxCurrent = currMag;
		}

		localPower = pElem.getPower(0).multiply(0.001);
		if ((pElem.getNormAmps() == 0.0) || (pElem.getEmergAmps() == 0.0)) {
			pw.printf(", %10.6g, %8.2f, %8.2f", maxCurrent, 0.0, 0.0);
		} else {
			pw.printf(", %10.6g, %8.2f, %8.2f", maxCurrent,
				maxCurrent / pElem.getNormAmps() * 100.0,
				maxCurrent / pElem.getEmergAmps() * 100.0);
		}
		pw.printf(", %10.6g, %10.6g, %d, %d, %d", localPower.getReal(), localPower.getImaginary(),
				pElem.getNumCustomers(), pElem.getTotalCustomers(), pElem.getNumPhases());

		pw.printf(", %-.3g ", ckt.getBus( ckt.getMapNodeToBus(pElem.getNodeRef(0)).busRef ).getKVBase());
		pw.println();
	}

	public static void exportCurrents(String fileName) {
		FileWriter fw;
		PrintWriter pw;
		Complex[] cBuffer;
		int maxCond, maxTerm;
		int i, j;

		Circuit ckt = DSS.activeCircuit;

		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			cBuffer = new Complex[Util.getMaxCktElementSize()];

			/* Calculate the width of the file */
			maxCond = 1;
			maxTerm = 2;
			for (CktElement pElem : ckt.getCktElements()) {
				if (pElem.getNumTerms() > maxTerm) maxTerm = pElem.getNumTerms();
				if (pElem.getNumConds() > maxCond) maxCond = pElem.getNumConds();
			}

			/* Branch currents */
			pw.print("Element");
			for (i = 1; i <= maxTerm; i++) {
				for (j = 1; j <= maxCond; j++)
					pw.printf(", I%d_%d, Ang%d_%d", i, j, i, j);
				pw.printf(", Iresid%d, AngResid%d", i, i);
			}
			pw.println();

			// sources first
			for (CktElement pElem : ckt.getSources()) {
				if (pElem.isEnabled()) {
					pElem.getCurrents(cBuffer);
					calcAndWriteCurrents(pw, pElem, cBuffer, maxCond, maxTerm);
				}
			}

			// PD elements next
			for (CktElement pElem : ckt.getPDElements()) {
				if (pElem.isEnabled()) {
					pElem.getCurrents(cBuffer);
					calcAndWriteCurrents(pw, pElem, cBuffer, maxCond, maxTerm);
				}
			}

			// faults
			for (CktElement pElem : ckt.getFaults()) {
				if (pElem.isEnabled()) {
					pElem.getCurrents(cBuffer);
					calcAndWriteCurrents(pw, pElem, cBuffer, maxCond, maxTerm);
				}
			}

			// PC elements next
			for (CktElement pElem : ckt.getPCElements()) {
				if (pElem.isEnabled()) {
					pElem.getCurrents(cBuffer);
					calcAndWriteCurrents(pw, pElem, cBuffer, maxCond, maxTerm);
				}
			}
			DSS.globalResult = fileName;

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting currents: " + e.getMessage(), -1);
		}
	}

	/**
	 * @param fileName
	 * @param opt 0: kVA, 1: MVA
	 */
	public static void exportPowers(String fileName, int opt) {
		FileWriter fw;
		PrintWriter pw;
		int nTerm, j;
		Complex S;
		String sep = ", ";

		Circuit ckt = DSS.activeCircuit;

		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			switch (opt) {
			case 1:
				pw.println("Element, Terminal, P(MW), Q(Mvar), P_Normal, Q_Normal, P_Emergency, Q_Emergency");
				break;
			default:
				pw.println("Element, Terminal, P(kW), Q(kvar),  P_Normal, Q_Normal, P_Emergency, Q_Emergency");
				break;
			}

			// PD elements first
			for (PDElement PDElem : ckt.getPDElements()) {
				if (PDElem.isEnabled()) {
					nTerm = PDElem.getNumTerms();

					for (j = 0; j < nTerm; j++) {
						pw.printf("\"%24s%s%d\"",
							PDElem.getDSSClassName() + "." + PDElem.getName().toUpperCase(),
							sep, j + 1);

						S = PDElem.getPower(j);
						if (opt == 1) S = S.multiply(0.001);

						pw.print(sep + S.getReal() * 0.001);
						pw.print(sep + S.getImaginary() * 0.001);
						if (j == 0) {
							S = PDElem.getExcessKVANorm(0);
							if (opt == 1) S = S.multiply(0.001);

							pw.print(sep + Math.abs(S.getReal()));
							pw.print(sep + Math.abs(S.getImaginary()));

							S = PDElem.getExcessKVAEmerg(0);
							if (opt == 1) S = S.multiply(0.001);

							pw.print(sep + Math.abs(S.getReal()));
							pw.print(sep + Math.abs(S.getImaginary()));
						}
						pw.println();
					}
				}
			}

			// PC elements next
			for (PCElement PCElem : ckt.getPCElements()) {
				if (PCElem.isEnabled()) {
					nTerm = PCElem.getNumTerms();

					for (j = 0; j < nTerm; j++) {
						pw.printf("\"%24s%s%d\"",
							PCElem.getDSSClassName() + "." + PCElem.getName().toUpperCase(),
							sep, j);
						S = PCElem.getPower(j);
						if (opt == 1) S = S.multiply(0.001);
						pw.print(sep + S.getReal() * 0.001);
						pw.print(sep + S.getImaginary() * 0.001);
						pw.println();
					}
				}
			}

			DSS.globalResult = fileName;

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting powers: " + e.getMessage(), -1);
		}
	}

	public static void exportLosses(String fileName) {
		FileWriter fw;
		PrintWriter pw;
		Complex[] Stot = new Complex[1];
		Complex[] Sload = new Complex[1];
		Complex[] S_noload = new Complex[1];

		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			pw.println("Element,  Total(W), Total(var),  I2R(W), I2X(var), No-load(W), No-load(var)");
			for (PDElement PDElem : DSS.activeCircuit.getPDElements()) {
				if (PDElem.isEnabled()) {
					PDElem.getLosses(Stot, Sload, S_noload);
					pw.printf("%s.%s, %.7g, %.7g, %.7g, %.7g, %.7g, %.7g",
						PDElem.getParentClass().getClassName(),
						PDElem.getName().toUpperCase(),
						Stot[0].getReal(), Stot[0].getImaginary(),
						Sload[0].getReal(), Sload[0].getImaginary(),
						S_noload[0].getReal(), S_noload[0].getImaginary());
					pw.println();
				}
			}
			DSS.globalResult = fileName;

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting losses: " + e.getMessage(), -1);
		}
	}

	/**
	 * Export powers by phase.
	 *
	 * @param fileName
	 * @param opt 0: kVA, 1: MVA
	 */
	public static void exportPowersByPhase(String fileName, int opt) {
		FileWriter fw;
		PrintWriter pw;
		int i;
		Complex S;

		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			switch (opt) {
			case 1:
				pw.println("Element, NumTerminals, NumConductors, NumPhases, MW1, Mvar1, MW2, Mvar2, MW3, Mvar3, ... ");
				break;
			default:
				pw.println("Element, NumTerminals, NumConductors, NumPhases, kW1, kvar1, kW2, kvar2, kW3, kvar3, ... ");
				break;
			}

			// PD elements first
			for (PDElement PDElem : DSS.activeCircuit.getPDElements()) {
				if (PDElem.isEnabled()) {
					PDElem.computeITerminal();
					PDElem.computeVTerminal();

					pw.printf("\"%s.%s\", %d, %d, %d", PDElem.getDSSClassName(), PDElem.getName().toUpperCase(),
							PDElem.getNumTerms(), PDElem.getNumConds(), PDElem.getNumPhases());

					for (i = 0; i < PDElem.getYOrder(); i++) {
						S = PDElem.getVTerminal(i).multiply( PDElem.getITerminal(i).conjugate() ).multiply(0.001);
						if (opt == 1) S = S.multiply(0.001);  // convert to MVA
						pw.printf(", %10.3f, %10.3f", S.getReal(), S.getImaginary());
					}
					pw.println();
				}
			}

			// PC elements next
			for (PCElement PCElem : DSS.activeCircuit.getPCElements()) {
				if (PCElem.isEnabled()) {
					PCElem.computeITerminal();
					PCElem.computeVTerminal();

					pw.printf("\"%s.%s\", %d, %d, %d", PCElem.getDSSClassName(), PCElem.getName().toUpperCase(),
						PCElem.getNumTerms(), PCElem.getNumConds(), PCElem.getNumPhases());

					for (i = 0; i < PCElem.getYOrder(); i++) {
						S = PCElem.getVTerminal(i).multiply( PCElem.getITerminal(i).conjugate() ).multiply(0.001);
						if (opt == 1) S = S.multiply(0.001);  // convert to MVA
						pw.printf(", %10.3f, %10.3f", S.getReal(), S.getImaginary());
					}
					pw.println();
				}
			}

			DSS.globalResult = fileName;

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting powers by phase: " + e.getMessage(), -1);
		}
	}

	/**
	 * @param fileName
	 * @param opt 0: kVA, 1: MVA
	 */
	public static void exportSeqPowers(String fileName, int opt) {
		FileWriter fw;
		PrintWriter pw;
		Complex[] cBuffer;
		int nCond, nTerm, i, j, k;
		Complex volts;
		Complex S;
		int nref;
		Complex[] Vph = new Complex[3];
		Complex[] V012 = new Complex[3];
		Complex[] Iph = new Complex[3];
		Complex[] I012 = new Complex[3];
		String sep = ", ";

		Circuit ckt = DSS.activeCircuit;

		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			cBuffer = new Complex[Util.getMaxCktElementSize()];

			switch (opt) {
			case 1:
				pw.println("Element, Terminal, P1(MW), Q1(Mvar), P2, Q2, P0, Q0, P_Normal, Q_Normal, P_Emergency, Q_Emergency");
				break;
			default:
				pw.println("Element, Terminal, P1(kW), Q1(kvar), P2, Q2, P0, Q0, P_Normal, Q_Normal, P_Emergency, Q_Emergency");
				break;
			}

			// PD elements first
			for (PDElement PDElem : ckt.getPDElements()) {
				if (PDElem.isEnabled()) {
					nCond = PDElem.getNumConds();
					nTerm = PDElem.getNumTerms();
					PDElem.getCurrents(cBuffer);

					for (j = 0; j < nTerm; j++) {
						pw.print(Util.pad("\"" + PDElem.getDSSClassName() + "." + PDElem.getName().toUpperCase() + "\"", 24) + sep + (j+1));
						for (i = 0; i < PDElem.getNumPhases(); i++) {
							k = j * nCond + i;
							nref = PDElem.getNodeRef(k);
							volts = ckt.getSolution().getNodeV(nref);
							Iph[i] = cBuffer[k];
							Vph[i] = volts;
						}
						if (PDElem.getNumPhases() >= 3) {
							MathUtil.phase2SymComp(Iph, I012);
							MathUtil.phase2SymComp(Vph, V012);
						} else {
							V012[0] = Complex.ZERO;
							I012[0] = Complex.ZERO;
							if (ckt.isPositiveSequence()) {
								V012[1] = Vph[0];
								I012[1] = Iph[0];
							} else {
								V012[1] = Complex.ZERO;
								I012[1] = Complex.ZERO;
							}
							V012[2] = Complex.ZERO;
							I012[2] = Complex.ZERO;
						}

						S = V012[1].multiply( I012[1].conjugate() );
						if (opt == 1) S = S.multiply(0.001);
						pw.print(sep + S.getReal() * 0.003);
						pw.print(sep + S.getImaginary() * 0.003);

						S = V012[2].multiply( I012[2].conjugate() );
						if (opt == 1) S = S.multiply(0.001);
						pw.print(sep + S.getReal() * 0.003);
						pw.print(sep + S.getImaginary() * 0.003);

						S = V012[0].multiply( I012[0].conjugate() );
						if (opt == 1) S = S.multiply(0.001);
						pw.print(sep + S.getReal() * 0.003);
						pw.print(sep + S.getImaginary() * 0.003);

						if (j == 0) {
							S = PDElem.getExcessKVANorm(0);
							if (opt == 1) S = S.multiply(0.001);
							pw.print(sep + Math.abs(S.getReal()));
							pw.print(sep + Math.abs(S.getImaginary()));

							S = PDElem.getExcessKVAEmerg(0);
							if (opt == 1) S = S.multiply(0.001);
							pw.print(sep + Math.abs(S.getReal()));
							pw.print(sep + Math.abs(S.getImaginary()));
						}
						pw.println();
					}
				}
			}

			// PC elements next
			for (PCElement PCElem : ckt.getPCElements()) {
				if (PCElem.isEnabled()) {
					nCond = PCElem.getNumConds();
					nTerm = PCElem.getNumTerms();
					PCElem.getCurrents(cBuffer);

					for (j = 0; j < nTerm; j++) {
						pw.print(Util.pad("\"" + PCElem.getDSSClassName() + "." + PCElem.getName().toUpperCase() + "\"", 24) + sep + (j+1));
						for (i = 0; i < PCElem.getNumPhases(); i++) {
							k = j * nCond + i;
							nref = PCElem.getNodeRef(k);
							volts = ckt.getSolution().getNodeV(nref);
							Iph[i] = cBuffer[k];
							Vph[i] = volts;
						}
						if (PCElem.getNumPhases() >= 3) {
							MathUtil.phase2SymComp(Iph, I012);
							MathUtil.phase2SymComp(Vph, V012);
						} else {
							V012[0] = Complex.ZERO;
							I012[0] = Complex.ZERO;
							if (ckt.isPositiveSequence()) {
								V012[1] = Vph[0];
								I012[2] = Iph[0];
							} else {
								V012[1] = Complex.ZERO;
								I012[1] = Complex.ZERO;
							}
							V012[2] = Complex.ZERO;
							I012[2] = Complex.ZERO;
						}

						S = V012[1].multiply( I012[1].conjugate() );
						if (opt == 1) S = S.multiply(0.001);
						pw.print(sep + S.getReal() * 0.003);
						pw.print(sep + S.getImaginary() * 0.003);

						S = V012[2].multiply( I012[2].conjugate() );
						if (opt == 1) S = S.multiply(0.001);
						pw.print(sep + S.getReal() * 0.003);
						pw.print(sep + S.getImaginary() * 0.003);

						S = V012[0].multiply( I012[0].conjugate() );
						if (opt == 1) S = S.multiply(0.001);
						pw.print(sep + S.getReal() * 0.003);
						pw.print(sep + S.getImaginary() * 0.003);

						pw.println();
					}
				}
			}
			DSS.globalResult = fileName;

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting sequence powers: " + e.getMessage(), -1);
		}
	}

	public static void exportFaultStudy(String fileName) {
		int i, iBus, iphs;
		CMatrix Yfault;
		Complex[] Vfault;  // big temp array
		FileWriter fw;
		PrintWriter pw;
		Complex Gfault;
		final String sep = ", ";
		double maxCurr, currMag;
		Bus bus;

		Circuit ckt = DSS.activeCircuit;

		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			/* Set source voltage injection currents */

			/* All phase faults */
			pw.println("Bus,  3-Phase,  1-Phase,  L-L");
			for (iBus = 0; iBus < ckt.getNumBuses(); iBus++) {
				/* Bus Norton equivalent current, Isc has been previously computed */
				bus = ckt.getBus(iBus);
				pw.print(Util.pad(ckt.getBusList().get(iBus).toUpperCase(), 12));
				maxCurr = 0.0;
				for (i = 0; i < bus.getNumNodesThisBus(); i++) {
					if (maxCurr < bus.getBusCurrent(i).abs())
						maxCurr = bus.getBusCurrent(i).abs();
				}
				pw.print(sep + maxCurr);

				/* One phase faults */

				/* Solve for fault injection currents */

				Yfault = new CMatrix(bus.getNumNodesThisBus());
				Vfault = new Complex[bus.getNumNodesThisBus()];

				/* Build YscTemp */

				Gfault = new Complex(10000.0, 0.0);

				maxCurr = 0.0;

				for (iphs = 0; iphs < bus.getNumNodesThisBus(); iphs++) {
					Yfault.copyFrom(bus.getYsc());
					Yfault.add(iphs, iphs, Gfault);

					/* Solve for injection currents */
					Yfault.invert();
					Yfault.vMult(Vfault, bus.getBusCurrent());  /* Gets voltage appearing at fault */

					currMag = Vfault[iphs].multiply(Gfault).abs();
					if (currMag > maxCurr) maxCurr = currMag;

				}
				/* Now, put it in the Css array where it belongs */
				pw.print(sep + maxCurr);

				Vfault = null;
				Yfault = null;

				/* Node-node faults */

				/* Bus Norton equivalent current, Isc has been previously computed */

				Yfault = new CMatrix(bus.getNumNodesThisBus());
				Vfault = new Complex[bus.getNumNodesThisBus()];

				Gfault = new Complex(10000.0, 0.0);

				maxCurr = 0.0;

				for (iphs = 0; iphs < bus.getNumNodesThisBus() - 1; iphs++) {
					Yfault.copyFrom(bus.getYsc());
					Yfault.add(iphs, iphs, Gfault);
					Yfault.add(iphs + 1, iphs + 1, Gfault);
					Yfault.addSym(iphs, iphs + 1, Gfault.negate());

					/* Solve for injection currents */
					Yfault.invert();
					Yfault.vMult(Vfault, bus.getBusCurrent());  /* Gets voltage appearing at fault */

					currMag = Vfault[iphs].subtract( Vfault[iphs + 1] ).multiply(Gfault).abs();
					if (currMag > maxCurr) maxCurr = currMag;
				}
				/* Now, put it in the Css array where it belongs */
				pw.print(sep + maxCurr);

				Vfault = null;
				Yfault = null;

				pw.println();
			}
			DSS.globalResult = fileName;

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting fault study: " + e.getMessage(), -1);
		}
	}

	public static void exportEstimation(String fileName) {
		FileWriter fw;
		PrintWriter pw;
		int i;
		double[] tmp = new double[3];  // temp number buffer

		Circuit ckt = DSS.activeCircuit;

		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			/* Do the energy meters first */
			pw.println("\"Energy Meters\" ");
			pw.println("\"energyMeter\", \"I1 Target\", \"I2 Target\", \"I3 Target\", \"I1 Calc\", \"I2 Calc\", \"I3 Calc\", \"I1 %Err\", \"I2 %Err\", \"I3 %Err\""/*, \"I1 Factor\", \"I2 Factor\", \"I3 Factor\""*/);

			for (EnergyMeterObj mtr : ckt.getEnergyMeters()) {
				if (mtr.isEnabled()) {
					pw.printf("\"EnergyMeter.%s\"", mtr.getName().toUpperCase());

					/* Sensor currents (target) */
					zeroTempXArray(tmp);
					for (i = 0; i < mtr.getNumPhases(); i++)
						tmp[i] = mtr.getSensorCurrent(i);
					for (i = 0; i < 3; i++)
						pw.printf(", %.6g", tmp[i]);

					/* Calculated currents */
					zeroTempXArray(tmp);
					for (i = 0; i < mtr.getNumPhases(); i++)
						tmp[i] = mtr.getCalculatedCurrent(i).abs();
					for (i = 0; i < 3; i++)
						pw.printf(", %.6g", tmp[i]);

					/* Percent error */
					for (i = 0; i < mtr.getNumPhases(); i++)
						tmp[i] = (1.0 - tmp[i] / Math.max(0.001, mtr.getSensorCurrent(i))) * 100.0;
					for (i = 0; i < 3; i++)
						pw.printf(", %.6g", tmp[i]);

					/****  Not all that useful
					// allocation factors
					zeroTempXArray(TempX);
					for (i = 0; i < pEnergyMeterObj.getNPhases(); i++)
						TempX[i] = pEnergyMeterObj.getPhsAllocationFactor()[i];
					for (i = 0; i < 3; i++)
						FPrinter.printf(" %.6g,", TempX[i]);
					*****/

					pw.println();
				}
			}

			/* Do the sensors next */
			pw.println();
			pw.println("\"Sensors\" ");
			pw.print("\"Sensor\", \"I1 Target\", \"I2 Target\", \"I3 Target\", \"I1 Calc\", \"I2 Calc\", \"I3 Calc\", \"I1 %Err\", \"I2 %Err\", \"I3 %Err\",");
			pw.println(" \"V1 Target\", \"V2 Target\", \"V3 Target\", \"V1 Calc\", \"V2 Calc\", \"V3 Calc\", \"V1 %Err\", \"V2 %Err\", \"V3 %Err\", \"WLS Voltage Err\", \"WLS Current Err\"");

			for (SensorObj sensor : ckt.getSensors()) {
				if (sensor.isEnabled()) {
					pw.printf("\"Sensor.%s\"", sensor.getName().toUpperCase());

					/* Sensor currents (target) */
					zeroTempXArray(tmp);
					for (i = 0; i < sensor.getNumPhases(); i++)
						tmp[i] = sensor.getSensorCurrent(i);
					for (i = 0; i < 3; i++)
						pw.printf(", %.6g", tmp[i]);

					/* Calculated currents */
					zeroTempXArray(tmp);
					for (i = 0; i < sensor.getNumPhases(); i++)
						tmp[i] = sensor.getCalculatedCurrent(i).abs();
					for (i = 0; i < 3; i++)
						pw.printf(", %.6g", tmp[i]);

					/* Percent error */
					for (i = 0; i < sensor.getNumPhases(); i++)
						tmp[i] = (1.0 - tmp[i] / Math.max(0.001, sensor.getSensorCurrent(i))) * 100.0;
					for (i = 0; i < 3; i++)
						pw.printf(", %.6g", tmp[i]);

					/* Sensor voltage (target) */
					zeroTempXArray(tmp);
					for (i = 0; i < sensor.getNumPhases(); i++)
						tmp[i] = sensor.getSensorVoltage(i);
					for (i = 0; i < 3; i++)
						pw.printf(", %.6g", tmp[i]);

					/* Calculated voltage */
					zeroTempXArray(tmp);
					for (i = 0; i < sensor.getNumPhases(); i++)
						tmp[i] = sensor.getCalculatedVoltage(i).abs();
					for (i = 0; i < 3; i++)
						pw.printf(", %.6g", tmp[i]);

					/* Percent error */
					for (i = 0; i < sensor.getNumPhases(); i++)
						tmp[i] = (1.0 - tmp[i] / Math.max(0.001, sensor.getSensorVoltage(i))) * 100.0;
					for (i = 0; i < 3; i++)
						pw.printf(", %.6g", tmp[i]);

					/* WLS errors */
					zeroTempXArray(tmp);
					pw.printf(", %.6g, %.6g", sensor.getWLSVoltageError(), sensor.getWLSCurrentError());

					pw.println();
				}
			}

			DSS.globalResult = fileName;

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting estimation: " + e.getMessage(), -1);
		}
	}

	private static void zeroTempXArray(double[] tempX) {
		for (int ii = 0; ii < 3; ii++)
			tempX[ii] = 0;
	}

	private static void writeMultipleMeterFiles() {
		FileWriter fw;
		PrintWriter pw;
		int i, j;
		EnergyMeter meterClass;
		String fileName;
		final String sep = ", ";

		Circuit ckt = DSS.activeCircuit;

		meterClass = ((EnergyMeter) DSSClassDefs.getDSSClass("EnergyMeter"));
		if (meterClass == null) return;

		for (EnergyMeterObj mtr : ckt.getEnergyMeters()) {
			if (mtr.isEnabled()) {
				try {
					fileName = DSS.dataDirectory + "EXP_MTR_" + mtr.getName().toUpperCase() + ".csv";

					if (!new File(fileName).exists()) {
						fw = new FileWriter(fileName);
						pw = new PrintWriter(fw);

						/* Write new header */
						pw.print("Year, LDCurve, Hour, Meter");
						for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
							pw.write(sep + "\"" + mtr.getRegisterName(i) + "\"");
						pw.println();

						pw.close();
						fw.close();
					}

					fw = new FileWriter(fileName, true);  // append
					pw = new PrintWriter(fw);

					pw.print(ckt.getSolution().getYear() + sep);
					pw.print(ckt.getLoadDurCurve() + sep);
					pw.print(ckt.getSolution().getIntHour() + sep);
					pw.print(Util.pad("\"" + mtr.getName().toUpperCase() + "\"", 14));
					for (j = 0; j < EnergyMeter.NUM_EM_REGISTERS; j++)
						pw.print(sep + mtr.getRegister(j));
					pw.println();

					DSS.globalResult = fileName;

					pw.close();
					fw.close();
				} catch (IOException e) {
					DSS.doSimpleMsg("Error encountered exporting multiple meter files: " + e.getMessage(), -1);
				}
			}
		}
	}

	private static void writeSingleMeterFile(String fileName) {
		FileWriter fw;
		PrintWriter pw;
		FileReader fr;
		BufferedReader br;
		int i, j;
		String testStr;
		final String sep = ", ";
		boolean rewriteFile;

		Circuit ckt = DSS.activeCircuit;

		try {
			if (new File(fileName).exists()) {
				// see if it has already been written on
				fr = new FileReader(fileName);
				br = new BufferedReader(fr);
				testStr = br.readLine();
				if (testStr != null) {
					/* See if it likely that the file is OK */
					rewriteFile = !testStr.startsWith("Year");
				} else {
					rewriteFile = true;
				}
				br.close();
				fr.close();
			} else {
				rewriteFile = true;
			}

			/* Either open or append the file */
			if (rewriteFile) {
				fw = new FileWriter(fileName, false);
				pw = new PrintWriter(fw);
				/* Write new header */
				pw.print("Year, LDCurve, Hour, Meter");

				EnergyMeterObj mtr = ckt.getEnergyMeters().get(0);
				for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
					pw.print(sep + "\""+ mtr.getRegisterNames()[i]+"\"");
				pw.println();
			} else {
				fw = new FileWriter(fileName, true);
				pw = new PrintWriter(fw);
			}

			for (EnergyMeterObj mtr : ckt.getEnergyMeters()) {
				if (mtr.isEnabled()) {
					pw.print(ckt.getSolution().getYear() + sep);
					pw.print(ckt.getLoadDurCurve() + sep);
					pw.print(ckt.getSolution().getIntHour() + sep);
					pw.print(Util.pad("\"" + mtr.getName().toUpperCase() + "\"", 14));
					for (j = 0; j < EnergyMeter.NUM_EM_REGISTERS; j++)
						pw.printf(sep + mtr.getRegister(j));
					pw.println();
				}
			}
			DSS.globalResult = fileName;

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting single meter file: " + e.getMessage(), -1);
		}
	}

	/**
	 * Export values of meter elements.
	 *
	 * These records are appended to an existing file so a running account is
	 * kept for some kinds of simulations.
	 *
	 * If switch /m is specified, a separate file is created for each meter
	 * using the meter's name.
	 */
	public static void exportMeters(String fileName) {
		if (fileName.substring(0, 2).toLowerCase() == "/m") {
			writeMultipleMeterFiles();
		} else {
			writeSingleMeterFile(fileName);
		}
	}

	private static void writeMultipleGenMeterFiles() {
		FileWriter fw;
		PrintWriter pw;
		int i, j;
		Generator generatorClass;
		String fileName;
		final String sep = ", ";

		Circuit ckt = DSS.activeCircuit;

		generatorClass = (Generator) DSSClassDefs.getDSSClass("generator");
		if (generatorClass == null) return;

		for (GeneratorObj gen : ckt.getGenerators()) {
			if (gen.isEnabled()) {
				try {
					fileName = DSS.dataDirectory + "EXP_GEN_" + gen.getName().toUpperCase() + ".csv";

					if (!new File(fileName).exists()) {
						fw = new FileWriter(fileName);
						pw = new PrintWriter(fw);
						/* Write new header */
						pw.print("Year, LDCurve, Hour, Generator");

						for (i = 0; i < Generator.NumGenRegisters; i++)
							pw.print(sep + "\"" + generatorClass.getRegisterName(i) + "\"");
						pw.println();

						pw.close();
						fw.close();
					}

					fw = new FileWriter(fileName, true);  // append
					pw = new PrintWriter(fw);

					pw.print(ckt.getSolution().getYear() + sep);
					pw.print(ckt.getLoadDurCurve() + sep);
					pw.print(ckt.getSolution().getIntHour() + sep);
					pw.print(Util.pad("\"" + gen.getName().toUpperCase() + "\"", 14));
					for (j = 0; j < Generator.NumGenRegisters; j++)
						pw.print(sep + gen.getRegister(j));
					pw.println();

					DSS.globalResult = fileName;

					pw.close();
					fw.close();
				} catch (IOException e) {
					DSS.doSimpleMsg("Error encountered exporting multiple gen meter files: " + e.getMessage(), -1);
				}
			}
		}
	}

	private static void writeSingleGenMeterFile(String fileName) {
		FileWriter fw;
		PrintWriter pw;
		FileReader fr;
		BufferedReader br;
		int i, j;
		Generator generatorClass;
		String testStr;
		final String sep = ", ";
		boolean rewriteFile;

		Circuit ckt = DSS.activeCircuit;

		generatorClass = (Generator) DSSClassDefs.getDSSClass("generator");
		if (generatorClass == null)
			return;

		try {
			if (new File(fileName).exists()) {
				// see if it has already been written on
				fr = new FileReader(fileName);
				br = new BufferedReader(fr);
				testStr = br.readLine();
				if (testStr != null) {
					/* See if it likely that the file is OK */
					rewriteFile = !testStr.startsWith("Year");
				} else {
					rewriteFile = true;
				}
				br.close();
				fr.close();
			} else {
				rewriteFile = true;
			}

			/* Either open or append the file */
			if (rewriteFile) {
				fw = new FileWriter(fileName);
				pw = new PrintWriter(fw);
				/* Write new header */
				pw.print("Year, LDCurve, Hour, Generator");
				for (i = 0; i < Generator.NumGenRegisters; i++)
					pw.print(sep + "\""+ generatorClass.getRegisterName(i) + "\"");
				pw.println();
			} else {
				fw = new FileWriter(fileName, true);  // append
				pw = new PrintWriter(fw);
			}

			for (GeneratorObj gen : ckt.getGenerators()) {
				if (gen.isEnabled()) {
					pw.print(ckt.getSolution().getYear() + sep);
					pw.print(ckt.getLoadDurCurve() + sep);
					pw.print(ckt.getSolution().getIntHour() + sep);
					pw.print(Util.pad("\"" + gen.getName().toUpperCase() + "\"", 14));
					for (j = 0; j < Generator.NumGenRegisters; j++)
						pw.print(sep + gen.getRegister(j));
					pw.println();
				}
			}

			DSS.globalResult = fileName;

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting single gen meter file: " + e.getMessage(), -1);
		}
	}

	/**
	 * Export values of generator meter elements.
	 *
	 * If switch /m is specified, a separate file is created for each
	 * generator using the generator's name.
	 */
	public static void exportGenMeters(String fileNm) {
		if (fileNm.substring(0, 2).toLowerCase() == "/m") {
			writeMultipleGenMeterFiles();
		} else {
			writeSingleGenMeterFile(fileNm);
		}
	}

	/**
	 * Export loads to view present allocation.
	 */
	public static void exportLoads(String fileName) {
		FileWriter fw;
		PrintWriter pw;
		final String sep = ", ";

		Circuit ckt = DSS.activeCircuit;

		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			/* Write header */
			pw.println("Load, Connected KVA, Allocation Factor, Phases, kW, kvar, PF, Model");

			for (LoadObj load : ckt.getLoads()) {
				if (load.isEnabled()) {
					pw.print(load.getName().toUpperCase());
					pw.print(sep + load.getConnectedkVA());
					pw.print(sep + load.getKVAAllocationFactor());
					pw.print(sep + load.getNumPhases());
					pw.print(sep + load.getKWBase());
					pw.print(sep + load.getKVArBase());
					pw.print(sep + load.getPFNominal());
					pw.print(sep + load.getLoadModel());
				}
				pw.println();
			}

			DSS.globalResult = fileName;

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting loads: " + e.getMessage(), -1);
		}
	}

	/**
	 * Similar to export currents except does only max of the phases and
	 * compares that to the NormAmps and EmergAmps rating.
	 */
	public static void exportCapacity(String fileNm) {
		FileWriter fw;
		PrintWriter pw;
		Complex[] cBuffer;

		Circuit ckt = DSS.activeCircuit;

		try {
			fw = new FileWriter(fileNm);
			pw = new PrintWriter(fw);

			cBuffer = new Complex[Util.getMaxCktElementSize()];

			pw.println("Name, Imax, %normal, %emergency, kW, kvar, NumCustomers, TotalCustomers, NumPhases, kVBase");

			// PD elements only
			for (PDElement elem : ckt.getPDElements()) {
				if (elem.isEnabled()) {
					elem.getCurrents(cBuffer);
					calcAndWriteMaxCurrents(pw, elem, cBuffer);
				}
			}

			DSS.globalResult = fileNm;

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting capacity: " + e.getMessage(), -1);
		}
	}

	public static void exportOverloads(String fileName) {
		FileWriter fw;
		PrintWriter pw;
		Complex[] cBuffer;  // allocate to max total conductors
		int nCond, i, j;
		Complex[] Iph = new Complex[3];
		Complex[] I012 = new Complex[3];
		double I0, I1, I2;
		double Inorm, Iemerg, Cmax;
		final String sep = ", ";

		Circuit ckt = DSS.activeCircuit;

		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			/* Allocate cBuffer big enough for largest circuit element */
			cBuffer = new Complex[Util.getMaxCktElementSize()];

			/* Sequence currents */
			pw.println("Element, Terminal,  I1, %Normal, %Emergency, I2, %I2/I1, I0, %I0/I1");

			// PD elements only
			for (PDElement elem : ckt.getPDElements()) {
				if (elem.isEnabled()) {
					if ((DSSClassDefs.CLASSMASK & elem.getObjType()) != DSSClassDefs.CAP_ELEMENT) {  // ignore caps
						nCond = elem.getNumConds();
						elem.getCurrents(cBuffer);

						for (j = 0; j < 1; j++) {  // only for terminal 1
							Cmax = 0.0;
							for (i = 0; i < Math.min(elem.getNumPhases(), 3); i++) {  // check only first 3 phases
								Iph[i] = cBuffer[j * nCond + i];
								Cmax = Math.max(Cmax, Iph[i].abs());
							}
							if (elem.getNumPhases() >= 3) {
								// report symmetrical component currents for
								MathUtil.phase2SymComp(Iph, I012);
								I0 = I012[0].abs();  // get abs values to report
								I1 = I012[1].abs();
								I2 = I012[2].abs();
							} else {
								// other than 3-phase
								I0 = 0.0;
								I1 = Iph[0].abs();  // ambiguous: report only first phase
								I2 = 0.0;
								Cmax = I1;
							}

							if ((elem.getNormAmps() > 0.0) || (elem.getEmergAmps() > 0.0)) {
								if ((Cmax > elem.getNormAmps()) || (Cmax > elem.getEmergAmps())) {
									pw.print(Util.pad("\"" + elem.getDSSClassName() + "." + elem.getName().toUpperCase() + "\"", 22) + sep + (j+1));
									pw.print(sep + I1);
									if (j == 0) {  // only for 1st terminal
										Inorm = elem.getNormAmps();
										if (Inorm > 0.0) {
											pw.print(sep + Cmax / Inorm * 100.0);
										} else {
											pw.print(sep + "     0.0");
										}
										Iemerg = elem.getEmergAmps();
										if (Iemerg > 0.0) {
											pw.print(sep + Cmax / Iemerg * 100.0);
										} else {
											pw.print(sep + "     0.0");
										}
									} else {
										pw.print(sep + "       0" + sep + "       0");
									}
									pw.print(sep + I2);
									if (I1 > 0.0) {
										pw.print(sep + 100.0 * I2 / I1);
									} else {
										pw.print(sep + "0.0");
									}
									pw.print(sep + I0);
									if (I1 > 0.0) {
										pw.print(sep + 100.0 * I0 / I1);
									} else {
										pw.print(sep + "0.0");
									}
									pw.println();
								}
							}
						}
					}
				}
			}

			DSS.globalResult = fileName;

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting overloads: " + e.getMessage(), -1);
		}
	}

	public static void exportUnserved(String fileName, boolean UE_Only) {
		FileWriter fw;
		PrintWriter pw;
		boolean doIt;

		Circuit ckt = DSS.activeCircuit;

		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			pw.println("Load, Bus, kW, EEN_Factor,  UE_Factor");

			for (LoadObj load : ckt.getLoads()) {
				if (load.isEnabled()) {
					if (UE_Only) {
						doIt = load.getUnserved();
					} else {
						doIt = load.getExceedsNormal();
					}

					if (doIt) {
						pw.print(load.getName().toUpperCase() + ", ");
						pw.print(load.getBus(0) + ", ");
						pw.print(load.getKWBase() + ", ");
						pw.print(load.getEEN_Factor() + ", ");
						pw.print(load.getUE_Factor());
						pw.println();
					}
				}
			}

			DSS.globalResult = fileName;

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting unserved: " + e.getMessage(), -1);
		}
	}

	/**
	 * Exports YPrim matrices for all circuit elements.
	 */
	public static void exportYprim(String fileName) {
		FileWriter fw;
		PrintWriter pw;
		int i, j, k;
		Complex[] cValues;
		CktElement cktElem;

		Circuit ckt = DSS.activeCircuit;

		if (ckt == null) return;

		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			for (k = 0; k < ckt.getNumDevices(); k++) {
				ckt.setActiveCktElement( ckt.getCktElements().get(k) );
				if (ckt.getActiveCktElement().isEnabled()) {
					if ((ckt.getActiveCktElement() instanceof PDElement) || (ckt.getActiveCktElement() instanceof PCElement)) {
						cktElem = ckt.getActiveCktElement();
						pw.println(cktElem.getParentClass().getClassName() + "." + cktElem.getName().toUpperCase());

						cValues = cktElem.getYPrimValues(YPrimType.ALL_YPRIM);
						for (i = 0; i < cktElem.getYOrder(); i++) {
							for (j = 0; j < cktElem.getYOrder(); j++)
								pw.printf("%-13.10g, %-13.10g, ",
									cValues[i + j * cktElem.getYOrder()].getReal(),
									cValues[i + j * cktElem.getYOrder()].getImaginary());
							pw.println();
						}
					}
				}
			}

			DSS.globalResult = fileName;

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting Yprim: " + e.getMessage(), -1);
		}
	}

	/**
	 * Exports system Y matrix in node order.
	 */
	public static void exportY(String fileName) {
		FileWriter fw;
		PrintWriter pw;
		int i, j, p;
		YMatrix Y;
		int nBus = 0, nnz = 0;
		int[] colPtr, rowIdx;
		Complex[] cVals;
		double re, im;

		Circuit ckt = DSS.activeCircuit;
		if (ckt == null) return;

		Y = ckt.getSolution().getY();
		if (Y == null) {
			DSS.doSimpleMsg("Y Matrix not built.", 222);
			return;
		}

		// this compresses the entries if necessary - no extra work if already solved
		Y.factorSparseMatrix();
		nnz = Y.getNNZ();
		nBus = Y.getSize();  // we should already know this

		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			colPtr = new int[nBus + 1];
			rowIdx = new int[nnz];
			cVals  = new Complex[nnz];
			Y.getCompressedMatrix(nBus + 1, nnz, colPtr, rowIdx, cVals);

			/* Write out fully qualified bus names */

			pw.printf("%d, ", ckt.getNumNodes());
			pw.println();
			/*for (i = 0; i < ckt.getNumNodes(); i++) {
				j = ckt.getMapNodeToBus(i).busRef;
				pw.printf("%s.%-d, +j,", ckt.getBusList().get(j), ckt.getMapNodeToBus(i).nodeNum);
			}
			pw.println();*/

			for (i = 1; i <= ckt.getNumNodes(); i++) {
				j =  ckt.getMapNodeToBus(i).busRef;
				pw.printf("%s.%-d, ", ckt.getBusList().get(j - 1).toUpperCase(), ckt.getMapNodeToBus(i).nodeNum);
				for (j = 0; j < ckt.getNumNodes(); j++) {
					re = 0.0;
					im = 0.0;
					// search for a non-zero element [i, j]
					for (p = colPtr[j]; p < colPtr[j + 1]; p++) {  // TODO: check zero based indexing
						if (rowIdx[p] == i - 1) {
							re = cVals[p].getReal();
							im = cVals[p].getImaginary();
						}
					}
					pw.printf("%-13.10g, +j %-13.10g,", re, im);
				}
				pw.println();
			}

			DSS.globalResult = fileName;

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting Y: " + e.getMessage(), -1);
		}
	}

	/**
	 * Export symmetrical component impedances at each bus.
	 */
	public static void exportSeqZ(String fileName) {
		FileWriter fw;
		PrintWriter pw;
		int i;
		Bus bus;
		Complex Z1, Z0;
		double X1R1, X0R0;

		Circuit ckt = DSS.activeCircuit;

		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			pw.println("Bus,  NumNodes, R1, X1, R0, X0, Z1, Z0, \"X1/R1\", \"X0/R0\"");

			for (i = 0; i < ckt.getNumBuses(); i++) {
				bus = ckt.getBus(i);
				Z1 = bus.getZsc1();
				Z0 = bus.getZsc0();
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

				pw.printf("\"%s\", %d, %10.6g, %10.6g, %10.6g, %10.6g, %10.6g, %10.6g, %8.4g, %8.4g",
						ckt.getBusList().get(i).toUpperCase(), bus.getNumNodesThisBus(),
						Z1.getReal(), Z1.getImaginary(), Z0.getReal(), Z0.getImaginary(),
						Z1.abs(), Z0.abs(), X1R1, X0R0);
				pw.println();
			}

			DSS.globalResult = fileName;

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting symmetrical component impedances: " + e.getMessage(), -1);
		}
	}

	public static void exportUUIDs(String fileName) {
		FileWriter fw;
		PrintWriter pw;
		LineCode clsCode;
		LineGeometry clsGeom;
		WireData clsWire;
		XfmrCode clsXfmr;
		NamedObject named;

		Circuit ckt = DSS.activeCircuit;

		try {
			clsCode = (LineCode)     DSS.DSSClassList.get(DSS.classNames.find("linecode"));
			clsWire = (WireData)     DSS.DSSClassList.get(DSS.classNames.find("wiredata"));
			clsGeom = (LineGeometry) DSS.DSSClassList.get(DSS.classNames.find("linegeometry"));
			clsXfmr = (XfmrCode)     DSS.DSSClassList.get(DSS.classNames.find("xfmrcode"));

			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			pw.printf("%s.%s %s", ckt.getDSSClassName(), ckt.getLocalName(), ckt.getID());
			pw.println();

			for (NamedObject pNamed : ckt.getCktElements()) {
				pw.printf("%s.%s %s", pNamed.getDSSClassName(), pNamed.getLocalName(), pNamed.getID());
				pw.println();
			}

			named = (NamedObject) clsCode.getElementList().getFirst();
			while (named != null) {
				pw.printf("%s.%s %s", named.getDSSClassName(), named.getLocalName(), named.getID());
				pw.println();
				named = (NamedObject) clsCode.getElementList().getNext();
			}

			named = (NamedObject) clsWire.getElementList().getFirst();
			while (named != null) {
				pw.printf("%s.%s %s", named.getDSSClassName(), named.getLocalName(), named.getID());
				pw.println();
				named = (NamedObject) clsWire.getElementList().getNext();
			}

			named = (NamedObject) clsGeom.getElementList().getFirst();
			while (named != null) {
				pw.printf("%s.%s %s", named.getDSSClassName(), named.getLocalName(), named.getID());
				pw.println();
				named = (NamedObject) clsGeom.getElementList().getNext();
			}

			named = (NamedObject) clsXfmr.getElementList().getFirst();
			while (named != null) {
				pw.printf("%s.%s %s", named.getDSSClassName(), named.getLocalName(), named.getID());
				pw.println();
				named = (NamedObject) clsXfmr.getElementList().getNext();
			}

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting UUIDs: " + e.getMessage(), -1);
		}
	}

	public static void exportCounts(String fileName) {
		FileWriter fw;
		PrintWriter pw;

		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			pw.println("Format: DSS Class Name = Instance Count");
			pw.println();
			for (DSSClass cls : DSS.DSSClassList) {
				pw.printf("%s = %d", cls.getClassName(), cls.getElementCount());
				pw.println();
			}

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting instance counts: " + e.getMessage(), -1);
		}
	}

	public static void exportSummary(String fileName) {
		FileWriter fw;
		PrintWriter pw;
		Complex cPower, cLosses;

		Circuit ckt = DSS.activeCircuit;

		try {
			if (new File(fileName).exists()) {
				fw = new FileWriter(fileName, true);  // append
				pw = new PrintWriter(fw);
			} else {
				fw = new FileWriter(fileName);
				pw = new PrintWriter(fw);

				// create and write the header
				pw.print("DateTime, CaseName");
				pw.print(", Status, Mode, Number, LoadMult, NumDevices, NumBuses, NumNodes");
				pw.print(", Iterations, ControlMode, ControlIterations");
				pw.print(", MostIterationsDone");
				if (ckt != null) {
					if (ckt.isSolved() && !ckt.isBusNameRedefined()) {
						pw.print(", Year, Hour, MaxPuVoltage, MinPuVoltage, TotalMW, TotalMvar");
						pw.print(", kWLosses, pctLosses, kvarLosses, Frequency");
					}
				}
				pw.println();
			}

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			pw.printf("\"%s\", ", sdf.format( cal.getTime() ));
			if (ckt != null) {
				pw.printf("%s, ", ckt.getCaseName());
			} else {
				pw.print("NONE, ");
			}

			if (ckt.isSolved()) {
				pw.print("Solved");
			} else {
				pw.print("Unsolved");
			}

			pw.printf(", %s",    Util.getSolutionModeID());
			pw.printf(", %d",    ckt.getSolution().getNumberOfTimes());
			pw.printf(", %8.3f", ckt.getLoadMultiplier());
			pw.printf(", %d",    ckt.getNumDevices());
			pw.printf(", %d",    ckt.getNumBuses());
			pw.printf(", %d",    ckt.getNumNodes());
			pw.printf(", %d",    ckt.getSolution().getIteration());
			pw.printf(", %s",    Util.getControlModeID());
			pw.printf(", %d",    ckt.getSolution().getControlIteration());
			pw.printf(", %d",    ckt.getSolution().getMostIterationsDone());
			if (ckt != null) {
				if (ckt.isSolved() && !ckt.isBusNameRedefined()) {
					pw.printf(", %d",    ckt.getSolution().getYear());
					pw.printf(", %d",    ckt.getSolution().getIntHour());
					pw.printf(", %-.5g", Util.getMaxPUVoltage());
					pw.printf(", %-.5g", Util.getMinPUVoltage(true));
					cPower = Util.getTotalPowerFromSources().multiply(0.000001);  // MVA
					pw.printf(", %-.6g", cPower.getReal());
					pw.printf(", %-.6g", cPower.getImaginary());
					cLosses = ckt.getLosses().multiply(0.000001);
					if (cPower.getReal() != 0.0) {
						pw.printf(", %-.6g, %-.4g", cLosses.getReal(), cLosses.getReal() / cPower.getReal() * 100.0);
					} else {
						pw.printf("Total Active Losses:   ****** MW, (**** %%)");
					}
					pw.printf(", %-.6g", cLosses.getImaginary());
					pw.printf(", %g", ckt.getSolution().getFrequency());
				}
			}
			pw.println();

			DSS.globalResult = fileName;

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting summary: " + e.getMessage(), -1);
		}
	}

	/**
	 * Export bus x, y coordinates.
	 */
	public static void exportBusCoords(String fileName) {
		FileWriter fw;
		PrintWriter pw;
		int i;

		Circuit ckt = DSS.activeCircuit;

		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			for (i = 0; i < ckt.getNumBuses(); i++) {
				if (ckt.getBus(i).isCoordDefined()) {
					pw.printf("%s, %-13.11g, %-13.11g",
						Util.checkForBlanks(ckt.getBusList().get(i).toUpperCase()),
						ckt.getBus(i).getX(), ckt.getBus(i).getY());
					pw.println();
				}
			}

			DSS.globalResult = fileName;

			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting bus coordinates: " + e.getMessage(), -1);
		}
	}

	public static void exportProfile(String fileName, int phasesToPlot) {
		int iEnergyMeter;
		EnergyMeterObj activeEnergyMeter;
		CktElement presentCktElement;
		Bus bus1, bus2;
		double puV1 = 0, puV2 = 0;
		int phs;
		int iphs2;
		String s;
		FileWriter fw;
		PrintWriter pw;
		int lineType = 0;

		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			pw.print("Name, Distance1, puV1, Distance2, puV2, Color, Thickness, Linetype, Markcenter, Centercode, NodeCode, NodeWidth,");

			/* New graph created before this routine is entered */
			if (phasesToPlot == ProfilePlot.LL.phs())
				s = "L-L Voltage Profile";
			else if (phasesToPlot == ProfilePlot.LLALL.phs())
				s = "L-L Voltage Profile";
			else if (phasesToPlot == ProfilePlot.LLPRI.phs())
				s = "L-L Voltage Profile";
			else
				s = "L-N Voltage Profile";

			pw.println("Title=" + s + ", Distance in km");

			iEnergyMeter = DSS.energyMeterClass.getFirst();
			while (iEnergyMeter >= 0) {
				activeEnergyMeter = (EnergyMeterObj) DSS.energyMeterClass.getActiveObj();

				/* Go down each branch list and draw a line */
				presentCktElement = (CktElement) activeEnergyMeter.getBranchList().getFirst();
				while (presentCktElement != null) {
					if (Util.isLineElement(presentCktElement)) {
						bus1 = ckt.getBus(presentCktElement.getTerminal(0).getBusRef() - 1);
						bus2 = ckt.getBus(presentCktElement.getTerminal(1).getBusRef() - 1);

						/* Now determine which phase to plot */
						if ((bus1.getKVBase() > 0.0) && (bus2.getKVBase() > 0.0)) {
							/* 3ph only */
							if (phasesToPlot == ProfilePlot.THREEPH.phs()) {
								if ((presentCktElement.getNumPhases() >= 3) && (bus1.getKVBase() > 1.0))
									for (phs = 1; phs <= 3; phs++) {
										puV1 = sol.getNodeV( bus1.getRef(bus1.findIdx(phs)) ).abs() / bus1.getKVBase() / 1000.0;
										puV2 = sol.getNodeV( bus2.getRef(bus2.findIdx(phs)) ).abs() / bus2.getKVBase() / 1000.0;

										writeNewLine(pw, presentCktElement.getName(), bus1.getDistFromMeter(), puV1, bus2.getDistFromMeter(),
												puV2, phs, 2, 0, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth());
									}
							}
							/* Plot all phases present (between 1 and 3) */
							else if (phasesToPlot == ProfilePlot.ALL.phs()) {
								for (phs = 1; phs <= 3; phs++)
									if ((bus1.findIdx(phs) >= 0) && (bus2.findIdx(phs) >= 0)) {
										lineType = (bus1.getKVBase() < 1.0) ? 2 : 0;
										puV1 = sol.getNodeV( bus1.getRef(bus1.findIdx(phs)) ).abs() / bus1.getKVBase() / 1000.0;
										puV2 = sol.getNodeV( bus2.getRef(bus2.findIdx(phs)) ).abs() / bus2.getKVBase() / 1000.0;

										writeNewLine(pw, presentCktElement.getName(), bus1.getDistFromMeter(), puV1, bus2.getDistFromMeter(),
												puV2, phs, 2, lineType, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth());
									}
							}
							/* Plot all phases present (between 1 and 3) for primary only */
							else if (phasesToPlot == ProfilePlot.ALLPRI.phs()) {
								if (bus1.getKVBase() > 1.0)
									for (phs = 1; phs <= 3; phs++)
										if ((bus1.findIdx(phs) >= 0) && (bus2.findIdx(phs) >= 0)) {
											lineType = (bus1.getKVBase() < 1.0) ? 2 : 0;
											puV1 = sol.getNodeV( bus1.getRef(bus1.findIdx(phs)) ).abs() / bus1.getKVBase() / 1000.0;
											puV2 = sol.getNodeV( bus2.getRef(bus2.findIdx(phs)) ).abs() / bus2.getKVBase() / 1000.0;

											writeNewLine(pw, presentCktElement.getName(), bus1.getDistFromMeter(), puV1, bus2.getDistFromMeter(),
													puV2, phs, 2, lineType, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth());
										}
							} else if (phasesToPlot == ProfilePlot.LL.phs()) {
								if (presentCktElement.getNumPhases() >= 3)
									for (phs = 1; phs <= 3; phs++) {
										iphs2 = phs + 1;
										if (iphs2 > 3) iphs2 = 1;
										if ((bus1.findIdx(phs) >= 0) && (bus2.findIdx(phs) >= 0) &&
												(bus1.findIdx(iphs2) >= 0) && (bus2.findIdx(iphs2) >= 0)) {
											lineType = (bus1.getKVBase() < 1.0) ? 2 : 0;
											puV1 = sol.getNodeV( bus1.getRef(bus1.findIdx(phs)) ).subtract( sol.getNodeV(bus1.getRef(bus1.findIdx(iphs2))) ).abs() / bus1.getKVBase() / 1732.0;
											puV2 = sol.getNodeV( bus2.getRef(bus2.findIdx(phs)) ).subtract( sol.getNodeV(bus2.getRef(bus2.findIdx(iphs2))) ).abs() / bus2.getKVBase() / 1732.0;

											writeNewLine(pw, presentCktElement.getName(), bus1.getDistFromMeter(), puV1, bus2.getDistFromMeter(), puV2,
													phs, 2, lineType, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth());
										}
									}
							} else if (phasesToPlot == ProfilePlot.LLALL.phs()) {
								for (phs = 1; phs <= 3; phs++) {
									iphs2 = phs + 1;
									if (iphs2 > 3) iphs2 = 1;
									if ((bus1.findIdx(phs) >= 0) && (bus2.findIdx(phs) >= 0) &&
											(bus1.findIdx(iphs2) >= 0) && (bus2.findIdx(iphs2) >= 0)) {
										lineType = (bus1.getKVBase() < 1.0) ? 2 : 0;
										puV1 = sol.getNodeV( bus1.getRef(bus1.findIdx(phs)) ).subtract( sol.getNodeV(bus1.getRef(bus1.findIdx(iphs2))) ).abs() / bus1.getKVBase() / 1732.0;
										puV2 = sol.getNodeV( bus2.getRef(bus2.findIdx(phs)) ).subtract( sol.getNodeV(bus2.getRef(bus2.findIdx(iphs2))) ).abs() / bus2.getKVBase() / 1732.0;

										writeNewLine(pw, presentCktElement.getName(), bus1.getDistFromMeter(), puV1, bus2.getDistFromMeter(), puV2,
												phs, 2, lineType, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth());
									}
								}
							} else if (phasesToPlot == ProfilePlot.LLPRI.phs()) {
								if (bus1.getKVBase() > 1.0) {
									for (phs = 1; phs <= 3; phs++) {
										iphs2 = phs + 1;
										if (iphs2 > 3) iphs2 = 1;
										if ((bus1.findIdx(phs) >= 0) && (bus2.findIdx(phs) >= 0) &&
												(bus1.findIdx(iphs2) >= 0) && (bus2.findIdx(iphs2) >= 0)) {
											lineType = (bus1.getKVBase() < 1.0) ? 2 : 0;
											puV1 = sol.getNodeV( bus1.getRef(bus1.findIdx(phs)) ).subtract( sol.getNodeV(bus1.getRef(bus1.findIdx(iphs2))) ).abs() / bus1.getKVBase() / 1732.0;
											puV2 = sol.getNodeV( bus2.getRef(bus2.findIdx(phs)) ).subtract( sol.getNodeV(bus2.getRef(bus2.findIdx(iphs2))) ).abs() / bus2.getKVBase() / 1732.0;

											writeNewLine(pw, presentCktElement.getName(), bus1.getDistFromMeter(), puV1, bus2.getDistFromMeter(),
													puV2, phs, 2, lineType, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth());
										}
									}
								}
							} else {  // plot just the selected phase
								phs = phasesToPlot;
								if ((bus1.findIdx(phs) >= 0) && (bus2.findIdx(phs) >= 0)) {
									lineType = (bus1.getKVBase() < 1.0) ? 2 : 0;
									puV1 = ckt.getSolution().getNodeV( bus1.getRef(bus1.findIdx(phs)) ).abs() / bus1.getKVBase() / 1000.0;
									puV2 = ckt.getSolution().getNodeV( bus2.getRef(bus2.findIdx(phs)) ).abs() / bus2.getKVBase() / 1000.0;

									writeNewLine(pw, presentCktElement.getName(), bus1.getDistFromMeter(), puV1, bus2.getDistFromMeter(),
											puV2, phs, 2, lineType, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth());
								}
							}
						}
					}
					presentCktElement = (CktElement) activeEnergyMeter.getBranchList().goForward();
				}
				iEnergyMeter = DSS.energyMeterClass.getNext();
			}

			DSS.globalResult = fileName;

			fw.close();
			pw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting profile: " + e.getMessage(), -1);
		}

	}

	private static void writeNewLine(PrintWriter pw,
			String name, double distFromMeter1, double puV1, double distFromMeter2, double puV2,
			int colorCode, int thickness, int lineType,
			int markCenter,
			int centerMarkerCode, int nodeMarkerCode, int nodeMarkerWidth) {

		pw.printf("%s, %.6g, %.6g, %.6g, %.6g,", name.toUpperCase(), distFromMeter1, puV1, distFromMeter2, puV2);
		pw.printf("%d, %d, %d, ", colorCode, thickness, lineType);
		pw.printf("%d, ", markCenter);
		pw.printf("%d, %d, %d", centerMarkerCode,  nodeMarkerCode, nodeMarkerWidth);
		pw.println();
	}

	/**
	 * Exports the present set of event strings.
	 *
	 * @param fileNm
	 */
	public static void exportEventLog(String fileNm) {
		PrintWriter pw;
		try {
			pw = new PrintWriter(fileNm);
			pw.println( DSS.eventStrings );
			DSS.globalResult = fileNm;
			pw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered exporting event log: " + e.getMessage(), -1);
		}
	}

	public static void exportCDPSM(String fileName, CIMProfileChoice profile) {
		throw new UnsupportedOperationException();
	}

}
