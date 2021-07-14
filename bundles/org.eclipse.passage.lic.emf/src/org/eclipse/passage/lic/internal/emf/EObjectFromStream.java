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

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.internal.emf.i18n.EmfMessages;

public abstract class EObjectFromStream<T extends EObject> {

	private final Class<T> expected;
	private final Supplier<Resource> factory;

	public EObjectFromStream(Class<T> expected, Supplier<Resource> factory) {
		Objects.requireNonNull(expected, getClass().getSimpleName() + "::expected"); //$NON-NLS-1$
		Objects.requireNonNull(factory, getClass().getSimpleName() + "::factory"); //$NON-NLS-1$
		this.expected = expected;
		this.factory = factory;
	}

	public T get() throws LicensingException {
		return get(Collections.emptyMap());
	}

	public T get(Map<?, ?> options) throws LicensingException {
		return from(only(content(options)));
	}

	protected abstract InputStream stream() throws IOException;

	private List<EObject> content(Map<?, ?> options) throws LicensingException {
		Resource resource = factory.get();
		try (InputStream input = stream()) {
			resource.load(input, options);
		} catch (IOException e) {
			throw new LicensingException(EmfMessages.XmiToEObject_failed_xmi_read, e);
		}
		return resource.getContents();
	}

	private EObject only(List<EObject> contents) throws LicensingException {
		if (contents.size() != 1) {
			throw new LicensingException(String.format(EmfMessages.XmiToEObject_unexpected_amount, contents.size()));
		}
		return contents.get(0);
	}

	private T from(EObject only) throws LicensingException {
		if (!expected.isInstance(only)) {
			throw new LicensingException(String.format(EmfMessages.XmiToEObject_unexpected_type,
					only.getClass().getName(), expected.getName()));
		}
		return expected.cast(only);
	}

}
