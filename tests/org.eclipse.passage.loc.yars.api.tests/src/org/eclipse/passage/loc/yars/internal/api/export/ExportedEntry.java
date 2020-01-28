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

import org.eclipse.passage.loc.yars.internal.api.DOSHandleMedia;
import org.eclipse.passage.loc.yars.internal.api.ExportData;

@SuppressWarnings("restriction")
public class ExportedEntry implements ExportData<ExportedEntry, DOSHandleMedia<ExportedEntry>> {

	private String name;

	public ExportedEntry(String name) {
		this.name = name;
	}

	@Override
	public void write(DOSHandleMedia<ExportedEntry> media) {
		media.inner(name, "name"); //$NON-NLS-1$
	}

	@Override // generated
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ExportedEntry exportedEntry = (ExportedEntry) o;
		return Objects.equals(name, exportedEntry.name);
	}

	@Override // generated
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}

}
