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
package org.eclipse.passage.lic.equinox;

import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.core.runtime.Platform;
import org.eclipse.passage.lic.internal.equinox.i18n.EquinoxMessages;

/**
 * @since 2.1
 */
public final class ProductContacts implements Supplier<String> {

	private final String key = "licensing.product.contacts"; //$NON-NLS-1$

	@Override
	public String get() {
		return Optional.ofNullable(Platform.getProduct())//
				.flatMap(p -> Optional.ofNullable(p.getProperty(key)))//
				.orElseGet(this::defaults);
	}

	public String defaults() {
		return EquinoxMessages.ProductContacts_defaults;
	}

}
