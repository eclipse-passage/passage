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
package org.eclipse.passage.loc.internal.licenses.core;

import java.util.Collection;
import java.util.Optional;

import org.eclipse.passage.lic.agreements.AgreementDescriptor;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.Hashes;
import org.eclipse.passage.lic.api.io.HashesRegistry;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.model.api.AgreementData;
import org.eclipse.passage.lic.licenses.model.api.LicenseRequisites;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;
import org.eclipse.passage.loc.internal.agreements.AgreementRegistry;
import org.eclipse.passage.loc.internal.api.workspace.Agreements;
import org.eclipse.passage.loc.internal.equinox.AgreementsService;
import org.eclipse.passage.loc.internal.equinox.OperatorGearAware;
import org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages;

@SuppressWarnings("restriction")
final class LicenseAgreements {

	private final AgreementRegistry registry;

	LicenseAgreements(AgreementRegistry registry) {
		this.registry = registry;
	}

	void install(LicensePlanDescriptor plan, LicenseRequisites license) throws LicensingException {
		Agreements service = new AgreementsService().get(); // TODO: cashed field
		Hashes hashes = hashes();// TODO: cashed field
		for (String identifier : plan.getAgreements()) {
			installAgreement(license, registry.agreement(identifier), service, hashes);
		}

	}

	private void installAgreement(LicenseRequisites license, AgreementDescriptor agreement, Agreements service,
			Hashes hashes) throws LicensingException {
		if (!service.exists(agreement.getFile())) {
			throw new LicensingException(String.format(//
					LicensesCoreMessages.LicenseOperatorServiceImpl_failed_to_find_agreement_file, //
					service.located(agreement.getFile()).info(), //
					agreement.getName()));
		}
		license.getAgreements().add(data(agreement, service, hashes));
	}

	private AgreementData data(AgreementDescriptor agreement, Agreements service, Hashes hashes)
			throws LicensingException {
		AgreementData data = LicensesFactory.eINSTANCE.createAgreementData();
		data.setIdentifier(agreement.getIdentifier());
		data.setName(agreement.getName());
		data.setFile(agreement.getFile());
		data.setContentType(agreement.getMime());
		byte[] content = content(agreement, service);
		data.setContent(content);
		data.setHashAlgo(hashes.id().toString());
		data.setHash(hashes.get(content));
		return data;
	}

	private byte[] content(AgreementDescriptor agreement, Agreements service) throws LicensingException {
		try {
			return service.located(agreement.getFile()).content();
		} catch (Exception e) {
			throw new LicensingException(String.format(//
					LicensesCoreMessages.LicenseOperatorServiceImpl_failed_to_attach_agreement, //
					agreement.getName(), //
					service.located(agreement.getFile()).info(), //
					e));
		}
	}

	private Hashes hashes() throws LicensingException {
		Optional<HashesRegistry> service = new OperatorGearAware().withGear(gear -> Optional.of(gear.hashes()));
		if (!service.isPresent()) {
			throw new LicensingException("There is no HashesRegistry service supplied by Operator Gear"); //$NON-NLS-1$
		}
		Collection<Hashes> all = service.get().get().services();
		if (all.isEmpty()) {
			throw new LicensingException("There is no Hashes service supplied by Operator Gear"); //$NON-NLS-1$
		}
		return all.iterator().next();
	}
}
