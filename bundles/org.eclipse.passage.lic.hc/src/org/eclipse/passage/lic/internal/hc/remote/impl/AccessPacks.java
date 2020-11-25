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
package org.eclipse.passage.lic.internal.hc.remote.impl;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.api.io.KeyKeeper;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.conditions.mining.DecodedContent;
import org.eclipse.passage.lic.internal.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.internal.base.diagnostic.code.ServiceCannotOperate;
import org.eclipse.passage.lic.internal.base.diagnostic.code.ServiceFailedOnInfrastructureDenial;
import org.eclipse.passage.lic.internal.base.diagnostic.code.ServiceFailedOnMorsel;
import org.eclipse.passage.lic.internal.emf.EObjectFromBytes;
import org.eclipse.passage.lic.internal.hc.i18n.AccessMessages;

public final class AccessPacks implements Supplier<ServiceInvocationResult<Collection<FloatingLicenseAccess>>> {

	private final LicensedProduct product;
	private final KeyKeeperRegistry keys;
	private final StreamCodecRegistry codecs;

	public AccessPacks(LicensedProduct product, KeyKeeperRegistry keys, StreamCodecRegistry codecs) {
		this.product = product;
		this.keys = keys;
		this.codecs = codecs;
	}

	@Override
	public ServiceInvocationResult<Collection<FloatingLicenseAccess>> get() {
		KeyKeeper key;
		StreamCodec codec;
		try {
			codec = codec();
			key = key();
		} catch (LicensingException e) {
			return new BaseServiceInvocationResult<>(
					new Trouble(new ServiceCannotOperate(), AccessMessages.AccessPacks_insufficient_configuration, e));
		}
		List<FloatingLicenseAccess> result = new ArrayList<>();
		Collection<Path> files;
		try {
			files = new AccessFiles(product).get();
		} catch (LicensingException e) {
			return new BaseServiceInvocationResult<>(new Trouble(new ServiceFailedOnInfrastructureDenial(),
					AccessMessages.AccessPacks_files_gaining_failed, e));
		}
		List<Trouble> failures = accessPacks(key, codec, result, files);
		return new BaseServiceInvocationResult<>(new BaseDiagnostic(Collections.emptyList(), failures), result);
	}

	private List<Trouble> accessPacks(KeyKeeper key, StreamCodec codec, List<FloatingLicenseAccess> result,
			Collection<Path> files) {
		List<Trouble> failures = new ArrayList<>();
		for (Path file : files) {
			try {
				result.add(from(decoded(file, key, codec)));
			} catch (LicensingException e) {
				failures.add(new Trouble(new ServiceFailedOnMorsel(),
						String.format(AccessMessages.AccessPacks_failed_on_file, file.toAbsolutePath()), e));
			}
		}
		return failures;
	}

	private byte[] decoded(Path file, KeyKeeper key, StreamCodec codec) throws LicensingException {
		return new DecodedContent(file, key, codec).get();
	}

	private FloatingLicenseAccess from(byte[] content) throws LicensingException {
		return new EObjectFromBytes<>(content, FloatingLicenseAccess.class).get();
	}

	private KeyKeeper key() throws LicensingException {
		if (!keys.get().hasService(product)) {
			throw new LicensingException(String.format(AccessMessages.AccessPacks_no_key_keeper, product));
		}
		return keys.get().service(product);
	}

	private StreamCodec codec() throws LicensingException {
		if (!codecs.get().hasService(product)) {
			throw new LicensingException(String.format(AccessMessages.AccessPacks_no_stream_codec, product));
		}
		return codecs.get().service(product);
	}

}
