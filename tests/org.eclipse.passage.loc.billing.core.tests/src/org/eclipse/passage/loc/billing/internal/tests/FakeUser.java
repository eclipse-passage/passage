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
 *     Nikifor Fedorov <zelenyhleb@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.billing.internal.tests;

import java.util.Set;

import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserLicenseDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;

public class FakeUser implements UserDescriptor {
	
	private final Set<UserLicenseDescriptor> licenses;
	
	public FakeUser(Set<UserLicenseDescriptor> licenses) {
		this.licenses = licenses;
	}

	@Override
	public String getIdentifier() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getEmail() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getFullName() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getDescription() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getPreferredConditionType() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getPreferredConditionExpression() {
		throw new UnsupportedOperationException();
	}

	@Override
	public UserOriginDescriptor getUserOrigin() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<? extends UserLicenseDescriptor> getUserLicenses() {
		return licenses;
	}

}
