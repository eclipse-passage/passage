/*******************************************************************************
 * Copyright (c) 2018, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support
 *******************************************************************************/
package org.eclipse.passage.lic.internal.licenses.model.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.passage.lic.emf.resource.BlindResourceFactory;

@SuppressWarnings("restriction")
public final class LicensesResourceFactoryImpl extends ResourceFactoryImpl implements BlindResourceFactory {

	@Override
	public Resource createResource(URI uri) {
		return new LicensesResourceImpl(uri);
	}

	@Override
	public Resource createResource() {
		return new LicensesResourceImpl();
	}

}
