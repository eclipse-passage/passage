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
package org.eclipse.passage.lic.internal.jface.dialogs;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.passage.lic.jface.RestrictionLabels;
import org.eclipse.passage.lic.jface.dialogs.LicensingRegistryPage;
import org.eclipse.passage.lic.jface.resource.LicensingColors;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.runtime.restrictions.RestrictionExecutorRegistry;
import org.eclipse.passage.lic.runtime.restrictions.RestrictionLevelDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

public class RestrictionLevelPage extends LicensingRegistryPage<RestrictionExecutorRegistry> {

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
			group.setLayout(new GridLayout(1, false));
			group.setLayoutData(groupData.create());

			Label image = new Label(group, SWT.NONE);
			String imageKey = RestrictionLabels.resolveImageKey(identifier);
			image.setImage(LicensingImages.getImage(imageKey));
			image.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));

			Label color = new Label(group, SWT.NONE);
			String colorKey = RestrictionLabels.resolveColorKey(identifier);
			color.setBackground(LicensingColors.getColorRegistry().get(colorKey));
			color.setLayoutData(new GridData(GridData.FILL_BOTH));

			Label description = new Label(group, SWT.WRAP);
			description.setText(descriptor.getDescription());
		}
	}

	@Override
	protected String getConfigurationErrorMessage() {
		return "Restriction levels definitions are not available.\nPlease check the product configuration";
	}

}
