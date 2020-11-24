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
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.floating.model.api.FeatureGrant;
import org.eclipse.passage.lic.floating.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;

final class FeatureGrants {

	private final LicensedProduct product;
	private final String user;
	private final String feature;

	FeatureGrants(LicensedProduct product, String user, String feature) {
		this.product = product;
		this.user = user;
		this.feature = feature;
	}

	/**
	 * Explore all licenses for the {@code product} and collect all grants for the
	 * given {@code feature}, if any
	 */
	Collection<FeatureGrant> get() throws LicensingException {
		return new LicensePacks(product).get().stream()//
				.filter(new AvailableForUser(user)) //
				.map(this::grantForFeature) //
				.filter(Optional::isPresent) //
				.map(Optional::get) //
				.collect(Collectors.toList());
	}

	/**
	 * Find out if the given {@code license} has grant for the {@code feature}
	 */
	private Optional<FeatureGrant> grantForFeature(FloatingLicensePack license) {
		return license.getFeatures().stream()//
				.filter(grant -> feature.equals(grant.getFeature()))//
				.findAny();//
	}

}
