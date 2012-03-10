package com.ncond.dss.editor.scanner;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.*;

public class DSSPartitionScanner extends RuleBasedPartitionScanner {

	public final static String DSS_COMMENT = "__dss_comment";
	public final static String DSS_INLINE_COMMENT = "__dss_inline_comment";
	public final static String DSS_ARRAY = "__dss_array";

	public DSSPartitionScanner() {

		IToken comment = new Token(DSS_COMMENT);
//		IToken array = new Token(DSS_ARRAY);
		IToken inlineComment = new Token(DSS_INLINE_COMMENT);

		List<IPredicateRule> rules = new ArrayList<IPredicateRule>();

		rules.add( new EndOfLineRule("//", comment) );
		rules.add( new EndOfLineRule("!", inlineComment) );

//		rules.add( new SingleLineRule("[", "]", array) );
//		rules.add( new SingleLineRule("{", "}", array) );
//		rules.add( new SingleLineRule("(", ")", array) );
//		rules.add( new SingleLineRule("\"", "\"", array) );
//		rules.add( new SingleLineRule("'", "'", array) );

	        IPredicateRule[] result = new IPredicateRule[rules.size()];
	        rules.toArray(result);
		setPredicateRules(result);
	}

	public class DSSWordDetector implements IWordDetector {
		public boolean isWordPart(char character) {
			return Character.isJavaIdentifierPart(character);
		}
		public boolean isWordStart(char character) {
			return Character.isJavaIdentifierStart(character);
		}
	}

}
