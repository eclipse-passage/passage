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

import java.util.List;

import org.eclipse.passage.lic.internal.licenses.model.i18n.Messages;
import org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant;

public final class DescribedPersonalFeatureGrants extends Described {

	private final List<PersonalFeatureGrant> grants;

	public DescribedPersonalFeatureGrants(List<PersonalFeatureGrant> grants) {
		this.grants = grants;
	}

	@Override
	public String get() {
		StringBuilder out = new StringBuilder();
		out.append(grants.size()).append(Messages.getString("DescribedPersonalFeatureGrants_grants")).append(nl); //$NON-NLS-1$
		for (PersonalFeatureGrant grant : grants) {
			out.append(new DescribedPersonalFeatureGrant(grant).get());
		}
		return out.toString();
	}

}
