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
package org.eclipse.passage.lic.base.version;

import org.eclipse.passage.lic.api.version.SemanticVersion;

/**
 * 
 * @since 2.1
 */
public final class DefaultVersion extends BaseVersion {

	@Override
	public String value() {
		return "0.0.0"; //$NON-NLS-1$
	}

	@Override
	public SemanticVersion semantic() {
		return new BaseSemanticVersion(0, 0, 0);
	}

}
