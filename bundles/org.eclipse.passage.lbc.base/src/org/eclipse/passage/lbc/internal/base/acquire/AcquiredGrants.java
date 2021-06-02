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
package org.eclipse.passage.lbc.internal.base.acquire;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lbc.internal.base.api.Grants;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.base.io.LicensingFolder;
import org.eclipse.passage.lic.internal.base.io.UserHomePath;
import org.eclipse.passage.lic.licenses.model.api.FeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.GrantAcqisition;

public final class AcquiredGrants implements Grants {

	private final AcquiredGrantsStorage storage;
	private final Supplier<Path> base;

	public AcquiredGrants(Supplier<Path> base) {
		this.base = base;
		this.storage = new AcquiredGrantsStorage();
	}

	public AcquiredGrants() {
		this(new LicensingFolder(new UserHomePath()));
	}

	@Override
	public Optional<GrantAcqisition> acquire(LicensedProduct product, String user, String feature)
			throws LicensingException {
		Collection<FeatureGrant> grants = new FeatureGrants(product, user, feature, base).get();
		if (grants.isEmpty()) {
			return Optional.empty();
		}
		for (FeatureGrant grant : grants) {
			Optional<GrantAcqisition> acquisition = storage.acquire(product, user, grant);
			if (acquisition.isPresent()) {
				return acquisition;
			}
		}
		return Optional.empty();
	}

	@Override
	public boolean release(LicensedProduct product, GrantAcqisition acquisition) {
		return storage.release(product, acquisition);
	}

}
