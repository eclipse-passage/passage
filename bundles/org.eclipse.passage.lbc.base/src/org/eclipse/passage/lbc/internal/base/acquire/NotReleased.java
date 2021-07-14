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
package org.eclipse.passage.lbc.internal.base.acquire;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.internal.net.handle.Failure;
import org.eclipse.passage.lic.licenses.model.api.GrantAcqisition;

/**
 * Public only for test purposes
 */
public final class NotReleased extends Failure {

	public NotReleased(LicensedProduct product, GrantAcqisition acqisition) {
		super(612, String.format("Failed to release grant %s acquisition for feature %s of product %s", //$NON-NLS-1$
				acqisition.getGrant(), acqisition.getFeature(), product));
	}

}