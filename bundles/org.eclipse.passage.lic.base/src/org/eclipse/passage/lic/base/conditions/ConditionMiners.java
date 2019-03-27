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
package org.eclipse.passage.lic.base.conditions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.LicensingException;
import org.eclipse.passage.lic.runtime.LicensingResult;
import org.eclipse.passage.lic.runtime.conditions.ConditionTransport;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.eclipse.passage.lic.runtime.io.KeyKeeper;
import org.eclipse.passage.lic.runtime.io.StreamCodec;

public class ConditionMiners {

	public static LicensingResult mine(String source, LicensingConfiguration configuration,
			List<LicensingCondition> mined, KeyKeeper keyKeeper, StreamCodec streamCodec, ConditionTransport transport,
			Iterable<String> packs) {
		String task = String.format("Mined licensing conditions for configuration: %s (%s)",
				configuration.getProductIdentifier(), configuration.getProductVersion());
		List<LicensingResult> errors = new ArrayList<>();
		for (String path : packs) {
			try (FileInputStream encoded = new FileInputStream(path);
					ByteArrayOutputStream decoded = new ByteArrayOutputStream();
					InputStream keyRing = keyKeeper.openKeyStream(configuration)) {
				streamCodec.decodeStream(encoded, decoded, keyRing, null);
				byte[] byteArray = decoded.toByteArray();
				try (ByteArrayInputStream input = new ByteArrayInputStream(byteArray)) {
					Iterable<LicensingCondition> extracted = transport.readConditions(input);
					for (LicensingCondition condition : extracted) {
						mined.add(condition);
					}
				}
			} catch (Exception e) {
				String message = String.format("Failed to to extract conditions from %s for configuration %s", path,
						configuration);
				errors.add(LicensingResults.createError(message, source, e));
			}
		}
		if (errors.isEmpty()) {
			return LicensingResults.createOK(task, source);
		}
		return LicensingResults.createError(task, source, errors);
	}

	public static List<String> collectPacks(Path configurationPath, String... extensions) throws LicensingException {
		String message = String.format("Failed to collect packs at %s", configurationPath);
		String source = ConditionMiners.class.getName();
		if (configurationPath == null) {
			IllegalArgumentException e = new IllegalArgumentException();
			throw new LicensingException(LicensingResults.createError(message, source, e));
		}
		List<String> licenseFiles = new ArrayList<>();
		try {
			Files.walkFileTree(configurationPath, new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
					File file = path.toFile();
					String filePath = file.getPath();
					String lowerCase = filePath.toLowerCase();
					for (String extension : extensions) {
						if (lowerCase.endsWith(extension)) {
							licenseFiles.add(filePath);
						}
					}
					return FileVisitResult.CONTINUE;
				}

			});
		} catch (IOException e) {
			throw new LicensingException(LicensingResults.createError(message, source, e));
		}
		return licenseFiles;
	}

}
