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
package org.eclipse.passage.lic.floating;

import org.eclipse.passage.lic.internal.base.io.PassageFileExtension;

public abstract class FloatingFileExtensions extends PassageFileExtension {

	public static final class FloatingLicenseEncrypted extends FloatingFileExtensions {

		@Override
		public String get() {
			return ".flicen"; //$NON-NLS-1$
		}

	}

	public static final class FloatingLicenseDecrypted extends FloatingFileExtensions {

		@Override
		public String get() {
			return ".flic"; //$NON-NLS-1$
		}

	}

	public static final class FloatingLicenseAccessDecrypted extends FloatingFileExtensions {

		@Override
		public String get() {
			return ".fla"; //$NON-NLS-1$
		}

	}

	public static final class FloatingLicenseAccessEncrypted extends FloatingFileExtensions {

		@Override
		public String get() {
			return ".flaen"; //$NON-NLS-1$
		}

	}

}
