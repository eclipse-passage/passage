/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
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

import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.users.model.api.UserOrigin;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;
import org.eclipse.passage.loc.internal.users.UserRegistry;
import org.eclipse.passage.loc.internal.users.ui.i18n.UsersUiMessages;
import org.eclipse.passage.loc.internal.workbench.SelectRequest;
import org.eclipse.passage.loc.internal.workbench.SupplySelectRequest;
import org.eclipse.passage.loc.jface.dialogs.Appearance;

/**
 * Creates {@link SelectRequest} for {@link UserOrigin} from the given
 * {@link MandatoryService}.
 * 
 */
public final class SelectUserOrigin extends SupplySelectRequest<UserOrigin> {

	public SelectUserOrigin(MandatoryService context) {
		super(context);
	}

	@Override
	public SelectRequest<UserOrigin> get() {
		return new SelectRequest<>(UserOrigin.class, domain(), input(), appearance());
	}

	private Supplier<Iterable<UserOrigin>> input() {
		return () -> StreamSupport.stream(context.get(UserRegistry.class).userOrigins().spliterator(), false)//
				.collect(Collectors.toList());
	}

	private Appearance appearance() {
		return new Appearance(UsersUiMessages.SelectUserOrigin_title, //
				() -> LicensingImages.getImage(UsersPackage.eINSTANCE.getUserOrigin().getName()), labels());
	}

	private String domain() {
		return UsersPackage.eNAME;
	}

}
