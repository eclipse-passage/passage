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

/**
 * Identifies a feature under license protection
 * 
 * @see Passage#canUse(FeatureIdentifier)
 * @see Passage#acquireLicense(FeatureIdentifier)
 * 
 * @since 4.0
 */
public interface FeatureIdentifier {

	String identifier();

}
