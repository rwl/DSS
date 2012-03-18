/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.common;

import java.util.List;

import javax.swing.JOptionPane;


public class SwingDSSForms implements DSSForms {

//	private static boolean controlPanelCreated;
//	private static ControlPanel controlPanel;

	private static boolean rebuildHelpForm;

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
