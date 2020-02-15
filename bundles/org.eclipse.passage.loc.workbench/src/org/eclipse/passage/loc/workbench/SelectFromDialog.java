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
package org.eclipse.passage.loc.workbench;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.passage.loc.jface.dialogs.Appearance;
import org.eclipse.passage.loc.jface.dialogs.FilteredSelectionDialog;
import org.eclipse.passage.loc.jface.dialogs.LabelSearchFilter;
import org.eclipse.swt.widgets.Shell;

/**
 * Selects the classifier from the given input with
 * {@link FilteredSelectionDialog}
 *
 * @param <C> classifier to be selected see {@link EClass#getName()}
 */
public class SelectFromDialog<C> implements Function<Iterable<C>, Optional<C>> {

	private final FilteredSelectionDialog<C> dialog;

	/**
	 * 
	 * @param shell      the {@link Shell} to use for
	 *                   {@link FilteredSelectionDialog}, must not be
	 *                   <code>null</code>
	 * @param appearance the title, image and {@link LabelProvider} to use for
	 *                   {@link FilteredSelectionDialog}, must not be
	 *                   <code>null</code>
	 */
	public SelectFromDialog(Shell shell, Appearance appearance) {
		Objects.requireNonNull(shell, WorkbenchMessages.SelectFromDialog_e_null_shell);
		Objects.requireNonNull(appearance, WorkbenchMessages.SelectFromDialog_e_null_appearance);
		this.dialog = new FilteredSelectionDialog<C>(shell, false, new LabelSearchFilter());
		dialog.setTitle(appearance.title());
		dialog.setImage(appearance.image());
		dialog.setLabelProvider(appearance.labelProvider());
	}

	/**
	 * 
	 * @param shell      the {@link Shell} to use for
	 *                   {@link FilteredSelectionDialog}, must not be
	 *                   <code>null</code>
	 * @param appearance the title, image and {@link LabelProvider} to use for
	 *                   {@link FilteredSelectionDialog}, must not be
	 *                   <code>null</code>
	 * @param initial    the object to be a default choice for
	 *                   {@link FilteredSelectionDialog}, must not be
	 *                   <code>null</code>
	 */
	public SelectFromDialog(Shell shell, Appearance appearance, C initial) {
		this(shell, appearance);
		Objects.requireNonNull(initial, WorkbenchMessages.SelectFromDialog_e_null_initial);
		dialog.setInitialSelection(Collections.singletonList(initial));
	}

	/**
	 * Returns the selected object or {@link Optional#empty()}
	 */
	@Override
	public Optional<C> apply(Iterable<C> input) {
		dialog.setInput(input);
		if (dialog.open() == Dialog.OK) {
			return dialog.getFirstResult();
		}
		return Optional.empty();
	}

}
