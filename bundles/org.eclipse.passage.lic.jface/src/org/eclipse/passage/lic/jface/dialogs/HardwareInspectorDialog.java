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
package org.eclipse.passage.lic.jface.dialogs;

import java.io.ByteArrayOutputStream;

import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.passage.lic.api.inspector.HardwareInspector;
import org.eclipse.passage.lic.internal.jface.JFaceMessages;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class HardwareInspectorDialog extends TrayDialog {

	private final HardwareInspector hardwareInspector;

	public HardwareInspectorDialog(Shell shell, HardwareInspector inspector) {
		super(shell);
		this.hardwareInspector = inspector;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(JFaceMessages.HardwareInspectorDialog_title);
		newShell.setImage(LicensingImages.getImage(LicensingImages.IMG_INSPECTOR));
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Text text = new Text(area, SWT.MULTI | SWT.READ_ONLY);
		text.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		StringBuilder infoBuilder = new StringBuilder();
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			hardwareInspector.dumpHardwareInfo(baos);
			infoBuilder.append(new String(baos.toByteArray()));
		} catch (Exception e) {
			infoBuilder.append(e.getMessage());
		}
		text.setText(infoBuilder.toString());
		return area;
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

}
