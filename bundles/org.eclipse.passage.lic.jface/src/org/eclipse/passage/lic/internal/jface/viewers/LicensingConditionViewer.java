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
package org.eclipse.passage.lic.internal.jface.viewers;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.passage.lic.internal.jface.i18n.JFaceMessages;
import org.eclipse.passage.lic.jface.viewers.LicensingTableVieweBuilder;
import org.eclipse.swt.widgets.Table;

public class LicensingConditionViewer {

	public static TableViewer createTableViewer(Table table) {
		LicensingTableVieweBuilder builder = LicensingTableVieweBuilder.forTable(table);
		builder.addColumn(JFaceMessages.LicensingConditionViewer_column_index, 20, LicensingConditionViewerAdapter.INDEX_STATUS);
		builder.addColumn(JFaceMessages.LicensingConditionViewer_column_provider, 150, LicensingConditionViewerAdapter.INDEX_PROVIDER);
		builder.addColumn(JFaceMessages.LicensingConditionViewer_column_name, 300, LicensingConditionViewerAdapter.INDEX_NAME);
		builder.addColumn(JFaceMessages.LicensingConditionViewer_column_identifier, 200, LicensingConditionViewerAdapter.INDEX_IDENTIFIER);
		builder.addColumn(JFaceMessages.LicensingConditionViewer_column_valid_from, 200, LicensingConditionViewerAdapter.INDEX_VALID_FROM);
		builder.addColumn(JFaceMessages.LicensingConditionViewer_column_valid_until, 200, LicensingConditionViewerAdapter.INDEX_VALID_UNTIL);
		builder.addColumn(JFaceMessages.LicensingConditionViewer_column_version, 80, LicensingConditionViewerAdapter.INDEX_MATCH_VERSION);
		builder.addColumn(JFaceMessages.LicensingConditionViewer_column_rule, 100, LicensingConditionViewerAdapter.INDEX_MATCH_RULE);
		builder.addColumn(JFaceMessages.LicensingConditionViewer_column_type, 100, LicensingConditionViewerAdapter.INDEX_CONDITION_TYPE);
		return builder.getTableViewer();
	}

}
