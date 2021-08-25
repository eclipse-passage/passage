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
package org.eclipse.passage.lbc.internal.base.mine;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.agreements.GlobalAgreement;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.conditions.ValidityPeriodClosed;
import org.eclipse.passage.lic.internal.licenses.convert.EAgreements;
import org.eclipse.passage.lic.internal.licenses.model.EmptyPersonalFeatureGrant;
import org.eclipse.passage.lic.internal.licenses.model.EmptyPersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;

@SuppressWarnings("restriction")
final class PersonalLicenseGenerated implements Supplier<PersonalLicensePack> {

	private final LicensedProduct product;
	private final String user;
	private final Collection<Condition> conditions;
	private final Collection<GlobalAgreement> agreements;

	PersonalLicenseGenerated(LicensedProduct product, String user, Collection<Condition> conditions,
			Collection<GlobalAgreement> agreements) {
		this.product = product;
		this.user = user;
		this.conditions = conditions;
		this.agreements = agreements;
	}

	@Override
	public PersonalLicensePack get() {
		PersonalLicensePack pack = new EmptyPersonalLicensePack().get();
		pack.getLicense().setIdentifier(generated());
		pack.getLicense().setIssueDate(new Date());
		pack.getLicense().setPlan("ignored"); //$NON-NLS-1$
		pack.getLicense().getProduct().setIdentifier(product.identifier());
		pack.getLicense().getProduct().setVersion(product.version());
		pack.getLicense().getUser().setName(user);
		pack.getLicense().getUser().setIdentifier(user);
		conditions.stream()//
				.map(this::grant)//
				.forEach(pack.getGrants()::add);
		new EAgreements(agreements).get()//
				.forEach(pack.getLicense().getAgreements()::add);
		return pack;
	}

	private String generated() {
		return String.format("generated:%s", UUID.randomUUID().toString());//$NON-NLS-1$
	}

	private PersonalFeatureGrant grant(Condition condition) {
		PersonalFeatureGrant grant = new EmptyPersonalFeatureGrant().get();
		grant.setIdentifier(condition.identifier());
		grant.getFeature().setIdentifier(condition.feature());
		grant.getFeature().getVersionMatch().setVersion(condition.versionMatch().version());
		grant.getFeature().getVersionMatch().setRule(condition.versionMatch().rule().identifier());
		grant.setCapacity(1);
		grant.getUserAuthentication().setExpression(condition.evaluationInstructions().expression());
		grant.getUserAuthentication().setType(condition.evaluationInstructions().type().identifier());
		((org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed) grant.getValid())
				.setFrom(date(condition, ValidityPeriodClosed::from));
		((org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed) grant.getValid())
				.setUntil(date(condition, ValidityPeriodClosed::to));
		return grant;
	}

	private Date date(Condition condition, Function<ValidityPeriodClosed, ZonedDateTime> source) {
		ValidityPeriodClosed closed = (ValidityPeriodClosed) condition.validityPeriod();
		return Date.from(source.apply(closed).toInstant());
	}

}
