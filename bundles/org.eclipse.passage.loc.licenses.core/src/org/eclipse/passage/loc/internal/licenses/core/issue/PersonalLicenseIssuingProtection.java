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

import org.eclipse.passage.lic.internal.api.conditions.ValidityPeriodClosed;
import org.eclipse.passage.lic.internal.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.lic.internal.equinox.EquinoxPassage;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;

@SuppressWarnings("restriction")
public final class PersonalLicenseIssuingProtection implements Consumer<LicensePack> {

	private final String feature = "org.eclipse.passage.loc.operator.issue.personal.full"; //$NON-NLS-1$
	private final List<Reduction<LicenseGrant>> reductions;

	public PersonalLicenseIssuingProtection() {
		this.reductions = Arrays.asList(//
				new ClosedValidityPeriodReduction<LicenseGrant>(this::validGet, this::validSet)//
		);
	}

	@Override
	public void accept(LicensePack license) {
		if (new EquinoxPassage().canUse(feature)) {
			return;
		}
		diminish(license);
	}

	private void diminish(LicensePack license) {
		license.getLicenseGrants().forEach(this::diminishGrant);
	}

	private void diminishGrant(LicenseGrant grant) {
		reductions.forEach(r -> r.accept(grant));
	}

	private Optional<ValidityPeriodClosed> validGet(LicenseGrant grant) {
		return Optional.of(new BaseValidityPeriodClosed(date(grant.getValidFrom()), date(grant.getValidUntil())));
	}

	private void validSet(LicenseGrant grant, ValidityPeriodClosed period) {
		grant.setValidFrom(Date.from(period.from().toInstant()));
		grant.setValidUntil(Date.from(period.to().toInstant()));
	}

	private ZonedDateTime date(Date date) {
		return ZonedDateTime.from(date.toInstant().atZone(ZoneId.systemDefault()));
	}

}
