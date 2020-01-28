/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.integration.tests;

import org.eclipse.passage.lic.base.LicensingProperties;
import org.osgi.service.component.annotations.Component;

@Component(property = { LicensingProperties.LICENSING_FEATURE_IDENTIFIER + "=some.licensed.component",
		LicensingProperties.LICENSING_FEATURE_VERSION + '=' + "1.2.0",
		LicensingProperties.LICENSING_RESTRICTION_LEVEL + '=' + LicensingProperties.LICENSING_RESTRICTION_LEVEL_ERROR })
public class LicIntegrationComponent {

}
