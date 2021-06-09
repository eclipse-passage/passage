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
package org.eclipse.passage.loc.users.emfforms.parts;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.api.UserGroup;

public final class UserOriginContentProvider implements ITreeContentProvider {

	private final Object[] nobody = new Object[0];
	private final AdapterFactoryContentProvider delegate;

	public UserOriginContentProvider() {
		delegate = new AdapterFactoryContentProvider(
				new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	@Override
	public Object[] getChildren(Object parent) {
		if (parent instanceof User) {
			return nobody;
		}
		if (parent instanceof UserGroup) {
			return nobody;
		}
		return delegate.getChildren(parent);
	}

	@Override
	public Object getParent(Object element) {
		return delegate.getParent(element);
	}

	@Override
	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}

}
