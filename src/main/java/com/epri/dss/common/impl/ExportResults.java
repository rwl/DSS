package com.epri.dss.common.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.epri.dss.common.Bus;
import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.DSSClass;
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
	public static void exportSeqVoltages(String fileName) {

		FileWriter f;
		PrintWriter writer;
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
			f = new FileWriter(fileName);
			writer = new PrintWriter(f);

			writer.println("Bus,  V1,  p.u.,Base kV, V2, %V2/V1, V0, %V0/V1, Vresidual, %NEMA");
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

				if (ckt.getBuses()[i].getKVBase() != 0.0) {
					Vpu = 0.001 * V1 / ckt.getBuses()[i].getKVBase();
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

				writer.printf("\"%s\", %10.6g, %9.5g, %8.2f, %10.6g, %8.4g, %10.6g, %8.4g, %10.6g, %8.4g",
						ckt.getBusList().get(i), V1, Vpu, (ckt.getBuses()[i].getKVBase() * DSSGlobals.SQRT3), V2, V2V1, V0, V0V1, Vresidual.abs(), V_NEMA);

			}

			DSSGlobals.getInstance().setGlobalResult(fileName);

			writer.close();
			f.close();
		} catch (IOException e) {
			// FIXME: handle exception
		}
	}

	/**
	 * Export symmetrical component bus voltages.
	 */
	public static void exportVoltages(String fileName) {
		int maxNumNodes;
		FileWriter f;
		PrintWriter writer;
		int i, j, jj;
		String busName;
		Complex volts;
		int nref;
		int nodeIdx = 0;
		double Vmag, Vpu;
		Bus bus;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		/* Find max nodes at a bus */
		maxNumNodes = 0;
		for (i = 0; i < ckt.getNumBuses(); i++)
			maxNumNodes = Math.max(maxNumNodes, ckt.getBuses()[i].getNumNodesThisBus());

		try {
			f = new FileWriter(fileName);
			writer = new PrintWriter(f);

			writer.print("Bus, BasekV");
			for (i = 0; i < maxNumNodes; i++)
				writer.printf(", Node%d, Magnitude%d, Angle%d, pu%d", i, i, i, i);
			writer.println();

			for (i = 0; i < ckt.getNumBuses(); i++) {
				busName = ckt.getBusList().get(i);
				writer.printf("\"%s\", %.5g", busName, ckt.getBuses()[i].getKVBase() * DSSGlobals.SQRT3);

				jj = 0;
				bus = ckt.getBuses()[i];
				for (j = 0; j < bus.getNumNodesThisBus(); j++) {
					while (nodeIdx <= 0) {  // TODO Check zero based indexing
						nodeIdx = bus.findIdx(jj);  // try to find nodes in order
						jj += 1;
					}
					nref = bus.getRef(nodeIdx);
					volts = sol.getNodeV()[nref];
					Vmag = volts.abs();
					if (bus.getKVBase() != 0.0) {
						Vpu = 0.001 * Vmag / bus.getKVBase();
					} else {
						Vpu = 0.0;
					}

					writer.printf(", %d, %10.6g, %6.1f, %9.5g",
							bus.getNum(nodeIdx), Vmag, volts.degArg(), Vpu);
				}
				/* Zero fill row */
				for (j = ckt.getBuses()[i].getNumNodesThisBus(); j < maxNumNodes + 1; j++)  // TODO Check zero based indexing
					writer.print(", 0, 0, 0, 0");
				writer.println();
			}

			DSSGlobals.getInstance().setGlobalResult(fileName);

			writer.close();
			f.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	private static void calcAndWriteSeqCurrents(PrintWriter writer, int j, CktElement pElem,
			Complex[] cBuffer, boolean doRatings) {

		double I0, I1, I2, I2I1, I0I1, INormal, IEmerg;
		int i, k, nCond;
		Complex[] Iph = new Complex[3];
		Complex[] I012 = new Complex[3];
		Complex IResidual;
		double I_NEMA;

		nCond = pElem.getNConds();
		if (pElem.getNPhases() >= 3) {

			for (i = 0; i < 3; i++) {  // TODO Check one based indexing
				k = (j - 1) * nCond + i;
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

		if (doRatings && (j == 0)) {  // Only for 1st Terminal   TODO Check zero based indexing
			INormal = ((PDElement) pElem).getNormAmps();
			if (INormal > 0.0) INormal = I1 / INormal * 100.0;
			IEmerg = ((PDElement) pElem).getEmergAmps();
			if (IEmerg > 0.0) IEmerg = I1 / IEmerg * 100.0;
		} else {
			INormal = 0.0;
			IEmerg = 0.0;
		}

		IResidual = Complex.ZERO;
		for (i = 0; i < nCond; i++)
			IResidual = IResidual.add(cBuffer[i]);

		writer.printf("\"%s\", %3d, %10.6g, %8.4g, %8.4g, %10.6g, %8.4g, %10.6g, %8.4g, %10.6g, %8.4g",
			(pElem.getDSSClassName() + "." + pElem.getName()), j, I1, INormal, IEmerg, I2, I2I1, I0, I0I1, IResidual.abs(), I_NEMA);
	}

	public static void exportSeqCurrents(String fileName) {
		FileWriter f;
		PrintWriter writer;
		int j;
		Complex[] cBuffer;  // allocate to max total conductors

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			f = new FileWriter(fileName);
			writer = new PrintWriter(f);

			/* Sequence currents */
			writer.println("Element, Terminal,  I1, %Normal, %Emergency, I2, %I2/I1, I0, %I0/I1, Iresidual, %NEMA");

			/* Allocate cBuffer big enough for largest circuit element */
			cBuffer = new Complex[Utilities.getMaxCktElementSize()];

			// sources first
			for (CktElement pElem : ckt.getSources()) {
				if (pElem.isEnabled()) {
					pElem.getCurrents(cBuffer);
					for (j = 0; j < pElem.getNTerms(); j++)
						calcAndWriteSeqCurrents(writer, j, pElem, cBuffer, false);
				}
			}


			// PD elements next
			for (PDElement PDElem : ckt.getPDElements()) {
				if (PDElem.isEnabled())
					PDElem.getCurrents(cBuffer);
				for (j = 0; j < PDElem.getNTerms(); j++)
					calcAndWriteSeqCurrents(writer, j, PDElem, cBuffer, true);
			}

			// PC elements next
			for (PCElement PCElem : ckt.getPCElements()) {
				if (PCElem.isEnabled()) {
					PCElem.getCurrents(cBuffer);
					for (j = 0; j < PCElem.getNTerms(); j++)
						calcAndWriteSeqCurrents(writer, j, PCElem, cBuffer, false);
				}
			}

			// faults next
			for (CktElement pElem : ckt.getFaults()) {
				if (pElem.isEnabled())
					pElem.getCurrents(cBuffer);
				for (j = 0; j < pElem.getNTerms(); j++)
					calcAndWriteSeqCurrents(writer, j, pElem, cBuffer, false);
			}

			DSSGlobals.getInstance().setGlobalResult(fileName);

			writer.close();
			f.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	private static void calcAndWriteCurrents(PrintWriter writer, CktElement pElem,
			Complex[] cBuffer, int condWidth, int termWidth) {
		int i, j, k;
		Complex IResid;

		k = 0;
		writer.printf("%s", pElem.getDSSClassName()+"."+pElem.getName());
		for (j = 0; j < pElem.getNTerms(); j++) {
			IResid = Complex.ZERO;
			for (i = 0; i < pElem.getNConds(); i++) {
				k += 1;
				writer.printf(", %10.6g, %8.2f", cBuffer[k].abs(), cBuffer[k].degArg());
				IResid = IResid.add(cBuffer[k]);
			}
			for (i = pElem.getNConds(); i < condWidth; i++)  // TODO Check zero based indexing
				writer.printf(", %10.6g, %8.2f", 0.0, 0.0);
			writer.printf(", %10.6g, %8.2f", IResid.abs(), IResid.degArg());
		}

		/* Filler if no. terms less than termwidth */
		for (j = pElem.getNTerms(); j < termWidth; j++)
			for (i = 0; i < condWidth + 1; i++)
				writer.printf(", %10.6g, %8.2f", 0.0, 0.0);
		writer.println();
	}

	private static void calcAndWriteMaxCurrents(PrintWriter writer, PDElement pElem, Complex[] cBuffer) {
		int i;
		double currMag, maxCurrent;
		Complex localPower;

		writer.printf("%s.%s", pElem.getDSSClassName(), pElem.getName());
		maxCurrent = 0.0;
		for (i = 0; i < pElem.getNPhases(); i++) {
			currMag = cBuffer[i].abs();
			if (currMag  > maxCurrent)
				maxCurrent = currMag;
		}

		localPower = pElem.getPower(0).multiply(0.001);  // TODO Check zero based indexing
		if ((pElem.getNormAmps() == 0.0) || (pElem.getEmergAmps() == 0.0)) {
			writer.printf(", %10.6g, %8.2f, %8.2f", maxCurrent, 0.0, 0.0);
		} else {
			writer.printf(", %10.6g, %8.2f, %8.2f", maxCurrent, maxCurrent / pElem.getNormAmps() * 100.0, maxCurrent / pElem.getEmergAmps() * 100.0);
		}
		writer.printf(", %10.6g, %10.6g, %d, %d, %d", localPower.getReal(), localPower.getImaginary(), pElem.getNumCustomers(), pElem.getTotalCustomers(), pElem.getNPhases());
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		writer.printf(", %-.3g ", ckt.getBuses()[ ckt.getMapNodeToBus()[pElem.getNodeRef()[0]].busRef ].getKVBase());
		writer.println();
	}

	public static void exportCurrents(String fileName) {
		FileWriter f;
		PrintWriter writer;
		Complex[] cBuffer;
		int maxCond, maxTerm;
		int i, j;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			f = new FileWriter(fileName);
			writer = new PrintWriter(f);

			cBuffer = new Complex[Utilities.getMaxCktElementSize()];

			/* Calculate the width of the file */
			maxCond = 1;
			maxTerm = 2;
			for (CktElement pElem : ckt.getCktElements()) {
				if (pElem.getNTerms() > maxTerm) maxTerm = pElem.getNTerms();
				if (pElem.getNConds() > maxCond) maxCond = pElem.getNConds();
			}

			/* Branch currents */
			writer.print("Element");
			for (i = 0; i < maxTerm; i++) {
				for (j = 0; j < maxCond; j++)
					writer.printf(", I%d_%d, Ang%d_%d", i, j, i, j);
				writer.printf(", Iresid%d, AngResid%d", i, i);
			}
			writer.println();

			// sources first
			for (CktElement pElem : ckt.getSources()) {
				if (pElem.isEnabled()) {
					pElem.getCurrents(cBuffer);
					calcAndWriteCurrents(writer, pElem, cBuffer, maxCond, maxTerm);
				}
			}

			// PD elements next
			for (CktElement pElem : ckt.getPDElements()) {
				if (pElem.isEnabled()) {
					pElem.getCurrents(cBuffer);
					calcAndWriteCurrents(writer, pElem, cBuffer, maxCond, maxTerm);
				}
			}

			// faults
			for (CktElement pElem : ckt.getFaults()) {
				if (pElem.isEnabled()) {
					pElem.getCurrents(cBuffer);
					calcAndWriteCurrents(writer, pElem, cBuffer, maxCond, maxTerm);
				}
			}

			// PC elements next
			for (CktElement pElem : ckt.getPCElements()) {
				if (pElem.isEnabled()) {
					pElem.getCurrents(cBuffer);
					calcAndWriteCurrents(writer, pElem, cBuffer, maxCond, maxTerm);
				}
			}

			DSSGlobals.getInstance().setGlobalResult(fileName);

			writer.close();
			f.close();
		} catch (IOException e) {
			cBuffer = null;
			// TODO: handle exception
		}
	}

	/**
	 * Opt = 0: kVA
	 * Opt = 1: MVA
	 */
	public static void exportPowers(String fileName, int opt) {
		FileWriter f;
		PrintWriter writer;
		int Nterm, j;
		Complex S;
		String sep = ", ";

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			f = new FileWriter(fileName);
			writer = new PrintWriter(f);

			switch (opt) {
			case 1:
				writer.println("Element, Terminal, P(MW), Q(Mvar), P_Normal, Q_Normal, P_Emergency, Q_Emergency");
				break;
			default:
				writer.println("Element, Terminal, P(kW), Q(kvar),  P_Normal, Q_Normal, P_Emergency, Q_Emergency");
				break;
			}

			// PD elements first
			for (PDElement PDElem : ckt.getPDElements()) {
				if (PDElem.isEnabled()) {
					Nterm = PDElem.getNTerms();

					for (j = 0; j < Nterm; j++) {
						writer.print(Utilities.pad("\""+PDElem.getDSSClassName() + "." + PDElem.getName()+"\"", 24) + sep + j);

						S = PDElem.getPower(j);
						if (opt == 1) S = S.multiply(0.001);
						writer.print(sep + S.getReal() * 0.001);
						writer.print(sep + S.getImaginary() * 0.001);
						if (j == 0) {
							S = PDElem.getExcessKVANorm(0);  // TODO Check zero based indexing
							if (opt == 1) S = S.multiply(0.001);
							writer.print(sep + Math.abs(S.getReal()));
							writer.print(sep + Math.abs(S.getImaginary()));
							S = PDElem.getExcessKVAEmerg(0);  // TODO Check zero based indexing
							if (opt == 1) S = S.multiply(0.001);
							writer.print(sep + Math.abs(S.getReal()));
							writer.print(sep + Math.abs(S.getImaginary()));
						}
						writer.println();
					}
				}
			}

			// PC elements next
			for (PCElement PCElem : ckt.getPCElements()) {
				if (PCElem.isEnabled()) {
					Nterm = PCElem.getNTerms();

					for (j = 0; j < Nterm; j++) {
						writer.print(Utilities.pad("\""+PCElem.getDSSClassName() + "." + PCElem.getName()+"\"", 24) + sep + j);
						S = PCElem.getPower(j);
						if (opt == 1) S = S.multiply(0.001);
						writer.print(sep + S.getReal() * 0.001);
						writer.print(sep + S.getImaginary() * 0.001);
						writer.println();
					}
				}
			}

			DSSGlobals.getInstance().setGlobalResult(fileName);

			writer.close();
			f.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void exportLosses(String fileName) {
		FileWriter f;
		PrintWriter writer;
		double[] S_total = new double[2];
		double[] S_Load = new double[2];
		double[] S_NoLoad = new double[2];

		DSSGlobals globals = DSSGlobals.getInstance();

		try {
			f = new FileWriter(fileName);
			writer = new PrintWriter(f);

			writer.println("Element,  Total(W), Total(var),  I2R(W), I2X(var), No-load(W), No-load(var)");
			for (PDElement PDElem : globals.getActiveCircuit().getPDElements()) {
				if (PDElem.isEnabled()) {
					PDElem.getLosses(S_total, S_Load, S_NoLoad);
					writer.printf("%s.%s, %.7g, %.7g, %.7g, %.7g, %.7g, %.7g", PDElem.getParentClass().getName(), PDElem.getName(), S_total[0], S_total[1], S_Load[0], S_Load[1], S_NoLoad[0], S_NoLoad[1]);
					writer.println();
				}
			}

			globals.setGlobalResult(fileName);

			writer.close();
			f.close();
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
	public static void exportPbyphase(String fileName, int opt) {
		FileWriter f;
		PrintWriter writer;
		int i;
		Complex S;

		DSSGlobals globals = DSSGlobals.getInstance();

		try {
			f = new FileWriter(fileName);
			writer = new PrintWriter(f);

			switch (opt) {
			case 1:
				writer.println("Element, NumTerminals, NumConductors, NumPhases, MW1, Mvar1, MW2, Mvar2, MW3, Mvar3, ... ");
				break;
			default:
				writer.println("Element, NumTerminals, NumConductors, NumPhases, kW1, kvar1, kW2, kvar2, kW3, kvar3, ... ");
				break;
			}

			// PD elements first
			for (PDElement PDElem : globals.getActiveCircuit().getPDElements()) {
				if (PDElem.isEnabled()) {
					PDElem.computeITerminal();
					PDElem.computeVTerminal();
					writer.printf("\"%s.%s\", %d, %d, %d", PDElem.getDSSClassName(), PDElem.getName(), PDElem.getNTerms(), PDElem.getNConds(), PDElem.getNPhases());
					for (i = 0; i < PDElem.getYorder(); i++) {
						S = PDElem.getVTerminal()[i].multiply( PDElem.getITerminal()[i].conjugate() ).multiply(0.001);
						if (opt == 1) S = S.multiply(0.001);  // convert to MVA
						writer.printf(", %10.3f, %10.3f", S.getReal(), S.getImaginary());
					}
					writer.println();
				}
			}

			// PC elements next
			for (PCElement PCElem : globals.getActiveCircuit().getPCElements()) {
				if (PCElem.isEnabled()) {
					PCElem.computeITerminal();
					PCElem.computeVTerminal();
					writer.printf("\"%s.%s\", %d, %d, %d", PCElem.getDSSClassName(), PCElem.getName(), PCElem.getNTerms(), PCElem.getNConds(), PCElem.getNPhases());
					for (i = 0; i < PCElem.getYorder(); i++) {
						S = PCElem.getVTerminal()[i].multiply( PCElem.getITerminal()[i].conjugate() ).multiply(0.001);
						if (opt == 1) S = S.multiply(0.001);  // convert to MVA
						writer.printf(", %10.3f, %10.3f", S.getReal(), S.getImaginary());
					}
					writer.println();
				}
			}

			globals.setGlobalResult(fileName);

			writer.close();
			f.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/**
	 * opt = 0: kVA
	 * opt = 1: MVA
	 */
	public static void exportSeqPowers(String fileName, int opt) {
		FileWriter f;
		PrintWriter writer;
		Complex[] cBuffer;
		int NCond, nTerm, i, j, k;
		Complex volts;
		Complex S;
		int nref;
		Complex[] Vph = new Complex[3];
		Complex[] V012 = new Complex[3];
		Complex[] Iph = new Complex[3];
		Complex[] I012 = new Complex[3];
		String sep = ", ";

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			f = new FileWriter(fileName);
			writer = new PrintWriter(f);

			cBuffer = new Complex[Utilities.getMaxCktElementSize()];

			switch (opt) {
			case 1:
				writer.println("Element, Terminal, P1(MW), Q1(Mvar), P2, Q2, P0, Q0, P_Normal, Q_Normal, P_Emergency, Q_Emergency");
				break;
			default:
				writer.println("Element, Terminal, P1(kW), Q1(kvar), P2, Q2, P0, Q0, P_Normal, Q_Normal, P_Emergency, Q_Emergency");
				break;
			}

			// PD elements first
			for (PDElement PDElem : ckt.getPDElements()) {
				if (PDElem.isEnabled()) {
					NCond = PDElem.getNConds();
					nTerm = PDElem.getNTerms();
					PDElem.getCurrents(cBuffer);

					for (j = 0; j < nTerm; j++) {
						writer.print(Utilities.pad("\""+PDElem.getDSSClassName() + "." + PDElem.getName()+"\"", 24) + sep + j);
						for (i = 0; i < PDElem.getNPhases(); i++) {
							k = (j - 1) * NCond + i;
							nref = PDElem.getNodeRef()[k];
							volts = ckt.getSolution().getNodeV()[nref];
							Iph[i] = cBuffer[k];
							Vph[i] = volts;
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
						writer.print(sep + S.getReal() * 0.003);
						writer.print(sep + S.getImaginary() * 0.003);

						S = V012[2].multiply( I012[2].conjugate() );
						if (opt == 1) S = S.multiply(0.001);
						writer.print(sep + S.getReal() * 0.003);
						writer.print(sep + S.getImaginary() * 0.003);

						S = V012[0].multiply( I012[0].conjugate() );
						if (opt == 1) S = S.multiply(0.001);
						writer.print(sep + S.getReal() * 0.003);
						writer.print(sep + S.getImaginary() * 0.003);

						if (j == 0) {
							S = PDElem.getExcessKVANorm(0);
							if (opt == 1) S = S.multiply(0.001);
							writer.print(sep + Math.abs(S.getReal()));
							writer.print(sep + Math.abs(S.getImaginary()));
							S = PDElem.getExcessKVAEmerg(0);
							if (opt == 1) S = S.multiply(0.001);
							writer.print(sep + Math.abs(S.getReal()));
							writer.print(sep + Math.abs(S.getImaginary()));
						}
						writer.println();
					}
				}
			}

			// PC elements next
			for (PCElement PCElem : ckt.getPCElements()) {
				if (PCElem.isEnabled()) {
					NCond = PCElem.getNConds();
					nTerm = PCElem.getNTerms();
					PCElem.getCurrents(cBuffer);

					for (j = 0; j < nTerm; j++) {
						writer.print(Utilities.pad("\""+PCElem.getDSSClassName() + "." + PCElem.getName()+"\"", 24) + sep + j);
						for (i = 0; i < PCElem.getNPhases(); i++) {
							k = (j - 1) * NCond + i;
							nref = PCElem.getNodeRef()[k];
							volts = ckt.getSolution().getNodeV()[nref];
							Iph[i] = cBuffer[k];
							Vph[i] = volts;
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
						writer.print(sep + S.getReal() * 0.003);
						writer.print(sep + S.getImaginary() * 0.003);

						S = V012[2].multiply( I012[2].conjugate() );
						if (opt == 1) S = S.multiply(0.001);
						writer.print(sep + S.getReal() * 0.003);
						writer.print(sep + S.getImaginary() * 0.003);

						S = V012[0].multiply( I012[0].conjugate() );
						if (opt == 1) S = S.multiply(0.001);
						writer.print(sep + S.getReal() * 0.003);
						writer.print(sep + S.getImaginary() * 0.003);

						writer.println();
					}
				}
			}

			DSSGlobals.getInstance().setGlobalResult(fileName);

			writer.close();
			f.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void exportFaultStudy(String fileName) {
		int i, iBus, iphs;
		CMatrix YFault;
		Complex[] VFault;  // big temp array
		FileWriter f;
		PrintWriter writer;
		Complex GFault;
		final String sep = ", ";
		double maxCurr, currMag;
		Bus bus;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			f = new FileWriter(fileName);
			writer = new PrintWriter(f);

			/* Set source voltage injection currents */

			/* All phase faults */
			writer.println("Bus,  3-Phase,  1-Phase,  L-L");
			for (iBus = 0; iBus < ckt.getNumBuses(); iBus++) {
				/* Bus Norton equivalent current, Isc has been previously computed */
				bus = ckt.getBuses()[iBus];
				writer.print(Utilities.pad(ckt.getBusList().get(iBus), 12));
				maxCurr = 0.0;
				for (i = 0; i < bus.getNumNodesThisBus(); i++) {
					if (maxCurr < bus.getBusCurrent()[i].abs())
						maxCurr = bus.getBusCurrent()[i].abs();
				}
				writer.print(sep + maxCurr);

				/* One phase faults */

				/* Solve for fault injection currents */

				YFault = new CMatrixImpl(bus.getNumNodesThisBus());
				VFault = new Complex[bus.getNumNodesThisBus()];

				/* Build YscTemp */

				GFault = new Complex(10000.0, 0.0);

				maxCurr = 0.0;

				for (iphs = 0; iphs < bus.getNumNodesThisBus(); iphs++) {
					YFault.copyFrom(bus.getYsc());
					YFault.addElement(iphs, iphs, GFault);

					/* Solve for injection currents */
					YFault.invert();
					YFault.MVMult(VFault, bus.getBusCurrent());  /* Gets voltage appearing at fault */

					currMag = VFault[iphs].multiply(GFault).abs();
					if (currMag > maxCurr) maxCurr = currMag;

				}
				/* Now, put it in the Css array where it belongs */
				writer.print(sep + maxCurr);

				VFault = null;
				YFault = null;

				/* Node-node faults */

				/* Bus Norton equivalent current, Isc has been previously computed */

				YFault = new CMatrixImpl(bus.getNumNodesThisBus());
				VFault = new Complex[bus.getNumNodesThisBus()];

				GFault = new Complex(10000.0, 0.0);

				maxCurr = 0.0;

				for (iphs = 0; iphs < bus.getNumNodesThisBus(); iphs++) {
					YFault.copyFrom(bus.getYsc());
					YFault.addElement(iphs, iphs, GFault);
					YFault.addElement(iphs + 1, iphs + 1, GFault);
					YFault.addElemSym(iphs, iphs + 1, GFault.negate());

					/* Solve for injection currents */
					YFault.invert();
					YFault.MVMult(VFault, bus.getBusCurrent());  /* Gets voltage appearing at fault */

					currMag = VFault[iphs].subtract( VFault[iphs + 1] ).multiply(GFault).abs();
					if (currMag > maxCurr) maxCurr = currMag;
				}
				/* Now, put it in the Css array where it belongs */

				writer.print(sep + maxCurr);

				VFault = null;
				YFault = null;

				writer.println();
			}

			DSSGlobals.getInstance().setGlobalResult(fileName);

			writer.close();
			f.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	private static void zeroTempXArray(double[] tempX) {
		for (int ii = 0; ii < 3; ii++)
			tempX[ii] = 0;
	}
	public static void exportEstimation(String fileName) {
		FileWriter f;
		PrintWriter writer;
		int i;
		double[] tempX = new double[3];  // temp number buffer

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			f = new FileWriter(fileName);
			writer = new PrintWriter(f);

			/* Do the energy meters first */
			writer.println("\"Energy Meters\" ");
			writer.println("\"energyMeter\", \"I1 Target\", \"I2 Target\", \"I3 Target\", \"I1 Calc\", \"I2 Calc\", \"I3 Calc\", \"I1 %Err\", \"I2 %Err\", \"I3 %Err\""/*, \"I1 Factor\", \"I2 Factor\", \"I3 Factor\""*/);

			for (EnergyMeterObj pEnergyMeterObj : ckt.getEnergyMeters()) {
				if (pEnergyMeterObj.isEnabled()) {
					writer.printf("\"Energymeter.%s\"", pEnergyMeterObj.getName());
					/* Sensor currents (target) */
					zeroTempXArray(tempX);
					for (i = 0; i < pEnergyMeterObj.getNPhases(); i++)
						tempX[i] = pEnergyMeterObj.getSensorCurrent()[i];
					for (i = 0; i < 3; i++)
						writer.printf(", %.6g", tempX[i]);
					/* Calculated currents */
					zeroTempXArray(tempX);
					for (i = 0; i < pEnergyMeterObj.getNPhases(); i++)
						tempX[i] = pEnergyMeterObj.getCalculatedCurrent()[i].abs();
					for (i = 0; i < 3; i++)
						writer.printf(", %.6g", tempX[i]);
					/* Percent error */
					for (i = 0; i < pEnergyMeterObj.getNPhases(); i++)
						tempX[i] = (1.0 - tempX[i] / Math.max(0.001, pEnergyMeterObj.getSensorCurrent()[i])) * 100.0;
					for (i = 0; i < 3; i++)
						writer.printf(", %.6g", tempX[i]);

					/****  Not all that useful
					// allocation factors
					zeroTempXArray(TempX);
					for (i = 0; i < pEnergyMeterObj.getNPhases(); i++)
						TempX[i] = pEnergyMeterObj.getPhsAllocationFactor()[i];
					for (i = 0; i < 3; i++)
						FPrinter.printf(" %.6g,", TempX[i]);
					*****/

					writer.println();
				}
			}

			/* Do the sensors next */
			writer.println();
			writer.println("\"Sensors\" ");
			writer.print("\"Sensor\", \"I1 Target\", \"I2 Target\", \"I3 Target\", \"I1 Calc\", \"I2 Calc\", \"I3 Calc\", \"I1 %Err\", \"I2 %Err\", \"I3 %Err\",");
			writer.println(" \"V1 Target\", \"V2 Target\", \"V3 Target\", \"V1 Calc\", \"V2 Calc\", \"V3 Calc\", \"V1 %Err\", \"V2 %Err\", \"V3 %Err\", \"WLS Voltage Err\", \"WLS Current Err\"");

			for (SensorObj pSensorObj : ckt.getSensors()) {
				if (pSensorObj.isEnabled()) {
					writer.printf("\"Sensor.%s\"", pSensorObj.getName());
					/* Sensor currents (target) */
					zeroTempXArray(tempX);
					for (i = 0; i < pSensorObj.getNPhases(); i++)
						tempX[i] = pSensorObj.getSensorCurrent()[i];
					for (i = 0; i < 3; i++)
						writer.printf(", %.6g", tempX[i]);
					/* Calculated currents */
					zeroTempXArray(tempX);
					for (i = 0; i < pSensorObj.getNPhases(); i++)
						tempX[i] = pSensorObj.getCalculatedCurrent()[i].abs();
					for (i = 0; i < 3; i++)
						writer.printf(", %.6g", tempX[i]);
					/* Percent error */
					for (i = 0; i < pSensorObj.getNPhases(); i++)
						tempX[i] = (1.0 - tempX[i] / Math.max(0.001, pSensorObj.getSensorCurrent()[i])) * 100.0;
					for (i = 0; i < 3; i++)
						writer.printf(", %.6g", tempX[i]);
					/* Sensor voltage (target) */
					zeroTempXArray(tempX);
					for (i = 0; i < pSensorObj.getNPhases(); i++)
						tempX[i] = pSensorObj.getSensorVoltage()[i];
					for (i = 0; i < 3; i++)
						writer.printf(", %.6g", tempX[i]);
					/* Calculated voltage */
					zeroTempXArray(tempX);
					for (i = 0; i < pSensorObj.getNPhases(); i++)
						tempX[i] = pSensorObj.getCalculatedVoltage()[i].abs();
					for (i = 0; i < 3; i++)
						writer.printf(", %.6g", tempX[i]);
					/* Percent error */
					for (i = 0; i < pSensorObj.getNPhases(); i++)
						tempX[i] = (1.0 - tempX[i] / Math.max(0.001, pSensorObj.getSensorVoltage()[i])) * 100.0;
					for (i = 0; i < 3; i++)
						writer.printf(", %.6g", tempX[i]);
					/* WLS errors */
					zeroTempXArray(tempX);
					writer.printf(", %.6g, %.6g", pSensorObj.getWLSVoltageError(), pSensorObj.getWLSCurrentError());

					writer.println();
				}
			}

			DSSGlobals.getInstance().setGlobalResult(fileName);

			writer.close();
			f.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	private static void writeMultipleMeterFiles() {
		FileWriter f;
		PrintWriter writer;
		int i, j;
		EnergyMeter meterClass;
		String fileName;
		final String sep = ", ";

		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();

		meterClass = ((EnergyMeter) DSSClassDefs.getDSSClass("EnergyMeter"));
		if (meterClass == null) return;

		for (EnergyMeterObj pElem : ckt.getEnergyMeters()) {
			if (pElem.isEnabled()) {
				try {
					fileName = globals.getDSSDataDirectory() + "EXP_MTR_"+pElem.getName()+".csv";

					if (!new File(fileName).exists()) {
						f = new FileWriter(fileName);
						writer = new PrintWriter(f);

						/* Write new header */
						writer.print("Year, LDCurve, Hour, Meter");
						for (i = 0; i < EnergyMeter.NumEMRegisters; i++)
							writer.write(sep + "\""+ pElem.getRegisterNames()[i]+"\"");
						writer.println();

						writer.close();
						f.close();
					}

					f = new FileWriter(fileName, true);  // append
					writer = new PrintWriter(f);

					writer.print(ckt.getSolution().getYear() + sep);
					writer.print(ckt.getLoadDurCurve() + sep);
					writer.print(ckt.getSolution().getIntHour() + sep);
					writer.print(Utilities.pad("\""+pElem.getName()+"\"", 14));
					for (j = 0; j < EnergyMeter.NumEMRegisters; j++)
						writer.print(sep + pElem.getRegisters()[j]);
					writer.println();

					globals.setGlobalResult(fileName);

					writer.close();
					f.close();
				} catch (IOException e) {
					// TODO: handle exception
				}
			}
		}
	}

	private static void writeSingleMeterFile(String fileName) {
		FileWriter f;
		PrintWriter writer;
		int i, j;
//		String TestStr;
		final String sep = ", ";
		boolean rewriteFile;

		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();

		try {
			if (new File(fileName).exists()) {
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

				rewriteFile = false;  // FIXME See if it likely that the file is OK
			} else {
				rewriteFile = true;
			}

			/* Either open or append the file */
			if (rewriteFile) {
				f = new FileWriter(fileName);
				writer = new PrintWriter(f);
				/* Write New Header */
				EnergyMeterObj pElem = ckt.getEnergyMeters().get(0);
				writer.print("Year, LDCurve, Hour, Meter");
				for (i = 0; i < EnergyMeter.NumEMRegisters; i++)
					writer.print(sep + "\""+ pElem.getRegisterNames()[i]+"\"");
				writer.println();
			} else {
				f = new FileWriter(fileName, true);
				writer = new PrintWriter(f);
			}

			for (EnergyMeterObj pElem : ckt.getEnergyMeters()) {
				if (pElem.isEnabled()) {
					writer.print(ckt.getSolution().getYear() + sep);
					writer.print(ckt.getLoadDurCurve() + sep);
					writer.print(ckt.getSolution().getIntHour() + sep);
					writer.print(Utilities.pad("\""+pElem.getName()+"\"", 14));
					for (j = 0; j < EnergyMeter.NumEMRegisters; j++)
						writer.printf(sep + pElem.getRegisters()[j]);
					writer.println();
				}
			}

			globals.setGlobalResult(fileName);

			writer.close();
			f.close();
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
	public static void exportMeters(String fileName) {
		if (fileName.substring(0, 2).toLowerCase() == "/m") {
			writeMultipleMeterFiles();
		} else {
			writeSingleMeterFile(fileName);
		}
	}

	private static void writeMultipleGenMeterFiles() {
		FileWriter f;
		PrintWriter writer;
		int i, j;
		Generator generatorClass;
		String fileName;
		final String sep = ", ";

		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();

		generatorClass = (Generator) DSSClassDefs.getDSSClass("generator");
		if (generatorClass == null)
			return;

		for (GeneratorObj pElem : ckt.getGenerators()) {
			if (pElem.isEnabled()) {
				try {
					fileName = globals.getDSSDataDirectory() + "EXP_GEN_" + pElem.getName() + ".csv";

					if (!new File(fileName).exists()) {
						f = new FileWriter(fileName);
						writer = new PrintWriter(f);
						/* Write new header */
						writer.print("Year, LDCurve, Hour, Generator");
						for (i = 0; i < Generator.NumGenRegisters; i++)
							writer.print(sep + "\"" + generatorClass.getRegisterNames()[i]+"\"");
						writer.println();

						writer.close();
						f.close();
					}

					f = new FileWriter(fileName, true);  // append
					writer = new PrintWriter(f);
					writer.print(ckt.getSolution().getYear() + sep);
					writer.print(ckt.getLoadDurCurve() + sep);
					writer.print(ckt.getSolution().getIntHour() + sep);
					writer.print(Utilities.pad("\""+pElem.getName()+"\"", 14));
					for (j = 0; j < Generator.NumGenRegisters; j++)
						writer.print(sep + pElem.getRegisters()[j]);
					writer.println();

					globals.setGlobalResult(fileName);

					writer.close();
					f.close();
				} catch (IOException e) {
					// TODO: handle exception
				}
			}
		}
	}

	private static void writeSingleGenMeterFile(String fileName) {
		FileWriter f;
		PrintWriter writer;
		int i, j;
		Generator generatorClass;
//		String TestStr;
		final String sep = ", ";
		boolean rewriteFile;

		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();

		generatorClass = (Generator) DSSClassDefs.getDSSClass("generator");
		if (generatorClass == null)
			return;

		try {
			if (new File(fileName).exists()) {
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

				rewriteFile = false;  // FIXME See if it likely that the file is OK
			} else {
				rewriteFile = true;
			}

			/* Either open or append the file */
			if (rewriteFile) {
				f = new FileWriter(fileName);
				writer = new PrintWriter(f);
				/* Write new header */
				writer.print("Year, LDCurve, Hour, Generator");
				for (i = 0; i < Generator.NumGenRegisters; i++)
					writer.print(sep + "\""+ generatorClass.getRegisterNames()[i]+"\"");
				writer.println();
			} else {
				f = new FileWriter(fileName, true);  // append
				writer = new PrintWriter(f);
			}

			for (GeneratorObj pElem : ckt.getGenerators()) {
				if (pElem.isEnabled()) {
					writer.print(ckt.getSolution().getYear() + sep);
					writer.print(ckt.getLoadDurCurve() + sep);
					writer.print(ckt.getSolution().getIntHour() + sep);
					writer.print(Utilities.pad("\""+pElem.getName()+"\"", 14));
					for (j = 0; j < Generator.NumGenRegisters; j++)
						writer.print(sep + pElem.getRegisters()[j]);
					writer.println();
				}
			}

			globals.setGlobalResult(fileName);

			writer.close();
			f.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/**
	 * Export values of generator meter elements.
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
		FileWriter f;
		PrintWriter writer;
		final String sep = ", ";

		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();

		try {
			f = new FileWriter(fileName);
			writer = new PrintWriter(f);
			/* Write header */
			writer.println("Load, Connected KVA, Allocation Factor, Phases, kW, kvar, PF, Model");

			for (LoadObj pElem : ckt.getLoads()) {
				if (pElem.isEnabled()) {
					writer.print(pElem.getName());
					writer.print(sep + pElem.getConnectedkVA());
					writer.print(sep + pElem.getkVAAllocationFactor());
					writer.print(sep + pElem.getNPhases());
					writer.print(sep + pElem.getkWBase());
					writer.print(sep + pElem.getKvarBase());
					writer.print(sep + pElem.getPFNominal());
					writer.print(sep + pElem.getLoadModel());
				}
				writer.println();
			}

			globals.setGlobalResult(fileName);

			writer.close();
			f.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/**
	 * Similar to export currents except does only max of the phases and
	 * compares that to the NormAmps and EmergAmps rating.
	 */
	public static void exportCapacity(String fileNm) {
		FileWriter f;
		PrintWriter writer;
		Complex[] cBuffer;

		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();

		try {
			f = new FileWriter(fileNm);
			writer = new PrintWriter(f);

			cBuffer = new Complex[Utilities.getMaxCktElementSize()];

			writer.println("Name, Imax, %normal, %emergency, kW, kvar, NumCustomers, TotalCustomers, NumPhases, kVBase");

			// PD elements only
			for (PDElement pElem : ckt.getPDElements()) {
				if (pElem.isEnabled()) {
					pElem.getCurrents(cBuffer);
					calcAndWriteMaxCurrents(writer, pElem, cBuffer);
				}
			}

			globals.setGlobalResult(fileNm);

			writer.close();
			f.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void exportOverloads(String fileName) {
		FileWriter f;
		PrintWriter writer;
		Complex[] cBuffer;  // allocate to max total conductors
		int NCond, i, j;
		Complex[] Iph = new Complex[3];
		Complex[] I012 = new Complex[3];
		double I0, I1, I2;
		double INormal, IEmerg, CMax;
		final String Separator = ", ";

		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();

		try {
			f = new FileWriter(fileName);
			writer = new PrintWriter(f);

			/* Allocate cBuffer big enough for largest circuit element */
			cBuffer = new Complex[Utilities.getMaxCktElementSize()];

			/* Sequence currents */
			writer.println("Element, Terminal,  I1, %Normal, %Emergency, I2, %I2/I1, I0, %I0/I1");

			// PD elements only
			for (PDElement PDElem : ckt.getPDElements()) {
				if (PDElem.isEnabled()) {
					if ((DSSClassDefs.CLASSMASK & PDElem.getDSSObjType()) != DSSClassDefs.CAP_ELEMENT) {  // ignore caps
						NCond = PDElem.getNConds();
						PDElem.getCurrents(cBuffer);

						for (j = 0; j < 1; j++) {  // only for terminal 1
							CMax = 0.0;
							for (i = 0; i < Math.min(PDElem.getNPhases(), 3); i++) {  // check only first 3 phases
								Iph[i] = cBuffer[(j - 1) * NCond + i];
								CMax = Math.max(CMax, Iph[i].abs());
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
								CMax = I1;
							}

							if ((PDElem.getNormAmps() > 0.0) || (PDElem.getEmergAmps() > 0.0)) {
								if ((CMax > PDElem.getNormAmps()) || (CMax > PDElem.getEmergAmps())) {
									writer.print(Utilities.pad("\""+PDElem.getDSSClassName() + "." + PDElem.getName()+"\"", 22) + Separator + j);
									writer.print(Separator + I1);
									if (j == 0) {  // Only for 1st Terminal
										INormal = PDElem.getNormAmps();
										if (INormal > 0.0) {
											writer.print(Separator + CMax / INormal * 100.0);
										} else {
											writer.print(Separator + "     0.0");
										}
										IEmerg = PDElem.getEmergAmps();
										if (IEmerg > 0.0) {
											writer.print(Separator + CMax / IEmerg * 100.0);
										} else {
											writer.print(Separator + "     0.0");
										}
									} else {
										writer.print(Separator + "       0" + Separator + "       0");
									}
									writer.print(Separator + I2);
									if (I1 > 0.0) {
										writer.print(Separator + 100.0 * I2 / I1);
									} else {
										writer.print(Separator + "0.0");
									}
									writer.print(Separator + I0);
									if (I1 > 0.0) {
										writer.print(Separator + 100.0 * I0 / I1);
									} else {
										writer.print(Separator + "0.0");
									}
									writer.println();
								}
							}
						}
					}
				}
			}

			globals.setGlobalResult(fileName);

			writer.close();
			f.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void exportUnserved(String fileName, boolean UE_Only) {
		FileWriter f;
		PrintWriter writer;
		boolean doIt;

		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();

		try {
			f = new FileWriter(fileName);
			writer = new PrintWriter(f);

			writer.println("Load, Bus, kW, EEN_Factor,  UE_Factor");

			// Load
			for (LoadObj pLoad : ckt.getLoads()) {
				if (pLoad.isEnabled()) {
					doIt = false;
					if (UE_Only) {
						if (pLoad.getUnserved()) doIt = true;
					} else {
						if (pLoad.getExceedsNormal()) doIt = true;
					}

					if (doIt) {
						writer.print(pLoad.getName() + ", ");
						writer.print(pLoad.getBus(1) + ", ");  // TODO Check zero based indexing
						writer.print(pLoad.getkWBase() + ", ");
						writer.print(pLoad.getEEN_Factor() + ", ");
						writer.print(pLoad.getUE_Factor());
						writer.println();
					}
				}
			}

			globals.setGlobalResult(fileName);

			writer.close();
			f.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/**
	 * Exports YPrim matrices for all circuit elements.
	 */
	public static void exportYprim(String fileName) {
		FileWriter f;
		PrintWriter writer;
		int i, j, k;
		Complex[] cValues;
		CktElement cktElem;

		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();

		if (ckt == null) return;

		try {
			f = new FileWriter(fileName);
			writer = new PrintWriter(f);

			for (k = 0; k < ckt.getNumDevices(); k++) {
				ckt.setActiveCktElement( ckt.getCktElements().get(k) );
				if (ckt.getActiveCktElement().isEnabled()) {
					if ((ckt.getActiveCktElement() instanceof PDElement) || (ckt.getActiveCktElement() instanceof PCElement)) {
						cktElem = ckt.getActiveCktElement();
						writer.println(cktElem.getParentClass().getName() + "." + cktElem.getName());
						cValues = cktElem.getYPrimValues(DSSGlobals.ALL_YPRIM);
						for (i = 0; i < cktElem.getYorder(); i++) {
							for (j = 0; j < cktElem.getYorder(); j++)
								writer.printf("%-13.10g, %-13.10g, ", cValues[i + (j - 1) * cktElem.getYorder()].getReal(), cValues[i + (j - 1) * cktElem.getYorder()].getImaginary());
							writer.println();
						}
					}
				}
			}

			globals.setGlobalResult(fileName);

			writer.close();
			f.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/**
	 * Exports system Y matrix in node order.
	 */
	public static void exportY(String fileName) {
		FileWriter f;
		PrintWriter writer;
		long i, j, p;
		CMatrix Y;
		long nBus = 0, nnz = 0;
		long[] colPtr, rowIdx;
		Complex[] cVals;
		double re, im;

		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();

		if (ckt == null) return;
		Y = ckt.getSolution().getY();
		if (Y == null) {
			globals.doSimpleMsg("Y Matrix not built.", 222);
			return;
		}
		// this compresses the entries if necessary - no extra work if already solved
//		KLU.factorSparseMatrix(Y);
//		KLU.getNNZ(Y, nNZ);
//		KLU.getSize(Y, nBus);  // we should already know this

		try {
			f = new FileWriter(fileName);
			writer = new PrintWriter(f);

			colPtr = new long[(int) (nBus + 1)];
			rowIdx = new long[(int) nnz];
			cVals  = new Complex[(int) nnz];
//			KLU.getCompressedMatrix(Y, nBus + 1, nNZ, ColPtr[0], RowIdx[0], cVals[0]);

			/* Write out fully qualified bus names */

			writer.printf("%d, ", ckt.getNumNodes());
			writer.println();
			/*for (i = 0; i < ckt.getNumNodes(); i++) {
				j = ckt.getMapNodeToBus()[i].BusRef;
				FPrinter.printf("%s.%-d, +j,", ckt.getBusList().get(j), ckt.getMapNodeToBus()[i].NodeNum);
			}
			FPrinter.println();*/

			for (i = 0; i < ckt.getNumNodes(); i++) {
				j =  ckt.getMapNodeToBus()[(int) i].busRef;
				writer.printf("%s.%-d, ", ckt.getBusList().get((int) j), ckt.getMapNodeToBus()[(int) i].nodeNum);
				for (j = 0; j < ckt.getNumNodes(); j++) {
					re = 0.0;
					im = 0.0;
					// search for a non-zero element [i, j]
					for (p = colPtr[(int) (j - 1)]; p < colPtr[(int) j] - 1; p++) {
						if (rowIdx[(int) p] + 1 == i) {
							re = cVals[(int) p].getReal();
							im = cVals[(int) p].getImaginary();
						}
					}
					writer.printf("%-13.10g, +j %-13.10g,", re, im);
				}
				writer.println();
			}

			globals.setGlobalResult(fileName);

			writer.close();
			f.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/**
	 * Export symmetrical component impedances at each bus.
	 */
	public static void exportSeqZ(String fileName) {
		FileWriter f;
		PrintWriter writer;
		int i;
		Complex Z1, Z0;
		double X1R1, X0R0;

		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();

		try {
			f = new FileWriter(fileName);
			writer = new PrintWriter(f);

			writer.println("Bus,  NumNodes, R1, X1, R0, X0, Z1, Z0, \"X1/R1\", \"X0/R0\"");

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

				writer.printf("\"%s\", %d, %10.6g, %10.6g, %10.6g, %10.6g, %10.6g, %10.6g, %8.4g, %8.4g",
						ckt.getBusList().get(i), ckt.getBuses()[i].getNumNodesThisBus(),
						Z1.getReal(), Z1.getImaginary(), Z0.getReal(), Z0.getImaginary(), Z1.abs(), Z0.abs(), X1R1, X0R0);
				writer.println();
			}

			globals.setGlobalResult(fileName);

			writer.close();
			f.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void exportUUIDs(String fileName) {
		FileWriter f;
		PrintWriter writer;
		LineCode clsCode;
		LineGeometry clsGeom;
		WireData clsWire;
		XfmrCode clsXfmr;
		NamedObject pName;

		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();

		try {
			clsCode = (LineCode)     globals.getDSSClassList().get(globals.getClassNames().find("linecode"));
			clsWire = (WireData)     globals.getDSSClassList().get(globals.getClassNames().find("wiredata"));
			clsGeom = (LineGeometry) globals.getDSSClassList().get(globals.getClassNames().find("linegeometry"));
			clsXfmr = (XfmrCode)     globals.getDSSClassList().get(globals.getClassNames().find("xfmrcode"));

			f = new FileWriter(fileName);
			writer = new PrintWriter(f);

			writer.printf("%s.%s %s", ckt.getDSSClassName(), ckt.getLocalName(), ckt.getID());
			writer.println();

			for (NamedObject pNamed : ckt.getCktElements()) {
				writer.printf("%s.%s %s", pNamed.getDSSClassName(), pNamed.getLocalName(), pNamed.getID());
				writer.println();
			}

			pName = (NamedObject) clsCode.getElementList().getFirst();  // FIXME Make generic
			while (pName != null) {
				writer.printf("%s.%s %s", pName.getDSSClassName(), pName.getLocalName(), pName.getID());
				writer.println();
				pName = (NamedObject) clsCode.getElementList().getNext();
			}

			pName = (NamedObject) clsWire.getElementList().getFirst();  // FIXME Make generic
			while (pName != null) {
				writer.printf("%s.%s %s", pName.getDSSClassName(), pName.getLocalName(), pName.getID());
				writer.println();
				pName = (NamedObject) clsWire.getElementList().getNext();
			}

			pName = (NamedObject) clsGeom.getElementList().getFirst();  // FIXME Make generic
			while (pName != null) {
				writer.printf("%s.%s %s", pName.getDSSClassName(), pName.getLocalName(), pName.getID());
				writer.println();
				pName = (NamedObject) clsGeom.getElementList().getNext();
			}

			pName = (NamedObject) clsXfmr.getElementList().getFirst();  // FIXME Make generic
			while (pName != null) {
				writer.printf("%s.%s %s", pName.getDSSClassName(), pName.getLocalName(), pName.getID());
				writer.println();
				pName = (NamedObject) clsXfmr.getElementList().getNext();
			}

			writer.close();
			f.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void exportCounts(String fileName) {
		FileWriter f;
		PrintWriter writer;

		DSSGlobals globals = DSSGlobals.getInstance();

		try {
			f = new FileWriter(fileName);
			writer = new PrintWriter(f);

			writer.println("Format: DSS Class Name = Instance Count");
			writer.println();
			for (DSSClass cls : globals.getDSSClassList()) {
				writer.printf("%s = %d", cls.getName(), cls.getElementCount());
				writer.println();
			}

			writer.close();
			f.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void exportSummary(String fileName) {
		FileWriter f;
		PrintWriter writer;
		Complex cPower, cLosses;

		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();

		try {
			if (new File(fileName).exists()) {
				f = new FileWriter(fileName, true);  // append
				writer = new PrintWriter(f);
			} else {
				f = new FileWriter(fileName);
				writer = new PrintWriter(f);
				// Create and write the header
				writer.print("DateTime, CaseName, ");
				writer.print("Status, Mode, Number, LoadMult, NumDevices, NumBuses, NumNodes");
				writer.print(", Iterations, ControlMode, ControlIterations");
				writer.print(", MostIterationsDone");
				if (ckt != null) {
					if (ckt.isSolved() && !ckt.isBusNameRedefined()) {
						writer.print(", Year, Hour, MaxPuVoltage, MinPuVoltage, TotalMW, TotalMvar");
						writer.print(", kWLosses, pctLosses, kvarLosses, Frequency");
					}
				}
				writer.println();
			}

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			writer.printf("\"%s\", ", sdf.format( cal.getTime() ));
			if (ckt != null) {
				writer.printf("%s, ", ckt.getCaseName());
			} else {
				writer.print("NONE, ");
			}

			if (ckt.isSolved()) {
				writer.print("SOLVED");
			} else {
				writer.print("UnSolved");
			}

			writer.printf(", %s",    Utilities.getSolutionModeID());
			writer.printf(", %d",    ckt.getSolution().getNumberOfTimes());
			writer.printf(", %8.3f", ckt.getLoadMultiplier());
			writer.printf(", %d",    ckt.getNumDevices());
			writer.printf(", %d",    ckt.getNumBuses());
			writer.printf(", %d",    ckt.getNumNodes());
			writer.printf(", %d",    ckt.getSolution().getIteration());
			writer.printf(", %s",    Utilities.getControlModeID());
			writer.printf(", %d",    ckt.getSolution().getControlIteration());
			writer.printf(", %d",    ckt.getSolution().getMostIterationsDone());
			if (ckt != null) {
				if (ckt.isSolved() && !ckt.isBusNameRedefined()) {
					writer.printf(", %d",    ckt.getSolution().getYear());
					writer.printf(", %d",    ckt.getSolution().getIntHour());
					writer.printf(", %-.5g", Utilities.getMaxPUVoltage());
					writer.printf(", %-.5g", Utilities.getMinPUVoltage(true));
					cPower = Utilities.getTotalPowerFromSources().multiply(0.000001);  // MVA
					writer.printf(", %-.6g", cPower.getReal());
					writer.printf(", %-.6g", cPower.getImaginary());
					cLosses = ckt.getLosses().multiply(0.000001);
					if (cPower.getReal() != 0.0) {
						writer.printf(", %-.6g, %-.4g", cLosses.getReal(), cLosses.getReal() / cPower.getReal() * 100.0);
					} else {
						writer.printf("Total Active Losses:   ****** MW, (**** %%)");
					}
					writer.printf(", %-.6g", cLosses.getImaginary());
					writer.printf(", %-g", ckt.getSolution().getFrequency());
				}
			}

			writer.println();

			globals.setGlobalResult(fileName);

			writer.close();
			f.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/**
	 * Export bus x, y coordinates.
	 */
	public static void exportBusCoords(String fileName) {
		FileWriter f;
		PrintWriter writer;
		int i;

		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();

		try {
			f = new FileWriter(fileName);
			writer = new PrintWriter(f);

			for (i = 0; i < ckt.getNumBuses(); i++) {
				if (ckt.getBuses()[i].isCoordDefined()) {
					writer.printf("%s, %-13.11g, %-13.11g", Utilities.checkForBlanks(ckt.getBusList().get(i)), ckt.getBuses()[i].getX(), ckt.getBuses()[i].getY());
					writer.println();
				}
			}

			globals.setGlobalResult(fileName);

			writer.close();
			f.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void exportCDPSM(String fileName, CIMProfileChoice Profile) {
		// TODO Auto-generated method stub

	}

	private static void writeNewLine(PrintWriter f,
			String cktElementName, double distFromMeter1, double puV1, double distFromMeter2, double puV2,
			int colorCode, int thickness, int lineType,
			int markCenter,
			int centerMarkerCode, int nodeMarkerCode, int nodeMarkerWidth) {

		f.printf("%s, %.6g, %.6g, %.6g, %.6g,", cktElementName, distFromMeter1, puV1, distFromMeter2, puV2);
		f.printf("%d, %d, %d, ", colorCode, thickness, lineType);
		f.printf("%d, ", markCenter);
		f.printf("%d, %d, %d", centerMarkerCode,  nodeMarkerCode, nodeMarkerWidth);
		f.println();
	}

	public static void exportProfile(String fileName, int phasesToPlot) {
		int iEnergyMeter;
		EnergyMeterObj activeEnergyMeter;
		CktElement presentCktElement;
		Bus bus1, bus2;
		double puV1 = 0, puV2 = 0;
		int iphs;
		int iphs2;
		String s;
		FileWriter f;
		PrintWriter writer;
		int Linetype = 0;

		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();

		try {
			f = new FileWriter(fileName);
			writer = new PrintWriter(f);

			writer.print("Name, Distance1, puV1, Distance2, puV2, Color, Thickness, Linetype, Markcenter, Centercode, NodeCode, NodeWidth,");

			/* New graph created before this routine is entered */
			switch (phasesToPlot) {
			case DSSGlobals.PROFILELL:
				s  = "L-L Voltage Profile";
				break;
			case DSSGlobals.PROFILELLALL:
				s  = "L-L Voltage Profile";
				break;
			case DSSGlobals.PROFILELLPRI:
				s  = "L-L Voltage Profile";
				break;
			default:
				s  = "L-N Voltage Profile";
				break;
			}

			writer.println("Title=" + s + ", Distance in km");

			iEnergyMeter = globals.getEnergyMeterClass().getFirst();
			while (iEnergyMeter >= 0) {

				activeEnergyMeter = (EnergyMeterObj) globals.getEnergyMeterClass().getActiveObj();
				/* Go down each branch list and draw a line */
				presentCktElement = (CktElement) activeEnergyMeter.getBranchList().getFirst();
				while (presentCktElement != null) {
					if (Utilities.isLineElement(presentCktElement)) {
						bus1 = ckt.getBuses()[presentCktElement.getTerminals()[0].busRef];
						bus2 = ckt.getBuses()[presentCktElement.getTerminals()[1].busRef];
						/* Now determin which phase to plot */
						if ((bus1.getKVBase() > 0.0) && (bus2.getKVBase() > 0.0)) {
							switch (phasesToPlot) {
							/* 3ph only */
							case DSSGlobals.PROFILE3PH:
								if ((presentCktElement.getNPhases() >= 3) && (bus1.getKVBase() > 1.0))
									for (iphs = 0; iphs < 3; iphs++) {
										puV1 = ckt.getSolution().getNodeV()[bus1.getRef(bus1.findIdx(iphs))].abs() / bus1.getKVBase() / 1000.0;
										puV2 = ckt.getSolution().getNodeV()[bus2.getRef(bus2.findIdx(iphs))].abs() / bus2.getKVBase() / 1000.0;
										writeNewLine(writer, presentCktElement.getName(), bus1.getDistFromMeter(), puV1, bus2.getDistFromMeter(), puV2,
												iphs, 2, 0, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth());
									}
								break;
							/* Plot all phases present (between 1 and 3) */
							case DSSGlobals.PROFILEALL:
								for (iphs = 0; iphs < 3; iphs++)
									if ((bus1.findIdx(iphs) >= 0) && (bus2.findIdx(iphs) >= 0)) {
										if (bus1.getKVBase() < 1.0) {
											Linetype = 2;
										} else {
											Linetype = 0;
										}
										puV1 = ckt.getSolution().getNodeV()[bus1.getRef(bus1.findIdx(iphs))].abs() / bus1.getKVBase() / 1000.0;
										puV2 = ckt.getSolution().getNodeV()[bus2.getRef(bus2.findIdx(iphs))].abs() / bus2.getKVBase() / 1000.0;
										writeNewLine(writer, presentCktElement.getName(), bus1.getDistFromMeter(), puV1, bus2.getDistFromMeter(), puV2,
												iphs, 2, Linetype, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth());
									}
								break;
							/* Plot all phases present (between 1 and 3) for Primary only */
							case DSSGlobals.PROFILEALLPRI:
								if (bus1.getKVBase() > 1.0)
									for (iphs = 0; iphs < 3; iphs++)
										if ((bus1.findIdx(iphs) >= 0) && (bus2.findIdx(iphs) >= 0)) {
											if (bus1.getKVBase() < 1.0) {
												Linetype = 2;
											} else {
												Linetype = 0;
											}
											puV1 = ckt.getSolution().getNodeV()[bus1.getRef(bus1.findIdx(iphs))].abs() / bus1.getKVBase() / 1000.0;
		                                    puV2 = ckt.getSolution().getNodeV()[bus2.getRef(bus2.findIdx(iphs))].abs() / bus2.getKVBase() / 1000.0;
		                                    writeNewLine(writer, presentCktElement.getName(), bus1.getDistFromMeter(), puV1, bus2.getDistFromMeter(), puV2,
		                                    		iphs, 2, Linetype, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth());
										}
								break;
							case DSSGlobals.PROFILELL:
								if (presentCktElement.getNPhases() >= 3)
									for (iphs = 0; iphs < 3; iphs++) {
										iphs2 = iphs + 1;
										if (iphs2 >= 3) iphs2 = 1;  // TODO Check zero based indexing
										if ((bus1.findIdx(iphs) >= 0) && (bus2.findIdx(iphs) >= 0) &&
												(bus1.findIdx(iphs2) >= 0) && (bus2.findIdx(iphs2) >= 0)) {
											if (bus1.getKVBase() < 1.0) {
												Linetype = 2;
											} else {
												Linetype = 0;
											}
											SolutionObj sol = ckt.getSolution();
											puV1 = sol.getNodeV()[bus1.getRef(bus1.findIdx(iphs))].subtract( sol.getNodeV()[bus1.getRef(bus1.findIdx(iphs2))] ).abs() / bus1.getKVBase() / 1732.0;
											puV2 = sol.getNodeV()[bus2.getRef(bus2.findIdx(iphs))].subtract( sol.getNodeV()[bus2.getRef(bus2.findIdx(iphs2))] ).abs() / bus2.getKVBase() / 1732.0;
										}
										writeNewLine(writer, presentCktElement.getName(), bus1.getDistFromMeter(), puV1, bus2.getDistFromMeter(), puV2,
												iphs, 2, Linetype, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth());
									}
								break;
							case DSSGlobals.PROFILELLALL:
								for (iphs = 0; iphs < 3; iphs++) {
									iphs2 = iphs + 1;
									if (iphs2 >= 3) iphs2 = 0;  // TODO Check zero based indexing
									if ((bus1.findIdx(iphs) >= 0) && (bus2.findIdx(iphs) >= 0) &&
											(bus1.findIdx(iphs2) >= 0) && (bus2.findIdx(iphs2) >= 0)) {
										if (bus1.getKVBase() < 1.0) {
											Linetype = 2;
										} else {
											Linetype = 0;
										}
										SolutionObj sol = ckt.getSolution();
										puV1 = sol.getNodeV()[bus1.getRef(bus1.findIdx(iphs))].subtract( sol.getNodeV()[bus1.getRef(bus1.findIdx(iphs2))] ).abs() / bus1.getKVBase() / 1732.0;
										puV2 = sol.getNodeV()[bus2.getRef(bus2.findIdx(iphs))].subtract( sol.getNodeV()[bus2.getRef(bus2.findIdx(iphs2))] ).abs() / bus2.getKVBase() / 1732.0;
									}
									writeNewLine(writer, presentCktElement.getName(), bus1.getDistFromMeter(), puV1, bus2.getDistFromMeter(), puV2,
											iphs, 2, Linetype, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth());
								}
								break;
							case DSSGlobals.PROFILELLPRI:
								if (bus1.getKVBase() > 1.0)
									for (iphs = 0; iphs < 3; iphs++) {
										iphs2 = iphs + 1;
										if (iphs2 >= 3) iphs2 = 0;  // TODO Check zero based indexing
										if ((bus1.findIdx(iphs) >= 0) && (bus2.findIdx(iphs) >= 0) &&
												(bus1.findIdx(iphs2) >= 0) && (bus2.findIdx(iphs2) >= 0)) {
											if (bus1.getKVBase() < 1.0) {
												Linetype = 2;
											} else {
												Linetype = 0;
											}
											SolutionObj sol = ckt.getSolution();
											puV1 = sol.getNodeV()[bus1.getRef(bus1.findIdx(iphs))].subtract( sol.getNodeV()[bus1.getRef(bus1.findIdx(iphs2))] ).abs() / bus1.getKVBase() / 1732.0;
											puV2 = sol.getNodeV()[bus2.getRef(bus2.findIdx(iphs))].subtract( sol.getNodeV()[bus2.getRef(bus2.findIdx(iphs2))] ).abs() / bus2.getKVBase() / 1732.0;
										}
										writeNewLine(writer, presentCktElement.getName(), bus1.getDistFromMeter(), puV1, bus2.getDistFromMeter(), puV2,
												iphs, 2, Linetype, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth());
									}
								break;
							default:  // plot just the selected phase
								iphs = phasesToPlot;
								if ((bus1.findIdx(iphs) > 0) && (bus2.findIdx(iphs) > 0)) {
									if (bus1.getKVBase() < 1.0) {
										Linetype = 2;
									} else {
										Linetype = 0;
									}
									puV1 = ckt.getSolution().getNodeV()[bus1.getRef(bus1.findIdx(iphs))].abs() / bus1.getKVBase() / 1000.0;
									puV2 = ckt.getSolution().getNodeV()[bus2.getRef(bus2.findIdx(iphs))].abs() / bus2.getKVBase() / 1000.0;
									writeNewLine(writer, presentCktElement.getName(), bus1.getDistFromMeter(), puV1, bus2.getDistFromMeter(), puV2,
											iphs, 2, Linetype, 0, 0, ckt.getNodeMarkerCode(), ckt.getNodeMarkerWidth());
								}
								break;
							}
						}
					}
					presentCktElement = (CktElement) activeEnergyMeter.getBranchList().GoForward();
				}
				iEnergyMeter = globals.getEnergyMeterClass().getNext();
			}

			globals.setGlobalResult(fileName);

			f.close();
			writer.close();
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
