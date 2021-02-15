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
package org.eclipse.passage.lic.internal.equinox;

import org.eclipse.passage.lic.internal.api.FrameworkSupplier;

/**
 * <p>
 * Base for services relying in a framework facilities.
 * </p>
 * <p>
 * Use {@code withFrameworkService} to implement client level secondary services
 * or {@code withFramework} to retrieve parts of configuration directly.
 */
public abstract class SuppliedFrameworkAware extends FrameworkAware<FrameworkSupplier> {

	protected SuppliedFrameworkAware() {
		super(FrameworkSupplier.class, FrameworkSupplier::get);
	}

}
