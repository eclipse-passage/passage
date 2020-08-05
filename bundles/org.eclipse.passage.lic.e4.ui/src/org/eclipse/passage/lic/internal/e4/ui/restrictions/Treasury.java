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
package org.eclipse.passage.lic.internal.e4.ui.restrictions;

import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

final class Treasury implements Supplier<Optional<IEclipseContext>> {

	@Override
	public Optional<IEclipseContext> get() {
		return Optional.ofNullable(FrameworkUtil.getBundle(getClass()))//
				.map(Bundle::getBundleContext)//
				.map(c -> EclipseContextFactory.getServiceContext(c));
	}

}
