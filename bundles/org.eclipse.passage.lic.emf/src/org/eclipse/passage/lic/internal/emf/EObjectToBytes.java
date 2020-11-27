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
package org.eclipse.passage.lic.internal.emf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.emf.i18n.EmfMessages;

public final class EObjectToBytes {

	private final EObject source;

	public EObjectToBytes(EObject source) {
		this.source = source;
	}

	public byte[] get() throws LicensingException {
		return get(Collections.emptyMap());
	}

	public byte[] get(Map<?, ?> options) throws LicensingException {
		XMIResourceImpl resource = new XMIResourceImpl();
		resource.getContents().add(source);
		try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
			resource.save(stream, options);
			return stream.toByteArray();
		} catch (IOException e) {
			throw new LicensingException(EmfMessages.EObjectToBytes_failure, e);
		}
	}

}
