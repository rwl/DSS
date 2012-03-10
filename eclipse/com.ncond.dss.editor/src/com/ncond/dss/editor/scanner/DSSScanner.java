package com.ncond.dss.editor.scanner;

import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.*;

import com.ncond.dss.editor.ColorManager;
import com.ncond.dss.editor.IDSSColorConstants;

public class DSSScanner extends RuleBasedScanner {

	public DSSScanner(ColorManager manager) {
		IToken procInstr = new Token(new TextAttribute(manager.getColor(IDSSColorConstants.COMMENT)));

		IRule[] rules = new IRule[2];
		// add rule for processing instructions
		rules[0] = new SingleLineRule("<?", "?>", procInstr);
		// add generic whitespace rule.
		rules[1] = new WhitespaceRule(new DSSWhitespaceDetector());

		setRules(rules);
	}
}
