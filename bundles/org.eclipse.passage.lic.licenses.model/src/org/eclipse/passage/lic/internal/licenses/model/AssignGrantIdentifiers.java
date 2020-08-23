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
package org.eclipse.passage.lic.internal.licenses.model;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.eclipse.emf.common.util.EList;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;

public final class AssignGrantIdentifiers implements Consumer<LicensePack> {

	@Override
	public void accept(LicensePack pack) {
		String identifier = pack.getIdentifier();
		EList<LicenseGrant> grants = pack.getLicenseGrants();
		for (int i = 0; i < grants.size(); i++) {
			LicenseGrant grant = grants.get(i);
			if (Optional.ofNullable(grant.getIdentifier()).filter(Predicate.not(String::isEmpty)).isPresent()) {
				continue;
			}
			grant.setIdentifier(new StringBuilder(identifier).append('#').append(i).toString());
		}

	}

}
