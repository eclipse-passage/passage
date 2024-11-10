/*******************************************************************************
 * Copyright (c) 2018, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support and improvements
 *******************************************************************************/
package org.eclipse.passage.lic.jface.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.passage.lic.base.BaseFeatureIdentifier;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;

public abstract class LicensedAction extends Action {

	/**
	 * @since 2.1
	 */
	protected abstract void doAction();

	@Override
	public final void runWithEvent(Event event) {
		runEverywhere(event.display);
	}

	@Override
	public final void run() {
		runEverywhere(Display.getDefault());
	}

	@SuppressWarnings("restriction")
	private void runEverywhere(Display display) {
		new LicensedRunnableUI(display::getActiveShell, new BaseFeatureIdentifier(getId()), this::doAction).run();
	}

}
