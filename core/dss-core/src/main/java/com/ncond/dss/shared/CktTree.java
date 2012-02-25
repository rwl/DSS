package com.ncond.dss.shared;

import com.ncond.dss.general.DSSObject;
import com.ncond.dss.shared.impl.CktTreeImpl.ZoneEndsList;

public interface CktTree {

	CktTreeNode getPresentBranch();

	void setPresentBranch(CktTreeNode presentBranch);

	ZoneEndsList getZoneEndsList();

	void setZoneEndsList(ZoneEndsList zoneEndsList);

	/** Adds a pointer to an object to be associated with the current node */
	void setNewObject(DSSObject value);

	/** Start Forward Search at the present location (can also use active) */
	void startHere();

	void addNewChild(DSSObject value, int busRef, int terminalNo);

	/** Adds child and makes it present */
	void setNew(DSSObject value);

	/** Returns pointer to first cktobject */
	Object getFirst();

	Object getParent();

	Object getFirstObject();

	Object getNextObject();

	Object goForward();

	Object goBackward();

	Object getActive();

	void setActive(DSSObject value);

	/** Get lexical level of present node */
	int getLevel();

}
