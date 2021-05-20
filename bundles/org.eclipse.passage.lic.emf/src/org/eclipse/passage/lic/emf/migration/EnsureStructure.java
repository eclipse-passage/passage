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
package org.eclipse.passage.lic.emf.migration;

import java.util.List;
import java.util.function.Function;

import org.eclipse.emf.ecore.EObject;

/**
 * Helps to ensure that inner data structures exists for the given EObject
 * 
 * @since 2.0
 */
public interface EnsureStructure extends Function<EObject, List<EObject>> {

}
