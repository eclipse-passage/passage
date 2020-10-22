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
package org.eclipse.passage.lic.internal.licenses.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.passage.lic.emf.meta.ClassMetadata;
import org.eclipse.passage.lic.emf.meta.EntityMetadata;
import org.eclipse.passage.lic.emf.meta.PlainEntityMetadata;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

public final class LicensesClassMetadata implements ClassMetadata {

	private final LicensesPackage meta;
	private final Map<Class<?>, EntityMetadata> map;

	public LicensesClassMetadata() {
		meta = LicensesPackage.eINSTANCE;
		map = new HashMap<Class<?>, EntityMetadata>();
		map.put(LicensePlanDescriptor.class, //
				new PlainEntityMetadata(//
						meta.getLicensePlan(), //
						meta.getLicensePlan_Identifier(), //
						meta.getLicensePlan_Name()));
		map.put(LicensePlan.class, map.get(LicensePlanDescriptor.class));
	}

	@Override
	public Optional<EntityMetadata> find(Class<?> clazz) {
		return Optional.ofNullable(map.get(clazz));
	}

}
