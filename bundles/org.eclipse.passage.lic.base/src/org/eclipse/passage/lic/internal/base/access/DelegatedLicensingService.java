/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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
package org.eclipse.passage.lic.internal.base.access;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.PassageLicenseCoverage;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.agreements.AgreementAcceptanceService;
import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;

/**
 * Implementation must be stateless.
 */
public interface DelegatedLicensingService extends PassageLicenseCoverage {

	LicensedProduct product();

	@Override
	ServiceInvocationResult<ExaminationCertificate> assess();

	/**
	 * For a given {@code agreement} a library should perform an analysis if this
	 * agreement is demanded by it, and in this case supply an instance and
	 * {@linkplain AgreementAcceptanceService} for acceptance.
	 */
	Optional<AgreementAcceptanceService> agreementsService(AgreementToAccept agreement);

	Collection<Condition> conditions(Path license);

	void installLicense(Path license) throws IOException;

}
