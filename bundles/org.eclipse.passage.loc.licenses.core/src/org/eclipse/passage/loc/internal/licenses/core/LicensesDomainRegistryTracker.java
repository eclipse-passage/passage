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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.loc.internal.emf.DomainContentAdapter;

public class LicensesDomainRegistryTracker extends DomainContentAdapter<LicensePlanDescriptor, LicenseDomainRegistry> {

	public LicensesDomainRegistryTracker(LicenseDomainRegistry registry) {
		super(registry);
	}

	@Override
	public void notifyChanged(Notification notification) {
		Object notifier = notification.getNotifier();
		if (notifier instanceof LicensePlan) {
			LicensePlan licensePlan = (LicensePlan) notifier;
			switch (notification.getFeatureID(LicensePlan.class)) {
			case LicensesPackage.LICENSE_PLAN__IDENTIFIER:
				processLicensePlanIdentifier(licensePlan, notification);
				break;
			// FIXME: over identifiers
			default:
				break;
			}
		}
		super.notifyChanged(notification);
	}

	protected void processLicensePlanIdentifier(LicensePlan licensePlan, Notification notification) {
		String oldValue = notification.getOldStringValue();
		String newValue = notification.getNewStringValue();
		switch (notification.getEventType()) {
		case Notification.SET:
			if (oldValue != null) {
				registry.unregisterLicensePlan(oldValue);
			}
			if (newValue != null) {
				registry.registerLicensePlan(licensePlan);
			}
			break;
		default:
			break;
		}
	}

}
