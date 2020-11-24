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
package org.eclipse.passage.lbc.internal.base.tobemoved.acquire;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.passage.lbc.internal.api.tobemoved.Grants;
import org.eclipse.passage.lic.floating.model.api.FeatureGrant;
import org.eclipse.passage.lic.floating.model.api.GrantAcqisition;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;

public final class AcquiredGrants implements Grants {

	private final Object lock = new Object();
	private final Content content;

	public AcquiredGrants() {
		synchronized (lock) {
			// TODO : eagerly load from persistent state
			content = new Content();
		}
	}

	public Optional<GrantAcqisition> acquire(LicensedProduct product, String user, String feature)
			throws LicensingException {
		Collection<FeatureGrant> grants = new FeatureGrants(product, user, feature).get();
		if (grants.isEmpty()) {
			return Optional.empty();
		}
		synchronized (lock) {
			for (FeatureGrant grant : grants) {
				if (acquire(product, user, grant)) {
					return Optional.of(acquisition(product, user, grant));
				}
			}
		}
		return Optional.empty();
	}

	public boolean release(GrantAcqisition acquisition) {
		return false;
	}

	private boolean acquire(LicensedProduct product, String user, FeatureGrant grant) {
		return false;// YTBD
	}

	private GrantAcqisition acquisition(LicensedProduct product, String user, FeatureGrant grant) {
		return null;// YTBD
	}

	private final static class Content {
		private final Map<LicensedProduct, ?> grants = new HashMap<>();
	}

}
