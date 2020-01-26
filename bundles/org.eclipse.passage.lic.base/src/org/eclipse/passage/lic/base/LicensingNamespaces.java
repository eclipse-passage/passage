/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.base;

public final class LicensingNamespaces {

	public static final String CAPABILITY_LICENSING_MANAGEMENT = "licensing.management"; //$NON-NLS-1$

	public static final String CAPABILITY_LICENSING_CONFIGURATION = "licensing.configuration"; //$NON-NLS-1$
	public static final String CAPABILITY_LICENSING_FEATURE = "licensing.feature"; //$NON-NLS-1$

	public static final String ATTRIBUTE_VERSION = "version"; //$NON-NLS-1$
	public static final String ATTRIBUTE_NAME = "name"; //$NON-NLS-1$
	public static final String ATTRIBUTE_PROVIDER = "provider"; //$NON-NLS-1$

	public static final String ATTRIBUTE_LEVEL = "level"; //$NON-NLS-1$
	public static final String ATTRIBUTE_LEVEL_INFO = "info"; //$NON-NLS-1$
	public static final String ATTRIBUTE_LEVEL_WARN = "warn"; //$NON-NLS-1$
	public static final String ATTRIBUTE_LEVEL_ERROR = "error"; //$NON-NLS-1$
	public static final String ATTRIBUTE_LEVEL_FATAL = "fatal"; //$NON-NLS-1$
	public static final String ATTRIBUTE_LEVEL_DEFAULT = ATTRIBUTE_LEVEL_WARN;

	private LicensingNamespaces() {
		// block
	}

	public static String toLevelAttribute(Object object) {
		if (object instanceof String) {
			String level = (String) object;
			if (ATTRIBUTE_LEVEL_INFO.equalsIgnoreCase(level)) {
				return ATTRIBUTE_LEVEL_INFO;
			}
			if (ATTRIBUTE_LEVEL_WARN.equalsIgnoreCase(level)) {
				return ATTRIBUTE_LEVEL_WARN;
			}
			if (ATTRIBUTE_LEVEL_ERROR.equalsIgnoreCase(level)) {
				return ATTRIBUTE_LEVEL_ERROR;
			}
			if (ATTRIBUTE_LEVEL_FATAL.equalsIgnoreCase(level)) {
				return ATTRIBUTE_LEVEL_FATAL;
			}
		}
		return ATTRIBUTE_LEVEL_DEFAULT;
	}

}
