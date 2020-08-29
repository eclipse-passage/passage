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
package org.eclipse.passage.lbc.chains.tests;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;

import org.eclipse.passage.lbc.internal.api.Requester;
import org.eclipse.passage.lbc.internal.api.persistence.BoundLicense;
import org.eclipse.passage.lbc.internal.base.BaseBoundLicense;
import org.eclipse.passage.lbc.internal.base.BaseRequester;
import org.eclipse.passage.lbc.internal.base.ConditionIdentifier;
import org.eclipse.passage.lbc.internal.base.LicenseCapacity;
import org.eclipse.passage.lbc.internal.base.LicenseTaken;
import org.eclipse.passage.lbc.json.Serialization;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationType;
import org.eclipse.passage.lic.internal.api.conditions.ValidityPeriod;
import org.eclipse.passage.lic.internal.api.conditions.VersionMatch;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionLevel;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.conditions.BaseCondition;
import org.eclipse.passage.lic.internal.base.conditions.BaseEvaluationInstructions;
import org.eclipse.passage.lic.internal.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.lic.internal.base.conditions.BaseVersionMatch;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleDefault;
import org.eclipse.passage.lic.internal.base.conditions.evaluation.BasePermission;
import org.eclipse.passage.lic.internal.base.requirements.BaseFeature;
import org.eclipse.passage.lic.internal.base.requirements.BaseRequirement;
import org.eclipse.passage.lic.internal.base.restrictions.BaseExaminationCertificate;

@SuppressWarnings("restriction")
public abstract class ChainTestsBase {

	protected LicensedProduct product() {
		return new BaseLicensedProduct(identifierValue(), versionValue());
	}

	protected Requester requester() {
		return new BaseRequester("process", "hardware", "feature"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	protected String identifierValue() {
		return "identifier"; //$NON-NLS-1$
	}

	protected String featureValue() {
		return "feature"; //$NON-NLS-1$
	}

	protected String versionValue() {
		return "version"; //$NON-NLS-1$
	}

	protected FakeLicensingRequest certificateRequest() {
		return new FakeLicensingRequest(Collections.emptyMap(), new Serialization<ExaminationCertificate>()
				.apply(new BaseServiceInvocationResult<>(certificate())).data().get());
	}

	protected FakeLicensingRequest conditionRequest() {
		return new FakeLicensingRequest(Collections.emptyMap(),
				new Serialization<Condition>().apply(new BaseServiceInvocationResult<>(condition())).data().get());
	}

	protected ExaminationCertificate certificate() {
		return new BaseExaminationCertificate(Collections.singletonMap(requirement(), permission()),
				Collections.emptyList());
	}

	private Permission permission() {
		return new BasePermission(product(), condition(), ZonedDateTime.now(), ZonedDateTime.now().plusDays(2));
	}

	private Requirement requirement() {
		return new BaseRequirement(new BaseFeature(identifierValue(), versionValue(), featureValue(), "provider"), //$NON-NLS-1$
				new RestrictionLevel.Info(), new Object());
	}

	protected BoundLicense boundLicense(int taken, int capacity) {
		return new BaseBoundLicense(new ConditionIdentifier(identifierValue()), new LicenseTaken(key -> taken),
				new LicenseCapacity(key -> capacity));
	}

	protected Condition condition() {
		return new BaseCondition(identifierValue(), featureValue(), versionMatch(), validityPeriod(),
				evaluationInstructions());
	}

	private BaseEvaluationInstructions evaluationInstructions() {
		return new BaseEvaluationInstructions(new EvaluationType.Hardware(), "expression"); //$NON-NLS-1$
	}

	private ValidityPeriod validityPeriod() {
		return new BaseValidityPeriodClosed(ZonedDateTime.now(),
				ZonedDateTime.of(2021, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault())); // $NON-NLS-1$
	}

	private VersionMatch versionMatch() {
		return new BaseVersionMatch("version", new MatchingRuleDefault()); //$NON-NLS-1$
	}

}
