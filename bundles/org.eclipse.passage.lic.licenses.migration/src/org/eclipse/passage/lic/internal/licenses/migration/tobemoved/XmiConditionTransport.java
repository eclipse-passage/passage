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
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;
import org.eclipse.passage.lic.base.conditions.LicensingConditions;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationType;
import org.eclipse.passage.lic.internal.api.conditions.ValidityPeriodClosed;
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
						descriptor.getValidFrom(), //
						descriptor.getValidUntil()), //
				new BaseEvaluationInstructions(//
						new EvaluationType.Of(descriptor.getConditionType()), //
						descriptor.getConditionExpression()));
	}

	@Override
	public void write(Collection<Condition> conditions, OutputStream output) throws IOException {
		Resource resource = new XMIResourceImpl();
		EList<EObject> contents = resource.getContents();
		for (Condition condition : conditions) {
			LicensingCondition conditionDescriptor = condition(condition);
			if (conditionDescriptor instanceof EObject) {
				EObject eObject = (EObject) conditionDescriptor;
				contents.add(eObject);
			}
		}
		resource.save(output, new HashMap<>());
	}

	// FIXME: severe error here: none of'em are EObject! Find a way to construct
	// true-emf LicenseGrantDescriptors
	private LicensingCondition condition(Condition condition) {
		LicensingCondition lic = LicensingConditions.create(//
				condition.feature(), //
				condition.versionMatch().version(), //
				condition.versionMatch().rule().identifier(), //
				((ValidityPeriodClosed) condition.validityPeriod()).from(), //
				((ValidityPeriodClosed) condition.validityPeriod()).to(), //
				condition.evaluationInstructions().type().identifier(), //
				condition.evaluationInstructions().expression());
		throw new UnsupportedOperationException();
	}

}
