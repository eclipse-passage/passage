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

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.core.di.annotations.CanExecute;

public class SaveHandler {
	@Execute
	public void execute(EPartService partService) {
		MPart activePart = partService.getActivePart();
		partService.savePart(activePart, false);
	}
	
	
	@CanExecute
	public boolean canExecute(EPartService partService) {
		MPart activePart = partService.getActivePart();
		return activePart != null;
	}
		
}