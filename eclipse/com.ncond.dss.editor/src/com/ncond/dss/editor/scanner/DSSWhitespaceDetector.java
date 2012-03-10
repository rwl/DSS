package com.ncond.dss.editor.scanner;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class DSSWhitespaceDetector implements IWhitespaceDetector {

	public boolean isWhitespace(char c) {
//		return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
		return Character.isWhitespace(c);
	}

}
