/*******************************************************************************
 * Copyright (c) 2021, 2024 ArSysOp
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
package org.eclipse.passage.loc.internal.agreements;

import java.util.Collection;
import java.util.Optional;

import org.eclipse.passage.lic.agreements.model.api.Agreement;
import org.eclipse.passage.lic.agreements.model.api.AgreementGroup;

public interface AgreementRegistry {

	Collection<AgreementGroup> groups();

	Optional<AgreementGroup> group(String id);

	Collection<Agreement> agreements();

	Optional<Agreement> agreement(String id);

}
