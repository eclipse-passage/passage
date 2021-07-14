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

import java.util.Optional;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.passage.lic.api.Framework;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.conditions.ConditionOrigin;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.equinox.EquinoxPassage;
import org.eclipse.passage.lic.equinox.SuppliedFrameworkAware;
import org.eclipse.passage.lic.internal.licenses.convert.EIssuerSignature;
import org.eclipse.passage.lic.internal.licenses.model.signature.SignatureData;
import org.eclipse.passage.lic.licenses.model.api.LicenseRequisites;
import org.eclipse.passage.lic.licenses.model.api.Signature;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;

@SuppressWarnings("restriction")
final class LicenseSignature implements Consumer<LicenseRequisites> {

	private final Logger log = LogManager.getLogger(getClass());

	@Override
	public void accept(LicenseRequisites license) {
		ServiceInvocationResult<LicensedProduct> response = new SuppliedFrameworkAware()
				.withFrameworkService(this::product);
		if (response.data().isEmpty()) {
			report(response.diagnostic());
			return;
		}
		license.setSignature(signature(response.data().get()));
	}

	private void report(Diagnostic diagnostic) {
		log.error(new DiagnosticExplained(diagnostic).get());
	}

	private ServiceInvocationResult<LicensedProduct> product(Framework framework) {
		return new BaseServiceInvocationResult<>(framework.product());
	}

	private Signature signature(LicensedProduct operator) {
		Signature signature = LicensesFactory.eINSTANCE.createSignature();
		new SignatureData.LicensingOperatorName(signature).put(operator.identifier());
		new SignatureData.LicensingOperatorVersion(signature).put(operator.version());
		installParentSignature(signature, operator);
		return signature;
	}

	private void installParentSignature(Signature signature, LicensedProduct operator) {
		ServiceInvocationResult<ExaminationCertificate> assess = new EquinoxPassage().assess();
		if (assess.data().isEmpty()) {
			report(assess.diagnostic());
			return;
		}
		ExaminationCertificate certificate = assess.data().get();
		Optional<Requirement> requirement = new LicensingOperatorRequirement(certificate, operator).get();
		if (requirement.isEmpty()) {
			new SignatureData.OperatorLicensingStatus(signature).notRequired();
			return;
		}
		if (!certificate.satisfied().contains(requirement.get())) {
			new SignatureData.OperatorLicensingStatus(signature).insufficient();
			return;
		}
		new SignatureData.OperatorLicensingStatus(signature).sufficient();
		installParentSignature(signature, certificate.satisfaction(requirement.get()).conditionOrigin());
	}

	private void installParentSignature(Signature signature, ConditionOrigin origin) {
		signature.setParent(new EIssuerSignature(origin.signature()).get());
	}

}
