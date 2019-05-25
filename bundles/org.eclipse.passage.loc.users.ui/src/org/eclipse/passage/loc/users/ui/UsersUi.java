/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.users.ui;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.passage.lic.emf.edit.ComposedAdapterFactoryProvider;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;
import org.eclipse.passage.lic.users.registry.UserRegistry;
import org.eclipse.passage.loc.internal.users.ui.i18n.UsersUiMessages;
import org.eclipse.passage.loc.workbench.LocWokbench;
import org.eclipse.swt.widgets.Shell;

public class UsersUi {

	public static final String BUNDLE_SYMBOLIC_NAME = "org.eclipse.passage.loc.users.ui"; //$NON-NLS-1$

	public static final String PERSPECTIVE_MAIN = BUNDLE_SYMBOLIC_NAME + '.' + "perspective.main"; //$NON-NLS-1$

	public static UserDescriptor selectUserDescriptor(Shell shell, ComposedAdapterFactoryProvider provider,
			UserRegistry registry, UserDescriptor initial) {
		String classifier = UsersPackage.eINSTANCE.getUser().getName();
		String title = UsersUiMessages.UsersUi_select_user;
		Iterable<? extends UserDescriptor> input = registry.getUsers();
		Class<UserDescriptor> clazz = UserDescriptor.class;
		ComposedAdapterFactory factory = provider.getComposedAdapterFactory();
		return LocWokbench.selectClassifier(shell, factory, classifier, title, input, initial, clazz);
	}

}
