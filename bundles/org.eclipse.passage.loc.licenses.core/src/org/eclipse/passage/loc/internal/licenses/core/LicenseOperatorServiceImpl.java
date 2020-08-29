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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.api.access.LicensingRequest;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.base.io.LicensingPaths;
import org.eclipse.passage.lic.emf.ecore.LicensingEcore;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.licenses.model.AssignGrantIdentifiers;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.lic.licenses.registry.LicenseRegistry;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.products.registry.ProductRegistry;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.api.UserLicense;
import org.eclipse.passage.lic.users.model.meta.UsersFactory;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;
import org.eclipse.passage.lic.users.registry.UserRegistry;
import org.eclipse.passage.lic.users.registry.UserRegistryEvents;
import org.eclipse.passage.loc.api.OperatorEvents;
import org.eclipse.passage.loc.api.OperatorLicenseEvents;
import org.eclipse.passage.loc.api.OperatorLicenseService;
import org.eclipse.passage.loc.api.OperatorProductService;
import org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.EventAdmin;

@SuppressWarnings("restriction")
@Component
public class LicenseOperatorServiceImpl implements OperatorLicenseService {

	private String pluginId;

	private EnvironmentInfo environmentInfo;
	private EventAdmin eventAdmin;
	private ProductRegistry productRegistry;
	private UserRegistry userRegistry;
	private LicenseRegistry licenseRegistry;
	private OperatorProductService operatorProductService;

	@Activate
	public void activate(BundleContext context) {
		pluginId = context.getBundle().getSymbolicName();
	}

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
	public LicensingResult issueLicensePack(LicensingRequest request, LicensePackDescriptor template) {
		if (request == null) {
			return LicensingResults.createError(
					LicensesCoreMessages.LicenseOperatorServiceImpl_status_invalid_licensing_request, pluginId);
		}
		LicensePack pack = null;
		if (template instanceof LicensePack) {
			pack = (LicensePack) template;
		} else {
			pack = createLicensePack(request);
		}
		LicensePack license = EcoreUtil.copy(pack);
		String errors = LicensingEcore.extractValidationError(license);
		if (errors != null) {
			return LicensingResults.createError(errors, pluginId);
		}
		String productIdentifier = license.getProductIdentifier();
		String productVersion = license.getProductVersion();
		Path basePath = getBasePath();
		Path path = basePath.resolve(productIdentifier).resolve(productVersion);
		String storageKeyFolder = path.toFile().getAbsolutePath();

		Date issueDate = new Date();
		license.setIdentifier(UUID.randomUUID().toString());
		license.setIssueDate(issueDate);
		new AssignGrantIdentifiers().accept(license);
		String userIdentifier = template.getUserIdentifier();
		UserDescriptor userDescriptor = userRegistry.getUser(userIdentifier);
		Map<String, Object> attachments = new HashMap<String, Object>();
		if (userDescriptor instanceof User) {
			User user = (User) userDescriptor;
			String conditionType = userDescriptor.getPreferredConditionType();
			String expression = userDescriptor.getPreferredConditionExpression();
			UserLicense userLicense = UsersFactory.eINSTANCE.createUserLicense();
			userLicense.setPackIdentifier(license.getIdentifier());
			userLicense.setIssueDate(issueDate);
			userLicense.setPlanIdentifier(request.getPlanIdentifier());
			userLicense.setValidFrom(request.getValidFrom());
			userLicense.setValidUntil(request.getValidUntil());
			userLicense.setConditionExpression(expression);
			userLicense.setConditionType(conditionType);
			userLicense.setProductIdentifier(productIdentifier);
			userLicense.setProductVersion(productVersion);
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
				return LicensingResults.createError(LicensesCoreMessages.LicenseOperatorServiceImpl_export_error,
						pluginId, e);
			}
		}

		String licenseIn = storageKeyFolder + File.separator + license.getIdentifier()
				+ LicensingPaths.EXTENSION_LICENSE_DECRYPTED;

		URI uri = URI.createFileURI(licenseIn);
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.createResource(uri);
		resource.getContents().add(license);
		try {
			resource.save(null);
			eventAdmin.postEvent(OperatorLicenseEvents.decodedIssued(licenseIn));
		} catch (IOException e) {
			return LicensingResults.createError(LicensesCoreMessages.LicenseOperatorServiceImpl_export_error, pluginId,
					e);
		}

		Optional<StreamCodec> codec = codec(new BaseLicensedProduct(productIdentifier, productVersion));
		if (!codec.isPresent()) {
			String format = LicensesCoreMessages.LicenseOperatorServiceImpl_w_no_encoding;
			String message = String.format(format, licenseIn);
			return LicensingResults.createWarning(message, pluginId, attachments);
		}

		String keyFileName = productIdentifier + '_' + productVersion;
		String privateKeyPath = storageKeyFolder + File.separator + keyFileName
				+ OperatorProductService.EXTENSION_KEY_PRIVATE;
		File privateProductToken = new File(privateKeyPath);
		if (!privateProductToken.exists()) {
			String pattern = LicensesCoreMessages.LicenseOperatorServiceImpl_private_key_not_found;
			String message = String.format(pattern, privateProductToken.getAbsolutePath());
			return LicensingResults.createError(message, pluginId);
		}

		String licenseOut = storageKeyFolder + File.separator + license.getIdentifier()
				+ LicensingPaths.EXTENSION_LICENSE_ENCRYPTED;
		File licenseEncoded = new File(licenseOut);
		try (FileInputStream licenseInput = new FileInputStream(licenseIn);
				FileOutputStream licenseOutput = new FileOutputStream(licenseEncoded);
				FileInputStream keyStream = new FileInputStream(privateProductToken)) {
			String username = productIdentifier;
			ProductVersionDescriptor pvd = productRegistry.getProductVersion(productIdentifier, productVersion);
			String password = operatorProductService.createPassword(pvd);
			codec.get().encode(licenseInput, licenseOutput, keyStream, username, password);
			eventAdmin.postEvent(OperatorLicenseEvents.encodedIssued(licenseOut));
			String format = LicensesCoreMessages.LicenseOperatorServiceImpl_export_success;
			String message = String.format(format, licenseOut);
			attachments.put(LicensesPackage.eNAME, licenseOut);
			return LicensingResults.createOK(message, pluginId, attachments);
		} catch (Exception e) {
			return LicensingResults.createError(LicensesCoreMessages.LicenseOperatorServiceImpl_export_error, pluginId,
					e);
		}
	}

	private Optional<StreamCodec> codec(LicensedProduct product) {
		return new CodecSupplier(product).get();
	}

	private Path getBasePath() {
		String areaValue = environmentInfo.getProperty("user.home"); //$NON-NLS-1$
		Path passagePath = Paths.get(areaValue, LicensingPaths.FOLDER_LICENSING_BASE);
		try {
			Files.createDirectories(passagePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return passagePath;
	}

	@Override
	public LicensePack createLicensePack(LicensingRequest request) {
		LicensesFactory licenseFactory = LicensesFactory.eINSTANCE;
		LicensePack licensePack = licenseFactory.createLicensePack();
		if (request == null) {
			return licensePack;
		}
		licensePack.setRequestIdentifier(request.getIdentifier());
		licensePack.setUserIdentifier(request.getUserIdentifier());
		licensePack.setUserFullName(request.getUserFullName());
		licensePack.setProductIdentifier(request.getProductIdentifier());
		licensePack.setProductVersion(request.getProductVersion());
		String planIdentifier = request.getPlanIdentifier();
		licensePack.setPlanIdentifier(planIdentifier);
		LicensePlanDescriptor licensePlan = licenseRegistry.getLicensePlan(planIdentifier);
		if (licensePlan == null) {
			return licensePack;
		}
		Iterable<? extends LicensePlanFeatureDescriptor> features = licensePlan.getLicensePlanFeatures();
		Date from = request.getValidFrom();
		Date until = request.getValidUntil();
		String conditionType = request.getConditionType();
		String expression = request.getConditionExpression();
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
