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
import java.util.Optional;

import org.eclipse.passage.lbc.internal.base.api.FlsGear;
import org.eclipse.passage.lbc.internal.base.api.FlsGearAwre;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.base.BaseLicensedProduct;
import org.eclipse.passage.lic.base.InvalidLicensedProduct;
import org.eclipse.passage.lic.base.conditions.mining.DecodedContent;
import org.eclipse.passage.lic.base.io.FileKeyKeeper;
import org.eclipse.passage.lic.internal.emf.EObjectFromBytes;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.licenses.model.api.ProductRef;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.lic.licenses.model.util.LicensesResourceImpl;

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
			// FIXME:AF: should be done via factory
			return new EObjectFromBytes<>(//
					bytes, //
					FloatingLicensePack.class, //
					LicensesResourceImpl::new//
			).get(Collections.singletonMap(LicensesPackage.eNAME, LicensesPackage.eINSTANCE));
		}

		private byte[] decoded() throws LicensingException {
			return new FlsGearAwre()//
					.withGear(g -> Optional.of(decoded(g)))//
					.orElseThrow(() -> new LicensingException("Fail resolving gear")); //$NON-NLS-1$
		}

		private final byte[] decoded(FlsGear gear) {
			try {
				return new DecodedContent(//
						license, //
						new FileKeyKeeper(key), //
						// 'product' is not demanded for decoding, only for error handling
						gear.codec(new InvalidLicensedProduct())//
				).get();
			} catch (LicensingException e) {
				e.printStackTrace(); // truly! outputs a handles by console
				return new byte[0];
			}
		}

		LicensedProduct product() {
			ProductRef ref = content.getLicense().getProduct();
			return new BaseLicensedProduct(ref.getIdentifier(), ref.getVersion());
		}

	}

}
