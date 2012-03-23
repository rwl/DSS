/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.common;

import java.util.List;
import java.util.Scanner;

import com.ncond.dss.executive.ExecCommands;

import lombok.extern.java.Log;

@Log
public class CommandLineDSSForms implements DSSForms {

	private static Scanner sc = new Scanner(System.in);

//	private boolean controlPanelCreated;
//	private static ControlPanel controlPanel;

	private boolean rebuildHelpForm;

	private CommandLineDSSForms() {

	}

	private static class DSSFormsHolder {
		public static final CommandLineDSSForms INSTANCE = new CommandLineDSSForms();
	}

	public static CommandLineDSSForms getInstance() {
		return DSSFormsHolder.INSTANCE;
	}

	public void createControlPanel() {

	}

	public void exitControlPanel() {

	}

	public void initProgressForm() {

	}

	public void progressCaption(String s) {

	}

	public void progressFormCaption(String s) {

	}

	public void progressHide() {

	}

	public void showControlPanel() {

	}

	public void showHelpForm(String cmd) {
		int idx;
		String help;

		if (cmd == null || cmd.length() == 0) {
			showHelpForm();
			return;
		}

		idx = ExecCommands.commandList.getCommand(cmd);
		if (idx >= 0) {
			help = ExecCommands.commandHelp[idx];
			log.info(help);
		} else {
			log.warning("Command not found: " + cmd);
		}
	}

	public void showHelpForm() {
		int i, dotpos;
		String cmd, help;

		for (i = 0; i < ExecCommands.NumExecCommands; i++) {
			cmd = ExecCommands.execCommand[i];
			if (cmd.charAt(0) != '_') {
				help = ExecCommands.commandHelp[i];
				dotpos = help.indexOf('.');
				if (dotpos >= 0) help = help.substring(0, dotpos + 1);
				log.info("* " + cmd + " - " + help);
			}
		}
	}

	public void showAboutBox() {

	}

	public void showPropEditForm() {

	}

	public void showPctProgress(int count) {

	}

	public void showMessageForm(List<String> s) {

	}

	public int messageDlg(String msg, boolean err) {
		int result;
		if (err) {
			log.warning(msg);
			result = 0;
		} else {
			while (true) {
				String answer;

				log.warning(msg);
				log.warning("\nContinue (y/n)?: ");

				answer = sc.next();
				if (answer.equalsIgnoreCase("n")) {
					result = -1;
					break;
				} else if (answer.equalsIgnoreCase("y") || answer.length() == 0) {
					result = 0;
					break;
				}
			}
		}
		return result;
	}

	public void infoMessageDlg(String msg) {
		log.info(msg);
	}

	public String getDSSExeFile() {
		return null;
	}

	public void closeDownForms() {

	}

	public void showTreeView(String fname) {

	}

	public boolean makeChannelSelection(int numFieldsToSkip, String filename) {
		return false;
	}

	public boolean isRebuildHelpForm() {
		return rebuildHelpForm;
	}

	public void setRebuildHelpForm(boolean rebuild) {
		rebuildHelpForm = rebuild;
	}

}
