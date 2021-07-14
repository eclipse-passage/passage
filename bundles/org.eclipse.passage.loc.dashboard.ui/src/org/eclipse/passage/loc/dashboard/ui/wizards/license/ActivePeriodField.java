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
package org.eclipse.passage.loc.dashboard.ui.wizards.license;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;
import org.eclipse.swt.widgets.Composite;

final class ActivePeriodField implements Field<List<LocalDate>> {

	private final DateField from;
	private final DateField until;

	ActivePeriodField(Runnable modified, LabelProvider labels, MandatoryService context) {
		from = new DateField(LocalDate.now(), //
				IssueLicensePageMessages.IssueLicenseRequestPage_lbl_valid_from, //
				modified, labels, context);
		until = new DateField(LocalDate.now().plusYears(1), //
				IssueLicensePageMessages.IssueLicenseRequestPage_lbl_valid_until, //
				modified, labels, context);
	}

	@Override
	public void installControll(Composite parent) {
		from.installControll(parent);
		until.installControll(parent);
	}

	@Override
	public Optional<List<LocalDate>> data() {
		Optional<LocalDate> start = from.data();
		Optional<LocalDate> end = until.data();
		return (!start.isPresent() || !end.isPresent()) //
				? Optional.empty() //
				: Optional.of(Arrays.asList(start.get(), end.get()));
	}

	@Override
	public Optional<String> errorIfAny() {
		Optional<String> start = from.error();
		if (start.isPresent()) {
			return start;
		}
		Optional<String> end = until.error();
		if (end.isPresent()) {
			return end;
		}
		if (from.data().get().isAfter(until.data().get())) {
			return Optional.of(IssueLicensePageMessages.IssueLicenseRequestPage_e_reversed_period);
		}
		return Optional.empty();
	}

	@Override
	public void enable(boolean enable) {
		from.enable(enable);
		until.enable(enable);
	}

}
