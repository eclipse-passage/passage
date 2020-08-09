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
package org.eclipse.passage.lbc.base.tests;

import org.eclipse.passage.lbc.internal.api.MiningRequest;
import org.eclipse.passage.lbc.internal.base.Requester;
import org.eclipse.passage.lic.internal.base.ProductIdentifier;
import org.eclipse.passage.lic.internal.base.ProductVersion;

public final class FakeMiningRequest implements MiningRequest {

	@Override
	public ProductIdentifier getProductIdentifier() {
		return new ProductIdentifier("identifier"); //$NON-NLS-1$
	}

	@Override
	public ProductVersion getProductVersion() {
		return new ProductVersion("version"); //$NON-NLS-1$
	}

	@Override
	public Requester getRequester() {
		return new Requester("temporal"); //$NON-NLS-1$
	}

}
