/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.hc.remote;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.conditions.mining.ContentType;

/**
 * 
 * @since 1.1
 */
public interface Connection {

	void beGet() throws LicensingException;

	void bePost() throws LicensingException;

	void withTimeout(int timeout);

	void withProperty(String name, String value);

	void withPayload(byte[] payload) throws LicensingException;

	boolean successful() throws LicensingException;

	int code() throws LicensingException;

	String message() throws LicensingException;

	ContentType contentType() throws LicensingException;

	byte[] payload() throws LicensingException;

}
