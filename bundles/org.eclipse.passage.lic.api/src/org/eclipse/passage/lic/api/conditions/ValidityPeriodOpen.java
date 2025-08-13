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
 * {@linkplain ValidityPeriod} with a defined start. Is <i>open</i>, meaning has
 * no predefined end date.
 *
 * @since 2.1
 */
public interface ValidityPeriodOpen extends ValidityPeriod {

	ZonedDateTime from();

}
