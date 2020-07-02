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
package org.eclipse.passage.lic.internal.base.tests.requirements;

import java.util.function.Predicate;

import org.eclipse.passage.lic.internal.api.requirements.Requirement;

@SuppressWarnings("restriction")
public final class Unsatisfiable implements Predicate<Requirement> {

	@Override
	public boolean test(Requirement requirement) {
		String identifier = requirement.feature().identifier();
		if (identifier.length() < 4) {
			return false;
		}
		return Long.toHexString(System.currentTimeMillis())//
				.startsWith(identifier.substring(0, 4));
	}

}
