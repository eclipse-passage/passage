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
package org.eclipse.passage.lic.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.passage.lic.runtime.ConditionEvaluator;
import org.eclipse.passage.lic.runtime.FeaturePermission;
import org.eclipse.passage.lic.runtime.LicensingCondition;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;

public abstract class BaseConditionEvaluator implements ConditionEvaluator {

	@Override
	public Iterable<FeaturePermission> evaluateConditions(Iterable<LicensingCondition> conditions, LicensingConfiguration configuration) {
		List<FeaturePermission> result = new ArrayList<>();
		if (conditions == null) {
			String message = "Evaluation rejected for invalid conditions";
			logError(message, new NullPointerException());
			return result;
		}
		for (LicensingCondition condition : conditions) {
			String expression = condition.getConditionExpression();
			Map<String,String> checks = LicensingConditions.parseExpression(expression);
			if (checks.isEmpty()) {
				String message = String.format("Expression checks are empty for condition %s", condition);
				logError(message, new Exception());
				continue;
			}
			Set<String> keySet = checks.keySet();
			
			boolean passed = true;
			for (String key : keySet) {
				String value = checks.get(key);
				try {
					passed = evaluateSegment(key, value);
				} catch (Exception e) {
					passed = false;
					String message = String.format("Failed for evaluate condition %s : key=%s, value=%s", condition, key, value);
					logError(message, new Exception());
				}
				if (!passed) {
					String message = String.format("Condition %s rejected: key=%s, value=%s", condition, key, value);
					logError(message, new Exception());
					break;
				}
			}
			if (passed) {
				result.add(createPermission(condition, configuration));
			}
		}
		
		return result;
	}

	protected FeaturePermission createPermission(LicensingCondition condition, LicensingConfiguration configuration) {
		return FeaturePermissions.createDefault(condition, configuration);
	}

	protected abstract boolean evaluateSegment(String key, String value);

	protected abstract void logError(String message, Throwable e);

}
