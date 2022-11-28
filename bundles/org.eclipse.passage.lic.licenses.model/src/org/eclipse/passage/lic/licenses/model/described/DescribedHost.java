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
import org.eclipse.passage.lic.licenses.FloatingServerDescriptor;

public final class DescribedHost extends Described {

	private final FloatingServerDescriptor server;

	public DescribedHost(FloatingServerDescriptor server) {
		this.server = server;
	}

	@Override
	public String get() {
		StringBuilder out = new StringBuilder();
		out.append(tab).append(Messages.getString("DescribedHost_host")) //$NON-NLS-1$
				.append(server.getIdentifier()).append(nl)//
				.append(tab).append(new DescribedAuthenticationInstructions(server.getAuthentication())).append(nl);
		return out.toString();
	}

}
