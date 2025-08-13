/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support and improvements
 *******************************************************************************/
package org.eclipse.passage.lic.api.acquire;

import java.util.Date;

import org.eclipse.passage.lic.api.FeatureIdentifier;
import org.eclipse.passage.lic.api.GrantIdentifier;
import org.eclipse.passage.lic.api.UserIdentifier;

/**
 *
 * @since 2.1
 */
public interface GrantAcquisition {

	String identifier();

	/**
	 * @since 4.0
	 */
	GrantIdentifier grant();

	/**
	 * @since 4.0
	 */
	FeatureIdentifier feature();

	/**
	 * @since 4.0
	 */
	UserIdentifier user();

	Date created();
}
