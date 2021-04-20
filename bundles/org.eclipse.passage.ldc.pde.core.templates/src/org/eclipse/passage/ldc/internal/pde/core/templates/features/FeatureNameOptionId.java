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
package org.eclipse.passage.ldc.internal.pde.core.templates.features;

import org.eclipse.passage.ldc.internal.pde.core.templates.OptionId;

public final class FeatureNameOptionId implements OptionId {

	@Override
	public String id() {
		return "featureName"; //$NON-NLS-1$
	}

}
