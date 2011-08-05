package com.epri.dss.common;

import java.util.List;

public interface DSSForms {

	void createControlPanel();

	void exitControlPanel();

	void initProgressForm();

	void progressCaption(String S);

	void progressFormCaption(String S);

	void progressHide();

	void showControlPanel();

	void showHelpForm();

	void showAboutBox();

	void showPropEditForm();

	void showPctProgress(int Count);

	void showMessageForm(List<String> S);

	int messageDlg(String msg, boolean err);

	void infoMessageDlg(String msg);

	String getDSSExeFile();

	void closeDownForms();

	void showTreeView(String Fname);

	boolean makeChannelSelection(int NumFieldsToSkip, String Filename);

	boolean isRebuildHelpForm();

	void setRebuildHelpForm(boolean rebuildHelpForm);

}
