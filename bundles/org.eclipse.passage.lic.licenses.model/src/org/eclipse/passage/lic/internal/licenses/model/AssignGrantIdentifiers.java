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
import org.eclipse.passage.lic.internal.licenses.model.migration.EnsurePersonalPackProduct;
import org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;

public final class AssignGrantIdentifiers implements Consumer<PersonalLicensePack> {

	private final Predicate<String> predicate;

	public AssignGrantIdentifiers() {
		predicate = ((Predicate<String>) String::isEmpty).negate();
	}

	@Override
	public void accept(PersonalLicensePack pack) {
		new EnsurePersonalPackProduct().apply(pack);
		String identifier = pack.getLicense().getIdentifier();
		EList<PersonalFeatureGrant> grants = pack.getGrants();
		for (int i = 0; i < grants.size(); i++) {
			PersonalFeatureGrant grant = grants.get(i);
			if (Optional.ofNullable(grant.getIdentifier()).filter(predicate).isPresent()) {
				continue;
			}
			grant.setIdentifier(identifier + '#' + i);
		}

	}

}
