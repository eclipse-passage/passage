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

import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.internal.equinox.i18n.EquinoxMessages;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * @since 2.1
 */
public final class LicensedApplication {

	public LicensedProduct product() throws LicensingException {
		BundleContext context = context();
		ServiceReference<IApplicationContext> reference = reference(context);
		LicensedProduct product = product(context, reference);
		context.ungetService(reference);
		return product;
	}

	private BundleContext context() throws LicensingException {
		Optional<BundleContext> context = Optional.ofNullable(FrameworkUtil.getBundle(getClass()).getBundleContext());
		if (!context.isPresent()) {
			throw new LicensingException(EquinoxMessages.LicensedApplication_no_bundle_context);
		}
		return context.get();
	}

	private ServiceReference<IApplicationContext> reference(BundleContext context) throws LicensingException {
		Optional<ServiceReference<IApplicationContext>> reference = //
				Optional.ofNullable(context.getServiceReference(IApplicationContext.class));
		if (!reference.isPresent()) {
			throw new LicensingException(EquinoxMessages.LicensedApplication_no_application_context_service_ref);
		}
		return reference.get();
	}

	private LicensedProduct product(BundleContext context, ServiceReference<IApplicationContext> reference)
			throws LicensingException {
		Optional<IApplicationContext> service = Optional.ofNullable(context.getService(reference));
		if (!service.isPresent()) {
			throw new LicensingException(EquinoxMessages.LicensedApplication_application_context_service_unregistered);
		}
		return new LicensedProductFromContext(service.get()).get();
	}

}
