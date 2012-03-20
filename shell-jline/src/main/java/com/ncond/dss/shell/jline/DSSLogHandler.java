package com.ncond.dss.shell.jline;

import jline.ANSIBuffer;
import jline.ConsoleReader;

import org.apache.commons.lang3.SystemUtils;
import org.springframework.roo.shell.ShellPromptAccessor;
import org.springframework.roo.shell.jline.JLineLogHandler;

public class DSSLogHandler extends JLineLogHandler {

	    private static final boolean BRIGHT_COLORS = Boolean
	            .getBoolean("roo.bright");

	public DSSLogHandler(ConsoleReader reader,
			ShellPromptAccessor shellPromptAccessor) {
		super(reader, shellPromptAccessor);
		// TODO Auto-generated constructor stub
	}


	    /**
	     * Makes text brighter if requested through system property 'roo.bright' and
	     * works around issue on Windows in using reverse() in combination with the
	     * Jansi lib, which leaves its 'negative' flag set unless reset explicitly.
	     *
	     * @return new patched ANSIBuffer
	     */
	    static ANSIBuffer getANSIBuffer() {
	        final char esc = (char) 27;
	        return new ANSIBuffer() {
	            @Override
	            public ANSIBuffer attrib(final String str, final int code) {
	                if (BRIGHT_COLORS && 30 <= code && code <= 37) {
	                    // This is a color code: add a 'bright' code
	                    return append(esc + "[" + code + ";1m").append(str).append(
	                            ANSICodes.attrib(0));
	                }
	                return super.attrib(str, code);
	            };

	            @Override
	            public ANSIBuffer reverse(final String str) {
	                if (SystemUtils.IS_OS_WINDOWS) {
	                    return super.reverse(str).append(ANSICodes.attrib(esc));
	                }
	                return super.reverse(str);
	            }
	        };
	    }
}
