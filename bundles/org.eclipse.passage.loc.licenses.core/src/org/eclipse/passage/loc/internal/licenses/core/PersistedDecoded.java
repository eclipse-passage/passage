/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.loc.internal.licenses.core;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages;

final class PersistedDecoded {

	private final Path residence;
	private final EObject target;

	PersistedDecoded(Path residence, EObject target) {
		this.residence = residence;
		this.target = target;
	}

	Path write(String file) throws LicensingException {
		Path decrypted = residence.resolve(file);
		save(decrypted, resource(decrypted));
		return decrypted;
	}

	private Resource resource(Path path) {
		URI uri = URI.createFileURI(path.toString());
		Resource resource = new ResourceSetImpl().createResource(uri);
		resource.getContents().add(target);
		return resource;
	}

	private void save(Path decrypted, Resource resource) throws LicensingException {
		try {
			resource.save(Collections.emptyMap());
		} catch (IOException e) {
			throw new LicensingException(//
					String.format(LicensesCoreMessages.EmfObjectPersisted_failed, target, decrypted), e);
		}
	}
}
