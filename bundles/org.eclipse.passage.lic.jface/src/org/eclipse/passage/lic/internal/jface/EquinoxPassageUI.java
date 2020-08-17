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
package org.eclipse.passage.lic.internal.jface;

import java.util.function.Supplier;

import org.eclipse.jface.window.Window;
import org.eclipse.passage.lic.internal.api.PassageUI;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.base.access.NoSevereRestrictions;
import org.eclipse.passage.lic.internal.equinox.EquinoxPassage;
import org.eclipse.passage.lic.internal.jface.dialogs.licensing.DiagnosticDialog;
import org.eclipse.passage.lic.internal.jface.dialogs.licensing.LicenseStatusDialog;
import org.eclipse.swt.widgets.Shell;

@SuppressWarnings("restriction")
public final class EquinoxPassageUI implements PassageUI {

	private final Supplier<Shell> shell;

	public EquinoxPassageUI(Supplier<Shell> shell) {
		this.shell = shell;
	}

	@Override
	public boolean canUse(String feature) {
		ServiceInvocationResult<ExaminationCertificate> result = ask(feature);
		while (exposeAndMayBeEvenFix(result)) {
			result = ask(feature);
		}
		return result.data().isPresent() ? new NoSevereRestrictions().test(result.data().get()) : false;
	}

	private ServiceInvocationResult<ExaminationCertificate> ask(String feature) {
		return new EquinoxPassage().acquireLicense(feature);
	}

	private boolean exposeAndMayBeEvenFix(ServiceInvocationResult<ExaminationCertificate> last) {
		if (!last.data().isPresent()) {
			new DiagnosticDialog(shell.get(), last.diagnostic()).open();
			return false;
		}

		if (!new NoSevereRestrictions().test(last.data().get())) {
			LicenseStatusDialog dialog = new LicenseStatusDialog(shell.get(), last.data().get());
			if (Window.OK != dialog.open()) {
				return false;
			}
			return dialog.goodIntention().paveTheWay();
		}
		return false;
	}

}
