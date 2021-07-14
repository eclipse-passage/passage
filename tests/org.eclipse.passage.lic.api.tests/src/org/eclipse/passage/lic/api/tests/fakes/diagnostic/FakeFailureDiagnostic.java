/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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

import java.util.Collections;
import java.util.List;

import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.api.diagnostic.Trouble;

public final class FakeFailureDiagnostic implements Diagnostic {

	@Override
	public List<Trouble> bearable() {
		return Collections.emptyList();
	}

	@Override
	public List<Trouble> severe() {
		return Collections.emptyList();
	}

}
