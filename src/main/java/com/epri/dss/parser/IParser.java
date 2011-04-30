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

	void setCmdString(String Value);

	String getCmdString();

	String makeString();

	int makeInteger();

	double makeDouble();

	String getNextParam();

	String getToken();

	void setToken(String Value);

	String parseAsBusName(int NumNodes, int[] NodeArray);

	int parseAsVector(int ExpectedSize, double[] VectorBuffer);

	int parseAsMatrix(int ExpectedOrder, double[] MatrixBuffer);

	int parseAsSymMatrix(int ExpectedOrder, double[] MatrixBuffer);

	/* resets delimiters to default */
	void resetDelims();

}
