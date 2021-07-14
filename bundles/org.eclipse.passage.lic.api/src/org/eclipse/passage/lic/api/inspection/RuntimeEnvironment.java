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
package org.eclipse.passage.lic.api.inspection;

import org.eclipse.passage.lic.api.EvaluationType;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.registry.Service;

/**
 * <p>
 * Runtime environment describes a separate aspect of the overwhelming
 * infrastructure around the licensed product: hardware, operating system, net,
 * etc. Such a part of environment is described in terms of environment
 * properties aggregated in an environment-specific manner. For instance:
 * {@code OS family} or {@code CPU name}.
 * </p>
 * <p>
 * It is used by condition expression evaluation services in order to define if
 * an expression is met by the supplied environment or not.
 * </p>
 * 
 * @since 2.1
 */
public interface RuntimeEnvironment extends Service<EvaluationType> {

	String state() throws LicensingException;

	boolean isAssuptionTrue(EnvironmentProperty property, String value) throws LicensingException;

}
