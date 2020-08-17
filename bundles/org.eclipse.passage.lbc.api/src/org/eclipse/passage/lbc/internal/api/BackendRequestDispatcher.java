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
package org.eclipse.passage.lbc.internal.api;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;

/**
 * @since 1.0
 */
public interface BackendRequestDispatcher {

	void dispatch(LicensingRequest request, LicensingResponse result) throws IOException;

	void addChain(Supplier<Optional<ConditionAction>> action, Chain chain);
}
