/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lbc.internal.base.troubles;

import org.eclipse.passage.lic.internal.api.diagnostic.TroubleCode;

/**
 * @since 1.0
 */
@SuppressWarnings("restriction")
public final class ConditionEntryNotFound extends TroubleCode {

	public ConditionEntryNotFound() {
		super(601, "No bound license found for specified condition."); //$NON-NLS-1$
	}

}
