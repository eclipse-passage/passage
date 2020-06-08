/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.loc.internal.licenses.ui.handlers;

import java.util.Optional;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.loc.internal.licenses.ui.i18n.LicensesUiMessages;
import org.eclipse.passage.loc.report.internal.core.license.LicenseReportExportService;
import org.eclipse.passage.loc.report.internal.core.license.LicenseStorage;
import org.eclipse.passage.loc.report.internal.ui.jface.license.ExposedIssuedLicensesReportWizard;
import org.eclipse.swt.widgets.Shell;

public class IssuedLicensesReportHandler {

	@Execute
	public void execute(IEclipseContext context) {
		Optional<LicenseStorage> storage = service(LicenseStorage.class, context);
		if (!storage.isPresent()) {
			return;
		}
		Optional<LicenseReportExportService> export = service(LicenseReportExportService.class, context);
		if (!export.isPresent()) {
			return;
		}
		new ExposedIssuedLicensesReportWizard(//
				storage.get(), //
				export.get()//
		).accept(context.get(Shell.class));
	}

	private <S> Optional<S> service(Class<S> service, IEclipseContext context) {
		Optional<S> implementation = Optional.ofNullable(context.get(service));
		if (!implementation.isPresent()) {
			MessageDialog.openError(//
					context.get(Shell.class), //
					LicensesUiMessages.IssuedLicensesReportHandler_unavailableTitle, //
					NLS.bind(LicensesUiMessages.IssuedLicensesReportHandler_unavailableMessage, service.getName())//
			);
		}
		return implementation;
	}

}
