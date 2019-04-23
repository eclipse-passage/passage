/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
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
package org.eclipse.passage.lic.internal.jface.dialogs;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.passage.lic.internal.jface.JFaceMessages;
import org.eclipse.passage.lic.jface.resource.LicensingColors;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.jface.viewers.RestrictionRepresenters;
import org.eclipse.passage.lic.runtime.restrictions.RestrictionExecutorRegistry;
import org.eclipse.passage.lic.runtime.restrictions.RestrictionLevelDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

public class RestrictionLevelPage extends LicensingRegistryPage<RestrictionExecutorRegistry> {
	private Map<String, RGB> restrictionColors = new HashMap<>();

	public RestrictionLevelPage() {
		super(RestrictionExecutorRegistry.class);
	}

	@Override
	protected void createContent(Composite parent, RestrictionExecutorRegistry registry) {
		Iterable<? extends RestrictionLevelDescriptor> restrictionLevels = registry.getRestrictionLevels();
		GridDataFactory groupData = GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).grab(true, false);
		for (RestrictionLevelDescriptor descriptor : restrictionLevels) {
			Group group = new Group(parent, SWT.NONE);
			String identifier = descriptor.getIdentifier();
			group.setText(descriptor.getName());
			group.setData(descriptor);
			group.setLayout(new GridLayout(2, false));
			group.setLayoutData(groupData.create());

			Label image = new Label(group, SWT.NONE);
			String imageKey = RestrictionRepresenters.resolveImageKey(identifier);
			image.setImage(LicensingImages.getImage(imageKey));
			image.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));

			Label color = new Label(group, SWT.NONE);
			String colorKey = RestrictionRepresenters.resolveColorKey(identifier);
			color.setBackground(LicensingColors.getColorRegistry().get(colorKey));
			color.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

			Button selectColor = new Button(group, SWT.PUSH);
			selectColor.setText(JFaceMessages.RestrictionLevelPage_select_btn_text);
			selectColor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
			selectColor.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ColorDialog dialog = new ColorDialog(Display.getDefault().getActiveShell());
					RGB rgbSelected = dialog.open();
					if (rgbSelected != null) {
						restrictionColors.put(colorKey, rgbSelected);
						color.setBackground(new Color(Display.getDefault(), rgbSelected));
					}
				}
			});

			Label description = new Label(group, SWT.WRAP);
			description.setText(descriptor.getDescription());
		}
	}

	@Override
	protected String getConfigurationErrorMessage() {
		return JFaceMessages.RestrictionLevelPage_e_not_available;
	}

	@Override
	protected IStatus accept() {
		return LicensingColors.acceptColors(restrictionColors);
	}
}
