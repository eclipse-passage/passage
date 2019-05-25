/*******************************************************************************
 * Copyright (c) 2019 ArSysOp and others
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Elena Parovyshnaya <elena.parovyshnaya@gmail.com> - initial API and implementation
 *     ArSysOp - ongoing support
 *******************************************************************************/
package org.eclipse.passage.loc.internal.licenses.emfforms;

import org.eclipse.osgi.util.NLS;

public class LicensesEmfFormsMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.loc.internal.licenses.emfforms.LicensesEmfFormsMessages"; //$NON-NLS-1$
	public static String ConditionExpressionRenderer_condition_expression;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, LicensesEmfFormsMessages.class);
	}

	private LicensesEmfFormsMessages() {
	}
}
