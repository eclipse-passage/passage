/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.base.io;

/**
 * @since 2.1
 */
public abstract class FloatingFileExtension extends PassageFileExtension {

	public static final class FloatingLicenseEncrypted extends FloatingFileExtension {

		@Override
		public String get() {
			return ".flicen"; //$NON-NLS-1$
		}

	}

	public static final class FloatingLicenseDecrypted extends FloatingFileExtension {

		@Override
		public String get() {
			return ".flic"; //$NON-NLS-1$
		}

	}

	public static final class FloatingLicenseAccessDecrypted extends FloatingFileExtension {

		@Override
		public String get() {
			return ".fla"; //$NON-NLS-1$
		}

	}

	public static final class FloatingLicenseAccessEncrypted extends FloatingFileExtension {

		@Override
		public String get() {
			return ".flaen"; //$NON-NLS-1$
		}

	}

}
