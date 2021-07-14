/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.loc.internal.licenses.core.issue;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.eclipse.passage.lic.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.lic.equinox.EquinoxPassage;
import org.eclipse.passage.lic.licenses.model.api.FeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.licenses.model.api.ValidityPeriod;
import org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;

public final class FloatingLicenseIssuingProtection implements Consumer<FloatingLicensePack> {

	private final String feature = "org.eclipse.passage.loc.operator.issue.floating.full"; //$NON-NLS-1$
	private final List<Reduction<FeatureGrant>> featureReductions;
	private final List<Reduction<FloatingLicensePack>> licReductions;

	public FloatingLicenseIssuingProtection() {
		this.featureReductions = Arrays.asList(//
				new ClosedValidityPeriodReduction<FeatureGrant>(this::validGet, this::validSet), //
				new FeatureGrantCapacityReduction()//
		);
		this.licReductions = Arrays.asList(//
				new ClosedValidityPeriodReduction<FloatingLicensePack>(this::validGet, this::validSet), //
				new UserGrantsAmountReduction()//
		);
	}

	@Override
	public void accept(FloatingLicensePack license) {
		if (new EquinoxPassage().canUse(feature)) {
			return;
		}
		diminish(license);
	}

	private void diminish(FloatingLicensePack license) {
		diminishFeatureGrants(license);
		diminishLicense(license);
	}

	private void diminishFeatureGrants(FloatingLicensePack license) {
		license.getFeatures().forEach(this::diminishGrant);
	}

	private void diminishLicense(FloatingLicensePack lic) {
		licReductions.forEach(r -> r.accept(lic));
	}

	private void diminishGrant(FeatureGrant grant) {
		featureReductions.forEach(r -> r.accept(grant));
	}

	private Optional<org.eclipse.passage.lic.api.conditions.ValidityPeriodClosed> validGet(
			FloatingLicensePack lic) {
		return validGet(lic.getLicense().getValid());
	}

	private Optional<org.eclipse.passage.lic.api.conditions.ValidityPeriodClosed> validGet(
			FeatureGrant grant) {
		return validGet(grant.getValid());
	}

	private Optional<org.eclipse.passage.lic.api.conditions.ValidityPeriodClosed> validGet(
			ValidityPeriod period) {
		if (!(period instanceof ValidityPeriodClosed)) {
			return Optional.empty(); // nothing we can reduce for now
		}
		ValidityPeriodClosed closed = (ValidityPeriodClosed) period;
		return Optional.of(new BaseValidityPeriodClosed(date(closed.getFrom()), date(closed.getUntil())));
	}

	private void validSet(FloatingLicensePack lic,
			org.eclipse.passage.lic.api.conditions.ValidityPeriodClosed period) {
		lic.getLicense().setValid(convert(period));
	}

	private void validSet(FeatureGrant grant,
			org.eclipse.passage.lic.api.conditions.ValidityPeriodClosed period) {
		grant.setValid(convert(period));
	}

	private ValidityPeriodClosed convert(org.eclipse.passage.lic.api.conditions.ValidityPeriodClosed period) {
		org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed valid = LicensesFactory.eINSTANCE
				.createValidityPeriodClosed();
		valid.setFrom(Date.from(period.from().toInstant()));
		valid.setUntil(Date.from(period.to().toInstant()));
		return valid;
	}

	private ZonedDateTime date(Date date) {
		return ZonedDateTime.from(date.toInstant().atZone(ZoneId.systemDefault()));
	}

}
