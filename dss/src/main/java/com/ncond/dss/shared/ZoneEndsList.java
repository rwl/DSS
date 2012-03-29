/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.shared;

import static com.ncond.dss.common.Util.resizeArray;


public class ZoneEndsList {

	private PointerList endNodeList;
	private int[] endBuses;

	protected int numEnds;

	public ZoneEndsList() {
		endNodeList = new PointerList(10);
		numEnds = 0;
		endBuses = null;
	}

	public void add(CktTreeNode node, int endBusRef) {
		numEnds += 1;
		endNodeList.add(node);
		endBuses = resizeArray(endBuses, numEnds);
		endBuses[numEnds - 1] = endBusRef;
	}

	public int get(int i, CktTreeNode node) {
		node = (CktTreeNode) endNodeList.get(i);
		return endBuses[i];
	}

	public int getNumEnds() {
		return numEnds;
	}

}
