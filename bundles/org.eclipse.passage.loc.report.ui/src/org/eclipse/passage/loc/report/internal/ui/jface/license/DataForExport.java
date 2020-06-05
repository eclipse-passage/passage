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
 *      ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.report.internal.ui.jface.license;

import java.nio.file.Path;
import java.util.Date;
import java.util.Set;
import java.util.function.Supplier;

import org.eclipse.passage.loc.report.internal.ui.jface.ExportWizardDecisions;

final class DataForExport extends ExportWizardDecisions {

	private final Supplier<Set<String>> plans;
	private final Supplier<Date> from;
	private final Supplier<Date> to;
	private final Supplier<Boolean> explain;

	DataForExport(//
			Supplier<Set<String>> plans, //
			Supplier<Date> from, //
			Supplier<Date> to, //
			Supplier<Boolean> explain, //
			Supplier<Path> target, //
			Supplier<Boolean> open) {
		super(target, open);
		this.plans = plans;
		this.from = from;
		this.to = to;
		this.explain = explain;
	}

	Set<String> plans() {
		return plans.get();
	}

	Date from() {
		return from.get();
	}

	Date to() {
		return to.get();
	}

	boolean explain() {
		return explain.get();
	}

	@Override
	protected boolean dataComplete() {
		return !plans.get().isEmpty() && //
				from.get().before(to.get());
	}

}
