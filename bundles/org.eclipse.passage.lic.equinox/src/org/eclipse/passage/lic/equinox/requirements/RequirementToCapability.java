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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.base.NamedData;
import org.eclipse.passage.lic.base.StringNamedData;

/**
 * <p>
 * Represent a {@linkplain Requirement} as named piece of textual information to
 * be kept in a bundle manifest under {@code Provide-Capability} header.
 * </p>
 * <p>
 * To turn your {@linkplain Requirement} to a proper string, use
 * </p>
 * <span> new NamedData.Writable&lt;Requirement&gt;(new
 * RequirementToCapability(requirement)).write(target); </span>
 * 
 * @see NamedData.Writable
 * @since 2.1
 */
public final class RequirementToCapability implements NamedData<Requirement> {

	private final Requirement requirement;

	public RequirementToCapability(Requirement requirement) {
		Objects.requireNonNull(requirement);
		this.requirement = requirement;
	}

	@Override
	public Optional<Requirement> get() {
		return Optional.of(requirement);
	}

	@Override
	public String key() {
		return new LicCapabilityNamespace().get();
	}

	@Override
	public String printed(Requirement value) {
		StringBuilder out = new StringBuilder();
		attributes().stream() //
				.map(NamedData.Writable<String>::new)//
				.forEach(w -> w.write(out));
		return out.toString();
	}

	@Override
	public String keyValueSeparator() {
		return ";"; //$NON-NLS-1$
	}

	@Override
	public String entrySeparator() {
		return ","; //$NON-NLS-1$
	}

	private List<StringNamedData> attributes() {
		List<StringNamedData> mandatory = Arrays.asList(//
				new CapabilityLicFeatureId(requirement), //
				new CapabilityLicFeatureName(requirement), //
				new CapabilityLicFeatureVersion(requirement), //
				new CapabilityLicFeatureProvider(requirement), //
				new CapabilityLicFeatureLevel(requirement)); //
		if (requirement.agreements().isEmpty()) {
			return mandatory;
		}
		List<StringNamedData> full = new ArrayList<>(mandatory);
		full.add(new CapabilityLicFeatureAgreements(requirement));
		return full;
	}

}
