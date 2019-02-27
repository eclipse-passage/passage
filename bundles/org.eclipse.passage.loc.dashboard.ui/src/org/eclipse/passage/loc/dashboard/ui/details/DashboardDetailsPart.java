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
package org.eclipse.passage.loc.dashboard.ui.details;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.jface.resource.JFaceResources;
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
		header.setLayoutData(GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.TOP).grab(true, false).indent(0, 20).create());
		String pattern = "Welcome to %s";
		String brandingName = contex.get(IApplicationContext.class).getBrandingName();
		header.setText(String.format(pattern, brandingName));

		Label title = new Label(content, SWT.NONE);
		title.setFont(JFaceResources.getBannerFont());
		title.setLayoutData(
				GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.TOP).grab(true, false).indent(0, 10).create());
		title.setText("Quick Start Guide");

		StyledText styled = new StyledText(content, SWT.MULTI | SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		styled.setLayoutData(GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.TOP).grab(true, true).create());
		String text = composeWelcomeText();
		styled.setText(text);
		styled.setBackground(JFaceColors.getInformationViewerBackgroundColor(Display.getDefault()));
	}

	protected String composeWelcomeText() {
		StringBuilder sb = new StringBuilder();
		sb.append('\n');
		sb.append("1. Create Feature Set").append('\n');
		sb.append("The 'Feature Set' groups the related functionality that may be distributed via several 'Products'");
		sb.append('\n');
		sb.append('\n');
		sb.append("2. Create Features").append('\n');
		sb.append("The 'Feature' corresponds to the scenario for your product, that you want to restrict. ");
		sb.append("For example, you may want to restrict export to some proprieatry format or other actions. ");
		sb.append("You do not need to describe all the funcionality of your 'Product', but only the parts you want to restrict. ");
		sb.append("The 'identifier' attribute of the 'Feature' is important for the licensing configuration.");
		sb.append('\n');
		sb.append('\n');
		sb.append("3. Create Feature Versions").append('\n');
		sb.append("The 'Feature Version' is a state of your 'Feature' that included to the 'Product Version'. ");
		sb.append("The 'version' attribute of the 'Feature Version' is important for the licensing configuration.");
		sb.append('\n');
		sb.append('\n');
		sb.append("4. Create Product Line").append('\n');
		sb.append("The 'Product Line' groups the related 'Products' in your offering. It may be different editions of the related functionality.");
		sb.append('\n');
		sb.append('\n');
		sb.append("5. Create Product").append('\n');
		sb.append("The 'Product' corresponds to the ready-to-use item in your offering. ");
		sb.append("The 'identifier' attribute of the 'Product' is important for the licensing configuration.");
		sb.append('\n');
		sb.append('\n');
		sb.append("6. Create Product Version").append('\n');
		sb.append("The 'Product Version' corresponds to the binaries you are planning to release or already released for the 'Product'. ");
		sb.append("It is recommended to create the 'Product Version' for each external 'Product' shipment. ");
		sb.append("The 'version' attribute of the 'Product Version' is important for the licensing configuration. ");
		sb.append("The 'Product Version' completes the definition of licensing configuration that will be checked in the user environment.");
		sb.append('\n');
		sb.append('\n');
		sb.append("7. Create Product Version Feature").append('\n');
		sb.append("The 'Product Version Feature' describes the state of functionality included to the 'Product Version'. ");
		sb.append("It is used as a reference to formulate 'License Pack'.");
		sb.append('\n');
		sb.append('\n');
		sb.append("8. Create User Origin").append('\n');
		sb.append("The 'User Origin' provides access to 'User' descriptors. ");
		sb.append("The typical example of the 'User Origin' is the list of users registered on your web site to download 'Product Version'.");
		sb.append('\n');
		sb.append('\n');
		sb.append("9. Create User").append('\n');
		sb.append("The 'User' describes the licensee of your functionality. ");
		sb.append("The 'identifier' of the 'User' is a part of the 'License Pack' definition.");
		sb.append('\n');
		sb.append('\n');
		sb.append("10. Create License Pack").append('\n');
		sb.append("The 'License Pack' is a container for 'License Grant' descriptors. ");
		sb.append("It has references to the 'Product Version' that is licensed to 'User'. ");
		sb.append("The 'License Pack' is intended to be delivered to the 'User' environment to allow usage of restricted functionality.");
		return sb.toString();
	}

}