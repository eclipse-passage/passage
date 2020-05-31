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
package org.eclipse.passage.loc.products.ui.handlers;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;
import org.eclipse.passage.loc.products.ui.ProductsUi;
import org.eclipse.passage.loc.workbench.LocWokbench;

public class LoadProductLineHandler {

	@Execute
	public void execute(IEclipseContext eclipseContext) {
		String domain = ProductsPackage.eNAME;
		String perspectiveId = ProductsUi.PERSPECTIVE_MAIN;
		LocWokbench.loadDomainResource(eclipseContext, domain, perspectiveId);
	}

}