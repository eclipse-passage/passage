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
package org.eclipse.passage.lic.api.io;

import java.io.InputStream;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.registry.Service;

/**
 * <p>
 * For license issuing for a product, pair of cryptographic keys must be
 * generated and managed for the appropriate {@link LicensedProduct}.
 * </p>
 * <p>
 * This interface covers the service that supplies access to the product's
 * public key content.
 * </p>
 * 
 * @since 2.1
 */
public interface KeyKeeper extends Service<LicensedProduct> {
	/**
	 * <p>
	 * Constructs new input stream to read the public key of a {@code product}
	 * configured for the keeper.
	 * </p>
	 * <p>
	 * Ones get the stream, a burden of closing it lies on one's shoulders as well.
	 * </p>
	 * 
	 * @return never {@code null}, fresh and ready to be used input stream from
	 *         content of the public key file
	 * @throws LicensingException in case of any infrastructure misbehavior or
	 *                            detected content discrepancy.
	 */
	InputStream productPublicKey() throws LicensingException;

}
