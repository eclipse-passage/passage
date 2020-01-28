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
package org.eclipse.passage.lic.internal.mail;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.passage.lic.email.EmailDescriptor;

/**
 * The e-mail descriptor implementation
 *
 * @since 0.1.0
 *
 */
public class EmailDescriptorImpl implements EmailDescriptor {

	private final String to;
	private final String from;
	private final String subject;
	private final String body;
	private final List<String> attachmentPaths = new ArrayList<>();

	public EmailDescriptorImpl(String to, String from, String subject, String body, Iterable<String> attachments) {
		super();
		this.to = to;
		this.from = from;
		this.subject = subject;
		this.body = body;
		attachments.forEach(this.attachmentPaths::add);
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
	public Iterable<String> getAttachmentPaths() {
		return attachmentPaths;
	}
}
