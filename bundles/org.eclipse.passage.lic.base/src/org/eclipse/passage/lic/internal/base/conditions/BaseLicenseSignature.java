/*******************************************************************************
 * Copyr
ight (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.base.conditions;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.passage.lic.internal.api.conditions.LicenseSignature;

public final class BaseLicenseSignature implements LicenseSignature {

	private final Map<String, String> attributes;
	private final Optional<LicenseSignature> parent;

	public BaseLicenseSignature(Map<String, String> attributes, LicenseSignature parent) {
		this(attributes, Optional.of(parent));
	}

	public BaseLicenseSignature(Map<String, String> attributes) {
		this(attributes, Optional.empty());
	}

	public BaseLicenseSignature() {
		this(Collections.emptyMap());
	}

	public BaseLicenseSignature(Map<String, String> attributes, Optional<LicenseSignature> parent) {
		Objects.requireNonNull(attributes, "BaseLicenseSignature::attributes"); //$NON-NLS-1$
		Objects.requireNonNull(parent, "BaseLicenseSignature::parent"); //$NON-NLS-1$
		this.attributes = attributes;
		this.parent = parent;
	}

	@Override
	public Map<String, String> attributes() {
		return attributes;
	}

	@Override
	public Optional<LicenseSignature> parent() {
		return parent;
	}

}
