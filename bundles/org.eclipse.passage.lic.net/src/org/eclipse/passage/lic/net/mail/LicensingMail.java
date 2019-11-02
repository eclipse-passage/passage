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
package org.eclipse.passage.lic.net.mail;

import java.io.OutputStream;
import java.util.function.Consumer;

import org.eclipse.core.runtime.IStatus;

/**
 * EMail-related functionality.
 *
 * @since 0.6
 */

public interface LicensingMail {

	public void emlToOutputStream(LicensingMailDescriptor descriptor, OutputStream output, Consumer<IStatus> statusHandler);

	public LicensingMailDescriptor getMailDescriptor(String to, String from, String subject, String body,
			Iterable<String> attachments);
}
