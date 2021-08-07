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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.base.NamedData;

/**
 * <p>
 * Turns a list of {@linkplain Requirement}s to string for writing into a bundle
 * manifest under {@code Provide-Capability} header.
 * </p>
 * <p>
 * Typical usage:
 * </p>
 * <span> new NamedData.Writable&lt;List&lt;Requirement&gt;&gt;(new
 * RequirementsToBundle(requirements)).write(target); </span>
 * 
 * @see NamedData.Writable
 * @since 2.1
 */
public final class RequirementsToBundle implements NamedData<List<Requirement>> {

	private final List<Requirement> requirements;

	public RequirementsToBundle(List<Requirement> requirements) {
		this.requirements = requirements;
	}

	public RequirementsToBundle() {
		this(Collections.emptyList());
	}

	@Override
	public Optional<List<Requirement>> get() {
		return Optional.of(requirements);
	}

	@Override
	public String key() {
		return "Provide-Capability"; //$NON-NLS-1$
	}

	@Override
	public String printed(List<Requirement> values) {
		StringBuilder out = new StringBuilder();
		values.stream()//
				.map(RequirementToCapability::new) //
				.map(NamedData.Writable<Requirement>::new)//
				.forEach(w -> w.write(out));
		return out.toString();
	}

	@Override
	public String keyValueSeparator() {
		return ": "; //$NON-NLS-1$
	}

	@Override
	public String entrySeparator() {
		return ""; //$NON-NLS-1$
	}

}
