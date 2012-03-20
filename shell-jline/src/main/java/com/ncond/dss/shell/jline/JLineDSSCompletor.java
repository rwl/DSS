package com.ncond.dss.shell.jline;

import java.util.ArrayList;
import java.util.List;

import org.springframework.roo.shell.Completion;
import org.springframework.roo.shell.Parser;

import jline.Completor;

import com.ncond.dss.shell.DSSCompletor;

public class JLineDSSCompletor implements Completor {

	    private final Parser parser;

	    public JLineDSSCompletor(final Parser parser) {
	        this.parser = parser;
	    }

	    @SuppressWarnings("all")
	    public int complete(final String buffer, final int cursor,
	            final List candidates) {
	        int result;
	        try {
	            DSSLogHandler.cancelRedrawProhibition();
//	            final List<Completion> completions = new ArrayList<Completion>();
//	            result = parser.completeAdvanced(buffer, cursor, completions);
//	            for (final Completion completion : completions) {
//	                candidates
//	                        .add(new jline.Completion(completion.getValue(),
//	                                completion.getFormattedValue(), completion
//	                                        .getHeading()));
//	            }
	            result = parser.complete(buffer, cursor, candidates);
	        }
	        finally {
	            DSSLogHandler.prohibitRedraw();
	        }
	        return result;
	    }

}
