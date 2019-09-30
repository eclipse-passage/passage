<<<<<<< HEAD
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

public class LicensingMailDescriptorImpl implements LicensingMailDescriptor {
	private String to;
	private String from;
	private String subject;
	private String body;
	private String attachment;

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
=======
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

public class LicensingMailDescriptorImpl implements LicensingMailDescriptor {
	private String to;
	private String from;
	private String subject;
	private String body;
	private String attachment;

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
>>>>>>> 9982586... Bug 550956 - [Passage] Support mail attachment
