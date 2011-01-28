package com.epri.dss.shared;

import com.epri.dss.shared.impl.CktTreeImpl.CktTreeNode;
import com.epri.dss.shared.impl.CktTreeImpl.ZoneEndsList;

public interface CktTree {

	CktTreeNode getPresentBranch();

	void setPresentBranch(CktTreeNode presentBranch);

	ZoneEndsList getZoneEndsList();

	void setZoneEndsList(ZoneEndsList zoneEndsList);

	/* Adds a pointer to an object to be associated with the current node */
	void setNewObject(Object Value);

	/* Start Forward Search at the present location (can also use active) */
	void startHere();

	void addNewChild(Object Value, int BusRef, int TerminalNo);

	/* Adds Child and makes it present */
	void setNew(Object Value);

	/* Returns pointer to first cktobject */
	Object getFirst();

	Object getParent();

	Object getFirstObject();

	Object getNextObject();

	Object GoForward();

	Object GoBackward();

	Object getActive();

	void setActive(Object Value);

	/* Get lexical level of present node */
	int getLevel();
}
