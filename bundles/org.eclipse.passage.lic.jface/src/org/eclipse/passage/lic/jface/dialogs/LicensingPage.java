/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.lic.jface.dialogs;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Abstract base implementation of a licensing configuration dialog page. All
 * licensing configuration dialog pages are subclasses of this one.
 * 
 * @since 0.5.0
 * 
 */
public abstract class LicensingPage extends DialogPage {

	protected void createErrorContent(Composite parent, String message) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		composite.setLayout(new GridLayout(2, false));
		Label image = new Label(composite, SWT.NONE);
		image.setImage(LicensingImages.getImage(LicensingImages.IMG_LEVEL_ERROR));
		Label text = new Label(composite, SWT.NONE);
		text.setText(message);
	}

	protected IStatus accept() {
		return Status.OK_STATUS;
	}

}
