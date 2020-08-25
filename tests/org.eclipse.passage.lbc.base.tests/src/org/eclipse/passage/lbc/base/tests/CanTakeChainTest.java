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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.emf.common.util.URI;
import org.eclipse.passage.lbc.internal.api.persistence.PersistableLicense;
import org.eclipse.passage.lbc.internal.base.BaseRequestedCondition;
import org.eclipse.passage.lbc.internal.base.chains.CanTake;
import org.eclipse.passage.lbc.json.JsonLoadedPersistableLicense;
import org.eclipse.passage.lbc.json.JsonPersistableLicense;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public final class CanTakeChainTest extends LbcTestsBase {

	@Rule
	public final TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void existingLicense() {
		try {
			PersistableLicense persistable = new JsonPersistableLicense(boundLicense(1, 1), () -> root());
			folder.newFolder("locked"); //$NON-NLS-1$
			folder.newFile(condition().identifier());
			persistable.save();
		} catch (IOException e) {
			fail();
		}
		assertFalse(new CanTake(new JsonLoadedPersistableLicense(() -> root()))
				.apply(new BaseRequestedCondition(condition(), requester())).data().get());
	}

	@Test
	public void nonExistingLicense() {
		assertFalse(new CanTake(new JsonLoadedPersistableLicense(() -> root()))
				.apply(new BaseRequestedCondition(condition(), requester())).diagnostic().severe().isEmpty());
	}

	private Path root() {
		return Paths.get(URI.decode(folder.getRoot().getAbsolutePath()));
	}

}
