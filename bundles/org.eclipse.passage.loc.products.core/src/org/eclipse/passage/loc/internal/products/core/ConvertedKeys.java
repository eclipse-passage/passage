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
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.base.BaseLicensedProduct;
import org.eclipse.passage.lic.base.io.LicensingFolder;
import org.eclipse.passage.lic.base.io.PassageFileExtension;
import org.eclipse.passage.lic.base.io.UserHomePath;
import org.eclipse.passage.lic.keys.model.api.KeyPair;

public final class ConvertedKeys {

	private final String ext = new PassageFileExtension.PublicKey().get();
	private final Consumer<ConvertKeysReport> exposure;

	public ConvertedKeys(Consumer<ConvertKeysReport> exposure) {
		this.exposure = exposure;
	}

	public ConvertedKeys() {
		this(new ToLog());
	}

	@SuppressWarnings("resource")
	public void persist() {
		Path root = new LicensingFolder(new UserHomePath().get()).get();
		Stream<Path> files;
		try {
			files = Files.walk(root); // resource leak never happens
		} catch (IOException e) {
			exposure.accept(failedToScan(root, e));
			return;
		}
		exposure.accept(//
				new ConvertKeysReport(files//
						.filter(Files::isRegularFile)//
						.filter(this::isPublicKey)//
						.map(this::convert)//
						.collect(Collectors.toList())//
				));
	}

	private boolean isPublicKey(Path file) {
		return file.toString().endsWith(ext);
	}

	private ConvertKeysReport.Record convert(Path pub) {
		Optional<LicensedProduct> product = product(pub);
		if (product.isEmpty()) {
			return noProduct(pub);
		}
		Optional<Path> scr = scr(pub);
		if (scr.isEmpty()) {
			return noPair(pub);
		}
		KeyPair pair;
		try {
			pair = new KeyPairUpgraded(product.get(), pub, scr.get()).get();
		} catch (IOException e) {
			return new ConvertKeysReport.ErrorOnKeyReading(pub.getParent(), keyName(pub), e);
		}
		Optional<String> locator;
		try {
			locator = new KeyPairStored(pair).store();
		} catch (LicensingException e) {
			return new ConvertKeysReport.ErrorOnKeyStoring(pub.getParent(), keyName(pub), e);
		}
		return new ConvertKeysReport.Success(pub.getParent(), keyName(pub), locator);
	}

	private String keyName(Path pub) {
		String path = pub.getFileName().toString();
		return path.substring(0, path.length() - ext.length());
	}

	private ConvertKeysReport failedToScan(Path dir, IOException e) {
		return new ConvertKeysReport(new ConvertKeysReport.ErrorOnScan(dir, e));
	}

	private ConvertKeysReport.Record noProduct(Path pub) {
		return new ConvertKeysReport.NoProduct(pub);
	}

	private ConvertKeysReport.Record noPair(Path pub) {
		return new ConvertKeysReport.ScrNotFound(pub);
	}

	private Optional<Path> scr(Path pub) {
		Path scr = pub.getParent().resolve(keyName(pub) + new PassageFileExtension.PrivateKey().get());
		return Files.exists(scr) && Files.isRegularFile(scr) //
				? Optional.of(scr) //
				: Optional.empty();
	}

	private Optional<LicensedProduct> product(Path pub) {
		String name = keyName(pub);
		int separator = name.lastIndexOf('_');
		if (separator < 0 || separator >= name.length()) {
			return productFromFolders(pub);
		}
		return Optional.of(new BaseLicensedProduct(//
				name.substring(0, separator), //
				name.substring(separator + 1)));
	}

	private Optional<LicensedProduct> productFromFolders(Path pub) {
		if (pub.getParent().getParent() == null) { // nio null
			return Optional.empty();
		}
		return Optional.of(new BaseLicensedProduct(//
				pub.getParent().getParent().getFileName().toString(), //
				pub.getParent().getFileName().toString()));
	}

	static final class ToLog implements Consumer<ConvertKeysReport> {

		private final Logger log = LogManager.getLogger(ConvertedKeys.class);

		@Override
		public void accept(ConvertKeysReport report) {
			report.records().forEach(this::print);
		}

		private void print(ConvertKeysReport.Record record) {
			log.info(String.format(//
					"%s || %s", //$NON-NLS-1$
					record.origin().toAbsolutePath(), //
					record.message()));
		}

	}
}
