/*******************************************************************************
 * Copyright (c) 2018, 2024 ArSysOp
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
package org.eclipse.passage.loc.workbench.viewers;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.products.model.api.ProductVersionFeature;
import org.eclipse.swt.graphics.Image;

public class DomainRegistryLabelProvider extends LabelProvider {

	private final AdapterFactoryLabelProvider delegate;

	/**
	 * @since 1.0
	 */
	public DomainRegistryLabelProvider() {
		this(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
	}

	public DomainRegistryLabelProvider(AdapterFactory adapterFactory) {
		this.delegate = new AdapterFactoryLabelProvider(adapterFactory);
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof Resource) {
			return LicensingImages.getImage(LicensingImages.IMG_DEFAULT);
		}
		return delegate.getImage(element);
	}

	@Override
	public String getText(Object element) {
		// FIXME: move to lic.products.edit
		if (element instanceof ProductVersionFeature pvf) {
			return getText(pvf.getProductVersion()) + ' ' + ':' + ' ' + pvf.getFeatureIdentifier() + ' '
					+ pvf.getFeatureVersion();
		}
		return delegate.getText(element);
	}

}
