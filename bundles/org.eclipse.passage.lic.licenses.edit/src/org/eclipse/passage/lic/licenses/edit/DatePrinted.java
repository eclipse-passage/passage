/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.licenses.edit;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public final class DatePrinted implements Supplier<String> {

	private final Optional<Date> date;

	public DatePrinted(Optional<Date> date) {
		Objects.requireNonNull(date, "DatePrinted::date"); //$NON-NLS-1$
		this.date = date;
	}

	/**
	 * @param date nullable
	 */
	public DatePrinted(Date date) {
		this(Optional.ofNullable(date));
	}

	@Override
	public String get() {
		return date//
				.map(d -> LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault()).toLocalDate().toString()) //
				.orElse("unknown");//$NON-NLS-1$
	}

}
