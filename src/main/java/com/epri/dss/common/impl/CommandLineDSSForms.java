package com.epri.dss.common.impl;

import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.epri.dss.common.DSSForms;

public class CommandLineDSSForms implements DSSForms {

	private static Scanner sc = new Scanner(System.in);

	private boolean ControlPanelCreated;
//	private static ControlPanel ControlPanel;

	private boolean RebuildHelpForm;

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

	public void progressCaption(String S) {

	}

	public void progressFormCaption(String S) {

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

	public void showPctProgress(int Count) {

	}

	public void showMessageForm(List<String> S) {

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

	public void showTreeView(String Fname) {

	}

	public boolean makeChannelSelection(int NumFieldsToSkip, String Filename) {
		return false;
	}

	public boolean isRebuildHelpForm() {
		return RebuildHelpForm;
	}

	public void setRebuildHelpForm(boolean rebuildHelpForm) {
		RebuildHelpForm = rebuildHelpForm;
	}

}
