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
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.internal.jface.dialogs.licensing;

import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

final class FirstSelected<T> implements Supplier<Optional<T>> {

	private final ISelection selection;
	private final Class<T> cls;

	FirstSelected(ISelection selection, Class<T> cls) {
		this.selection = selection;
		this.cls = cls;
	}

	@Override
	public Optional<T> get() {
		return Optional.ofNullable(selection)//
				.filter(IStructuredSelection.class::isInstance)//
				.map(IStructuredSelection.class::cast)//
				.filter(structured -> !structured.isEmpty()) //
				.map(IStructuredSelection::getFirstElement)//
				.map(cls::cast); //
	}

}
