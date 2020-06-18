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
package org.eclipse.passage.lic.base;

import java.util.Objects;

import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleCompatible;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleDefault;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleEquivalent;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleGreaterOrEqual;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRulePerfect;
import org.eclipse.passage.lic.internal.base.version.DefaultVersion;
import org.eclipse.passage.lic.internal.base.version.SafeVersion;

public final class LicensingVersions {

	/**
	 * @deprecated use {@linkplain SafeVersion} or {@linkplain DefaultVersion}
	 */
	@Deprecated
	public static final String VERSION_DEFAULT = "0.0.0"; //$NON-NLS-1$

	/**
	 * @deprecated use {@linkplain MatchingRulePerfect}
	 */
	@Deprecated
	public static final String RULE_PERFECT = "perfect"; //$NON-NLS-1$
	/**
	 * @deprecated use {@linkplain MatchingRuleEquivalent}
	 */
	@Deprecated
	public static final String RULE_EQUIVALENT = "equivalent"; //$NON-NLS-1$
	/**
	 * @deprecated use {@linkplain MatchRuleEquivalentCompatible}
	 */
	@Deprecated
	public static final String RULE_COMPATIBLE = "compatible"; //$NON-NLS-1$
	/**
	 * @deprecated use {@linkplain MatchingRuleGreaterOrEqual}
	 */
	@Deprecated
	public static final String RULE_GREATER_OR_EQUAL = "greaterOrEqual"; //$NON-NLS-1$
	/**
	 * @deprecated use {@linkplain MatchingRuleDefault}
	 */
	@Deprecated
	public static final String RULE_DEFAULT = RULE_COMPATIBLE;

	private static final String SEPARATOR_REGEX = "\\."; //$NON-NLS-1$

	private LicensingVersions() {
		// block
	}

	/**
	 * @deprecated use {@linkplain SafeVersion}
	 */
	@Deprecated
	public static String toVersionValue(Object object) {
		if (object instanceof String) {
			String version = (String) object;
			version = version.trim();
			if (version.length() == 0) {
				return VERSION_DEFAULT;
			}
			String[] split = version.split(SEPARATOR_REGEX);
			int major = extractSegment(split, 0);
			int minor = extractSegment(split, 1);
			int service = extractSegment(split, 2);
			String qualifier = ""; //$NON-NLS-1$
			if (split.length > 3) {
				if (major + minor + service > 0) {
					qualifier = split[3];
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append(major).append('.');
			sb.append(minor).append('.');
			sb.append(service);
			if (!qualifier.isEmpty()) {
				sb.append('.');
				sb.append(qualifier);
			}
			return sb.toString();
		}
		return VERSION_DEFAULT;
	}

	private static int extractSegment(String[] split, int index) {
		if (split.length > index) {
			try {
				return Integer.parseInt(split[index]);
			} catch (Exception e) {
				// ignore
			}
		}
		return 0;
	}

	public static String toRuleValue(Object object) {
		if (object instanceof String) {
			String rule = (String) object;
			if (RULE_GREATER_OR_EQUAL.equalsIgnoreCase(rule)) {
				return RULE_GREATER_OR_EQUAL;
			}
			if (RULE_COMPATIBLE.equalsIgnoreCase(rule)) {
				return RULE_COMPATIBLE;
			}
			if (RULE_EQUIVALENT.equalsIgnoreCase(rule)) {
				return RULE_EQUIVALENT;
			}
			if (RULE_PERFECT.equalsIgnoreCase(rule)) {
				return RULE_PERFECT;
			}
		}
		return LicensingVersions.RULE_DEFAULT;
	}

	/**
	 * @deprecated use MatchRule
	 */
	@Deprecated
	public static boolean isMatch(String required, String allowed, String match) {
		String rule = toRuleValue(match);
		if (RULE_GREATER_OR_EQUAL.equals(rule)) {
			return isGreaterOrEqual(required, allowed);
		}
		if (RULE_COMPATIBLE.equals(rule)) {
			return isCompatible(required, allowed);
		}
		if (RULE_EQUIVALENT.equals(rule)) {
			return isEquivalent(required, allowed);
		}
		if (RULE_PERFECT.equals(rule)) {
			return isPerfect(required, allowed);
		}
		if (required == null || allowed == null) {
			return false;
		}
		return Objects.equals(required, allowed);
	}

	/**
	 * @deprecated use {@linkplain MatchingRuleGreaterOrEqual}
	 */
	@Deprecated
	public static boolean isGreaterOrEqual(String required, String allowed) {
		if (VERSION_DEFAULT.equals(allowed)) {
			return true;
		}
		return compare(required, allowed, 0);
	}

	/**
	 * @deprecated use {@linkplain MatchingRuleCompatible}
	 */
	@Deprecated
	public static boolean isCompatible(String required, String allowed) {
		return compare(required, allowed, 1);
	}

	/**
	 * @deprecated use {@linkplain MatchingRuleEquivalent}
	 */
	@Deprecated
	public static boolean isEquivalent(String required, String allowed) {
		return compare(required, allowed, 2);
	}

	/**
	 * @deprecated use {@linkplain MatchingRulePerfect}
	 */
	@Deprecated
	public static boolean isPerfect(String required, String allowed) {
		if (required == null || allowed == null) {
			return false;
		}
		return Objects.equals(required, allowed);
	}

	/**
	 * Use {@linkplain MatchRule} implementations
	 */
	@Deprecated
	public static boolean compare(String required, String allowed, int match) {
		if (required == null || allowed == null) {
			return false;
		}
		if (Objects.equals(required, allowed)) {
			return true;
		}
		if (VERSION_DEFAULT.equals(required)) {
			return true;
		}
		String expected = toVersionValue(allowed);
		String actual = toVersionValue(required);
		String[] esplit = expected.split(SEPARATOR_REGEX);
		String[] asplit = actual.split(SEPARATOR_REGEX);
		for (int i = 0; i < match; i++) {
			int es = extractSegment(esplit, i);
			int as = extractSegment(asplit, i);
			if (es != as) {
				return false;
			}
		}
		int es = extractSegment(esplit, match);
		int as = extractSegment(asplit, match);
		if (es > as) {
			return false;
		}
		return true;
	}

}
