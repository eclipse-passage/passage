/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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
package org.eclipse.passage.loc.dashboard.ui.wizards;

import org.eclipse.jface.wizard.WizardPage;

/**
 * Except for the control construction phase, that WizardPage protocol demands,
 * this type of pages has initialization phase, where actual data can be read
 * and reflected in controls.
 */
abstract class TwoPhaseWizardPage extends WizardPage {

	protected TwoPhaseWizardPage(String name) {
		super(name);
	}

	abstract void init();

}
