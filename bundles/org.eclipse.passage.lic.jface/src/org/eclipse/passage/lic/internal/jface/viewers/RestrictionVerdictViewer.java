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
package org.eclipse.passage.lic.internal.jface.viewers;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.passage.lic.internal.jface.i18n.JFaceMessages;
import org.eclipse.passage.lic.jface.viewers.LicensingTableVieweBuilder;
import org.eclipse.swt.widgets.Table;

public class RestrictionVerdictViewer {

	public static TableViewer createTableViewer(Table table) {
		LicensingTableVieweBuilder builder = LicensingTableVieweBuilder.forTable(table);
		builder.addColumn(JFaceMessages.RestrictionVerdictViewer_column_status, 20, RestrictionVerdictViewerAdapter.INDEX_STATUS);
		builder.addColumn(JFaceMessages.RestrictionVerdictViewer_column_provider, 150, RestrictionVerdictViewerAdapter.INDEX_PROVIDER);
		builder.addColumn(JFaceMessages.RestrictionVerdictViewer_column_name, 300, RestrictionVerdictViewerAdapter.INDEX_NAME);
		builder.addColumn(JFaceMessages.RestrictionVerdictViewer_column_version, 80, RestrictionVerdictViewerAdapter.INDEX_VERSION);
		builder.addColumn(JFaceMessages.RestrictionVerdictViewer_column_verdict, 200,
				RestrictionVerdictViewerAdapter.INDEX_VERDICT);
		return builder.getTableViewer();
	}

}
