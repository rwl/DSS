package com.epri.dss.general.impl;

import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.LineUnits;
import com.epri.dss.shared.impl.MathUtil;

import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.general.OHLineConstants;
import com.epri.dss.shared.CMatrix;

public class OHLineConstantsImpl extends LineConstantsImpl implements OHLineConstants {

	public OHLineConstantsImpl(int NumConductors) {
		super(NumConductors);
	}

}
