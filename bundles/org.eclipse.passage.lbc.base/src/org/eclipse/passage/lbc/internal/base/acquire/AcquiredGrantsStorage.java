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

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.licenses.model.api.FeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.GrantAcqisition;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;

final class AcquiredGrantsStorage {

	/**
	 * <p>
	 * For a {@code product} we keep all {@code acquisitions} in a {@code Map}
	 * </p>
	 * <p>
	 * LicensedProduct -> [FeatureGrant::identifier -> {GrantAcquistion}s]
	 * </p>
	 * <p>
	 * Where each {@code feature grant} (globally uniquely identified) keeps it's
	 * own collection of {@code acquisitions}
	 * </p>
	 */
	private final Map<LicensedProduct, Map<String, Collection<GrantAcqisition>>> locks = new HashMap<>();
	private final Logger log = LogManager.getLogger(getClass());

	AcquiredGrantsStorage() {
		synchronized (this) {
			// TODO: read locks from persistent state #569158
		}
	}

	synchronized Optional<GrantAcqisition> acquire(LicensedProduct product, String user, FeatureGrant grant) {
		Collection<GrantAcqisition> acquisitions = grantLocks(product, grant.getIdentifier());
		if (acquisitions.size() < grant.getCapacity()) {
			GrantAcqisition acquistion = acquistion(grant, user);
			acquisitions.add(acquistion);
			logAcquisition("acquire", acquistion, product); //$NON-NLS-1$
			return Optional.of(acquistion);
		}
		return Optional.empty();
	}

	synchronized boolean release(LicensedProduct product, GrantAcqisition acquisition) {
		Collection<GrantAcqisition> colleagues = grantLocks(product, acquisition.getGrant());
		for (GrantAcqisition colleage : colleagues) {
			if (matches(colleage, acquisition)) {
				colleagues.remove(colleage);
				logAcquisition("release", colleage, product); //$NON-NLS-1$
				return true;
			}
		}
		return false;
	}

	private boolean matches(GrantAcqisition actual, GrantAcqisition expected) {
		return actual.getIdentifier().equals(expected.getIdentifier());
	}

	private GrantAcqisition acquistion(FeatureGrant grant, String user) {
		GrantAcqisition acquisition = LicensesFactory.eINSTANCE.createGrantAcqisition();
		acquisition.setCreated(new Date());
		acquisition.setFeature(grant.getFeature());
		acquisition.setGrant(grant.getIdentifier());
		acquisition.setIdentifier(UUID.randomUUID().toString());
		acquisition.setUser(user);
		return acquisition;
	}

	private Collection<GrantAcqisition> grantLocks(LicensedProduct product, String grant) {
		return productLocks(product).computeIfAbsent(grant, id -> new HashSet<>());
	}

	private Map<String, Collection<GrantAcqisition>> productLocks(LicensedProduct product) {
		return locks.computeIfAbsent(product, p -> new HashMap<String, Collection<GrantAcqisition>>());
	}

	private void logAcquisition(String operation, GrantAcqisition grant, LicensedProduct product) {
		log.debug(String.format("|%s| acquisiiton [%s] for user %s on feature %s product %s v%s", //$NON-NLS-1$
				operation, //
				grant.getIdentifier(), //
				grant.getUser(), //
				grant.getFeature(), //
				product.identifier(), //
				product.version()));

	}
}
