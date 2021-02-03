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

package org.eclipse.passage.lic.mail.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeNoException;

import java.io.File;
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

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.internet.AddressException;

import org.eclipse.passage.lic.email.EmailDescriptor;
import org.eclipse.passage.lic.email.Mailing;
import org.eclipse.passage.lic.internal.mail.MailImpl;
import org.junit.BeforeClass;
import org.junit.Test;

public class MailImplTest {

	private static final String MAIL_TO = "to.user@to.com"; //$NON-NLS-1$
	private static final String MAIL_FROM = "from.user@from.com"; //$NON-NLS-1$
	private static final String MAIL_SUBJECT = "mail.subject"; //$NON-NLS-1$
	private static final String MAIL_BODY = "The body mail content"; //$NON-NLS-1$
	private static final String MAIL_ATTACHMENT = "mail.attachment"; //$NON-NLS-1$
	private static final String MAIL_FILE_OUT = "test.file"; //$NON-NLS-1$
	private static final String MAIL_ATTACHMENT_CONTENT = "Content by TimeStamp:"; //$NON-NLS-1$

	private static String resolveOutputDirName() {
		return System.getProperty("project.build.directory", //$NON-NLS-1$
				System.getProperty("user.dir") + File.separator + "target"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@BeforeClass
	public static void initialize() {
		// need this extra setup to avoid java.lang.AssertionError: no object DCH for
		// MIME type multipart/mixed;
		MailImpl mailing = new MailImpl();
		mailing.activate();
		System.out.println("multipart/*" + ' ' //$NON-NLS-1$
				+ ((MailcapCommandMap) CommandMap.getDefaultCommandMap()).createDataContentHandler("multipart/*")); //$NON-NLS-1$
	}

	@Test
	public void shouldCreateEmlByParametersPositiveTest() {
		MailImpl mailing = new MailImpl();
		String attachment = createAttachment();
		assertFalse(attachment.isEmpty());
		EmailDescriptor mailDescriptor = mailing.createMail(MAIL_TO, MAIL_FROM, MAIL_SUBJECT, MAIL_BODY,
				Collections.singleton(attachment));
		assertNotNull(mailDescriptor);
		try (FileOutputStream fileOutput = new FileOutputStream(
				Paths.get(resolveOutputDirName(), MAIL_FILE_OUT).toFile())) {
			mailing.writeEml(mailDescriptor, fileOutput, (m, t) -> failure(m, t));
		} catch (IOException e) {
			assumeNoException(e);
		}
	}

	private void failure(String message, Throwable t) {
		t.printStackTrace();
		fail(message);
	}

	@Test
	public void shouldCreateEmlByParametersNegativeTest() {
		MailImpl mailing = new MailImpl();
		String attachment = createAttachment();
		assertFalse(attachment.isEmpty());
		EmailDescriptor mailDescriptor = mailing.createMail("", "", "", "", Collections.singleton(attachment)); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$//$NON-NLS-4$
		assertNotNull(mailDescriptor);
		try (FileOutputStream fileOutput = new FileOutputStream(
				Paths.get(resolveOutputDirName(), MAIL_FILE_OUT).toFile())) {
			mailing.writeEml(mailDescriptor, fileOutput, (m, t) -> assertEquals(AddressException.class, t.getClass()));
		} catch (IOException e) {
			assumeNoException(e);
		}
	}

	@Test
	public void shouldFailWithNullAttachmentTest() {
		Mailing mailing = new MailImpl();
		try {
			mailing.createMail("", "", "", "", null); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$//$NON-NLS-4$
			fail();
		} catch (Exception e) {
			assertEquals(NullPointerException.class, e.getClass());
		}
	}

	private String createAttachment() {
		String userDir = resolveOutputDirName();
		try {
			Path createFile = Files.createFile(Paths.get(userDir, MAIL_ATTACHMENT));
			List<String> content = new ArrayList<>();
			String attachmentContent = new String(MAIL_ATTACHMENT_CONTENT + System.currentTimeMillis());
			content.add(attachmentContent);
			return Files.write(createFile, content, StandardCharsets.UTF_8, StandardOpenOption.WRITE).toString();
		} catch (IOException e) {
			assumeNoException(e);
		}
		return ""; //$NON-NLS-1$
	}

}
