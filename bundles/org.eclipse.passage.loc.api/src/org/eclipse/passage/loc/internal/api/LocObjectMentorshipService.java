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
package org.eclipse.passage.loc.internal.api;

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;

public interface LocObjectMentorshipService {

	/**
	 * @return name of a mentor, if object has one.
	 */
	Optional<String> mentor(EObject object);

}
