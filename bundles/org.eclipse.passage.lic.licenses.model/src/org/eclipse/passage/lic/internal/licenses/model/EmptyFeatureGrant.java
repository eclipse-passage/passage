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
package org.eclipse.passage.lic.internal.licenses.model;

import java.util.function.Supplier;

import org.eclipse.passage.lic.licenses.model.api.FeatureGrant;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;

public final class EmptyFeatureGrant implements Supplier<FeatureGrant> {

	@Override
	public FeatureGrant get() {
		FeatureGrant grant = LicensesFactory.eINSTANCE.createFeatureGrant();
		grant.setFeature(LicensesFactory.eINSTANCE.createFeatureRef());
		grant.getFeature().setVersionMatch(LicensesFactory.eINSTANCE.createVersionMatch());
		return grant;
	}

}
