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
package org.eclipse.passage.lic.equinox;

import org.eclipse.passage.lic.api.FrameworkSupplier;

/**
 * <p>
 * Base for services relying in a framework facilities.
 * </p>
 * <p>
 * Use {@code withFrameworkService} to implement client level secondary services
 * or {@code withFramework} to retrieve parts of configuration directly.
 * 
 * @since 2.1
 */
public final class SuppliedFrameworkAware extends EquinoxFrameworkAware<FrameworkSupplier> {

	public SuppliedFrameworkAware() {
		super(FrameworkSupplier.class, FrameworkSupplier::get);
	}

}
