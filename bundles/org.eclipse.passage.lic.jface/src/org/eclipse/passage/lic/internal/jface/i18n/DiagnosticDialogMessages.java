/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.internal.jface.i18n;

import org.eclipse.osgi.util.NLS;

public class DiagnosticDialogMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.internal.jface.i18n.DiagnosticDialogMessages"; //$NON-NLS-1$
	public static String DiagnosticDialog_action_copy;
	public static String DiagnosticDialog_action_copy_tooltip;
	public static String DiagnosticDialog_action_view_failure;
	public static String DiagnosticDialog_action_view_failure_tooltip;
	public static String DiagnosticDialog_column_code;
	public static String DiagnosticDialog_column_details;
	public static String DiagnosticDialog_column_type;
	public static String DiagnosticDialog_description;
	public static String DiagnosticDialog_error_title;
	public static String DiagnosticDialog_title;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, DiagnosticDialogMessages.class);
	}

	private DiagnosticDialogMessages() {
	}
}
