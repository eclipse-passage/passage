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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.service.environment.EnvironmentInfo;
import org.eclipse.passage.lic.emf.edit.ComposedAdapterFactoryProvider;
import org.eclipse.passage.lic.emf.edit.DomainContentAdapter;
import org.eclipse.passage.lic.emf.edit.DomainRegistryAccess;
import org.eclipse.passage.lic.emf.edit.EditingDomainRegistry;
import org.eclipse.passage.lic.model.api.User;
import org.eclipse.passage.lic.model.api.UserOrigin;
import org.eclipse.passage.lic.model.meta.LicPackage;
import org.eclipse.passage.lic.registry.Identified;
import org.eclipse.passage.lic.registry.UserDescriptor;
import org.eclipse.passage.lic.registry.UserOriginDescriptor;
import org.eclipse.passage.lic.registry.UserRegistry;
import org.eclipse.passage.lic.registry.UsersEvents;
import org.eclipse.passage.lic.registry.UsersRegistry;
import org.eclipse.passage.loc.edit.EditingDomainBasedRegistry;
import org.eclipse.passage.loc.edit.UserDomainRegistry;
import org.eclipse.passage.loc.runtime.OperatorEvents;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.EventAdmin;

@Component(property = { DomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + UsersRegistry.DOMAIN_NAME,
		DomainRegistryAccess.PROPERTY_FILE_EXTENSION + '=' + UsersRegistry.FILE_EXTENSION_XMI })
public class UserDomainRegistryImpl extends EditingDomainBasedRegistry
		implements UserRegistry, UserDomainRegistry, EditingDomainRegistry {

	private final Map<String, UserOrigin> userOriginIndex = new HashMap<>();
	private final Map<String, User> userIndex = new HashMap<>();

	@Reference
	@Override
	public void bindEnvironmentInfo(EnvironmentInfo environmentInfo) {
		super.bindEnvironmentInfo(environmentInfo);
	}

	@Override
	public void unbindEnvironmentInfo(EnvironmentInfo environmentInfo) {
		super.unbindEnvironmentInfo(environmentInfo);
	}

	@Reference
	@Override
	public void bindEventAdmin(EventAdmin eventAdmin) {
		super.bindEventAdmin(eventAdmin);
	}

	@Override
	public void unbindEventAdmin(EventAdmin eventAdmin) {
		super.unbindEventAdmin(eventAdmin);
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
		return UsersRegistry.FILE_EXTENSION_XMI;
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
	public Iterable<UserDescriptor> getUsers() {
		return new ArrayList<>(userIndex.values());
	}

	@Override
	public Iterable<UserDescriptor> getUsers(String userOriginId) {
		UserOrigin userOrigin = userOriginIndex.get(userOriginId);
		if (userOrigin == null) {
			return Collections.emptyList();
		}
		return new ArrayList<>(userOrigin.getUsers());
	}

	@Override
	public UserDescriptor getUser(String identifier) {
		return userIndex.get(identifier);
	}

	@Override
	protected DomainContentAdapter<UserDomainRegistry> createContentAdapter() {
		return new UserDomainRegistryTracker(this);
	}

	@Override
	public void registerUserOrigin(UserOrigin userOrigin) {
		String identifier = userOrigin.getIdentifier();
		UserOrigin existing = userOriginIndex.put(identifier, userOrigin);
		if (existing != null) {
			// FIXME: warning
		}
		eventAdmin.postEvent(OperatorEvents.create(UsersEvents.USER_ORIGIN_CREATE, userOrigin));
		EList<User> users = userOrigin.getUsers();
		for (User user : users) {
			registerUser(user);
		}
	}

	@Override
	public void registerUser(User user) {
		String identifier = user.getEmail();
		User existing = userIndex.put(identifier, user);
		if (existing != null) {
			// FIXME: warning
		}
		eventAdmin.postEvent(OperatorEvents.create(UsersEvents.USER_CREATE, user));
	}

	@Override
	public void unregisterUserOrigin(String userOriginId) {
		UserOrigin removed = userOriginIndex.remove(userOriginId);
		if (removed != null) {
			eventAdmin.postEvent(OperatorEvents.create(UsersEvents.USER_ORIGIN_DELETE, removed));
			EList<User> users = removed.getUsers();
			for (User user : users) {
				unregisterUser(user.getEmail());
			}
		}
	}

	@Override
	public void unregisterUser(String userId) {
		User removed = userIndex.remove(userId);
		if (removed != null) {
			eventAdmin.postEvent(OperatorEvents.create(UsersEvents.USER_DELETE, removed));
		}
	}

	@Override
	public EClass getContentClassifier() {
		return LicPackage.eINSTANCE.getUserOrigin();
	}

	@Override
	public EStructuralFeature getContentIdentifierAttribute() {
		return LicPackage.eINSTANCE.getUserOrigin_Identifier();
	}

	@Override
	public EStructuralFeature getContentNameAttribute() {
		return LicPackage.eINSTANCE.getUserOrigin_Name();
	}

	@Override
	public void registerContent(Identified content) {
		if (content instanceof UserOrigin) {
			UserOrigin userOrigin = (UserOrigin) content;
			registerUserOrigin(userOrigin);
		} else {
			//TODO: warning
		}
	}

	@Override
	public void unregisterContent(String identifier) {
		unregisterUserOrigin(identifier);
	}

}
