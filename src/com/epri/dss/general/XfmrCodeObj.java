package com.epri.dss.general;

import com.epri.dss.delivery.TransformerObj;

public interface XfmrCodeObj extends DSSObject {
	
	void setNumWindings(int N);
	
	void pullFromTransformer(TransformerObj obj);

}
