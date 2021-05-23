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
package org.eclipse.passage.lic.emf.migration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;

/**
 * 
 * @since 2.0
 *
 */
public final class SimpleAttributeRoute implements EAttributeRoute {

	private final EAttribute destination;
	private final List<EReference> path;

	public SimpleAttributeRoute(EAttribute destination) {
		this(destination, Collections.emptyList());
	}

	public SimpleAttributeRoute(EAttribute destination, EReference... path) {
		this(destination, Arrays.asList(path));
	}

	public SimpleAttributeRoute(EAttribute destination, List<EReference> path) {
		Objects.requireNonNull(destination, "SimpleAttributePath::destination"); //$NON-NLS-1$
		Objects.requireNonNull(path, "SimpleAttributePath::path"); //$NON-NLS-1$
		this.destination = destination;
		this.path = new ArrayList<>(path);
	}

	@Override
	public EAttribute destination() {
		return destination;
	}

	@Override
	public List<EReference> path() {
		return new ArrayList<>(path);
	}

}
