package com.ncond.dss.editor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import com.ncond.dss.editor.scanner.DSSPartitionScanner;

public class DSSConfiguration extends SourceViewerConfiguration {

	private RuleBasedScanner scanner;
	private RuleBasedScanner commentScanner;
	private RuleBasedScanner inlineCommentScanner;
	private ColorManager colorManager;

	public DSSConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}

	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			DSSPartitionScanner.DSS_COMMENT,
			DSSPartitionScanner.DSS_INLINE_COMMENT
		};
	}

	protected RuleBasedScanner getDSSScanner() {
		if (scanner == null) {
			scanner = new RuleBasedScanner();
			scanner.setDefaultReturnToken(new Token(
				new TextAttribute(colorManager.getColor(IDSSColorConstants.DEFAULT)))
			);
		}
		return scanner;
	}

	protected RuleBasedScanner getDSSCommentScanner() {
		if (commentScanner == null) {
			commentScanner = new RuleBasedScanner();
			commentScanner.setDefaultReturnToken(new Token(
				new TextAttribute(colorManager.getColor(IDSSColorConstants.COMMENT)))
			);
		}
		return commentScanner;
	}

	protected RuleBasedScanner getDSSInlineCommentScanner() {
		if (inlineCommentScanner == null) {
			inlineCommentScanner = new RuleBasedScanner();
			inlineCommentScanner.setDefaultReturnToken(new Token(
				new TextAttribute(colorManager.getColor(IDSSColorConstants.INLINE_COMMENT)))
			);
		}
		return inlineCommentScanner;
	}

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getDSSCommentScanner());
		reconciler.setDamager(dr, DSSPartitionScanner.DSS_COMMENT);
		reconciler.setRepairer(dr, DSSPartitionScanner.DSS_COMMENT);

		dr = new DefaultDamagerRepairer(getDSSInlineCommentScanner());
		reconciler.setDamager(dr, DSSPartitionScanner.DSS_INLINE_COMMENT);
		reconciler.setRepairer(dr, DSSPartitionScanner.DSS_INLINE_COMMENT);

		dr = new DefaultDamagerRepairer(getDSSScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		return reconciler;
	}

}
