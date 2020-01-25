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
package org.eclipse.passage.loc.workbench.handlers;

import java.util.Objects;
import java.util.Optional;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.passage.loc.workbench.LocWokbench;

public class OpenPerspectiveHandler {

	@Execute
	public void execute(IApplicationContext applicationContext, MWindow window, EPartService partService, @Named(LocWokbench.COMMANDPARAMETER_VIEW_PERSPECTIVE_ID) String perspectiveId) {
		String brandingName = applicationContext.getBrandingName();
		Optional<MPerspective> switched = partService.switchPerspective(perspectiveId);
		if (switched.isPresent()) {
			MPerspective perspective = switched.get();
			String label = perspective.getLocalizedLabel();
			String title = brandingName + ' ' + '-' + ' ' + label;
			window.setLabel(title);
		}
		
	}

	@CanExecute
	public boolean canExecute(MWindow window, EModelService modelService, @Named(LocWokbench.COMMANDPARAMETER_VIEW_PERSPECTIVE_ID) String perspectiveId) {
		MUIElement found = modelService.find(perspectiveId, window);
		if (found instanceof MPerspective) {
			MPerspective active = modelService.getActivePerspective(window);
			if (active == null) {
				return true;
			}
			return !Objects.equals(perspectiveId, active.getElementId());
		}
		return false;
	}

}