/*******************************************************************************
 * Copyright (c) 2024 ArSysOp
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
package org.eclipse.passage.lic.api;

import org.eclipse.passage.lic.api.acquire.GrantAcquisition;

/**
 * Identifies a grant acquired to use a feature under license protection
 * 
 * @see GrantAcquisition#grant()
 * 
 * @since 4.0
 */
public interface GrantIdentifier {

	String identifier();

}
