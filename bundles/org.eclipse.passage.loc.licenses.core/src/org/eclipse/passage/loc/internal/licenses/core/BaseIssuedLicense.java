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
package org.eclipse.passage.loc.internal.licenses.core;

import java.nio.file.Path;
import java.util.Objects;

import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.loc.internal.api.IssuedLicense;

final class BaseIssuedLicense implements IssuedLicense {

	private final PersonalLicensePack license;
	private final Path encrypted;
	private final Path decrypted;

	BaseIssuedLicense(PersonalLicensePack license, Path encrypted, Path decrypted) {
		Objects.requireNonNull(license, "BaseIssuedLicense::license"); //$NON-NLS-1$
		Objects.requireNonNull(encrypted, "BaseIssuedLicense::encrypted"); //$NON-NLS-1$
		Objects.requireNonNull(decrypted, "BaseIssuedLicense::decrypted"); //$NON-NLS-1$
		this.license = license;
		this.encrypted = encrypted;
		this.decrypted = decrypted;
	}

	@Override
	public PersonalLicensePack license() {
		return license;
	}

	@Override
	public Path encrypted() {
		return encrypted;
	}

	@Override
	public Path decrypted() {
		return decrypted;
	}

}
