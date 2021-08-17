/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.equinox.requirements;

import java.util.Map;

import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.base.NamedData;

/**
 * <p>
 * Encapsulate reading of a {@code agreements} '::'-separated list of a feature
 * under licensing from a {@code Capability}'s attributes.
 * </p>
 * <p>
 * Sample value: "LICENSE::terms of use.txt::thirdparty/license.html"
 * </p>
 * 
 * @see NamedData
 * @since 2.1
 */
public final class CapabilityLicFeatureAgreements extends CapabilityLicFeatureInfo {

	public CapabilityLicFeatureAgreements(Requirement requirement) {
		super(new ListOfAgreements().toSource(requirement.agreements()));
	}

	public CapabilityLicFeatureAgreements(Map<String, Object> container) {
		super(container);
	}

	@Override
	public String key() {
		return "agreements"; //$NON-NLS-1$
	}

}
