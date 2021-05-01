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
package org.eclipse.passage.lic.emf.resource;

import java.util.Optional;
import java.util.function.Function;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * Extracts {@link EObject} from the given object
 * 
 * @since 2.0
 *
 */
public final class ExtractEObject implements Function<Object, Optional<EObject>> {

	@Override
	public Optional<EObject> apply(Object object) {
		if (object instanceof EObject) {
			return Optional.of((EObject) object);
		}
		if (object instanceof Resource) {
			Resource resource = (Resource) object;
			EList<EObject> contents = resource.getContents();
			if (!contents.isEmpty()) {
				return Optional.of(contents.get(0));
			}
		}
		return Optional.empty();
	}

}
