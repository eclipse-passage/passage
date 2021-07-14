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
package org.eclipse.passage.lic.internal.net.api;

import org.eclipse.passage.lic.api.Framework;
import org.eclipse.passage.lic.internal.net.api.handle.NetRequest;
import org.eclipse.passage.lic.internal.net.handle.ProductUserRequest;

public interface FrameworkConstructor {

	<R extends NetRequest> Framework forRequest(ProductUserRequest<R> request); //

}
