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

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.ValidityPeriodClosed;
import org.eclipse.passage.lic.internal.licenses.model.EmptyPersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;

@SuppressWarnings("restriction")
final class PersonalLicenseGenerated implements Supplier<PersonalLicensePack> {

	private final LicensedProduct product;
	private final String user;
	private final Collection<Condition> conditions;

	PersonalLicenseGenerated(LicensedProduct product, String user, Collection<Condition> conditions) {
		this.product = product;
		this.user = user;
		this.conditions = conditions;
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
		return pack;
	}

	private String generated() {
		return String.format("generated:%s", UUID.randomUUID().toString());//$NON-NLS-1$
	}

	private LicenseGrant grant(Condition condition) {
		LicensesFactory licenseFactory = LicensesFactory.eINSTANCE;
		LicenseGrant grant = licenseFactory.createLicenseGrant();
		grant.setIdentifier(condition.identifier());
		grant.setFeatureIdentifier(condition.feature());
		grant.setMatchVersion(condition.versionMatch().version());
		grant.setMatchRule(condition.versionMatch().rule().identifier());
		grant.setCapacity(1);
		grant.setConditionExpression(condition.evaluationInstructions().expression());
		grant.setConditionType(condition.evaluationInstructions().type().identifier());
		grant.setValidFrom(date(condition, ValidityPeriodClosed::from));
		grant.setValidUntil(date(condition, ValidityPeriodClosed::to));
		return grant;
	}

	private Date date(Condition condition, Function<ValidityPeriodClosed, ZonedDateTime> source) {
		ValidityPeriodClosed closed = (ValidityPeriodClosed) condition.validityPeriod();
		return Date.from(source.apply(closed).toInstant());
	}

}
