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
package org.eclipse.passage.lic.base.conditions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;
import org.eclipse.passage.lic.base.BaseLicensingResult;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.internal.base.i18n.BaseMessages;

public class LicensingConditions {

	private static final String SEGMENT_SEPARATOR = ";"; //$NON-NLS-1$
	private static final String VALUE_SEPARATOR = "="; //$NON-NLS-1$

	private LicensingConditions() {
		// block
	}

	public static Map<String, String> parseExpression(String expression) {
		Map<String, String> map = new HashMap<>();
		if (expression == null) {
			// FIXME: report error;
			return map;
		}
		String[] segments = expression.split(SEGMENT_SEPARATOR);
		for (String segment : segments) {
			String[] split = segment.split(VALUE_SEPARATOR);
			if (split.length != 2) {
				// FIXME: report error;
				continue;
			}
			map.put(split[0], split[1]);
		}
		return map;
	}

	public static boolean evaluateSegmentValue(String expected, String actual) {
		if (actual == null) {
			return false;
		}
		String regexp = expected.replaceAll("\\*", ".*"); //$NON-NLS-1$ //$NON-NLS-2$
		return actual.matches(regexp);
	}

	public static LicensingCondition create(String identifier, String version, String rule, Date from, Date until,
			String type, String expression) {
		return new BaseLicensingCondition(identifier, version, rule, from, until, type, expression);
	}

	public static LicensingResult validate(LicensingCondition condition, String source) {
		List<LicensingResult> details = new ArrayList<>();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(LicensingCondition.class.getName(), condition);
		Date validFrom = condition.getValidFrom();
		if (validFrom == null) {
			String format = BaseMessages.getString("LicensingConditions_validation_no_from"); //$NON-NLS-1$
			String message = String.format(format, condition);
			return new BaseLicensingResult(LicensingResult.ERROR, message, 400, source, null, details, data);
		}
		Date now = new Date();
		if (validFrom.after(now)) {
			String format = BaseMessages.getString("LicensingConditions_validation_invalid_from"); //$NON-NLS-1$
			String message = String.format(format, condition);
			return new BaseLicensingResult(LicensingResult.ERROR, message, 416, source, null, details, data);
		}
		Date validUntil = condition.getValidUntil();
		if (validUntil == null) {
			String format = BaseMessages.getString("LicensingConditions_validation_no_until"); //$NON-NLS-1$
			String message = String.format(format, condition);
			return new BaseLicensingResult(LicensingResult.ERROR, message, 400, source, null, details, data);
		}
		if (validUntil.before(now)) {
			String format = BaseMessages.getString("LicensingConditions_validation_invalid_until"); //$NON-NLS-1$
			String message = String.format(format, condition);
			return new BaseLicensingResult(LicensingResult.ERROR, message, 417, source, null, details, data);
		}
		return LicensingResults.createOK("", source); //$NON-NLS-1$
	}

}
