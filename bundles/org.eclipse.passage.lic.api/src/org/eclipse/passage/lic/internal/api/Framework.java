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
package org.eclipse.passage.lic.internal.api;

import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;

/**
 * <p>
 * All the framework-relying constructions are to originate from this point this
 * way or another. Major part of Passage code base should stay completely
 * framework-agnostic, gaining all the data and services directly through
 * constructors.
 * </p>
 * <p>
 * Currently we use
 * </p>
 * <ul>
 * <li>service implementation discovery</li>
 * <li>IOC-wiring functionality</li>
 * <li>event bus</li>
 * </ul>
 * <p>
 * <p>
 * Two implementation is currently planned: Equinox-based and Spring based.
 * </p>
 */
public interface Framework {

	Supplier<Registry<StringServiceId, ResolvedRequirements>> requirementsSupplierRegistry();

}
