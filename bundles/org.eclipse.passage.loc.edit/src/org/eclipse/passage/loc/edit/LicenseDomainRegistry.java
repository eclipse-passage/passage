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
package org.eclipse.passage.loc.edit;

import org.eclipse.passage.lic.emf.edit.EditingDomainRegistry;
import org.eclipse.passage.lic.model.api.LicensePack;
import org.eclipse.passage.lic.registry.DescriptorRegistry;
import org.eclipse.passage.lic.registry.LicenseRegistry;

public interface LicenseDomainRegistry extends LicenseRegistry, EditingDomainRegistry, DescriptorRegistry {

	void registerLicensePack(LicensePack licensePack);

	void unregisterLicensePack(String identifier);

}
