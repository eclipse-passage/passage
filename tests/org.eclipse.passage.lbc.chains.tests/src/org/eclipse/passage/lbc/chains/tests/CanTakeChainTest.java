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
package org.eclipse.passage.lbc.chains.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.emf.common.util.URI;
import org.eclipse.passage.lbc.chains.CanTake;
import org.eclipse.passage.lbc.internal.api.persistence.PersistableLicense;
import org.eclipse.passage.lbc.internal.base.persistence.LockFolder;
import org.eclipse.passage.lbc.internal.base.troubles.ConditionEntryNotFound;
import org.eclipse.passage.lbc.json.JsonPersistableLicense;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

@SuppressWarnings("restriction")
public final class CanTakeChainTest extends ChainTestsBase {

	@Rule
	public final TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void existingLicense() {
		try {
			PersistableLicense persistable = new JsonPersistableLicense(boundLicense(1, 1),
					new LockFolder(() -> root()));
			folder.newFolder("locked"); //$NON-NLS-1$
			folder.newFile(condition().identifier());
			persistable.save();
		} catch (IOException e) {
			fail();
		}
		assertEquals("{ result: \"false\"}", chain()); //$NON-NLS-1$
	}

	@Test
	public void nonExistingLicense() {
		assertEquals("{ error: \"" + new ConditionEntryNotFound().explanation() + "\"}", chain()); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private String chain() {
		return new CanTake(new LockFolder(() -> root())).apply(conditionRequest());
	}

	private Path root() {
		return Paths.get(URI.decode(folder.getRoot().getAbsolutePath()));
	}

}
