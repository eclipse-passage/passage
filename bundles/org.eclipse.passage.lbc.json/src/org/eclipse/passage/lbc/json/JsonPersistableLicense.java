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
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.passage.lbc.internal.api.persistence.BoundLicense;
import org.eclipse.passage.lbc.internal.api.persistence.PersistableLicense;
import org.eclipse.passage.lbc.internal.base.BaseBoundLicense;
import org.eclipse.passage.lbc.internal.base.ConditionIdentifier;
import org.eclipse.passage.lbc.internal.base.LicenseCapacity;
import org.eclipse.passage.lbc.internal.base.LicenseTaken;
import org.eclipse.passage.lbc.internal.base.persistence.LockFile;
import org.eclipse.passage.lbc.internal.base.persistence.LockFolder;

/**
 * @since 1.0
 */
@SuppressWarnings("restriction")
public final class JsonPersistableLicense extends PersistableLicense {

	private final Supplier<Path> base;

	public JsonPersistableLicense(BoundLicense license, Supplier<Path> base) {
		super(license);
		Objects.requireNonNull(base, "JsonPersistableLicense::base"); //$NON-NLS-1$
		this.base = base;
	}

	/**
	 * Saves PersistableLicense to <b>existing</b> file
	 * 
	 * @throws IOException if file does not exist
	 */
	@Override
	public void save() throws IOException {
		Files.writeString(new LockFile(new LockFolder(base), get()).get(),
				new LbcJsonObjectMapper().get().writeValueAsString(get()), StandardCharsets.UTF_8);
	}

	public void writeInitial() throws IOException {
		save();
	}

	@Override
	public boolean takeOne() {
		try {
			offsetLicense(1).save();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public boolean releaseOne() {
		try {
			offsetLicense(-1).save();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	private JsonPersistableLicense offsetLicense(int offset) {
		return new JsonPersistableLicense(
				new BaseBoundLicense(new ConditionIdentifier(get().identifier()),
						new LicenseTaken(get().taken().get().get() + offset), new LicenseCapacity(get().capacity())),
				base);
	}

}
