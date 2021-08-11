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
import java.util.function.Supplier;

final class AgreementFormat {

	private final String name;
	private final String extention;
	private final String mime;

	AgreementFormat(String name, String extention, String mime) {
		this.name = name;
		this.extention = extention;
		this.mime = mime;
	}

	String name() {
		return name;
	}

	String extention() {
		return extention;
	}

	String extentionFilter() {
		return '*' + extention;
	}

	String mime() {
		return mime;
	}

	static class Supported implements Supplier<List<AgreementFormat>> {

		@Override
		public List<AgreementFormat> get() {
			return Arrays.asList(//
					new AgreementFormat(".txt", "Text file (*.txt)", "text/plain") //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
			);
		}

	}
}
