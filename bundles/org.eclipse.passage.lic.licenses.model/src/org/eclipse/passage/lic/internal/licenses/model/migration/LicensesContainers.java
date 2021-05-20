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
package org.eclipse.passage.lic.internal.licenses.model.migration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.passage.lic.emf.migration.EnsureStructure;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.util.LicensesSwitch;

public class LicensesContainers extends LicensesSwitch<List<EObject>> implements EnsureStructure {

	@Override
	public List<EObject> apply(EObject object) {
		return doSwitch(object);
	}

	@Override
	public List<EObject> caseLicenseGrant(LicenseGrant object) {
		List<EObject> result = new ArrayList<>();
		result.add(new EnsureLicenseGrantEvaluationInstructions().apply(object));
		result.add(new EnsureLicenseGrantFeature().apply(object));
		result.add(new EnsureLicenseGrantValidityPeriod().apply(object));
		return result;
	}

	@Override
	public List<EObject> casePersonalLicensePack(PersonalLicensePack object) {
		List<EObject> result = new ArrayList<>();
		result.add(new EnsurePersonalPackLicense().apply(object));
		result.add(new EnsurePersonalPackProduct().apply(object));
		result.add(new EnsurePersonalPackUser().apply(object));
		return result;
	}

	@Override
	public List<EObject> defaultCase(EObject object) {
		return Collections.emptyList();
	}

}
