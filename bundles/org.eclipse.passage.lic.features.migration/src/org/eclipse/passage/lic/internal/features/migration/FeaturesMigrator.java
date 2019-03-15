/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.internal.features.migration;

import org.eclipse.emf.ecore.EPackage;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true)
public class FeaturesMigrator {

	@Activate
	public void activate() {
		EPackage.Registry.INSTANCE.get("http://www.eclipse.org/passage/lic/0.3.3"); //$NON-NLS-1$
	}

}
