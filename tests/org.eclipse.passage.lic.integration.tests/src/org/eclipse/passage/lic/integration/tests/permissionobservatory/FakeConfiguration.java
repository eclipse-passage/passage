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
package org.eclipse.passage.lic.integration.tests.permissionobservatory;

/**
 * Dummy licensing configuration
 * 
 * @since 0.6
 */
class FakeConfiguration implements org.eclipse.passage.lic.api.LicensingConfiguration {

	@Override
	public String getProductIdentifier() {
		return "product";//$NON-NLS-1$
	}

	@Override
	public String getProductVersion() {
		return "1.0.0"; //$NON-NLS-1$
	}
}
