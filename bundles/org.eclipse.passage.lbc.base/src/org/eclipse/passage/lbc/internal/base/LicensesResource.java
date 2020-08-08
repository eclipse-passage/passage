/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lbc.internal.base;

import java.util.function.Function;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;

/**
 * @since 1.0
 */
public final class LicensesResource implements Function<LicensePack, Resource> {

	@Override
	public Resource apply(LicensePack t) {
		Resource resource = new XMIResourceImpl();
		resource.getContents().add(t);
		return resource;
	}

}
