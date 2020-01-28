/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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

package org.eclipse.passage.lic.email;

/**
 * E-Mail Descriptor
 *
 * @since 0.1
 *
 */
public interface EmailDescriptor {

	String getTo();

	String getFrom();

	String getSubject();

	String getBody();

	Iterable<String> getAttachmentPaths();
}
