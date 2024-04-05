/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.hc.internal.remote;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.hc.remote.Connection;
import org.eclipse.passage.lic.hc.remote.Request;

/**
 * 
 * @since 1.1
 */
public interface Client<C extends Connection, T> {

	ServiceInvocationResult<T> request(Request<C> request, ResponseHandler<T> handler);

}
