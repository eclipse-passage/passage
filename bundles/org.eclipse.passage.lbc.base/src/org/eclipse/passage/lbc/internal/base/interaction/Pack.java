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
package org.eclipse.passage.lbc.internal.base.interaction;

import java.nio.file.Path;
import java.util.Collections;

import org.eclipse.passage.lic.floating.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.floating.model.api.ProductRef;
import org.eclipse.passage.lic.floating.model.meta.FloatingPackage;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.InvalidLicensedProduct;
import org.eclipse.passage.lic.internal.base.conditions.mining.DecodedContent;
import org.eclipse.passage.lic.internal.base.io.FileKeyKeeper;
import org.eclipse.passage.lic.internal.bc.BcStreamCodec;
import org.eclipse.passage.lic.internal.emf.EObjectFromBytes;

final class Pack {

	private final Path license;
	private final Path key;

	Pack(Path license, Path key) {
		this.license = license;
		this.key = key;
	}

	Resolved resolve() throws LicensingException {
		return new Resolved();
	}

	Path[] content() {
		return new Path[] { license, key };
	}

	@Override
	public String toString() {
		return String.format("pack license=[%s], key=[%s]", license.toAbsolutePath(), key.toAbsolutePath()); //$NON-NLS-1$
	}

	final class Resolved {

		private final FloatingLicensePack content;

		Resolved() throws LicensingException {
			this.content = read(decoded());
		}

		private FloatingLicensePack read(byte[] bytes) throws LicensingException {
			return new EObjectFromBytes<>(//
					bytes, //
					FloatingLicensePack.class//
			).get(Collections.singletonMap(FloatingPackage.eNAME, FloatingPackage.eINSTANCE));
		}

		private byte[] decoded() throws LicensingException {
			return new DecodedContent(//
					license, //
					new FileKeyKeeper(key), //
					new BcStreamCodec(InvalidLicensedProduct::new) // not demanded for decoding, only for error handling
			).get();
		}

		String id() {
			return content.getLicense().getIdentifier();
		}

		LicensedProduct product() {
			ProductRef ref = content.getLicense().getProduct();
			return new BaseLicensedProduct(ref.getProduct(), ref.getVersion());
		}

	}

}
