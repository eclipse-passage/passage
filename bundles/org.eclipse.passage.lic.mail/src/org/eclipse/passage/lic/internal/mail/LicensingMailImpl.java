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
import java.io.OutputStream;
import java.util.function.Consumer;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.passage.lic.net.mail.Mailing;
import org.eclipse.passage.lic.net.mail.LicensingMailDescriptor;
import org.osgi.service.component.annotations.Component;

/**
 * The Licensing mail service implementation
 *
 * @since 0.1
 *
 */
@Component
public class LicensingMailImpl implements Mailing {

	public static final String BUNDLE_ID = "org.eclipse.passage.lic.mail"; //$NON-NLS-1$

	@Override
	public void writeEml(LicensingMailDescriptor descriptor, OutputStream output,
			Consumer<IStatus> consumerStatus) {
		try {
			Message message = createMessage(descriptor);
			fulfillMessage(descriptor, message);
			message.writeTo(output);
		} catch (MessagingException | IOException  | NullPointerException e) {
			IStatus status = new Status(IStatus.ERROR, BUNDLE_ID, e.getMessage(), e);
			consumerStatus.accept(status);
		}
	}

	private Message createMessage(LicensingMailDescriptor descriptor) throws MessagingException, AddressException {
		Message message = new MimeMessage(Session.getInstance(System.getProperties()));
		message.setFrom(new InternetAddress(descriptor.getFrom()));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(descriptor.getTo()));
		message.setSubject(descriptor.getSubject());
		return message;
	}

	private void fulfillMessage(LicensingMailDescriptor descriptor, Message message) throws MessagingException {
		Multipart multipart = createBody(descriptor.getBody());
		attachFiles(descriptor, multipart);
		message.setContent(multipart);
	}

	private Multipart createBody(String body) throws MessagingException {
		MimeBodyPart content = new MimeBodyPart();
		content.setText(body);
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(content);
		return multipart;
	}

	private void attachFiles(LicensingMailDescriptor descriptor, Multipart multipart) throws MessagingException {
		Iterable<String> attachmentPaths = descriptor.getAttachmentPaths();
		for (String path : attachmentPaths) {
			final File attache = new File(path);
			MimeBodyPart attachment = new MimeBodyPart();
			DataSource source = new FileDataSource(attache);
			attachment.setDataHandler(new DataHandler(source));
			attachment.setFileName(attache.getName());
			multipart.addBodyPart(attachment);
		}
	}

	@Override
	public LicensingMailDescriptor createMail(String to, String from, String subject, String body,
			Iterable<String> attachments) {
		return new LicensingMailDescriptorImpl(to, from, subject, body, attachments);
	}
}
