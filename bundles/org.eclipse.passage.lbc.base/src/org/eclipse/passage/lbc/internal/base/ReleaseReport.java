/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lbc.internal.base;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lbc.internal.base.ReleaseReport.Verdict;
import org.eclipse.passage.lic.internal.api.conditions.Condition;

/**
 * ReleaseReport represents a set of releasing results for each Condition that
 * was to release.
 * 
 * @since 1.0
 */
public final class ReleaseReport implements Supplier<Collection<Verdict>> {

	private final Collection<Verdict> result;

	public ReleaseReport(Collection<Verdict> result) {
		Objects.requireNonNull(result, "ReleaseReport::result"); //$NON-NLS-1$
		this.result = result;
	}

	public Collection<Condition> bad() {
		return get().stream().filter(Predicate.not(Verdict::verdict)).map(Verdict::condition)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<Verdict> get() {
		return result;
	}

	public final static class Verdict {
		private final Condition condition;
		private final boolean verdict;

		public Verdict(Condition condition, boolean verdict) {
			this.condition = condition;
			this.verdict = verdict;
		}

		public Condition condition() {
			return condition;
		}

		public boolean verdict() {
			return verdict;
		}

	}

}
