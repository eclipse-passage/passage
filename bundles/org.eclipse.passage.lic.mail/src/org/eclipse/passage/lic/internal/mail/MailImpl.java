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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.BiConsumer;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
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
		//it **may** work "out-of-the-box", but let's declare explicitly to know where to dig
		MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap(); 
		mc.addMailcap("text/plain;;		x-java-content-handler=org.apache.geronimo.mail.handlers.TextHandler"); 
		mc.addMailcap("text/xml;;		x-java-content-handler=org.apache.geronimo.mail.handlers.XMLHandler"); 
		mc.addMailcap("text/html;;		x-java-content-handler=org.apache.geronimo.mail.handlers.HtmlHandler"); 
		mc.addMailcap("message/rfc822;;	x-java-content-handler=org.apache.geronimo.mail.handlers.MessageHandler"); 
		mc.addMailcap("multipart/*;;		x-java-content-handler=org.apache.geronimo.mail.handlers.MultipartHandler; x-java-fallback-entry=true"); 
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

	private void fulfillMessage(EmailDescriptor descriptor, Message message) throws MessagingException, IOException {
		Multipart multipart = createBody(descriptor.getBody());
		attachFiles(descriptor, multipart);
		message.setContent(multipart);
	}

	private Multipart createBody(String body) throws MessagingException {
		Multipart multipart = new MimeMultipart("mixed"); //$NON-NLS-1$
		MimeBodyPart content = new MimeBodyPart();
		multipart.addBodyPart(content);
		content.setText(body, "UTF-8");
		return multipart;
	}

	private void attachFiles(EmailDescriptor descriptor, Multipart multipart) throws MessagingException, IOException {
		Iterable<String> attachmentPaths = descriptor.getAttachmentPaths();
		for (String path : attachmentPaths) {
			final File attache = new File(path);
			MimeBodyPart attachment = new MimeBodyPart();
			//FIXME: we need to support content types near to attachment path
			attachment.setContent(Files.readAllBytes(Paths.get(path)), "application/binary");
			attachment.addHeader("Content-Transfer-Encoding", "base64");			
			attachment.setDisposition(Part.ATTACHMENT);
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
