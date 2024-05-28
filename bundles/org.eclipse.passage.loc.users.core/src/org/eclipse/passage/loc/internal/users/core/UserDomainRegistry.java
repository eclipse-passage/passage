/*******************************************************************************
 * Copyright (c) 2018, 2024 ArSysOp
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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.internal.equinox.events.EquinoxEvent;
import org.eclipse.passage.lic.internal.users.model.util.UsersResourceImpl;
import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.api.UserOrigin;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;
import org.eclipse.passage.loc.internal.api.OperatorGearSupplier;
import org.eclipse.passage.loc.internal.api.workspace.KnownResources;
import org.eclipse.passage.loc.internal.api.workspace.OperatorWorkspace;
import org.eclipse.passage.loc.internal.api.workspace.ResourceHandle;
import org.eclipse.passage.loc.internal.api.workspace.Users;
import org.eclipse.passage.loc.internal.emf.DomainContentAdapter;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistry;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.internal.equinox.BaseDomainRegistry;
import org.eclipse.passage.loc.internal.users.UserRegistry;
import org.eclipse.passage.loc.internal.users.UserRegistryEvents;
import org.eclipse.passage.loc.internal.users.core.i18n.UsersCoreMessages;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.event.EventAdmin;

@SuppressWarnings("restriction")
@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + UsersPackage.eNAME,
		EditingDomainRegistryAccess.PROPERTY_FILE_EXTENSION + '=' + "users_xmi" })
public final class UserDomainRegistry extends BaseDomainRegistry<UserOrigin>
		implements UserRegistry, EditingDomainRegistry<UserOrigin> {

	private final Map<String, UserOrigin> origins = new HashMap<>();
	private final Map<String, User> users = new HashMap<>();

	private final List<EventAdmin> events = new ArrayList<>();

	@Reference(cardinality = ReferenceCardinality.MANDATORY)
	public void bindEventAdmin(EventAdmin admin) {
		this.events.add(admin);
	}

	public void unbindEventAdmin(EventAdmin admin) {
		this.events.remove(admin);
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

	@Activate
	public void load(Map<String, Object> properties) {
		super.activate(properties);
	}

	@Deactivate
	public void unload(Map<String, Object> properties) {
		users.clear();
		origins.clear();
		super.deactivate(properties);
	}

	@Override
	public String getFileExtension() {
		return "users_xmi"; //$NON-NLS-1$
	}

	@Override
	public Class<UserOrigin> getContentClass() {
		return UserOrigin.class;
	}

	@Override
	public String resolveIdentifier(UserOrigin content) {
		return content.getIdentifier();
	}

	@Override
	public Collection<UserOrigin> userOrigins() {
		return new ArrayList<>(origins.values());
	}

	@Override
	public Optional<UserOrigin> userOrigin(String identifier) {
		return Optional.ofNullable(origins.get(identifier));
	}

	@Override
	public Collection<User> users() {
		return new ArrayList<>(users.values());
	}

	@Override
	public Optional<User> user(String identifier) {
		return Optional.ofNullable(users.get(identifier));
	}

	@Override
	protected DomainContentAdapter<UserOrigin, UserDomainRegistry> createContentAdapter() {
		return new UsersDomainRegistryTracker(this);
	}

	void registerUserOrigin(UserOrigin origin) {
		UserOrigin existing = origins.put(origin.getIdentifier(), origin);
		if ((existing != null) && (existing != origin)) {
			Platform.getLog(getClass())
					.warn(NLS.bind(UsersCoreMessages.UserDomain_instance_duplication_message, existing, origin));
		}
		events().postEvent(new EquinoxEvent(UserRegistryEvents.USER_ORIGIN_CREATE, origin).get());
		origin.getUsers().forEach(u -> registerUser(u));
	}

	void registerUser(User user) {
		User existing = users.put(user.getContact().getEmail(), user);
		if ((existing != null) && (existing != user)) {
			Platform.getLog(getClass())
					.warn(NLS.bind(UsersCoreMessages.UserDomain_instance_duplication_message, existing, user));
		}
		events().postEvent(new EquinoxEvent(UserRegistryEvents.USER_CREATE, user).get());
	}

	void unregisterUserOrigin(String id) {
		UserOrigin removed = origins.remove(id);
		if (removed != null) {
			events().postEvent(new EquinoxEvent(UserRegistryEvents.USER_ORIGIN_DELETE, removed).get());
			removed.getUsers().forEach(u -> unregisterUser(u.getContact().getEmail()));
		}
	}

	void unregisterUser(String userId) {
		User removed = users.remove(userId);
		if (removed != null) {
			events().postEvent(new EquinoxEvent(UserRegistryEvents.USER_DELETE, removed).get());
		}
	}

	private EventAdmin events() {
		return events.stream().findAny().get();
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
	public void registerContent(UserOrigin content) {
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
