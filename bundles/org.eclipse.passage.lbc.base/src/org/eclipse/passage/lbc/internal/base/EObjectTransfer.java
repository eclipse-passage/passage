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

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.passage.lbc.internal.api.FloatingResponse;

public final class EObjectTransfer implements FloatingResponse {

	private final EObject payload;

	public EObjectTransfer(EObject payload) {
		this.payload = payload;
	}

	@Override
	public boolean failed() {
		return false;
	}

	@Override
	public Error error() {
		throw new IllegalStateException("Successful response does not posess error information"); //$NON-NLS-1$ // dev
	}

	@Override
	public void write(OutputStream output) throws IOException {
		Resource resource = new XMIResourceImpl();
		resource.getContents().add(payload);
		resource.save(output, Collections.emptyMap());
	}

}
