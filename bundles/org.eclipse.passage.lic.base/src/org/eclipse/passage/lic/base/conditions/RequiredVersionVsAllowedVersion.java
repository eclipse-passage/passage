/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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

import java.util.List;
import java.util.Objects;

import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.base.version.DefaultVersion;
import org.eclipse.passage.lic.base.version.NumericalVersion;
import org.eclipse.passage.lic.base.version.SafeVersion;

/**
 * Define if the {@code actual} version (that originates in declared
 * {@linkplain Requirement}s) fits to the {@code expected} version (which comes
 * from license by means of licensing {@linkplain Condition}s.
 * 
 * @since 2.1
 */
public final class RequiredVersionVsAllowedVersion {

	private final String required;
	private final String allowed;
	private final String defaultVersion = new DefaultVersion().value();

	public RequiredVersionVsAllowedVersion(String required, String allowed) {
		Objects.requireNonNull(required, "MatchRuleParsing::requires"); //$NON-NLS-1$
		Objects.requireNonNull(allowed, "MatchRuleParsing::allowed"); //$NON-NLS-1$
		this.required = required;
		this.allowed = allowed;
	}

	/**
	 * <p>
	 * Goes through both versions segments pair by pair.
	 * </p>
	 * <p>
	 * Expects the first {@code equals} pairs (head) to be equal; reports
	 * {@code do not match} result, if some equality expectation here is not met.
	 * </p>
	 * <p>
	 * Then takes the next pair for analysis. Reports {@code true} if required
	 * (actual)'s segment is greater or equal the the corresponding segment of
	 * allowed (expected) version. Reports {@code false} otherwise.
	 * </p>
	 * <p>
	 * The rest of the segments, if any, are skipped.
	 * </p>
	 * 
	 * @param equals number of version segments that are demanded to be equal before
	 *               the next pair is analyzed for the final result. The rest of the
	 *               version is skipped.
	 * @return {@code true} if the {@code actual} version value (required, from
	 *         requirements) is greater or equal to expected version (allowed, from
	 *         license) and {@code false} otherwise.
	 */
	public boolean match(int equals) {
		if (Objects.equals(required, allowed)) {
			return true;
		}
		if (defaultVersion.equals(required)) {
			return true;
		}
		List<Integer> expected = segments(allowed);
		List<Integer> actual = segments(required);
		if (!headsAreEqual(expected, actual, equals)) {
			return false;
		}
		return expected.get(equals) <= actual.get(equals);
	}

	private List<Integer> segments(String raw) {
		return new NumericalVersion(new SafeVersion(raw).semantic()).get();
	}

	private boolean headsAreEqual(List<Integer> expected, List<Integer> actual, int length) {
		for (int i = 0; i < length; i++) {
			if (expected.get(i) != actual.get(i)) {
				return false;
			}
		}
		return true;
	}
}
