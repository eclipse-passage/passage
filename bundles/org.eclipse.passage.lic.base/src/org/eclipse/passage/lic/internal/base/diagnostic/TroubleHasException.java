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
package org.eclipse.passage.lic.internal.base.diagnostic;

import java.util.Optional;
import java.util.function.Predicate;

import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;

public final class TroubleHasException implements Predicate<Optional<Trouble>> {

	@Override
	public boolean test(Optional<Trouble> trouble) {
		if (!trouble.isPresent()) {
			return false;
		}
		return trouble.get().exception().isPresent();
	}

}
