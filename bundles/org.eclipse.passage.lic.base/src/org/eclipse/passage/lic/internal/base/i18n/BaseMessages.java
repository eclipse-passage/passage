/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp and others
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Elena Parovyshnaya <elena.parovyshnaya@gmail.com> - initial API and implementation
 *     ArSysOp - ongoing support
 *******************************************************************************/
package org.eclipse.passage.lic.internal.base.i18n;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class BaseMessages {
	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.internal.base.i18n.BaseMessages"; //$NON-NLS-1$
	private static final ResourceBundle ACCESS_RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private BaseMessages() {
	}

	public static String getString(String key) {
		try {
			return ACCESS_RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
