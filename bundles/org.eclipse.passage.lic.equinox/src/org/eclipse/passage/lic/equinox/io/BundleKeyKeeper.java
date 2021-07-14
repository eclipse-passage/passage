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
package org.eclipse.passage.lic.equinox.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.KeyKeeper;
import org.eclipse.passage.lic.base.io.FileNameFromLicensedProduct;
import org.eclipse.passage.lic.base.io.PassageFileExtension;
import org.eclipse.passage.lic.equinox.BundleResource;
import org.eclipse.passage.lic.internal.equinox.i18n.EquinoxMessages;
import org.osgi.framework.Bundle;

/**
 * Look for the product's public key into OSGI-INF folder of the configured
 * bundle.
 * 
 * @since 2.1
 */
public final class BundleKeyKeeper implements KeyKeeper {

	private final Supplier<LicensedProduct> product;
	private final Bundle bundle;
	private final String midpath;

	public BundleKeyKeeper(Supplier<LicensedProduct> product, Supplier<Bundle> bundle) {
		this(product, bundle.get());
	}

	public BundleKeyKeeper(Supplier<LicensedProduct> product, Bundle bundle) {
		this(product, bundle, "OSGI-INF"); //$NON-NLS-1$
	}

	public BundleKeyKeeper(Supplier<LicensedProduct> product, Bundle bundle, String midpath) {
		Objects.requireNonNull(product, "BundleKeyKeeper::product"); //$NON-NLS-1$
		Objects.requireNonNull(bundle, "BundleKeyKeeper::bundle"); //$NON-NLS-1$
		this.product = product;
		this.bundle = bundle;
		this.midpath = midpath;
	}

	@Override
	public LicensedProduct id() {
		return product.get();
	}

	@Override
	public InputStream productPublicKey() throws LicensingException {
		URL resource = resource(Paths.get(midpath).resolve(keyFile()));
		try {
			return resource.openStream();
		} catch (IOException e) {
			throw new LicensingException(String.format(//
					EquinoxMessages.BundleKeyKeeper_failed_to_open_stream, //
					product.get(), //
					resource.toString()));
		}
	}

	/**
	 * Either returns functional URL for the given {@code path} under the configured
	 * {@code bundle}, or fails with properly explained
	 * {@linkplain LicensingException}
	 */
	private URL resource(Path path) throws LicensingException {
		Optional<URL> url = new BundleResource(bundle, path).get();
		if (!url.isPresent()) {
			throw new LicensingException(String.format(//
					EquinoxMessages.BundleKeyKeeper_failed_to_find_file, //
					product.get(), //
					path.toString(), //
					bundle.getSymbolicName()));
		}
		return url.get();
	}

	private String keyFile() {
		return new FileNameFromLicensedProduct(product.get(), new PassageFileExtension.PublicKey()).get();
	}

}
