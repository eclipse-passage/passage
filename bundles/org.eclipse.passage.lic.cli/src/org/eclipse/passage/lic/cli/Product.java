/*******************************************************************************
 * Copyright (c) 2025 ArSysOp
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

package org.eclipse.passage.lic.cli;

import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.equinox.EquinoxPassage;

@SuppressWarnings("restriction")
final class Product implements Supplier<LicensedProduct> {

	@Override
	public LicensedProduct get() {
		ServiceInvocationResult<LicensedProduct> product = new EquinoxPassage().product();
		if (!product.data().isPresent()) {
			System.err.printf("Failed to read product credentials:%s\n", //$NON-NLS-1$
					new DiagnosticExplained(product.diagnostic()).get());
		}
		return product.data().get(); // let it fail in case of incorrect configuration

	}

}
