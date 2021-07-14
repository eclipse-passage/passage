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
package org.eclipse.passage.lbc.internal.base.interaction;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.base.diagnostic.SumOfLists;
import org.eclipse.passage.lic.base.diagnostic.code.ServiceFailedOnMorsel;
import org.eclipse.passage.lic.base.io.ExternalLicense;

public final class IncomingLicense {

	private final Path origin;
	private final Path storage;

	public IncomingLicense(String origin, Path storage) {
		Objects.requireNonNull(origin, "IncomingLicense:origin");//$NON-NLS-1$
		Objects.requireNonNull(storage, "IncomingLicense:storage");//$NON-NLS-1$
		this.origin = Paths.get(origin);
		this.storage = storage;
		validateOrigin();
	}

	public ServiceInvocationResult<List<Path>> upload() {
		ServiceInvocationResult<Collection<Pack>> packs = new PacksFound(origin).get();
		if (!packs.data().isPresent() || packs.data().get().isEmpty()) {
			return new BaseServiceInvocationResult<>(packs.diagnostic());
		}
		return packs.data().get().stream()//
				.map(this::uploadPack)//
				.reduce(new BaseServiceInvocationResult<>(Collections.emptyList()),
						new BaseServiceInvocationResult.Sum<>(new SumOfLists<>()));
	}

	private ServiceInvocationResult<List<Path>> uploadPack(Pack pack) {
		try {
			Pack.Resolved resolved = pack.resolve();
			Path destination = new ExternalLicense(storage, resolved.product()).install(pack.content());
			return new BaseServiceInvocationResult<>(Collections.singletonList(destination));
		} catch (Exception e) {
			return failedToUploadPack(pack, e);
		}
	}

	private BaseServiceInvocationResult<List<Path>> failedToUploadPack(Pack pack, Exception e) {
		return new BaseServiceInvocationResult<>(new BaseDiagnostic(Collections.singletonList(//
				new Trouble(//
						new ServiceFailedOnMorsel(), //
						String.format("Failed to upload %s", pack), //$NON-NLS-1$
						e))));
	}

	private final void validateOrigin() {
		String info = origin.toAbsolutePath().toString();
		if (!Files.exists(origin)) {
			throw new IllegalArgumentException(String.format("%s does not exist", info)); //$NON-NLS-1$
		}
		if (!Files.isDirectory(origin)) {
			throw new IllegalArgumentException(String.format("%s is not a directory", info)); //$NON-NLS-1$
		}
		if (!Files.isExecutable(origin)) {
			throw new IllegalArgumentException(String.format("%s cannot be read", info)); //$NON-NLS-1$
		}
	}

}
