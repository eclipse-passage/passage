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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.passage.lic.mail.core.LicensingMails;
import org.eclipse.passage.lic.net.LicensingMail;
import org.eclipse.passage.lic.net.LicensingMailDescriptor;
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

	private Path filePath;

	@Test
	public void testCreateEmlByParametersPositive() {
		LicensingMail licensingEmlService = LicensingMails.getLicensingEmlService();
		assertTrue(licensingEmlService != null);
		String attachment = createAttachment();
		assertFalse(attachment.isEmpty());
		LicensingMailDescriptor mailDescriptor = LicensingMails.getMailDescriptor(MAIL_TO, MAIL_FROM, MAIL_SUBJECT,
				MAIL_BODY, attachment);
		assertTrue(mailDescriptor != null);

		try (FileOutputStream fileOutput = new FileOutputStream(MAIL_FILE_OUT)) {
			IStatus createEml = licensingEmlService.createEml(mailDescriptor, fileOutput);
			assertTrue(createEml.isOK());
		} catch (IOException | CoreException e) {
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
			filePath = Files.write(createFile, content, StandardCharsets.UTF_8, StandardOpenOption.WRITE);
			return filePath.toString();
		} catch (IOException e) {
			assumeNoException(e);
		}
		return "";
	}

	@After
	public void removeAttachment() throws IOException {
		if (filePath != null) {
			Files.delete(filePath);
		}
	}
}
