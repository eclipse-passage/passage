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
package org.eclipse.passage.lic.internal.base.tests.conditions.mining;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.KeyKeeper;
import org.eclipse.passage.lic.internal.base.tests.conditions.mining.LocalConditionsTest.Spy;

final class RuntimeKeyKeeper implements KeyKeeper {

	private final LicensedProduct product;
	private final Spy spy;

	RuntimeKeyKeeper(LicensedProduct product, Spy spy) {
		this.product = product;
		this.spy = spy;
	}

	@Override
	public LicensedProduct id() {
		return product;
	}

	@Override
	public InputStream productPublicKey() throws LicensingException {
		spy.keyAsked();
		return new ByteArrayInputStream("key-not-used".getBytes()); //$NON-NLS-1$
	}

}
