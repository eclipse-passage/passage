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

import java.time.ZonedDateTime;

/**
 * <p>
 * Additionally to {@linkplain ValidityPeriodOpen} declares when the validity
 * ends, thus denoting fixed period bounds.
 * </p>
 * <p>
 * Guarantees that if an instance survived creation, it is consistent:
 * {@code from < to}
 * </p>
 * 
 * @since 2.1
 */
public interface ValidityPeriodClosed extends ValidityPeriodOpen {

	ZonedDateTime to();

}
