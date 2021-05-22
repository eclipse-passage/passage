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

import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
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
				processUserOriginUsers(userOrigin, notification);
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

	protected void processUserOriginUsers(UserOrigin userOrigin, Notification notification) {
		Object oldValue = notification.getOldValue();
		Object newValue = notification.getNewValue();
		switch (notification.getEventType()) {
		case Notification.ADD:
			if (newValue instanceof User) {
				User user = (User) newValue;
				if (Objects.equals(userOrigin, user.getOrigin())) {
					// FIXME: warning
				}
				String email = user.getContact().getEmail();
				if (email != null) {
					registry.registerUser(user);
				} else {
					// FIXME: warning
				}
			}
			break;
		case Notification.REMOVE:
			if (oldValue instanceof User) {
				User user = (User) oldValue;
				if (Objects.equals(userOrigin, user.getOrigin())) {
					// FIXME: warning
				}
				String email = user.getContact().getEmail();
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

	protected void processUserEmail(User user, Notification notification) {
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

}
