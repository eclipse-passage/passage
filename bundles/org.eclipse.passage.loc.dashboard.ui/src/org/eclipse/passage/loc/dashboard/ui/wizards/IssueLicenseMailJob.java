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
package org.eclipse.passage.loc.dashboard.ui.wizards;

import java.io.File;
import java.util.Optional;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;
import org.eclipse.passage.loc.internal.licenses.core.LicenseMailSupport;
import org.eclipse.passage.loc.licenses.core.Licenses;
import org.eclipse.swt.program.Program;

public class IssueLicenseMailJob extends Job {
	private LicensingResult licensinResult;
	private boolean createEml;
	private boolean createMail;
	private LicenseMailSupport mailSupport;
	private Optional<File> optEmlFile;

	public IssueLicenseMailJob(String jobName, LicensingResult licensinResult, LicenseMailSupport mailSupport,
			boolean createMail, boolean createEml) {
		super(jobName);
		this.licensinResult = licensinResult;
		this.createEml = createEml;
		this.createMail = createMail;
		this.mailSupport = mailSupport;
		this.optEmlFile = Optional.empty();
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		monitor.beginTask(IssueLicensePageMessages.IssueLicensingMailJob_task_text, IProgressMonitor.UNKNOWN);
		if (createMail) {
			processingToMailClient();
		}

		if (createEml) {
			String licenseOut = (String) licensinResult.getAttachment(Licenses.LICENSE_OUT);
			if (licenseOut != null && !licenseOut.isEmpty()) {
				optEmlFile = processingToMailEml(new File(licenseOut));
			}
		}
		monitor.done();
		return Status.OK_STATUS;
	}

	public void processingToMailClient() {
		Program.launch(mailSupport.getMailToString());
	}

	public Optional<File> processingToMailEml(File attachement) {
		return mailSupport.createEmlFile(attachement);
	}

	public Optional<File> getEmlFile() {
		return optEmlFile;
	}
}
