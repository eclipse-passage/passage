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
package org.eclipse.passage.lic.api.internal.registry;

/**
 * Marker interface for {@linkplain Service} configuration - precise immutable
 * set of values of a {@code service} configuring parameters, if any.
 */
public interface Configuration {

	public static final class Empty implements Configuration {

	}

}
