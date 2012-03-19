/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.parser;

import lombok.Getter;
import lombok.Setter;

import com.ncond.dss.common.DSS;

@Getter @Setter
public class Parser {

	private static final char COMMENT_CHAR = '!';

	private String cmdString;
	private int position;
	private String parameterBuffer;
	private String token;
	private String delimChars;
	private String whiteSpaceChars;
	private String beginQuoteChars;
	private String endQuoteChars;
	private char lastDelimiter;
	private char matrixRowTerminator;
	private boolean autoIncrement;
	private boolean convertError;
	private boolean isQuotedString;
	private RPNCalc RPNCalculator;

	private Parser() {
		super();

		delimChars = ",=";
		whiteSpaceChars = " " + "\t";  // blank + tab
		beginQuoteChars = "(\"'[{";
		endQuoteChars = ")\"']}";
		position = 0;
		matrixRowTerminator = '|';
		autoIncrement = false;
		RPNCalculator = new RPNCalc();
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

	private int processRPNCommand(String tokenBuffer, RPNCalc rpn) throws ParserProblem {
		double number = 0;
		int errorCode = 0;  // error code on conversion error

		/* First try to make a valid number. If that fails, check for RPN command */
		try {
			number = Double.parseDouble(tokenBuffer);
			errorCode = 1;
		} catch (NumberFormatException e) {
			errorCode = 0;
		}

		if (errorCode == 0) {
			rpn.setX(number);  // enters number in X register
		} else {  /* Check for RPN command. */
			errorCode = 0; // reset error return
			String s = tokenBuffer.toLowerCase();

			if (s.equalsIgnoreCase("+")) {
				rpn.add();
			} else if (s.equalsIgnoreCase("-")) {
				rpn.subtract();
			} else if (s.equalsIgnoreCase("*")) {
				rpn.multiply();
			} else if (s.equalsIgnoreCase("/")) {
				rpn.divide();
			} else if (s.equalsIgnoreCase("sqrt")) {
				rpn.sqrt();
			} else if (s.equalsIgnoreCase("sqr")) {
				rpn.square();
			} else if (s.equalsIgnoreCase("^")) {
				rpn.yToTheXPower();
			} else if (s.equalsIgnoreCase("sin")) {
				rpn.sinDeg();
			} else if (s.equalsIgnoreCase("cos")) {
				rpn.cosDeg();
			} else if (s.equalsIgnoreCase("tan")) {
				rpn.tanDeg();
			} else if (s.equalsIgnoreCase("asin")) {
				rpn.aSinDeg();
			} else if (s.equalsIgnoreCase("acos")) {
				rpn.aCosDeg();
			} else if (s.equalsIgnoreCase("atan")) {
				rpn.aTanDeg();
			} else if (s.equalsIgnoreCase("atan2")) {
				rpn.aTan2Deg();
			} else if (s.equalsIgnoreCase("swap")) {
				rpn.swapXY();
			} else if (s.equalsIgnoreCase("rollup")) {
				rpn.rollUp();
			} else if (s.equalsIgnoreCase("rolldn")) {
				rpn.rollDn();
			} else if (s.equalsIgnoreCase("ln")) {
				rpn.natLog();
			} else if (s.equalsIgnoreCase("pi")) {
				rpn.enterPi();
			} else if (s.equalsIgnoreCase("log10")) {
				rpn.tenLog();
			} else if (s.equalsIgnoreCase("exp")) {
				rpn.eToTheX();
			} else if (s.equalsIgnoreCase("inv")) {
				rpn.inv();
			} else {
				errorCode = 1;  // error
				throw new ParserProblem("Invalid inline math entry: \"" + tokenBuffer + "\"");
			}
		}
		return errorCode;
	}

	public void setCmdString(final String value) {
		int[] mPosition;

		cmdString = value + " ";  // add some white space at end to get last param

		mPosition = new int[1];
		skipWhiteSpace(cmdString, mPosition);  // position at first non whitespace character
		position = mPosition[0];  // passed by reference
	}

	/**
	 * Resets delimiters to default.
	 */
	public void resetDelims() {
		delimChars = ",=";
		whiteSpaceChars = " " + "\t";  // blank + tab
		matrixRowTerminator = '|';
		beginQuoteChars = "(\"'[{";
		endQuoteChars = ")\"']}";
	}

	private boolean isWhiteSpace(char ch) {
		for (int i = 0; i < whiteSpaceChars.length(); i++)
			if (ch == whiteSpaceChars.charAt(i))
				return true;
		return false;
	}

	private boolean isDelimiter(final String lineBuffer, int[] linePos) {
		int i;
		char ch;
		boolean isdelim = false;

		if (isCommentChar(lineBuffer, linePos)) {
			isdelim = true;
			lastDelimiter = COMMENT_CHAR;
			return isdelim;
		}

		ch = lineBuffer.charAt(linePos[0]);

		for (i = 0; i < delimChars.length(); i++) {
			if (ch == delimChars.charAt(i)) {
				isdelim = true;
				lastDelimiter = ch;
				return isdelim;
			}
		}

		for (i = 0; i < whiteSpaceChars.length(); i++) {
			if (ch == whiteSpaceChars.charAt(i)) {
				isdelim = true;
				lastDelimiter = ' ';  // to indicate stopped on white space
				return isdelim;
			}
		}
		return isdelim;
	}

	private boolean isDelimChar(char ch) {
		for (int i = 0; i < delimChars.length(); i++)
			if (ch == delimChars.charAt(i))
				return true;
		return false;
	}

	private void skipWhiteSpace(final String lineBuffer, int[] linePos) {
		while ((linePos[0] < lineBuffer.length() - 1) &&
				isWhiteSpace(lineBuffer.charAt(linePos[0]))) {
			linePos[0]++;
		}
	}


	private int _tokenStart;
	private int _cmdBufLength;
	private int _quoteIndex;  // value of quote character found
	private String _lineBuffer;
	private int _linePos;
	private String _result;

	private void _parseToEndChar(char endChar) {
		_linePos += 1;
		_tokenStart = _linePos;
		while ((_linePos < _cmdBufLength - 1) && (_lineBuffer.charAt(_linePos) != endChar))
			_linePos += 1;

		_result = _lineBuffer.substring(_tokenStart, _linePos);
		if (_linePos < _cmdBufLength)
			_linePos += 1;  // increment past endchar
	}

	private void _parseToEndQuote() {
		_parseToEndChar(endQuoteChars.charAt(_quoteIndex));
		isQuotedString = true;
	}

	private boolean _isBeginQuote(char ch) {
		_quoteIndex = beginQuoteChars.indexOf(ch);
		return (_quoteIndex >= 0);
	}

	private String getToken(final String lineBuffer, int[] linePos) {
		int[] mLinePos;
		_lineBuffer = lineBuffer;
		_linePos = linePos[0];

		_result = "";  // if it doesn't find anything, return null string

		_cmdBufLength = _lineBuffer.length();
		if (_linePos < _cmdBufLength) {
			/* Handle quotes and parentheses around tokens */
			isQuotedString = false;

			if (_isBeginQuote(_lineBuffer.charAt(_linePos))) {
				_parseToEndQuote();
			} else {  /* Copy to next delimiter or whitespace */
				_tokenStart = _linePos;
				mLinePos = new int[] {_linePos};

				while ((mLinePos[0] < _cmdBufLength) &&
						!isDelimiter(_lineBuffer, mLinePos)) {
					mLinePos[0]++;
				}

				_linePos = mLinePos[0];

				_result = _lineBuffer.substring(_tokenStart, _linePos);
			}

			/* Check for stop on comment */

			// if stop on comment, ignore rest of line.
			if (lastDelimiter == COMMENT_CHAR) {
				_linePos = _lineBuffer.length() + 1;
			} else {
				/* Get rid of trailing white space */
				if (lastDelimiter == ' ') {
					mLinePos = new int[] {_linePos};
					skipWhiteSpace(_lineBuffer, mLinePos);
					_linePos = mLinePos[0];
				}

				if (isDelimChar(_lineBuffer.charAt(_linePos))) {
					lastDelimiter = _lineBuffer.charAt(_linePos);
					_linePos += 1;  // move past terminating delimiter
				}

				mLinePos = new int[] {_linePos};
				skipWhiteSpace(_lineBuffer, mLinePos);
				_linePos = mLinePos[0];
			}
		}

		linePos[0] = _linePos;

		return _result;
	}

	public String getNextParam() {
		int[] mPosition;

		if (position < cmdString.length()) {
			mPosition = new int[] {position};
			lastDelimiter = ' ';
			// get entire token and put in token buffer
			token = getToken(cmdString, mPosition);

			if (lastDelimiter == '=') {
				parameterBuffer = token;
				token = getToken(cmdString, mPosition);
			} else {
				// init to null string
				parameterBuffer = "";
			}

			position = mPosition[0];
		} else {
			parameterBuffer = "";
			token = "";
		}
		return parameterBuffer;
	}

	/**
	 * Looking for "busName.1.2.3" in the TokenBuffer.
	 * Assumes nodeArray is big enough to hold the numbers.
	 */
	public String parseAsBusName(int[] numNodes, int[] nodeArray) {
		int dotpos;
		String nodeBuffer, delimSave, tokenSave, name;
		int[] nodeBufferPos;

		if (autoIncrement) getNextParam();

		numNodes[0] = 0;

		dotpos = token.indexOf('.');
		if (dotpos == -1) {
			return token;
		} else {
			name = token.substring(0, dotpos).trim();  // bus name
			tokenSave = token;
			// now get nodes
			nodeBuffer = token.substring(dotpos, token.length() - dotpos) + " ";

			nodeBufferPos = new int[] {0};
			delimSave = delimChars;
			delimChars = ".";
			token = getToken(nodeBuffer, nodeBufferPos);
			try {
				while (token.length() > 0) {
					numNodes[0]++;
					nodeArray[numNodes[0]] = makeInteger();
					if (convertError) nodeArray[numNodes[0]] = -1;  // indicate an error
					token = getToken(nodeBuffer, nodeBufferPos);
				}
			} catch (Exception e) {
				DSS.forms.messageDlg("Node buffer too small: " + e.getMessage(), true);
			}

			delimChars = delimSave;  // restore to original delimiters
			token = tokenSave;
		}
		return name;
	}

	public int parseAsVector(int expectedSize, double[] vectorBuffer) {
		int numElements, i;
		String parseBuffer = null, delimSave = null;
		int[] parseBufferPos = new int[1];

		if (autoIncrement) getNextParam();

		numElements = 0;
		int success = 0;  // return 0 if none found or error occurred
		try {
			for (i = 0; i < expectedSize; i++) {
				vectorBuffer[i] = 0.0;
			}

			/* now get vector values */
			parseBuffer = token + " ";

			parseBufferPos[0] = 0;
			delimSave = delimChars;
			delimChars = delimChars + matrixRowTerminator;

			skipWhiteSpace(parseBuffer, parseBufferPos);
			token = getToken(parseBuffer, parseBufferPos);
			while (token.length() > 0) {
				numElements += 1;
				if (numElements <= expectedSize)
					vectorBuffer[numElements] = makeDouble();
				if (lastDelimiter == matrixRowTerminator) break;
				token = getToken(parseBuffer, parseBufferPos);
			}

			success = numElements;
		} catch (Exception e) {
			DSS.forms.messageDlg("Vector buffer in parseAsVector probably too small: " +
					e.getMessage(), true);
		}

		delimChars  = delimSave;  // restore to original delimiters
		token = parseBuffer.substring(parseBufferPos[0],
				parseBuffer.length());  // prepare for next trip

		return success;
	}

	public int parseAsMatrix(int expectedOrder, double[] matrixBuffer) {
		int i, j, k, elementsFound;
		double[] rowBuf;

		if (autoIncrement) getNextParam();

		try {
			rowBuf = new double[expectedOrder];

			for (i = 0; i < expectedOrder * expectedOrder; i++)
				matrixBuffer[i] = 0.0;

			for (i = 0; i < expectedOrder; i++) {
				elementsFound = parseAsVector(expectedOrder, rowBuf);

				/* Returns matrix in column order (Fortran order) */
				k = i;
				for (j = 0; j < elementsFound; j++) {
					matrixBuffer[k] = rowBuf[j];
					expectedOrder += k;
				}
			}
		} catch (Exception e) {
			DSS.forms.messageDlg("Matrix buffer in parseAsMatrix probably too small: " + e.getMessage(), true);
		}

		return expectedOrder;
	}

	public int parseAsSymMatrix(int expectedOrder, double[] matrixBuffer) {
		int i, j, elementsFound;
		double[] rowBuf;

		if (autoIncrement) getNextParam();

		try {
			rowBuf = new double[expectedOrder];

			for (i = 0; i < expectedOrder * expectedOrder; i++)
				matrixBuffer[i] = 0.0;

			for (i = 0; i < expectedOrder; i++) {
				elementsFound = parseAsVector(expectedOrder, rowBuf);

				/* Returns matrix in Column Order (Fortran order) */
				for (j = 0; j < elementsFound; j++) {
					matrixBuffer[_elementIndex(i, j, expectedOrder)] = rowBuf[j];
					if (i != j) matrixBuffer[_elementIndex(j, i, expectedOrder)] = rowBuf[j];
				}

			}

		} catch (Exception e) {
			DSS.forms.messageDlg("Matrix buffer in parseAsSymMatrix() probably too small: " +
					e.getMessage(), true);
		}

		return expectedOrder;
	}

	private int _elementIndex(int ii, int jj, int expectedOrder) {
		return jj * expectedOrder + ii;
	}

	public String makeString() {
		if (autoIncrement) getNextParam();
		return token;
	}

	public int makeInteger() {
		// hex integers must be preceeded by "$"
		int integer = 0;
		double tmp = 0;
		int[] code = new int[1];

		convertError = false;
		if (autoIncrement) getNextParam();

		if (token.length() == 0) {
			integer = 0;
		} else {
			if (isQuotedString) {
				tmp = interpretRPNString(code);
				integer = (int) Math.round(tmp);
			} else {
				try {
					integer = Integer.valueOf(token);  // try direct conversion to integer
					code[0] = 0;
				} catch (NumberFormatException e) {
					code[0] = 1;  // index of the offending character
				}
			}

			if (code[0] != 0) {  // on error for integer conversion
				// try again with an double result in case value specified in decimal or some other technique
				try {
					tmp = Double.parseDouble(token);
					code[0] = 1;
				} catch (NumberFormatException e) {
					code[0] = 0;
				}
				if (code[0] != 0) {
					// not needed with throw ...  Result = 0;
					convertError = true;
					//throw new ParserProblem("Integer number conversion error for string: \"" + tokenBuffer + "\"");
					DSS.doSimpleMsg("Integer number conversion error for string: \"" + token + "\"", 0);
				} else {
					integer = (int) Math.round(tmp);
				}
			}
		}

		return integer;
	}

	public double makeDouble() {
		int[] code = new int[1];
		double dbl = 0;

		if (autoIncrement) getNextParam();

		convertError = false;

		if (token.length() == 0) {
			dbl = 0.0;
		} else {
			if (isQuotedString) {
				dbl = interpretRPNString(code);
			} else {
				try {
					dbl = Double.parseDouble(token);
					code[0] = 0;
				} catch (NumberFormatException e) {
					code[0] = 1;  // index of the offending character
				}
			}

			if (code[0] != 0) {
				// not needed with throw ... result = 0.0;
				convertError = true;
				//throw new ParserProblem("Floating point number conversion error for string: \"" + tokenBuffer + "\"");
				DSS.doSimpleMsg("Floating point number conversion error for string: \"" + token + "\"", 0);
			}
		}

		return dbl;
	}

	public String getRemainder() {
		return cmdString.substring(position, cmdString.length());
	}

	/**
	 * Checks for commentChar and '//'.
	 */
	private boolean isCommentChar(final String lineBuffer, int[] linePos) {
		char c = lineBuffer.charAt(linePos[0]);
		if (c == COMMENT_CHAR) {
			return true;
		} else if (c == DSS.SEPARATOR.charAt(0)) {
			if ((lineBuffer.length() - 1 > linePos[0]) &&
					(lineBuffer.charAt(linePos[0] + 1) == '/')) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private double interpretRPNString(int[] code) {
		int[] parseBufferPos;
		String parseBuffer;

		code[0] = 0;
		parseBuffer = token + " ";
		parseBufferPos = new int[1];

		skipWhiteSpace(parseBuffer, parseBufferPos);
		token = getToken(parseBuffer, parseBufferPos);

		while (token.length() > 0) {
			try {
				code[0] = processRPNCommand(token, RPNCalculator);
			} catch (ParserProblem e) {
				DSS.doErrorMsg("", e.getMessage(), "", 0);
				if (code[0] > 0) break;  // stop on any floating point error
			}
			token = getToken(parseBuffer, parseBufferPos);
		}

		double dbl = RPNCalculator.getX();

		// prepare for next trip
		token = parseBuffer.substring(parseBufferPos[0], parseBuffer.length());

		return dbl;
	}

	public String getToken() {
		return token;
	}

}
