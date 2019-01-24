/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.lic.bc.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.passage.lic.base.LicensingPaths;
import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.bc.BcProperties;
import org.eclipse.passage.lic.bc.BcStreamCodec;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

@SuppressWarnings("restriction")
public class BcStreamCodecTest {

	/**
	 * Passed through maven-surefire-plugin configuration
	 */
	private static final String MVN_PROJECT_OUTPUT_PROPERTY = "project.build.directory"; //$NON-NLS-1$

	private static final String MVN_PROJECT_OUTPUT_VALUE = "target"; //$NON-NLS-1$

	private static final String TEST_CONTENT = "#some test content" + //$NON-NLS-1$
			"my.product=cool one" + //$NON-NLS-1$
			"my.feature.1=essential one" +//$NON-NLS-1$
			"my.feature.2=special one";//$NON-NLS-1$

	@Rule
	public TemporaryFolder keyFolder = new TemporaryFolder(new File(resolveOutputDirName()));

	public static String resolveOutputDirName() {
		String userDir = System.getProperty("user.dir"); //$NON-NLS-1$
		String defaultValue = userDir + File.separator + MVN_PROJECT_OUTPUT_VALUE;
		String outDir = System.getProperty(MVN_PROJECT_OUTPUT_PROPERTY, defaultValue);
		return outDir;
	}

	@Test
	public void testActivate() throws Exception {
		BcStreamCodec codec = new BcStreamCodec();
		assertEquals(BcProperties.KEY_ALGO_DEFAULT, codec.getKeyAlgo());
		assertEquals(BcProperties.KEY_SIZE_DEFAULT, codec.getKeySize());
		Map<String, Object> properties = new HashMap<>();
		String keyAlgo = "algo"; //$NON-NLS-1$
		Integer keySize = Integer.valueOf("512"); //$NON-NLS-1$
		properties.put(LicensingProperties.LICENSING_SECURITY_KEY_ALGO, keyAlgo);
		properties.put(LicensingProperties.LICENSING_SECURITY_KEY_SIZE, keySize);
		codec.activate(properties);
		assertEquals(keyAlgo, codec.getKeyAlgo());
		assertEquals(keySize.intValue(), codec.getKeySize());
	}

	@Test
	public void testCodecPositive() throws Exception {
		BcStreamCodec codec = new BcStreamCodec();
		String keyName = "key"; ////$NON-NLS-1$
		File publicFile = keyFolder.newFile(keyName + LicensingPaths.EXTENSION_PRODUCT_PUBLIC);
		String publicKey = publicFile.getPath();
		File privateFile = keyFolder.newFile(keyName + ".scr"); //$NON-NLS-1$
		String privateKey = privateFile.getPath();
		String username = "user"; //$NON-NLS-1$
		String password = "password"; //$NON-NLS-1$
		codec.createKeyPair(publicKey, privateKey, username, password);
		assertTrue(publicFile.exists());
		assertTrue(privateFile.exists());

		byte[] source = TEST_CONTENT.getBytes();
		ByteArrayInputStream openInput = new ByteArrayInputStream(source);
		ByteArrayOutputStream encodedOutput = new ByteArrayOutputStream();
		try (FileInputStream key = new FileInputStream(privateFile)) {
			codec.encodeStream(openInput, encodedOutput, key, username, password);
		}
		assertTrue(privateFile.delete());

		ByteArrayInputStream encodedSource = new ByteArrayInputStream(encodedOutput.toByteArray());
		ByteArrayOutputStream openOutput = new ByteArrayOutputStream();
		try (FileInputStream key = new FileInputStream(publicFile)) {
			Object decodeKey = codec.decodeStream(encodedSource, openOutput, key, null);
			assertNotNull(decodeKey);
		}
		byte[] target = openOutput.toByteArray();
		assertArrayEquals(source, target);
		assertTrue(publicFile.delete());
	}

}
