/*******************************************************************************
 * Copyright (c) 2020, 2022 ArSysOp
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

import java.io.File;
import java.nio.file.Path;
import java.util.function.Supplier;

/**
 * @since 2.1
 */
public abstract class PassageFileExtension implements Supplier<String> {

	public final boolean ends(Path path) {
		return path.getFileName().toString().endsWith(get());
	}

	/**
	 * @since 2.3
	 */
	public final boolean ends(File file) {
		return file.getName().endsWith(get());
	}

	public static final class LicenseEncrypted extends PassageFileExtension {

		@Override
		public String get() {
			return ".licen"; //$NON-NLS-1$
		}

	}

	public static final class LicenseDecrypted extends PassageFileExtension {

		@Override
		public String get() {
			return ".lic"; //$NON-NLS-1$
		}

	}

	public static final class PublicKey extends PassageFileExtension {

		@Override
		public String get() {
			return ".pub"; //$NON-NLS-1$
		}

	}

	public static final class PrivateKey extends PassageFileExtension {

		@Override
		public String get() {
			return ".scr"; //$NON-NLS-1$
		}

	}

	public static final class Settings extends PassageFileExtension {

		@Override
		public String get() {
			return ".settings"; //$NON-NLS-1$
		}

	}

	public static final class Of extends PassageFileExtension {

		private final Supplier<String> extension;

		public Of(Supplier<String> extension) {
			this.extension = extension;
		}

		@Override
		public String get() {
			return extension.get();
		}

	}

}
