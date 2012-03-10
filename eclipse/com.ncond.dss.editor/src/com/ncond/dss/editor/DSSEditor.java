package com.ncond.dss.editor;

import org.eclipse.ui.editors.text.TextEditor;

public class DSSEditor extends TextEditor {

	private ColorManager colorManager;

	public DSSEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new DSSConfiguration(colorManager));
		setDocumentProvider(new DSSDocumentProvider());
	}

	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
