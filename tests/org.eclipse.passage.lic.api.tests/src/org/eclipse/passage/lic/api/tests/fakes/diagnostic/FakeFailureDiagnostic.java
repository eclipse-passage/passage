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
package org.eclipse.passage.lic.api.tests.fakes.diagnostic;

import java.util.List;

import org.eclipse.passage.lic.internal.api.diagnostic.FailureDiagnostic;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;

@SuppressWarnings("restriction")
public final class FakeFailureDiagnostic implements FailureDiagnostic {

	@Override
	public List<Trouble> troubles() {
		throw new UnsupportedOperationException();
	}

}
