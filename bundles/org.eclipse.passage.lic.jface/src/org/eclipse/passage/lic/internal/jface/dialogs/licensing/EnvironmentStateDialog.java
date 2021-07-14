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
package org.eclipse.passage.lic.internal.jface.dialogs.licensing;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.inspection.RuntimeEnvironment;
import org.eclipse.passage.lic.equinox.Environments;
import org.eclipse.passage.lic.internal.jface.i18n.EnvironmentStateDialogMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

public final class EnvironmentStateDialog extends Dialog {

	private final List<RuntimeEnvironment> environments;

	public EnvironmentStateDialog(Shell shell) {
		super(shell);
		this.environments = new ArrayList<>(new Environments().get());
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		TabFolder folder = new TabFolder(parent, SWT.BORDER);
		Rectangle clientArea = parent.getClientArea();
		folder.setLocation(clientArea.x, clientArea.y);
		folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		environments.forEach(env -> installTabPerEnvironment(folder, env));
		folder.pack();
		return folder;
	}

	private void installTabPerEnvironment(TabFolder folder, RuntimeEnvironment environment) {
		TabItem item = new TabItem(folder, SWT.NONE);
		item.setText(environment.id().identifier());
		item.setControl(content(folder, environment));
	}

	private Composite content(TabFolder folder, RuntimeEnvironment environment) {
		ScrolledComposite scrolled = new ScrolledComposite(folder, SWT.V_SCROLL | SWT.BORDER);
		Composite content = display(scrolled, environment);
		scrolled.addControlListener(ControlListener.controlResizedAdapter(e -> {
			Rectangle area = scrolled.getClientArea();
			scrolled.setMinSize(content.computeSize(area.width, SWT.DEFAULT));
		}));
		return scrolled;
	}

	private Composite display(ScrolledComposite scrolled, RuntimeEnvironment environment) {
		Composite composite = new Composite(scrolled, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		composite.setLayout(new GridLayout(1, false));
		Text state = new Text(composite, SWT.MULTI | SWT.READ_ONLY | SWT.WRAP);
		state.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		state.setText(state(environment));
		composite.pack();
		scrolled.setContent(composite);
		return composite;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setSize(500, 700);
		shell.setText(EnvironmentStateDialogMessages.EnvironmentStatesDialog_title);
	}

	private String state(RuntimeEnvironment environment) {
		try {
			return environment.state();
		} catch (LicensingException e) {
			return String.format(//
					EnvironmentStateDialogMessages.EnvironmentStatesDialog_env_error, //
					environment.id(), //
					e.getLocalizedMessage());
		}
	}

}
