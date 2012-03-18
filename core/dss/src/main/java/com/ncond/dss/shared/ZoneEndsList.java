package com.ncond.dss.shared;

import lombok.Getter;
import lombok.Setter;

import com.ncond.dss.common.Util;

@Getter @Setter
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
		endBuses = Util.resizeArray(endBuses, numEnds);
		endBuses[numEnds - 1] = endBusRef;
	}

	public int get(int i, CktTreeNode node) {
		node = (CktTreeNode) endNodeList.get(i);
		return endBuses[i];
	}

}
