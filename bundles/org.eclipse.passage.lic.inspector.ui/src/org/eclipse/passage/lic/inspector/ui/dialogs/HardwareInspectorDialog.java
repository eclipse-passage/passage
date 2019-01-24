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
package org.eclipse.passage.lic.inspector.ui.dialogs;

import java.io.ByteArrayOutputStream;

import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.passage.lic.inspector.HardwareInspector;
import org.eclipse.passage.lic.inspector.ui.LicInspectorUi;
import org.eclipse.passage.lic.jface.LicensingImages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class HardwareInspectorDialog extends TrayDialog {
	
	private final LicensingImages licensingImages;
	private final HardwareInspector hardwareInspector;

	public HardwareInspectorDialog(LicensingImages images, HardwareInspector inspector, Shell shell) {
		super(shell);
		this.licensingImages = images;
		this.hardwareInspector = inspector;
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Hardware Inspector");
		newShell.setImage(licensingImages.getImage(LicInspectorUi.IMG_INSPECTOR));
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Text text = new Text(area, SWT.MULTI);
		text.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		StringBuilder infoBuilder = new StringBuilder();
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()){
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
