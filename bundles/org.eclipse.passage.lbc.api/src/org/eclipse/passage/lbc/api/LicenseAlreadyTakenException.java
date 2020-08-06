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
package org.eclipse.passage.lbc.api;

import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;

/**
 * @since 1.0
 */
public class LicenseAlreadyTakenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 950020125519095982L;

	public LicenseAlreadyTakenException(ConditionPack conditionPack) {
		super("License is already taken: " + conditionPack.toString()); //$NON-NLS-1$
	}

}
