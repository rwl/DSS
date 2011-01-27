package com.epri.dss.shared;

import com.epri.dss.shared.impl.CktTreeImpl.CktTreeNode;
import com.epri.dss.shared.impl.CktTreeImpl.ZoneEndsList;

public interface CktTree {

	public CktTreeNode getPresentBranch();

	public void setPresentBranch(CktTreeNode presentBranch);

	public ZoneEndsList getZoneEndsList();

	public void setZoneEndsList(ZoneEndsList zoneEndsList);

	/* Adds a pointer to an object to be associated with the current node */
	public void setNewObject(Object Value);

	/* Start Forward Search at the present location (can also use active) */
	public void startHere();

	public void addNewChild(Object Value, int BusRef, int TerminalNo);

	/* Adds Child and makes it present */
	public void setNew(Object Value);

	/* Returns pointer to first cktobject */
	public Object getFirst();

	public Object getParent();

	public Object getFirstObject();

	public Object getNextObject();

	public Object GoForward();

	public Object GoBackward();

	public Object getActive();

	public void setActive(Object Value);

	/* Get lexical level of present node */
	public int getLevel();
}
