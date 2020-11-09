/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lbc.internal.base.chains;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.eclipse.passage.lbc.internal.api.RequestedCertificate;
import org.eclipse.passage.lbc.internal.api.persistence.Deserialization;
import org.eclipse.passage.lbc.internal.api.persistence.LoadedLicense;
import org.eclipse.passage.lbc.internal.api.persistence.PersistableLicense;
import org.eclipse.passage.lbc.internal.api.persistence.Serialization;
import org.eclipse.passage.lbc.internal.base.AcquiringFailures;
import org.eclipse.passage.lbc.internal.base.SatisfiedRequirements;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.api.restrictions.Restriction;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.restrictions.BaseExaminationCertificate;
import org.eclipse.passage.lic.internal.net.LicensingAction;

@SuppressWarnings("restriction")
public final class Acquire extends Operation<RequestedCertificate, ExaminationCertificate> {

	public Acquire(Deserialization<ExaminationCertificate> deserialization,
			Serialization<ExaminationCertificate> serialization, LoadedLicense load) {
		super(new CertificateOfRequest(deserialization), serialization, load);
	}

	@Override
	protected ServiceInvocationResult<ExaminationCertificate> execute(RequestedCertificate request) {
		final Map<Requirement, Permission> satisfied = new SatisfiedRequirements().apply(request.certificate());
		final Collection<Restriction> restrictions = new AcquiringFailures(this::take).apply(satisfied);
		return new BaseServiceInvocationResult<>(new BaseExaminationCertificate(satisfied, restrictions));
	}

	private boolean take(Condition condition) {
		final Optional<PersistableLicense> found = license(condition);
		if (found.isPresent()) {
			PersistableLicense persistable = found.get();
			if (!persistable.get().takeable()) {
				return false;
			}
			return persistable.takeOne();
		}
		return false;
	}

	@Override
	public LicensingAction action() {
		return new LicensingAction(new ConditionAction.Aquire());
	}

}
