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
package org.eclipse.passage.lic.api;

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
 * 
 * @since 2.1
 */
public interface Framework {

	LicensedProduct product();

	AccessCycleConfiguration accessCycleConfiguration();

}
