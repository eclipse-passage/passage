/*******************************************************************************
 * Copyright (c) 2022, 2024 ArSysOp
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
import org.eclipse.passage.lic.licenses.model.api.UserGrant;

public final class DescribedUserGrant extends Described {

	private UserGrant grant;

	public DescribedUserGrant(UserGrant grant) {
		this.grant = grant;
	}

	@Override
	public String get() {
		StringBuilder out = new StringBuilder();
		out.append(tab).append(Messages.getString("DescribedPersonalFeatureGrant_user")) //$NON-NLS-1$
				.append(grant.getUser()).append(nl)//
				.append(tabs).append(new DescribedAuthenticationInstructions(grant.getAuthentication()).get())
				.append(nl);
		return out.toString();
	}

}
