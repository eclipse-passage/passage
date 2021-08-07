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
package org.eclipse.passage.loc.internal.agreements.ui.i18n;

import org.eclipse.osgi.util.NLS;

public final class AgreementsUiMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.passage.loc.internal.agreements.ui.i18n.AgreementsUiMessages"; //$NON-NLS-1$

	public static String ExportCustomersHandler_unavailableMessage;
	public static String ExportCustomersHandler_unavailableTitle;
	public static String SelectAgreement_title;
	public static String SelectAgreementGroup_title;
	public static String AgreementsUi_select_agreement;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, AgreementsUiMessages.class);
	}

	private AgreementsUiMessages() {
	}
}
