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

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.passage.lic.emf.migration.EAttributeRoute;
import org.eclipse.passage.lic.emf.migration.EReferenceRoute;
import org.eclipse.passage.lic.emf.migration.MigrateFeatures;
import org.eclipse.passage.lic.emf.migration.MigrationException;
import org.eclipse.passage.lic.emf.migration.MigrationRoutes;

/**
 * @since 2.0
 */
public final class RecognizeFeatures implements MigrateFeatures {

	private final AnyType any;
	private final MigrationRoutes routes;

	public RecognizeFeatures(AnyType any, MigrationRoutes routes) {
		Objects.requireNonNull(any, "ApplyFeatureMap::any"); //$NON-NLS-1$
		Objects.requireNonNull(routes, "ApplyFeatureMap::routes"); //$NON-NLS-1$
		this.any = any;
		this.routes = routes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void apply(EObject root) throws MigrationException {
		for (Iterator<Entry> iterator = any.getMixed().iterator(); iterator.hasNext();) {
			Entry entry = iterator.next();
			if (routes.ignored(entry.getEStructuralFeature().getName(), root.eClass())) {
				continue;
			}
			Optional<EReferenceRoute> route = routes.reference(entry.getEStructuralFeature().getName(), root.eClass());
			if (!route.isPresent()) {
				throw new MigrationException(entry);
			}
			EReference feature = route.get().destination();
			EClass type = feature.getEReferenceType();
			Object value = entry.getValue();
			if (value instanceof AnyType) {
				AnyType child = (AnyType) value;
				EObject created = type.getEPackage().getEFactoryInstance().create(type);
				RecognizeFeatures restore = new RecognizeFeatures(child, routes);
				restore.apply(created);
				if (feature.isMany()) {
					((List<EObject>) root.eGet(feature)).add(created);
				} else {
					root.eSet(feature, created);
				}
			}
		}
		attributes(root);
	}

	private void attributes(EObject object) throws MigrationException {
		for (Iterator<Entry> iterator = any.getAnyAttribute().iterator(); iterator.hasNext();) {
			Entry entry = iterator.next();
			if (routes.ignored(entry.getEStructuralFeature().getName(), object.eClass())) {
				continue;
			}
			Optional<EAttributeRoute> route = routes.attribute(entry.getEStructuralFeature().getName(),
					object.eClass());
			if (!route.isPresent()) {
				throw new MigrationException(entry);
			}
			EObject target = resolveReferences(object, route.get().path());
			applyAttributeValue(target, route.get().destination(), entry.getValue());
		}
	}

	private EObject resolveReferences(EObject object, List<EReference> path) {
		EObject target = object;
		for (EReference reference : path) {
			EObject host = target;
			target = Optional.ofNullable(target.eGet(reference))//
					.filter(EObject.class::isInstance).map(EObject.class::cast)//
					.orElseGet(() -> ensureReferenceFulfilled(host, reference));
		}
		return target;
	}

	private EObject ensureReferenceFulfilled(EObject host, EReference reference) {
		EClass type = resolveConcrete(reference.getEReferenceType());
		EObject created = type.getEPackage().getEFactoryInstance().create(type);
		host.eSet(reference, created);
		return created;
	}

	private EClass resolveConcrete(EClass specified) {
		if (specified.isAbstract()) {
			Optional<EClass> candidate = specified.getEPackage().getEClassifiers().stream()//
					.filter(EClass.class::isInstance)//
					.map(EClass.class::cast)//
					.filter(c -> c.getEAllSuperTypes().contains(specified))//
					.findFirst();
			if (candidate.isPresent()) {
				return candidate.get();
			}
		}
		return specified;
	}

	private void applyAttributeValue(EObject object, EAttribute attribute, Object raw) {
		EDataType type = attribute.getEAttributeType();
		Object value = type.getEPackage().getEFactoryInstance().createFromString(type, String.valueOf(raw));
		object.eSet(attribute, value);
	}

}
