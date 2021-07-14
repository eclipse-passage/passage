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

import org.eclipse.passage.lic.api.conditions.ValidityPeriodClosed;
import org.eclipse.passage.lic.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.lic.equinox.EquinoxPassage;
import org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;

public final class PersonalLicenseIssuingProtection implements Consumer<PersonalLicensePack> {

	private final String feature = "org.eclipse.passage.loc.operator.issue.personal.full"; //$NON-NLS-1$
	private final List<Reduction<PersonalFeatureGrant>> reductions;

	public PersonalLicenseIssuingProtection() {
		this.reductions = Arrays.asList(//
				new ClosedValidityPeriodReduction<PersonalFeatureGrant>(this::validGet, this::validSet)//
		);
	}

	@Override
	public void accept(PersonalLicensePack license) {
		if (new EquinoxPassage().canUse(feature)) {
			return;
		}
		diminish(license);
	}

	private void diminish(PersonalLicensePack license) {
		license.getGrants().forEach(this::diminishGrant);
	}

	private void diminishGrant(PersonalFeatureGrant grant) {
		reductions.forEach(r -> r.accept(grant));
	}

	private Optional<ValidityPeriodClosed> validGet(PersonalFeatureGrant grant) {
		return Optional.of(new BaseValidityPeriodClosed(//
				date(((org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed) grant.getValid()).getFrom()), //
				date(((org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed) grant.getValid()).getUntil())));
	}

	private void validSet(PersonalFeatureGrant grant, ValidityPeriodClosed period) {
		((org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed) grant.getValid())
				.setFrom(Date.from(period.from().toInstant()));
		((org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed) grant.getValid())
				.setUntil(Date.from(period.to().toInstant()));
	}

	private ZonedDateTime date(Date date) {
		return ZonedDateTime.from(date.toInstant().atZone(ZoneId.systemDefault()));
	}

}
