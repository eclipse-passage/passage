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
package org.eclipse.passage.lic.integration.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.passage.lic.bc.BcStreamCodec;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.runtime.io.StreamCodec;

public class LocOfflineEmulator {
	
	private LocOfflineEmulator() {
		//block
	}
	
	private static StreamCodec streamCodec = new BcStreamCodec();

	static void encodeLicense(LicensePack license, File publicFile, File privateFile, File licFile, File licenFile)
			throws IOException, FileNotFoundException {
		String publicKeyPath = publicFile.getPath();
		String privateKeyPath = privateFile.getPath();
		String username = "user"; //$NON-NLS-1$
		String password = "password"; //$NON-NLS-1$
	
		streamCodec.createKeyPair(publicKeyPath, privateKeyPath, username, password);
	
		try (FileInputStream open = new FileInputStream(licFile);
				FileOutputStream encoded = new FileOutputStream(licenFile);
				FileInputStream key = new FileInputStream(privateFile)) {
			streamCodec.encodeStream(open, encoded, key, username, password);
		}
	}

	static void storeLicense(LicensePack license, File licFile) throws IOException, FileNotFoundException {
		try (FileOutputStream fos = new FileOutputStream(licFile)) {
			Resource resource = new XMIResourceImpl();
			resource.getContents().add(license);
			resource.save(fos, new HashMap<>());
		}
	}

}
