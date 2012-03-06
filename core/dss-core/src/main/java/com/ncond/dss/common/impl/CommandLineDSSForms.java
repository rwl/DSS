package com.ncond.dss.common.impl;

import java.util.List;
import java.util.Scanner;

import com.ncond.dss.common.DSSForms;

public class CommandLineDSSForms implements DSSForms {

	private static Scanner sc = new Scanner(System.in);

	private boolean controlPanelCreated;
//	private static ControlPanel ControlPanel;

	private boolean rebuildHelpForm;

	private CommandLineDSSForms() {

	}

	private static class DSSFormsHolder {
		public static final CommandLineDSSForms INSTANCE = new CommandLineDSSForms();
	}

	public static CommandLineDSSForms getInstance() {
		return DSSFormsHolder.INSTANCE;
	}

	@Override
	public void createControlPanel() {

	}

	@Override
	public void exitControlPanel() {

	}

	@Override
	public void initProgressForm() {

	}

	@Override
	public void progressCaption(String s) {

	}

	@Override
	public void progressFormCaption(String s) {

	}

	@Override
	public void progressHide() {

	}

	@Override
	public void showControlPanel() {

	}

	@Override
	public void showHelpForm() {

	}

	@Override
	public void showAboutBox() {

	}

	@Override
	public void showPropEditForm() {

	}

	@Override
	public void showPctProgress(int count) {

	}

	@Override
	public void showMessageForm(List<String> s) {

	}

	@Override
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

	@Override
	public void infoMessageDlg(String msg) {
		System.out.println(msg);
	}

	@Override
	public String getDSSExeFile() {
		return null;
	}

	@Override
	public void closeDownForms() {

	}

	@Override
	public void showTreeView(String fname) {

	}

	@Override
	public boolean makeChannelSelection(int numFieldsToSkip, String filename) {
		return false;
	}

	@Override
	public boolean isRebuildHelpForm() {
		return rebuildHelpForm;
	}

	@Override
	public void setRebuildHelpForm(boolean rebuild) {
		rebuildHelpForm = rebuild;
	}

}
