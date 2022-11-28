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
package org.eclipse.passage.lic.licenses.model.described;

import java.util.function.Supplier;

import org.eclipse.passage.lic.licenses.ValidityPeriodClosedDescriptor;
import org.eclipse.passage.lic.licenses.ValidityPeriodDescriptor;

public final class DescribedValidityPeriod implements Supplier<String> {

	private final ValidityPeriodDescriptor valid;

	public DescribedValidityPeriod(ValidityPeriodDescriptor valid) {
		this.valid = valid;
	}

	@Override
	public String get() {
		if (valid instanceof ValidityPeriodClosedDescriptor) {
			return new DescribedValidityPeriodClosed(valid).get();
		}
		throw new UnsupportedOperationException("Type of validity period is not supported"); //$NON-NLS-1$
	}

}
