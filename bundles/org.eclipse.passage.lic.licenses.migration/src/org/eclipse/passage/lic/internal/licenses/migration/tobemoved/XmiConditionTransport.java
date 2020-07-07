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
package org.eclipse.passage.lic.internal.licenses.migration.tobemoved;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationType;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.internal.base.conditions.BaseCondition;
import org.eclipse.passage.lic.internal.base.conditions.BaseEvaluationInstructions;
import org.eclipse.passage.lic.internal.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.lic.internal.base.conditions.BaseVersionMatch;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleForIdentifier;
import org.eclipse.passage.lic.licenses.LicenseGrantDescriptor;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;

@SuppressWarnings("restriction")
public final class XmiConditionTransport implements ConditionTransport {

	private final ContentType type = new ContentType.Xml();

	@Override
	public ContentType id() {
		return type;
	}

	@Override
	public Collection<Condition> read(InputStream input) throws IOException {
		Resource resource = new XMIResourceImpl();
		resource.load(input, new HashMap<>());
		return resource.getContents().stream() //
				.filter(LicensePackDescriptor.class::isInstance) //
				.map(LicensePackDescriptor.class::cast) //
				.map(LicensePackDescriptor::getLicenseGrants) //
				.flatMap(i -> StreamSupport.stream(i.spliterator(), false)) //
				.map(this::condition) //
				.collect(Collectors.toList());
	}

	private Condition condition(LicenseGrantDescriptor descriptor) {
		return new BaseCondition(//
				descriptor.getFeatureIdentifier(), //
				new BaseVersionMatch(descriptor.getMatchVersion(), //
						new MatchingRuleForIdentifier(descriptor.getMatchRule()).get()), //
				new BaseValidityPeriodClosed(//
						fromDate(descriptor.getValidFrom()), //
						fromDate(descriptor.getValidUntil())), //
				new BaseEvaluationInstructions(//
						new EvaluationType.Of(descriptor.getConditionType()), //
						descriptor.getConditionExpression()));
	}

	private ZonedDateTime fromDate(Date date) {
		return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

}
