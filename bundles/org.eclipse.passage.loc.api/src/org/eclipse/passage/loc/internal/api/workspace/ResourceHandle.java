/*******************************************************************************
 * Copyright (c) 2021, 2022 ArSysOp
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
package org.eclipse.passage.loc.internal.api.workspace;

public interface ResourceHandle {

	/**
	 * Tell a client where the content is hosted (path to a file, for instance)
	 */
	String info();

	ResourceType type();

	void write(byte[] content) throws Exception;

	byte[] content() throws Exception;

	// FIXME: AF: find better solution
	String uri();

}
