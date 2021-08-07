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
package org.eclipse.passage.lic.internal.agreements.e4.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.passage.lic.agreements.edit.AgreementsEditPlugin;
import org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.swt.widgets.Display;

public class AgreementsE4UiProcessor {

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

		registerFeatures(pattern);
	}

	private void registerFeatures(String pattern) {
		Map<String, String> paths = new HashMap<String, String>();
		AgreementsPackage agreements = AgreementsPackage.eINSTANCE;
		paths.put(agreements.getName(), String.format(pattern, "agreement.png")); //$NON-NLS-1$
		paths.put(agreements.getAgreementGroup().getName(), String.format(pattern, "agreement.png")); //$NON-NLS-1$
		paths.put(agreements.getAgreement().getName(), String.format(pattern, "agreement.png")); //$NON-NLS-1$
		LicensingImages.declareImages(AgreementsEditPlugin.class, paths);
	}

}
