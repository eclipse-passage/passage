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
package org.eclipse.passage.lic.base.restrictions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.restrictions.Restriction;
import org.eclipse.passage.lic.api.restrictions.RestrictionComparator;

/**
 * 
 * @since 2.1
 */
public final class WorstRestrictionsPerFeature implements Supplier<Collection<Restriction>> {

	private final Collection<Restriction> restrictions;

	public WorstRestrictionsPerFeature(Collection<Restriction> restrictions) {
		this.restrictions = restrictions;
	}

	@Override
	public Collection<Restriction> get() {
		Map<String, List<Restriction>> featured = restrictions.stream()//
				.collect(Collectors.groupingBy(r -> r.unsatisfiedRequirement().feature().identifier()));
		return featured.values().stream()//
				.map(this::worst) //
				.collect(Collectors.toSet());
	}

	private Restriction worst(List<Restriction> source) {
		return sorted(source).get(source.size() - 1);
	}

	private List<Restriction> sorted(Collection<Restriction> source) {
		List<Restriction> sorted = new ArrayList<>(source);
		Collections.sort(sorted, new RestrictionComparator());
		return sorted;
	}

}
