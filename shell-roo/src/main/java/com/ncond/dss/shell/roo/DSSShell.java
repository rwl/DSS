package com.ncond.dss.shell.roo;

import java.net.URL;
import java.util.Collection;

import org.springframework.roo.shell.ExecutionStrategy;
import org.springframework.roo.shell.Parser;
import org.springframework.roo.shell.jline.DSSJLineShell;

import static org.apache.commons.io.IOUtils.LINE_SEPARATOR;

import com.ncond.dss.common.DSS;
import com.ncond.dss.executive.Executive;
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
	public boolean executeCommand(final String line) {
		boolean success = super.executeCommand(line);
		setDSSPromptPath();
		return success;
	}

	@Override
	protected void logCommandIfRequired(final String line,
			final boolean successful) {
		super.logCommandIfRequired(line, successful);

		if (!successful) logger.warning("Error processing command: " + line);
	}

	@Override
	public void run() {
		Executive executive = Executive.getInstance();
		executive.createDefaultDSSItems();
		DSS.makeNewCircuit("ckt1");
		DSS.setObject("source");
		super.run();
	}

	@Override
	public void promptLoop() {
		setDSSPromptPath();
		super.promptLoop();
	}

	private void setDSSPromptPath() {
		DSSObject obj = DSS.activeDSSObject;
		if (obj != null) setPromptPath(obj.getDSSClassName() + "." + obj.getName());
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

	public static void main(String[] args) {
		new DSSShell().run();
	}

}
