/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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
package org.eclipse.passage.lic.internal.base.access;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.passage.lic.api.acquire.GrantAcquisition;

final class Residence {

	private final Path file;
	private final Logger log = LogManager.getLogger(getClass());

	Residence(Supplier<Path> srotage) {
		this.file = srotage.get().resolve("forsaken-grants.bin"); //$NON-NLS-1$
	}

	List<GrantAcquisition> read() {
		if (notWrittenYet()) {
			return Collections.emptyList();
		}
		List<GrantAcquisition> grants = new ArrayList<>();
		safelyAndSilently(() -> rawRead(grants));
		return grants;
	}

	void write(List<GrantAcquisition> grants) {
		checkFile();
		safelyAndSilently(() -> rawWrite(grants));
	}

	private void rawWrite(List<GrantAcquisition> grants) throws Exception {
		checkFile();
		try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file.toFile()))) {
			stream.writeInt(grants.size());
			for (GrantAcquisition grant : grants) {
				stream.writeObject(grant);
			}
			stream.flush();
		}
	}

	private void rawRead(List<GrantAcquisition> grants) throws Exception {
		try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file.toFile()))) {
			int amount = stream.readInt();
			for (int i = 0; i < amount; i++) {
				grants.add((GrantAcquisition) stream.readObject());
			}
		}
	}

	private void safelyAndSilently(Operation operation) {
		String path = file.toFile().getAbsolutePath().intern();
		synchronized (path) {
			try {
				operation.run();
			} catch (Exception e) {
				log.error("Forsaken grants residence operation fails", e); //$NON-NLS-1$
			}
		}
	}

	private boolean notWrittenYet() {
		return !Files.exists(file) || !Files.isRegularFile(file);
	}

	private void checkFile() {
		if (Files.exists(file)) {
			List<String> message = new ArrayList<>();
			if (Files.isDirectory(file)) {
				message.add("directory");//$NON-NLS-1$
			}
			if (!Files.isReadable(file)) {
				message.add("not readable");//$NON-NLS-1$
			}
			if (!Files.isWritable(file)) {
				message.add("not writable");//$NON-NLS-1$
			}
			if (!message.isEmpty()) {
				log.error(String.format(
						"Forsaken grant residence will constantly fail to operate: residence file [%s] is %s", //$NON-NLS-1$
						file.toAbsolutePath(), message.stream().collect(Collectors.joining(", ")))); //$NON-NLS-1$
			}
		}
		Path owner = file.getParent();
		if (!Files.exists(owner)) {
			boolean successful = file.toFile().mkdirs();
			if (!successful) {
				log.error(String.format(
						"Forsaken grant residence will constantly fail to operate: failed to create folder structure [%s]", //$NON-NLS-1$
						file.toAbsolutePath()));
			}
		} else if (!Files.isExecutable(owner)) {
			log.error(String.format(
					"Forsaken grant residence will constantly fail to operate: folder [%s] is not executable", //$NON-NLS-1$
					owner.toAbsolutePath()));

		}
	}

	private interface Operation {
		void run() throws Exception;
	}
}
