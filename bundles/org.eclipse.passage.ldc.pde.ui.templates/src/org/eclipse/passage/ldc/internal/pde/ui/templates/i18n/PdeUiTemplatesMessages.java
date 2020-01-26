/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.ldc.internal.pde.ui.templates.i18n;

import org.eclipse.osgi.util.NLS;

public class PdeUiTemplatesMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.ldc.internal.pde.ui.templates.i18n.PdeUiTemplatesMessages"; //$NON-NLS-1$
	public static String LicensedE3ProductTemplateSection_key_application_class_label;
	public static String LicensedE3ProductTemplateSection_key_package_name_label;
	public static String LicensedE3ProductTemplateSection_key_window_title_label;
	public static String LicensedE3ProductTemplateSection_page_description;
	public static String LicensedE3ProductTemplateSection_page_title;
	public static String LicensedE4ProductTemplateSection_key_package_name_label;
	public static String LicensedE4ProductTemplateSection_key_window_title_label;
	public static String LicensedE4ProductTemplateSection_page_description;
	public static String LicensedE4ProductTemplateSection_page_title;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, PdeUiTemplatesMessages.class);
	}

	private PdeUiTemplatesMessages() {
	}
}
