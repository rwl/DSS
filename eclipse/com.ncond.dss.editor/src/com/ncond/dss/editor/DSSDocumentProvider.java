package com.ncond.dss.editor;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.editors.text.FileDocumentProvider;

import com.ncond.dss.editor.scanner.DSSPartitionScanner;

public class DSSDocumentProvider extends FileDocumentProvider {

	protected IDocument createDocument(Object element) throws CoreException {
		IDocument document = super.createDocument(element);
		if (document != null) {
			IDocumentPartitioner partitioner = new FastPartitioner(
				new DSSPartitionScanner(),
				new String[] {
					DSSPartitionScanner.DSS_COMMENT,
					DSSPartitionScanner.DSS_INLINE_COMMENT
				}
			);
			partitioner.connect(document);
			document.setDocumentPartitioner(partitioner);
		}
		return document;
	}

}
