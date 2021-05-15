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
package org.eclipse.passage.lic.internal.emf.migration;

import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.passage.lic.emf.migration.EFeatureRoutes;

/**
 * @since 2.0
 */
public final class RecognizeFeatures {

	private final AnyType any;
	private final EFeatureRoutes features;

	public RecognizeFeatures(AnyType any, EFeatureRoutes features) {
		Objects.requireNonNull(any, "ApplyFeatureMap::any"); //$NON-NLS-1$
		Objects.requireNonNull(features, "ApplyFeatureMap::features"); //$NON-NLS-1$
		this.any = any;
		this.features = features;
	}

	public RecognizeFeatures mixed(EObject object) {
		EList<EReference> references = object.eClass().getEAllReferences();
		for (Iterator<Entry> iterator = any.getMixed().iterator(); iterator.hasNext();) {
			Entry entry = iterator.next();
			Optional<EStructuralFeature> candidate = features.route(entry.getEStructuralFeature().getName())//
					.filter(references::contains);
			if (!candidate.isPresent()) {
				continue;
			}
			// FIXME: different routes for attributes and references?
			EReference feature = (EReference) candidate.get();
			EClass type = feature.getEReferenceType();
			Object value = entry.getValue();
			if (value instanceof AnyType) {
				AnyType child = (AnyType) value;
				EObject created = type.getEPackage().getEFactoryInstance().create(type);
				new RecognizeFeatures(child, features).attributes(created);
				if (feature.isMany()) {
					object.eSet(feature, Collections.singletonList(created));
				} else {
					object.eSet(feature, created);
				}
			}
		}
		return this;
	}

	public RecognizeFeatures attributes(EObject object) {
		EList<EAttribute> attributes = object.eClass().getEAllAttributes();
		for (Iterator<Entry> iterator = any.getAnyAttribute().iterator(); iterator.hasNext();) {
			Entry entry = iterator.next();
			Optional<EStructuralFeature> candidate = features.route(entry.getEStructuralFeature().getName())//
					.filter(attributes::contains);
			if (!candidate.isPresent()) {
				continue;
			}
			// FIXME: different routes for attributes and references?
			EAttribute feature = (EAttribute) candidate.get();
			EDataType type = feature.getEAttributeType();
			Object value = type.getEPackage().getEFactoryInstance().createFromString(type,
					String.valueOf(entry.getValue()));
			object.eSet(feature, value);
		}
		return this;
	}

}
