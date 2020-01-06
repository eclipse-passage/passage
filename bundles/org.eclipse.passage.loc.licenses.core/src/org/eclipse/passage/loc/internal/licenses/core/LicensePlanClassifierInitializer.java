/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.loc.internal.licenses.core;

import org.eclipse.passage.lic.emf.edit.ClassifierInitializer;
import org.eclipse.passage.lic.emf.edit.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages;
import org.eclipse.passage.loc.licenses.core.Licenses;
import org.osgi.service.component.annotations.Component;

@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + Licenses.DOMAIN_NAME })
public final class LicensePlanClassifierInitializer implements ClassifierInitializer {
	@Override
	public String newObjectIdentifier() {
		return "new.license.plan"; //$NON-NLS-1$
	}

	@Override
	public String newObjectName() {
		return LicensesCoreMessages.LicensePlanClassifierInitializer_new_lic_plan_name;
	}

	@Override
	public String newFileName() {
		return "new_license_plan"; //$NON-NLS-1$
	}

	@Override
	public String newObjectTitle() {
		return LicensesCoreMessages.LicensePlanClassifierInitializer_lic_plan_title;
	}

	@Override
	public String newObjectMessage() {
		return LicensesCoreMessages.LicensePlanClassifierInitializer_new_lic_plan_message;
	}

	@Override
	public String newFileMessage() {
		return LicensesCoreMessages.LicensePlanClassifierInitializer_file_name_message;
	}
}