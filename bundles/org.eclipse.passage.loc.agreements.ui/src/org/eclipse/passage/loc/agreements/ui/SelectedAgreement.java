/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.loc.agreements.ui;

import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.agreements.AgreementDescriptor;
import org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage;
import org.eclipse.passage.loc.internal.agreements.AgreementRegistry;
import org.eclipse.passage.loc.internal.agreements.ui.i18n.AgreementsUiMessages;
import org.eclipse.passage.loc.workbench.LocWokbench;
import org.eclipse.swt.widgets.Shell;

public final class SelectedAgreement implements Supplier<Optional<AgreementDescriptor>> {

	private final Shell shell;
	private final AgreementRegistry registry;
	private final AgreementDescriptor initial;

	public SelectedAgreement(Shell shell, AgreementRegistry registry, AgreementDescriptor initial) {
		this.shell = shell;
		this.registry = registry;
		this.initial = initial;
	}

	@Override
	public Optional<AgreementDescriptor> get() {
		return Optional.ofNullable(//
				LocWokbench.selectClassifier(//
						shell, //
						AgreementsPackage.eINSTANCE.getAgreement().getName(), //
						AgreementsUiMessages.AgreementsUi_select_agreement, //
						registry.agreements(), //
						initial, //
						AgreementDescriptor.class)//
		);
	}

}
