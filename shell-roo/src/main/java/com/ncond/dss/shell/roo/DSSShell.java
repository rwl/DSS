package com.ncond.dss.shell.roo;

import java.net.URL;
import java.util.Collection;

import org.springframework.roo.shell.ExecutionStrategy;
import org.springframework.roo.shell.Parser;
import org.springframework.roo.shell.jline.DSSJLineShell;

import static org.apache.commons.io.IOUtils.LINE_SEPARATOR;

import com.ncond.dss.common.DSS;
import com.ncond.dss.general.DSSObject;

public class DSSShell extends DSSJLineShell {

	@Override
	protected Collection<URL> findResources(String arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected ExecutionStrategy getExecutionStrategy() {
		return new DSSExecutionStrategy();
	}

	@Override
	protected Parser getParser() {
		return new DSSParser();
	}

	@Override
	public String version(String extra) {
		StringBuilder sb = new StringBuilder();

		if ("dss".equals(extra)) {
		        sb.append(" _  _  _  _       _  _  _  _      _  _  _  _     ").append(LINE_SEPARATOR);
		        sb.append("(_)(_)(_)(_)    _(_)(_)(_)(_)_  _(_)(_)(_)(_)_   ").append(LINE_SEPARATOR);
		        sb.append(" (_)      (_)_ (_)          (_)(_)          (_)  ").append(LINE_SEPARATOR);
		        sb.append(" (_)        (_)(_)_  _  _  _   (_)_  _  _  _     ").append(LINE_SEPARATOR);
		        sb.append(" (_)        (_)  (_)(_)(_)(_)_   (_)(_)(_)(_)_   ").append(LINE_SEPARATOR);
		        sb.append(" (_)       _(_) _           (_) _           (_)  ").append(LINE_SEPARATOR);
		        sb.append(" (_)      (_)_ (_)          (_)(_)          (_)  ").append(LINE_SEPARATOR);
		        sb.append(" (_)_  _  (_)  (_)_  _  _  _(_)(_)_  _  _  _(_)  ").append(LINE_SEPARATOR);
		        sb.append("(_)(_)(_)(_)     (_)(_)(_)(_)    (_)(_)(_)(_)    ").append(versionInfo());
		        sb.append(LINE_SEPARATOR);
		} else {
		        sb.append("   ___  ________").append(LINE_SEPARATOR);
		        sb.append("  / _ \\/ __/ __/").append(LINE_SEPARATOR);
		        sb.append(" / // /\\ \\_\\ \\  ").append(LINE_SEPARATOR);
		        sb.append("/____/___/___/   ").append(versionInfo())
		                .append(LINE_SEPARATOR);
		}

	        return sb.toString();
	}


	public boolean executeCommand(final String line) {
		boolean success = super.executeCommand(line);
		DSSObject obj = DSS.activeDSSObject;
		if (obj != null)
			setPromptPath(obj.getDSSClassName() + "." + obj.getName());
		return success;
	}

	public static void main(String[] args) {
		new DSSShell().run();
	}

}
