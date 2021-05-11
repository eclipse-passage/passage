/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.users.e4.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.users.edit.UsersEditPlugin;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;
import org.eclipse.swt.widgets.Display;

public class UsersE4UiProcessor {

	@Execute
	void process(IEclipseContext context) {
		Display display = context.get(Display.class);
		if (display != null) {
			doRegisterImages();
		}
	}

	private void doRegisterImages() {
		LicensingImages.getImageRegistry();
		String pattern = "$nl$/icons/full/obj16/%s"; //$NON-NLS-1$

		registerUsers(pattern);
	}

	private void registerUsers(String pattern) {
		Map<String, String> paths = new HashMap<String, String>();
		UsersPackage users = UsersPackage.eINSTANCE;
		paths.put(users.getName(), String.format(pattern, "user.png")); //$NON-NLS-1$
		paths.put(users.getUserOrigin().getName(), String.format(pattern, "user.png")); //$NON-NLS-1$
		paths.put(users.getUser().getName(), String.format(pattern, "user.png")); //$NON-NLS-1$
		LicensingImages.declareImages(UsersEditPlugin.class, paths);
	}

}
