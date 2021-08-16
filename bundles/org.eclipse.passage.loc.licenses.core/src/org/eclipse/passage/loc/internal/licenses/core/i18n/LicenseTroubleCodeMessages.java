/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.loc.internal.licenses.core.i18n;

import org.eclipse.osgi.util.NLS;

public final class LicenseTroubleCodeMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.loc.internal.licenses.core.i18n.LicenseTroubleCodeMessages"; //$NON-NLS-1$

	public static String LicenseValidationFailed_explanation;
	public static String LicenseIssuingFailed_explanation;
	public static String LicenseIssuingIsPartial_explanation;
	public static String LicenseAgreementAttachFailed_explanation;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, LicenseTroubleCodeMessages.class);
	}

	private LicenseTroubleCodeMessages() {
	}
}
