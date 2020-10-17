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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.osgi.service.environment.EnvironmentInfo;
import org.eclipse.passage.lic.emf.ecore.LicensingEcore;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.io.FileNameFromLicensedProduct;
import org.eclipse.passage.lic.internal.base.io.PassageFileExtension;
import org.eclipse.passage.lic.internal.base.io.UserHomeProductResidence;
import org.eclipse.passage.lic.internal.licenses.model.AssignGrantIdentifiers;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.api.UserLicense;
import org.eclipse.passage.lic.users.model.meta.UsersFactory;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;
import org.eclipse.passage.loc.internal.api.CodecSupplier;
import org.eclipse.passage.loc.internal.api.IssuedLicense;
import org.eclipse.passage.loc.internal.api.PersonalLicenseRequest;
import org.eclipse.passage.loc.internal.api.OperatorEvents;
import org.eclipse.passage.loc.internal.api.OperatorLicenseEvents;
import org.eclipse.passage.loc.internal.api.OperatorLicenseService;
import org.eclipse.passage.loc.internal.api.OperatorProductService;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistry;
import org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages;
import org.eclipse.passage.loc.internal.licenses.trouble.code.LicenseIssuingFailed;
import org.eclipse.passage.loc.internal.licenses.trouble.code.LicenseIssuingIsPartial;
import org.eclipse.passage.loc.internal.licenses.trouble.code.LicenseValidationFailed;
import org.eclipse.passage.loc.internal.products.ProductRegistry;
import org.eclipse.passage.loc.internal.users.UserRegistry;
import org.eclipse.passage.loc.internal.users.UserRegistryEvents;
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
	public ServiceInvocationResult<IssuedLicense> issueLicensePack(PersonalLicenseRequest request,
			LicensePackDescriptor template) {
		Objects.requireNonNull("LicenseOperatorServiceImpl::issueLicensePack: cannot issue license for null request"); //$NON-NLS-1$
		LicensePack pack = null;
		if (template instanceof LicensePack) {
			pack = (LicensePack) template;
		} else {
			pack = createLicensePack(request);
		}
		LicensePack license = EcoreUtil.copy(pack);
		String errors = LicensingEcore.extractValidationError(license);
		if (errors != null) {
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseValidationFailed(), errors));
		}
		LicensedProduct product = new BaseLicensedProduct(license.getProductIdentifier(), license.getProductVersion());

		Date issueDate = new Date();
		license.setIdentifier(UUID.randomUUID().toString());
		license.setIssueDate(issueDate);
		new AssignGrantIdentifiers().accept(license);
		String userIdentifier = template.getUserIdentifier();
		UserDescriptor userDescriptor = userRegistry.getUser(userIdentifier);
		Map<String, Object> attachments = new HashMap<String, Object>();
		UserLicense userLicense = null;
		if (userDescriptor instanceof User) {
			User user = (User) userDescriptor;
			String conditionType = userDescriptor.getPreferredConditionType();
			String expression = userDescriptor.getPreferredConditionExpression();
			userLicense = UsersFactory.eINSTANCE.createUserLicense();
			userLicense.setPackIdentifier(license.getIdentifier());
			userLicense.setIssueDate(issueDate);
			userLicense.setPlanIdentifier(request.plan());
			userLicense.setValidFrom(request.validFrom());
			userLicense.setValidUntil(request.validUntil());
			userLicense.setConditionExpression(expression);
			userLicense.setConditionType(conditionType);
			userLicense.setProductIdentifier(product.identifier());
			userLicense.setProductVersion(product.version());
			if (userRegistry instanceof IEditingDomainProvider) {
				IEditingDomainProvider edp = (IEditingDomainProvider) userRegistry;
				EditingDomain editingDomain = edp.getEditingDomain();
				EReference structured = UsersPackage.eINSTANCE.getUser_UserLicenses();
				CommandStack stack = editingDomain.getCommandStack();
				stack.execute(AddCommand.create(editingDomain, user, structured, userLicense));
			} else {
				user.getUserLicenses().add(userLicense);
			}
			eventAdmin.postEvent(OperatorEvents.create(UserRegistryEvents.USER_LICENSE_CREATE, userLicense));
			try {
				// FIXME: define parameters
				user.eResource().save(null);
				attachments.put(userLicense.eClass().getName(), userLicense);
			} catch (IOException e) {
				return new BaseServiceInvocationResult<>(new Trouble(new LicenseIssuingFailed(),
						LicensesCoreMessages.LicenseOperatorServiceImpl_error_io, e));
			}
		}
		Path path = new UserHomeProductResidence(product).get();
		Path decrypted = path.resolve(license.getIdentifier() + new PassageFileExtension.LicenseDecrypted().get());
		URI uri = URI.createFileURI(decrypted.toString());
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.createResource(uri);
		resource.getContents().add(license);
		try {
			resource.save(null);
			eventAdmin.postEvent(OperatorLicenseEvents.decodedIssued(decrypted.toString()));
		} catch (IOException e) {
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseIssuingFailed(),
					LicensesCoreMessages.LicenseOperatorServiceImpl_failed_to_save_decoded, e));
		}

		Optional<StreamCodec> codec = codec(product);
		if (!codec.isPresent()) {
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseIssuingIsPartial(), //
					String.format(LicensesCoreMessages.LicenseOperatorServiceImpl_w_no_encoding, decrypted)));
		}

		Path privateKeyPath = path.resolve(//
				new FileNameFromLicensedProduct(product, new PassageFileExtension.PrivateKey()).get());
		File privateProductToken = privateKeyPath.toFile();
		if (!privateProductToken.exists()) {
			String pattern = LicensesCoreMessages.LicenseOperatorServiceImpl_private_key_not_found;
			String message = String.format(pattern, privateProductToken.getAbsolutePath());
			return new BaseServiceInvocationResult<>(new Trouble(new LicenseIssuingFailed(), message));
		}

		Path encrypted = path.resolve(license.getIdentifier() + new PassageFileExtension.LicenseEncrypted().get());
		File licenseEncoded = encrypted.toFile();
		try (FileInputStream licenseInput = new FileInputStream(decrypted.toFile());
				FileOutputStream licenseOutput = new FileOutputStream(licenseEncoded);
				FileInputStream keyStream = new FileInputStream(privateProductToken)) {
			String username = product.identifier();
			ProductVersionDescriptor pvd = productRegistry.getProductVersion(product.identifier(), product.version());
			String password = operatorProductService.createPassword(pvd);
			codec.get().encode(licenseInput, licenseOutput, keyStream, username, password);
			eventAdmin.postEvent(OperatorLicenseEvents.encodedIssued(encrypted.toString()));
			attachments.put(LicensesPackage.eNAME, encrypted);
			return new BaseServiceInvocationResult<>(new BaseIssuedLicense(userLicense, encrypted, decrypted));
		} catch (Exception e) {
			return new BaseServiceInvocationResult<>(//
					new Trouble(//
							new LicenseIssuingFailed(), //
							LicensesCoreMessages.LicenseOperatorServiceImpl_export_error, e));
		}
	}

	private Optional<StreamCodec> codec(LicensedProduct product) {
		return new CodecSupplier(product).get();
	}

	@Override
	public LicensePack createLicensePack(PersonalLicenseRequest request) {
		LicensesFactory licenseFactory = LicensesFactory.eINSTANCE;
		LicensePack licensePack = licenseFactory.createLicensePack();
		if (request == null) {
			return licensePack;
		}
		licensePack.setRequestIdentifier(request.identifier());
		licensePack.setUserIdentifier(request.user());
		licensePack.setUserFullName(request.userFullName());
		licensePack.setProductIdentifier(request.productIdentifier());
		licensePack.setProductVersion(request.productVersion());
		String planIdentifier = request.plan();
		licensePack.setPlanIdentifier(planIdentifier);
		LicensePlanDescriptor licensePlan = licenseRegistry.getLicensePlan(planIdentifier);
		if (licensePlan == null) {
			return licensePack;
		}
		Iterable<? extends LicensePlanFeatureDescriptor> features = licensePlan.getLicensePlanFeatures();
		Date from = request.validFrom();
		Date until = request.validUntil();
		String conditionType = request.conditionType();
		String expression = request.conditionExpression();
		EList<LicenseGrant> grants = licensePack.getLicenseGrants();
		for (LicensePlanFeatureDescriptor planFeature : features) {
			LicenseGrant grant = createLicenseGrant(planFeature, from, until, conditionType, expression);
			grants.add(grant);
		}
		return licensePack;
	}

	private LicenseGrant createLicenseGrant(LicensePlanFeatureDescriptor planFeature, Date from, Date until,
			String conditionType, String expression) {
		LicensesFactory licenseFactory = LicensesFactory.eINSTANCE;
		LicenseGrant grant = licenseFactory.createLicenseGrant();
		grant.setFeatureIdentifier(planFeature.getFeatureIdentifier());
		grant.setMatchVersion(planFeature.getMatchVersion());
		grant.setMatchRule(planFeature.getMatchRule());
		grant.setCapacity(1);
		grant.setConditionExpression(expression);
		grant.setConditionType(conditionType);
		grant.setValidFrom(from);
		grant.setValidUntil(until);
		return grant;
	}

}
