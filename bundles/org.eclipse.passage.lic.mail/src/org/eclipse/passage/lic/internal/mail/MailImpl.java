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

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.function.BiConsumer;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.eclipse.passage.lic.email.EmailDescriptor;
import org.eclipse.passage.lic.email.Mailing;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * The Mailing service implementation based on javax.mail
 *
 * @since 0.1
 *
 */
@Component
public class MailImpl implements Mailing {
	
	@Activate
	public void activate() {
		MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap(); 
		mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html"); 
		mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml"); 
		mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain"); 
		mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed"); 
		mc.addMailcap("message/rfc822;; x-java-content- handler=com.sun.mail.handlers.message_rfc822");
		mc.addMailcap("multipart/report;;  x-java-content-handler=com.sun.mail.dsn.multipart_report");
		mc.addMailcap("message/delivery-status;; x-java-content-handler=com.sun.mail.dsn.message_deliverystatus");
		mc.addMailcap("message/disposition-notification;; x-java-content-handler=com.sun.mail.dsn.message_dispositionnotification");
		mc.addMailcap("text/rfc822-headers;;   x-java-content-handler=com.sun.mail.dsn.text_rfc822headers");
	}

	@Override
	public void writeEml(EmailDescriptor descriptor, OutputStream output, BiConsumer<String, Throwable> consumerStatus) {
		try {
			Message message = createMessage(descriptor);
			fulfillMessage(descriptor, message);
			message.writeTo(output);
		} catch (MessagingException | IOException e) {
			consumerStatus.accept(e.getMessage(), e);
		}
	}

	private Message createMessage(EmailDescriptor descriptor) throws MessagingException, AddressException {
		Message message = new MimeMessage(Session.getInstance(System.getProperties()));
		message.setFrom(new InternetAddress(descriptor.getFrom()));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(descriptor.getTo()));
		message.setSubject(descriptor.getSubject());
		return message;
	}

	private void fulfillMessage(EmailDescriptor descriptor, Message message) throws MessagingException {
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

	private void attachFiles(EmailDescriptor descriptor, Multipart multipart) throws MessagingException {
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
	public EmailDescriptor createMail(String to, String from, String subject, String body,
			Iterable<String> attachments) {
		return new EmailDescriptorImpl(to, from, subject, body, attachments);
	}
}
