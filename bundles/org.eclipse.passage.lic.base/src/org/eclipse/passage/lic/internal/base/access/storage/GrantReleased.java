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
package org.eclipse.passage.lic.internal.base.access.storage;

import java.util.function.Predicate;

import org.eclipse.passage.lic.api.ServiceInvocationResult;

/**
 * @since 2.3
 */
public final class GrantReleased implements Predicate<ServiceInvocationResult<Boolean>> {

	@Override
	public boolean test(ServiceInvocationResult<Boolean> response) {
		return response.data().isPresent() && response.data().get();
	}

}
