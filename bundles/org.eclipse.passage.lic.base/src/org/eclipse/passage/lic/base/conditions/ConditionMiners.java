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

import org.eclipse.passage.lic.base.BaseLicensingResult;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.base.io.LicensingPaths;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.LicensingException;
import org.eclipse.passage.lic.runtime.LicensingResult;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.eclipse.passage.lic.runtime.conditions.ConditionTransport;
import org.eclipse.passage.lic.runtime.io.KeyKeeper;
import org.eclipse.passage.lic.runtime.io.StreamCodec;

public class ConditionMiners {

	public static LicensingResult mineDecrypted(ConditionTransport transport, Path configurationPath,
			List<LicensingCondition> mined, String source) {
		String task = String.format("Mining licensing conditions from %s", configurationPath);
		BaseLicensingResult holder = LicensingResults.createHolder(task, source);
		List<Path> licenseFiles;
		try {
			licenseFiles = collectPacks(configurationPath, LicensingPaths.EXTENSION_LICENSE_DECRYPTED, source);
		} catch (LicensingException e) {
			holder.addChild(e.getResult());
			return holder;
		}
		for (Path path : licenseFiles) {
			try (FileInputStream raw = new FileInputStream(path.toFile())) {
				Iterable<LicensingCondition> extracted = transport.readConditions(raw);
				for (LicensingCondition condition : extracted) {
					mined.add(condition);
				}
			} catch (Exception e) {
				String message = String.format("Failed to mine packs at %s", configurationPath);
				holder.addChild(LicensingResults.createError(message, e));
			}
		}
		return holder;
	}

	public static LicensingResult mineEncrypted(ConditionTransport transport,
			LicensingConfiguration configuration, Path configurationPath, StreamCodec streamCodec, KeyKeeper keyKeeper,
			List<LicensingCondition> mined, String source) {
		String task = String.format("Mining licensing conditions from %s", configurationPath);
		BaseLicensingResult holder = LicensingResults.createHolder(task, source);
		List<Path> licenseFiles;
		try {
			licenseFiles = collectPacks(configurationPath, LicensingPaths.EXTENSION_LICENSE_ENCRYPTED, source);
		} catch (LicensingException e) {
			holder.addChild(e.getResult());
			return holder;
		}
		for (Path path : licenseFiles) {
			try (FileInputStream encoded = new FileInputStream(path.toFile());
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
				holder.addChild(LicensingResults.createError(message, e));
			}
		}
		return holder;
	}

	public static List<Path> collectPacks(Path configurationPath, String extension, String source)
			throws LicensingException {
		List<Path> licenseFiles = new ArrayList<>();
		try {
			Files.walkFileTree(configurationPath, new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					String lowerCase = file.toString().toLowerCase();
					if (lowerCase.endsWith(extension)) {
						licenseFiles.add(file);
					}
					return FileVisitResult.CONTINUE;
				}

			});
		} catch (IOException e) {
			String message = String.format("Failed to collect packs at %s", configurationPath);
			LicensingResults.throwError(message, source, e);
		}
		return licenseFiles;
	}

}
