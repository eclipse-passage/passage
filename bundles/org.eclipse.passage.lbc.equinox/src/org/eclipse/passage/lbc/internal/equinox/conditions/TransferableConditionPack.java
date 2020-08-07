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
package org.eclipse.passage.lbc.internal.equinox.conditions;

import java.util.Collection;

import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;

public class TransferableConditionPack {

	private final String origin;
	private final Collection<Condition> conditions;

	public TransferableConditionPack(ConditionPack conditionPack) {
		this.conditions = conditionPack.conditions();
		this.origin = conditionPack.origin();
	}

	public LicensePack ePack() {
		LicensesFactory factory = LicensesFactory.eINSTANCE;
		LicensePack pack = factory.createLicensePack();
		conditions.forEach(condition -> pack.getLicenseGrants().add(grantFrom(condition, factory)));
		return pack;
	}

	private LicenseGrant grantFrom(Condition condition, LicensesFactory factory) {
		LicenseGrant eCondition = factory.createLicenseGrant();
		eCondition.setFeatureIdentifier(condition.feature());
		eCondition.setMatchVersion(condition.versionMatch().version());
		eCondition.setConditionExpression(condition.evaluationInstructions().expression());
		eCondition.setConditionType(condition.evaluationInstructions().type().identifier());
		return eCondition;
	}

}
