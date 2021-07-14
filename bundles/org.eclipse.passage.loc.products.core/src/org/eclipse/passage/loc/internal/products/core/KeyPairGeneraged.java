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

import java.util.function.Supplier;

import org.eclipse.passage.lic.api.io.StreamCodec;
import org.eclipse.passage.lic.keys.model.api.KeyPair;
import org.eclipse.passage.lic.keys.model.api.ProductRef;
import org.eclipse.passage.lic.keys.model.meta.KeysFactory;

final class KeyPairGeneraged implements Supplier<KeyPair> {

	private final StreamCodec author;
	private final byte[] pub;
	private final byte[] scr;

	KeyPairGeneraged(StreamCodec author, byte[] pub, byte[] scr) {
		this.author = author;
		this.pub = pub;
		this.scr = scr;
	}

	@Override
	public KeyPair get() {
		KeyPair pair = KeysFactory.eINSTANCE.createKeyPair();
		pair.setProduct(product());
		pair.setAlgorithm(author.algorithm().name());
		pair.setKey(author.keySize().size());
		pair.setPub(new String(pub));
		pair.setScr(new String(scr));
		return pair;
	}

	private ProductRef product() {
		ProductRef product = KeysFactory.eINSTANCE.createProductRef();
		product.setIdentifier(author.id().identifier());
		product.setVersion(author.id().version());
		return product;
	}

}
