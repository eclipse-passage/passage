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
package org.eclipse.passage.lic.base.access;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.passage.lic.base.BaseLicensingResult;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.base.conditions.LicensingConditions;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.LicensingException;
import org.eclipse.passage.lic.runtime.access.FeaturePermission;
import org.eclipse.passage.lic.runtime.access.PermissionEmitter;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;

public abstract class BasePermissionEmitter implements PermissionEmitter {

	@Override
	public Iterable<FeaturePermission> emitPermissions(Iterable<LicensingCondition> conditions,
			LicensingConfiguration configuration) throws LicensingException {
		List<FeaturePermission> result = new ArrayList<>();
		String source = getClass().getName();
		if (conditions == null) {
			String message = "Evaluation rejected for invalid conditions";
			Exception e = new IllegalArgumentException();
			BaseLicensingResult error = LicensingResults.createError(message, source, e);
			throw new LicensingException(error);
		}
		for (LicensingCondition condition : conditions) {
			String expression = condition.getConditionExpression();
			Map<String, String> checks = LicensingConditions.parseExpression(expression);
			if (checks.isEmpty()) {
				String message = String.format("Expression checks are empty for condition %s", condition);
				Exception e = new IllegalArgumentException();
				BaseLicensingResult error = LicensingResults.createError(message, source, e);
				throw new LicensingException(error);
			}
			Set<String> keySet = checks.keySet();

			boolean passed = true;
			for (String key : keySet) {
				String value = checks.get(key);
				try {
					passed = evaluateSegment(key, value);
				} catch (Exception e) {
					passed = false;
					String message = String.format("Failed for evaluate condition %s : key=%s, value=%s", condition,
							key, value);
					BaseLicensingResult error = LicensingResults.createError(message, source, e);
					throw new LicensingException(error);
				}
				if (!passed) {
					String message = String.format("Condition %s rejected: key=%s, value=%s", condition, key, value);
					Exception e = new IllegalArgumentException();
					BaseLicensingResult error = LicensingResults.createError(message, source, e);
					throw new LicensingException(error);
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

}
