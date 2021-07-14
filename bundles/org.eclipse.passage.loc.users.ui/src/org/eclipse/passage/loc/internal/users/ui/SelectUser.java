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
package org.eclipse.passage.loc.internal.users.ui;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;
import org.eclipse.passage.loc.internal.users.UserRegistry;
import org.eclipse.passage.loc.internal.users.ui.i18n.UsersUiMessages;
import org.eclipse.passage.loc.internal.workbench.SelectRequest;
import org.eclipse.passage.loc.internal.workbench.SupplySelectRequest;
import org.eclipse.passage.loc.jface.dialogs.Appearance;

/**
 * Selects or creates {@link UserOriginDescriptor}. Will return either
 * {@link Optional} with selected/created license plan or
 * {@link Optional#empty()}
 * 
 */
public final class SelectUser extends SupplySelectRequest<UserDescriptor> {

	public SelectUser(MandatoryService context) {
		super(context);
	}

	@Override
	public SelectRequest<UserDescriptor> get() {
		return new SelectRequest<>(UserDescriptor.class, domain(), input(), appearance());
	}

	private Supplier<Iterable<UserDescriptor>> input() {
		return () -> StreamSupport.stream(context.get(UserRegistry.class).getUsers().spliterator(), false)//
				.collect(Collectors.toList());
	}

	private Appearance appearance() {
		return new Appearance(UsersUiMessages.SelectUser_title, //
				() -> LicensingImages.getImage(UsersPackage.eINSTANCE.getUser().getName()), labels());
	}

	private String domain() {
		return UsersPackage.eNAME;
	}

}
