package com.ncond.dss.common.impl;

import java.util.List;

import javax.swing.JOptionPane;

import com.ncond.dss.common.DSSForms;

public class SwingDSSForms implements DSSForms {

	private static boolean controlPanelCreated;
//	private static ControlPanel ControlPanel;

	private static boolean rebuildHelpForm;

	private SwingDSSForms() {

	}

	private static class DSSFormsHolder {
		public static final SwingDSSForms INSTANCE = new SwingDSSForms();
	}

	public static SwingDSSForms getInstance() {
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

	@Override
	public void infoMessageDlg(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Information", JOptionPane.INFORMATION_MESSAGE);
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
