/*******************************************************************************
 * Copyright (c) 2021, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support
 *******************************************************************************/
package org.eclipse.passage.loc.internal.licenses.core;

import java.util.Collection;
import java.util.Optional;

import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.agreements.model.api.Agreement;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.Hashes;
import org.eclipse.passage.lic.api.io.HashesRegistry;
import org.eclipse.passage.lic.licenses.model.api.AgreementData;
import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.lic.licenses.model.api.LicenseRequisites;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;
import org.eclipse.passage.loc.internal.agreements.AgreementRegistry;
import org.eclipse.passage.loc.internal.api.workspace.Agreements;
import org.eclipse.passage.loc.internal.equinox.AgreementsService;
import org.eclipse.passage.loc.internal.equinox.OperatorGearAware;
import org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages;

final class LicenseAgreements {

	private final AgreementRegistry registry;

	LicenseAgreements(AgreementRegistry registry) {
		this.registry = registry;
	}

	void install(LicensePlan plan, LicenseRequisites license) throws LicensingException {
		Agreements service = new AgreementsService().get(); // TODO: cashed field
		Hashes hashes = hashes();// TODO: cashed field
		for (String identifier : plan.getAgreements()) {
			installAgreement(license, registry.agreement(identifier)
					.orElseThrow(() -> new LicensingException(
							NLS.bind(LicensesCoreMessages.LicenseAgreements_e_agreement_not_found, identifier))),
					service, hashes);
		}

	}

	private void installAgreement(LicenseRequisites license, Agreement agreement, Agreements service, Hashes hashes)
			throws LicensingException {
		if (!service.exists(agreement.getFile(), agreement)) {
			throw new LicensingException(String.format(//
					LicensesCoreMessages.LicenseOperatorServiceImpl_failed_to_find_agreement_file, //
					service.located(agreement.getFile(), agreement).info(), //
					agreement.getName()));
		}
		license.getAgreements().add(data(agreement, service, hashes));
	}

	private AgreementData data(Agreement agreement, Agreements service, Hashes hashes) throws LicensingException {
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

	private byte[] content(Agreement agreement, Agreements service) throws LicensingException {
		try {
			return service.located(agreement.getFile(), agreement).content();
		} catch (Exception e) {
			throw new LicensingException(String.format(//
					LicensesCoreMessages.LicenseOperatorServiceImpl_failed_to_attach_agreement, //
					agreement.getName(), //
					service.located(agreement.getFile(), agreement).info(), //
					e));
		}
	}

	@SuppressWarnings("restriction")
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
