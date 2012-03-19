/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.general;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;
import com.ncond.dss.delivery.Winding;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class XfmrCode extends DSSClass {

	public static final int NumPropsThisClass = 33;

	private enum WdgParmChoice {CONN, KV, KVA, R, TAP};

	public static XfmrCodeObj activeXfmrCodeObj;

	public XfmrCode() {
		super();
		className = "XfmrCode";
		classType = DSSClassDefs.DSS_OBJECT;

		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	@Override
	protected void defineProperties() {
		final String CRLF = DSS.CRLF;

		numProperties = XfmrCode.NumPropsThisClass;
		countProperties();  // get inherited property count

		allocatePropertyArrays();

		propertyName[0] = "phases";
		propertyName[1] = "windings";

		// winding definition
		propertyName[2] = "wdg";
		propertyName[3] = "conn";
		propertyName[4] = "kV"; // for 2-and 3- always kVLL else actual winding KV
		propertyName[5] = "kVA";
		propertyName[6] = "tap";
		propertyName[7] = "%R";
		propertyName[8] = "Rneut";
		propertyName[9] = "Xneut";

		// general data
		propertyName[10] = "conns";
		propertyName[11] = "kVs";
		propertyName[12] = "kVAs";
		propertyName[13] = "taps";
		propertyName[14] = "Xhl";
		propertyName[15] = "Xht";
		propertyName[16] = "Xlt";
		propertyName[17] = "Xscarray";  // x12 13 14... 23 24.. 34 ..
		propertyName[18] = "thermal";
		propertyName[19] = "n";
		propertyName[20] = "m";
		propertyName[21] = "flrise";
		propertyName[22] = "hsrise";
		propertyName[23] = "%loadloss";
		propertyName[24] = "%noloadloss";
		propertyName[25] = "normhkVA";
		propertyName[26] = "emerghkVA";
		propertyName[27] = "MaxTap";
		propertyName[28] = "MinTap";
		propertyName[29] = "NumTaps";
		propertyName[30] = "%imag";
		propertyName[31] = "ppm_antifloat";
		propertyName[32] = "%Rs";

		// define property help values
		propertyHelp[0] = "Number of phases this transformer. Default is 3.";
		propertyHelp[1] = "Number of windings, this transformers. (Also is the number of terminals) "+
					"Default is 2.";
		// winding definition
		propertyHelp[2] = "Set this = to the number of the winding you wish to define.  Then set "+
				"the values for this winding.  Repeat for each winding.  Alternatively, use "+
				"the array collections (buses, kvas, etc.) to define the windings.  Note: "+
				"reactances are BETWEEN pairs of windings; they are not the property of a single winding.";
		propertyHelp[3] = "Connection of this winding. Default is \"wye\" with the neutral solidly grounded.";
		propertyHelp[4] = "For 2-or 3-phase, enter phase-phase kV rating.  Otherwise, kV rating of the actual winding";
		propertyHelp[5] = "Base kVA rating of the winding. Side effect: forces change of max normal and emerg kva ratings." +
				"If 2-winding transformer, forces other winding to same value. " +
				"When winding 1 is defined, all other windings are defaulted to the same rating " +
				"and the first two winding resistances are defaulted to the %loadloss value.";
		propertyHelp[6] = "Per unit tap that this winding is normally on.";
		propertyHelp[7] = "Percent resistance this winding.  (half of total for a 2-winding).";
		propertyHelp[8] = "Default = -1. Neutral resistance of wye (star)-connected winding in actual ohms." +
				"If entered as a negative value, the neutral is assumed to be open, or floating.";
		propertyHelp[9] = "Neutral reactance of wye(star)-connected winding in actual ohms.  May be + or -.";

		// general data
		propertyHelp[10] = "Use this to specify all the Winding connections at once using an array. Example:"+CRLF+CRLF+
				"New Transformer.T1 buses=\"Hibus, lowbus\" "+
				"~ conns=(delta, wye)";
		propertyHelp[11] = "Use this to specify the kV ratings of all windings at once using an array. Example:"+CRLF+CRLF+
				"New Transformer.T1 buses=\"Hibus, lowbus\" "+CRLF+
				"~ conns=(delta, wye)"+CRLF+
				"~ kvs=(115, 12.47)"+CRLF+CRLF+
				"See kV= property for voltage rules.";
		propertyHelp[12] = "Use this to specify the kVA ratings of all windings at once using an array.";
		propertyHelp[13] = "Use this to specify the normal p.u. tap of all windings at once using an array.";
		propertyHelp[14] = "Use this to specify the percent reactance, H-L (winding 1 to winding 2).  Use "+
				"for 2- or 3-winding transformers. On the kva base of winding 1.";
		propertyHelp[15] = "Use this to specify the percent reactance, H-T (winding 1 to winding 3).  Use "+
				"for 3-winding transformers only. On the kVA base of winding 1.";
		propertyHelp[16] = "Use this to specify the percent reactance, L-T (winding 2 to winding 3).  Use "+
				"for 3-winding transformers only. On the kVA base of winding 1.";
		propertyHelp[17] = "Use this to specify the percent reactance between all pairs of windings as an array. "+
				"All values are on the kVA base of winding 1.  The order of the values is as follows:"+CRLF+CRLF+
				"(x12 13 14... 23 24.. 34 ..)  "+CRLF+CRLF+
				"There will be n(n-1)/2 values, where n=number of windings.";
		propertyHelp[18] = "Thermal time constant of the transformer in hours.  Typically about 2.";
		propertyHelp[19] = "n Exponent for thermal properties in IEEE C57.  Typically 0.8.";
		propertyHelp[20] = "m Exponent for thermal properties in IEEE C57.  Typically 0.9 - 1.0";
		propertyHelp[21] = "Temperature rise, deg C, for full load.  Default is 65.";
		propertyHelp[22] = "Hot spot temperature rise, deg C.  Default is 15.";
		propertyHelp[23] = "Percent load loss at full load. The %R of the High and Low windings (1 and 2) are adjusted to agree at rated kVA loading.";
		propertyHelp[24] = "Percent no load losses at rated excitatation voltage. Default is 0. Converts to a resistance in parallel with the magnetizing impedance in each winding.";
		propertyHelp[25] = "Normal maximum kVA rating of H winding (winding 1).  Usually 100% - 110% of"+
				"maximum nameplate rating, depending on load shape. Defaults to 110% of kVA rating of Winding 1.";
		propertyHelp[26] = "Emergency (contingency)  kVA rating of H winding (winding 1).  Usually 140% - 150% of"+
				"maximum nameplate rating, depending on load shape. Defaults to 150% of kVA rating of Winding 1.";
		propertyHelp[27] = "Max per unit tap for the active winding.  Default is 1.10";
		propertyHelp[28] = "Min per unit tap for the active winding.  Default is 0.90";
		propertyHelp[29] = "Total number of taps between min and max tap.  Default is 32.";
		propertyHelp[30] = "Percent magnetizing current. Default=0.0. Magnetizing branch is in parallel with windings in each phase. Also, see \"ppm_antifloat\".";
		propertyHelp[31] = "Default=1 ppm.  Parts per million of transformer winding VA rating connected to ground to protect against accidentally floating a winding without a reference. " +
				"If positive then the effect is adding a very large reactance to ground.  If negative, then a capacitor.";
		propertyHelp[32] = "Use this property to specify all the winding %resistances using an array. Example:"+CRLF+CRLF+
				"New Transformer.T1 buses=\"Hibus, lowbus\" "+
				"~ %Rs=(0.2  0.3)";

		activeProperty = NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list

	}

	@Override
	public int newObject(String objName) {
		DSS.activeDSSObject = new XfmrCodeObj(this, objName);
		return addObjectToList(DSS.activeDSSObject);
	}

	private void interpretWindings(String s, WdgParmChoice which) {
		String str;

		DSS.auxParser.setCmdString(s);
		XfmrCodeObj elem = activeXfmrCodeObj;

		for (int i = 0; i < elem.getNumWindings(); i++) {
			elem.setActiveWindingIdx(i);
			DSS.auxParser.getNextParam();  // ignore any parameter name not expecting any
			str = DSS.auxParser.makeString();

			if (str.length() > 0) {
				switch (which) {
				case CONN:
					elem.getWinding(elem.getActiveWindingIdx()).setConnection(Util.interpretConnection(str));
					break;
				case KV:
					elem.getWinding(elem.getActiveWindingIdx()).setKVLL(DSS.auxParser.makeDouble());
					break;
				case KVA:
					elem.getWinding(elem.getActiveWindingIdx()).setKVA(DSS.auxParser.makeDouble());
					break;
				case R:
					elem.getWinding(elem.getActiveWindingIdx()).setRpu(0.01 * DSS.auxParser.makeDouble());
					break;
				case TAP:
					elem.getWinding(elem.getActiveWindingIdx()).setPuTap(DSS.auxParser.makeDouble());
					break;
				}
			}
		}
	}

	@Override
	public int edit() {
		activeXfmrCodeObj = (XfmrCodeObj) elementList.getActive();
		DSS.activeDSSObject = activeXfmrCodeObj;
		boolean updateXsc = false;

		Parser parser = Parser.getInstance();
		XfmrCodeObj elem = activeXfmrCodeObj;

		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.makeString();

		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if (paramPointer >= 0 && paramPointer < numProperties)
				elem.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"XfmrCode." +
						elem.getName() + "\"", 110);
				break;
			case 0:
				elem.setNPhases(parser.makeInteger());
				break;
			case 1:
				elem.setNumWindings(parser.makeInteger());  // reallocate stuff if bigger
				break;
			case 2:
				elem.setActiveWindingIdx(parser.makeInteger() - 1);
				break;
			case 3:
				elem.getWinding(elem.getActiveWindingIdx()).setConnection(Util.interpretConnection(param));
				break;
			case 4:
				elem.getWinding(elem.getActiveWindingIdx()).setKVLL(parser.makeDouble());
				break;
			case 5:
				elem.getWinding(elem.getActiveWindingIdx()).setKVA(parser.makeDouble());
				break;
			case 6:
				elem.getWinding(elem.getActiveWindingIdx()).setPuTap(parser.makeDouble());
				break;
			case 7:
				elem.getWinding(elem.getActiveWindingIdx()).setRpu(parser.makeDouble() * 0.01);  // %R
				break;
			case 8:
				elem.getWinding(elem.getActiveWindingIdx()).setRNeut(parser.makeDouble());
				break;
			case 9:
				elem.getWinding(elem.getActiveWindingIdx()).setXNeut(parser.makeDouble());
				break;
			case 10:
				interpretWindings(param, WdgParmChoice.CONN);
				break;
			case 11:
				interpretWindings(param, WdgParmChoice.KV);
				break;
			case 12:
				interpretWindings(param, WdgParmChoice.KVA);
				break;
			case 13:
				interpretWindings(param, WdgParmChoice.TAP);
				break;
			case 14:
				elem.setXHL(parser.makeDouble() * 0.01);
				break;
			case 15:
				elem.setXHT(parser.makeDouble() * 0.01);
				break;
			case 16:
				elem.setXLT(parser.makeDouble() * 0.01);
				break;
			case 17:
				parser.parseAsVector(((elem.getNumWindings() - 1) * elem.getNumWindings() / 2), elem.getXSC());
				break;
			case 18:
				elem.setThermalTimeConst(parser.makeDouble());
				break;
			case 19:
				elem.setNThermal(parser.makeDouble());
				break;
			case 20:
				elem.setMThermal(parser.makeDouble());
				break;
			case 21:
				elem.setLRise(parser.makeDouble());
				break;
			case 22:
				elem.setHSRise(parser.makeDouble());
				break;
			case 23:
				elem.setPctLoadLoss(parser.makeDouble());
				break;
			case 24:
				elem.setPctNoLoadLoss(parser.makeDouble());
				break;
			case 25:
				elem.setNormMaxHKVA(parser.makeDouble());
				break;
			case 26:
				elem.setEmergMaxHKVA(parser.makeDouble());
				break;
			case 27:
				elem.getWinding(elem.getActiveWindingIdx()).setMaxTap(parser.makeDouble());
				break;
			case 28:
				elem.getWinding(elem.getActiveWindingIdx()).setMinTap(parser.makeDouble());
				break;
			case 29:
				elem.getWinding(elem.getActiveWindingIdx()).setNumTaps(parser.makeInteger());
				break;
			case 30:
				elem.setPctImag(parser.makeDouble());
				break;
			case 31:
				elem.setPpmFloatFactor(parser.makeDouble() * 1.0e-6);
				break;
			case 32:
				interpretWindings(param, WdgParmChoice.R);
				break;
			default:
				classEdit(activeXfmrCodeObj, paramPointer - XfmrCode.NumPropsThisClass);
				break;
			}

			/* Take care of properties that require some additional work, */
			switch (paramPointer) {
			// default all winding kVAs to first winding so latter do not have to be specified
			case 5:
				if (elem.getActiveWindingIdx() == 0) {
					for (int i = 1; i < elem.getNumWindings(); i++)
						elem.getWinding(i).setKVA(elem.getWinding(0).getKVA());
					elem.setNormMaxHKVA(1.1 * elem.getWinding(0).getKVA());    // defaults for new winding rating
					elem.setEmergMaxHKVA(1.5 * elem.getWinding(0).getKVA());
				} else {
					if (elem.getNumWindings() == 2)
						elem.getWinding(0).setKVA(elem.getWinding(1).getKVA());  // for 2-winding, force both kVAs to be same
				}
				// update loadLosskW if winding %r changed; using only windings 1 and 2
				break;
			case 7:
				elem.setPctLoadLoss(elem.getWinding(0).getRpu() + elem.getWinding(1).getRpu() * 100.0);
				break;
			case 12:
				elem.setNormMaxHKVA(1.1 * elem.getWinding(0).getKVA());  // defaults for new winding rating
				elem.setEmergMaxHKVA(1.5 * elem.getWinding(0).getKVA());
				break;
			case 14:
			case 15:
			case 16:
				updateXsc = true;
				break;
			case 17:
				for (int i = 0; i < ((elem.getNumWindings() - 1) * elem.getNumWindings() / 2); i++)
					elem.getXSC()[i] = elem.getXSC()[i] * 0.01;  // convert to per unit
				break;
			case 23:  // assume load loss is split evenly between windings 1 and 2
				elem.getWinding(0).setRpu(elem.getPctLoadLoss() / 2.0 / 100.0);
				elem.getWinding(1).setRpu(elem.getWinding(0).getRpu());
				break;
			case 32:
				elem.setPctLoadLoss( (elem.getWinding(0).getRpu() + elem.getWinding(1).getRpu()) * 100.0 );  // keep this up to date
			}
			/* advance to next property on input line */
			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		if (updateXsc) {
			if (elem.getNumWindings() <= 3) {
				for (int i = 0; i < (elem.getNumWindings() * (elem.getNumWindings() - 1) / 2); i++) {
					switch (i) {
					case 0:
						elem.getXSC()[0] = elem.getXHL();
						break;
					case 1:
						elem.getXSC()[1] = elem.getXHT();
						break;
					case 2:
						elem.getXSC()[2] = elem.getXLT();
						break;
					}
				}
			}
		}

		return 0;
	}

	@Override
	protected int makeLike(String name) {
		int i, success = 0;

		/* See if we can find this ode in the present collection */
		XfmrCodeObj other = (XfmrCodeObj) find(name);

		if (other != null) {
			XfmrCodeObj elem = activeXfmrCodeObj;

			elem.setNPhases(other.getNPhases());
			elem.setNumWindings(other.getNumWindings());
			for (i = 0; i < elem.getNumWindings(); i++) {
				Winding wdg = elem.getWinding(i);

				wdg.setConnection(other.getWinding(i).getConnection());
				wdg.setKVLL(other.getWinding(i).getKVLL());
				wdg.setVbase(other.getWinding(i).getVbase());
				wdg.setKVA(other.getWinding(i).getKVA());
				wdg.setPuTap(other.getWinding(i).getPuTap());
				wdg.setRpu(other.getWinding(i).getRpu());
				wdg.setRNeut(other.getWinding(i).getRNeut());
				wdg.setXNeut(other.getWinding(i).getXNeut());
				wdg.setTapIncrement(other.getWinding(i).getTapIncrement());
				wdg.setMinTap(other.getWinding(i).getMinTap());
				wdg.setMaxTap(other.getWinding(i).getMaxTap());
				wdg.setNumTaps(other.getWinding(i).getNumTaps());
			}
			elem.setXHL(other.getXHL());
			elem.setXHT(other.getXHT());
			elem.setXLT(other.getXLT());
			for (i = 0; i < (elem.getNumWindings() * (elem.getNumWindings() - 1) / 2); i++)
				elem.getXSC()[i] = other.getXSC()[i];
			elem.setThermalTimeConst(other.getThermalTimeConst());
			elem.setNThermal(other.getNThermal());
			elem.setMThermal(other.getMThermal());
			elem.setLRise(other.getLRise());
			elem.setHSRise(other.getHSRise());
			elem.setPctLoadLoss(other.getPctLoadLoss());
			elem.setPctNoLoadLoss(other.getPctNoLoadLoss());
			elem.setNormMaxHKVA(other.getNormMaxHKVA());
			elem.setEmergMaxHKVA(other.getEmergMaxHKVA());

			for (i = 0; i < elem.getParentClass().getNumProperties(); i++){
				elem.setPropertyValue(i, other.getPropertyValue(i));
			}
			success = 1;
		} else {
			DSS.doSimpleMsg("Error in XfmrCode.makeLike: \"" + name + "\" not found.", 102);
		}
		return success;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement XfmrCode.init", -1);
		return 0;
	}

	public String getCode() {
		XfmrCodeObj elem = (XfmrCodeObj) elementList.getActive();
		return elem.getName();
	}

	public void setCode(String value) {
		XfmrCodeObj elem;

		activeXfmrCodeObj = null;

		for (int i = 0; i < elementList.size(); i++) {
			elem = (XfmrCodeObj) elementList.get(i);
			if (elem.getName().equalsIgnoreCase(value)) {
				activeXfmrCodeObj = elem;
				return;
			}
		}
		DSS.doSimpleMsg("XfmrCode: \"" + value + "\" not found.", 103);
	}

}
