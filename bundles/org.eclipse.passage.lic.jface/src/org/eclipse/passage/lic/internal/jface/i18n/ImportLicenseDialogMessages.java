package org.eclipse.passage.lic.internal.jface.i18n;

import org.eclipse.osgi.util.NLS;

public class ImportLicenseDialogMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.internal.jface.i18n.ImportLicenseDialogMessages"; //$NON-NLS-1$
	public static String ImportLicenseDialog_browse_dialog_title;
	public static String ImportLicenseDialog_column_evaluation;
	public static String ImportLicenseDialog_column_feature;
	public static String ImportLicenseDialog_column_period;
	public static String ImportLicenseDialog_path_label;
	public static String ImportLicenseDialog_prelude;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, ImportLicenseDialogMessages.class);
	}

	private ImportLicenseDialogMessages() {
	}
}
