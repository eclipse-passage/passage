/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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

public class AgreementsDialogMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.internal.jface.i18n.AgreementsDialogMessages"; //$NON-NLS-1$
	public static String AgreementPage_accept;
	public static String AgreementPage_title;
	public static String AgreementsWizard_description;
	public static String AgreementsWizard_error;
	public static String AgreementsWizard_error_description;
	public static String AgreementsWizard_failure;
	public static String AgreementsWizard_failure_description;
	public static String AgreementsWizardDialog_title;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, AgreementsDialogMessages.class);
	}

	private AgreementsDialogMessages() {
	}
}
