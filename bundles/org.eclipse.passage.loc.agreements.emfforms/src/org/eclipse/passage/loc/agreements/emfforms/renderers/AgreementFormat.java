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
package org.eclipse.passage.loc.agreements.emfforms.renderers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

final class AgreementFormat {

	private final String extention;
	private final String description;
	private final String mime;

	AgreementFormat(String name, String extention, String mime) {
		this.extention = name;
		this.description = extention;
		this.mime = mime;
	}

	String extention() {
		return extention;
	}

	String description() {
		return description;
	}

	String name() {
		return '*' + extention;
	}

	String mime() {
		return mime;
	}

	static class Supported implements Supplier<List<AgreementFormat>> {
		// TODO: get rid of the code in constructor: use CashingFunction from
		// ru.arsysop.lang
		private final List<AgreementFormat> supported = Arrays.asList(//
				new AgreementFormat(".txt", "*.txt", "text/plain") //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		);

		@Override
		public List<AgreementFormat> get() {
			return supported;
		}

		public Optional<AgreementFormat> forFile(String name) {
			return supported.stream()//
					.filter(format -> name.endsWith(format.extention))//
					.findAny();
		}

	}
}
