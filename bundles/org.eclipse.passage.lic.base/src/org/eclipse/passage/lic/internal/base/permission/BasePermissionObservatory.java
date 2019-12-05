/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
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
package org.eclipse.passage.lic.internal.base.permission;

import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.StreamSupport;

import org.eclipse.passage.lic.api.access.FeaturePermission;
import org.eclipse.passage.lic.internal.base.permission.observatory.CheckSchedule;
import org.eclipse.passage.lic.internal.base.permission.observatory.Observatory;

/**
 * <p>
 * Base implementation for {@linkplain PermissionObservatory} component.
 * </p>
 * <p>
 * Covers {@linkplain Observatory} tuned for permission tracking and is it's
 * representation for the domain specific environment.
 * </p>
 * 
 * @since 0.6
 */
public final class BasePermissionObservatory implements PermissionObservatory {
	private final Observatory<LimitedPermission> observatory;

	public BasePermissionObservatory(CheckSchedule schedule, Consumer<Set<LimitedPermission>> farewell) {
		this.observatory = new Observatory<LimitedPermission>(schedule, farewell);
	}

	public void watch(Iterable<FeaturePermission> permissions) {
		onEachPermission(permissions, limited -> observatory.watch(limited));
	}

	public void forget(Iterable<FeaturePermission> permissions) {
		onEachPermission(permissions, limited -> observatory.forget(limited));
	}

	public void open() {
		observatory.open();
	}

	private void onEachPermission(Iterable<FeaturePermission> permissions, Consumer<LimitedPermission> action) {
		StreamSupport.stream(permissions.spliterator(), false)//
				.map(LimitedPermission::new) //
				.forEach(limited -> action.accept(limited));
	}

}
