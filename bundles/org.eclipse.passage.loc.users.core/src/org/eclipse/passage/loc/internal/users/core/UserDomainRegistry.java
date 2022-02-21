/*******************************************************************************
 * Copyright (c) 2018, 2022 ArSysOp
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
package org.eclipse.passage.loc.internal.users.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.internal.equinox.events.EquinoxEvent;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;
import org.eclipse.passage.lic.users.model.util.UsersResourceImpl;
import org.eclipse.passage.loc.internal.api.OperatorGearSupplier;
import org.eclipse.passage.loc.internal.api.workspace.KnownResources;
import org.eclipse.passage.loc.internal.api.workspace.OperatorWorkspace;
import org.eclipse.passage.loc.internal.api.workspace.ResourceHandle;
import org.eclipse.passage.loc.internal.api.workspace.Users;
import org.eclipse.passage.loc.internal.emf.BaseDomainRegistry;
import org.eclipse.passage.loc.internal.emf.DomainContentAdapter;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistry;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.internal.users.UserRegistry;
import org.eclipse.passage.loc.internal.users.UserRegistryEvents;
import org.eclipse.passage.loc.internal.users.core.i18n.UsersCoreMessages;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.EventAdmin;

@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + UsersPackage.eNAME,
		EditingDomainRegistryAccess.PROPERTY_FILE_EXTENSION + '=' + "users_xmi" })
public class UserDomainRegistry extends BaseDomainRegistry<UserOriginDescriptor>
		implements UserRegistry, EditingDomainRegistry<UserOriginDescriptor> {

	private final Map<String, UserOriginDescriptor> userOriginIndex = new HashMap<>();
	private final Map<String, UserDescriptor> userIndex = new HashMap<>();

	private EventAdmin events;

	@Reference
	public void bindEventAdmin(EventAdmin admin) {
		this.events = admin;
	}

	public void unbindEventAdmin(@SuppressWarnings("unused") EventAdmin admin) {
		this.events = null;
	}

	@Override
	@Reference
	public void bindGear(OperatorGearSupplier supplier) {
		super.bindGear(supplier);
	}

	@Override
	public void unbindGear(OperatorGearSupplier supplier) {
		super.unbindGear(supplier);
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
		return "users_xmi"; //$NON-NLS-1$
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
	protected DomainContentAdapter<UserOriginDescriptor, UserDomainRegistry> createContentAdapter() {
		return new UsersDomainRegistryTracker(this);
	}

	public void registerUserOrigin(UserOriginDescriptor userOrigin) {
		String identifier = userOrigin.getIdentifier();
		UserOriginDescriptor existing = userOriginIndex.put(identifier, userOrigin);
		if (existing != null) {
			String msg = NLS.bind(UsersCoreMessages.UserDomain_instance_duplication_message, existing, userOrigin);
			Platform.getLog(getClass()).warn(msg);
		}
		events.postEvent(new EquinoxEvent(UserRegistryEvents.USER_ORIGIN_CREATE, userOrigin).get());
		userOrigin.getUsers().forEach(u -> registerUser(u));
	}

	public void registerUser(UserDescriptor user) {
		String identifier = user.getContact().getEmail();
		UserDescriptor existing = userIndex.put(identifier, user);
		if (existing != null) {
			String msg = NLS.bind(UsersCoreMessages.UserDomain_instance_duplication_message, existing, user);
			Platform.getLog(getClass()).warn(msg);
		}
		events.postEvent(new EquinoxEvent(UserRegistryEvents.USER_CREATE, user).get());
	}

	public void unregisterUserOrigin(String userOriginId) {
		UserOriginDescriptor removed = userOriginIndex.remove(userOriginId);
		if (removed != null) {
			events.postEvent(new EquinoxEvent(UserRegistryEvents.USER_ORIGIN_DELETE, removed).get());
			removed.getUsers().forEach(u -> {
				unregisterUser(u.getContact().getEmail());
			});
		}
	}

	public void unregisterUser(String userId) {
		UserDescriptor removed = userIndex.remove(userId);
		if (removed != null) {
			events.postEvent(new EquinoxEvent(UserRegistryEvents.USER_DELETE, removed).get());
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
	protected final Resource createResource(URI uri) {
		return new UsersResourceImpl(uri);
	}

	@Override
	protected boolean emfResource(ResourceHandle handle) {
		return Users.xmi.equals(handle.type()) || Users.xmi033.equals(handle.type());
	}

	@Override
	protected KnownResources knownResources(OperatorWorkspace workspace) {
		return workspace.users();
	}

}
