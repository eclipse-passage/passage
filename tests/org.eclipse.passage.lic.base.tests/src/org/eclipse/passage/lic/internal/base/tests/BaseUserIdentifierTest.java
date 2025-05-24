/*******************************************************************************
 * Copyright (c) 2024 ArSysOp
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

package org.eclipse.passage.lic.internal.base.tests;

import org.eclipse.passage.lic.api.UserIdentifier;
import org.eclipse.passage.lic.api.tests.IdentifierContractTest;
import org.eclipse.passage.lic.base.BaseUserIdentifier;

@SuppressWarnings("restriction")
public final class BaseUserIdentifierTest extends IdentifierContractTest.User {

	@Override
	protected UserIdentifier identifierForInput(String input) {
		return new BaseUserIdentifier(input);
	}

	@Override
	protected UserIdentifier theMostInvalidIdentifier() {
		return new BaseUserIdentifier("invalid"); //$NON-NLS-1$
	}

}
