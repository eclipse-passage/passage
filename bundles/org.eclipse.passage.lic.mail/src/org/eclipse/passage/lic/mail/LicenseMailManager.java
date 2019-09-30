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
package org.eclipse.passage.lic.mail;

import org.eclipse.passage.lic.api.mail.LicenseMailBuilder;

/**
 * The Licensing mail manager interface
 * 
 * @since 0.5.0
 *
 */
public interface LicenseMailManager {

	public LicenseMailBuilder getLicenseMailBuilder();
}
