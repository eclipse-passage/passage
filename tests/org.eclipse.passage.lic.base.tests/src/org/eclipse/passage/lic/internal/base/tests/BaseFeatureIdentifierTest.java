/*******************************************************************************
 * Copyright (c) 2025 ArSysOp
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

import org.eclipse.passage.lic.api.FeatureIdentifier;
import org.eclipse.passage.lic.api.tests.IdentifierContractTest;
import org.eclipse.passage.lic.base.BaseFeatureIdentifier;

@SuppressWarnings("restriction")
public final class BaseFeatureIdentifierTest extends IdentifierContractTest.Feature {

	@Override
	protected FeatureIdentifier identifierForInput(String input) {
		return new BaseFeatureIdentifier(input);
	}

	@Override
	protected FeatureIdentifier theMostInvalidIdentifier() {
		return new BaseFeatureIdentifier("invalid"); //$NON-NLS-1$
	}

}
