package com.epri.dss.parser.impl;

import org.apache.commons.lang.mutable.MutableInt;

import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.parser.RPNCalc;

public class Parser {

	private static final char CommentChar = '!';

	private String cmdBuffer;
	private int position;
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
	private RPNCalc RPNCalculator;

	private Parser() {
		super();

		delimChars          = ",=";
		whiteSpaceChars     = " " + "\t";  // blank + tab
		beginQuoteChars     = "(\"'[{";
		endQuoteChars       = ")\"']}";
		position            = 0;
		matrixRowTerminator = '|';
		autoIncrement       = false;
		RPNCalculator       = new RPNCalcImpl();
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
		int result = 0;  // error code on conversion error

		/* First try to make a valid number. If that fails, check for RPN command */
		try {
			number = Double.valueOf(tokenBuffer);
			result = 1;
		} catch (NumberFormatException e) {
			result = 0;
		}

		if (result == 0) {
			rpn.setX(number);  // enters number in X register
		} else {  /* Check for RPN command. */
			result = 0; // reset error return
			String S = tokenBuffer.toLowerCase();

			if (S.equalsIgnoreCase("+")) {
				rpn.add();
			} else if (S.equalsIgnoreCase("-")) {
				rpn.subtract();
			} else if (S.equalsIgnoreCase("*")) {
				rpn.multiply();
			} else if (S.equalsIgnoreCase("/")) {
				rpn.divide();
			} else if (S.equalsIgnoreCase("sqrt")) {
				rpn.sqrt();
			} else if (S.equalsIgnoreCase("sqr")) {
				rpn.square();
			} else if (S.equalsIgnoreCase("^")) {
				rpn.yToTheXPower();
			} else if (S.equalsIgnoreCase("sin")) {
				rpn.sinDeg();
			} else if (S.equalsIgnoreCase("cos")) {
				rpn.cosDeg();
			} else if (S.equalsIgnoreCase("tan")) {
				rpn.tanDeg();
			} else if (S.equalsIgnoreCase("asin")) {
				rpn.aSinDeg();
			} else if (S.equalsIgnoreCase("acos")) {
				rpn.aCosDeg();
			} else if (S.equalsIgnoreCase("atan")) {
				rpn.aTanDeg();
			} else if (S.equalsIgnoreCase("atan2")) {
				rpn.aTan2Deg();
			} else if (S.equalsIgnoreCase("swap")) {
				rpn.swapXY();
			} else if (S.equalsIgnoreCase("rollup")) {
				rpn.rollUp();
			} else if (S.equalsIgnoreCase("rolldn")) {
				rpn.rollDn();
			} else if (S.equalsIgnoreCase("ln")) {
				rpn.natLog();
			} else if (S.equalsIgnoreCase("pi")) {
				rpn.enterPi();
			} else if (S.equalsIgnoreCase("log10")) {
				rpn.tenLog();
			} else if (S.equalsIgnoreCase("exp")) {
				rpn.eToTheX();
			} else if (S.equalsIgnoreCase("inv")) {
				rpn.inv();
			} else {
				result = 1;  // error
				throw new ParserProblem("Invalid inline math entry: \""+tokenBuffer+"\"");
			}
		}
		return result;
	}

	public void setCmdString(final String value) {
		MutableInt mPosition;

		cmdBuffer = value + " ";  // add some white space at end to get last param

		mPosition = new MutableInt(0);
		skipWhiteSpace(cmdBuffer, mPosition);  // position at first non whitespace character
		position = mPosition.intValue();  // passed by reference
	}

	/**
	 * Resets delimiters to default.
	 */
	public void resetDelims() {
		delimChars          = ",=";
		whiteSpaceChars     = " " + "\t";  // blank + tab
		matrixRowTerminator = '|';
		beginQuoteChars     = "(\"'[{";
		endQuoteChars       = ")\"']}";
	}

	private boolean isWhiteSpace(char ch) {
		for (int i = 0; i < whiteSpaceChars.length(); i++)
			if (ch == whiteSpaceChars.charAt(i))
				return true;
		return false;
	}

	private boolean isDelimiter(final String lineBuffer, MutableInt linePos) {
		int i;
		char ch;

		boolean result = false;

		if (isCommentChar(lineBuffer, linePos)) {
			result = true;
			lastDelimiter = CommentChar;
			return result;
		}

		ch = lineBuffer.charAt(linePos.intValue());

		for (i = 0; i < delimChars.length(); i++) {
			if (ch == delimChars.charAt(i)) {
				result = true;
				lastDelimiter = ch;
				return result;
			}
		}

		for (i = 0; i < whiteSpaceChars.length(); i++) {
			if (ch == whiteSpaceChars.charAt(i)) {
				result = true;
				lastDelimiter = ' ';  // to indicate stopped on white space
				return result;
			}
		}
		return result;
	}

	private boolean isDelimChar(char ch) {
		for (int i = 0; i < delimChars.length(); i++)
			if (ch == delimChars.charAt(i))
				return true;
		return false;
	}

	private void skipWhiteSpace(final String lineBuffer, MutableInt linePos) {
		while ((linePos.intValue() < lineBuffer.length() - 1) && isWhiteSpace(lineBuffer.charAt(linePos.intValue())))
			linePos.increment();
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

	private String getToken(final String lineBuffer, MutableInt linePos) {
		MutableInt mLinePos;
		_lineBuffer = lineBuffer;
		_linePos = linePos.intValue();

		_result = "";  // if it doesn't find anything, return null string

		_cmdBufLength = _lineBuffer.length();
		if (_linePos < _cmdBufLength) {

			/* Handle quotes and parentheses around tokens */
			isQuotedString = false;
			if (_isBeginQuote(_lineBuffer.charAt(_linePos))) {
				_parseToEndQuote();
			} else {  /* Copy to next delimiter or whitespace */
				_tokenStart = _linePos;
				mLinePos = new MutableInt(_linePos);
				while ((mLinePos.intValue() < _cmdBufLength) && (!isDelimiter(_lineBuffer, mLinePos)))
					mLinePos.increment();
				_linePos = mLinePos.intValue();

				_result = _lineBuffer.substring(_tokenStart, _linePos);
			}

			/* Check for stop on comment */

			// if stop on comment, ignore rest of line.
			if (lastDelimiter == CommentChar) {
				_linePos = _lineBuffer.length() + 1;
			} else {

				/* Get rid of trailing white space */
				if (lastDelimiter == ' ') {
					mLinePos = new MutableInt(_linePos);
					skipWhiteSpace(_lineBuffer, mLinePos);
					_linePos = mLinePos.intValue();
				}
				if (isDelimChar(_lineBuffer.charAt(_linePos))) {
					lastDelimiter = _lineBuffer.charAt(_linePos);
					_linePos += 1;  // move past terminating delimiter
				}
				mLinePos = new MutableInt(_linePos);
				skipWhiteSpace(_lineBuffer, mLinePos);
				_linePos = mLinePos.intValue();
			}
		}

		linePos.setValue(_linePos);

		return _result;
	}

	public String getNextParam() {
		MutableInt mPosition;

		if (position < cmdBuffer.length()) {
			mPosition = new MutableInt(position);  // pass by reference
			lastDelimiter = ' ';
			// get entire token and put in token buffer
			tokenBuffer = getToken(cmdBuffer, mPosition);
			if (lastDelimiter == '=') {
				parameterBuffer = tokenBuffer;
				tokenBuffer = getToken(cmdBuffer, mPosition);
			} else {
				// init to null string
				parameterBuffer = "";
			}
			position = mPosition.intValue();
		} else {
			parameterBuffer = "";
			tokenBuffer = "";
		}
		return parameterBuffer;
	}

	/**
	 * Looking for "busName.1.2.3" in the TokenBuffer.
	 * Assumes nodeArray is big enough to hold the numbers.
	 */
	public String parseAsBusName(MutableInt numNodes, int[] nodeArray) {
		int dotpos;
		String nodeBuffer, delimSave, tokenSave, result;
		MutableInt nodeBufferPos;

		if (autoIncrement)
			getNextParam();

		numNodes.setValue(0);
		dotpos = tokenBuffer.indexOf('.');
		if (dotpos == -1) {
			return tokenBuffer;
		} else {
			result = tokenBuffer.substring(0, dotpos).trim();  // bus name  TODO Check zero based indexing
			tokenSave = tokenBuffer;
			/* Now get nodes */
			nodeBuffer = tokenBuffer.substring(dotpos, tokenBuffer.length() - dotpos) + " ";

			nodeBufferPos = new MutableInt(0);
			delimSave = delimChars;
			delimChars = ".";
			tokenBuffer = getToken(nodeBuffer, nodeBufferPos);
			try {
				while (tokenBuffer.length() > 0) {
					numNodes.increment();
					nodeArray[numNodes.intValue()] = makeInteger();
					if (convertError)
						nodeArray[numNodes.intValue()] = -1;  // indicate an error
					tokenBuffer = getToken(nodeBuffer, nodeBufferPos);
				}
			} catch (Exception e) {
				DSSGlobals.DSSForms.messageDlg("Node buffer too small: " + e.getMessage(), true);
			}

			delimChars = delimSave;  // restore to original delimiters
			tokenBuffer = tokenSave;
		}
		return result;
	}

	public int parseAsVector(int expectedSize, double[] vectorBuffer) {
		int numElements, i;
		String parseBuffer = null, delimSave = null;
		MutableInt parseBufferPos = new MutableInt();

		if (autoIncrement)
			getNextParam();

		numElements = 0;
		int result = 0;  // return 0 if none found or error occurred
		try {
			for (i = 0; i < expectedSize; i++)
				vectorBuffer[i] = 0.0;

			/* now get vector values */
			parseBuffer = tokenBuffer + " ";

			parseBufferPos.setValue(0);
			delimSave  = delimChars;
			delimChars = delimChars + matrixRowTerminator;

			skipWhiteSpace(parseBuffer, parseBufferPos);
			tokenBuffer = getToken(parseBuffer, parseBufferPos);
			while (tokenBuffer.length() > 0) {
				numElements += 1;
				if (numElements <= expectedSize)
					vectorBuffer[numElements] = makeDouble();
				if (lastDelimiter == matrixRowTerminator)
					break;
				tokenBuffer = getToken(parseBuffer, parseBufferPos);
			}

			result = numElements;
		} catch (Exception e) {
			DSSGlobals.DSSForms.messageDlg("Vector buffer in parseAsVector probably too small: " + e.getMessage(), true);
		}

		delimChars  = delimSave;  // restore to original delimiters
		tokenBuffer = parseBuffer.substring(parseBufferPos.intValue(), parseBuffer.length());  // prepare for next trip

		return result;
	}

	public int parseAsMatrix(int expectedOrder, double[] matrixBuffer) {
		int i, j, k, elementsFound;
		double[] rowBuf;

		if (autoIncrement)
			getNextParam();

		rowBuf = null;

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
			DSSGlobals.DSSForms.messageDlg("Matrix buffer in parseAsMatrix probably too small: " + e.getMessage(), true);
		}

		if (rowBuf != null)
			rowBuf = null;

		return expectedOrder;
	}

	private int _elementIndex(int ii, int jj, int expectedOrder) {
		return (jj - 1) * expectedOrder + ii;  // TODO Check zero based indexing
	}

	public int parseAsSymMatrix(int expectedOrder, double[] matrixBuffer) {
		int i, j, elementsFound;
		double[] rowBuf;

		if (autoIncrement)
			getNextParam();

		rowBuf = null;

		try {
			rowBuf = new double[expectedOrder];

			for (i = 0; i < expectedOrder * expectedOrder; i++)
				matrixBuffer[i] = 0.0;

			for (i = 0; i < expectedOrder; i++) {
				elementsFound = parseAsVector(expectedOrder, rowBuf);

				/* Returns matrix in Column Order (Fortran order) */
				for (j = 0; j < elementsFound; j++) {
					matrixBuffer[_elementIndex(i, j, expectedOrder)] = rowBuf[j];
					if (i != j)
						matrixBuffer[_elementIndex(j, i, expectedOrder)] = rowBuf[j];
				}

			}

		} catch (Exception e) {
			DSSGlobals.DSSForms.messageDlg("Matrix buffer in parseAsSymMatrix() probably too small: " + e.getMessage(), true);
		}

		if (rowBuf != null)
			rowBuf = null;

		return expectedOrder;
	}

	public String makeString() {
		if (autoIncrement)
			getNextParam();

		return tokenBuffer;
	}

	public int makeInteger() {
		// Hex integers must be preceeded by "$"
		int result = 0;
		double temp = 0;
		MutableInt code = new MutableInt();

		convertError = false;
		if (autoIncrement)
			getNextParam();

		if (tokenBuffer.length() == 0) {
			result = 0;
		} else {
			if (isQuotedString) {
				temp = interpretRPNString(code);
				result = (int) Math.round(temp);
			} else {
				try {
					result = Integer.valueOf(tokenBuffer);  // try direct conversion to integer
					code.setValue(1);
				} catch (NumberFormatException e) {
					code.setValue(0);
				}
			}

			if (code.intValue() != 0) {  // on error for integer conversion
				// try again with an double result in case value specified in decimal or some other technique
				try {
					temp = Double.valueOf(tokenBuffer);
					code.setValue(1);
				} catch (NumberFormatException e) {
					code.setValue(0);
				}
				if (code.intValue() != 0) {
					// not needed with throw ...  Result = 0;
					convertError = true;
//					throw new ParserProblem("Integer number conversion error for string: \""+TokenBuffer+"\"");
					DSSGlobals.doErrorMsg("", "Integer number conversion error for string: \""+tokenBuffer+"\"", "", 0);
				} else {
					result = (int) Math.round(temp);
				}
			}
		}

		return result;
	}

	public double makeDouble() {
		MutableInt code = new MutableInt();
		double result = 0;

		if (autoIncrement)
			getNextParam();
		convertError = false;
		if (tokenBuffer.length() == 0) {
			result = 0.0;
		} else {
			if (isQuotedString) {
				result = interpretRPNString(code);
			} else {
				try {
					result = Double.valueOf(tokenBuffer);
					code.setValue(1);
				} catch (NumberFormatException e) {
					code.setValue(0);
				}
			}

			if (code.intValue() != 0) {
				// not needed with throw ... Result = 0.0;
				convertError = true;
//				throw new ParserProblem("Floating point number conversion error for string: \""+TokenBuffer+"\"");
				DSSGlobals.doErrorMsg("", "Floating point number conversion error for string: \""+tokenBuffer+"\"", "", 0);
			}
		}

		return result;
	}

	public String getRemainder() {
		return cmdBuffer.substring(position, cmdBuffer.length());
	}

	/**
	 * Checks for CommentChar and '//'.
	 */
	private boolean isCommentChar(final String lineBuffer, MutableInt linePos) {
		switch (lineBuffer.charAt(linePos.intValue())) {
		case CommentChar:
			return true;
		case '/':
			if ((lineBuffer.length() - 1 > linePos.intValue()) && (lineBuffer.charAt(linePos.intValue() + 1) == '/')) {
				return true;
			} else {
				return false;
			}
		default:
			return false;
		}
	}

	private double interpretRPNString(MutableInt code) {
		MutableInt parseBufferPos;
		String parseBuffer;

		code.setValue(0);
		parseBuffer = tokenBuffer + " ";
		parseBufferPos = new MutableInt(0);

		skipWhiteSpace(parseBuffer, parseBufferPos);
		tokenBuffer = getToken(parseBuffer, parseBufferPos);

		while (tokenBuffer.length() > 0) {

			try {
				code.setValue( processRPNCommand(tokenBuffer, RPNCalculator) );
			} catch (ParserProblem e) {
				DSSGlobals.doErrorMsg("", e.getMessage(), "", 0);
				if (code.intValue() > 0)
					break;  // stop on any floating point error
			}

			tokenBuffer = getToken(parseBuffer, parseBufferPos);
		}

		double result = RPNCalculator.getX();

		// prepare for next trip
		tokenBuffer = parseBuffer.substring(parseBufferPos.intValue(), parseBuffer.length());

		return result;
	}

	public String getDelimChars() {
		return delimChars;
	}

	public void setDelimChars(String chars) {
		delimChars = chars;
	}

	public String getWhiteSpaceChars() {
		return whiteSpaceChars;
	}

	public void setWhiteSpaceChars(String chars) {
		whiteSpaceChars = chars;
	}

	public String getBeginQuoteChars() {
		return beginQuoteChars;
	}

	public void setBeginQuoteChars(String chars) {
		beginQuoteChars = chars;
	}

	public String getEndQuoteChars() {
		return endQuoteChars;
	}

	public void setEndQuoteChars(String chars) {
		endQuoteChars = chars;
	}

	public boolean isAutoIncrement() {
		return autoIncrement;
	}

	public void setAutoIncrement(boolean auto) {
		autoIncrement = auto;
	}

	public String getCmdString() {
		return cmdBuffer;
	}

	public String getToken() {
		return tokenBuffer;
	}

	public void setToken(String Value) {
		tokenBuffer = Value;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
