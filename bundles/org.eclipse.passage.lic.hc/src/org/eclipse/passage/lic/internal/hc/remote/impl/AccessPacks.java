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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.api.io.KeyKeeper;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.conditions.mining.DecodedContent;
import org.eclipse.passage.lic.internal.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.internal.base.diagnostic.code.ServiceFailedOnInfrastructureDenial;
import org.eclipse.passage.lic.internal.base.diagnostic.code.ServiceFailedOnMorsel;
import org.eclipse.passage.lic.internal.hc.i18n.AccessMessages;

public final class AccessPacks implements Supplier<ServiceInvocationResult<Collection<FloatingLicenseAccess>>> {

	private final LicensedProduct product;
	private final KeyKeeper key;
	private final StreamCodec codec;

	public AccessPacks(LicensedProduct product, KeyKeeper key, StreamCodec codec) {
		this.product = product;
		this.key = key;
		this.codec = codec;
	}

	@Override
	public ServiceInvocationResult<Collection<FloatingLicenseAccess>> get() {
		List<FloatingLicenseAccess> result = new ArrayList<>();
		Collection<Path> files;
		try {
			files = new AccessFiles(product).get();
		} catch (LicensingException e) {
			return new BaseServiceInvocationResult<>(new Trouble(new ServiceFailedOnInfrastructureDenial(),
					AccessMessages.AccessPacks_files_gaining_failed, e));
		}
		List<Trouble> failures = new ArrayList<>();
		for (Path file : files) {
			try {
				result.add(from(content(decoded(file))));
			} catch (LicensingException e) {
				failures.add(new Trouble(new ServiceFailedOnMorsel(),
						String.format(AccessMessages.AccessPacks_failed_on_file, file.toAbsolutePath()), e));
			}
		}
		return new BaseServiceInvocationResult<>(new BaseDiagnostic(Collections.emptyList(), failures), result);
	}

	private byte[] decoded(Path file) throws LicensingException {
		try {
			return new DecodedContent(file, key, codec).get();
		} catch (IOException e) {
			throw new LicensingException(String.format(AccessMessages.AccessPacks_failure, file.toAbsolutePath()), e);
		}
	}

	private EList<EObject> content(byte[] content) throws LicensingException {
		Resource resource = new XMIResourceImpl();
		try (InputStream input = new ByteArrayInputStream(content)) {
			resource.load(input, Collections.emptyMap());
		} catch (IOException e) {
			throw new LicensingException(AccessMessages.AccessPacks_failed_xmi_read, e);
		}
		return resource.getContents();
	}

	private FloatingLicenseAccess from(EList<EObject> contents) throws LicensingException {
		if (contents.size() != 1) {
			throw new LicensingException(String.format(AccessMessages.AccessPacks_unexpected_amount, contents.size()));
		}
		EObject only = contents.get(0);
		if (!FloatingLicenseAccess.class.isInstance(only)) {
			throw new LicensingException(
					String.format(AccessMessages.AccessPacks_unexpected_type, only.eClass().getName()));
		}
		return FloatingLicenseAccess.class.cast(only);
	}

}
