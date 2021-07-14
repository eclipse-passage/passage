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
 * General purpose time period(s) definition in which {@code Condition} is
 * valid.
 * </p>
 * <p>
 * Examples: 'from 1st of May, 2020 for two months' or 'each Friday ever'.
 * </p>
 * 
 * @since 2.1
 */
public interface ValidityPeriod {

	/**
	 * Check if the given {@code date} belongs to the validity period.
	 * 
	 * @param not null date to be checked if it belongs to the period of validity or
	 *            not
	 * @return {@code true} if date is valid and {@code false} otherwise
	 */
	boolean valid(ZonedDateTime date);

}
