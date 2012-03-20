package com.ncond.dss.shell.jline;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.roo.shell.Completion;
import org.springframework.roo.shell.ParseResult;
import org.springframework.roo.shell.Parser;

import com.ncond.dss.executive.Executive;
import com.ncond.dss.shell.DSSCompletor;

public class BasicParser implements Parser {

	DSSCompletor completor;

	private Executive executive;

	public BasicParser() {
		completor = new DSSCompletor();
		executive = Executive.getInstance();
		//executive.createDefaultDSSItems();
	}

	@Override
	public int complete(String buffer, int cursor, List<String> candidates) {
		return completor.complete(buffer, cursor, candidates);
	}

	@Override
	public int completeAdvanced(String buffer, int cursor, List<Completion> completions) {
		final List<String> candidates = new ArrayList<String>();

		int idx = completor.complete(buffer, cursor, candidates);

		for (String cand : candidates)
			completions.add(new Completion(cand));

		return idx;
	}

	@Override
	public ParseResult parse(String line) {
		Method method;
		try {
			method = executive.getClass().getMethod("setCommand", new Class[] {String.class});
		} catch (SecurityException e) {
			throw new IllegalStateException("Executive.setCommand method inaccessible");
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException("Executive.setCommand method inaccessible");
		}
		return new ParseResult(method, executive, new String[] {line});
	}

}
