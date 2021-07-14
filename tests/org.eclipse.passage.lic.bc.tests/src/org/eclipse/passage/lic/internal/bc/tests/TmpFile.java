/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.bc.tests;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.stream.IntStream;

import org.eclipse.passage.lic.base.io.PassageFileExtension;
import org.junit.rules.TemporaryFolder;

final class TmpFile {

	private final TemporaryFolder folder;

	TmpFile(TemporaryFolder folder) {
		this.folder = folder;
	}

	/**
	 * Physically creates an empty file with demanded passage file extension
	 */
	Path keyFile(PassageFileExtension extension) throws IOException {
		return file(extension.get());
	}

	/**
	 * Physically creates an empty file with an arbitrary extension
	 */
	Path file(String extension) throws IOException {
		return folder.newFile(Long.toHexString(System.nanoTime()) + extension).toPath();
	}

	/**
	 * Creates a reference for not yet existing file
	 */
	Path keyPath(PassageFileExtension extension) throws IOException {
		return folder.getRoot().toPath().resolve(Long.toHexString(System.nanoTime()) + extension.get());
	}

	Path fileWithContent() throws IOException {
		Path path = file(".txt"); //$NON-NLS-1$
		try (PrintWriter writer = new PrintWriter(path.toFile())) {
			writer.println("content row 1"); //$NON-NLS-1$
			writer.println("content row 2"); //$NON-NLS-1$
			writer.println("content row 3"); //$NON-NLS-1$
		}
		return path;
	}

	Path fileWithRandomContent(int length) throws IOException {
		Path path = file(".txt"); //$NON-NLS-1$
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; //$NON-NLS-1$
		StringBuilder fast = new StringBuilder();
		IntStream.range(0, length)//
				.mapToObj(i -> Character.toString(alphabet.charAt((int) (Math.random() * (alphabet.length() - 1)))))//
				.forEach(fast::append);
		try (PrintWriter writer = new PrintWriter(path.toFile())) {
			writer.println(fast.toString());
		}
		return path;
	}
}
