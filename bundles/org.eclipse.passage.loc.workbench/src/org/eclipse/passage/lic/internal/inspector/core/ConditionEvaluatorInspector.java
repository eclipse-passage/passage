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
package org.eclipse.passage.lic.internal.inspector.core;

import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_CONDITION_TYPE;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.passage.lic.runtime.ConditionEvaluator;
import org.eclipse.passage.lic.runtime.inspector.ConditionInpector;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

@Component
public class ConditionEvaluatorInspector implements ConditionInpector {
	
	private final Map<String, ConditionEvaluator> conditionEvaluators = new HashMap<>();
	
	@Reference(cardinality=ReferenceCardinality.MULTIPLE)
	public void bindConditionEvaluator(ConditionEvaluator conditionEvaluator, Map<String, Object> properties) {
		Object conditionType = properties.get(LICENSING_CONDITION_TYPE);
		String type = String.valueOf(conditionType);
		conditionEvaluators.put(type, conditionEvaluator);
	}

	public void unbindConditionEvaluator(ConditionEvaluator conditionEvaluator, Map<String, Object> properties) {
		Object conditionType = properties.get(LICENSING_CONDITION_TYPE);
		String type = String.valueOf(conditionType);
		conditionEvaluators.remove(type);
	}

	@Override
	public Iterable<String> getSupportedTypes() {
		return Collections.unmodifiableSet(conditionEvaluators.keySet());
	}

	@Override
	public String getDefaultType() {
		// TODO configure via property?
		return "<undefined>";
	}

}
