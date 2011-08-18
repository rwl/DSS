package com.epri.dss.shared;

import com.epri.dss.general.DSSObject;

public interface CktTreeNode<T> {

	CktTreeNode<T> getFirstChild();

	CktTreeNode<T> getNextChild();

	CktTreeNode<T> getParent();

	void addChild(CktTreeNode<T> value);

	void addObject(T value);

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

	T getFirstObject();

	T getNextObject();

	void resetToBusList();


	T getCktObject();

	void setCktObject(T cktObject);

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

	T getLoopLineObj();

	void setLoopLineObj(T loopLineObj);

	// FIXME Protected members in OpenDSS

	boolean isChildAdded();

	void setChildAdded(boolean childAdded);

	int getLexicalLevel();

	void setLexicalLevel(int lexicalLevel);

}
