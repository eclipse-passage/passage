/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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
package org.eclipse.passage.lic.licenses.model.described;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;

public final class DescribedDate implements Supplier<String> {

	private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.YYYY z"); //$NON-NLS-1$

	private final Date date;

	public DescribedDate(Date date) {
		this.date = date;
	}

	@Override
	public String get() {
		return format.format(date);
	}

}
