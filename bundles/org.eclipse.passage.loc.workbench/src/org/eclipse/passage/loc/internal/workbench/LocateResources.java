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
package org.eclipse.passage.loc.internal.workbench;

import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;

/**
 * 
 * Supplies EMF {@link ResourceLocator} for a given {@link EObject} or
 * {@link EClass}
 *
 */
public class LocateResources implements Supplier<ResourceLocator> {

	private final EObject eObject;

	public LocateResources(EObject eObject) {
		this.eObject = eObject;
	}

	public LocateResources(EClass eClass) {
		this.eObject = eClass.getEPackage().getEFactoryInstance().create(eClass);
	}

	@Override
	public ResourceLocator get() {
		return Optional.ofNullable(//
				new ComposedAdapterFactory(//
						ComposedAdapterFactory.Descriptor.Registry.INSTANCE)//
								.adapt(eObject, IItemLabelProvider.class))
				.filter(ResourceLocator.class::isInstance)//
				.map(ResourceLocator.class::cast)//
				.orElse(EMFEditPlugin.INSTANCE);
	}

}
