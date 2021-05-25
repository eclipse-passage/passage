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
package org.eclipse.passage.lic.internal.api.conditions;

import java.util.function.Predicate;

public final class LicenseSignatureIsEmpty implements Predicate<LicenseSignature> {

	@Override
	public boolean test(LicenseSignature signature) {
		return signature.attributes().isEmpty() && !signature.parent().isPresent();
	}

}
