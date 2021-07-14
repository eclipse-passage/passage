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

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.SumOfCollections;
import org.eclipse.passage.lic.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.base.diagnostic.code.NoDataOfType;
import org.eclipse.passage.lic.base.diagnostic.code.ServiceFailedOnMorsel;
import org.eclipse.passage.lic.base.io.FloatingFileExtension;
import org.eclipse.passage.lic.base.io.PassageFileExtension;

final class PacksFound implements Supplier<ServiceInvocationResult<Collection<Pack>>> {

	private final Path source;
	private final String flicen = new FloatingFileExtension.FloatingLicenseEncrypted().get();
	private final String pub = new PassageFileExtension.PublicKey().get();

	public PacksFound(Path source) {
		this.source = source;
	}

	@Override
	public ServiceInvocationResult<Collection<Pack>> get() {
		List<ServiceInvocationResult<Collection<Pack>>> packs = new ArrayList<>();
		searchThroughDirectory(source.toFile(), packs);
		if (packs.isEmpty()) {
			return new BaseServiceInvocationResult<Collection<Pack>>(
					new Trouble(new NoDataOfType(), "floating license")); //$NON-NLS-1$
		}
		return packs.stream().reduce(//
				new BaseServiceInvocationResult<>(Collections.emptyList()), //
				new BaseServiceInvocationResult.Sum<>(new SumOfCollections<>()));
	}

	private void searchThroughDirectory(File parent, List<ServiceInvocationResult<Collection<Pack>>> packs) {
		for (File file : parent.listFiles()) {
			if (file.isDirectory()) {
				searchThroughDirectory(file, packs);
			} else {
				maybePack(file).ifPresent(packs::add);
			}
		}
	}

	private Optional<ServiceInvocationResult<Collection<Pack>>> maybePack(File license) {
		if (!license.getName().endsWith(flicen)) {
			return Optional.empty();
		}
		Path key;
		try {
			key = new OnlyFileOfType(license.getParentFile().toPath(), pub).get();
		} catch (Exception e) {
			return Optional.of(failToFindKey(license, e));
		}
		return Optional.of(packIndeed(license, key));

	}

	private ServiceInvocationResult<Collection<Pack>> packIndeed(File license, Path key) {
		return new BaseServiceInvocationResult<>(Collections.singletonList(new Pack(license.toPath(), key)));
	}

	private ServiceInvocationResult<Collection<Pack>> failToFindKey(File license, Exception e) {
		Trouble error = new Trouble(//
				new ServiceFailedOnMorsel(), //
				String.format("Failed to find public key for license file %s", //$NON-NLS-1$
						license.getAbsolutePath()),
				e);
		return new BaseServiceInvocationResult<>(new BaseDiagnostic(Collections.singletonList(error)));
	}
}
