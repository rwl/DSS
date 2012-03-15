package com.ncond.dss.delivery;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;
import com.ncond.dss.common.types.Connection;
import com.ncond.dss.general.XfmrCode;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class Transformer extends PDClass {

	public static final int NumPropsThisClass = 39;

	public static TransformerObj activeTransfObj;

	public static XfmrCode XfmrCodeClass = null;

	public Transformer() {
		super();
		className = "Transformer";
		classType = classType + DSSClassDefs.XFMR_ELEMENT; // override PDElement (kept in both actually)

		activeElement = -1;

		defineProperties();

		/* Make space for transformer property list */
		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);  // allow property list abbreviations
	}

	@Override
	protected void defineProperties() {
		numProperties = Transformer.NumPropsThisClass;
		countProperties();  // get inherited property count

		allocatePropertyArrays();

		/* Define property names */
		propertyName[0] = "phases";
		propertyName[1] = "windings";

		// winding definition
		propertyName[2] = "wdg";
		propertyName[3] = "bus";
		propertyName[4] = "conn";
		propertyName[5] = "kV";  // for 2-and 3- always kVLL else actual winding kV
		propertyName[6] = "kVA";
		propertyName[7] = "tap";
		propertyName[8] = "%R";
		propertyName[9] = "Rneut";
		propertyName[10] = "Xneut";

		// general data
		propertyName[11] = "buses";
		propertyName[12] = "conns";
		propertyName[13] = "kVs";
		propertyName[14] = "kVAs";
		propertyName[15] = "taps";
		propertyName[16] = "Xhl";
		propertyName[17] = "Xht";
		propertyName[18] = "Xlt";
		propertyName[19] = "Xscarray";  // x12 13 14... 23 24.. 34 ..
		propertyName[20] = "thermal";
		propertyName[21] = "n";
		propertyName[22] = "m";
		propertyName[23] = "flrise";
		propertyName[24] = "hsrise";
		propertyName[25] = "%loadloss";
		propertyName[26] = "%noloadloss";
		propertyName[27] = "normhkVA";
		propertyName[28] = "emerghkVA";
		propertyName[29] = "sub";  // =y/n
		propertyName[30] = "MaxTap";
		propertyName[31] = "MinTap";
		propertyName[32] = "NumTaps";
		propertyName[33] = "subname";
		propertyName[34] = "%imag";
		propertyName[35] = "ppm_antifloat";
		propertyName[36] = "%Rs";

		propertyName[37] = "bank";
		propertyName[38] = "XfmrCode";


		// define property help values
		propertyHelp[0] = "Number of phases this transformer. Default is 3.";
		propertyHelp[1] = "Number of windings, this transformers. (Also is the number of terminals) "+
				"Default is 2.";
		// winding definition
		propertyHelp[2] = "Set this = to the number of the winding you wish to define.  Then set "+
				"the values for this winding.  Repeat for each winding.  Alternatively, use "+
				"the array collections (buses, kvas, etc.) to define the windings.  Note: "+
				"reactances are BETWEEN pairs of windings; they are not the property of a single winding.";
		propertyHelp[3] = "Bus connection spec for this winding.";
		propertyHelp[4] = "Connection of this winding. Default is \"wye\" with the neutral solidly grounded.";
		propertyHelp[5] = "For 2-or 3-phase, enter phase-phase kV rating.  Otherwise, kV rating of the actual winding";
		propertyHelp[6] = "Base kVA rating of the winding. Side effect: forces change of max normal and emerg kva ratings." +
				"If 2-winding transformer, forces other winding to same value. " +
				"When winding 1 is defined, all other windings are defaulted to the same rating " +
				"and the first two winding resistances are defaulted to the %loadloss value.";
		propertyHelp[7] = "Per unit tap that this winding is on.";
		propertyHelp[8] = "Percent resistance this winding.  (half of total for a 2-winding).";
		propertyHelp[9] = "Default = -1. Neutral resistance of wye (star)-connected winding in actual ohms." +
				"If entered as a negative value, the neutral is assumed to be open, or floating.";
		propertyHelp[10] = "Neutral reactance of wye(star)-connected winding in actual ohms.  May be + or -.";

		// general data
		propertyHelp[11] = "Use this to specify all the bus connections at once using an array. Example:"+DSS.CRLF+DSS.CRLF+
				"New Transformer.T1 buses=\"Hibus, lowbus\"";
		propertyHelp[12] = "Use this to specify all the Winding connections at once using an array. Example:"+DSS.CRLF+DSS.CRLF+
				"New Transformer.T1 buses=\"Hibus, lowbus\" "+
				"~ conns=(delta, wye)";
		propertyHelp[13] = "Use this to specify the kV ratings of all windings at once using an array. Example:"+DSS.CRLF+DSS.CRLF+
				"New Transformer.T1 buses=\"Hibus, lowbus\" "+DSS.CRLF+
				"~ conns=(delta, wye)"+DSS.CRLF+
				"~ kvs=(115, 12.47)"+DSS.CRLF+DSS.CRLF+
				"See kV= property for voltage rules.";
		propertyHelp[14] = "Use this to specify the kVA ratings of all windings at once using an array.";
		propertyHelp[15] = "Use this to specify the p.u. tap of all windings at once using an array.";
		propertyHelp[16] = "Use this to specify the percent reactance, H-L (winding 1 to winding 2).  Use "+
				"for 2- or 3-winding transformers. On the kva base of winding 1.";
		propertyHelp[17] = "Use this to specify the percent reactance, H-T (winding 1 to winding 3).  Use "+
				"for 3-winding transformers only. On the kVA base of winding 1.";
		propertyHelp[18] = "Use this to specify the percent reactance, L-T (winding 2 to winding 3).  Use "+
				"for 3-winding transformers only. On the kVA base of winding 1.";
		propertyHelp[19] = "Use this to specify the percent reactance between all pairs of windings as an array. "+
				"All values are on the kVA base of winding 1.  The order of the values is as follows:"+DSS.CRLF+DSS.CRLF+
				"(x12 13 14... 23 24.. 34 ..)  "+DSS.CRLF+DSS.CRLF+
				"There will be n(n-1)/2 values, where n=number of windings.";
		propertyHelp[20] = "Thermal time constant of the transformer in hours.  Typically about 2.";
		propertyHelp[21] = "n Exponent for thermal properties in IEEE C57.  Typically 0.8.";
		propertyHelp[22] = "m Exponent for thermal properties in IEEE C57.  Typically 0.9 - 1.0";
		propertyHelp[23] = "Temperature rise, deg C, for full load.  Default is 65.";
		propertyHelp[24] = "Hot spot temperature rise, deg C.  Default is 15.";
		propertyHelp[25] = "Percent load loss at full load. The %R of the High and Low windings (1 and 2) are adjusted to agree at rated kVA loading.";
		propertyHelp[26] = "Percent no load losses at rated excitatation voltage. Default is 0. Converts to a resistance in parallel with the magnetizing impedance in each winding.";
		propertyHelp[27] = "Normal maximum kVA rating of H winding (winding 1).  Usually 100% - 110% of"+
				"maximum nameplate rating, depending on load shape. Defaults to 110% of kVA rating of Winding 1.";
		propertyHelp[28] = "Emergency (contingency)  kVA rating of H winding (winding 1).  Usually 140% - 150% of"+
				"maximum nameplate rating, depending on load shape. Defaults to 150% of kVA rating of Winding 1.";
		propertyHelp[29] = "={Yes|No}  Designates whether this transformer is to be considered a substation."+
				"Default is No.";  // =y/n

		propertyHelp[30] = "Max per unit tap for the active winding.  Default is 1.10";
		propertyHelp[31] = "Min per unit tap for the active winding.  Default is 0.90";
		propertyHelp[32] = "Total number of taps between min and max tap.  Default is 32.";
		propertyHelp[33] = "Substation Name. Optional. Default is null. If specified, printed on plots";
		propertyHelp[34] = "Percent magnetizing current. Default=0.0. Magnetizing branch is in parallel with windings in each phase. Also, see \"ppm_antifloat\".";
		propertyHelp[35] = "Default=1 ppm.  Parts per million of transformer winding VA rating connected to ground to protect against accidentally floating a winding without a reference. " +
				"If positive then the effect is adding a very large reactance to ground.  If negative, then a capacitor.";
		propertyHelp[36] = "Use this property to specify all the winding %resistances using an array. Example:"+DSS.CRLF+DSS.CRLF+
				"New Transformer.T1 buses=\"Hibus, lowbus\" "+
				"~ %Rs=(0.2  0.3)";
		propertyHelp[37] = "Name of the bank this transformer is part of, for CIM, MultiSpeak, and other interfaces.";
		propertyHelp[38] = "Name of a library entry for transformer properties. The named XfmrCode must already be defined.";

		activeProperty = Transformer.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSS.activeCircuit.setActiveCktElement(new TransformerObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	/**
	 * A Transf defaults to 3-phases, 2-windings (both wye).
	 */
	@Override
	public int edit() {
		// continue parsing cmdline presently in parser
		Parser parser = Parser.getInstance();

		/* Make this object the active circuit element */
		activeTransfObj = (TransformerObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeTransfObj);  // use property to set this value

		TransformerObj elem = activeTransfObj;

		elem.setXHLChanged(false);

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
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"Transformer." +
						elem.getName() + "\"", 110);
				break;
			case 0:
				elem.setNumPhases(parser.makeInteger());
				break;
			case 1:
				elem.setNumWindings(parser.makeInteger());  // reallocate stuff if bigger
				break;
			case 2:
				setActiveWinding(parser.makeInteger());
				break;
			case 3:
				elem.setBus(elem.getActiveWindingIdx(), param);
				break;
			case 4:
				interpretConnection(param);
				break;
			case 5:
				elem.getWinding(elem.getActiveWindingIdx()).setKVLL(parser.makeDouble());
				break;
			case 6:
				elem.getWinding(elem.getActiveWindingIdx()).setKVA(parser.makeDouble());
				break;
			case 7:
				elem.getWinding(elem.getActiveWindingIdx()).setPuTap(parser.makeDouble());
				break;
			case 8:
				elem.getWinding(elem.getActiveWindingIdx()).setRpu(parser.makeDouble() * 0.01);  // %R
				break;
			case 9:
				elem.getWinding(elem.getActiveWindingIdx()).setRNeut(parser.makeDouble());
				break;
			case 10:
				elem.getWinding(elem.getActiveWindingIdx()).setXNeut(parser.makeDouble());
				break;
			case 11:
				interpretAllBuses(param);
				break;
			case 12:
				interpretAllConns(param);
				break;
			case 13:
				interpretAllkVRatings(param);
				break;
			case 14:
				interpretAllkVARatings(param);
				break;
			case 15:
				interpretAllTaps(param);
				break;
			case 16:
				elem.setXHL(parser.makeDouble() * 0.01);
				break;
			case 17:
				elem.setXHT(parser.makeDouble() * 0.01);
				break;
			case 18:
				elem.setXLT(parser.makeDouble() * 0.01);
				break;
			case 19:
				parser.parseAsVector(((elem.getNumWindings() - 1) * elem.getNumWindings() / 2), elem.getXSC());
				break;
			case 20:
				elem.setThermalTimeConst(parser.makeDouble());
				break;
			case 21:
				elem.setNThermal(parser.makeDouble());
				break;
			case 22:
				elem.setMThermal(parser.makeDouble());
				break;
			case 23:
				elem.setFLRise(parser.makeDouble());
				break;
			case 24:
				elem.setHSRise(parser.makeDouble());
				break;
			case 25:
				elem.setPctLoadLoss(parser.makeDouble());
				break;
			case 26:
				elem.setPctNoLoadLoss(parser.makeDouble());
				break;
			case 27:
				elem.setNormMaxHKVA(parser.makeDouble());
				break;
			case 28:
				elem.setEmergMaxHKVA(parser.makeDouble());
				break;
			case 29:
				elem.setSubstation(Util.interpretYesNo(param));
				break;
			case 30:
				elem.getWinding(elem.getActiveWindingIdx()).setMaxTap(parser.makeDouble());
				break;
			case 31:
				elem.getWinding(elem.getActiveWindingIdx()).setMinTap(parser.makeDouble());
				break;
			case 32:
				elem.getWinding(elem.getActiveWindingIdx()).setNumTaps(parser.makeInteger());
				break;
			case 33:
				elem.setSubstationName(param);
				break;
			case 34:
				elem.setPctImag(parser.makeDouble());
				break;
			case 35:
				elem.setPpmFloatFactor(parser.makeDouble() * 1.0e-6);
				break;
			case 36:
				interpretAllRs(param);
				break;
			case 37:
				elem.setXfmrBank(param);
				break;
			case 38:
				elem.fetchXfmrCode(param);
				break;
			default:
				// inherited properties
				classEdit(activeTransfObj, paramPointer - Transformer.NumPropsThisClass);
				break;
			}

			/* Take care of properties that require some additional work */
			switch (paramPointer) {
			case 0:
				// force redefinition of number of conductors and reallocation of matrices
				elem.setNumConds(elem.getNumPhases() + 1);
				break;
			case 6:
				// default all winding kVAs to first winding so latter do not have to be specified
				if (elem.getActiveWindingIdx() == 0) {
					for (int i = 1; i < elem.getNumWindings(); i++)
						elem.getWinding(i).setKVA(elem.getWinding(0).getKVA());

					elem.setNormMaxHKVA(1.1 * elem.getWinding(0).getKVA());  // defaults for new winding rating
					elem.setEmergMaxHKVA(1.5 * elem.getWinding(0).getKVA());
				} else if (elem.getNumWindings() == 2) {
					elem.getWinding(0).setKVA(elem.getWinding(1).getKVA());  // for 2-winding, force both kVAs to be same
				}
				break;
			case 8:
				// update loadLossKW if winding %r changed, using only windings 1 and 2
				elem.setPctLoadLoss( (elem.getWinding(0).getRpu() + elem.getWinding(1).getRpu()) * 100.0 );
				break;
			case 14:
				elem.setNormMaxHKVA(1.1 * elem.getWinding(0).getKVA());  // defaults for new winding rating
				elem.setEmergMaxHKVA(1.5 * elem.getWinding(0).getKVA());
				break;
			case 16:
				elem.setXHLChanged(true);
				break;
			case 17:
				elem.setXHLChanged(true);
				break;
			case 18:
				elem.setXHLChanged(true);
				break;
			case 19:
				for (int i = 0; i < elem.getNumWindings() * elem.getNumWindings() / 2; i++)
					elem.getXSC()[i] = elem.getXSC()[i] * 0.01;  // convert to per unit
				break;
			case 25:  // assume load loss is split evenly between windings 1 and 2
				elem.getWinding(0).setRpu(elem.getPctLoadLoss() / 2.0 / 100.0);
				elem.getWinding(1).setRpu(elem.getWinding(0).getRpu());
				break;
			case 36:
				elem.setPctLoadLoss((elem.getWinding(0).getRpu() + elem.getWinding(1).getRpu()) * 100.0);  // update
			}

			// YPrim invalidation on anything that changes impedance values
			if (paramPointer >= 4 && paramPointer <= 18) {
				elem.setYPrimInvalid(true);
			} else {
				switch (paramPointer) {
				case 25:
				case 26:
				case 34:
				case 35:
				case 36:
					elem.setYPrimInvalid(true);
					break;
				}
			}

			/* Advance to next property on input line */
			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		elem.recalcElementData();

		return 0;
	}

	private void setActiveWinding(int w) {
		TransformerObj elem = activeTransfObj;

		if (w >= 0 && w < elem.getNumWindings()) {
			elem.setActiveWindingIdx(w);
		} else {
			DSS.doSimpleMsg("Wdg parameter invalid for \"" + elem.getName() + "\"", 112);
		}
	}

	/**
	 * Accepts (case insensitive):
	 * 		delta or LL
	 * 		Y, wye, or LN
	 */
	private void interpretConnection(String s) {
		TransformerObj elem = activeTransfObj;
		Winding w = elem.getWinding(elem.getActiveWindingIdx());

		switch (s.toLowerCase().charAt(0)) {
		case 'y':
			w.setConnection(Connection.WYE);
			break;
		case 'w':
			w.setConnection(Connection.WYE);
			break;
		case 'd':
			w.setConnection(Connection.DELTA);
			break;
		case 'l':
			switch (s.toLowerCase().charAt(1)) {
			case 'n':
				w.setConnection(Connection.WYE);
				break;
			case 'l':
				w.setConnection(Connection.DELTA);
				break;
			}
			break;
		}

		elem.setYOrder(elem.getNumConds() * elem.getNumTerms());
		elem.setYPrimInvalid(true);
	}

	/**
	 * Routine expecting all winding connections expressed in one array of strings.
	 */
	private void interpretAllConns(String s) {
		String s2;
		TransformerObj elem = activeTransfObj;

		DSS.auxParser.setCmdString(s);  // load up parser

		/* Loop for no more than the expected number of windings */
		for (int i = 0; i < elem.getNumWindings(); i++) {
			elem.setActiveWindingIdx(i);
			DSS.auxParser.getNextParam();  // ignore any parameter name not expecting any
			s2 = DSS.auxParser.makeString();
			if (s2.length() > 0)
				interpretConnection(s2);
		}
	}

	/**
	 * Routine expecting all winding bus connections expressed in one array of strings.
	 */
	private void interpretAllBuses(String s) {
		String busName;
		TransformerObj elem = activeTransfObj;

		DSS.auxParser.setCmdString(s);  // load up parser

		/* Loop for no more than the expected number of windings; Ignore omitted values */
		for (int i = 0; i < elem.getNumWindings(); i++) {
			elem.setActiveWindingIdx(i);
			DSS.auxParser.getNextParam();  // ignore any parameter name  not expecting any
			busName = DSS.auxParser.makeString();
			if (busName.length() > 0)
				elem.setBus(elem.getActiveWindingIdx(), busName);
		}
	}

	/**
	 * Routine expecting all winding kV ratings expressed in one array of strings.
	 */
	private void interpretAllkVRatings(String s) {
		String dataStr;
		TransformerObj elem = activeTransfObj;

		DSS.auxParser.setCmdString(s);  // load up parser

		/* Loop for no more than the expected number of windings; ignore omitted values */
		for (int i = 0; i < elem.getNumWindings(); i++) {
			elem.setActiveWindingIdx(i);
			DSS.auxParser.getNextParam();  // ignore any parameter name  not expecting any
			dataStr = DSS.auxParser.makeString();
			if (dataStr.length() > 0)
				elem.getWinding(elem.getActiveWindingIdx()).setKVLL(DSS.auxParser.makeDouble());
		}
	}

	/**
	 * Routine expecting all winding ratings expressed in one array of strings.
	 */
	private void interpretAllkVARatings(String s) {
		String dataStr;
		TransformerObj elem = activeTransfObj;

		DSS.auxParser.setCmdString(s);  // load up parser

		/* Loop for no more than the expected number of windings; ignore omitted values */
		for (int i = 0; i < elem.getNumWindings(); i++) {
			elem.setActiveWindingIdx(i);
			DSS.auxParser.getNextParam();  // ignore any parameter name not expecting any
			dataStr = DSS.auxParser.makeString();
			if (dataStr.length() > 0)
				elem.getWinding(elem.getActiveWindingIdx()).setKVA(DSS.auxParser.makeDouble());
		}
	}

	/**
	 * Routine expecting all winding ratings expressed in one array of strings.
	 */
	private void interpretAllRs(String s) {
		String dataStr;
		TransformerObj elem = activeTransfObj;

		DSS.auxParser.setCmdString(s);  // load up parser

		/* Loop for no more than the expected number of windings; ignore omitted values */
		for (int i = 0; i < elem.getNumWindings(); i++) {
			elem.setActiveWindingIdx(i);
			DSS.auxParser.getNextParam();  // ignore any parameter name not expecting any
			dataStr = DSS.auxParser.makeString();
			if (dataStr.length() > 0)
				elem.getWinding(elem.getActiveWindingIdx()).setRpu(DSS.auxParser.makeDouble() * 0.01);
		}
	}

	/**
	 * Routine expecting all winding taps expressed in one array of strings.
	 */
	private void interpretAllTaps(String s) {
		String dataStr;
		TransformerObj elem = activeTransfObj;

		DSS.auxParser.setCmdString(s);  // load up parser

		/* Loop for no more than the expected number of windings; ignore omitted values */
		for (int i = 0; i < elem.getNumWindings(); i++) {
			elem.setActiveWindingIdx(i);
			DSS.auxParser.getNextParam();  // ignore any parameter name, not expecting any
			dataStr = DSS.auxParser.makeString();
			if (dataStr.length() > 0)
				elem.getWinding(elem.getActiveWindingIdx()).setPuTap(DSS.auxParser.makeDouble());
		}
	}

	@Override
	protected int makeLike(String transfName) {
		int i, success = 0;
		Winding w;

		/* See if we can find this Transf name in the present collection */
		TransformerObj other = (TransformerObj) find(transfName);

		if (other != null) {
			TransformerObj elem = activeTransfObj;

			elem.setNumPhases(other.getNumPhases());
			elem.setNumWindings(other.getNumWindings());
			elem.setNumConds(elem.getNumPhases() + 1);  // forces reallocation of terminals and conductors

			elem.setYOrder(elem.getNumConds() * elem.getNumTerms());
			elem.setYPrimInvalid(true);

			for (i = 0; i < elem.getNumWindings(); i++) {
				w = elem.getWinding(i);
				w.setConnection(other.getWinding(i).getConnection());
				w.setKVLL(other.getWinding(i).getKVLL());
				w.setVbase(other.getWinding(i).getVbase());
				w.setKVA(other.getWinding(i).getKVA());
				w.setPuTap(other.getWinding(i).getPuTap());
				w.setRpu(other.getWinding(i).getRpu());
				w.setRNeut(other.getWinding(i).getRNeut());
				w.setXNeut(other.getWinding(i).getXNeut());
				// copy the taps
				w.setTapIncrement(other.getWinding(i).getTapIncrement());
				w.setMinTap(other.getWinding(i).getMinTap());
				w.setMaxTap(other.getWinding(i).getMaxTap());
				w.setNumTaps(other.getWinding(i).getNumTaps());
			}

			elem.setTermRef();

			elem.setXHL(other.getXHL());
			elem.setXHT(other.getXHT());
			elem.setXLT(other.getXLT());

			for (i = 0; i < (elem.getNumWindings() * (elem.getNumWindings() - 1) / 2); i++)
				elem.getXSC()[i] = other.getXSC(i);

			elem.getZB().copyFrom(other.getZB());
			elem.getY_1Volt().copyFrom(other.getY_1Volt());
			elem.getY_Term().copyFrom(other.getY_Term());
			elem.getY_1Volt_NL().copyFrom(other.getY_1Volt_NL());
			elem.getY_Term_NL().copyFrom(other.getY_Term_NL());

			elem.setThermalTimeConst(other.getThermalTimeConst());
			elem.setNThermal(other.getNThermal());
			elem.setMThermal(other.getMThermal());
			elem.setFLRise(other.getFLRise());
			elem.setHSRise(other.getHSRise());
			elem.setPctLoadLoss(other.getPctLoadLoss());
			elem.setPctNoLoadLoss(other.getPctNoLoadLoss());
			elem.setNormMaxHKVA(other.getNormMaxHKVA());
			elem.setEmergMaxHKVA(other.getEmergMaxHKVA());

			elem.setXfmrBank(other.getXfmrBank());
			elem.setXfmrCode(other.getXfmrCode());

			classMakeLike(other);

			for (i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));
			success = 1;
		} else {
			DSS.doSimpleMsg("Error in Transf makeLike: \"" + transfName + "\" not found.", 113);
		}

		return success;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement Transformer.init()", -1);
		return 0;
	}

}
