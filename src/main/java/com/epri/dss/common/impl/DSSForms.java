package com.epri.dss.common.impl;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DSSForms {

	private static boolean ControlPanelCreated;
//	private static ControlPanel ControlPanel;

	private static boolean RebuildHelpForm;

	private DSSForms() {}

	public static void createControlPanel() {

	}

	public static void exitControlPanel() {

	}

	public static void initProgressForm() {

	}

	public static void progressCaption(String S) {

	}

	public static void progressFormCaption(String S) {

	}

	public static void progressHide() {

	}

	public static void showControlPanel() {

	}

	public static void showHelpForm() {

	}

	public static void showAboutBox() {

	}

	public static void showPropEditForm() {

	}

	public static void showPctProgress(int Count) {

	}

	public static void showMessageForm(ArrayList<String> S) {

	}

	public static int messageDlg(String msg, boolean err) {
		int Result;
		if (err) {
			JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
			Result = 0;
		} else {
			Object[] options = {"Abort", "Ignore"};

			int idx = JOptionPane.showOptionDialog(null, msg, "Warning",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);

			Result = (idx == 0 || idx == JOptionPane.CLOSED_OPTION) ? -1 : 0;
		}
		return Result;
	}

	public static void infoMessageDlg(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Information", JOptionPane.INFORMATION_MESSAGE);
	}

	public static String getDSSExeFile() {
		return null;
	}

	public static void closeDownForms() {

	}

	public static void showTreeView(String Fname) {

	}

	public static boolean makeChannelSelection(int NumFieldsToSkip, String Filename) {
		return false;
	}

	public static boolean isRebuildHelpForm() {
		return RebuildHelpForm;
	}

	public static void setRebuildHelpForm(boolean rebuildHelpForm) {
		RebuildHelpForm = rebuildHelpForm;
	}

}
