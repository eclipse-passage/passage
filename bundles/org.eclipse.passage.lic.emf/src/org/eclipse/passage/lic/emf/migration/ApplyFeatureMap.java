/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.emf.migration;

import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;

/**
 * @since 2.0
 */
public final class ApplyFeatureMap {

	private final FeatureMap features;
	private final EFeatureRoutes routes;

	public ApplyFeatureMap(FeatureMap features, EFeatureRoutes routes) {
		Objects.requireNonNull(features, "ApplyFeatureMap::features"); //$NON-NLS-1$
		Objects.requireNonNull(routes, "ApplyFeatureMap::routes"); //$NON-NLS-1$
		this.features = features;
		this.routes = routes;
	}

	public void apply(EObject object) {
		for (Iterator<Entry> iterator = features.iterator(); iterator.hasNext();) {
			Entry entry = iterator.next();
			Optional.of(entry.getEStructuralFeature().getName())//
					.flatMap(routes)//
					.ifPresent(f -> object.eSet(f, entry.getValue()));
		}
	}

}
