/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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
package org.eclipse.passage.lic.licenses.model.described;

import org.eclipse.passage.lic.internal.licenses.model.i18n.Messages;
import org.eclipse.passage.lic.licenses.EvaluationInstructionsDescriptor;

public final class DescribedAuthenticationInstructions extends Described {

	private final EvaluationInstructionsDescriptor auth;

	public DescribedAuthenticationInstructions(EvaluationInstructionsDescriptor auth) {
		this.auth = auth;
	}

	@Override
	public String get() {
		StringBuilder out = new StringBuilder();
		out.append(tabs).append(Messages.getString("DescribedAuthenticationInstructions_environment")) //$NON-NLS-1$
				.append(auth.getType()).append(nl)//
				.append(tabs).append(tabs).append(Messages.getString("DescribedAuthenticationInstructions_expression")) //$NON-NLS-1$
				.append(auth.getExpression()).append(nl);
		return out.toString();
	}

}
