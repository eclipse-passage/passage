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
package org.eclipse.passage.loc.internal.licenses.core;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.passage.lic.emf.ecore.DomainContentAdapter;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;
import org.eclipse.passage.lic.model.api.FeatureSet;
import org.eclipse.passage.lic.model.api.LicensePack;
import org.eclipse.passage.lic.model.meta.LicPackage;

public class LicensesDomainRegistryTracker extends DomainContentAdapter<LicensePackDescriptor, LicenseDomainRegistry> {

	public LicensesDomainRegistryTracker(LicenseDomainRegistry registry) {
		super(registry);
	}

	@Override
	public void notifyChanged(Notification notification) {
		Object notifier = notification.getNotifier();
		if (notifier instanceof LicensePack) {
			LicensePack licensePack = (LicensePack) notifier;
			switch (notification.getFeatureID(FeatureSet.class)) {
			case LicPackage.LICENSE_PACK__IDENTIFIER:
				processLicensePackIdentifier(licensePack, notification);
				break;
			// FIXME: over identifiers
			default:
				break;
			}
		}
		super.notifyChanged(notification);
	}

	protected void processLicensePackIdentifier(LicensePack licensePack, Notification notification) {
		String oldValue = notification.getOldStringValue();
		String newValue = notification.getNewStringValue();
		switch (notification.getEventType()) {
		case Notification.SET:
			if (oldValue != null) {
				registry.unregisterLicensePack(oldValue);
			}
			if (newValue != null) {
				registry.registerLicensePack(licensePack);
			}
		default:
			break;
		}
	}

}
