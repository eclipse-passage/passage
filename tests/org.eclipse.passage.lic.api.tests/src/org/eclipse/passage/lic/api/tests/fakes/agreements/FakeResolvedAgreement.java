/*******************************************************************************
 * Copyright (c) 2024 ArSysOp
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
package org.eclipse.passage.lic.api.tests.fakes.agreements;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.passage.lic.api.agreements.ResolvedAgreement;

@SuppressWarnings("restriction")
public final class FakeResolvedAgreement implements ResolvedAgreement {

	@Override
	public InputStream content() throws IOException {
		return new ByteArrayInputStream(new byte[0]);
	}

	@Override
	public String path() {
		return "not very much real path"; //$NON-NLS-1$
	}

}
