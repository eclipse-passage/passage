/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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

public interface Features extends KnownResources {

	ResourceType xmi = new ResourceType() {

		@Override
		public String id() {
			return "features_xmi"; //$NON-NLS-1$
		}
	};

	ResourceType xmi033 = new ResourceType() {

		@Override
		public String id() {
			return "lic_features"; //$NON-NLS-1$
		}
	};

}
