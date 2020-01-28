/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.lic.email;

import java.io.OutputStream;
import java.util.function.BiConsumer;

/**
 * EMail service
 *
 * @since 0.1
 */

public interface Mailing {

	public void writeEml(EmailDescriptor descriptor, OutputStream output, BiConsumer<String, Throwable> statusHandler);

	public EmailDescriptor createMail(String to, String from, String subject, String body,
			Iterable<String> attachments);
}
