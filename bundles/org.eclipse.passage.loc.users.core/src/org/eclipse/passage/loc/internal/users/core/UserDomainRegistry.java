/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.loc.internal.users.core;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.passage.lic.api.LicensingReporter;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.emf.ecore.DomainContentAdapter;
import org.eclipse.passage.lic.emf.ecore.EditingDomainRegistry;
import org.eclipse.passage.lic.emf.edit.BaseDomainRegistry;
import org.eclipse.passage.lic.emf.edit.ComposedAdapterFactoryProvider;
import org.eclipse.passage.lic.emf.edit.EditingDomainRegistryAccess;
import org.eclipse.passage.lic.equinox.io.EquinoxPaths;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserLicenseDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;
import org.eclipse.passage.lic.users.registry.UserRegistry;
import org.eclipse.passage.lic.users.registry.UserRegistryEvents;
import org.eclipse.passage.loc.users.core.Users;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + Users.DOMAIN_NAME,
		EditingDomainRegistryAccess.PROPERTY_FILE_EXTENSION + '=' + Users.FILE_EXTENSION_XMI })
public class UserDomainRegistry extends BaseDomainRegistry<UserOriginDescriptor>
		implements UserRegistry, EditingDomainRegistry<UserOriginDescriptor> {

	private final Map<String, UserOriginDescriptor> userOriginIndex = new HashMap<>();
	private final Map<String, UserDescriptor> userIndex = new HashMap<>();
	private final Map<String, UserLicenseDescriptor> userLicenseIndex = new HashMap<>();

	@Reference
	@Override
	public void bindLicensingReporter(LicensingReporter admin) {
		super.bindLicensingReporter(admin);
	}

	@Override
	public void unbindLicensingReporter(LicensingReporter admin) {
		super.unbindLicensingReporter(admin);
	}

	@Reference
	@Override
	public void bindFactoryProvider(ComposedAdapterFactoryProvider factoryProvider) {
		super.bindFactoryProvider(factoryProvider);
	}

	@Override
	public void unbindFactoryProvider(ComposedAdapterFactoryProvider factoryProvider) {
		super.unbindFactoryProvider(factoryProvider);
	}

	@Override
	@Activate
	public void activate(Map<String, Object> properties) {
		super.activate(properties);
	}

	@Deactivate
	@Override
	public void deactivate(Map<String, Object> properties) {
		userIndex.clear();
		userOriginIndex.clear();
		super.deactivate(properties);
	}

	@Override
	public String getFileExtension() {
		return Users.FILE_EXTENSION_XMI;
	}

	@Override
	public Class<UserOriginDescriptor> getContentClass() {
		return UserOriginDescriptor.class;
	}

	@Override
	public String resolveIdentifier(UserOriginDescriptor content) {
		return content.getIdentifier();
	}

	@Override
	public Iterable<UserOriginDescriptor> getUserOrigins() {
		return new ArrayList<>(userOriginIndex.values());
	}

	@Override
	public UserOriginDescriptor getUserOrigin(String identifier) {
		return userOriginIndex.get(identifier);
	}

	@Override
	public Iterable<? extends UserDescriptor> getUsers() {
		return new ArrayList<>(userIndex.values());
	}

	public Iterable<? extends UserDescriptor> getUsers(String userOriginId) {
		UserOriginDescriptor userOrigin = userOriginIndex.get(userOriginId);
		if (userOrigin == null) {
			return Collections.emptyList();
		}
		return userOrigin.getUsers();
	}

	@Override
	public UserDescriptor getUser(String identifier) {
		return userIndex.get(identifier);
	}

	@Override
	public Iterable<? extends UserLicenseDescriptor> getUserLicenses() {
		return new ArrayList<>(userLicenseIndex.values());
	}

	@Override
	protected DomainContentAdapter<UserOriginDescriptor, UserDomainRegistry> createContentAdapter() {
		return new UsersDomainRegistryTracker(this);
	}

	public void registerUserOrigin(UserOriginDescriptor userOrigin) {
		String identifier = userOrigin.getIdentifier();
		UserOriginDescriptor existing = userOriginIndex.put(identifier, userOrigin);
		if (existing != null) {
			// FIXME: warning
		}
		licensingReporter.postResult(LicensingResults.createEvent(UserRegistryEvents.USER_ORIGIN_CREATE, userOrigin));
		userOrigin.getUsers().forEach(u -> registerUser(u));
	}

	public void registerUser(UserDescriptor user) {
		String identifier = user.getEmail();
		UserDescriptor existing = userIndex.put(identifier, user);
		if (existing != null) {
			// FIXME: warning
		}
		licensingReporter.postResult(LicensingResults.createEvent(UserRegistryEvents.USER_CREATE, user));
		user.getUserLicenses().forEach(u -> registerUserLicense(u));
	}

	public void registerUserLicense(UserLicenseDescriptor userLicense) {
		String identifier = userLicense.getPackIdentifier();
		UserLicenseDescriptor existing = userLicenseIndex.put(identifier, userLicense);
		if (existing != null) {
			// FIXME: warning
		}
		licensingReporter.postResult(LicensingResults.createEvent(UserRegistryEvents.USER_LICENSE_CREATE, userLicense));
	}

	public void unregisterUserOrigin(String userOriginId) {
		UserOriginDescriptor removed = userOriginIndex.remove(userOriginId);
		if (removed != null) {
			licensingReporter.postResult(LicensingResults.createEvent(UserRegistryEvents.USER_ORIGIN_DELETE, removed));
			removed.getUsers().forEach(u -> {
				unregisterUser(u.getEmail());
			});
		}
	}

	public void unregisterUser(String userId) {
		UserDescriptor removed = userIndex.remove(userId);
		if (removed != null) {
			licensingReporter.postResult(LicensingResults.createEvent(UserRegistryEvents.USER_DELETE, removed));
		}
	}

	public void unregisterUserLicense(String packId) {
		UserLicenseDescriptor removed = userLicenseIndex.remove(packId);
		if (removed != null) {
			licensingReporter.postResult(LicensingResults.createEvent(UserRegistryEvents.USER_LICENSE_DELETE, removed));
		}
	}

	@Override
	public EClass getContentClassifier() {
		return UsersPackage.eINSTANCE.getUserOrigin();
	}

	@Override
	public EStructuralFeature getContentIdentifierAttribute() {
		return UsersPackage.eINSTANCE.getUserOrigin_Identifier();
	}

	@Override
	public EStructuralFeature getContentNameAttribute() {
		return UsersPackage.eINSTANCE.getUserOrigin_Name();
	}

	@Override
	public void registerContent(UserOriginDescriptor content) {
		registerUserOrigin(content);
	}

	@Override
	public void unregisterContent(String identifier) {
		unregisterUserOrigin(identifier);
	}

	@Override
	protected Path getResourceSetPath() throws Exception {
		Path passagePath = EquinoxPaths.resolveInstallBasePath();
		Files.createDirectories(passagePath);
		Path domainPath = passagePath.resolve(domainName);
		return domainPath;
	}

}
