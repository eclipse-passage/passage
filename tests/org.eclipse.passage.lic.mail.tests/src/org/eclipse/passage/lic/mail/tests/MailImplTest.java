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

package org.eclipse.passage.lic.mail.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeNoException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.mail.internet.AddressException;

import org.eclipse.passage.lic.email.EmailDescriptor;
import org.eclipse.passage.lic.email.Mailing;
import org.eclipse.passage.lic.internal.mail.MailImpl;
import org.junit.After;
import org.junit.Test;

public class MailImplTest {

	private static final String MAIL_TO = "to.user@to.com"; //$NON-NLS-1$
	private static final String MAIL_FROM = "from.user@from.com"; //$NON-NLS-1$
	private static final String MAIL_SUBJECT = "mail.subject"; //$NON-NLS-1$
	private static final String MAIL_BODY = "The body mail content"; //$NON-NLS-1$
	private static final String MAIL_ATTACHMENT = "mail.attachment"; //$NON-NLS-1$
	private static final String MAIL_FILE_OUT = "test.file"; //$NON-NLS-1$
	private static final String MAIL_ATTACHMENT_CONTENT = "Content by TimeStamp:"; //$NON-NLS-1$
	private Path fileAttachmentPath;

	@Test
	public void shouldCreateEmlByParametersPositiveTest() {
		Mailing mailing = new MailImpl();
		String attachment = createAttachment();
		assertFalse(attachment.isEmpty());
		EmailDescriptor mailDescriptor = mailing.createMail(MAIL_TO, MAIL_FROM, MAIL_SUBJECT,
				MAIL_BODY, Collections.singleton(attachment));
		assertNotNull(mailDescriptor);
		try (FileOutputStream fileOutput = new FileOutputStream(MAIL_FILE_OUT)) {
			mailing.writeEml(mailDescriptor, fileOutput, (m, t) -> fail());
		} catch (IOException e) {
			assumeNoException(e);
		}
	}

	@Test
	public void shouldCreateEmlByParametersNagativeTest() {
		Mailing mailing = new MailImpl();
		String attachment = createAttachment();
		assertFalse(attachment.isEmpty());
		EmailDescriptor mailDescriptor = mailing.createMail("", "", "", "", Collections.singleton(attachment));
		assertNotNull(mailDescriptor);
		try (FileOutputStream fileOutput = new FileOutputStream(MAIL_FILE_OUT)) {
			mailing.writeEml(mailDescriptor, fileOutput, (m, t) -> assertEquals(AddressException.class, t.getClass()));
		} catch (IOException e) {
			assumeNoException(e);
		}
	}

	@Test
	public void shouldFailWithNullAttachmentTest() {
		Mailing mailing = new MailImpl();
		try {
			mailing.createMail("", "", "", "", null);
			fail();
		} catch (Exception e) {
			assertEquals(NullPointerException.class, e.getClass());
		}
	}

	private String createAttachment() {
		String userHome = System.getProperty("user.home");
		try {
			Path createFile = Files.createFile(Paths.get(userHome, MAIL_ATTACHMENT));
			List<String> content = new ArrayList<>();
			String attachmentContent = new String(MAIL_ATTACHMENT_CONTENT + System.currentTimeMillis());
			content.add(attachmentContent);
			fileAttachmentPath = Files.write(createFile, content, StandardCharsets.UTF_8, StandardOpenOption.WRITE);
			return fileAttachmentPath.toString();
		} catch (IOException e) {
			assumeNoException(e);
		}
		return "";
	}

	@After
	public void removeAttachment() throws IOException {
		if (fileAttachmentPath != null) {
			Files.delete(fileAttachmentPath);
		}
		Files.deleteIfExists(Paths.get(MAIL_FILE_OUT));
	}

}
