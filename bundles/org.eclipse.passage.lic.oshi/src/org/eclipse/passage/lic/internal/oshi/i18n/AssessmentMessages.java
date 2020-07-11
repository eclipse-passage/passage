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
package org.eclipse.passage.lic.internal.oshi.i18n;

import org.eclipse.osgi.util.NLS;

public class AssessmentMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.internal.oshi.i18n.AssessmentMessages"; //$NON-NLS-1$
	public static String HardwareAssessmentService_error_on_assessment;
	public static String HardwareAssessmentService_no_hw_inspector;
	public static String State_error_reading_hw;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, AssessmentMessages.class);
	}

	private AssessmentMessages() {
	}
}
