package com.epri.dss.shared;

import com.epri.dss.general.DSSObject;
import com.epri.dss.shared.impl.CktTreeImpl.ZoneEndsList;

public interface CktTree {

	CktTreeNode getPresentBranch();

	void setPresentBranch(CktTreeNode presentBranch);

	ZoneEndsList getZoneEndsList();

	void setZoneEndsList(ZoneEndsList zoneEndsList);

	/** Adds a pointer to an object to be associated with the current node */
	void setNewObject(DSSObject Value);

	/** Start Forward Search at the present location (can also use active) */
	void startHere();

	void addNewChild(DSSObject Value, int BusRef, int TerminalNo);

	/** Adds child and makes it present */
	void setNew(DSSObject Value);

	/** Returns pointer to first cktobject */
	Object getFirst();

	Object getParent();

	Object getFirstObject();

	Object getNextObject();

	Object GoForward();

	Object GoBackward();

	Object getActive();

	void setActive(DSSObject Value);

	/** Get lexical level of present node */
	int getLevel();

}
