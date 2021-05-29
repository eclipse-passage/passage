/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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
package org.eclipse.passage.loc.licenses.emfforms.parts;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emfforms.spi.swt.treemasterdetail.util.CreateElementCallback;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistryEvents;
import org.eclipse.passage.loc.workbench.emfforms.parts.DetailsView;

public class LicensesDetailsPart extends DetailsView {

	@Inject
	public LicensesDetailsPart(MPart part, ESelectionService selectionService) {
		super(part, selectionService);
	}

	@Inject
	@Optional
	public void showLicensePlan(@UIEventTopic(LicenseRegistryEvents.LICENSE_PLAN_CREATE) LicensePlan input,
			IEclipseContext context) {
		show(input, context);
	}

	@Inject
	@Optional
	public void showLicensePack(@UIEventTopic(LicenseRegistryEvents.LICENSE_PACK_CREATE) PersonalLicensePack input,
			IEclipseContext context) {
		show(input, context);
	}

	@Override
	protected CreateElementCallback getCreateElementCallback() {
		return new LicensesCreateElementCallback();
	}

	@Override
	protected java.util.Optional<IStructuredContentProvider> customizedContentProvider() {
		return java.util.Optional.of(new LicensePlanContentProvider());
	}

}
