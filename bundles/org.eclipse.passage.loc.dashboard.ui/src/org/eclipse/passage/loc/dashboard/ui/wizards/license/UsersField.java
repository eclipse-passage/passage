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
package org.eclipse.passage.loc.dashboard.ui.wizards.license;

import java.util.Collection;
import java.util.Optional;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;
import org.eclipse.passage.loc.internal.users.ui.SelectUser;
import org.eclipse.passage.loc.internal.workbench.SelectRoots;
import org.eclipse.swt.widgets.Text;

public final class UsersField extends SelectableField<Collection<UserDescriptor>> {

	UsersField(Collection<UserDescriptor> users, Runnable modified, LabelProvider labels, MandatoryService context) {
		super(Optional.of(users), modified, labels, context);
	}

	@Override
	protected String label() {
		return IssueLicensePageMessages.IssueLicenseRequestPage_lbl_users;
	}

	@Override
	protected String errorText() {
		return IssueLicensePageMessages.IssueLicenseRequestPage_e_no_user;
	}

	@Override
	protected Optional<Collection<UserDescriptor>> select(Text control) {
		return Optional.of(new SelectRoots<>(new SelectUser(context).get(), context).get());
	}

}
