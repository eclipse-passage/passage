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
package org.eclipse.passage.lic.licenses.model.transport;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.passage.lic.api.EvaluationType;
import org.eclipse.passage.lic.api.agreements.GlobalAgreement;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.conditions.IssuerSignature;
import org.eclipse.passage.lic.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.base.conditions.BaseCondition;
import org.eclipse.passage.lic.base.conditions.BaseEvaluationInstructions;
import org.eclipse.passage.lic.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.lic.internal.licenses.convert.PAgreements;
import org.eclipse.passage.lic.internal.licenses.convert.PIssuerSignature;
import org.eclipse.passage.lic.internal.licenses.convert.PVersionMatch;
import org.eclipse.passage.lic.internal.licenses.model.migration.LicensesResourceHandler;
import org.eclipse.passage.lic.licenses.ValidityPeriodClosedDescriptor;
import org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.lic.licenses.model.util.LicensesResourceImpl;

@SuppressWarnings("restriction")
abstract class BaseXmiConditionTransport implements ConditionTransport {

	private final ContentType type = new ContentType.Xml();
	private final Predicate<PersonalLicensePack> filter;

	protected BaseXmiConditionTransport() {
		this(p -> true);
	}

	protected BaseXmiConditionTransport(Predicate<PersonalLicensePack> filter) {
		this.filter = filter;
	}

	@Override
	public ContentType id() {
		return type;
	}

	@Override
	public Data read(InputStream input) throws IOException {
		// FIXME:AF: should be done via factory
		Resource resource = new LicensesResourceImpl();
		resource.load(input, loadOptions());
		Optional<PersonalLicensePack> license = resource.getContents().stream() //
				.filter(PersonalLicensePack.class::isInstance) //
				.map(PersonalLicensePack.class::cast) //
				.filter(filter)//
				.findAny();
		if (!license.isPresent()) {
			return new Data();
		}
		return new Data(conditions(license.get()), agreements(license.get()), signature(license.get()));
	}

	private Collection<GlobalAgreement> agreements(PersonalLicensePack pack) {
		return new PAgreements(pack.getLicense().getAgreements()).get();
	}

	private Optional<IssuerSignature> signature(PersonalLicensePack pack) {
		if (pack.getLicense().getSignature() == null) {
			return Optional.empty();
		}
		return Optional.of(new PIssuerSignature(pack.getLicense().getSignature()).get());
	}

	private Collection<Condition> conditions(PersonalLicensePack license) {
		return license.getGrants().stream() //
				.map(this::condition) //
				.collect(Collectors.toList());
	}

	private Map<String, Object> loadOptions() {
		Map<String, Object> options = new HashMap<>();
		options.put(LicensesPackage.eNS_URI, LicensesPackage.eINSTANCE);
		options.put(LicensesPackage.eNAME, LicensesPackage.eINSTANCE);
		options.put(XMLResource.OPTION_RESOURCE_HANDLER, new LicensesResourceHandler());
		return options;
	}

	private Condition condition(PersonalFeatureGrant grant) {
		return new BaseCondition(grant.getIdentifier(), //
				grant.getFeature().getIdentifier(), //
				new PVersionMatch(grant.getFeature().getVersionMatch()).get(), //
				new BaseValidityPeriodClosed(//
						fromDate(((ValidityPeriodClosedDescriptor) grant.getValid()).getFrom()), //
						fromDate(((ValidityPeriodClosedDescriptor) grant.getValid()).getUntil())), //
				new BaseEvaluationInstructions(//
						new EvaluationType.Of(grant.getUserAuthentication().getType()), //
						grant.getUserAuthentication().getExpression()));
	}

	private ZonedDateTime fromDate(Date date) {
		if (date == null) {
			// it should be in the past to be marked as invalid
			return ZonedDateTime.now().minusMinutes(1);
		}
		return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

}
