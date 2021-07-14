/*******************************************************************************
 * Copyright (c) 2020, 2021  ArSysOp
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
package org.eclipse.passage.lic.api.version;

/**
 * <p>
 * Raw notion of a version constructed and parsed for an arbitrary source
 * supplies a valid string representation or a structured equivalent.
 * </p>
 * <p>
 * Instance of this interface that survived the construction must always be
 * valid. That means eager parsing.
 * </p>
 * 
 * @since 2.1
 */
public interface Version {

	String value();

	SemanticVersion semantic();

}
