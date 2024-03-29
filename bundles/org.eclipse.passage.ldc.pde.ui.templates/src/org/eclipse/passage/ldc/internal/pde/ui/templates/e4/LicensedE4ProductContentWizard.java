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
package org.eclipse.passage.ldc.internal.pde.ui.templates.e4;

import org.eclipse.pde.ui.templates.ITemplateSection;
import org.eclipse.pde.ui.templates.NewPluginTemplateWizard;

@Deprecated
public class LicensedE4ProductContentWizard extends NewPluginTemplateWizard {

	@Override
	public ITemplateSection[] createTemplateSections() {
		return new ITemplateSection[] { //
				new LicensedE4ProductTemplateSection(), //
		};
	}

	@Override
	public String[] getImportPackages() {
		return new String[] { "javax.inject;version=\"[1.0.0,2.0.0)\"" }; //$NON-NLS-1$
	}
}
