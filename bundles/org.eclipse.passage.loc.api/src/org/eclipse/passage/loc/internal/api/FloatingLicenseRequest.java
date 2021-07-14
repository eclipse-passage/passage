/*******************************************************************************
 * Copyright (c) 2019, 2021 ArSysOp
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
package org.eclipse.passage.loc.internal.api;

import java.util.Collection;

import org.eclipse.passage.lic.api.EvaluationInstructions;

/**
 * Collection of all the data required to issue a floating license pack.
 */
public interface FloatingLicenseRequest extends GeneralLicenseRequest {

	/**
	 * Identifiers of all the <code>User</code>s selected to to gave access to this
	 * floating license
	 */
	Collection<String> users();

	/**
	 * Licensing condition for a <code>User</code> identified by the given
	 * {@code user} value.
	 */
	EvaluationInstructions userAuthentication(String user);

	/**
	 * Default capacity for all the <code>FeatureGrant</code>s defined in this
	 * floating license pack.
	 */
	int defaultCapacity();

}
