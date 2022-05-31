/*******************************************************************************
 * Copyright (c) 2020, 2022 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     Hannes Wellmann (IILS mbH) - Simplify IO operations(#1071)
 *******************************************************************************/
package org.eclipse.passage.lic.base.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.internal.base.i18n.BaseMessages;

/**
 * <p>
 * Traverses all the files down to the given {@code base} directory looking for
 * <i>settings</i> files. These ones are read as property-files one by one and
 * merged into the final map of properties, until {@code enough} predicate (if
 * any) says these is no need to proceed.
 * </p>
 * 
 * @see PassageFileExtension.Settings
 * @since 2.1
 */
public final class Settings {

	private final Supplier<Path> base;
	private final Predicate<Map<String, Object>> enough;

	/**
	 * Tell me where to start searching and when to stop
	 */
	public Settings(Supplier<Path> base, Predicate<Map<String, Object>> enough) {
		this.base = base;
		this.enough = enough;
	}

	/**
	 * Load all settings files located under the given {@code base} directory
	 */
	public Settings(Supplier<Path> base) {
		this(base, map -> false);
	}

	public Map<String, Object> get() throws LicensingException {
		try {
			return properties(settingFiles());
		} catch (IOException e) {
			throw new LicensingException(//
					String.format(BaseMessages.getString("Settings.error_on_reading_settings"), base.get()), //$NON-NLS-1$
					e);
		}
	}

	private Collection<Path> settingFiles() throws LicensingException {
		return new FileCollection(base, new PassageFileExtension.Settings()).get();
	}

	private Map<String, Object> properties(Collection<Path> files) throws IOException {
		Map<String, Object> properties = new HashMap<>();
		for (Path file : files) {
			loadAndAdd(properties, file);
			if (enough.test(properties)) {
				return properties;
			}
		}
		return properties;
	}

	private void loadAndAdd(Map<String, Object> properties, Path file) throws IOException {
		Properties heap = new Properties();
		try (InputStream stream = Files.newInputStream(file)) {
			heap.load(stream);
		}
		heap.forEach((k, v) -> properties.put(k.toString(), v));
	}
}
