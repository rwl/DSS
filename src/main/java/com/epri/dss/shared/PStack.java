package com.epri.dss.shared;

import com.epri.dss.general.DSSObject;

public interface PStack extends StackBase {

	void push(DSSObject p);

	DSSObject pop();

}
