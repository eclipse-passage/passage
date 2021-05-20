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

import java.util.Optional;
import java.util.function.Function;

import org.eclipse.passage.lic.licenses.model.api.FeatureRef;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;

public final class EnsureLicenseGrantFeature implements Function<LicenseGrant, FeatureRef> {

	@Override
	public FeatureRef apply(LicenseGrant grant) {
		return Optional.ofNullable(grant.getFeature()).orElseGet(() -> reset(grant));
	}

	private FeatureRef reset(LicenseGrant grant) {
		FeatureRef feature = LicensesFactory.eINSTANCE.createFeatureRef();
		grant.setFeature(feature);
		return feature;
	}

}
