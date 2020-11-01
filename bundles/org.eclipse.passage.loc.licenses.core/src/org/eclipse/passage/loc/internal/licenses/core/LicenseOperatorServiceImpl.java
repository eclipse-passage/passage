/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.loc.internal.licenses.core;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.osgi.service.environment.EnvironmentInfo;
import org.eclipse.passage.lic.floating.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.loc.internal.api.FloatingLicenseRequest;
import org.eclipse.passage.loc.internal.api.IssuedFloatingLicense;
import org.eclipse.passage.loc.internal.api.IssuedLicense;
import org.eclipse.passage.loc.internal.api.OperatorLicenseService;
import org.eclipse.passage.loc.internal.api.OperatorProductService;
import org.eclipse.passage.loc.internal.api.PersonalLicenseRequest;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistry;
import org.eclipse.passage.loc.internal.products.ProductRegistry;
import org.eclipse.passage.loc.internal.users.UserRegistry;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.EventAdmin;

@SuppressWarnings("restriction")
@Component
public class LicenseOperatorServiceImpl implements OperatorLicenseService {

	private EnvironmentInfo environmentInfo;
	private EventAdmin eventAdmin;
	private ProductRegistry productRegistry;
	private UserRegistry userRegistry;
	private LicenseRegistry licenseRegistry;
	private OperatorProductService operatorProductService;

	@Reference
	public void bindEnvironmentInfo(EnvironmentInfo environment) {
		this.environmentInfo = environment;
	}

	public void unbindEnvironmentInfo(EnvironmentInfo environment) {
		if (Objects.equals(this.environmentInfo, environment)) {
			this.environmentInfo = null;
		}
	}

	@Reference
	public void bindEventAdmin(EventAdmin admin) {
		this.eventAdmin = admin;
	}

	public void unbindEventAdmin(EventAdmin admin) {
		if (Objects.equals(this.eventAdmin, admin)) {
			this.eventAdmin = null;
		}
	}

	@Reference
	public void bindProductRegistry(ProductRegistry registry) {
		this.productRegistry = registry;
	}

	public void unbindProductRegistry(ProductRegistry registry) {
		if (Objects.equals(this.productRegistry, registry)) {
			this.productRegistry = null;
		}
	}

	@Reference
	public void bindLicenseRegistry(LicenseRegistry registry) {
		this.licenseRegistry = registry;
	}

	public void unbindLicenseRegistry(LicenseRegistry registry) {
		if (Objects.equals(this.licenseRegistry, registry)) {
			this.licenseRegistry = null;
		}
	}

	@Reference
	public void bindUserRegistry(UserRegistry registry) {
		this.userRegistry = registry;
	}

	public void unbindUserRegistry(UserRegistry registry) {
		if (Objects.equals(this.userRegistry, registry)) {
			this.userRegistry = null;
		}
	}

	@Reference
	public void bindProductOperatorService(OperatorProductService productService) {
		this.operatorProductService = productService;
	}

	public void unbindProductOperatorService(OperatorProductService productService) {
		if (Objects.equals(this.operatorProductService, productService)) {
			this.operatorProductService = null;
		}
	}

	@Override
	public LicensePack createLicensePack(PersonalLicenseRequest request) {
		return new PersonalLicensePackFromRequest(request, licenseRegistry).get();
	}

	@Override
	public ServiceInvocationResult<IssuedLicense> issueLicensePack(PersonalLicenseRequest request,
			LicensePackDescriptor template) {
		Objects.requireNonNull(request,
				"LicenseOperatorServiceImpl::issueLicensePack: cannot issue license for null request"); //$NON-NLS-1$
		Supplier<LicensePack> pack = (template instanceof LicensePack) //
				? () -> LicensePack.class.cast(template)//
				: () -> createLicensePack(request);
		return new IssuePersonalLicense(userRegistry, productRegistry, operatorProductService, eventAdmin)
				.issue(request, pack);
	}

	@Override
	public FloatingLicensePack createFloatingLicensePack(FloatingLicenseRequest request,
			Optional<FloatingLicensePack> template) {
		return new FloatingLicensePackFromRequest(request, template, licenseRegistry, userRegistry).get();
	}

	@Override
	public ServiceInvocationResult<IssuedFloatingLicense> issueFloatingLicensePack(FloatingLicenseRequest request,
			FloatingLicensePack template) {
		// TODO YTBD
		return null;
	}

}
