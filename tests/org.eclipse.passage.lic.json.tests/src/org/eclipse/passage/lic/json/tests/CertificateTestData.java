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
package org.eclipse.passage.lic.json.tests;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationType;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.diagnostic.TroubleCode;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.Restriction;
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionLevel;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.conditions.BaseCondition;
import org.eclipse.passage.lic.internal.base.conditions.BaseEvaluationInstructions;
import org.eclipse.passage.lic.internal.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.lic.internal.base.conditions.BaseVersionMatch;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleDefault;
import org.eclipse.passage.lic.internal.base.conditions.evaluation.BasePermission;
import org.eclipse.passage.lic.internal.base.requirements.BaseFeature;
import org.eclipse.passage.lic.internal.base.requirements.BaseRequirement;
import org.eclipse.passage.lic.internal.base.restrictions.BaseRestriction;

@SuppressWarnings("restriction")
public class CertificateTestData {

	public LicensedProduct product() {
		return new BaseLicensedProduct("product", "version");//$NON-NLS-1$ //$NON-NLS-2$
	}

	public Condition condition() {
		return new BaseCondition("identifier", "feature", new BaseVersionMatch("version", new MatchingRuleDefault()), //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
				new BaseValidityPeriodClosed(ZonedDateTime.now(),
						ZonedDateTime.of(2020, 10, 1, 1, 1, 1, 1, ZoneId.systemDefault())),
				new BaseEvaluationInstructions(new EvaluationType.Hardware(), "expression")); //$NON-NLS-1$
	}

	public ZonedDateTime nowPlusTwo() {
		return ZonedDateTime.now().plusHours(2);
	}

	public Requirement requirement() {
		return new BaseRequirement(new BaseFeature("feature", "version", "name", "provider"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				new RestrictionLevel.Warning(), "source"); //$NON-NLS-1$
	}

	public TroubleCode trouble() {
		return new TroubleCode.Of(1, ""); //$NON-NLS-1$
	}

	public Permission permission() {
		return new BasePermission(product(), condition(), ZonedDateTime.now(), nowPlusTwo());
	}

	public Restriction restriction() {
		return new BaseRestriction(product(), requirement(), trouble());
	}

	public Collection<Permission> permissions() {
		return Collections.singletonList(permission());
	}

	public Collection<Restriction> restrictions() {
		return Collections.singletonList(restriction());
	}

	public Collection<Restriction> emptyRestrictions() {
		return Collections.emptyList();
	}

}
