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
package org.eclipse.passage.lic.base.agreements;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.agreements.AgreementAcceptanceService;
import org.eclipse.passage.lic.api.agreements.AgreementState;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.api.io.Hashes;
import org.eclipse.passage.lic.api.io.HashesRegistry;
import org.eclipse.passage.lic.api.registry.Registry;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.base.diagnostic.code.NoServicesOfType;
import org.eclipse.passage.lic.base.diagnostic.code.ServiceFailedOnMorsel;
import org.eclipse.passage.lic.base.io.UserHomeProductResidence;

public final class BaseAgreementAcceptanceService implements AgreementAcceptanceService {

	private final HashesRegistry hashes;
	private final LicensedProduct product;

	public BaseAgreementAcceptanceService(HashesRegistry hashes, Supplier<LicensedProduct> product) {
		this.hashes = hashes;
		this.product = product.get();
	}

	@Override
	public void accept(Supplier<byte[]> agreement) throws Exception {
		byte[] content = agreement.get();
		@SuppressWarnings("hiding")
		Optional<Hashes> hashes = hashingService();
		if (!hashes.isPresent()) {
			throw new LicensingException(new NoServicesOfType("hash calculator").toString()); //$NON-NLS-1$
		}
		write(acceptedContentFile(content, hashes.get()), content);
	}

	@Override
	public AgreementState accepted(byte[] content, String name) {
		@SuppressWarnings("hiding")
		Optional<Hashes> hashes = hashingService();
		if (!hashes.isPresent()) {
			return new Assessment(name, noHashingService());
		}
		Path accepted;
		try {
			accepted = acceptedContentFile(content, hashes.get());
		} catch (Exception e) {
			return new Assessment(name, faliledToLocateContent(e, name));
		}
		return new Assessment(name, content, Files.exists(accepted) && Files.isRegularFile(accepted));
	}

	private Path acceptedContentFile(byte[] content, @SuppressWarnings("hiding") Hashes hashes) throws Exception {
		String bame = toFileName(content, hashes);
		String name = chop(bame) + ".txt"; //$NON-NLS-1$
		return new UserHomeProductResidence(product).get().resolve(name);
	}

	private Optional<Hashes> hashingService() {
		Registry<StringServiceId, Hashes> registry = hashes.get();
		if (registry.services().isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(registry.services().iterator().next());
	}

	private String toFileName(byte[] content, @SuppressWarnings("hiding") Hashes hashes) throws Exception {
		return chop(onlyLegal(base64(hashes.get(content))));
	}

	private String onlyLegal(String string) {
		return IntStream.range(0, string.length())//
				.map(string::charAt)//
				.filter(c -> Character.isLetter(c) || Character.isDigit(c))//
				.mapToObj(c -> Character.toString((char) c))//
				.collect(Collectors.joining());
	}

	private String base64(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}

	/**
	 * Not sure of the hash-string length, but most operating systems prohibit file
	 * names longer than 255 symbols.
	 * 
	 * @return normalized file name, to fit, with the extension added, with 255
	 *         symbols length
	 */
	private String chop(String file) {
		int allowed = 251;
		return file.length() <= allowed ? file : file.substring(0, allowed);
	}

	private void write(Path target, byte[] content) throws IOException {
		Files.write(target, content);
	}

	private Trouble faliledToLocateContent(Exception e, String name) {
		return new Trouble(//
				new ServiceFailedOnMorsel(), //
				String.format("Failed to locate content for agreement [%s]", name), //$NON-NLS-1$
				e);
	}

	private Trouble noHashingService() {
		return new Trouble(//
				new NoServicesOfType("hash calculator"), //$NON-NLS-1$
				"Any agreement assessment is impossible."); //$NON-NLS-1$
	}

	public static final class Smart implements AgreementAcceptanceService {
		private final AgreementAcceptanceService delegate;

		public Smart(AgreementAcceptanceService delegate) {
			super();
			this.delegate = delegate;
		}

		@Override
		public void accept(Supplier<byte[]> agreement) throws Exception {
			delegate.accept(agreement);
		}

		@Override
		public AgreementState accepted(byte[] content, String name) {
			return delegate.accepted(content, name);
		}

		public AgreementState accepted(InputStream stream, String name) {
			byte[] content;
			try {
				content = content(stream);
			} catch (IOException e) {
				return new Assessment(name, faliledToReadContentFromStream(e, name));
			}
			return delegate.accepted(content, name);

		}

		private byte[] content(InputStream content) throws IOException {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int read;
			byte[] data = new byte[16384];
			while ((read = content.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, read);
			}
			buffer.flush();
			byte[] result = buffer.toByteArray();
			buffer.close();
			return result;
		}

		private Trouble faliledToReadContentFromStream(IOException e, String name) {
			return new Trouble(//
					new ServiceFailedOnMorsel(), //
					String.format("Failed to read content of agreement [%s]", name), //$NON-NLS-1$
					e);
		}

	}

}
