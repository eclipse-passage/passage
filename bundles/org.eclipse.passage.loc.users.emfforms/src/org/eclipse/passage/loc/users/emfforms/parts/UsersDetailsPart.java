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
package org.eclipse.passage.loc.users.emfforms.parts;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emfforms.spi.swt.treemasterdetail.util.CreateElementCallback;
import org.eclipse.passage.lic.model.api.UserOrigin;
import org.eclipse.passage.lic.users.registry.UserRegistryEvents;
import org.eclipse.passage.loc.workbench.emfforms.parts.DetailsView;

public class UsersDetailsPart extends DetailsView {

	@Inject
	public UsersDetailsPart(MPart part, ESelectionService selectionService) {
		super(part, selectionService);
	}

	@Inject
	@Optional
	public void showFeatureSet(@UIEventTopic(UserRegistryEvents.USER_ORIGIN_CREATE) UserOrigin input,
			IEclipseContext context) {
		show(input, context);
	}

	@Override
	protected CreateElementCallback getCreateElementCallback() {
		return new UsersCreateElementCallback();
	}

}
