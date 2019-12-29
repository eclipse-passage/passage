/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.report.internal.core;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserLicenseDescriptor;
import org.eclipse.passage.lic.users.registry.UserRegistry;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * <p>
 * OSGi {@code component} implementation for the {@linkplain CustomerStorage}
 * interface.
 * </p>
 * 
 * <p>
 * {@linkplain UserRegistry} injection is intended to be done by OSGi.
 * </p>
 * 
 * @since 0.1
 */
@Component
public final class Customers implements CustomerStorage {

	private UserRegistry registry;

	@Override
	public Set<UserDescriptor> forProducts(Set<String> products) {
		return StreamSupport.stream(registry.getUserLicenses().spliterator(), false)//
				.filter(lic -> products.contains(lic.getProductIdentifier())) //
				.map(UserLicenseDescriptor::getUser) //
				.collect(Collectors.toSet());
	}

	@Override
	@Reference
	public void installUserRegistry(UserRegistry userRegistry) {
		this.registry = userRegistry;
	}

}
