/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.loc.internal.products.core;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.eclipse.passage.loc.internal.products.core.i18n.ConverterMessages;

public final class ConvertKeysReport {

	private final List<Record> records;

	ConvertKeysReport(List<Record> records) {
		this.records = records;
	}

	ConvertKeysReport(Record record) {
		this(Collections.singletonList(record));
	}

	public List<Record> records() {
		return records;
	}

	public static abstract class Record {

		private final Path directory;
		private final String message;

		protected Record(Path directory, String message) {
			this.directory = directory;
			this.message = message;
		}

		public final Path origin() {
			return directory;
		}

		public final String message() {
			return message;
		}

	}

	static final class ErrorOnScan extends Record {

		ErrorOnScan(Path directory, Throwable thro) {
			super(directory, String.format(//
					ConverterMessages.ConvertKeysReport_e_scan, //
					thro.getClass().getName(), //
					thro.getMessage()));
		}

	}

	static final class NoProduct extends Record {

		NoProduct(Path pub) {
			super(pub.getParent(), String.format(//
					ConverterMessages.ConvertKeysReport_e_product, //
					pub));
		}

	}

	static final class ScrNotFound extends Record {

		ScrNotFound(Path pub) {
			super(pub.getParent(), String.format(//
					ConverterMessages.ConvertKeysReport_e_pair, //
					pub.getFileName()));
		}

	}

	static final class ErrorOnKeyReading extends Record {

		ErrorOnKeyReading(Path directory, String key, Throwable thro) {
			super(directory, String.format(//
					ConverterMessages.ConvertKeysReport_e_reading, //
					key, //
					thro.getClass().getName(), //
					thro.getMessage()));
		}

	}

	static final class ErrorOnKeyStoring extends Record {

		ErrorOnKeyStoring(Path directory, String key, Throwable thro) {
			super(directory, String.format(//
					ConverterMessages.ConvertKeysReport_e_storing, //
					key, //
					thro.getClass().getName(), //
					thro.getMessage()));
		}

	}

	static final class Success extends Record {

		Success(Path origin, String name, Optional<String> locator) {
			super(origin, String.format(//
					ConverterMessages.ConvertKeysReport_success, //
					name, //
					locator.orElse(ConverterMessages.ConvertKeysReport_no_locator)));
		}

	}
}
