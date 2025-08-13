/*******************************************************************************
 * Copyright (c) 2024, 2025 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation; further evolution
 *******************************************************************************/
package org.eclipse.passage.lic.base;

import org.eclipse.passage.lic.api.UserIdentifier;

/**
 * Base implementation for {@link UserIdentifier}
 *
 * @since 4.0
 */
@SuppressWarnings("serial")
public final class BaseUserIdentifier extends BaseIdentifier implements UserIdentifier {

	public BaseUserIdentifier(String identifier) {
		super(identifier);
	}

	@Override
	public String identifier() {
		return value();
	}

}
