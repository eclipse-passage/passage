/*******************************************************************************
 * Copyright (c) 2019, 2021 ArSysOp
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
package org.eclipse.passage.loc.report.internal.core;

import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;

public final class FakeUserDescriptor implements UserDescriptor {

	private final String email;
	private final String name;

	public FakeUserDescriptor(String email, String name) {
		this.email = email;
		this.name = name;
	}

	@Override
	public String getIdentifier() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getFullName() {
		return name;
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

}
