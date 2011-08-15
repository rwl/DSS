package com.epri.dss.parser;

public interface IParser {

	String getDelimChars();

	void setDelimChars(String delimChars);

	String getWhiteSpaceChars();

	void setWhiteSpaceChars(String whiteSpaceChars);

	String getBeginQuoteChars();

	void setBeginQuoteChars(String beginQuoteChars);

	String getEndQuoteChars();

	void setEndQuoteChars(String endQuoteChars);

	boolean isAutoIncrement();

	void setAutoIncrement(boolean autoIncrement);

	String getRemainder();

	void setCmdString(String value);

	String getCmdString();

	String makeString();

	int makeInteger();

	double makeDouble();

	String getNextParam();

	String getToken();

	void setToken(String value);

	String parseAsBusName(int numNodes, int[] nodeArray);

	int parseAsVector(int expectedSize, double[] vectorBuffer);

	int parseAsMatrix(int expectedOrder, double[] matrixBuffer);

	int parseAsSymMatrix(int expectedOrder, double[] matrixBuffer);

	/* resets delimiters to default */
	void resetDelims();

}
