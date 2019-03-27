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
package org.eclipse.passage.lic.internal.e4.ui.restrictions;

import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.base.restrictions.RestrictionVerdicts;
import org.eclipse.passage.lic.jface.dialogs.LicensingStatusDialog;
import org.eclipse.passage.lic.runtime.LicensingResult;
import org.eclipse.passage.lic.runtime.restrictions.RestrictionExecutor;
import org.eclipse.passage.lic.runtime.restrictions.RestrictionVerdict;
import org.eclipse.swt.widgets.Shell;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component
public class WorkbenchRestrictionExecutor implements RestrictionExecutor {

	private BundleContext bundleContext;
	private IApplicationContext applicationContext;

	@Activate
	public void activate(BundleContext context) {
		this.bundleContext = context;
	}

	@Reference
	public void bindApplication(IApplicationContext context) {
		this.applicationContext = context;
	}

	public void unbindApplication() {
		this.applicationContext = null;
	}

	@Override
	public LicensingResult execute(Iterable<RestrictionVerdict> verdicts) {
		String featureId = applicationContext.getBrandingId();
		RestrictionVerdict lastVerdict = RestrictionVerdicts.resolveLastVerdict(verdicts, featureId);
		boolean showDialog = RestrictionVerdicts.shouldPauseExecution(lastVerdict);
		IEclipseContext serviceContext = EclipseContextFactory.getServiceContext(bundleContext);
		if (showDialog) {
			Shell shell = serviceContext.get(Shell.class);
			LicensingStatusDialog dialog = new LicensingStatusDialog(shell, featureId);
			dialog.open();
			lastVerdict = RestrictionVerdicts.resolveLastVerdict(dialog.getRestrictions(), featureId);
		}
		boolean andExit = RestrictionVerdicts.shouldInterruptExecution(lastVerdict);
		if (andExit) {
			IWorkbench workbench = serviceContext.get(IWorkbench.class);
			workbench.close();
		}
		return LicensingResults.createOK();
	}

}
