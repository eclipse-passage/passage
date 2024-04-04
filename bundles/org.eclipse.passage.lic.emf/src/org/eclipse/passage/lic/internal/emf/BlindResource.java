/*******************************************************************************
 * Copyright (c) 2024 ArSysOp
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
package org.eclipse.passage.lic.internal.emf;

import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory.Descriptor;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.passage.lic.emf.resource.BlindResourceFactory;

public final class BlindResource implements Supplier<Resource> {

	private final EClass type;

	public BlindResource(EClass type) {
		this.type = Objects.requireNonNull(type);
	}

	public BlindResource(EObject source) {
		this(Objects.requireNonNull(source).eClass());
	}

	@Override
	public Resource get() {
		String domain = type.getEPackage().getName();
		Object factory = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().get(domain);
		if (factory instanceof Resource.Factory.Descriptor) {
			Resource.Factory.Descriptor descriptor = (Descriptor) factory;
			factory = descriptor.createFactory();
		}
		if (factory instanceof BlindResourceFactory) {
			return ((BlindResourceFactory) factory).createResource();
		}
		return new XMIResourceImpl();
	}

}
