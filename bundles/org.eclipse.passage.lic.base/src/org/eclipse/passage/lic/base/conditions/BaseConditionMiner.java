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
import org.eclipse.passage.lic.base.LicensingPaths;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.runtime.ConditionMiner;
import org.eclipse.passage.lic.runtime.LicensingCondition;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.LicensingException;
import org.eclipse.passage.lic.runtime.io.KeyKeeper;
import org.eclipse.passage.lic.runtime.io.LicensingConditionTransport;
import org.eclipse.passage.lic.runtime.io.StreamCodec;

public abstract class BaseConditionMiner implements ConditionMiner {

	protected void mineDecrypted(LicensingConditionTransport transport, Path configurationPath,
			List<LicensingCondition> mined) throws LicensingException {
		List<Path> licenseFiles = collectPacks(configurationPath, LicensingPaths.EXTENSION_LICENSE_DECRYPTED);
		for (Path path : licenseFiles) {
			try (FileInputStream raw = new FileInputStream(path.toFile())) {
				Iterable<LicensingCondition> extracted = transport.readConditionDescriptors(raw);
				for (LicensingCondition condition : extracted) {
					mined.add(condition);
				}
			} catch (Exception e) {
				String message = String.format("Failed to mine packs at %s", configurationPath);
				String source = LicensingConditions.class.getName();
				BaseLicensingResult error = LicensingResults.createError(message, source, e);
				throw new LicensingException(error);
			}
		}
	}

	protected void mineEncrypted(LicensingConditionTransport transport, LicensingConfiguration configuration,
			Path configurationPath, StreamCodec streamCodec, KeyKeeper keyKeeper, List<LicensingCondition> mined)
			throws LicensingException {
		List<Path> licenseFiles = collectPacks(configurationPath, LicensingPaths.EXTENSION_LICENSE_ENCRYPTED);
		for (Path path : licenseFiles) {
			try (FileInputStream encoded = new FileInputStream(path.toFile());
					ByteArrayOutputStream decoded = new ByteArrayOutputStream();
					InputStream keyRing = keyKeeper.openKeyStream(configuration)) {
				streamCodec.decodeStream(encoded, decoded, keyRing, null);
				byte[] byteArray = decoded.toByteArray();
				try (ByteArrayInputStream input = new ByteArrayInputStream(byteArray)) {
					Iterable<LicensingCondition> extracted = transport.readConditionDescriptors(input);
					for (LicensingCondition condition : extracted) {
						mined.add(condition);
					}
				} catch (Exception e) {
					String message = String.format("Failed to to extract conditions from %s", path);
					String source = LicensingConditions.class.getName();
					BaseLicensingResult error = LicensingResults.createError(message, source, e);
					throw new LicensingException(error);
				}
			} catch (Exception e) {
				String message = String.format("Failed to mine packs at %s", configurationPath);
				String source = LicensingConditions.class.getName();
				BaseLicensingResult error = LicensingResults.createError(message, source, e);
				throw new LicensingException(error);
			}
		}
	}

	protected List<Path> collectPacks(Path configurationPath, String extension) throws LicensingException {
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
			String source = LicensingConditions.class.getName();
			BaseLicensingResult error = LicensingResults.createError(message, source, e);
			throw new LicensingException(error);
		}
		return licenseFiles;
	}

}
