package com.epri.dss.delivery.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.delivery.Transformer;
import com.epri.dss.delivery.TransformerObj;
import com.epri.dss.delivery.Winding;
import com.epri.dss.general.XfmrCode;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class TransformerImpl extends PDClassImpl implements Transformer {

	private static TransformerObj ActiveTransfObj;

	private static XfmrCode XfmrCodeClass = null;

	public TransformerImpl() {
		super();
		this.Class_Name   = "Transformer";
		this.DSSClassType = this.DSSClassType + DSSClassDefs.XFMR_ELEMENT; // override PDElement   (kept in both actually)

		this.ActiveElement = -1;

		defineProperties();

		/* Make space for transformer property list */
		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);  // Allow property list abbreviations
	}

	protected void defineProperties() {

		NumProperties = Transformer.NumPropsThisClass;
		countProperties();   // Get inherited property count
		allocatePropertyArrays();

		/* Define Property names */

		PropertyName[0] = "phases";
		PropertyName[1] = "windings";

		// Winding Definition
		PropertyName[2] = "wdg";
		PropertyName[3] = "bus";
		PropertyName[4] = "conn";
		PropertyName[5] = "kV"; // FOR 2-and 3- always kVLL ELSE actual winding KV
		PropertyName[6] = "kVA";
		PropertyName[7] = "tap";
		PropertyName[8] = "%R";
		PropertyName[9] = "Rneut";
		PropertyName[10] = "Xneut";

		// General Data
		PropertyName[11] = "buses";
		PropertyName[12] = "conns";
		PropertyName[13] = "kVs";
		PropertyName[14] = "kVAs";
		PropertyName[15] = "taps";
		PropertyName[16] = "Xhl";
		PropertyName[17] = "Xht";
		PropertyName[18] = "Xlt";
		PropertyName[19] = "Xscarray";  // x12 13 14... 23 24.. 34 ..
		PropertyName[20] = "thermal";
		PropertyName[21] = "n";
		PropertyName[22] = "m";
		PropertyName[23] = "flrise";
		PropertyName[24] = "hsrise";
		PropertyName[25] = "%loadloss";
		PropertyName[26] = "%noloadloss";
		PropertyName[27] = "normhkVA";
		PropertyName[28] = "emerghkVA";
		PropertyName[29] = "sub";  // =y/n
		PropertyName[30] = "MaxTap";
		PropertyName[31] = "MinTap";
		PropertyName[32] = "NumTaps";
		PropertyName[33] = "subname";
		PropertyName[34] = "%imag";
		PropertyName[35] = "ppm_antifloat";
		PropertyName[36] = "%Rs";

		PropertyName[37] = "bank";
		PropertyName[38] = "XfmrCode";



		// define Property help values
		PropertyHelp[0] = "Number of phases this transformer. Default is 3.";
		PropertyHelp[1] = "Number of windings, this transformers. (Also is the number of terminals) "+
					"Default is 2.";
		// Winding Definition
		PropertyHelp[2] = "Set this = to the number of the winding you wish to define.  Then set "+
					"the values for this winding.  Repeat for each winding.  Alternatively, use "+
					"the array collections (buses, kvas, etc.) to define the windings.  Note: "+
					"reactances are BETWEEN pairs of windings; they are not the property of a single winding.";
		PropertyHelp[3] = "Bus connection spec for this winding.";
		PropertyHelp[4] = "Connection of this winding. Default is \"wye\" with the neutral solidly grounded.";
		PropertyHelp[5] = "For 2-or 3-phase, enter phase-phase kV rating.  Otherwise, kV rating of the actual winding";
		PropertyHelp[6] = "Base kVA rating of the winding. Side effect: forces change of max normal and emerg kva ratings." +
						"If 2-winding transformer, forces other winding to same value. " +
						"When winding 1 is defined, all other windings are defaulted to the same rating " +
						"and the first two winding resistances are defaulted to the %loadloss value.";
		PropertyHelp[7] = "Per unit tap that this winding is on.";
		PropertyHelp[8] = "Percent resistance this winding.  (half of total for a 2-winding).";
		PropertyHelp[9] = "Default = -1. Neutral resistance of wye (star)-connected winding in actual ohms." +
							"If entered as a negative value, the neutral is assumed to be open, or floating.";
		PropertyHelp[10] = "Neutral reactance of wye(star)-connected winding in actual ohms.  May be + or -.";

		// General Data
		PropertyHelp[11] = "Use this to specify all the bus connections at once using an array. Example:"+DSSGlobals.CRLF+DSSGlobals.CRLF+
							"New Transformer.T1 buses=\"Hibus, lowbus\"";
		PropertyHelp[12] = "Use this to specify all the Winding connections at once using an array. Example:"+DSSGlobals.CRLF+DSSGlobals.CRLF+
							"New Transformer.T1 buses=\"Hibus, lowbus\" "+
							"~ conns=(delta, wye)";
		PropertyHelp[13] = "Use this to specify the kV ratings of all windings at once using an array. Example:"+DSSGlobals.CRLF+DSSGlobals.CRLF+
							"New Transformer.T1 buses=\"Hibus, lowbus\" "+DSSGlobals.CRLF+
							"~ conns=(delta, wye)"+DSSGlobals.CRLF+
							"~ kvs=(115, 12.47)"+DSSGlobals.CRLF+DSSGlobals.CRLF+
							"See kV= property for voltage rules.";
		PropertyHelp[14] = "Use this to specify the kVA ratings of all windings at once using an array.";
		PropertyHelp[15] = "Use this to specify the p.u. tap of all windings at once using an array.";
		PropertyHelp[16] = "Use this to specify the percent reactance, H-L (winding 1 to winding 2).  Use "+
							"for 2- or 3-winding transformers. On the kva base of winding 1.";
		PropertyHelp[17] = "Use this to specify the percent reactance, H-T (winding 1 to winding 3).  Use "+
							"for 3-winding transformers only. On the kVA base of winding 1.";
		PropertyHelp[18] = "Use this to specify the percent reactance, L-T (winding 2 to winding 3).  Use "+
							"for 3-winding transformers only. On the kVA base of winding 1.";
		PropertyHelp[19] = "Use this to specify the percent reactance between all pairs of windings as an array. "+
							"All values are on the kVA base of winding 1.  The order of the values is as follows:"+DSSGlobals.CRLF+DSSGlobals.CRLF+
							"(x12 13 14... 23 24.. 34 ..)  "+DSSGlobals.CRLF+DSSGlobals.CRLF+
							"There will be n(n-1)/2 values, where n=number of windings.";
		PropertyHelp[20] = "Thermal time constant of the transformer in hours.  Typically about 2.";
		PropertyHelp[21] = "n Exponent for thermal properties in IEEE C57.  Typically 0.8.";
		PropertyHelp[22] = "m Exponent for thermal properties in IEEE C57.  Typically 0.9 - 1.0";
		PropertyHelp[23] = "Temperature rise, deg C, for full load.  Default is 65.";
		PropertyHelp[24] = "Hot spot temperature rise, deg C.  Default is 15.";
		PropertyHelp[25] = "Percent load loss at full load. The %R of the High and Low windings (1 and 2) are adjusted to agree at rated kVA loading.";
		PropertyHelp[26] = "Percent no load losses at rated excitatation voltage. Default is 0. Converts to a resistance in parallel with the magnetizing impedance in each winding.";
		PropertyHelp[27] = "Normal maximum kVA rating of H winding (winding 1).  Usually 100% - 110% of"+
							"maximum nameplate rating, depending on load shape. Defaults to 110% of kVA rating of Winding 1.";
		PropertyHelp[28] = "Emergency (contingency)  kVA rating of H winding (winding 1).  Usually 140% - 150% of"+
							"maximum nameplate rating, depending on load shape. Defaults to 150% of kVA rating of Winding 1.";
		PropertyHelp[29] = "={Yes|No}  Designates whether this transformer is to be considered a substation."+
							"Default is No.";  // =y/n

		PropertyHelp[30] = "Max per unit tap for the active winding.  Default is 1.10";
		PropertyHelp[31] = "Min per unit tap for the active winding.  Default is 0.90";
		PropertyHelp[32] = "Total number of taps between min and max tap.  Default is 32.";
		PropertyHelp[33] = "Substation Name. Optional. Default is null. If specified, printed on plots";
		PropertyHelp[34] = "Percent magnetizing current. Default=0.0. Magnetizing branch is in parallel with windings in each phase. Also, see \"ppm_antifloat\".";
		PropertyHelp[35] = "Default=1 ppm.  Parts per million of transformer winding VA rating connected to ground to protect against accidentally floating a winding without a reference. " +
							"If positive then the effect is adding a very large reactance to ground.  If negative, then a capacitor.";
		PropertyHelp[36] = "Use this property to specify all the winding %resistances using an array. Example:"+DSSGlobals.CRLF+DSSGlobals.CRLF+
							"New Transformer.T1 buses=\"Hibus, lowbus\" "+
							"~ %Rs=(0.2  0.3)";
		PropertyHelp[37] = "Name of the bank this transformer is part of, for CIM, MultiSpeak, and other interfaces.";
		PropertyHelp[38] = "Name of a library entry for transformer properties. The named XfmrCode must already be defined.";

		ActiveProperty = Transformer.NumPropsThisClass - 1;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new TransformerObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	/**
	 * A Transf defaults to 3-phases, 2-windings (both wye).
	 */
	@Override
	public int edit() {
		// continue parsing cmdline presently in Parser
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		/* Make this object the active circuit element */
		setActiveTransfObj((TransformerObj) ElementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActiveTransfObj());  // use property to set this value

		int Result = 0;

		TransformerObj at = getActiveTransfObj();

		at.setXHLChanged(false);
		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer < NumProperties))
				at.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"Transformer." + at.getName() + "\"", 110);
			case 0:
				at.setNPhases(parser.makeInteger());
			case 1:
				at.setNumWindings(parser.makeInteger());  // Reallocate stuff if bigger
			case 2:
				at.setActiveWinding(parser.makeInteger());
			case 3:
				at.setBus(at.getActiveWinding(), Param);
			case 4:
				interpretConnection(Param);
			case 5:
				at.getWinding()[at.getActiveWinding()].setKvll(parser.makeDouble());
			case 6:
				at.getWinding()[at.getActiveWinding()].setKva(parser.makeDouble());
			case 7:
				at.getWinding()[at.getActiveWinding()].setPuTap(parser.makeDouble());
			case 8:
				at.getWinding()[at.getActiveWinding()].setRpu(parser.makeDouble() * 0.01);  // %R
			case 9:
				at.getWinding()[at.getActiveWinding()].setRneut(parser.makeDouble());
			case 10:
				at.getWinding()[at.getActiveWinding()].setXneut(parser.makeDouble());
			case 11:
				interpretAllBuses(Param);
			case 12:
				interpretAllConns(Param);
			case 13:
				interpretAllkVRatings(Param);
			case 14:
				interpretAllkVARatings(Param);
			case 15:
				interpretAllTaps(Param);
			case 16:
				at.setXHL(parser.makeDouble() * 0.01);
			case 17:
				at.setXHT(parser.makeDouble() * 0.01);
			case 18:
				at.setXLT(parser.makeDouble() * 0.01);
			case 19:
				parser.parseAsVector(((at.getNumWindings() - 1) * at.getNumWindings() / 2), at.getXSC());
			case 20:
				at.setThermalTimeConst(parser.makeDouble());
			case 21:
				at.setN_thermal(parser.makeDouble());
			case 22:
				at.setM_thermal(parser.makeDouble());
			case 23:
				at.setFLrise(parser.makeDouble());
			case 24:
				at.setHSrise(parser.makeDouble());
			case 25:
				at.setPctLoadLoss(parser.makeDouble());
			case 26:
				at.setPctNoLoadLoss(parser.makeDouble());
			case 27:
				at.setNormMaxHKVA(parser.makeDouble());
			case 28:
				at.setEmergMaxHKVA(parser.makeDouble());
			case 29:
				at.setIsSubstation(Utilities.interpretYesNo(Param));
			case 30:
				at.getWinding()[at.getActiveWinding()].setMaxTap(parser.makeDouble());
			case 31:
				at.getWinding()[at.getActiveWinding()].setMinTap(parser.makeDouble());
			case 32:
				at.getWinding()[at.getActiveWinding()].setNumTaps(parser.makeInteger());
			case 33:
				at.setSubstationName(Param);
			case 34:
				at.setPctImag(parser.makeDouble());
			case 35:
				at.setPpm_FloatFactor(parser.makeDouble() * 1.0e-6);
			case 36:
				interpretAllRs(Param);
			case 37:
				at.setXfmrBank(Param);
			case 38:
				at.fetchXfmrCode(Param);
			default:
				// Inherited properties
				classEdit(getActiveTransfObj(), ParamPointer - Transformer.NumPropsThisClass);
			}

			/* Take care of properties that require some additional work, */
			switch (ParamPointer) {
			case 0:
				// Force redefinition of number of conductors and reallocation of matrices
				at.setNConds(at.getNPhases() + 1);
			case 6:
				// default all winding kvas to first winding so latter do not have to be specified
				if (at.getActiveWinding() == 0) {  // TODO Check zero based indexing
					for (int i = 1; i < at.getNumWindings(); i++)
						at.getWinding()[i].setKva(at.getWinding()[0].getKva());
					at.setNormMaxHKVA(1.1 * at.getWinding()[0].getKva());    // Defaults for new winding rating.
					at.setEmergMaxHKVA(1.5 * at.getWinding()[0].getKva());
					at.getWinding()[0].setRpu(at.getPctLoadLoss() / 2.0 / 100.0);
					at.getWinding()[1].setRpu(at.getWinding()[0].getRpu());
				} else if (at.getNumWindings() == 2) {
					at.getWinding()[0].setKva(at.getWinding()[1].getKva());  // For 2-winding, force both kVAs to be same
				}
			case 8:
				// Update LoadLosskW if winding %r changed. Using only windings 1 and 2
				at.setPctLoadLoss( (at.getWinding()[0].getRpu() + at.getWinding()[1].getRpu()) * 100.0 );
			case 14:
				at.setNormMaxHKVA(1.1 * at.getWinding()[0].getKva());    // Defaults for new winding rating.
				at.setEmergMaxHKVA(1.5 * at.getWinding()[0].getKva());
			case 16:
				at.setXHLChanged(true);
			case 17:
				at.setXHLChanged(true);
			case 18:
				at.setXHLChanged(true);
			case 19:
				for (int i = 0; i < ((at.getNumWindings() - 1) * at.getNumWindings() / 2); i++)
					at.getXSC()[i] = at.getXsc(i) * 0.01;  // Convert to per unit   TODO Check translation
			case 25:  // Assume load loss is split evenly  between windings 1 and 2
				at.getWinding()[0].setRpu(at.getPctLoadLoss() / 2.0 / 100.0);
				at.getWinding()[1].setRpu(at.getWinding()[0].getRpu());
			}

			// YPrim invalidation on anything that changes impedance values
			if ((ParamPointer >= 4) && (ParamPointer <= 18)) {
				at.setYprimInvalid(true);
			} else {
				switch (ParamPointer) {
				case 25:
					at.setYprimInvalid(true);
				case 26:
					at.setYprimInvalid(true);
				case 34:
					at.setYprimInvalid(true);
				case 35:
					at.setYprimInvalid(true);
				}
			}

			/* Advance to next property on input line */
			ParamName = parser.getNextParam();
			Param     = parser.makeString();
		}

		at.recalcElementData();

		return Result;
	}

	private void setActiveWinding(int w) {
		TransformerObj at = getActiveTransfObj();

		if ((w >= 0) && (w < at.getNumWindings())) {
			at.setActiveWinding(w);
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Wdg parameter invalid for \"" + at.getName() + "\"", 112);
		}
	}

	/**
	 * Accepts:
	 * 		delta or LL           (Case insensitive)
	 * 		Y, wye, or LN
	 */
	private void interpretConnection(String S) {
		TransformerObj at = getActiveTransfObj();
		Winding aw = at.getWinding()[at.getActiveWinding()];

		switch (S.toLowerCase().charAt(0)) {
		case 'y':
			aw.setConnection(0);  /* Wye */
		case 'w':
			aw.setConnection(0);  /* Wye */
		case 'd':
			aw.setConnection(1);  /* Delta or line-Line */
		case 'l':
			switch (S.toLowerCase().charAt(1)) {
			case 'n':
				aw.setConnection(0);
			case 'l':
				aw.setConnection(1);
			}
		}

		at.setYorder(at.getNConds() * at.getNTerms());
		at.setYprimInvalid(true);
	}

	/**
	 * Routine expecting all winding connections expressed in one array of strings.
	 */
	private void interpretAllConns(String S) {
		@SuppressWarnings("unused")
		String S1;
		String S2;

		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getAuxParser().setCmdString(S);  // Load up Parser

		/* Loop for no more than the expected number of windings */
		TransformerObj at = getActiveTransfObj();
		for (int i = 0; i < at.getNumWindings(); i++) {
			at.setActiveWinding(i);
			S1 = Globals.getAuxParser().getNextParam();  // ignore any parameter name not expecting any
			S2 = Globals.getAuxParser().makeString();
			if (S2.length() > 0)
				interpretConnection(S2);
		}
	}

	/**
	 * Routine expecting all winding bus connections expressed in one array of strings.
	 */
	private void interpretAllBuses(String S) {
		String BusNam;

		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getAuxParser().setCmdString(S);  // Load up Parser

		/* Loop for no more than the expected number of windings; Ignore omitted values */
		TransformerObj at = getActiveTransfObj();
		for (int i = 0; i < at.getNumWindings(); i++) {
			at.setActiveWinding(i);
			Globals.getAuxParser().getNextParam();  // ignore any parameter name  not expecting any
			BusNam = Globals.getAuxParser().makeString();
			if (BusNam.length() > 0)
				at.setBus(at.getActiveWinding(), BusNam);
		}
	}

	/**
	 * Routine expecting all winding kV ratings expressed in one array of strings.
	 */
	private void interpretAllkVRatings(String S) {
		String DataStr;

		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getAuxParser().setCmdString(S);  // Load up Parser

		/* Loop for no more than the expected number of windings; Ignore omitted values */
		TransformerObj at = getActiveTransfObj();
		for (int i = 0; i < at.getNumWindings(); i++) {
			at.setActiveWinding(i);
			Globals.getAuxParser().getNextParam();  // ignore any parameter name  not expecting any
			DataStr = Globals.getAuxParser().makeString();
			if (DataStr.length() > 0)
				at.getWinding()[at.getActiveWinding()].setKvll(Globals.getAuxParser().makeDouble());
		}
	}

	/**
	 * Routine expecting all winding ratings expressed in one array of strings.
	 */
	private void interpretAllkVARatings(String S) {
		String DataStr;

		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getAuxParser().setCmdString(S);  // Load up Parser

		/* Loop for no more than the expected number of windings; Ignore omitted values */
		TransformerObj at = getActiveTransfObj();
		for (int i = 0; i < at.getNumWindings(); i++) {
			at.setActiveWinding(i);
			Globals.getAuxParser().getNextParam();  // ignore any parameter name  not expecting any
			DataStr = Globals.getAuxParser().makeString();
			if (DataStr.length() > 0)
				at.getWinding()[at.getActiveWinding()].setKva(Globals.getAuxParser().makeDouble());
		}
	}

	/**
	 * Routine expecting all winding ratings expressed in one array of strings.
	 */
	private void interpretAllRs(String S) {
		String DataStr;

		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getAuxParser().setCmdString(S);  // Load up Parser

		/* Loop for no more than the expected number of windings; Ignore omitted values */
		TransformerObj at = getActiveTransfObj();
		for (int i = 0; i < at.getNumWindings(); i++) {
			at.setActiveWinding(i);
			Globals.getAuxParser().getNextParam();  // ignore any parameter name  not expecting any
			DataStr = Globals.getAuxParser().makeString();
			if (DataStr.length() > 0)
				at.getWinding()[at.getActiveWinding()].setRpu(Globals.getAuxParser().makeDouble() * 0.01);
		}
	}

	/**
	 * Routine expecting all winding taps expressed in one array of strings.
	 */
	private void interpretAllTaps(String S) {
		String DataStr;

		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getAuxParser().setCmdString(S);  // Load up Parser

		/* Loop for no more than the expected number of windings; Ignore omitted values */
		TransformerObj at = getActiveTransfObj();
		for (int i = 0; i < at.getNumWindings(); i++) {
			at.setActiveWinding(i);
			Globals.getAuxParser().getNextParam();  // ignore any parameter name,  not expecting any
			DataStr = Globals.getAuxParser().makeString();
			if (DataStr.length() > 0)
				at.getWinding()[at.getActiveWinding()].setPuTap(Globals.getAuxParser().makeDouble());
		}
	}

	@Override
	protected int makeLike(String TransfName) {
		int i;

		int Result = 0;
		/* See if we can find this Transf name in the present collection */
		TransformerObj OtherTransf = (TransformerObj) find(TransfName);
		if (OtherTransf != null) {
			TransformerObj at = getActiveTransfObj();

			at.setNPhases(OtherTransf.getNPhases());
			at.setNumWindings(OtherTransf.getNumWindings());
			at.setNConds(at.getNPhases() + 1); // forces reallocation of terminals and conductors

			at.setYorder(at.getNConds() * at.getNTerms());
			at.setYprimInvalid(true);

			for (i = 0; i < at.getNumWindings(); i++) {
				Winding w = at.getWinding()[i];
				w.setConnection(OtherTransf.getWinding()[i].getConnection());
				w.setKvll(OtherTransf.getWinding()[i].getKvll());
				w.setVBase(OtherTransf.getWinding()[i].getVBase());
				w.setKva(OtherTransf.getWinding()[i].getKva());
				w.setPuTap(OtherTransf.getWinding()[i].getPuTap());
				w.setRpu(OtherTransf.getWinding()[i].getRpu());
				w.setRneut(OtherTransf.getWinding()[i].getRneut());
				w.setXneut(OtherTransf.getWinding()[i].getXneut());
				// copy the taps
				w.setTapIncrement(OtherTransf.getWinding()[i].getTapIncrement());
				w.setMinTap(OtherTransf.getWinding()[i].getMinTap());
				w.setMaxTap(OtherTransf.getWinding()[i].getMaxTap());
				w.setNumTaps(OtherTransf.getWinding()[i].getNumTaps());
			}

			at.setTermRef();

			at.setXHL(OtherTransf.getXHL());
			at.setXHT(OtherTransf.getXHT());
			at.setXLT(OtherTransf.getXLT());

			for (i = 0; i < (at.getNumWindings() * (at.getNumWindings() - 1) / 2); i++)
				at.getXSC()[i] = OtherTransf.getXsc(i);

			at.getZB().copyFrom(OtherTransf.getZB());
			at.getY_1Volt().copyFrom(OtherTransf.getY_1Volt());
			at.getY_Term().copyFrom(OtherTransf.getY_Term());
			at.getY_1Volt_NL().copyFrom(OtherTransf.getY_1Volt_NL());
			at.getY_Term_NL().copyFrom(OtherTransf.getY_Term_NL());

			at.setThermalTimeConst(OtherTransf.getThermalTimeConst());
			at.setN_thermal(OtherTransf.getN_thermal());
			at.setM_thermal(OtherTransf.getM_thermal());
			at.setFLrise(OtherTransf.getFLrise());
			at.setHSrise(OtherTransf.getHSrise());
			at.setPctLoadLoss(OtherTransf.getPctLoadLoss());
			at.setPctNoLoadLoss(OtherTransf.getPctNoLoadLoss());
			at.setNormMaxHKVA(OtherTransf.getNormMaxHKVA());
			at.setEmergMaxHKVA(OtherTransf.getEmergMaxHKVA());

			at.setXfmrBank(OtherTransf.getXfmrBank());
			at.setXfmrCode(OtherTransf.getXfmrCode());

			classMakeLike(OtherTransf);

			for (i = 0; i < at.getParentClass().getNumProperties(); i++)
				at.setPropertyValue(i, OtherTransf.getPropertyValue(i));
			Result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Transf MakeLike: \"" + TransfName + "\" Not Found.", 113);
		}

		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement Transformer.init()", -1);
		return 0;
	}

	public static TransformerObj getActiveTransfObj() {
		return ActiveTransfObj;
	}

	public static void setActiveTransfObj(TransformerObj activeTransfObj) {
		ActiveTransfObj = activeTransfObj;
	}

	public static XfmrCode getXfmrCodeClass() {
		return XfmrCodeClass;
	}

	public static void setXfmrCodeClass(XfmrCode xfmrCodeClass) {
		XfmrCodeClass = xfmrCodeClass;
	}

}
