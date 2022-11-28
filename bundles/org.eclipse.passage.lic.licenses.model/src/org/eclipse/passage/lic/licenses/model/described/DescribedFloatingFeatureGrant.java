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
import org.eclipse.passage.lic.licenses.FeatureGrantDescriptor;

public final class DescribedFloatingFeatureGrant extends Described {

	private FeatureGrantDescriptor grant;

	public DescribedFloatingFeatureGrant(FeatureGrantDescriptor grant) {
		this.grant = grant;
	}

	@Override
	public String get() {
		StringBuilder out = new StringBuilder();
		out.append(tab).append(Messages.getString("DescribedPersonalFeatureGrant_feature")) // //$NON-NLS-1$
				.append(grant.getFeature().getIdentifier()).append(nl)//

				.append(tabs).append(Messages.getString("DescribedPersonalFeatureGrant_version")) // //$NON-NLS-1$
				.append(grant.getFeature().getVersionMatch().getVersion())//
				.append(Messages.getString("DescribedPersonalFeatureGrant_rule")) // //$NON-NLS-1$
				.append(grant.getFeature().getVersionMatch().getRule())//
				.append(nl)//

				.append(tabs).append(new DescribedValidityPeriod(grant.getValid()).get()).append(nl)//

				.append(tabs).append(Messages.getString("DescribedPersonalFeatureGrant_capacity"))// //$NON-NLS-1$
				.append(grant.getCapacity()).append(nl);//
		return out.toString();
	}

}
