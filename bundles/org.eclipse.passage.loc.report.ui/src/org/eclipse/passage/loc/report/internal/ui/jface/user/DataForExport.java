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
package org.eclipse.passage.loc.report.internal.ui.jface.user;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Supplier;

import org.eclipse.passage.loc.report.internal.ui.jface.ExportWizardDecisions;

final class DataForExport extends ExportWizardDecisions {

	private final Supplier<Set<String>> products;

	DataForExport(//
			Supplier<Set<String>> products, //
			Supplier<Path> target, //
			Supplier<Boolean> open) {
		super(target, open);
		this.products = products;
	}

	Set<String> products() {
		return products.get();
	}

	@Override
	protected boolean dataComplete() {
		return !products.get().isEmpty();
	}

}
