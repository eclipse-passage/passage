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
package org.eclipse.passage.lbc.internal.base.chains;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lbc.internal.api.UploadRequest;
import org.eclipse.passage.lbc.internal.api.persistence.BoundLicense;
import org.eclipse.passage.lbc.internal.api.persistence.PersistableLicense;
import org.eclipse.passage.lbc.internal.base.BoundGrant;
import org.eclipse.passage.lbc.internal.base.persistence.LockFile;
import org.eclipse.passage.lbc.internal.base.persistence.LockFolder;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;

public final class Upload implements Function<UploadRequest, ServiceInvocationResult<List<Boolean>>> {

	private final Function<BoundLicense, PersistableLicense> prepare;
	private final LockFolder folder;

	public Upload(Function<BoundLicense, PersistableLicense> prepare) {
		Objects.requireNonNull(prepare, "Receive::prepare"); //$NON-NLS-1$
		this.prepare = prepare;
		this.folder = new LockFolder();
	}

	public Upload(Function<BoundLicense, PersistableLicense> prepare, Supplier<Path> base) {
		Objects.requireNonNull(prepare, "Receive::prepare"); //$NON-NLS-1$
		Objects.requireNonNull(prepare, "Receive::folder"); //$NON-NLS-1$
		this.prepare = prepare;
		this.folder = new LockFolder(base);
	}

	@Override
	public ServiceInvocationResult<List<Boolean>> apply(UploadRequest request) {
		return new BaseServiceInvocationResult<>(request.grants().stream() //
				.map(new BoundGrant()) //
				.map(prepare) //
				.map(this::save) //
				.collect(Collectors.toList()));
	}

	private boolean save(PersistableLicense license) {
		try {
			createFolders();
			createFile(license);
			license.save();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private void createFolders() throws IOException {
		if (!folder.exists()) {
			folder.create();
		}
	}

	private void createFile(PersistableLicense license) throws IOException {
		LockFile file = new LockFile(folder, license.get());
		if (!file.exists()) {
			file.create();
		}
	}

}
