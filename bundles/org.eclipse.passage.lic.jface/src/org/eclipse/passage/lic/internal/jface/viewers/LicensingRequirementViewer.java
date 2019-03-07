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

public class LicensingRequirementViewer {

	public static TableViewer createTableViewer(Table table) {
		LicensingTableVieweBuilder builder = LicensingTableVieweBuilder.forTable(table);
		builder.addColumn("", 20, LicensingRequirementViewerAdapter.INDEX_STATUS);
		builder.addColumn("Provider", 150, LicensingRequirementViewerAdapter.INDEX_PROVIDER);
		builder.addColumn("Name", 300, LicensingRequirementViewerAdapter.INDEX_NAME);
		builder.addColumn("Version", 80, LicensingRequirementViewerAdapter.INDEX_VERSION);
		builder.addColumn("Idenitifier", 200, LicensingRequirementViewerAdapter.INDEX_IDENTIFIER);
		builder.addColumn("Level", 50, LicensingRequirementViewerAdapter.INDEX_LEVEL);
		return builder.getTableViewer();
	}

}
