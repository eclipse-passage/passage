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

import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.internal.base.diagnostic.SumOfLists;
import org.eclipse.passage.lic.internal.base.diagnostic.code.ServiceFailedOnMorsel;
import org.eclipse.passage.lic.internal.base.io.ExternalLicense;

public final class IncomingLicense {

	private final Path residence;

	public IncomingLicense(String residence) {
		Objects.requireNonNull(residence, "IncomingLicense:residence");//$NON-NLS-1$
		this.residence = Paths.get(residence);
		validateResidence();
	}

	public ServiceInvocationResult<List<Path>> upload() {
		ServiceInvocationResult<Collection<Pack>> packs = new PacksFound(residence).get();
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
			Path destination = new ExternalLicense(resolved.product()).install(pack.content());
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

	private final void validateResidence() {
		String info = residence.toAbsolutePath().toString();
		if (!Files.exists(residence)) {
			throw new IllegalArgumentException(String.format("%s does not exist", info)); //$NON-NLS-1$
		}
		if (!Files.isDirectory(residence)) {
			throw new IllegalArgumentException(String.format("%s is not a directory", info)); //$NON-NLS-1$
		}
		if (!Files.isExecutable(residence)) {
			throw new IllegalArgumentException(String.format("%s cannot be read", info)); //$NON-NLS-1$
		}
	}

}
