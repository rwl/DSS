package com.ncond.edss.editor;

import org.eclipse.ui.texteditor.AbstractTextEditor;

public class DSSEditor extends AbstractTextEditor {

	public DSSEditor() {
		super();
		setSourceViewerConfiguration(new DSSConfiguration());
		setDocumentProvider(new DSSDocumentProvider());
	}

	protected void createActions() {
		super.createActions();
		//... add other editor actions here
	}

}
