/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
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
package org.eclipse.passage.lic.internal.mail;

import java.util.Map;

import org.eclipse.passage.lic.api.mail.LicenseMailBuilder;
import org.eclipse.passage.lic.mail.LicenseMailManager;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

/**
 * The Licensing mail manager service implementation
 * 
 * @since 0.5.0
 *
 */
@Component
public class LicenseMailManagerImpl implements LicenseMailManager {

	private LicenseMailBuilder licenseMailBuilder;

	@Override
	public LicenseMailBuilder getLicenseMailBuilder() {
		return licenseMailBuilder;
	}

	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
	public void bindLicenseMailBuilder(LicenseMailBuilder mailbuilder, Map<String, Object> context) {
		this.licenseMailBuilder = mailbuilder;
	}

	public void unbindLicenseMailBuilder() {
		this.licenseMailBuilder = null;
	}
}
