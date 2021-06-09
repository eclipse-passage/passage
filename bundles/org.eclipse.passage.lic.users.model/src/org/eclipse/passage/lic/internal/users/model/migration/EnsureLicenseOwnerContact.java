/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.users.model.migration;

import java.util.Optional;
import java.util.function.Function;

import org.eclipse.passage.lic.users.model.api.Contact;
import org.eclipse.passage.lic.users.model.api.LicenseOwner;
import org.eclipse.passage.lic.users.model.meta.UsersFactory;

public final class EnsureLicenseOwnerContact implements Function<LicenseOwner, Contact> {

	@Override
	public Contact apply(LicenseOwner owner) {
		return Optional.ofNullable(owner)//
				.map(LicenseOwner::getContact)//
				.orElseGet(UsersFactory.eINSTANCE::createContact);
	}

}
