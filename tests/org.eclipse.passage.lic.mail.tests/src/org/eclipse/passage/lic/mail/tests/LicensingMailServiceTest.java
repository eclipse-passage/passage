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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNoException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.passage.lic.net.mail.LicensingMail;
import org.eclipse.passage.lic.net.mail.LicensingMailDescriptor;
import org.eclipse.passage.lic.net.mail.api.LicensingMails;
import org.junit.After;
import org.junit.Test;

public class LicensingMailServiceTest {

	private static final String MAIL_TO = "to.user@to.com"; //$NON-NLS-1$
	private static final String MAIL_FROM = "from.user@from.com"; //$NON-NLS-1$
	private static final String MAIL_SUBJECT = "mail.subject"; //$NON-NLS-1$
	private static final String MAIL_BODY = "The body mail content"; //$NON-NLS-1$
	private static final String MAIL_ATTACHMENT = "mail.attachment"; //$NON-NLS-1$
	private static final String MAIL_FILE_OUT = "test.file"; //$NON-NLS-1$
	private static final String MAIL_ATTACHMENT_CONTENT = "Content by TimeStamp:"; //$NON-NLS-1$
	private Path fileAttachmentPath;

	@Test
	public void shouldGetLicensingMailServiceTest() {
		Optional<LicensingMail> optLicensingEmlService = LicensingMails.getLicensingEmlService();
		assertTrue(optLicensingEmlService.isPresent());
	}

	@Test
	public void shouldCreateEmlByParametersPositiveTest() {
		Optional<LicensingMail> optLicensingEmlService = LicensingMails.getLicensingEmlService();
		assertTrue(optLicensingEmlService.isPresent());
		LicensingMail licensingEmlService = optLicensingEmlService.get();
		String attachment = createAttachment();
		assertFalse(attachment.isEmpty());
		LicensingMailDescriptor mailDescriptor = licensingEmlService.getMailDescriptor(MAIL_TO, MAIL_FROM, MAIL_SUBJECT,
				MAIL_BODY, attachment);
		assertNotNull(mailDescriptor);
		try (FileOutputStream fileOutput = new FileOutputStream(MAIL_FILE_OUT)) {
			IStatus okStatus = new Status(IStatus.OK, this.getClass().getCanonicalName(), 0, "", null);
			LicensingMailStatusConsumer statusConsumer = new LicensingMailStatusConsumer(okStatus);
			licensingEmlService.emlToOutputStream(mailDescriptor, fileOutput, statusConsumer);
		} catch (IOException e) {
			assumeNoException(e);
		}
	}

	@Test
	public void shouldCreateEmlByParametersNagativeTest() {
		Optional<LicensingMail> optLicensingEmlService = LicensingMails.getLicensingEmlService();
		assertTrue(optLicensingEmlService.isPresent());
		LicensingMail licensingEmlService = optLicensingEmlService.get();
		String attachment = createAttachment();
		assertFalse(attachment.isEmpty());
		LicensingMailDescriptor mailDescriptor = licensingEmlService.getMailDescriptor("", "", "", "", attachment);
		assertNotNull(mailDescriptor);
		try (FileOutputStream fileOutput = new FileOutputStream(MAIL_FILE_OUT)) {
			IStatus errorStatus = new Status(IStatus.ERROR, this.getClass().getCanonicalName(), 1, "", null);
			LicensingMailStatusConsumer statusConsumer = new LicensingMailStatusConsumer(errorStatus);
			licensingEmlService.emlToOutputStream(mailDescriptor, fileOutput, statusConsumer);
		} catch (IOException e) {
			assumeNoException(e);
		}
	}

	@Test
	public void shouldFailWithNullAttachmentTest() {
		Optional<LicensingMail> optLicensingEmlService = LicensingMails.getLicensingEmlService();
		assertTrue(optLicensingEmlService.isPresent());
		LicensingMail licensingEmlService = optLicensingEmlService.get();
		LicensingMailDescriptor mailDescriptor = licensingEmlService.getMailDescriptor("", "", "", "", null);
		assertNotNull(mailDescriptor);
		try (FileOutputStream fileOutput = new FileOutputStream(MAIL_FILE_OUT)) {
			IStatus errorStatus = new Status(IStatus.ERROR, this.getClass().getCanonicalName(), 1, "", null);
			LicensingMailStatusConsumer statusConsumer = new LicensingMailStatusConsumer(errorStatus);
			licensingEmlService.emlToOutputStream(mailDescriptor, fileOutput, statusConsumer);
		} catch (IOException e) {
			assumeNoException(e);
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
	}

	class LicensingMailStatusConsumer implements Consumer<IStatus> {
		private final IStatus expectedResult;

		public LicensingMailStatusConsumer(IStatus status) {
			expectedResult = status;
		}

		@Override
		public void accept(IStatus t) {
			assertTrue(expectedResult.getSeverity() == t.getSeverity());
		}
	}
}
