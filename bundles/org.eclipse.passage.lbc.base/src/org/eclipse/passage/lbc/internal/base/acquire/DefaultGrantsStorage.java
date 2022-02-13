/*******************************************************************************
 * Copyright (c) 2021, 2022 ArSysOp
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
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.licenses.model.api.FeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.GrantAcqisition;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;

public abstract class DefaultGrantsStorage implements GrantsStorage {

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
	protected final Map<LicensedProduct, Map<String, Collection<GrantAcqisition>>> locks = new HashMap<>();
	protected final Logger log = LogManager.getLogger(getClass());

	@Override
	public final Optional<GrantAcqisition> acquire(LicensedProduct product, String user, FeatureGrant grant) {
		if (!beforeAcquire()) {
			logStateAlternationError("acquire: blocked by preliminary checks", grant, product); //$NON-NLS-1$
			return Optional.empty();
		}
		Optional<GrantAcqisition> result = Optional.empty();
		try {
			final int capacity = capacity(grant);
			synchronized (this) {
				Collection<GrantAcqisition> acquisitions = grantLocks(product, grant.getIdentifier());
				if (acquisitions.size() < capacity) {
					GrantAcqisition acquistion = acquistion(grant, user);
					acquisitions.add(acquistion);
					logStateAlternation("acquire", acquistion, product); //$NON-NLS-1$
					result = Optional.of(acquistion);
				}
			}
		} finally {
			if (!afterAcquire()) {
				logStateAlternationError("acquire: blocked by post checks", grant, product); //$NON-NLS-1$
				result = Optional.empty();
			}
		}
		return result;
	}

	@Override
	public final boolean release(LicensedProduct product, GrantAcqisition acquisition) {
		beforeRelease();
		try {
			synchronized (this) {
				Collection<GrantAcqisition> colleagues = grantLocks(product, acquisition.getGrant());
				for (GrantAcqisition colleage : colleagues) {
					if (matches(colleage, acquisition)) {
						colleagues.remove(colleage);
						logStateAlternation("release", colleage, product); //$NON-NLS-1$
						return true;
					}
				}
			}
		} finally {
			afterRelease();
		}
		logStateAlternationError("release: failed as grant was not acquired", acquisition, product); //$NON-NLS-1$
		return false;
	}

	protected abstract boolean beforeAcquire();

	protected abstract boolean afterAcquire();

	protected abstract void beforeRelease();

	protected abstract void afterRelease();

	protected abstract int capacity(FeatureGrant grant);

	private boolean matches(GrantAcqisition actual, GrantAcqisition expected) {
		return actual.getIdentifier().equals(expected.getIdentifier());
	}

	private GrantAcqisition acquistion(FeatureGrant grant, String user) {
		GrantAcqisition acquisition = LicensesFactory.eINSTANCE.createGrantAcqisition();
		acquisition.setCreated(new Date());
		acquisition.setFeature(grant.getFeature().getIdentifier());
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

	private void logStateAlternation(String operation, GrantAcqisition grant, LicensedProduct product) {
		logStateAlternation(operation, grant, product, log::debug);
	}

	private void logStateAlternationError(String operation, GrantAcqisition grant, LicensedProduct product) {
		logStateAlternation(operation, grant, product, log::error);
	}

	private void logStateAlternationError(String message, FeatureGrant grant, LicensedProduct product) {
		log.error(String.format("|%s|: grant [%s] on feature %s product %s v%s", //$NON-NLS-1$
				message, //
				grant.getIdentifier(), //
				grant.getFeature(), //
				product.identifier(), //
				product.version()));
	}

	private void logStateAlternation(String operation, GrantAcqisition grant, LicensedProduct product,
			Consumer<String> logging) {
		logging.accept(String.format("|%s| acquisition [%s] for user %s on feature %s product %s v%s", //$NON-NLS-1$
				operation, //
				grant.getIdentifier(), //
				grant.getUser(), //
				grant.getFeature(), //
				product.identifier(), //
				product.version()));
	}
}
