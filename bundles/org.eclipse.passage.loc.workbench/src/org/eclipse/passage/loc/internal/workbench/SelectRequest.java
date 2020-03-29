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
package org.eclipse.passage.loc.internal.workbench;

import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.passage.loc.jface.dialogs.Appearance;

/**
 * Encapsulates the information required to select the classifier
 * 
 * @param <R> target type to be selected
 */
public final class SelectRequest<R> {

	private final Class<R> target;
	private final String domain;
	private final Supplier<Iterable<R>> input;
	private final Appearance appearance;

	/**
	 * Creates a {@link SelectRequest} instance for target classifier that belongs
	 * to a given domain, also specifies the input to select from and the dialog
	 * appearance
	 * 
	 * @param target     the type of object to be selected, must not be
	 *                   <code>null</code>
	 * @param domain     the domain that target belongs to, must not be
	 *                   <code>null</code>
	 * @param input      the input to select from, must not be <code>null</code>
	 * @param appearance the appearance of UI dialog, must not be <code>null</code>
	 */
	public SelectRequest(Class<R> target, String domain, Supplier<Iterable<R>> input, Appearance appearance) {
		Objects.requireNonNull(target, WorkbenchMessages.SelectRequest_e_target_null);
		Objects.requireNonNull(domain, WorkbenchMessages.SelectRequest_e_domain_null);
		Objects.requireNonNull(input, WorkbenchMessages.SelectRequest_e_input_null);
		Objects.requireNonNull(appearance, WorkbenchMessages.SelectRequest_e_appearance_null);
		this.target = target;
		this.domain = domain;
		this.input = input;
		this.appearance = appearance;
	}

	/**
	 * The type of object to be selected
	 * 
	 * @return non-<code>null</code> target
	 */
	public Class<R> target() {
		return target;
	}

	/**
	 * The domain of object to be selected
	 * 
	 * @return non-<code>null</code> domain
	 */
	public String domain() {
		return domain;
	}

	/**
	 * The supplier of input to select from
	 * 
	 * @return non-<code>null</code> supplier of input
	 */
	public Supplier<Iterable<R>> input() {
		return input;
	}

	/**
	 * The appearance to use for UI
	 * 
	 * @return non-<code>null</code> appearance
	 */
	public Appearance appearance() {
		return appearance;
	}

}
