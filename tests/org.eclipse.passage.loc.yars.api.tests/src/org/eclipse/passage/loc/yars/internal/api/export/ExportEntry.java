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
package org.eclipse.passage.loc.yars.internal.api.export;

import java.util.Objects;

import org.eclipse.passage.loc.yars.internal.api.DosHandleMedia;
import org.eclipse.passage.loc.yars.internal.api.ExportData;
import org.eclipse.passage.loc.yars.internal.api.Progress;

@SuppressWarnings("restriction")
final class ExportEntry implements ExportData<ExportEntry, DosHandleMedia<ExportEntry>> {

	private String name;

	public ExportEntry(String name) {
		this.name = name;
	}

	@Override
	public void write(DosHandleMedia<ExportEntry> media, Progress<ExportEntry> progress) {
		media.inner(name, "name"); //$NON-NLS-1$
		progress.report(name);
	}

	@Override // generated
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ExportEntry exportedEntry = (ExportEntry) o;
		return Objects.equals(name, exportedEntry.name);
	}

	@Override // generated
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}

}
