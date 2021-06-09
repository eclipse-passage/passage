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
package org.eclipse.passage.loc.internal.users.core;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
import org.eclipse.passage.lic.users.model.api.Contact;
import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.api.UserOrigin;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;
import org.eclipse.passage.loc.internal.emf.DomainContentAdapter;

public class UsersDomainRegistryTracker extends DomainContentAdapter<UserOriginDescriptor, UserDomainRegistry> {

	public UsersDomainRegistryTracker(UserDomainRegistry registry) {
		super(registry);
	}

	@Override
	public void notifyChanged(Notification notification) {
		Object notifier = notification.getNotifier();
		if (notifier instanceof UserOrigin) {
			UserOrigin userOrigin = (UserOrigin) notifier;
			switch (notification.getFeatureID(UserOrigin.class)) {
			case UsersPackage.USER_ORIGIN__IDENTIFIER:
				processUserOriginIdentifier(userOrigin, notification);
				break;
			case UsersPackage.USER_ORIGIN__USERS:
				processUserOriginUsers(notification);
				break;
			default:
				break;
			}
		} else if (notifier instanceof User) {
			User user = (User) notifier;
			switch (notification.getFeatureID(User.class)) {
			case UsersPackage.USER__IDENTIFIER:
				processUserIdentifier(user, notification);
				break;
			case UsersPackage.USER__CONTACT:
				processUserContact(user, notification);
				break;
			default:
				break;
			}
		} else if (notifier instanceof Contact) {
			Contact contact = (Contact) notifier;
			switch (notification.getFeatureID(Contact.class)) {
			case UsersPackage.CONTACT__EMAIL:
				processContactEmail(contact, notification);
				break;
			default:
				break;
			}
		}
		super.notifyChanged(notification);
	}

	protected void processUserOriginIdentifier(UserOrigin userOrigin, Notification notification) {
		String oldValue = notification.getOldStringValue();
		String newValue = notification.getNewStringValue();
		switch (notification.getEventType()) {
		case Notification.SET:
			if (oldValue != null) {
				registry.unregisterUserOrigin(oldValue);
			}
			if (newValue != null) {
				registry.registerUserOrigin(userOrigin);
			}
			break;
		default:
			break;
		}
	}

	protected void processUserOriginUsers(Notification notification) {
		Object oldValue = notification.getOldValue();
		Object newValue = notification.getNewValue();
		switch (notification.getEventType()) {
		case Notification.ADD:
			if (newValue instanceof User) {
				// FIXME: AF: not yet
//				User user = (User) newValue;
//				String identifier = user.getIdentifier();
//				if (identifier != null) {
//					registry.registerUser(user);
//				}
			}
			break;
		case Notification.REMOVE:
			if (oldValue instanceof User) {
				User user = (User) oldValue;
				String identifier = user.getIdentifier();
				if (identifier != null) {
					registry.unregisterUser(identifier);
				}
			}
			break;

		default:
			break;
		}
	}

	protected void processUserIdentifier(User user, Notification notification) {
		String oldValue = notification.getOldStringValue();
		String newValue = notification.getNewStringValue();
		switch (notification.getEventType()) {
		case Notification.SET:
			if (oldValue != null) {
				registry.unregisterUser(oldValue);
			}
			if (newValue != null) {
				registry.registerUser(user);
			}
			break;
		default:
			break;
		}
	}

	protected void processUserContact(User user, Notification notification) {
		Object oldValue = notification.getOldValue();
		Object newValue = notification.getNewValue();
		switch (notification.getEventType()) {
		case Notification.SET:
			if (newValue instanceof Contact) {
				Contact contact = (Contact) newValue;
				String email = contact.getEmail();
				if (email != null) {
					registry.registerUser(user);
				} else {
					// FIXME: warning
				}
			}
			break;
		case Notification.UNSET:
			if (oldValue instanceof User) {
				Contact contact = (Contact) oldValue;
				String email = contact.getEmail();
				if (email != null) {
					registry.unregisterUser(email);
				} else {
					// FIXME: warning
				}
			}
			break;

		default:
			break;
		}
	}

	protected void processContactEmail(Contact contact, Notification notification) {
		String oldValue = notification.getOldStringValue();
		String newValue = notification.getNewStringValue();
		switch (notification.getEventType()) {
		case Notification.SET:
			if (oldValue != null) {
				registry.unregisterUser(oldValue);
			}
			if (newValue != null) {
				EObject eContainer = contact.eContainer();
				if (eContainer instanceof User) {
					User user = (User) eContainer;
					registry.registerUser(user);
				}
			}
			break;
		default:
			break;
		}
	}

}
