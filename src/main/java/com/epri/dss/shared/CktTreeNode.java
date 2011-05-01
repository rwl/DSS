package com.epri.dss.shared;

import com.epri.dss.general.DSSObject;

public interface CktTreeNode {

	CktTreeNode getFirstChild();

	CktTreeNode getNextChild();

	CktTreeNode getParent();

	void addChild(CktTreeNode Value);

	void addObject(DSSObject Value);

	/**
	 * Number of children at present node.
	 */
	int getNumChildren();

	/**
	 * Number of objects at present node.
	 */
	int getNumObjects();

	int getToBusReference();

	void setToBusReference(int Value);

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

	boolean isIsLoopedHere();

	void setIsLoopedHere(boolean isLoopedHere);

	boolean isIsParallel();

	void setIsParallel(boolean isParallel);

	boolean isIsDangling();

	void setIsDangling(boolean isDangling);

	Object getLoopLineObj();

	void setLoopLineObj(Object loopLineObj);

	// FIXME Protected members in OpenDSS

	boolean isChildAdded();

	void setChildAdded(boolean childAdded);

	int getLexicalLevel();

	void setLexicalLevel(int lexicalLevel);

}
