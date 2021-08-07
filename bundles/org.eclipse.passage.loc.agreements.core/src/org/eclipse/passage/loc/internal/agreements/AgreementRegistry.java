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
package org.eclipse.passage.loc.internal.agreements;

import java.util.Collection;

import org.eclipse.passage.lic.agreements.AgreementDescriptor;
import org.eclipse.passage.lic.agreements.AgreementGroupDescriptor;

public interface AgreementRegistry {

	Collection<AgreementGroupDescriptor> groups();

	AgreementGroupDescriptor group(String id);

	Collection<AgreementDescriptor> agreements();

	AgreementDescriptor agreement(String id);

}
