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
package org.eclipse.passage.moveto.lic.emf.edit;

import java.util.Objects;
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
public class EClassResources implements Supplier<ResourceLocator> {

	private final ComposedAdapterFactory adapterFactory;
	private final EObject eObject;

	public EClassResources(EObject eObject) {
		this(eObject, //
				new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
	}

	public EClassResources(EClass eClass) {
		this(eClass.getEPackage().getEFactoryInstance().create(eClass),
				new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
	}

	public EClassResources(EObject eObject, ComposedAdapterFactory adapterFactory) {
		Objects.requireNonNull(eObject, "Null EObject"); //$NON-NLS-1$
		Objects.requireNonNull(adapterFactory, "Null ComposedAdapterFactory"); //$NON-NLS-1$
		this.eObject = eObject;
		this.adapterFactory = adapterFactory;
	}

	/**
	 * The {@link EClass} that these resources dedicated for
	 * 
	 * @return the {@link EClass}
	 */
	public EClass eClass() {
		return eObject.eClass();
	}

	/**
	 * The {@link ComposedAdapterFactory} that will be used to resolve resoures
	 * 
	 * @return the {@link EClass}
	 */
	public ComposedAdapterFactory adapterFactory() {
		return adapterFactory;
	}

	@Override
	public ResourceLocator get() {
		return Optional.ofNullable(//
				adapterFactory.adapt(eObject, IItemLabelProvider.class))//
				.filter(ResourceLocator.class::isInstance)//
				.map(ResourceLocator.class::cast)//
				.orElse(EMFEditPlugin.INSTANCE);
	}

}
