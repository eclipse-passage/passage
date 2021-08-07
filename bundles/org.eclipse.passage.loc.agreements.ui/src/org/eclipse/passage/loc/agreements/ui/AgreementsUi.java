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

import org.eclipse.passage.lic.agreements.AgreementDescriptor;
import org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage;
import org.eclipse.passage.loc.internal.agreements.AgreementRegistry;
import org.eclipse.passage.loc.internal.agreements.ui.i18n.AgreementsUiMessages;
import org.eclipse.passage.loc.workbench.LocWokbench;
import org.eclipse.swt.widgets.Shell;

public final class AgreementsUi {

	public static final String BUNDLE_SYMBOLIC_NAME = "org.eclipse.passage.loc.agreements.ui"; //$NON-NLS-1$

	public static final String PERSPECTIVE_MAIN = BUNDLE_SYMBOLIC_NAME + '.' + "perspective.main"; //$NON-NLS-1$

	public static AgreementDescriptor selectAgreementDescriptor(Shell shell, AgreementRegistry registry,
			AgreementDescriptor initial) {
		String classifier = AgreementsPackage.eINSTANCE.getAgreement().getName();
		String title = AgreementsUiMessages.AgreementsUi_select_agreement;
		Iterable<? extends AgreementDescriptor> input = registry.agreements();
		Class<AgreementDescriptor> clazz = AgreementDescriptor.class;
		return LocWokbench.selectClassifier(shell, classifier, title, input, initial, clazz);
	}
}
