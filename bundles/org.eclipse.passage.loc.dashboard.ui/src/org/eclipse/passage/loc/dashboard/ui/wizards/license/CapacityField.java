/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.loc.dashboard.ui.wizards.license;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;

public final class CapacityField extends PositiveIntField {

	CapacityField(String name, Runnable modified, LabelProvider labels, MandatoryService context) {
		super(1, name, modified, labels, context);
	}

	@Override
	protected String errorText() {
		return IssueLicensePageMessages.IssueLicenseRequestPage_e_invalid_capacity;
	}

}
