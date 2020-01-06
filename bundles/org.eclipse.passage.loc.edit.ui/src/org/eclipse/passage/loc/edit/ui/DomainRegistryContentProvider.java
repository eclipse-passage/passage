/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.passage.lic.emf.edit.BaseDomainRegistry;

public class DomainRegistryContentProvider implements ITreeContentProvider {

	private static final Object[] NO_CHILDREN = new Object[0];

	@Override
	public void dispose() {

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof List<?>) {
			List<?> list = (List<?>) parentElement;
			return list.toArray();
		}
		if (parentElement instanceof BaseDomainRegistry) {
			BaseDomainRegistry<?> baseRegistry = (BaseDomainRegistry<?>) parentElement;
			ResourceSet resourceSet = baseRegistry.getEditingDomain().getResourceSet();
			EList<Resource> resources = resourceSet.getResources();
			return resources.toArray();
		}
		return NO_CHILDREN;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		Object[] children = getChildren(element);
		return children.length > 0;
	}

}