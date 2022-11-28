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
import org.eclipse.passage.lic.licenses.LicenseRequisitesDescriptor;

public final class DescribedLicenseRequisites extends Described {

	private final LicenseRequisitesDescriptor license;

	public DescribedLicenseRequisites(LicenseRequisitesDescriptor license) {
		this.license = license;
	}

	@Override
	public String get() {
		StringBuilder out = new StringBuilder();
		out.append(Messages.getString("DescribedLicenseRequisites_requisites")).append(nl)// //$NON-NLS-1$
				.append(tab).append(Messages.getString("DescribedLicenseRequisites_product")) //$NON-NLS-1$
				.append(license.getProduct().getIdentifier())//
				.append(Messages.getString("DescribedLicenseRequisites_version")) //$NON-NLS-1$
				.append(license.getProduct().getVersion()).append(nl)//

				.append(tab).append(Messages.getString("DescribedLicenseRequisites_identifier")) //$NON-NLS-1$
				.append(license.getIdentifier()).append(nl)//

				.append(tab).append(new DescribedValidityPeriod(license.getValid()).get()).append(nl) //

				.append(tab).append(Messages.getString("DescribedLicenseRequisites_issued")) //$NON-NLS-1$
				.append(new DescribedDate(license.getIssueDate()).get()).append(nl)//

				.append(tab).append(Messages.getString("DescribedLicenseRequisites_plan")).append(license.getPlan()) //$NON-NLS-1$
				.append(nl)//

				.append(tab).append(license.getAgreements().size())
				.append(Messages.getString("DescribedLicenseRequisites_agreements")).append(nl); //$NON-NLS-1$
		return out.toString();
	}

}
