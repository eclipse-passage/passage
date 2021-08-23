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
package org.eclipse.passage.lic.internal.base.restrictions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.api.io.Hashes;
import org.eclipse.passage.lic.api.restrictions.AgreementState;
import org.eclipse.passage.lic.base.diagnostic.code.ServiceFailedOnMorsel;
import org.eclipse.passage.lic.base.io.UserHomeProductResidence;

public final class AgreementAcceptanceService {

	private final Hashes hashes;
	private final LicensedProduct product;

	public AgreementAcceptanceService(Hashes hashes, LicensedProduct product) {
		this.hashes = hashes;
		this.product = product;
	}

	public void accept(BaseAgreementToAccept agreement) throws Exception {
		byte[] content = agreement.acceptance().content();
		write(acceptedContentFile(content), content);
	}

	public AgreementState accepted(InputStream stream, String name) {
		byte[] content;
		try {
			content = content(stream);
		} catch (IOException e) {
			return new Assessment(name, faliledToReadContentFromStream(e, name));
		}
		return accepted(content, name);

	}

	public AgreementState accepted(byte[] content, String name) {
		Path accepted;
		try {
			accepted = acceptedContentFile(content);
		} catch (Exception e) {
			return new Assessment(name, faliledToLocateContent(e, name));
		}
		return new Assessment(name, content, Files.exists(accepted) && Files.isRegularFile(accepted));
	}

	private Path acceptedContentFile(byte[] content) throws Exception {
		String hash = hash(content);
		String name = chop(hash) + ".txt"; //$NON-NLS-1$
		return new UserHomeProductResidence(product).get().resolve(name);
	}

	private String hash(byte[] content) throws Exception {
		return new String(hashes.get(content), "UTF-8"); //$NON-NLS-1$
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

	private void write(Path target, byte[] content) throws IOException {
		Files.write(target, content);
	}

	private Trouble faliledToReadContentFromStream(IOException e, String name) {
		return new Trouble(//
				new ServiceFailedOnMorsel(), //
				String.format("Failed to read content of agreement [%s]", name), //$NON-NLS-1$
				e);
	}

	private Trouble faliledToLocateContent(Exception e, String name) {
		return new Trouble(//
				new ServiceFailedOnMorsel(), //
				String.format("Failed to locate content for agreement [%s]", name), //$NON-NLS-1$
				e);
	}

}
