/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.internal.equinox.conditions;

import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_CONDITION_TYPE;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.passage.lic.runtime.ConditionEvaluator;
import org.eclipse.passage.lic.runtime.registry.ConditionEvaluatorRegistry;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

@Component
public class EquinoxConditionEvaluatorRegistry implements ConditionEvaluatorRegistry {

	private final Map<String, ConditionEvaluator> conditionEvaluators = new HashMap<>();

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void bindConditionEvaluator(ConditionEvaluator evaluator, Map<String, Object> properties) {
		Object conditionType = properties.get(LICENSING_CONDITION_TYPE);
		String type = String.valueOf(conditionType);
		conditionEvaluators.put(type, evaluator);
	}

	public void unbindConditionEvaluator(ConditionEvaluator evaluator, Map<String, Object> properties) {
		Object conditionType = properties.get(LICENSING_CONDITION_TYPE);
		String type = String.valueOf(conditionType);
		ConditionEvaluator existing = conditionEvaluators.remove(type);
		if (evaluator == existing) {
			conditionEvaluators.remove(type);
		}
	}

	@Override
	public Iterable<String> getSupportedConditionTypes() {
		return Collections.unmodifiableSet(conditionEvaluators.keySet());
	}

	@Override
	public String getDefaultConditionType() {
		// TODO configure via property?
		return "<undefined>";
	}

	@Override
	public ConditionEvaluator getConditionEvaluator(String conditionType) {
		return conditionEvaluators.get(conditionType);
	}

	@Override
	public Iterable<? extends ConditionEvaluator> getConditionEvaluators() {
		return conditionEvaluators.values();
	}

}
