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
package org.eclipse.passage.lbc.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.eclipse.passage.lbc.internal.api.persistence.BoundLicense;
import org.eclipse.passage.lbc.internal.api.persistence.PersistableLicense;
import org.eclipse.passage.lbc.internal.base.persistence.LockFile;
import org.eclipse.passage.lic.internal.api.conditions.Condition;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @since 1.0
 */
@SuppressWarnings("restriction")
public final class JsonPersistableLicense extends PersistableLicense {

	public JsonPersistableLicense(BoundLicense license) {
		super(license);
	}

	/**
	 * Loads PersistableLicense from <b>existing</b> file
	 * 
	 * @throws IOException if file does not exist
	 */
	public static PersistableLicense load(Condition condition) throws IOException {
		File licenseFile = new LockFile(condition).get().toFile();
		FileInputStream fileReader = new FileInputStream(licenseFile);
		ObjectMapper mapper = new JsonObjectMapper().get();
		String json = new String(fileReader.readAllBytes(), StandardCharsets.UTF_8);
		JsonPersistableLicense license = new JsonPersistableLicense(mapper.readValue(json, BoundLicense.class));
		fileReader.close();
		return license;
	}

	/**
	 * Saves PersistableLicense to <b>existing</b> file
	 * 
	 * @throws IOException if file does not exist
	 */
	@Override
	public void save() throws IOException {
		Files.writeString(new LockFile(get()).get(), new JsonObjectMapper().get().writeValueAsString(get()),
				StandardCharsets.UTF_8);
	}

}
