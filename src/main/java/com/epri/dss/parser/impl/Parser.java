package com.epri.dss.parser.impl;

import com.epri.dss.common.impl.DSSForms;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.parser.RPNCalc;

public class Parser {

	private static final char CommentChar = '!';

	private String CmdBuffer;
	private int Position;
	private String ParameterBuffer;
	private String TokenBuffer;
	private String DelimChars;
	private String WhiteSpaceChars;
	private String BeginQuoteChars;
	private String EndQuoteChars;
	private char LastDelimiter;
	private char MatrixRowTerminator;
	private boolean AutoIncrement;
	private boolean ConvertError;
	private boolean IsQuotedString;
	private RPNCalc RPNCalculator;

	private Parser() {
		super();

		this.DelimChars          = ",=";
		this.WhiteSpaceChars     = " " + "\t";   // blank + tab
		this.BeginQuoteChars     = "(\"'[{";
		this.EndQuoteChars       = ")\"']}";
		this.Position            = 0;
		this.MatrixRowTerminator = '|';
		this.AutoIncrement       = false;
		this.RPNCalculator       = new RPNCalcImpl();
	}

	private static class ParserHolder {
		public static final Parser INSTANCE = new Parser();
	}

	private static class AuxParserHolder {
		public static final Parser INSTANCE = new Parser();
	}

	public static Parser getInstance() {
		return ParserHolder.INSTANCE;
	}

	public static Parser getAuxInstance() {
		return AuxParserHolder.INSTANCE;
	}

	private int processRPNCommand(String TokenBuffer, RPNCalc RPN) throws ParserProblem {
		double Number = 0;
		int Result = 0;  // Error Code on conversion error

		/* First Try to make a valid number. If that fails, check for RPN command */
		try {
			Number = Double.valueOf(TokenBuffer);
			Result = 1;
		} catch (NumberFormatException e) {
			Result = 0;
		}

		if (Result == 0) {
			RPN.setX(Number);  // Enters number in X register
		} else {  /* Check for RPN command. */
			Result = 0; // reset error return
			String S = TokenBuffer.toLowerCase();

			if (S.equals("+")) {
				RPN.add();
			} else if (S.equals("-")) {
				RPN.subtract();
			} else if (S.equals("*")) {
				RPN.multiply();
			} else if (S.equals("/")) {
				RPN.divide();
			} else if (S.equals("sqrt")) {
				RPN.sqrt();
			} else if (S.equals("sqr")) {
				RPN.square();
			} else if (S.equals("^")) {
				RPN.yToTheXPower();
			} else if (S.equals("sin")) {
				RPN.sinDeg();
			} else if (S.equals("cos")) {
				RPN.cosDeg();
			} else if (S.equals("tan")) {
				RPN.tanDeg();
			} else if (S.equals("asin")) {
				RPN.aSinDeg();
			} else if (S.equals("acos")) {
				RPN.aCosDeg();
			} else if (S.equals("atan")) {
				RPN.aTanDeg();
			} else if (S.equals("atan2")) {
				RPN.aTan2Deg();
			} else if (S.equals("swap")) {
				RPN.swapXY();
			} else if (S.equals("rollup")) {
				RPN.rollUp();
			} else if (S.equals("rolldn")) {
				RPN.rollDn();
			} else if (S.equals("ln")) {
				RPN.natLog();
			} else if (S.equals("pi")) {
				RPN.enterPi();
			} else if (S.equals("log10")) {
				RPN.tenLog();
			} else if (S.equals("exp")) {
				RPN.eToTheX();
			} else if (S.equals("inv")) {
				RPN.inv();
			} else {
				Result = 1;  // error
				throw new ParserProblem("Invalid inline math entry: \""+TokenBuffer+"\"");
			}
		}
		return Result;
	}

	public void setCmdString(String Value) {
		CmdBuffer = Value + " ";  // add some white space at end to get last param
		Position  = 0;
		skipWhiteSpace(CmdBuffer, Position);   // position at first non whitespace character
	}

	/**
	 * Resets delimiters to default.
	 */
	public void resetDelims() {
		DelimChars          = ",=";
		WhiteSpaceChars     = " " + "\t";   // blank + tab
		MatrixRowTerminator = '|';
		BeginQuoteChars     = "(\"'[{";
		EndQuoteChars       = ")\"']}";
	}

	private boolean isWhiteSpace(char ch) {
		for (int i = 0; i < WhiteSpaceChars.length(); i++)
			if (ch == WhiteSpaceChars.charAt(i))
				return true;
		return false;
	}

	private boolean isDelimiter(String LineBuffer, int LinePos) {
		int i;
		char ch;

		boolean Result = false;

		if (isCommentChar(LineBuffer, LinePos)) {
			Result = true;
			LastDelimiter = CommentChar;
			return Result;
		}

		ch = LineBuffer.charAt(LinePos);

		for (i = 0; i < DelimChars.length(); i++) {
			if (ch == DelimChars.charAt(i)) {
				Result = true;
				LastDelimiter = ch;
				return Result;
			}
		}

		for (i = 0; i < WhiteSpaceChars.length(); i++) {
			if (ch == WhiteSpaceChars.charAt(i)) {
				Result = true;
				LastDelimiter = ' ';  // to indicate stopped on white space
				return Result;
			}
		}
		return Result;
	}

	private boolean isDelimChar(char ch) {
		for (int i = 0; i < DelimChars.length(); i++)
			if (ch == DelimChars.charAt(i))
				return true;
		return false;
	}

	private void skipWhiteSpace(String LineBuffer, int LinePos) {
		while ((LinePos < LineBuffer.length()) && isWhiteSpace(LineBuffer.charAt(LinePos)))
			LinePos += 1;
	}


	private int TokenStart;
	private int CmdBufLength;
	private int QuoteIndex;  // value of quote character found
	private String LineBuffer;
	private int LinePos;
	private String Result = "";  // if it doesn't find anything, return null string

	private void _parseToEndChar(char EndChar) {
		LinePos += 1;
		TokenStart = LinePos;
		while ((LinePos < CmdBufLength) && (LineBuffer.charAt(LinePos) != EndChar))
			LinePos += 1;

		Result = LineBuffer.substring(TokenStart, LinePos - TokenStart);
		if (LinePos < CmdBufLength)
			LinePos += 1;  // Increment past endchar
	}

	private void _parseToEndQuote() {
		_parseToEndChar(EndQuoteChars.charAt(QuoteIndex));
		IsQuotedString = true;
	}

	private boolean _isBeginQuote(char ch) {
		QuoteIndex = BeginQuoteChars.indexOf(ch);
		return (QuoteIndex >= 0);
	}

	private String getToken(String lineBuffer, int linePos) {

		CmdBufLength = LineBuffer.length();
		if (LinePos <= CmdBufLength) {

			/* Handle Quotes and Parentheses around tokens */
			IsQuotedString= false;
			if (_isBeginQuote(LineBuffer.charAt(LinePos))) {
				_parseToEndQuote();
			} else {  /* Copy to next delimiter or whitespace */
				TokenStart = LinePos;
				while ((LinePos < CmdBufLength) && (!isDelimiter(LineBuffer, LinePos)))
					LinePos += 1;

				System.arraycopy(LineBuffer, TokenStart, Result, 0, LinePos-TokenStart);  // TODO Double-check translation
			}

			/* Check for stop on comment */

			// if stop on comment, ignore rest of line.
			if (LastDelimiter == CommentChar) {
				LinePos = LineBuffer.length() + 1;
			} else {

				/* Get Rid of Trailing White Space */
				if (LastDelimiter == ' ')
					skipWhiteSpace(LineBuffer,LinePos);
				if (isDelimChar(LineBuffer.charAt(LinePos))) {
					LastDelimiter = LineBuffer.charAt(LinePos);
					LinePos += 1;  // Move past terminating delimiter
				}
				skipWhiteSpace(LineBuffer,LinePos);
			}
		}

		return Result;
	}

	public String getNextParam() {
		if (Position <= CmdBuffer.length()) {
			LastDelimiter = ' ';
			TokenBuffer = getToken(CmdBuffer, Position); // Get entire token and put in token Buffer
			if (LastDelimiter == '=') {
				ParameterBuffer = TokenBuffer;
				TokenBuffer = getToken(CmdBuffer, Position);
			} else {
				ParameterBuffer = "";  // init to null string
			}
		} else {
			ParameterBuffer = "";
			TokenBuffer = "";
		}
		return ParameterBuffer;
	}

	/**
	 * Looking for "BusName.1.2.3" in the TokenBuffer.
	 * Assumes NodeArray is big enough to hold the numbers.
	 */
	public String parseAsBusName(int NumNodes, int[] NodeArray) {
		int DotPos, NodeBufferPos;
		String NodeBuffer, DelimSave, TokenSave, Result;

		if (AutoIncrement)
			getNextParam();

		NumNodes = 0;
		DotPos = TokenBuffer.indexOf('.');
		if (DotPos == -1) {
			return TokenBuffer;
		} else {
			Result = TokenBuffer.substring(0, DotPos).trim();  // bus name  TODO Check zero based indexing
			TokenSave = TokenBuffer;
			/* now get nodes */
			NodeBuffer = TokenBuffer.substring(DotPos, TokenBuffer.length() - DotPos) + " ";

			NodeBufferPos = 0;
			DelimSave = DelimChars;
			DelimChars = ".";
			TokenBuffer = getToken(NodeBuffer, NodeBufferPos);
			try {
				while (TokenBuffer.length() > 0) {
					NumNodes += 1;
					NodeArray[NumNodes] = makeInteger();
					if (ConvertError)
						NodeArray[NumNodes] = -1;  // Indicate an error
					TokenBuffer = getToken(NodeBuffer, NodeBufferPos);
				}
			} catch (Exception e) {
				DSSForms.messageDlg("Node Buffer Too Small: " + e.getMessage(), true);
			}

			DelimChars = DelimSave;   //restore to original delimiters
			TokenBuffer = TokenSave;
		}
		return Result;
	}

	public int parseAsVector(int ExpectedSize, double[] VectorBuffer) {
		int ParseBufferPos = 0, NumElements, i;
		String ParseBuffer = null, DelimSave = null;

		if (AutoIncrement)
			getNextParam();

		NumElements = 0;
		int Result = 0;  // return 0 if none found or error occurred
		try {
			for (i = 0; i < ExpectedSize; i++)
				VectorBuffer[i] = 0.0;

			/* now get vector values */
			ParseBuffer = TokenBuffer + " ";

			ParseBufferPos = 0;
			DelimSave      = DelimChars;
			DelimChars     = DelimChars + MatrixRowTerminator;

			skipWhiteSpace(ParseBuffer, ParseBufferPos);
			TokenBuffer = getToken(ParseBuffer, ParseBufferPos);
			while (TokenBuffer.length() > 0) {
				NumElements += 1;
				if (NumElements <= ExpectedSize)
					VectorBuffer[NumElements] = makeDouble();
				if (LastDelimiter == MatrixRowTerminator)
					break;
				TokenBuffer = getToken(ParseBuffer, ParseBufferPos);
			}

			Result = NumElements;
		} catch (Exception e) {
			DSSForms.messageDlg("Vector Buffer in ParseAsVector Probably Too Small: " + e.getMessage(), true);
		}

		DelimChars  = DelimSave;   // restore to original delimiters
		TokenBuffer = ParseBuffer.substring(ParseBufferPos, ParseBuffer.length());  // prepare for next trip

		return Result;
	}

	public int parseAsMatrix(int ExpectedOrder, double[] MatrixBuffer) {
		int i, j, k, ElementsFound;
		double[] RowBuf;

		if (AutoIncrement)
			getNextParam();

		RowBuf = null;

		try {
			RowBuf = new double[ExpectedOrder];

			for (i = 0; i < ExpectedOrder * ExpectedOrder; i++)
				MatrixBuffer[i] = 0.0;

			for (i = 0; i < ExpectedOrder; i++) {
				ElementsFound = parseAsVector(ExpectedOrder, RowBuf);

				/* Returns matrix in Column Order (Fortran order) */
				k = i;
				for (j = 0; j < ElementsFound; j++) {
					MatrixBuffer[k] = RowBuf[j];
					ExpectedOrder += k;
				}
			}
		} catch (Exception e) {
			DSSForms.messageDlg("Matrix Buffer in parseAsMatrix Probably Too Small: " + e.getMessage(), true);
		}

		if (RowBuf != null)
			RowBuf = null;

		return ExpectedOrder;
	}

	private int _elementIndex(int ii, int jj, int ExpectedOrder) {
		return (jj - 1) * ExpectedOrder + ii;  // TODO Check zero based indexing
	}

	public int parseAsSymMatrix(int ExpectedOrder, double[] MatrixBuffer) {
		int i, j, ElementsFound;
		double[] RowBuf;

		if (AutoIncrement)
			getNextParam();

		RowBuf = null;

		try {
			RowBuf = new double[ExpectedOrder];

			for (i = 0; i < ExpectedOrder * ExpectedOrder; i++)
				MatrixBuffer[i] = 0.0;

			for (i = 0; i < ExpectedOrder; i++) {
				ElementsFound = parseAsVector(ExpectedOrder, RowBuf);

				/* Returns matrix in Column Order (Fortran order) */
				for (j = 0; j < ElementsFound; j++) {
					MatrixBuffer[_elementIndex(i, j, ExpectedOrder)] = RowBuf[j];
					if (i != j)
						MatrixBuffer[_elementIndex(j, i, ExpectedOrder)] = RowBuf[j];
				}

			}

		} catch (Exception e) {
			DSSForms.messageDlg("Matrix Buffer in parseAsSymMatrix() probably too small: " + e.getMessage(), true);
		}

		if (RowBuf != null)
			RowBuf = null;

		return ExpectedOrder;
	}

	public String makeString() {
		if (AutoIncrement)
			getNextParam();

		return TokenBuffer;
	}

	public int makeInteger() {
		// Hex integers must be preceeded by "$"
		int Code = 0, Result = 0;
		double Temp = 0;

		ConvertError = false;
		if (AutoIncrement)
			getNextParam();

		if (TokenBuffer.length() == 0) {
			Result = 0;
		} else {
			if (IsQuotedString) {
				Temp = interpretRPNString(Code);
				Result = (int) Math.round(Temp);
			} else {
				try {
					Result = Integer.valueOf(TokenBuffer);  // Try direct conversion to integer
					Code = 1;
				} catch (NumberFormatException e) {
					Code = 0;
				}
			}

			if (Code != 0) {  // on error for integer conversion
				// Try again with an double result in case value specified in decimal or some other technique
				try {
					Temp = Double.valueOf(TokenBuffer);
					Code = 1;
				} catch (NumberFormatException e) {
					Code = 0;
				}
				if (Code != 0) {
					// not needed with throw ...  Result = 0;
					ConvertError = true;
//					throw new ParserProblem("Integer number conversion error for string: \""+TokenBuffer+"\"");
					DSSGlobals.getInstance().doErrorMsg("", "Integer number conversion error for string: \""+TokenBuffer+"\"", "", 0);
				} else {
					Result = (int) Math.round(Temp);
				}
			}
		}

		return Result;
	}

	public double makeDouble() {
		int Code = 0;
		double Result = 0;

		if (AutoIncrement)
			getNextParam();
		ConvertError = false;
		if (TokenBuffer.length() == 0) {
			Result = 0.0;
		} else {
			if (IsQuotedString) {
				Result = interpretRPNString(Code);
			} else {
				try {
					Result = Double.valueOf(TokenBuffer);
					Code = 1;
				} catch (NumberFormatException e) {
					Code = 0;
				}
			}

			if (Code != 0) {
				// not needed with throw ...  Result = 0.0;
				ConvertError = true;
//				throw new ParserProblem("Floating point number conversion error for string: \""+TokenBuffer+"\"");
				DSSGlobals.getInstance().doErrorMsg("", "Floating point number conversion error for string: \""+TokenBuffer+"\"", "", 0);
			}
		}

		return Result;
	}

	public String getRemainder() {
		return CmdBuffer.substring(Position, CmdBuffer.length() - Position + 1);  // TODO Check zero based indexing
	}

	/**
	 * Checks for CommentChar and '//'.
	 */
	private boolean isCommentChar(String LineBuffer, int LinePos) {
		switch (LineBuffer.charAt(LinePos)) {
		case CommentChar:
			return true;
		case '/':
			if ((LineBuffer.length() > LinePos) && (LineBuffer.charAt(LinePos + 1) == '/')) {
				return true;
			} else {
				return false;
			}
		default:
			return false;
		}
	}

	private double interpretRPNString(int Code) {
		int ParseBufferPos;
		String ParseBuffer;

		Code = 0;
		ParseBuffer = TokenBuffer + " ";
		ParseBufferPos = 0;

		skipWhiteSpace(ParseBuffer, ParseBufferPos);
		TokenBuffer = getToken(ParseBuffer, ParseBufferPos);

		while (TokenBuffer.length() > 0) {

			try {
				Code = processRPNCommand(TokenBuffer, RPNCalculator);
			} catch (ParserProblem e) {
				DSSGlobals.getInstance().doErrorMsg("", e.getMessage(), "", 0);
				if (Code > 0)
					break;  // Stop on any floating point error
			}

			TokenBuffer = getToken(ParseBuffer, ParseBufferPos);
		}

		double Result = RPNCalculator.getX();

		TokenBuffer = ParseBuffer.substring(ParseBufferPos, ParseBuffer.length());  // prepare for next trip

		return Result;
	}

	public String getDelimChars() {
		return DelimChars;
	}

	public void setDelimChars(String delimChars) {
		DelimChars = delimChars;
	}

	public String getWhiteSpaceChars() {
		return WhiteSpaceChars;
	}

	public void setWhiteSpaceChars(String whiteSpaceChars) {
		WhiteSpaceChars = whiteSpaceChars;
	}

	public String getBeginQuoteChars() {
		return BeginQuoteChars;
	}

	public void setBeginQuoteChars(String beginQuoteChars) {
		BeginQuoteChars = beginQuoteChars;
	}

	public String getEndQuoteChars() {
		return EndQuoteChars;
	}

	public void setEndQuoteChars(String endQuoteChars) {
		EndQuoteChars = endQuoteChars;
	}

	public boolean isAutoIncrement() {
		return AutoIncrement;
	}

	public void setAutoIncrement(boolean autoIncrement) {
		AutoIncrement = autoIncrement;
	}

	public String getCmdString() {
		return CmdBuffer;
	}

	public String getToken() {
		return TokenBuffer;
	}

	public void setToken(String Value) {
		this.TokenBuffer = Value;
	}
}
