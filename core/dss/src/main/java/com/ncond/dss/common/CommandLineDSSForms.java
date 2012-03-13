package com.ncond.dss.common;

import java.util.List;
import java.util.Scanner;


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

	public void showHelpForm() {

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
		int Result;
		if (err) {
			System.err.println(msg);
			Result = 0;
		} else {
			while (true) {
				String answer;

				System.out.println(msg);
				System.out.print("\nEnter \"Ignore\" or \"Abort\" [Ignore]: ");

				answer = sc.next();
				if (answer.equalsIgnoreCase("abort")) {
					Result = -1;
					break;
				} else if (answer.equalsIgnoreCase("ignore") || answer.length() == 0) {
					Result = 0;
					break;
				}
			}
		}
		return Result;
	}

	public void infoMessageDlg(String msg) {
		System.out.println(msg);
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
