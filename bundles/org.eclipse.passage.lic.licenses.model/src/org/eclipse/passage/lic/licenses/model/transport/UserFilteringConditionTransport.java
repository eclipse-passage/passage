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
package org.eclipse.passage.lic.licenses.model.transport;

import java.util.Objects;
import java.util.function.Supplier;

public final class UserFilteringConditionTransport extends BaseXmiConditionTransport {

	public UserFilteringConditionTransport(Supplier<String> user) {
		super(pack -> user.get().equals(pack.getLicense().getUser().getIdentifier()));
		Objects.requireNonNull(user, "UserFilteringConditionTransport::user"); //$NON-NLS-1$
	}

}
