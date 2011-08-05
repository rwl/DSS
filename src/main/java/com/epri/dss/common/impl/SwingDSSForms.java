package com.epri.dss.common.impl;

import java.util.List;

import javax.swing.JOptionPane;

import com.epri.dss.common.DSSForms;

public class SwingDSSForms implements DSSForms {

	private static boolean ControlPanelCreated;
//	private static ControlPanel ControlPanel;

	private static boolean RebuildHelpForm;

	private SwingDSSForms() {

	}

	private static class DSSFormsHolder {
		public static final SwingDSSForms INSTANCE = new SwingDSSForms();
	}

	public static SwingDSSForms getInstance() {
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

	public void infoMessageDlg(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Information", JOptionPane.INFORMATION_MESSAGE);
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
