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
package org.eclipse.passage.loc.internal.users.core;

import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.passage.lic.emf.ecore.DomainContentAdapter;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.api.UserLicense;
import org.eclipse.passage.lic.users.model.api.UserOrigin;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;

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
		} else if (notifier instanceof User) {
			User user = (User) notifier;
			switch (notification.getFeatureID(User.class)) {
			case UsersPackage.USER__EMAIL:
				processUserEmail(user, notification);
				break;
			case UsersPackage.USER__USER_LICENSES:
				processUserUserLicenses(user, notification);
				break;
			default:
				break;
			}
		} else if (notifier instanceof UserLicense) {
			UserLicense userLicense = (UserLicense) notifier;
			switch (notification.getFeatureID(User.class)) {
			case UsersPackage.USER_LICENSE__PACK_IDENTIFIER:
				processUserLicensePackageIdentifier(userLicense, notification);
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
				if (Objects.equals(userOrigin, user.getUserOrigin())) {
					// FIXME: warning
				}
				String email = user.getEmail();
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
				if (Objects.equals(userOrigin, user.getUserOrigin())) {
					// FIXME: warning
				}
				String email = user.getEmail();
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

	protected void processUserUserLicenses(User user, Notification notification) {
		Object oldValue = notification.getOldValue();
		Object newValue = notification.getNewValue();
		switch (notification.getEventType()) {
		case Notification.ADD:
			if (newValue instanceof UserLicense) {
				UserLicense userLicense = (UserLicense) newValue;
				if (Objects.equals(user, userLicense.getUser())) {
					// FIXME: warning
				}
				String identifier = userLicense.getPackIdentifier();
				if (identifier != null) {
					registry.registerUserLicense(userLicense);
				} else {
					// FIXME: warning
				}
			}
			break;
		case Notification.REMOVE:
			if (oldValue instanceof UserLicense) {
				UserLicense userLicense = (UserLicense) oldValue;
				if (Objects.equals(user, userLicense.getUser())) {
					// FIXME: warning
				}
				String packIdentifier = userLicense.getPackIdentifier();
				if (packIdentifier != null) {
					registry.unregisterUserLicense(packIdentifier);
				} else {
					// FIXME: warning
				}
			}
			break;

		default:
			break;
		}
	}

	protected void processUserLicensePackageIdentifier(UserLicense userLicense, Notification notification) {
		String oldValue = notification.getOldStringValue();
		String newValue = notification.getNewStringValue();
		switch (notification.getEventType()) {
		case Notification.SET:
			if (oldValue != null) {
				registry.unregisterUserLicense(oldValue);
			}
			if (newValue != null) {
				registry.registerUserLicense(userLicense);
			}
			break;
		default:
			break;
		}
	}

}
