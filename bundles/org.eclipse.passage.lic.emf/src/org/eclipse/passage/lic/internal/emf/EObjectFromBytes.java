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
package org.eclipse.passage.lic.internal.emf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

public class EObjectFromBytes<T extends EObject> extends EObjectFromStream<T> {

	private final byte[] content;

	public EObjectFromBytes(byte[] content, Class<T> expected, Supplier<Resource> factory) {
		super(expected, factory);
		Objects.requireNonNull(content, "EObjectFromBytes::content"); //$NON-NLS-1$
		this.content = content;

	}

	@Override
	protected InputStream stream() throws IOException {
		return new ByteArrayInputStream(content);
	}

}
