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
package org.eclipse.passage.lic.internal.base.time;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.Predicate;

public class IsLocalFuture implements Predicate<String> {

	@Override
	public boolean test(String value) {
		try {
			return !Duration.between(//
					LocalDateTime.now(), //
					LocalDateTime.parse(value)).isNegative();
		} catch (Exception e) {
			return false;
		}
	}

}
