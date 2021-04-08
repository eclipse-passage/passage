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
import java.util.Collections;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.passage.lbc.internal.base.api.FlsGear;
import org.eclipse.passage.lbc.internal.base.api.FlsGearAwre;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.licenses.model.api.FeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;

final class FeatureGrants {

	private final LicensedProduct product;
	private final String user;
	private final String feature;
	private final Supplier<Path> base;
	private final Logger log = LogManager.getLogger(getClass());

	FeatureGrants(LicensedProduct product, String user, String feature, Supplier<Path> base) {
		this.product = product;
		this.user = user;
		this.feature = feature;
		this.base = base;
	}

	/**
	 * Explore all licenses for the {@code product} and collect all grants for the
	 * given {@code feature}, if any
	 */
	Collection<FeatureGrant> get() {
		return new FlsGearAwre()//
				.withGear(gear -> Optional.of(get(gear)))//
				.orElse(failedOnGathering());
	}

	private Collection<FeatureGrant> failedOnGathering() {
		log.error(String.format("Failed on gathering grants for product %s", product)); //$NON-NLS-1$
		return Collections.emptyList();
	}

	private Collection<FeatureGrant> get(FlsGear gear) {
		try {
			return new LicensePacks(//
					gear.keyKeper(product, base), //
					gear.codec(product), //
					product, //
					base//
			).get().stream()//
					.filter(new AvailableForUser(user)) //
					.map(this::grantForFeature) //
					.filter(Optional::isPresent) //
					.map(Optional::get) //
					.collect(Collectors.toList());
		} catch (LicensingException e) {
			log.error(e);
			return Collections.emptyList();
		}
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
