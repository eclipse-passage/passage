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
package org.eclipse.passage.lic.internal.base.io;

import java.util.function.Supplier;

public interface FileExtension extends Supplier<String> {

	public static final class LicenseEncrypted implements FileExtension {

		@Override
		public String get() {
			return ".licen"; //$NON-NLS-1$
		}

	}

	public static final class LicenseDecrypted implements FileExtension {

		@Override
		public String get() {
			return ".lic"; //$NON-NLS-1$
		}

	}

	public static final class PublicKey implements FileExtension {

		@Override
		public String get() {
			return ".pub"; //$NON-NLS-1$
		}

	}

	public static final class PrivateKey implements FileExtension {

		@Override
		public String get() {
			return ".scr"; //$NON-NLS-1$
		}

	}

}
