package com.epri.dss.forms;

import javax.swing.JOptionPane;

public class DSSForms {
	
	private DSSForms() {}

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

}
