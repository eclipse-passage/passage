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
package org.eclipse.passage.loc.licenses.ui.viewers;

import javax.inject.Inject;

import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.passage.lic.jface.LicensingImages;
import org.eclipse.passage.loc.edit.LicenseDomainRegistry;
import org.eclipse.passage.loc.workbench.parts.DomainRegistryExplorer;

public class LicenseExplorer extends DomainRegistryExplorer {

	@Inject
	public LicenseExplorer(LicenseDomainRegistry registry, ESelectionService selectionService, LicensingImages images) {
		super(registry, selectionService, images);
	}

}
