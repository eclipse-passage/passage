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
package org.eclipse.passage.lic.base.conditions;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.passage.lic.api.conditions.IssuerSignature;

/**
 * @since 2.1
 */
public final class BaseIssuerSignature implements IssuerSignature {

	private final Map<String, String> attributes;
	private final Optional<IssuerSignature> parent;

	public BaseIssuerSignature(Map<String, String> attributes, IssuerSignature parent) {
		this(attributes, Optional.of(parent));
	}

	public BaseIssuerSignature(Map<String, String> attributes) {
		this(attributes, Optional.empty());
	}

	public BaseIssuerSignature() {
		this(Collections.emptyMap());
	}

	public BaseIssuerSignature(Map<String, String> attributes, Optional<IssuerSignature> parent) {
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
	public Optional<IssuerSignature> parent() {
		return parent;
	}

}
