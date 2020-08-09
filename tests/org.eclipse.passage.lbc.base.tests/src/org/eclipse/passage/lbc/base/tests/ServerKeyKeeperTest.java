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
package org.eclipse.passage.lbc.base.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.eclipse.passage.lbc.internal.base.ServerKeyKeeper;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.io.FileNameFromLicensedProduct;
import org.eclipse.passage.lic.internal.base.io.LicensingFolder;
import org.eclipse.passage.lic.internal.base.io.PassageFileExtension;
import org.eclipse.passage.lic.internal.base.io.UserHomePath;
import org.junit.Test;

@SuppressWarnings("restriction")
public class ServerKeyKeeperTest extends LbcTestsBase {

	@Test
	public void packInfo() {
		assertEquals(product().identifier(), keyKeeper().id().identifier());
		assertEquals(product().version(), keyKeeper().id().version());
	}

	@Test
	public void positive() {
		try {
			createFile();
			String contents = read();
			deleteFile();
			assertEquals(message(), contents);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} catch (LicensingException e) {
			fail();
		}
	}

	@Test
	public void wrongFile() {
		assertThrows(LicensingException.class, () -> {
			// No file created, throws exception
			read();
		});
	}

	private String read() throws LicensingException, IOException {
		InputStream stream = keyKeeper().productPublicKey();
		String contents = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
		stream.close();
		return contents;
	}

	private void deleteFile() throws IOException {
		Files.deleteIfExists(path());
	}

	private void createFile() throws IOException {
		Files.createDirectories(path().getParent());
		Files.createFile(path());
		Files.writeString(path(), message(), StandardCharsets.UTF_8,
				new OpenOption[] { StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND });
	}

	private BaseLicensedProduct product() {
		return new BaseLicensedProduct(identifierValue(), versionValue());
	}

	private ServerKeyKeeper keyKeeper() {
		return new ServerKeyKeeper(() -> product());
	}

	private Path path() {
		return Paths.get(new LicensingFolder(new UserHomePath()).get()
				.resolve(new FileNameFromLicensedProduct(product(), new PassageFileExtension.PublicKey()).get())
				.toString());
	}

	private String message() {
		return "I am a key, just believe me"; //$NON-NLS-1$
	}

}
