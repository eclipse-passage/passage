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
package org.eclipse.passage.lic.base.conditions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.runtime.LicensingResult;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;

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
		Date validFrom = condition.getValidFrom();
		if (validFrom == null) {
			String format = "Valid from not specified for condition %s";
			String message = String.format(format, condition);
			return LicensingResults.createError(message, source);
		}
		Date now = new Date();
		if (validFrom.after(now)) {
			String format = "Valid from starts in the future for condition %s";
			String message = String.format(format, condition);
			return LicensingResults.createError(message, source);
		}
		Date validUntil = condition.getValidUntil();
		if (validUntil == null) {
			String format = "Valid until not specified for condition %s";
			String message = String.format(format, condition);
			return LicensingResults.createError(message, source);
		}
		if (validUntil.before(now)) {
			String format = "Valid until ends in the past for condition %s";
			String message = String.format(format, condition);
			return LicensingResults.createError(message, source);
		}
		return LicensingResults.createOK("", source); //$NON-NLS-1$
	}

}
