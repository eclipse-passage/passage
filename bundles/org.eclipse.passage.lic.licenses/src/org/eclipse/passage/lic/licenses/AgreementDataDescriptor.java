/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.licenses;

/**
 * @since 2.1
 */
public interface AgreementDataDescriptor {

	String getIdentifier();

	String getName();

	String getFile();

	String getHashAlgo();

	String getHash();

	byte[] getContent();

	String getContentType();
}