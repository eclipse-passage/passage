/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support and improvements
 *******************************************************************************/
package org.eclipse.passage.lic.equinox.requirements;

import java.util.Map;
import java.util.Optional;

import org.eclipse.passage.lic.api.FeatureIdentifier;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.base.BaseFeatureIdentifier;
import org.eclipse.passage.lic.base.BaseNamedData;
import org.eclipse.passage.lic.base.NamedData;

/**
 * Encapsulate reading of a {@code feature identifier} from a
 * {@code Capability}'s attributes.
 *
 * @see NamedData
 * @since 2.1
 */
public final class CapabilityLicFeatureId extends BaseNamedData<FeatureIdentifier> {

	public CapabilityLicFeatureId(Requirement requirement) {
		super(k -> requirement.feature().identifier());
	}

	public CapabilityLicFeatureId(Map<String, Object> container) {
		super(key -> Optional.ofNullable(container.get(key))//
				.map(String::valueOf) //
				.map(BaseFeatureIdentifier::new) //
				.orElse(null));
	}

	@Override
	public String key() {
		return "licensing.feature"; //$NON-NLS-1$
	}

	@Override
	public String printed(FeatureIdentifier value) {
		return "\"" + value.identifier() + "\""; //$NON-NLS-1$ //$NON-NLS-2$
	}

}
