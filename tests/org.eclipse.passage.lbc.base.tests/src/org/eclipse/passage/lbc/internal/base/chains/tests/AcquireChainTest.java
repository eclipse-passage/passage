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
package org.eclipse.passage.lbc.internal.base.chains.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.emf.common.util.URI;
import org.eclipse.passage.lbc.internal.api.persistence.LoadedLicense;
import org.eclipse.passage.lbc.internal.api.persistence.PersistableLicense;
import org.eclipse.passage.lbc.internal.base.chains.Acquire;
import org.eclipse.passage.lbc.internal.base.persistence.LockFolder;
import org.eclipse.passage.lbc.json.JsonDeserialization;
import org.eclipse.passage.lbc.json.JsonLoadedLicense;
import org.eclipse.passage.lbc.json.JsonPersistableLicense;
import org.eclipse.passage.lbc.json.JsonSerialization;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

@SuppressWarnings("restriction")
public final class AcquireChainTest extends ChainTestsBase {

	@Rule
	public final TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void acquireable() {
		createLicense(0, 1);
		assertTrue(new JsonDeserialization<>(ExaminationCertificate.class).apply(acquire()).data().get().restrictions()
				.isEmpty());
	}

	@Test
	public void notAcquireable() {
		createLicense(1, 1);
		assertFalse(new JsonDeserialization<>(ExaminationCertificate.class).apply(acquire()).data().get().restrictions()
				.isEmpty());
	}

	@Test
	public void notExisting() {
		assertFalse(new JsonDeserialization<>(ExaminationCertificate.class).apply(acquire()).data().get().restrictions()
				.isEmpty());
	}

	private String acquire() {
		return new Acquire(new JsonDeserialization<>(ExaminationCertificate.class),
				new JsonSerialization<ExaminationCertificate>(), load()).apply(certificateRequest());
	}

	private void createLicense(int taken, int capacity) {
		try {
			PersistableLicense persistable = new JsonPersistableLicense(boundLicense(taken, capacity),
					new LockFolder(() -> root()));
			folder.newFolder("locked"); //$NON-NLS-1$
			folder.newFile(condition().identifier());
			persistable.save();
		} catch (IOException e) {
			fail();
		}
	}

	private Path root() {
		return Paths.get(URI.decode(folder.getRoot().getAbsolutePath()));
	}

	private LoadedLicense load() {
		return new JsonLoadedLicense(new LockFolder(() -> root()));
	}

}