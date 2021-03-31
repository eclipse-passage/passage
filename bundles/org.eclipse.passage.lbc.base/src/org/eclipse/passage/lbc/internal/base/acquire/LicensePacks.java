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
package org.eclipse.passage.lbc.internal.base.acquire;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.passage.lic.floating.FloatingFileExtension;
import org.eclipse.passage.lic.floating.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.floating.model.meta.FloatingPackage;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.io.KeyKeeper;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;
import org.eclipse.passage.lic.internal.base.conditions.mining.DecodedContent;
import org.eclipse.passage.lic.internal.base.io.FileCollection;
import org.eclipse.passage.lic.internal.base.io.PathFromLicensedProduct;
import org.eclipse.passage.lic.internal.emf.EObjectFromBytes;

final class LicensePacks {

	private final LicensedProduct product;
	private final KeyKeeper key;
	private final StreamCodec codec;
	private final Supplier<Path> base;
	private final Logger log = LogManager.getLogger(getClass());

	public LicensePacks(KeyKeeper key, StreamCodec codec, LicensedProduct product, Supplier<Path> base) {
		this.product = product;
		this.key = key;
		this.codec = codec;
		this.base = base;
	}

	Collection<FloatingLicensePack> get() throws LicensingException {
		return files().stream() //
				.map(this::pack) //
				.filter(Optional::isPresent) //
				.map(Optional::get) //
				.collect(Collectors.toList());
	}

	private Collection<Path> files() throws LicensingException {
		return new FileCollection(//
				new PathFromLicensedProduct(base, product), //
				new FloatingFileExtension.FloatingLicenseEncrypted()//
		).get();
	}

	private Optional<FloatingLicensePack> pack(Path license) {
		FloatingLicensePack pack;
		try {
			pack = new EObjectFromBytes<>(decoded(license), FloatingLicensePack.class)//
					.get(Collections.singletonMap(FloatingPackage.eNS_URI, FloatingPackage.eINSTANCE));
		} catch (LicensingException e) {
			log.error("failed: ", e); //$NON-NLS-1$
			return Optional.empty();
		}
		return Optional.of(pack);
	}

	private byte[] decoded(Path source) throws LicensingException {
		return new DecodedContent(source, key, codec).get();
	}

}
