package com.epri.dss.meter.impl;

import com.epri.dss.common.Bus;
import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.conversion.LoadObj;
import com.epri.dss.delivery.LineObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.CktTree;
import com.epri.dss.shared.CktTreeNode;

/**
 * Reduction Algorithms
 *
 * Primarily called from EnergyMeter.
 *
 */
public class ReduceAlgs {

	private ReduceAlgs() {
	}

	/**
	 * Merge all lines in this zone that are marked in parallel.
	 */
	public static void doMergeParallelLines(CktTree BranchList) {
		LineObj LineElement;

		if (BranchList != null) {
			BranchList.getFirst();
			LineElement = (LineObj) BranchList.GoForward();  // Always keep the first element
			while (LineElement != null) {
				if (BranchList.getPresentBranch().isIsParallel()) {
					/* There will always be two lines in parallel.  The first operation will disable the second */
					if (LineElement.isEnabled())
						LineElement.mergeWith( (LineObj) BranchList.getPresentBranch().getLoopLineObj(), false );  // Guaranteed to be a line
				}
				LineElement = (LineObj) BranchList.GoForward();
			}
		}
	}

	public static void doBreakLoops(CktTree BranchList) {
		LineObj LineElement;

		if (BranchList != null) {
			BranchList.getFirst();
			LineElement = (LineObj) BranchList.GoForward();  // Always keep the first element
			while (LineElement != null) {
				if (BranchList.getPresentBranch().isIsLoopedHere()) {
					/* There will always be two lines in the loop.  The first operation will disable the second */
					if (LineElement.isEnabled())
						((LineObj) BranchList.getPresentBranch().getLoopLineObj()).setEnabled(false);  // Disable the other
				}
				LineElement = (LineObj) BranchList.GoForward();
			}
		}
	}

	public static void doReduceTapEnds(CktTree BranchList) {

	}

	public static void doReduceDangling(CktTree BranchList) {
		CktElement pLineElem1;
		int ToBusRef;

		if (BranchList != null) {
			/* Let's throw away all dangling end branches */
			BranchList.getFirst();
			pLineElem1 = (CktElement) BranchList.GoForward();  // Always keep the first element

			while (pLineElem1 != null) {
				if (Utilities.isLineElement(pLineElem1)) {
					CktTreeNode pb = BranchList.getPresentBranch();

					/* If it is at the end of a section and has no load,cap, reactor,
					 * or coordinate, just throw it away.
					 */
					if (pb.isIsDangling()) {
						ToBusRef = pb.getToBusReference();  // only access this property once!
						if (ToBusRef > 0) {
							Bus bus = DSSGlobals.getInstance().getActiveCircuit().getBuses()[ToBusRef];
							if (!bus.isKeep())
								pLineElem1.setEnabled(false);
						}
					}
				}
				pLineElem1 = (CktElement) BranchList.GoForward();
			}
		}
	}

	/**
	 * Eliminate short stubs and merge with lines on either side.
	 */
	public static void doReduceStubs(CktTree BranchList) {
		LineObj LineElement1, LineElement2;
		LoadObj LoadElement;
		CktTreeNode ParentNode;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		if (BranchList != null) {  /* eliminate really short lines */
			/* First, flag all elements that need to be merged */
			LineElement1 = (LineObj) BranchList.getFirst();
			LineElement1 = (LineObj) BranchList.GoForward();  // Always keep the first element
			while (LineElement1 != null) {
				if (Utilities.isLineElement(LineElement1)) {
					if (Utilities.isStubLine(LineElement1)) {
						LineElement1.setFlag(true);  /* Too small: Mark for merge with something */
					} else {
						LineElement1.setFlag(false);
					}
				}
				LineElement1 = (LineObj) BranchList.GoForward();
			}

			LineElement1 = (LineObj) BranchList.getFirst();
			LineElement1 = (LineObj) BranchList.GoForward(); // Always keep the first element
			while (LineElement1 != null) {
				if (LineElement1.isFlag()) {  // Merge this element out

					CktTreeNode pb = BranchList.getPresentBranch();

					if ((pb.getNumChildren() == 0) && (pb.getNumObjects() == 0)) {
						LineElement1.setEnabled(false);  // just discard it
					} else if ((pb.getNumChildren() == 0) || (pb.getNumChildren() > 1)) {
						/* Merge with Parent and move loads on parent to To node */
						ParentNode = pb.getParent();
						if (ParentNode != null) {
							if (ParentNode.getNumChildren() == 1)  // only works for in-line
								if (!ckt.getBuses()[ParentNode.getToBusReference()].isKeep()) {
									/* Let's consider merging */
									LineElement2 = (LineObj) ParentNode.getCktObject();
									if (LineElement2.isEnabled())  // Check to make sure it hasn't been merged out
										if (Utilities.isLineElement(LineElement2))
											if (LineElement2.mergeWith(LineElement1, true))
												/* Move any loads to ToBus Reference of downline branch */
												if (ParentNode.getNumObjects() > 0) {
													/* Redefine bus connection for PC elements hanging on the bus that is eliminated */
													LoadElement = (LoadObj) ParentNode.getFirstObject();
													while (LoadElement != null) {
														Parser.getInstance().setCmdString("bus1=\"" +ckt.getBusList().get(pb.getToBusReference())+"\"");
														LoadElement.edit();
														LoadElement = (LoadObj) ParentNode.getNextObject();

													}
												}
								}
						}  /* if ParentNode */
					} else {  /* Merge with child */
						if (!ckt.getBuses()[pb.getToBusReference()].isKeep()) {
							/* Let's consider merging */
							LineElement2 = (LineObj) pb.getFirstChild().getCktObject();
							if (Utilities.isLineElement(LineElement2))
								if (LineElement2.mergeWith(LineElement1, true))
									if (pb.getFirstChild().getNumObjects() > 0) {
										/* Redefine bus connection to upline bus */
										LoadElement = (LoadObj) pb.getFirstChild().getFirstObject();
										while (LoadElement != null) {
											Parser.getInstance().setCmdString("bus1=\"" +ckt.getBusList().get(pb.getFromBusReference())+"\"");
											LoadElement.edit();
											LoadElement = (LoadObj) pb.getFirstChild().getNextObject();
										}
									}
						}
					}
				}
				LineElement1 = (LineObj) BranchList.GoForward();
			}
		}
	}

	/**
	 * Merge switches in with lines or delete if dangling.
	 */
	public static void doReduceSwitches(CktTree BranchList) {
		LineObj LineElement1, LineElement2;

		if (BranchList != null) {
			LineElement1 = (LineObj) BranchList.getFirst();
			LineElement1 = (LineObj) BranchList.GoForward();  // Always keep the first element
			while (LineElement1 != null) {

				if (LineElement1.isEnabled()) {  // maybe we threw it away already
					if (Utilities.isLineElement(LineElement1))
						if (LineElement1.isIsSwitch()) {
							CktTreeNode pb = BranchList.getPresentBranch();
							/* see if eligble for merging */
							switch (pb.getNumChildren()) {
							case 0:  /* Throw away if dangling */
								if (pb.getNumObjects() == 0)
									LineElement1.setEnabled(false);

							case 1:
								if (pb.getNumObjects() == 0)
									if (!DSSGlobals.getInstance().getActiveCircuit().getBuses()[pb.getToBusReference()].isKeep()) {
										/* Let's consider merging */
										LineElement2 = (LineObj) pb.getFirstChild().getCktObject();
										if (Utilities.isLineElement(LineElement2))
											if (!LineElement2.isIsSwitch())
												LineElement2.mergeWith(LineElement1, true);  /* Series Merge */
									}
							}
						}
				}
				LineElement1 = (LineObj) BranchList.GoForward();
			}
		}
	}

	public static void doReduceDefault(CktTree BranchList) {
		LineObj LineElement1, LineElement2;

		if (BranchList != null) {

			/* Now merge remaining lines */

			LineElement1 = (LineObj) BranchList.getFirst();
			LineElement1 = (LineObj) BranchList.GoForward();  // Always keep the first element
			while (LineElement1 != null) {

				if (Utilities.isLineElement(LineElement1))
					if (!LineElement1.isIsSwitch())
						if (LineElement1.isEnabled()) {  // maybe we threw it away already
							CktTreeNode pb = BranchList.getPresentBranch();
							/* see if eligble for merging */
							if (pb.getNumChildren() == 1)
								if (pb.getNumObjects() == 0)
									if (!DSSGlobals.getInstance().getActiveCircuit().getBuses()[pb.getToBusReference()].isKeep()) {
										/* Let's consider merging */
										LineElement2 = (LineObj) pb.getFirstChild().getCktObject();
										if (Utilities.isLineElement(LineElement2))
											if (!LineElement2.isIsSwitch())
												LineElement2.mergeWith(LineElement1, true);  /* Series Merge */
									}
						}
				LineElement1 = (LineObj) BranchList.GoForward();
			}
		}
	}

}
