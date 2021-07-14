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
package org.eclipse.passage.lic.internal.licenses.convert;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.conditions.IssuerSignature;
import org.eclipse.passage.lic.base.conditions.BaseIssuerSignature;
import org.eclipse.passage.lic.licenses.model.api.Signature;
import org.eclipse.passage.lic.licenses.model.api.SignatureAttribute;

public final class PIssuerSignature implements Supplier<IssuerSignature> {

	private final Signature signature;

	public PIssuerSignature(Signature signature) {
		this.signature = signature;
	}

	@Override
	public IssuerSignature get() {
		return new BaseIssuerSignature(attributes(), parent());
	}

	private Map<String, String> attributes() {
		return signature.getAttributes().stream()//
				.collect(Collectors.toMap(//
						SignatureAttribute::getName, //
						SignatureAttribute::getValue));
	}

	private Optional<IssuerSignature> parent() {
		Signature parent = signature.getParent();
		return parent == null //
				? Optional.empty() //
				: Optional.of(new PIssuerSignature(parent).get());
	}

}
