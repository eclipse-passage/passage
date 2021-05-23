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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * 
 * @since 2.0
 *
 */
public final class SimpleMigrationRoutes implements MigrationRoutes {

	private final Map<String, EAttributeRoute> attributes;
	private final Map<String, EReferenceRoute> references;
	private final Set<String> ignored;

	public SimpleMigrationRoutes() {
		attributes = new HashMap<>();
		references = new HashMap<>();
		ignored = new HashSet<>();
	}

	@Override
	public void define(String found, EAttributeRoute route) {
		attributes.put(key(route.destination().getEContainingClass(), found), route);
		List<EReference> list = route.path();
		while (!list.isEmpty()) {
			EStructuralFeature last = list.remove(list.size() - 1);
			attributes.put(key(last.getEContainingClass(), found), //
					new SimpleAttributeRoute(route.destination(), //
							route.path()));// FIXME: AF: not clear why we should use full route here
		}
	}

	@Override
	public void define(String found, EReferenceRoute route) {
		references.put(key(route.destination().getEContainingClass(), found), route);
		List<EReference> list = route.path();
		while (!list.isEmpty()) {
			EStructuralFeature last = list.remove(list.size() - 1);
			references.put(key(last.getEContainingClass(), found), //
					new SimpleReferenceRoute(route.destination(), //
							route.path()));// FIXME: AF: not clear why we should use full route here
		}
	}

	@Override
	public void ignore(String found, EClass location) {
		ignored.add(key(location, found));
	}

	private String key(EClass scope, String found) {
		return scope.getName() + '.' + found;
	}

	@Override
	public Optional<EAttributeRoute> attribute(String found, EClass context) {
		Optional<EAttributeRoute> direct = Optional.ofNullable(attributes.get(key(context, found)));
		if (direct.isPresent()) {
			return direct;
		}
		return superAttributes(found, context);
	}

	private Optional<EAttributeRoute> superAttributes(String found, EClass context) {
		return context.getEAllSuperTypes().stream()//
				.map(t -> attribute(found, t))//
				.filter(Optional::isPresent)//
				.map(Optional::get)//
				.findFirst();
	}

	@Override
	public Optional<EReferenceRoute> reference(String found, EClass context) {
		Optional<EReferenceRoute> direct = Optional.ofNullable(references.get(key(context, found)));
		if (direct.isPresent()) {
			return direct;
		}
		return superReferences(found, context);
	}

	private Optional<EReferenceRoute> superReferences(String found, EClass context) {
		return context.getEAllSuperTypes().stream()//
				.map(t -> reference(found, t))//
				.filter(Optional::isPresent)//
				.map(Optional::get)//
				.findFirst();
	}

	@Override
	public boolean ignored(String found, EClass location) {
		return ignored.contains(key(location, found));
	}

}
