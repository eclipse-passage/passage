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
package org.eclipse.passage.loc.operator.seal;

import java.util.Optional;

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.FrameworkSupplier;
import org.osgi.service.component.annotations.Component;

@SuppressWarnings("restriction")
@Component
public final class OperatorFrameworkSupplier implements FrameworkSupplier {

	@Override
	public Optional<Framework> get() {
		return Optional.of(OperatorFramework.instance);
	}

}
