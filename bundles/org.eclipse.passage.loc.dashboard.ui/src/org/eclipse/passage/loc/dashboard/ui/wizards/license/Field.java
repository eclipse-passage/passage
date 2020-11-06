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
package org.eclipse.passage.loc.dashboard.ui.wizards.license;

import java.util.Optional;

import org.eclipse.swt.widgets.Composite;

public interface Field<T> {

	/**
	 * Once in a lifetime physically creates UI control representing the field.
	 */
	void installControll(Composite parent);

	/**
	 * Return a value from the field's control, it any.
	 */
	Optional<T> data();

	/**
	 * Is used by page validation. When called, reports an error, if the filed
	 * control's value is not valid. Return {@code Optional.empty()} otherwise.
	 */
	Optional<String> errorIfAny();

	void enable(boolean enable);
}
