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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.passage.lbc.internal.api.persistence.BoundLicense;
import org.eclipse.passage.lbc.internal.api.persistence.LoadedLicense;
import org.eclipse.passage.lbc.internal.api.persistence.PersistableLicense;
import org.eclipse.passage.lbc.internal.base.persistence.LockFile;
import org.eclipse.passage.lbc.internal.base.persistence.LockFolder;
import org.eclipse.passage.lic.internal.api.conditions.Condition;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("restriction")
public final class JsonLoadedLicense implements LoadedLicense {

	private final LockFolder base;

	public JsonLoadedLicense(LockFolder base) {
		Objects.requireNonNull(base, "JsonLoadedPersistableLicense::base"); //$NON-NLS-1$
		this.base = base;
	}

	@Override
	public Optional<PersistableLicense> apply(Condition condition) {
		try {
			ObjectMapper mapper = new LbcJsonObjectMapper().get();
			String raw = Files.readString(new LockFile(base, condition).get(), StandardCharsets.UTF_8);
			PersistableLicense license = new JsonPersistableLicense(mapper.readValue(raw, BoundLicense.class), base);
			return Optional.of(license);
		} catch (IOException e) {
			return Optional.empty();
		}
	}

}
