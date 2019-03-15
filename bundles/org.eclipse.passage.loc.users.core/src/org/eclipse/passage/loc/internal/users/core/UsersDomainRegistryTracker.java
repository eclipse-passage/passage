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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.passage.lic.emf.edit.DomainContentAdapter;
import org.eclipse.passage.lic.model.api.User;
import org.eclipse.passage.lic.model.api.UserOrigin;
import org.eclipse.passage.lic.model.meta.LicPackage;
import org.eclipse.passage.lic.users.UserOriginDescriptor;

public class UsersDomainRegistryTracker extends DomainContentAdapter<UserOriginDescriptor, UsersDomainRegistry> {

	public UsersDomainRegistryTracker(UsersDomainRegistry registry) {
		super(registry);
	}

	@Override
	public void notifyChanged(Notification notification) {
		Object notifier = notification.getNotifier();
		if (notifier instanceof UserOrigin) {
			UserOrigin userOrigin = (UserOrigin) notifier;
			switch (notification.getFeatureID(UserOrigin.class)) {
			case LicPackage.USER_ORIGIN__IDENTIFIER:
				processUserOriginIdentifier(userOrigin, notification);
				break;
			case LicPackage.USER_ORIGIN__USERS:
				processUserOriginUsers(userOrigin, notification);
				break;
			default:
				break;
			}
		} else if (notifier instanceof User) {
			User user = (User) notifier;
			switch (notification.getFeatureID(User.class)) {
			case LicPackage.USER__EMAIL:
				processUserEmail(user, notification);
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
				String email = user.getEmail();
				if (email != null) {
					registry.registerUser(user);
				}
			}
			break;
		case Notification.REMOVE:
			if (oldValue instanceof User) {
				User user = (User) oldValue;
				String email = user.getEmail();
				if (email != null) {
					registry.unregisterUser(email);
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
