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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * Extracts {@link Resource} from the given object
 * 
 * @since 2.0
 *
 */
public final class ExtractResource implements Function<Object, Optional<Resource>> {

	@Override
	public Optional<Resource> apply(Object object) {
		if (object instanceof EObject) {
			EObject eObject = (EObject) object;
			return Optional.ofNullable(eObject.eResource());
		}
		if (object instanceof Resource) {
			return Optional.of((Resource) object);
		}
		return Optional.empty();
	}

}
