/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.osgi.service.environment.EnvironmentInfo;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.loc.internal.agreements.AgreementRegistry;
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

@Component
public class LicenseOperatorServiceImpl implements OperatorLicenseService {

	private EnvironmentInfo environmentInfo;
	private EventAdmin events;
	private ProductRegistry products;
	private UserRegistry users;
	private LicenseRegistry licenses;
	private AgreementRegistry agreements;
	private OperatorProductService operator;

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
		this.events = admin;
	}

	public void unbindEventAdmin(EventAdmin admin) {
		if (Objects.equals(this.events, admin)) {
			this.events = null;
		}
	}

	@Reference
	public void bindProductRegistry(ProductRegistry registry) {
		this.products = registry;
	}

	public void unbindProductRegistry(ProductRegistry registry) {
		if (Objects.equals(this.products, registry)) {
			this.products = null;
		}
	}

	@Reference
	public void bindLicenseRegistry(LicenseRegistry registry) {
		this.licenses = registry;
	}

	public void unbindLicenseRegistry(LicenseRegistry registry) {
		if (Objects.equals(this.licenses, registry)) {
			this.licenses = null;
		}
	}

	@Reference
	public void bindAgreementRegistry(AgreementRegistry registry) {
		this.agreements = registry;
	}

	public void unbindAgreementRegistry(AgreementRegistry registry) {
		if (Objects.equals(this.agreements, registry)) {
			this.agreements = null;
		}
	}

	@Reference
	public void bindUserRegistry(UserRegistry registry) {
		this.users = registry;
	}

	public void unbindUserRegistry(UserRegistry registry) {
		if (Objects.equals(this.users, registry)) {
			this.users = null;
		}
	}

	@Reference
	public void bindProductOperatorService(OperatorProductService productService) {
		this.operator = productService;
	}

	public void unbindProductOperatorService(OperatorProductService productService) {
		if (Objects.equals(this.operator, productService)) {
			this.operator = null;
		}
	}

	@Override
	public PersonalLicensePack createLicensePack(PersonalLicenseRequest request) {
		return new PersonalLicensePackFromRequest(request, licenses).get();
	}

	@Override
	public ServiceInvocationResult<IssuedLicense> issueLicensePack(PersonalLicenseRequest request,
			PersonalLicensePackDescriptor template) {
		Objects.requireNonNull(request,
				"LicenseOperatorServiceImpl::issueLicensePack: cannot issue license for null request"); //$NON-NLS-1$
		Supplier<PersonalLicensePack> pack = (template instanceof PersonalLicensePack) //
				? () -> PersonalLicensePack.class.cast(template)//
				: () -> createLicensePack(request);
		return new IssuePersonalLicense(licenses, agreements, products, operator, events).issue(pack);
	}

	@Override
	public FloatingLicensePack createFloatingLicensePack(FloatingLicenseRequest request,
			Optional<FloatingLicensePack> template) {
		return new FloatingLicensePackFromRequest(request, template, licenses, users).get();
	}

	@Override
	public ServiceInvocationResult<IssuedFloatingLicense> issueFloatingLicensePack(FloatingLicensePack pack,
			Collection<FloatingLicenseAccess> configs) {
		Objects.requireNonNull(pack,
				"LicenseOperatorServiceImpl::issueFloatingLicensePack: cannot issue license over no data"); //$NON-NLS-1$
		Objects.requireNonNull(configs, "LicenseOperatorServiceImpl::configs"); //$NON-NLS-1$
		return new IssueFloatingLicense(licenses, agreements, products, operator).issue(pack, configs);
	}

}
