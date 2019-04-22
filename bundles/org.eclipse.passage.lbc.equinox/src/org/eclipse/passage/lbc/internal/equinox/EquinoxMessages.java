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
 *******************************************************************************/
package org.eclipse.passage.lbc.internal.equinox;

import org.eclipse.osgi.util.NLS;

public class EquinoxMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.lbc.internal.equinox.EquinoxMessages"; //$NON-NLS-1$
	public static String AcquireConditionActionExecutor_0;
	public static String AcquireConditionActionExecutor_log_execute_action;
	public static String ServerConditionArbiter_log_already_lease;
	public static String ServerConditionArbiter_log_lease;
	public static String ServerConditionArbiter_log_not_reserved;
	public static String ServerConditionArbiter_log_reserv;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, EquinoxMessages.class);
	}

	private EquinoxMessages() {
	}
}
