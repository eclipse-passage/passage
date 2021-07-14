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
package org.eclipse.passage.lic.internal.licenses.model.signature;

import java.util.Optional;

import org.eclipse.passage.lic.base.NamedData;
import org.eclipse.passage.lic.licenses.model.api.Signature;
import org.eclipse.passage.lic.licenses.model.api.SignatureAttribute;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;

/**
 * @since 2.0
 */
public abstract class SignatureData implements NamedData<String> {

	private final Signature signature;

	protected SignatureData(Signature signature) {
		this.signature = signature;
	}

	@Override
	public final Optional<String> get() {
		return attribute().map(SignatureAttribute::getValue);
	}

	private Optional<SignatureAttribute> attribute() {
		return signature.getAttributes().stream()//
				.filter(attr -> key().equals(attr.getName()))//
				.findAny();
	}

	public final void put(String value) {
		Optional<SignatureAttribute> attribute = attribute();
		if (!attribute.isPresent()) {
			attribute = Optional.of(LicensesFactory.eINSTANCE.createSignatureAttribute());
			attribute.get().setName(key());
			signature.getAttributes().add(attribute.get());
		}
		attribute.get().setValue(value);
	}

	public static final class LicensingOperatorName extends SignatureData {

		public LicensingOperatorName(Signature signature) {
			super(signature);
		}

		@Override
		public String key() {
			return "issuer.name"; //$NON-NLS-1$
		}

	}

	public static final class LicensingOperatorVersion extends SignatureData {

		public LicensingOperatorVersion(Signature signature) {
			super(signature);
		}

		@Override
		public String key() {
			return "issuer.version"; //$NON-NLS-1$
		}

	}

	public static final class OperatorLicensingStatus extends SignatureData {

		public OperatorLicensingStatus(Signature signature) {
			super(signature);
		}

		@Override
		public String key() {
			return "issuer.licensing_status"; //$NON-NLS-1$
		}

		public void notRequired() {
			put("not_required"); //$NON-NLS-1$
		}

		public void sufficient() {
			put("sufficient"); //$NON-NLS-1$
		}

		public void insufficient() {
			put("insufficient"); //$NON-NLS-1$
		}
	}

	public static final class Of extends SignatureData {

		private final String name;

		public Of(String name, Signature signature) {
			super(signature);
			this.name = name;
		}

		@Override
		public String key() {
			return name;
		}

	}

}
