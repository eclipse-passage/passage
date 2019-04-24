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

import org.eclipse.passage.lic.api.access.FeaturePermission;
import org.eclipse.passage.lic.api.access.PermissionEmitter;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;
import org.eclipse.passage.lic.base.BaseMessages;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.base.conditions.LicensingConditions;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.LicensingException;
import org.eclipse.passage.lic.runtime.LicensingResult;

public abstract class BasePermissionEmitter implements PermissionEmitter {

	@Override
	public Iterable<FeaturePermission> emitPermissions(LicensingConfiguration configuration,
			Iterable<LicensingCondition> conditions) throws LicensingException {
		List<FeaturePermission> result = new ArrayList<>();
		String source = getClass().getName();
		if (conditions == null) {
			String message = BaseMessages.getString("BasePermissionEmitter_prem_emit_error_invalid_consitions"); //$NON-NLS-1$
			Exception e = new IllegalArgumentException();
			LicensingResult error = LicensingResults.createError(message, source, e);
			throw new LicensingException(error);
		}
		for (LicensingCondition condition : conditions) {
			String expression = condition.getConditionExpression();
			Map<String, String> checks = LicensingConditions.parseExpression(expression);
			if (checks.isEmpty()) {
				String message = String.format(BaseMessages.getString("BasePermissionEmitter_prem_emit_error_no_expression_checks"), condition); //$NON-NLS-1$
				Exception e = new IllegalArgumentException();
				LicensingResult error = LicensingResults.createError(message, source, e);
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
					String message = String.format(BaseMessages.getString("BasePermissionEmitter_prem_emit_error_condition_evaluation_error"), condition, //$NON-NLS-1$
							key, value);
					LicensingResult error = LicensingResults.createError(message, source, e);
					throw new LicensingException(error);
				}
				if (!passed) {
					String message = String.format(BaseMessages.getString("BasePermissionEmitter_prem_emit_error_condition_rejected"), condition, key, value); //$NON-NLS-1$
					Exception e = new IllegalArgumentException();
					LicensingResult error = LicensingResults.createError(message, source, e);
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
		return FeaturePermissions.createDefault(configuration, condition);
	}

	protected abstract boolean evaluateSegment(String key, String value);

}
