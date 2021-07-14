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

import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.conditions.IssuerSignature;
import org.eclipse.passage.lic.api.conditions.LicenseSignatureIsEmpty;
import org.eclipse.passage.lic.licenses.model.api.Signature;
import org.eclipse.passage.lic.licenses.model.api.SignatureAttribute;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;

public final class EIssuerSignature implements Supplier<Signature> {

	private final IssuerSignature signature;

	public EIssuerSignature(IssuerSignature signature) {
		Objects.requireNonNull(signature, "ELicenseSignature::signature"); //$NON-NLS-1$
		this.signature = signature;
	}

	@Override
	public Signature get() {
		if (new LicenseSignatureIsEmpty().test(signature)) {
			return null; // EMF null
		}
		Signature esignature = LicensesFactory.eINSTANCE.createSignature();
		installAttributes(esignature);
		installParent(esignature);
		return esignature;
	}

	private void installParent(Signature esignature) {
		if (!signature.parent().isPresent()) {
			return;
		}
		esignature.setParent(new EIssuerSignature(signature.parent().get()).get());
	}

	private void installAttributes(Signature esignature) {
		signature.attributes().entrySet().stream()//
				.map(this::eattribute)//
				.forEach(esignature.getAttributes()::add);
	}

	private SignatureAttribute eattribute(Entry<String, String> attribute) {
		SignatureAttribute eattribute = LicensesFactory.eINSTANCE.createSignatureAttribute();
		eattribute.setName(attribute.getKey());
		eattribute.setValue(attribute.getValue());
		return eattribute;
	}

}
