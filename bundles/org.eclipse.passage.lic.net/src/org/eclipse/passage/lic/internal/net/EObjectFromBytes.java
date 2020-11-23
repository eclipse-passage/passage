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
package org.eclipse.passage.lic.internal.net;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.net.i18n.NetMessages;

/**
 * Reads xmi content from raw byte array and retrieve the single root element of
 * the expected type. Throws {@code LicensingException} is case of any surprise.
 */
public final class EObjectFromBytes<T> {

	private final byte[] content;
	private final Class<T> cls;

	public EObjectFromBytes(byte[] content, Class<T> cls) {
		this.content = content;
		this.cls = cls;
	}

	public T get() throws LicensingException {
		return from(only(content()));
	}

	private List<EObject> content() throws LicensingException {
		Resource resource = new XMIResourceImpl();
		try (InputStream input = new ByteArrayInputStream(content)) {
			resource.load(input, Collections.emptyMap());
		} catch (IOException e) {
			throw new LicensingException(NetMessages.XmiToEObject_failed_xmi_read, e);
		}
		return resource.getContents();
	}

	private EObject only(List<EObject> contents) throws LicensingException {
		if (contents.size() != 1) {
			throw new LicensingException(String.format(NetMessages.XmiToEObject_unexpected_amount, contents.size()));
		}
		return contents.get(0);
	}

	private T from(EObject only) throws LicensingException {
		if (!cls.isInstance(only)) {
			throw new LicensingException(
					String.format(NetMessages.XmiToEObject_unexpected_type, only.eClass().getName()));
		}
		return cls.cast(only);
	}

}
