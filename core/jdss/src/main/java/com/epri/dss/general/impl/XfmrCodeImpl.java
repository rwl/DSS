package com.epri.dss.general.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.delivery.Winding;
import com.epri.dss.general.XfmrCode;
import com.epri.dss.general.XfmrCodeObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class XfmrCodeImpl extends DSSClassImpl implements XfmrCode {

	private enum WdgParmChoice {CONN, KV, KVA, R, TAP};

	public static XfmrCodeObj activeXfmrCodeObj;

	public XfmrCodeImpl() {
		super();
		className = "XfmrCode";
		classType = DSSClassDefs.DSS_OBJECT;

		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandListImpl(commands);
		commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		final String CRLF = DSSGlobals.CRLF;

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
		DSSGlobals.activeDSSObject = new XfmrCodeObjImpl(this, objName);
		return addObjectToList(DSSGlobals.activeDSSObject);
	}

	private void setActiveWinding(int w) {
		XfmrCodeObj axc = activeXfmrCodeObj;

		if (w >= 0 && w < axc.getNumWindings()) {
			axc.setActiveWinding(w);
		} else {
			DSSGlobals.doSimpleMsg("Wdg parameter invalid for \"" + activeXfmrCodeObj.getName() + "\"", 112);
		}
	}

	private void interpretWindings(String s, WdgParmChoice which) {
		String str;

		DSSGlobals.auxParser.setCmdString(s);
		XfmrCodeObj axc = activeXfmrCodeObj;
		for (int i = 0; i < axc.getNumWindings(); i++) {
			axc.setActiveWinding(i);
			DSSGlobals.auxParser.getNextParam();  // ignore any parameter name not expecting any
			str = DSSGlobals.auxParser.makeString();
			if (str.length() > 0) {
				switch (which) {
				case CONN:
					axc.getWinding()[axc.getActiveWinding()].setConnection(Utilities.interpretConnection(str));
					break;
				case KV:
					axc.getWinding()[axc.getActiveWinding()].setKVLL(DSSGlobals.auxParser.makeDouble());
					break;
				case KVA:
					axc.getWinding()[axc.getActiveWinding()].setKVA(DSSGlobals.auxParser.makeDouble());
					break;
				case R:
					axc.getWinding()[axc.getActiveWinding()].setRpu(0.01 * DSSGlobals.auxParser.makeDouble());
					break;
				case TAP:
					axc.getWinding()[axc.getActiveWinding()].setPUTap(DSSGlobals.auxParser.makeDouble());
					break;
				}
			}
		}
	}

	@Override
	public int edit() {
		activeXfmrCodeObj = (XfmrCodeObj) elementList.getActive();
		DSSGlobals.activeDSSObject = activeXfmrCodeObj;
		boolean updateXsc = false;

		Parser parser = Parser.getInstance();
		XfmrCodeObj axc = activeXfmrCodeObj;

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
				axc.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSSGlobals.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"XfmrCode." + axc.getName() + "\"", 110);
				break;
			case 0:
				axc.setNPhases(parser.makeInteger());
				break;
			case 1:
				axc.setNumWindings(parser.makeInteger());  // reallocate stuff if bigger
				break;
			case 2:
				axc.setActiveWinding(parser.makeInteger());
				break;
			case 3:
				axc.getWinding()[axc.getActiveWinding()].setConnection(Utilities.interpretConnection(param));
				break;
			case 4:
				axc.getWinding()[axc.getActiveWinding()].setKVLL(parser.makeDouble());
				break;
			case 5:
				axc.getWinding()[axc.getActiveWinding()].setKVA(parser.makeDouble());
				break;
			case 6:
				axc.getWinding()[axc.getActiveWinding()].setPUTap(parser.makeDouble());
				break;
			case 7:
				axc.getWinding()[axc.getActiveWinding()].setRpu(parser.makeDouble() * 0.01);  // %R
				break;
			case 8:
				axc.getWinding()[axc.getActiveWinding()].setRNeut(parser.makeDouble());
				break;
			case 9:
				axc.getWinding()[axc.getActiveWinding()].setXNeut(parser.makeDouble());
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
				axc.setXHL(parser.makeDouble() * 0.01);
				break;
			case 15:
				axc.setXHT(parser.makeDouble() * 0.01);
				break;
			case 16:
				axc.setXLT(parser.makeDouble() * 0.01);
				break;
			case 17:
				parser.parseAsVector(((axc.getNumWindings() - 1) * axc.getNumWindings() / 2), axc.getXSC());
				break;
			case 18:
				axc.setThermalTimeConst(parser.makeDouble());
				break;
			case 19:
				axc.setNThermal(parser.makeDouble());
				break;
			case 20:
				axc.setMThermal(parser.makeDouble());
				break;
			case 21:
				axc.setLRise(parser.makeDouble());
				break;
			case 22:
				axc.setHSRise(parser.makeDouble());
				break;
			case 23:
				axc.setPctLoadLoss(parser.makeDouble());
				break;
			case 24:
				axc.setPctNoLoadLoss(parser.makeDouble());
				break;
			case 25:
				axc.setNormMaxHKVA(parser.makeDouble());
				break;
			case 26:
				axc.setEmergMaxHKVA(parser.makeDouble());
				break;
			case 27:
				axc.getWinding()[axc.getActiveWinding()].setMaxTap(parser.makeDouble());
				break;
			case 28:
				axc.getWinding()[axc.getActiveWinding()].setMinTap(parser.makeDouble());
				break;
			case 29:
				axc.getWinding()[axc.getActiveWinding()].setNumTaps(parser.makeInteger());
				break;
			case 30:
				axc.setPctImag(parser.makeDouble());
				break;
			case 31:
				axc.setPpmFloatFactor(parser.makeDouble() * 1.0e-6);
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
				if (axc.getActiveWinding() == 1) {  // TODO Check zero based indexing
					for (int i = 1; i < axc.getNumWindings(); i++)
						axc.getWinding()[i].setKVA(axc.getWinding()[0].getKVA());  // TODO Check zero based indexing
					axc.setNormMaxHKVA(1.1 * axc.getWinding()[0].getKVA());    // defaults for new winding rating
					axc.setEmergMaxHKVA(1.5 * axc.getWinding()[0].getKVA());
				} else {
					if (axc.getNumWindings() == 2)
						axc.getWinding()[0].setKVA(axc.getWinding()[1].getKVA());  // for 2-winding, force both kVAs to be same
				}
				// update LoadLosskW if winding %r changed; using only windings 1 and 2
				break;
			case 7:
				axc.setPctLoadLoss(axc.getWinding()[0].getRpu() + axc.getWinding()[1].getRpu() * 100.0);
				break;
			case 12:
				axc.setNormMaxHKVA(1.1 * axc.getWinding()[0].getKVA());  // defaults for new winding rating
				axc.setEmergMaxHKVA(1.5 * axc.getWinding()[0].getKVA());
				break;
			case 14:
				updateXsc = true;
				break;
			case 15:
				updateXsc = true;
				break;
			case 16:
				updateXsc = true;
				break;
			case 17:
				for (int i = 0; i < ((axc.getNumWindings() - 1) * axc.getNumWindings() / 2); i++)
					axc.getXSC()[i] = axc.getXSC()[i] * 0.01;  // Convert to per unit
				break;
			case 23:  // assume load loss is split evenly between windings 1 and 2
				axc.getWinding()[0].setRpu(axc.getPctLoadLoss() / 2.0 / 100.0);
				axc.getWinding()[1].setRpu(axc.getWinding()[0].getRpu());
				break;
			case 32:
				axc.setPctLoadLoss( (axc.getWinding()[0].getRpu() + axc.getWinding()[1].getRpu()) * 100.0 ); // Keep this up to date
			}
			/* advance to next property on input line */
			paramName = parser.getNextParam();
			param     = parser.makeString();
		}

		if (updateXsc) {
			if (axc.getNumWindings() <= 3) {
				for (int i = 0; i < (axc.getNumWindings() * (axc.getNumWindings() - 1) / 2); i++) {
					switch (i) {
					case 0:
						axc.getXSC()[0] = axc.getXHL();
						break;
					case 1:
						axc.getXSC()[1] = axc.getXHT();
						break;
					case 2:
						axc.getXSC()[2] = axc.getXLT();
						break;
					}
				}
			}
		}

		return 0;
	}

	@Override
	protected int makeLike(String name) {
		int i;
		int result = 0;
		/* See if we can find this ode in the present collection */
		XfmrCodeObj other = (XfmrCodeObj) find(name);
		if (other != null) {
			XfmrCodeObj axc = activeXfmrCodeObj;

			axc.setNPhases(other.getNPhases());
			axc.setNumWindings(other.getNumWindings());
			for (i = 0; i < axc.getNumWindings(); i++) {
				Winding wdg = axc.getWinding()[i];

				wdg.setConnection(other.getWinding()[i].getConnection());
				wdg.setKVLL(other.getWinding()[i].getKVLL());
				wdg.setVBase(other.getWinding()[i].getVBase());
				wdg.setKVA(other.getWinding()[i].getKVA());
				wdg.setPUTap(other.getWinding()[i].getPUTap());
				wdg.setRpu(other.getWinding()[i].getRpu());
				wdg.setRNeut(other.getWinding()[i].getRNeut());
				wdg.setXNeut(other.getWinding()[i].getXNeut());
				wdg.setTapIncrement(other.getWinding()[i].getTapIncrement());
				wdg.setMinTap(other.getWinding()[i].getMinTap());
				wdg.setMaxTap(other.getWinding()[i].getMaxTap());
				wdg.setNumTaps(other.getWinding()[i].getNumTaps());
			}
			axc.setXHL(other.getXHL());
			axc.setXHT(other.getXHT());
			axc.setXLT(other.getXLT());
			for (i = 0; i < (axc.getNumWindings() * (axc.getNumWindings() - 1) / 2); i++)
				axc.getXSC()[i] = other.getXSC()[i];
			axc.setThermalTimeConst(other.getThermalTimeConst());
			axc.setNThermal(other.getNThermal());
			axc.setMThermal(other.getMThermal());
			axc.setLRise(other.getLRise());
			axc.setHSRise(other.getHSRise());
			axc.setPctLoadLoss(other.getPctLoadLoss());
			axc.setPctNoLoadLoss(other.getPctNoLoadLoss());
			axc.setNormMaxHKVA(other.getNormMaxHKVA());
			axc.setEmergMaxHKVA(other.getEmergMaxHKVA());

			for (i = 0; i < axc.getParentClass().getNumProperties(); i++){
				axc.setPropertyValue(i, other.getPropertyValue(i));
				result = 1;
			}
		} else {
			DSSGlobals.doSimpleMsg("Error in XfmrCode.makeLike: \"" + name + "\" not found.", 102);
		}
		return result;
	}

	@Override
	public int init(int handle) {
		DSSGlobals.doSimpleMsg("Need to implement XfmrCode.init", -1);
		return 0;
	}

	public String getCode() {
		XfmrCodeObj pXfmrCode = (XfmrCodeObj) elementList.getActive();
		return pXfmrCode.getName();
	}

	public void setCode(String value) {
		XfmrCodeObj xfmrCode;

		activeXfmrCodeObj = null;
		for (int i = 0; i < elementList.size(); i++) {
			xfmrCode = (XfmrCodeObj) elementList.get(i);
			if (xfmrCode.getName().equalsIgnoreCase(value)) {
				activeXfmrCodeObj = xfmrCode;
				return;
			}
		}
		DSSGlobals.doSimpleMsg("XfmrCode: \"" + value + "\" not found.", 103);
	}

}
