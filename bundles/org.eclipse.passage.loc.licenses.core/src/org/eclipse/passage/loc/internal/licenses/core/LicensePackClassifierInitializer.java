/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.internal.licenses.core;

import org.eclipse.passage.lic.emf.edit.ClassifierInitializer;
import org.eclipse.passage.lic.emf.edit.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.licenses.core.Licenses;
import org.osgi.service.component.annotations.Component;

@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + Licenses.DOMAIN_NAME })
public final class LicensePackClassifierInitializer implements ClassifierInitializer {
	@Override
	public String newObjectIdentifier() {
		return "new.license.pack"; //$NON-NLS-1$
	}

	@Override
	public String newObjectName() {
		return CoreMessages.LicensePackClassifierInitializer_new_lic_pack_name;
	}

	@Override
	public String newFileName() {
		return "new_license_pack"; //$NON-NLS-1$
	}

	@Override
	public String newObjectTitle() {
		return CoreMessages.LicensePackClassifierInitializer_lic_pack_title;
	}

	@Override
	public String newObjectMessage() {
		return CoreMessages.LicensePackClassifierInitializer_new_lic_pack_message;
	}

	@Override
	public String newFileMessage() {
		return CoreMessages.LicensePackClassifierInitializer_file_name_message;
	}
}