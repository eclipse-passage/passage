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
package org.eclipse.passage.lic.internal.equinox.access;

import java.util.Optional;

import org.eclipse.passage.lic.api.agreements.AgreementAcceptanceService;
import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.internal.base.access.Libraries;

/**
 * <p>
 * Licensing agreement can be demanded for acceptance by product itself as well
 * as by ant library it employs.
 * </p>
 * <p>
 * Here we survey all registered libraries whether they are eligible to perform
 * acceptance of a given agreement, and use product own acceptance service if
 * none of the libraries step in.
 * </p>
 */
@SuppressWarnings("restriction")
public final class AgreementAcceptanceDelegate {

	private final AgreementAcceptanceService root;
	private final Libraries libraries;

	public AgreementAcceptanceDelegate(AgreementAcceptanceService root, Libraries libraries) {
		this.root = root;
		this.libraries = libraries;
	}

	public void accept(AgreementToAccept agreement) throws Exception {
		AgreementAcceptanceService service = root;
		Optional<AgreementAcceptanceService> library = libraries.agreementsService(agreement);
		if (library.isPresent()) {
			service = library.get();
		}
		service.accept(() -> agreement.acceptance().content());
	}

}
