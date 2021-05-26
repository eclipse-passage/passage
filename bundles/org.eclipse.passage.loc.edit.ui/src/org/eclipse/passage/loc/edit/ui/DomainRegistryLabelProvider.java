/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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
package org.eclipse.passage.loc.edit.ui;

import java.util.Optional;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.loc.internal.workbench.LocDomainRegistryAccess;
import org.eclipse.swt.graphics.Image;

class DomainRegistryLabelProvider extends LabelProvider {

	private final LocDomainRegistryAccess access;

	public DomainRegistryLabelProvider(LocDomainRegistryAccess access) {
		this.access = access;
	}

	@Override
	public String getText(Object element) {
		return uri(element)//
				.map(URI::toFileString)//
				.orElseGet(() -> super.getText(element));
	}

	@Override
	public Image getImage(Object element) {
		return uri(element)//
				.flatMap(u -> Optional.ofNullable(u.fileExtension()))//
				.flatMap(access::domainForExtension)//
				.flatMap(d -> Optional.ofNullable(LicensingImages.getImage(d)))
				.orElseGet(() -> super.getImage(element));
	}

	private Optional<URI> uri(Object element) {
		return Optional.ofNullable(element)//
				.filter(Resource.class::isInstance)//
				.map(Resource.class::cast)//
				.flatMap(r -> Optional.ofNullable(r.getURI()));
	}

}
