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

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;<<<<<<<HEAD
import java.util.function.Consumer;=======>>>>>>>9e33590...Bug 550956-[Passage]Support mail attachment

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

<<<<<<<HEAD=======
import org.eclipse.core.runtime.CoreException;>>>>>>>9e33590...Bug 550956-[Passage]Support mail attachment
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.passage.lic.net.mail.LicensingMail;
import org.eclipse.passage.lic.net.mail.LicensingMailDescriptor;<<<<<<<HEAD=======

>>>>>>>9e33590...Bug 550956-[Passage]Support mail attachment
import org.osgi.service.component.annotations.Component;

/**
 * The Licensing mail service implementation
 * 
 * @since 0.5.0
 *
 */
@Component
public class LicensingMailImpl implements LicensingMail {

	public static final String BUNDLE_ID = "org.eclipse.passage.lic.mail"; //$NON-NLS-1$

	@Override
	public IStatus createEml(LicensingMailDescriptor descriptor, OutputStream output) throws CoreException {
		try {
			createEmlFile(descriptor.getTo(), descriptor.getFrom(), descriptor.getSubject(), descriptor.getBody(),
					descriptor.getAttachment(), output);
		} catch (MessagingException | IOException e) {
			IStatus status = new Status(IStatus.ERROR, BUNDLE_ID, e.getMessage(), e);
			throw new CoreException(status);
		}
		return Status.OK_STATUS;
	}

	private void createEmlFile(String to, String from, String subject, String body, String attacheFileName,
			OutputStream output) throws MessagingException, IOException {
		File attache = new File(attacheFileName);
		Message message = new MimeMessage(Session.getInstance(System.getProperties()));
		message.setFrom(new InternetAddress(from));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		message.setSubject(subject);
		MimeBodyPart content = new MimeBodyPart();

		content.setText(body);
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(content);

		MimeBodyPart attachment = new MimeBodyPart();
		DataSource source = new FileDataSource(attache);
		attachment.setDataHandler(new DataHandler(source));
		attachment.setFileName(attache.getName());
		multipart.addBodyPart(attachment);
		message.setContent(multipart);
		message.writeTo(output);
	}

	@Override
	public LicensingMailDescriptor getMailDescriptor(String to, String from, String subject, String body,
			String attachment) {
		return new LicensingMailDescriptorImpl(to, from, subject, body, attachment);
	}
}
