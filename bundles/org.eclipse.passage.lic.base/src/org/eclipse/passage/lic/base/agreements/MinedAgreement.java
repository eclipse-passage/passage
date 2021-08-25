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
package org.eclipse.passage.lic.base.agreements;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.passage.lic.api.agreements.GlobalAgreement;
import org.eclipse.passage.lic.api.agreements.ResolvedAgreement;

public final class MinedAgreement implements ResolvedAgreement {
	private final GlobalAgreement agreement;

	public MinedAgreement(GlobalAgreement agreement) {
		this.agreement = agreement;
	}

	@Override
	public InputStream content() throws IOException {
		return new ByteArrayInputStream(agreement.content());
	}

	@Override
	public String path() {
		return agreement.file();
	}

}
