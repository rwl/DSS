package com.epri.dss.shared;

import com.epri.dss.common.CktElement;
import com.epri.dss.shared.impl.CktTreeImpl.ZoneEndsList;

public interface CktTree<T extends CktElement> {

	CktTreeNode<T> getPresentBranch();

	void setPresentBranch(CktTreeNode<T> presentBranch);

	ZoneEndsList getZoneEndsList();

//	void setZoneEndsList(ZoneEndsList zoneEndsList);

	/** Adds a pointer to an object to be associated with the current node */
	void setNewObject(T value);

	/** Start Forward Search at the present location (can also use active) */
	void startHere();

	void addNewChild(T value, int busRef, int terminalNo);

	/** Adds child and makes it present */
	void setNew(T value);

	/** Returns pointer to first cktobject */
	T getFirst();

	T getParent();

	T getFirstObject();

	T getNextObject();

	T goForward();

	T goBackward();

	T getActive();

	void setActive(T value);

	/** Get lexical level of present node */
	int getLevel();

}
