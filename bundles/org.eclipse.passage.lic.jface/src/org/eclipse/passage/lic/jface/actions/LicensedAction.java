/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.jface.actions;

import java.util.Optional;

import org.eclipse.jface.action.Action;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.base.restrictions.CertificateIsRestrictive;
import org.eclipse.passage.lic.internal.equinox.EquinoxPassage;
import org.eclipse.passage.lic.internal.jface.EquinoxPassageUI;
import org.eclipse.swt.widgets.Event;

@SuppressWarnings("restriction")
public class LicensedAction extends Action {

	@Override
	public void runWithEvent(Event event) {
		Optional<ServiceInvocationResult<ExaminationCertificate>> response = Optional.empty();
		try {
			response = Optional.of(new EquinoxPassageUI(event.display::getActiveShell).acquireLicense(getId()));
			if (!new CertificateIsRestrictive().test(response.get().data())) {
				super.runWithEvent(event);
			}
		} finally {
			response.flatMap(ServiceInvocationResult::data)//
					.ifPresent(certificate -> new EquinoxPassage().releaseLicense(certificate));
		}
	}

}
