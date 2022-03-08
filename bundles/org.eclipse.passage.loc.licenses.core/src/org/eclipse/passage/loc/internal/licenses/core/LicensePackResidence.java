/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.base.io.UserHomeProductResidence;
import org.eclipse.passage.lic.licenses.model.api.LicenseRequisites;
import org.eclipse.passage.loc.internal.api.workspace.LicensePacks;
import org.eclipse.passage.loc.internal.equinox.OperatorGearAware;

@SuppressWarnings("restriction")
final class LicensePackResidence implements Supplier<Path> {

	private final LicenseRequisites license;

	LicensePackResidence(LicenseRequisites license) {
		this.license = license;
	}

	@Override
	public Path get() {
		return existing(residence());
	}

	private Path residence() {
		return fromWorkspace().orElseGet(this::userHomeResidence);
	}

	private Path userHomeResidence() {
		return new UserHomeProductResidence(//
				license.getProduct().getIdentifier(), //
				license.getProduct().getVersion())//
						.get()//
						.resolve(license.getIdentifier());
	}

	private Optional<Path> fromWorkspace() {
		try {
			return new OperatorGearAware()
					.withGear(gear -> Optional.of(fromWorkspace(gear.workspace().licensePacks())));
		} catch (LicensingException e) {
			return Optional.empty();
		}
	}

	private Path fromWorkspace(LicensePacks service) {
		return Paths.get(service.packResidence(//
				license.getIdentifier(), //
				license.getProduct().getIdentifier(), //
				license.getProduct().getVersion()//
		).uri());
	}

	private Path existing(Path folder) {
		folder.toFile().mkdirs();
		return folder;
	}

}
