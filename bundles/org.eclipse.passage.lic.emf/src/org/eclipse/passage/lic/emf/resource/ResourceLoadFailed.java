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
package org.eclipse.passage.lic.emf.resource;

import org.eclipse.passage.lic.api.diagnostic.TroubleCode;
import org.eclipse.passage.lic.internal.emf.i18n.EmfMessages;

/**
 * @since 2.0
 */
public final class ResourceLoadFailed extends TroubleCode {

	public ResourceLoadFailed() {
		super(899, EmfMessages.ResourceLoadFailed_explanation);
	}

}
