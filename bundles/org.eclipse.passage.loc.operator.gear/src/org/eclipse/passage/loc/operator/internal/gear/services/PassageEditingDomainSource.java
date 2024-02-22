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
package org.eclipse.passage.loc.operator.internal.gear.services;

import java.util.HashMap;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.passage.loc.internal.api.EditingDomainSource;

public final class PassageEditingDomainSource implements EditingDomainSource {

	@Override
	public EditingDomain create() {
		return new AdapterFactoryEditingDomain(
				new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE), //
				new BasicCommandStack(), //
				new HashMap<>()//
		);
	}

}
