package com.epri.dss.shared;

public interface PStack<T> extends StackBase {

	void push(T p);

	T pop();

}
