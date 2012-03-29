/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.meter;

import com.ncond.dss.common.Bus;
import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.Util;
import com.ncond.dss.conversion.LoadObj;
import com.ncond.dss.delivery.LineObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CktTree;
import com.ncond.dss.shared.CktTreeNode;

/**
 * Reduction algorithms.
 *
 * Primarily called from EnergyMeter.
 */
public class ReduceAlgs {

	private ReduceAlgs() {}

	/**
	 * Merge all lines in this zone that are marked in parallel.
	 */
	public static void doMergeParallelLines(CktTree branchList) {
		if (branchList != null) {
			branchList.getFirst();
			LineObj lineElement = (LineObj) branchList.goForward();  // always keep the first element
			while (lineElement != null) {
				if (branchList.getPresentBranch().isParallel()) {
					/* There will always be two lines in parallel. The first operation will disable the second */
					if (lineElement.isEnabled())
						lineElement.mergeWith((LineObj) branchList.getPresentBranch().getLoopLineObj(), false);  // guaranteed to be a line
				}
				lineElement = (LineObj) branchList.goForward();
			}
		}
	}

	public static void doBreakLoops(CktTree branchList) {
		if (branchList != null) {
			branchList.getFirst();
			LineObj lineElement = (LineObj) branchList.goForward();  // always keep the first element
			while (lineElement != null) {
				if (branchList.getPresentBranch().isLoopedHere()) {
					/* There will always be two lines in the loop. The first operation will disable the second */
					if (lineElement.isEnabled())
						((LineObj) branchList.getPresentBranch().getLoopLineObj()).setEnabled(false);  // disable the other
				}
				lineElement = (LineObj) branchList.goForward();
			}
		}
	}

	public static void doReduceTapEnds(CktTree branchList) {

	}

	public static void doReduceDangling(CktTree branchList) {
		CktElement lineElem1;
		int toBusRef;
		CktTreeNode branch;
		Bus bus;

		if (branchList != null) {
			/* Let's throw away all dangling end branches */
			branchList.getFirst();
			lineElem1 = (CktElement) branchList.goForward();  // always keep the first element

			while (lineElem1 != null) {
				if (Util.isLineElement(lineElem1)) {
					branch = branchList.getPresentBranch();

					/* If it is at the end of a section and has no load,cap, reactor,
					 * or coordinate, just throw it away.
					 */
					if (branch.isDangling()) {
						toBusRef = branch.getToBusReference();  // only access this property once
						if (toBusRef > 0) {
							bus = DSS.activeCircuit.getBus(toBusRef - 1);
							if (!bus.isKeep()) lineElem1.setEnabled(false);
						}
					}
				}
				lineElem1 = (CktElement) branchList.goForward();
			}
		}
	}

	/**
	 * Eliminate short stubs and merge with lines on either side.
	 */
	public static void doReduceStubs(CktTree branchList) {
		LineObj lineElement1, lineElement2;
		LoadObj loadElement;
		CktTreeNode parentNode;
		CktTreeNode branch;

		Circuit ckt = DSS.activeCircuit;

		if (branchList != null) {  /* eliminate really short lines */
			/* First, flag all elements that need to be merged */
			lineElement1 = (LineObj) branchList.getFirst();
			lineElement1 = (LineObj) branchList.goForward();  // always keep the first element

			while (lineElement1 != null) {
				if (Util.isLineElement(lineElement1)) {
					if (Util.isStubLine(lineElement1)) {
						lineElement1.setFlag(true);  /* Too small: Mark for merge with something */
					} else {
						lineElement1.setFlag(false);
					}
				}
				lineElement1 = (LineObj) branchList.goForward();
			}

			lineElement1 = (LineObj) branchList.getFirst();
			lineElement1 = (LineObj) branchList.goForward();  // always keep the first element

			while (lineElement1 != null) {
				if (lineElement1.isFlag()) {  // merge this element out

					branch = branchList.getPresentBranch();

					if (branch.getNumChildren() == 0 && branch.getNumObjects() == 0) {
						lineElement1.setEnabled(false);  // just discard it
					} else if (branch.getNumChildren() == 0 || branch.getNumChildren() > 1) {
						/* Merge with parent and move loads on parent to to node */
						parentNode = branch.getParent();
						if (parentNode != null) {
							if (parentNode.getNumChildren() == 1) {  // only works for in-line
								if (!ckt.getBus(parentNode.getToBusReference() - 1).isKeep()) {
									/* Let's consider merging */
									lineElement2 = (LineObj) parentNode.getCktObject();
									if (lineElement2.isEnabled()) {  // check to make sure it hasn't been merged out
										if (Util.isLineElement(lineElement2)) {
											if (lineElement2.mergeWith(lineElement1, true)) {
												/* Move any loads to toBus Reference of downline branch */
												if (parentNode.getNumObjects() > 0) {
													/* Redefine bus connection for PC elements hanging on the bus that is eliminated */
													loadElement = (LoadObj) parentNode.getFirstObject();
													while (loadElement != null) {
														Parser.getInstance().setCommand("bus1=\"" + ckt.getBusList().get(branch.getToBusReference()) + "\"");
														loadElement.edit();
														loadElement = (LoadObj) parentNode.getNextObject();
													}
												}
											}
										}
									}
								}
							}
						}  /* if parentNode */
					} else {  /* Merge with child */
						if (!ckt.getBus(branch.getToBusReference() -1).isKeep()) {
							/* Let's consider merging */
							lineElement2 = (LineObj) branch.getFirstChild().getCktObject();
							if (Util.isLineElement(lineElement2)) {
								if (lineElement2.mergeWith(lineElement1, true)) {
									if (branch.getFirstChild().getNumObjects() > 0) {
										/* Redefine bus connection to upline bus */
										loadElement = (LoadObj) branch.getFirstChild().getFirstObject();
										while (loadElement != null) {
											Parser.getInstance().setCommand("bus1=\"" + ckt.getBusList().get(branch.getFromBusReference() - 1) + "\"");
											loadElement.edit();
											loadElement = (LoadObj) branch.getFirstChild().getNextObject();
										}
									}
								}
							}
						}
					}
				}
				lineElement1 = (LineObj) branchList.goForward();
			}
		}
	}

	/**
	 * Merge switches in with lines or delete if dangling.
	 */
	public static void doReduceSwitches(CktTree branchList) {
		LineObj lineElement1, lineElement2;
		CktTreeNode branch;

		if (branchList != null) {
			lineElement1 = (LineObj) branchList.getFirst();
			lineElement1 = (LineObj) branchList.goForward();  // always keep the first element

			while (lineElement1 != null) {
				if (lineElement1.isEnabled()) {  // maybe we threw it away already
					if (Util.isLineElement(lineElement1)) {
						if (lineElement1.isSwitch()) {
							branch = branchList.getPresentBranch();
							/* See if eligble for merging */
							switch (branch.getNumChildren()) {
							case 0:  /* Throw away if dangling */
								if (branch.getNumObjects() == 0)
									lineElement1.setEnabled(false);
								break;

							case 1:
								if (branch.getNumObjects() == 0)
									if (!DSS.activeCircuit.getBus(branch.getToBusReference()).isKeep()) {
										/* Let's consider merging */
										lineElement2 = (LineObj) branch.getFirstChild().getCktObject();
										if (Util.isLineElement(lineElement2))
											if (!lineElement2.isSwitch())
												lineElement2.mergeWith(lineElement1, true);  // series merge
									}
								break;
							}
						}
					}
				}
				lineElement1 = (LineObj) branchList.goForward();
			}
		}
	}

	public static void doReduceDefault(CktTree branchList) {
		LineObj lineElement1, lineElement2;
		CktTreeNode branch;

		if (branchList != null) {
			/* Now merge remaining lines */
			lineElement1 = (LineObj) branchList.getFirst();
			lineElement1 = (LineObj) branchList.goForward();  // always keep the first element

			while (lineElement1 != null) {
				if (Util.isLineElement(lineElement1)) {
					if (!lineElement1.isSwitch()) {
						if (lineElement1.isEnabled()) {  // maybe we threw it away already
							branch = branchList.getPresentBranch();
							/* see if eligble for merging */
							if (branch.getNumChildren() == 1) {
								if (branch.getNumObjects() == 0) {
									if (!DSS.activeCircuit.getBus(branch.getToBusReference() - 1).isKeep()) {
										/* Let's consider merging */
										lineElement2 = (LineObj) branch.getFirstChild().getCktObject();
										if (Util.isLineElement(lineElement2))
											if (!lineElement2.isSwitch())
												lineElement2.mergeWith(lineElement1, true);  /* Series merge */
									}
								}
							}
						}
					}
				}
				lineElement1 = (LineObj) branchList.goForward();
			}
		}
	}

}
