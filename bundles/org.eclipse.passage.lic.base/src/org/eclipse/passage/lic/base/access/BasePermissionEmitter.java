/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.base.access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.api.access.FeaturePermission;
import org.eclipse.passage.lic.api.access.PermissionEmitter;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;
import org.eclipse.passage.lic.base.BaseLicensingResult;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.base.conditions.LicensingConditions;
import org.eclipse.passage.lic.internal.base.i18n.BaseMessages;

public abstract class BasePermissionEmitter implements PermissionEmitter {

	@Override
	public Iterable<FeaturePermission> emitPermissions(LicensingConfiguration configuration,
			Iterable<LicensingCondition> conditions) throws LicensingException {
		List<FeaturePermission> result = new ArrayList<>();
		if (conditions == null) {
			String message = BaseMessages.getString("BasePermissionEmitter_prem_emit_error_invalid_consitions"); //$NON-NLS-1$
			Exception e = new IllegalArgumentException();
			LicensingResult error = LicensingResults.createError(message, getClass().getName(), e);
			throw new LicensingException(error);
		}
		for (LicensingCondition condition : conditions) {
			String expression = condition.getConditionExpression();
			Map<String, String> checks = LicensingConditions.parseExpression(expression);
			if (checks.isEmpty()) {
				String message = String.format(
						BaseMessages.getString("BasePermissionEmitter_prem_emit_error_no_expression_checks"), //$NON-NLS-1$
						condition);
				throw new LicensingException(failure(condition, 401, message));
			}
			Set<String> keySet = checks.keySet();
			for (String key : keySet) {
				String value = checks.get(key);
				final boolean passed;
				try {
					passed = evaluateSegment(key, value);
				} catch (Throwable t) {// any kind of failures should be processed here
					String message = String.format(
							BaseMessages.getString("BasePermissionEmitter_prem_emit_error_condition_evaluation_error"), //$NON-NLS-1$
							condition, key, value);
					throw new LicensingException(failure(condition, 401, message));
				}
				if (!passed) {
					String message = String.format(
							BaseMessages.getString("BasePermissionEmitter_prem_emit_error_condition_rejected"), //$NON-NLS-1$
							condition, key, value);
					throw new LicensingException(failure(condition, 403, message));
				}
			}
			result.add(createPermission(condition, configuration));
		}

		return result;
	}

	protected FeaturePermission createPermission(LicensingCondition condition, LicensingConfiguration configuration) {
		return FeaturePermissions.createDefault(configuration, condition);
	}

	protected abstract boolean evaluateSegment(String key, String value);

	private LicensingResult failure(LicensingCondition condition, int code, String message) {
		List<LicensingResult> details = new ArrayList<>();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(LicensingCondition.class.getName(), condition);
		LicensingResult error = new BaseLicensingResult(LicensingResult.ERROR, message, code, getClass().getName(),
				null, details, data);
		return error;
	}

}
