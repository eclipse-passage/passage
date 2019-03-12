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
package org.eclipse.passage.lic.internal.equinox.access;

import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_CONDITION_TYPE;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.passage.lic.runtime.access.PermissionEmitter;
import org.eclipse.passage.lic.runtime.access.PermissionEmitterRegistry;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

@Component
public class EquinoxPermissionEmitterRegistry implements PermissionEmitterRegistry {

	private final Map<String, PermissionEmitter> permissionEmitters = new HashMap<>();

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void bindPermissionEmitter(PermissionEmitter emitter, Map<String, Object> properties) {
		Object conditionType = properties.get(LICENSING_CONDITION_TYPE);
		String type = String.valueOf(conditionType);
		permissionEmitters.put(type, emitter);
	}

	public void unbindPermissionEmitter(PermissionEmitter emitter, Map<String, Object> properties) {
		Object conditionType = properties.get(LICENSING_CONDITION_TYPE);
		String type = String.valueOf(conditionType);
		PermissionEmitter existing = permissionEmitters.remove(type);
		if (emitter == existing) {
			permissionEmitters.remove(type);
		}
	}

	@Override
	public Iterable<String> getSupportedConditionTypes() {
		return Collections.unmodifiableSet(permissionEmitters.keySet());
	}

	@Override
	public String getDefaultConditionType() {
		// TODO configure via property?
		return "<undefined>";
	}

	@Override
	public PermissionEmitter getPermissionEmitter(String conditionType) {
		return permissionEmitters.get(conditionType);
	}

	@Override
	public Iterable<? extends PermissionEmitter> getPermissionEmitters() {
		return permissionEmitters.values();
	}

}
