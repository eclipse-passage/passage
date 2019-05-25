/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.internal.workbench.i18n;

import org.eclipse.osgi.util.NLS;

public class WorkbenchMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages"; //$NON-NLS-1$
	public static String CreateFileWizard_q_exists_message;
	public static String CreateFileWizard_q_exists_title;
	public static String CreateFileWizardPage_button_browse;
	public static String CreateFileWizardPage_e_specify_identifier;
	public static String CreateFileWizardPage_e_specify_name;
	public static String CreateFileWizardPage_e_specify_path;
	public static String CreateFileWizardPage_label_file;
	public static String CreateFileWizardPage_label_identifier;
	public static String CreateFileWizardPage_label_name;
	public static String ExitWorkbenchHandler_exit_message;
	public static String ExitWorkbenchHandler_exit_title;
	public static String FilteredSelectionDialog_filtering_label;
	public static String FilteredSelectionDialog_items_label;
	public static String LicensingStatusToolControl_text_undefined;
	public static String LocWokbench_e_saving;
	public static String RedoHandler_label_base;
	public static String RedoHandler_label_pattern;
	public static String UndoHandler_label_base;
	public static String UndoHandler_label_handler;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, WorkbenchMessages.class);
	}

	private WorkbenchMessages() {
	}
}
