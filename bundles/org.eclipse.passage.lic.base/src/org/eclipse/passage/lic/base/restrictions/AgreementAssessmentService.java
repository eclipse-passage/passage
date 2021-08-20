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
package org.eclipse.passage.lic.base.restrictions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.io.Hashes;
import org.eclipse.passage.lic.api.io.HashesRegistry;
import org.eclipse.passage.lic.api.registry.Registry;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.requirements.ResolvedAgreement;
import org.eclipse.passage.lic.api.restrictions.AgreementState;
import org.eclipse.passage.lic.api.restrictions.Restriction;
import org.eclipse.passage.lic.base.diagnostic.code.AgreementNotAccepted;
import org.eclipse.passage.lic.base.diagnostic.code.AgreementNotAccessible;
import org.eclipse.passage.lic.base.diagnostic.code.NoServicesOfType;
import org.eclipse.passage.lic.base.diagnostic.code.ServiceFailedOnMorsel;
import org.eclipse.passage.lic.base.io.UserHomeProductResidence;

final class AgreementAssessmentService {

	private final LicensedProduct product;
	private final Collection<Requirement> requirements;
	private final HashesRegistry hashes;

	AgreementAssessmentService(LicensedProduct product, Collection<Requirement> requirements, HashesRegistry hashes) {
		this.product = product;
		this.requirements = requirements;
		this.hashes = hashes;
	}

	Assessment assessment() {
		requirements.stream()//
				.forEach(this::assessment);

		return new Assessment(Collections.EMPTY_MAP); // TODO: work here
	}

	private void assessment(Requirement requirement) {
		requirement.agreements().stream()//
				.forEach(a -> assessment(a, requirement)); // TODO
	}

	private Optional<Restriction> assessment(ResolvedAgreement agreement, Requirement requirement) {
		try (InputStream content = agreement.content()) {
			return contentAssessment(bytes(content), agreement, requirement);
		} catch (IOException e) {
			return Optional.of(cannotBeRead(agreement.path(), requirement, e));
		}
	}

	private byte[] bytes(InputStream content) throws IOException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[16384];
		while ((nRead = content.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}
		buffer.flush();
		byte[] result = buffer.toByteArray();
		buffer.close();
		return result;
	}

	private Optional<Restriction> contentAssessment(byte[] content, ResolvedAgreement agreement,
			Requirement requirement) {
		@SuppressWarnings("hiding")
		Optional<Hashes> hashes = hashingService();
		if (!hashes.isPresent()) {
			return Optional.of(noHashingService(requirement));
		}
		String hash;
		try {
			hash = new String(hashes.get().get(content), "UTF-8"); //$NON-NLS-1$
		} catch (Exception e) {
			return Optional.of(failedToCalculateHash(requirement, e));
		}
		String name = chop(hash) + ".txt"; //$NON-NLS-1$
		Path accepted = new UserHomeProductResidence(product).get().resolve(name);
		if (!Files.exists(accepted) || !Files.isRegularFile(accepted)) {
			return Optional.of(notAccepted(agreement.path(), requirement));
		}
		return Optional.empty();

	}

	private String chop(String file) {
		int allowed = 251;
		return file.length() < allowed ? file : file.substring(0, allowed + 1);
	}

	private Restriction cannotBeRead(String agreement, Requirement requirement, Exception e) {
		return new BaseRestriction(product, requirement, //
				new AgreementNotAccessible(agreement, requirement.toString(), //
						e.getClass().getName() + ':' + e.getMessage()));
	}

	private Restriction notAccepted(String agreement, Requirement requirement) {
		return new BaseRestriction(product, requirement, new AgreementNotAccepted(agreement, requirement.toString()));
	}

	private Restriction failedToCalculateHash(Requirement requirement, Exception e) {
		return new BaseRestriction(product, requirement, new ServiceFailedOnMorsel(e)); // $NON-NLS-1$
	}

	private Restriction noHashingService(Requirement requirement) {
		return new BaseRestriction(product, requirement, new NoServicesOfType("hash calculator")); //$NON-NLS-1$
	}

	private Optional<Hashes> hashingService() {
		Registry<StringServiceId, Hashes> registry = hashes.get();
		if (registry.services().isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(registry.services().iterator().next());
	}

	static final class Assessment implements AgreementState {

		private final Map<Restriction, String> restrictions;

		Assessment(Map<Restriction, String> restrictions) {
			this.restrictions = restrictions;
		}

		Assessment() {
			this(Collections.emptyMap());
		}

		Map<Restriction, String> restrictions() {
			return restrictions;
		}

	}
}
