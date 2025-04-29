/*******************************************************************************
 * Copyright (c) 2025 ArSysOp
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

import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.diagnostic.RequirementStatus;
import org.eclipse.passage.lic.base.diagnostic.RequirementsCoverage;
import org.eclipse.passage.lic.internal.jface.i18n.LicenseStatusDialogMessages;
import org.eclipse.swt.widgets.Composite;

public final class RequirementsStatusViewer {

	private final ExaminationCertificate certificate;
	private TableViewer viewer;

	public RequirementsStatusViewer(ExaminationCertificate certificate) {
		this.certificate = certificate;
	}

	public void installControl(Composite parent) {
		installControl(parent, List.of(//
				new SimpleViewerColumn<RequirementStatus>(LicenseStatusDialogMessages.LicenseStatusDialog_column_id,
						600, RequirementStatus::feature),
				new SimpleViewerColumn<RequirementStatus>(LicenseStatusDialogMessages.LicenseStatusDialog_column_status,
						500, RequirementStatus::status)//
		));
	}

	public void installControl(Composite parent, List<SimpleViewerColumn<RequirementStatus>> columns) {
		viewer = new HereTable<>(parent, RequirementStatus.class) //
				.withColumns(columns) //
				.viewer();
	}

	public void installInput() {
		viewer.setInput(new RequirementsCoverage(certificate).get());
	}

	public TableViewer viewer() {
		return viewer;
	}

}
