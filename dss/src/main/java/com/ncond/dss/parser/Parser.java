/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.parser;

import com.ncond.dss.common.DSS;

import static java.lang.Math.round;


public class Parser {

	private static final char COMMENT_CHAR = '!';

	private String cmdBuffer;
	private int[] position;
	private String parameterBuffer;
	private String tokenBuffer;
	private String delimChars;
	private String whiteSpaceChars;
	private String beginQuoteChars;
	private String endQuoteChars;
	private char lastDelimiter;
	private char matrixRowTerminator;
	private boolean autoIncrement;
	private boolean convertError;
	private boolean isQuotedString;
	private RPNCalculator rpnCalc;

	private Parser() {
		super();
		delimChars = ",=";
		whiteSpaceChars = " " + "\t";  // blank + tab
		beginQuoteChars = "(\"'[{";
		endQuoteChars = ")\"']}";
		position = new int[] {0};
		matrixRowTerminator = '|';
		autoIncrement = false;
		rpnCalc = new RPNCalculator();
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

	public void setCommand(final String value) {
		cmdBuffer = value + " ";  // add some white space at end to get last param
		position[0] = 0;
		skipWhiteSpace(cmdBuffer, position);  // position at first non whitespace character
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

	private boolean isDelimiter(final String buf, int[] pos) {
		int i;
		char ch;

		if (isCommentChar(buf, pos)) {
			lastDelimiter = COMMENT_CHAR;
			return true;
		}

		ch = buf.charAt(pos[0]);

		for (i = 0; i < delimChars.length(); i++) {
			if (ch == delimChars.charAt(i)) {
				lastDelimiter = ch;
				return true;
			}
		}

		for (i = 0; i < whiteSpaceChars.length(); i++) {
			if (ch == whiteSpaceChars.charAt(i)) {
				lastDelimiter = ' ';  // to indicate stopped on white space
				return true;
			}
		}
		return false;
	}

	private boolean isDelimChar(char ch) {
		for (int i = 0; i < delimChars.length(); i++)
			if (ch == delimChars.charAt(i))
				return true;
		return false;
	}

	private void skipWhiteSpace(final String buffer, int[] pos) {
		while ((pos[0] < buffer.length() - 1) && isWhiteSpace(buffer.charAt(pos[0]))) {
			pos[0] += 1;
		}
	}

	private String getToken(final String buffer, int[] pos) {
		int tokenStart;
		int quoteIdx;  // value of quote character found
		String tok = "";  // if it doesn't find anything, return null string

		if (pos[0] < buffer.length()) {
			/* Handle quotes and parentheses around tokens */
			isQuotedString = false;

			quoteIdx = beginQuoteChars.indexOf( buffer.charAt(pos[0]) );
			if (quoteIdx >= 0) {
				tok = _parseToEndChar(endQuoteChars.charAt(quoteIdx), buffer, pos);
				isQuotedString = true;
			} else {  // copy to next delimiter or whitespace
				tokenStart = pos[0];

				while ((pos[0] < buffer.length() - 1) && !isDelimiter(buffer, pos))
					pos[0] += 1;

				tok = buffer.substring(tokenStart, pos[0]);
			}

			// if stop on comment, ignore rest of line.
			if (lastDelimiter == COMMENT_CHAR) {
				pos[0] = buffer.length();
			} else {
				// get rid of trailing white space
				if (lastDelimiter == ' ')
					skipWhiteSpace(buffer, pos);

				if (isDelimChar(buffer.charAt(pos[0]))) {
					lastDelimiter = buffer.charAt(pos[0]);
					pos[0] += 1;  // move past terminating delimiter
				}

				skipWhiteSpace(buffer, pos);
			}
		}

		return tok;
	}

	private String _parseToEndChar(char endChar, final String buffer, int[] pos) {
		String tok;

		pos[0] += 1;
		int tokenStart = pos[0];

		while ((pos[0] < buffer.length() - 1) && buffer.charAt(pos[0]) != endChar)
			pos[0] += 1;

		tok = buffer.substring(tokenStart, pos[0]);

		if (pos[0] < buffer.length() - 1)
			pos[0] += 1;  // increment past endchar

		return tok;
	}

	public String getNextParam() {
		if (position[0] < cmdBuffer.length()) {
			lastDelimiter = ' ';
			// get entire token and put in token buffer
			tokenBuffer = getToken(cmdBuffer, position);

			if (lastDelimiter == '=') {
				parameterBuffer = tokenBuffer;
				tokenBuffer = getToken(cmdBuffer, position);
			} else {
				// init to null string
				parameterBuffer = "";
			}
		} else {
			parameterBuffer = "";
			tokenBuffer = "";
		}
		return parameterBuffer;
	}

	/**
	 * Looking for "busName.1.2.3" in the tokenBuffer. Assumes
	 * nodeArray is big enough to hold the numbers.
	 */
	public String parseAsBusName(int[] numNodes, int[] nodeArray) {
		int dotpos;
		String nodeBuffer, delimSave, tokenSave, busName;
		int[] nodeBufferPos;

		if (autoIncrement) getNextParam();

		numNodes[0] = 0;

		dotpos = tokenBuffer.indexOf('.');
		if (dotpos == -1) {
			return tokenBuffer;
		} else {
			busName = tokenBuffer.substring(0, dotpos).trim();
			tokenSave = tokenBuffer;
			// now get nodes
			nodeBuffer = tokenBuffer.substring(dotpos + 1, tokenBuffer.length()) + " ";

			nodeBufferPos = new int[] {0};
			delimSave = delimChars;
			delimChars = ".";
			tokenBuffer = getToken(nodeBuffer, nodeBufferPos);
			try {
				while (tokenBuffer.length() > 0) {
					numNodes[0] += 1;
					nodeArray[numNodes[0] - 1] = integerValue();
					if (convertError) nodeArray[numNodes[0] - 1] = -1;  // indicate an error
					tokenBuffer = getToken(nodeBuffer, nodeBufferPos);
				}
			} catch (Exception e) {
				DSS.forms.messageDlg("Node buffer too small: " + e.getMessage(), true);
			}

			delimChars = delimSave;  // restore to original delimiters
			tokenBuffer = tokenSave;
		}
		return busName;
	}

	public int parseAsVector(int expectedSize, double[] vectorBuffer) {
		int numElements, i;
		String parseBuffer = "", delimSave = delimChars;
		int[] parseBufferPos = new int[1];

		if (autoIncrement) getNextParam();

		numElements = 0;
		int success = 0;  // return 0 if none found or error occurred
		try {
			for (i = 0; i < expectedSize; i++)
				vectorBuffer[i] = 0.0;

			/* now get vector values */
			parseBuffer = tokenBuffer + " ";

			parseBufferPos[0] = 0;
			delimChars = delimChars + matrixRowTerminator;

			skipWhiteSpace(parseBuffer, parseBufferPos);

			tokenBuffer = getToken(parseBuffer, parseBufferPos);

			while (tokenBuffer.length() > 0) {
				numElements += 1;
				if (numElements <= expectedSize)
					vectorBuffer[numElements - 1] = doubleValue();
				if (lastDelimiter == matrixRowTerminator) break;
				tokenBuffer = getToken(parseBuffer, parseBufferPos);
			}

			success = numElements;
		} catch (Exception e) {
			DSS.forms.messageDlg("Vector buffer too small: " + e.getMessage(), true);
		}

		delimChars = delimSave;  // restore to original delimiters
		tokenBuffer = parseBuffer.substring(parseBufferPos[0]);  // prepare for next trip

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
					k += expectedOrder;
				}
			}
		} catch (Exception e) {
			DSS.forms.messageDlg("Matrix buffer too small: " + e.getMessage(), true);
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

				/* Returns matrix in column order (Fortran order) */
				for (j = 0; j < elementsFound; j++) {
					matrixBuffer[_elementIndex(i, j, expectedOrder)] = rowBuf[j];
					if (i != j) {
						matrixBuffer[_elementIndex(j, i, expectedOrder)] = rowBuf[j];
					}
				}
			}

		} catch (Exception e) {
			DSS.forms.messageDlg("Matrix buffer too small: " + e.getMessage(), true);
		}

		return expectedOrder;
	}

	private int _elementIndex(int ii, int jj, int expectedOrder) {
		return jj * expectedOrder + ii;
	}

	public String stringValue() {
		if (autoIncrement) getNextParam();
		return tokenBuffer;
	}

	public int integerValue() {
		// hex integers must be preceeded by "$"
		int result = 0;
		double tmp = 0;
		int[] code = new int[1];

		convertError = false;
		if (autoIncrement) getNextParam();

		if (tokenBuffer.length() == 0) {
			result = 0;
		} else {
			if (isQuotedString) {
				tmp = interpretRPNString(code);
				result = (int) round(tmp);
			} else {
				try {
					result = Integer.valueOf(tokenBuffer);  // try direct conversion to integer
					code[0] = 0;
				} catch (NumberFormatException e) {
					code[0] = 1;  // index of the offending character
				}
			}

			if (code[0] != 0) {  // on error for integer conversion
				// try again with an double result in case value specified in
				// decimal or some other technique
				try {
					tmp = Double.parseDouble(tokenBuffer);
					code[0] = 0;
				} catch (NumberFormatException e) {
					code[0] = 1;
				}
				if (code[0] != 0) {
					// not needed with throw ...  result = 0;
					convertError = true;
					//throw new ParserProblem("Integer number conversion error for string: \"" + tokenBuffer + "\"");
					DSS.forms.messageDlg("Integer number conversion error for string: \"" +
							tokenBuffer + "\"", true);
				} else {
					result = (int) round(tmp);
				}
			}
		}

		return result;
	}

	public double doubleValue() {
		int[] code = new int[1];
		double dbl = 0;

		if (autoIncrement) getNextParam();

		convertError = false;

		if (tokenBuffer.length() == 0) {
			dbl = 0.0;
		} else {
			if (isQuotedString) {
				dbl = interpretRPNString(code);
			} else {
				try {
					dbl = Double.parseDouble(tokenBuffer);
					code[0] = 0;
				} catch (NumberFormatException e) {
					code[0] = 1;  // index of the offending character
				}
			}

			if (code[0] != 0) {
				// not needed with throw ... result = 0.0;
				convertError = true;
				//throw new ParserProblem("Floating point number conversion error for string: \"" + tokenBuffer + "\"");
				DSS.forms.messageDlg("Floating point number conversion error for string: \"" +
						tokenBuffer + "\"", true);
			}
		}

		return dbl;
	}

	public String getRemainder() {
		return cmdBuffer.substring(position[0]);
	}

	/**
	 * Checks for commentChar and '//'.
	 */
	private boolean isCommentChar(final String lineBuffer, int[] linePos) {
		char c = lineBuffer.charAt(linePos[0]);
		if (c == COMMENT_CHAR) {
			return true;
		} else if (c == DSS.SEPARATOR.charAt(0)) {  // FIXME: separator is a string
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
		parseBuffer = tokenBuffer + " ";
		parseBufferPos = new int[1];

		skipWhiteSpace(parseBuffer, parseBufferPos);
		tokenBuffer = getToken(parseBuffer, parseBufferPos);

		while (tokenBuffer.length() > 0) {
			try {
				code[0] = processRPNCommand(tokenBuffer, rpnCalc);
			} catch (ParserProblem e) {
				//DSS.doErrorMsg("", e.getMessage(), "", 0);
				if (code[0] > 0) break;  // stop on any floating point error
			}
			tokenBuffer = getToken(parseBuffer, parseBufferPos);
		}

		// prepare for next trip
		tokenBuffer = parseBuffer.substring(parseBufferPos[0]);

		return rpnCalc.getX();
	}

	private int processRPNCommand(final String tokenBuffer, RPNCalculator rpn) throws ParserProblem {
		double number = 0;
		int error = 0;  // error code on conversion error

		/* First try to make a valid number. If that fails, check for RPN command */
		try {
			number = Double.parseDouble(tokenBuffer);
			error = 0;
		} catch (NumberFormatException e) {
			error = 1;  // index of first invalid character
		}

		if (error == 0) {
			rpn.setX(number);  // enters number in X register
		} else {  /* Check for RPN command. */
			error = 0; // reset error return
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
				error = 1;  // error
				throw new ParserProblem("Invalid inline math entry: \"" +
						tokenBuffer + "\"");
			}
		}

		return error;
	}

	public String getToken() {
		return tokenBuffer;
	}

	public void setToken(String token) {
		tokenBuffer = token;
	}

	public int getPosition() {
		return position[0];
	}

	public void setPosition(int pos) {
		position[0] = pos;
	}

	public String getDelimChars() {
		return delimChars;
	}

	public void setDelimChars(String delimChars) {
		this.delimChars = delimChars;
	}

	public String getCommand() {
		return cmdBuffer;
	}

}
