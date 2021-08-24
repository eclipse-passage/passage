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
package org.eclipse.passage.lic.base.agreements;

import java.util.Objects;

import org.eclipse.passage.lic.api.agreements.GlobalAgreement;

public final class BaseGlobalAgreement implements GlobalAgreement {
	private final String identifier;
	private final String name;
	private final String file;
	private final String hashAlgo;
	private final byte[] hash;
	private final byte[] content;

	public BaseGlobalAgreement(String identifier, String name, String file, String hashAlgo, byte[] hash,
			byte[] content) {
		Objects.requireNonNull(identifier, "BaseGlobalAgreement::identifier"); //$NON-NLS-1$
		Objects.requireNonNull(name, "BaseGlobalAgreement::name"); //$NON-NLS-1$
		Objects.requireNonNull(file, "BaseGlobalAgreement::file"); //$NON-NLS-1$
		Objects.requireNonNull(hashAlgo, "BaseGlobalAgreement::hashAlgo"); //$NON-NLS-1$
		Objects.requireNonNull(hash, "BaseGlobalAgreement::hash"); //$NON-NLS-1$
		Objects.requireNonNull(content, "BaseGlobalAgreement::content"); //$NON-NLS-1$
		this.identifier = identifier;
		this.name = name;
		this.file = file;
		this.hashAlgo = hashAlgo;
		this.hash = hash;
		this.content = content;
	}

	@Override
	public String identifier() {
		return identifier;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public String file() {
		return file;
	}

	@Override
	public String hashAlgo() {
		return hashAlgo;
	}

	@Override
	public byte[] hash() {
		return hash;
	}

	@Override
	public byte[] content() {
		return content;
	}

}
