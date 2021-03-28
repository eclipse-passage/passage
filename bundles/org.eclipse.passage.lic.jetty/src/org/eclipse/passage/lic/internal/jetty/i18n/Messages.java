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
package org.eclipse.passage.lic.internal.jetty.i18n;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @since 0.1
 */
public final class Messages {

	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.internal.jetty.i18n.Messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	public static final String stopped = getString("server_stopped"); //$NON-NLS-1$
	public static final String started = getString("server_started"); //$NON-NLS-1$
	public static final String already_running = getString("server_already_running"); //$NON-NLS-1$
	public static final String not_running = getString("server_not_running"); //$NON-NLS-1$
	public static final String error_onstart = getString("error_onstart"); //$NON-NLS-1$
	public static final String error_onstop = getString("error_onstop"); //$NON-NLS-1$
	public static final String error_onstate = getString("error_onstate"); //$NON-NLS-1$

	private Messages() {
	}

	private static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
