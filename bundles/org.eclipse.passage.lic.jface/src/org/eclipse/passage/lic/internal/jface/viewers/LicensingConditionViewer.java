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
import org.eclipse.passage.lic.jface.viewers.LicensingTableVieweBuilder;
import org.eclipse.swt.widgets.Table;

public class LicensingConditionViewer {

	public static TableViewer createTableViewer(Table table) {
		LicensingTableVieweBuilder builder = LicensingTableVieweBuilder.forTable(table);
		builder.addColumn("", 20, LicensingConditionViewerAdapter.INDEX_STATUS);
		builder.addColumn("Provider", 150, LicensingConditionViewerAdapter.INDEX_PROVIDER);
		builder.addColumn("Name", 300, LicensingConditionViewerAdapter.INDEX_NAME);
		builder.addColumn("Idenitifier", 200, LicensingConditionViewerAdapter.INDEX_IDENTIFIER);
		builder.addColumn("Valid From", 200, LicensingConditionViewerAdapter.INDEX_VALID_FROM);
		builder.addColumn("Valid Until", 200, LicensingConditionViewerAdapter.INDEX_VALID_UNTIL);
		builder.addColumn("Version", 80, LicensingConditionViewerAdapter.INDEX_MATCH_VERSION);
		builder.addColumn("Rule", 100, LicensingConditionViewerAdapter.INDEX_MATCH_RULE);
		builder.addColumn("Type", 100, LicensingConditionViewerAdapter.INDEX_CONDITION_TYPE);
		return builder.getTableViewer();
	}

}
