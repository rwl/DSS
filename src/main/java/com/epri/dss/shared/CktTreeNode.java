package com.epri.dss.shared;

public interface CktTreeNode {

	CktTreeNode getFirstChild();

	CktTreeNode getNextChild();

	CktTreeNode getParent();

	void setAddChild(CktTreeNode Value);

	void setAddObject(Object Value);

	/**
	 * Number of children at present node.
	 */
	int getNumChildren();

	/**
	 * Number of objects at present node.
	 */
	void getNumObjects();

	int getToBusReference();

	void setToBusReference(int Value);

	Object getFirstObject();

	Object getNextObject();

	void resetToBusList();


	Object getCktObject();

	void setCktObject(Object cktObject);

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

}
