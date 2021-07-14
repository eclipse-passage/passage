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
package org.eclipse.passage.loc.internal.products.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.io.EncryptionAlgorithm;
import org.eclipse.passage.lic.api.io.EncryptionKeySize;
import org.eclipse.passage.lic.keys.model.api.KeyPair;
import org.eclipse.passage.lic.keys.model.api.ProductRef;
import org.eclipse.passage.lic.keys.model.meta.KeysFactory;

public final class KeyPairUpgraded {

	private final LicensedProduct product;
	private final Path pub;
	private final Path scr;

	public KeyPairUpgraded(LicensedProduct product, Path pub, Path scr) {
		this.product = product;
		this.pub = pub;
		this.scr = scr;
	}

	public KeyPair get() throws IOException {
		KeyPair pair = KeysFactory.eINSTANCE.createKeyPair();
		pair.setProduct(product());
		pair.setAlgorithm(new EncryptionAlgorithm.Default().name());
		pair.setKey(new EncryptionKeySize.Default().size());
		pair.setPub(fileContent(pub));
		pair.setScr(fileContent(scr));
		return pair;
	}

	private ProductRef product() {
		ProductRef ref = KeysFactory.eINSTANCE.createProductRef();
		ref.setIdentifier(product.identifier());
		ref.setVersion(product.version());
		return ref;
	}

	private String fileContent(Path file) throws IOException {
		return new String(Files.readAllBytes(file));
	}
}
