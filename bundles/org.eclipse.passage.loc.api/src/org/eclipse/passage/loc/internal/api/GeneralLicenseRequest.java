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
package org.eclipse.passage.loc.internal.api;

import java.util.Date;

/**
 * Collects common data required to issue a license of any type
 */
public interface GeneralLicenseRequest {

	/**
	 * Returns the identifier of this licensing request. This is the value of its
	 * <code>"identifier"</code> attribute.
	 */
	String identifier();

	/**
	 * Returns the creation date of this licensing request. This is the value of its
	 * <code>"creationDate"</code> attribute.
	 */
	Date creationDate();

	/**
	 * Returns the <code>"Product"</code> identifier of this licensing request. This
	 * is the value of its <code>"productIdentifier"</code> attribute.
	 */
	String productIdentifier();

	/**
	 * Returns the <code>"Product Version"</code> version of this licensing request.
	 * This is the value of its <code>"productVersion"</code> attribute.
	 */
	String productVersion();

	/**
	 * Returns the <code>"License Plan"</code> identifier of this licensing request.
	 * This is the value of its <code>"planIdentifier"</code> attribute.
	 */
	String plan();

	/**
	 * Returns the validity period start date of this licensing request. This is the
	 * value of its <code>"validFrom"</code> attribute.
	 */
	Date validFrom();

	/**
	 * Returns the validity period end date of this licensing request. This is the
	 * value of its <code>"validUntil"</code> attribute.
	 */
	Date validUntil();

}
