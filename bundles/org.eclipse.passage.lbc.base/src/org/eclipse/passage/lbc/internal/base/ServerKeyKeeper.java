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
package org.eclipse.passage.lbc.internal.base;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.function.Supplier;

import org.eclipse.passage.lbc.internal.base.i18n.BaseMessages;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.io.KeyKeeper;
import org.eclipse.passage.lic.internal.base.io.FileNameFromLicensedProduct;
import org.eclipse.passage.lic.internal.base.io.LicensingFolder;
import org.eclipse.passage.lic.internal.base.io.PassageFileExtension;
import org.eclipse.passage.lic.internal.base.io.UserHomePath;

/**
 * @since 1.0
 */
public class ServerKeyKeeper implements KeyKeeper {

	private final Supplier<LicensedProduct> product;

	public ServerKeyKeeper(Supplier<LicensedProduct> product) {
		this.product = product;
	}

	@Override
	public LicensedProduct id() {
		return product.get();
	}

	@Override
	public InputStream productPublicKey() throws LicensingException {
		String path = new LicensingFolder(new UserHomePath()).get().resolve(keyFile()).toString();
		try {
			return new FileInputStream(path);
		} catch (Exception e) {
			throw new LicensingException(BaseMessages.ServerKeyKeeper_input_stream_error);
		}
	}

	private String keyFile() {
		return new FileNameFromLicensedProduct(product.get(), new PassageFileExtension.PublicKey()).get();
	}

}
