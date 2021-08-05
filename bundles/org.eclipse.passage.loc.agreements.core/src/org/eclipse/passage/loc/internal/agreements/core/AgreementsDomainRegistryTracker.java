/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.loc.internal.agreements.core;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.passage.lic.agreements.AgreementsGroupDescriptor;
import org.eclipse.passage.lic.agreements.model.api.Agreement;
import org.eclipse.passage.lic.agreements.model.api.AgreementsGroup;
import org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage;
import org.eclipse.passage.loc.internal.emf.DomainContentAdapter;

@SuppressWarnings("restriction")
public class AgreementsDomainRegistryTracker
		extends DomainContentAdapter<AgreementsGroupDescriptor, AgreementDomainRegistry> {

	public AgreementsDomainRegistryTracker(AgreementDomainRegistry registry) {
		super(registry);
	}

	@Override
	public void notifyChanged(Notification notification) {
		Object notifier = notification.getNotifier();
		if (notifier instanceof AgreementsGroup) {
			AgreementsGroup group = (AgreementsGroup) notifier;
			switch (notification.getFeatureID(AgreementsGroup.class)) {
			case AgreementsPackage.AGREEMENTS_GROUP__IDENTIFIER:
				processAgreementsGroupIdentifier(group, notification);
				break;
			case AgreementsPackage.AGREEMENTS_GROUP__AGREEMENTS:
				processAgreementsGroupContent(notification);
				break;
			default:
				break;
			}
		} else if (notifier instanceof Agreement) {
			Agreement agreement = (Agreement) notifier;
			switch (notification.getFeatureID(Agreement.class)) {
			case AgreementsPackage.AGREEMENT__IDENTIFIER:
				processAgreementIdentifier(agreement, notification);
				break;
			default:
				break;
			}
		}
		super.notifyChanged(notification);
	}

	protected void processAgreementsGroupIdentifier(AgreementsGroup group, Notification notification) {
		String old = notification.getOldStringValue();
		switch (notification.getEventType()) {
		case Notification.SET:
			if (old != null) {
				registry.unregisterAgreementsGroup(old);
			}
			if (notification.getNewStringValue() != null) {
				registry.registerAgreementsGroup(group);
			}
			break;
		default:
			break;
		}
	}

	protected void processAgreementsGroupContent(Notification notification) {
		Object oldValue = notification.getOldValue();
		Object newValue = notification.getNewValue();
		switch (notification.getEventType()) {
		case Notification.ADD:
			if (newValue instanceof Agreement) {
			}
			break;
		case Notification.REMOVE:
			if (oldValue instanceof Agreement) {
				String identifier = ((Agreement) oldValue).getIdentifier();
				if (identifier != null) {
					registry.unregisterAgreement(identifier);
				}
			}
			break;
		default:
			break;
		}
	}

	protected void processAgreementIdentifier(Agreement agreement, Notification notification) {
		String oldValue = notification.getOldStringValue();
		String newValue = notification.getNewStringValue();
		switch (notification.getEventType()) {
		case Notification.SET:
			if (oldValue != null) {
				registry.unregisterAgreement(oldValue);
			}
			if (newValue != null) {
				registry.registerAgreement(agreement);
			}
			break;
		default:
			break;
		}
	}

}
