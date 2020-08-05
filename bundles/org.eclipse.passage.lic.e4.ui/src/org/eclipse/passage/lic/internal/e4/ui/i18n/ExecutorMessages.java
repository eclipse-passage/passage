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
package org.eclipse.passage.lic.internal.e4.ui.i18n;

import org.eclipse.osgi.util.NLS;

public class ExecutorMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.internal.e4.ui.i18n.ExecutorMessages"; //$NON-NLS-1$
	public static String WorkbenchRestrictionExecutingService_no_eclipse_context;
	public static String WorkbenchRestrictionExecutingService_no_graded_service;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, ExecutorMessages.class);
	}

	private ExecutorMessages() {
	}
}
