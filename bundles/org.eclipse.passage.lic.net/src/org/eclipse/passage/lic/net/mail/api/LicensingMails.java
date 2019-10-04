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
package org.eclipse.passage.lic.net.mail.api;

import org.eclipse.passage.lic.equinox.LicensingEquinox;
import org.eclipse.passage.lic.net.mail.LicensingMail;

/**
 * @since 0.7
 */
public class LicensingMails {

	private LicensingMails() {
	}

	public static LicensingMail getLicensingEmlService() {
		return LicensingEquinox.getLicensingService(LicensingMail.class);
	}
}
