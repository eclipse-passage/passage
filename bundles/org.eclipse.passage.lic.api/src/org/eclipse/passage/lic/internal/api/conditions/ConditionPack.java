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
package org.eclipse.passage.lic.internal.api.conditions;

import java.util.Collection;

/**
 * <p>
 * Set of conditions of a common origin.
 * </p>
 * <p>
 * Unsuccessful condition evaluation is supposed to be contagious. Origin limits
 * the scope of contamination: having one failed condition among all the
 * conditions of the same origin means this origin compromising, but does not
 * affect fates of conditions from other origins.
 * </p>
 */
public interface ConditionPack {

	ConditionOrigin origin();

	Collection<Condition> conditions();

}
