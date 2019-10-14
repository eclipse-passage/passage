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

import org.eclipse.passage.lic.net.mail.LicensingMailDescriptor;

/**
 * The Licensing mail descriptor implementation
 *
 * @since 0.1.0
 *
 */
public class LicensingMailDescriptorImpl implements LicensingMailDescriptor {
	private final String to;
	private final String from;
	private final String subject;
	private final String body;
	private final String attachment;

	public LicensingMailDescriptorImpl(String to, String from, String subject, String body, String attachment) {
		super();
		this.to = to;
		this.from = from;
		this.subject = subject;
		this.body = body;
		this.attachment = attachment;
	}

	@Override
	public String getTo() {
		return to;
	}

	@Override
	public String getFrom() {
		return from;
	}

	@Override
	public String getSubject() {
		return subject;
	}

	@Override
	public String getBody() {
		return body;
	}

	@Override
	public String getAttachment() {
		return attachment;
	}
}
