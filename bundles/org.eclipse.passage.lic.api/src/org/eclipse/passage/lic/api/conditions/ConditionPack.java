/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.api.conditions;

import java.util.Collection;

import org.eclipse.passage.lic.api.agreements.GlobalAgreement;

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
 * 
 * @since 2.1
 */
public interface ConditionPack {

	ConditionOrigin origin();

	Collection<Condition> conditions();

	Collection<GlobalAgreement> agreements();

}
