/*******************************************************************************
 * Copyright (c) 2022, 2024 ArSysOp
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

import java.util.Date;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.licenses.model.i18n.Messages;
import org.eclipse.passage.lic.licenses.model.api.ValidityPeriod;
import org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed;

public final class DescribedValidityPeriodClosed implements Supplier<String> {

	private final ValidityPeriodClosed valid;

	public DescribedValidityPeriodClosed(ValidityPeriodClosed valid) {
		this.valid = valid;
	}

	public DescribedValidityPeriodClosed(ValidityPeriod valid) {
		this((ValidityPeriodClosed) valid);
	}

	@Override
	public String get() {
		return String.format(//
				Messages.getString("DescribedValidityPeriodClosed.template"), //$NON-NLS-1$
				date(valid.getFrom()), //
				date(valid.getUntil())//
		);
	}

	private String date(Date date) {
		return new DescribedDate(date).get();
	}

}
