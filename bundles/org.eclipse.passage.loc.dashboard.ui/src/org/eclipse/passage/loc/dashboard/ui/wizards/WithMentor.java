/*******************************************************************************
 * Copyright (c) 2024 ArSysOp
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
package org.eclipse.passage.loc.dashboard.ui.wizards;

import java.util.Objects;
import java.util.Optional;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecp.view.spi.model.VViewModelProperties;
import org.eclipse.passage.loc.internal.api.LocObjectMentorProperty;
import org.eclipse.passage.loc.internal.api.LocObjectMentorshipService;

public final class WithMentor {

	private final EObject target;
	private final IEclipseContext context;

	public WithMentor(EObject target, IEclipseContext context) {
		this.target = Objects.requireNonNull(target);
		this.context = Objects.requireNonNull(context);
	}

	public void inProperties(VViewModelProperties properties) {
		mentor().ifPresent(mentor -> properties.addNonInheritableProperty(new LocObjectMentorProperty().get(), mentor));
	}

	private Optional<String> mentor() {
		return Optional.ofNullable(context.get(LocObjectMentorshipService.class))//
				.flatMap(service -> service.mentor(target));
	}
}
