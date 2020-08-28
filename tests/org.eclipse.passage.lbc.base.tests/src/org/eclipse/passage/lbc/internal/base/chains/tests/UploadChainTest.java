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

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.emf.common.util.URI;
import org.eclipse.passage.lbc.base.tests.LbcTestsBase;
import org.eclipse.passage.lbc.internal.api.persistence.PersistableLicense;
import org.eclipse.passage.lbc.internal.base.BaseUploadRequest;
import org.eclipse.passage.lbc.internal.base.chains.Upload;
import org.eclipse.passage.lbc.json.JsonLoadedPersistableLicense;
import org.eclipse.passage.lbc.json.JsonPersistableLicense;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

@SuppressWarnings("restriction")
public final class UploadChainTest extends LbcTestsBase {

	@Rule
	public final TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void uploadNonExisting() {
		new Upload(bound -> new JsonPersistableLicense(bound, base()), base())
				.apply(new BaseUploadRequest(grants(), requester()));

		PersistableLicense license = new JsonLoadedPersistableLicense(base()).apply(condition()).get();
		assertEquals(identifierValue(), license.get().identifier().get().get());
		assertEquals(Integer.valueOf(3), license.get().capacity().get().get());
	}

	private Supplier<Path> base() {
		return () -> root();
	}

	private List<LicenseGrant> grants() {
		return Arrays.asList(grant(identifierValue(), 3));
	}

	private Path root() {
		return Paths.get(URI.decode(folder.getRoot().getAbsolutePath()));
	}

}
