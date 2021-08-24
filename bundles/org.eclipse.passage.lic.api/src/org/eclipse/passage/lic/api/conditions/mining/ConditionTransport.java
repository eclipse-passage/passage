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
package org.eclipse.passage.lic.api.conditions.mining;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.eclipse.passage.lic.api.agreements.GlobalAgreement;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.conditions.IssuerSignature;
import org.eclipse.passage.lic.api.registry.Service;

/**
 * Encapsulates knowledge how to retrieve {@link Condition}(s) from an input
 * source of the configured {@linkplain ContentType}.
 * 
 * @since 2.1
 */
public interface ConditionTransport extends Service<ContentType> {

	/**
	 * Reads {@link Condition}(s) from the given {@link InputStream}. Stream remains
	 * open.
	 */
	Data read(InputStream input) throws IOException;

	public static final class Data {

		private final Collection<Condition> conditions;
		private final Collection<GlobalAgreement> agreements;
		private final Optional<IssuerSignature> signature;

		public Data() {
			this(Collections.emptyList(), Collections.emptyList(), Optional.empty());
		}

		public Data(Collection<Condition> conditions) {
			this(conditions, Collections.emptyList(), Optional.empty());
		}

		public Data(Collection<Condition> conditions, Collection<GlobalAgreement> agreements,
				Optional<IssuerSignature> signature) {
			this.conditions = conditions;
			this.signature = signature;
			this.agreements = agreements;
		}

		public Collection<Condition> conditions() {
			return conditions;
		}

		public Collection<GlobalAgreement> agreements() {
			return agreements;
		}

		public Optional<IssuerSignature> signature() {
			return signature;
		}

	}
}
