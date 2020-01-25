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
package org.eclipse.passage.loc.dashboard.ui.details;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.DashboardUiMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class DashboardDetailsPart {

	private final IEclipseContext contex;

	@Inject
	public DashboardDetailsPart(IEclipseContext context) {
		this.contex = context;
	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		Composite content = new Composite(parent, SWT.NONE);
		content.setLayout(GridLayoutFactory.fillDefaults().create());
		Label header = new Label(content, SWT.NONE);
		header.setFont(JFaceResources.getHeaderFont());
		header.setLayoutData(
				GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.TOP).grab(true, false).indent(0, 20).create());
		String pattern = DashboardUiMessages.DashboardDetailsPart_welcome;
		String brandingName = contex.get(IApplicationContext.class).getBrandingName();
		header.setText(String.format(pattern, brandingName));

		Label title = new Label(content, SWT.NONE);
		title.setFont(JFaceResources.getBannerFont());
		title.setLayoutData(
				GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.TOP).grab(true, false).indent(0, 10).create());
		title.setText(DashboardUiMessages.DashboardDetailsPart_title);

		StyledText styled = new StyledText(content, SWT.MULTI | SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		styled.setLayoutData(GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.TOP).grab(true, true).create());
		String text = composeWelcomeText();
		styled.setText(text);
		styled.setBackground(JFaceColors.getInformationViewerBackgroundColor(Display.getDefault()));
	}

	protected String composeWelcomeText() {
		StringBuilder sb = new StringBuilder();
		sb.append('\n');
		sb.append(DashboardUiMessages.DashboardDetailsPart_feature_set_create).append('\n');
		sb.append(DashboardUiMessages.DashboardDetailsPart_feature_set_description);
		sb.append('\n');
		sb.append('\n');
		sb.append(DashboardUiMessages.DashboardDetailsPart_feature_create).append('\n');
		sb.append(DashboardUiMessages.DashboardDetailsPart_feature_description);
		sb.append('\n');
		sb.append('\n');
		sb.append(DashboardUiMessages.DashboardDetailsPart_feature_version_create).append('\n');
		sb.append(DashboardUiMessages.DashboardDetailsPart_feature_version_description);
		sb.append('\n');
		sb.append('\n');
		sb.append(DashboardUiMessages.DashboardDetailsPart_product_line_create).append('\n');
		sb.append(DashboardUiMessages.DashboardDetailsPart_product_line_description);
		sb.append('\n');
		sb.append('\n');
		sb.append(DashboardUiMessages.DashboardDetailsPart_product_create).append('\n');
		sb.append(DashboardUiMessages.DashboardDetailsPart_product_description);
		sb.append('\n');
		sb.append('\n');
		sb.append(DashboardUiMessages.DashboardDetailsPart_product_version_create).append('\n');
		sb.append(DashboardUiMessages.DashboardDetailsPart_product_version_description);
		sb.append('\n');
		sb.append('\n');
		sb.append(DashboardUiMessages.DashboardDetailsPart_product_version_feature_create).append('\n');
		sb.append(DashboardUiMessages.DashboardDetailsPart_product_version_feature_description);
		sb.append('\n');
		sb.append('\n');
		sb.append(DashboardUiMessages.DashboardDetailsPart_user_origin_create).append('\n');
		sb.append(DashboardUiMessages.DashboardDetailsPart_user_origin_description);
		sb.append('\n');
		sb.append('\n');
		sb.append(DashboardUiMessages.DashboardDetailsPart_user_create).append('\n');
		sb.append(DashboardUiMessages.DashboardDetailsPart_user_description);
		sb.append('\n');
		sb.append('\n');
		sb.append(DashboardUiMessages.DashboardDetailsPart_license_pack_create).append('\n');
		sb.append(DashboardUiMessages.DashboardDetailsPart_license_pack_description);
		return sb.toString();
	}
}