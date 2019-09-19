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
package org.eclipse.passage.lic.mail.core;

import org.eclipse.passage.lic.equinox.LicensingEquinox;
import org.eclipse.passage.lic.internal.mail.LicensingMailDescriptorImpl;
import org.eclipse.passage.lic.net.LicensingMail;
import org.eclipse.passage.lic.net.LicensingMailDescriptor;

public class LicensingMails {
	private LicensingMails() {
	}

	public static LicensingMailDescriptor getMailDescriptor(String to, String from, String subject, String body,
			String attachment) {
		return new LicensingMailDescriptorImpl(to, from, subject, body, attachment);
	}

	public static LicensingMail getLicensingEmlService() {
		return LicensingEquinox.getLicensingService(LicensingMail.class);
	}
}
