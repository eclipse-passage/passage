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
package org.eclipse.passage.lic.base.conditions.mining;

import java.util.function.Function;

import org.eclipse.passage.lic.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.base.BaseNamedData;

/**
 * @since 2.1
 */
public final class LicensingContentType extends BaseNamedData<ContentType> {

	public LicensingContentType(Function<String, ContentType> retrieve) {
		super(retrieve);
	}

	public LicensingContentType(ContentType type) {
		super(key -> type);
	}

	@Override
	public String key() {
		return "licensing.content.type"; //$NON-NLS-1$
	}

}
