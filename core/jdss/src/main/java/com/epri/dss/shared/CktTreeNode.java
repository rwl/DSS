package com.epri.dss.shared;

import com.epri.dss.general.DSSObject;

public interface CktTreeNode {

	CktTreeNode getFirstChild();

	CktTreeNode getNextChild();

	CktTreeNode getParent();

	void addChild(CktTreeNode value);

	void addObject(DSSObject value);

	/**
	 * Number of children at present node.
	 */
	int getNumChildren();

	/**
	 * Number of objects at present node.
	 */
	int getNumObjects();

	int getToBusReference();

	void setToBusReference(int value);

	DSSObject getFirstObject();

	DSSObject getNextObject();

	void resetToBusList();


	DSSObject getCktObject();

	void setCktObject(DSSObject cktObject);

	int getFromBusReference();

	void setFromBusReference(int fromBusReference);

	int getVoltBaseIndex();

	void setVoltBaseIndex(int voltBaseIndex);

	int getFromTerminal();

	void setFromTerminal(int fromTerminal);

	boolean isLoopedHere();

	void setLoopedHere(boolean isLoopedHere);

	boolean isParallel();

	void setParallel(boolean isParallel);

	boolean isDangling();

	void setDangling(boolean isDangling);

	Object getLoopLineObj();

	void setLoopLineObj(Object loopLineObj);

	// FIXME Protected members in OpenDSS

	boolean isChildAdded();

	void setChildAdded(boolean childAdded);

	int getLexicalLevel();

	void setLexicalLevel(int lexicalLevel);

}
