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
package org.eclipse.passage.loc.dashboard.ui.wizards.license;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.loc.internal.workbench.MandatoryEclipseContext;
import org.eclipse.passage.loc.workbench.viewers.DomainRegistryLabelProvider;

public final class SwitchableFields implements Fields {

	private final Fields dependants;
	private final Field<Boolean> switcher;
	private MandatoryService context;
	private LabelProvider labels;

	SwitchableFields(IEclipseContext context, String name, boolean value, Fields dependants) {
		this.context = new MandatoryEclipseContext(context);
		this.labels = new DomainRegistryLabelProvider();
		this.dependants = dependants;
		this.switcher = new SwitchField(name, value, this::modified, labels, this.context);
	}

	@Override
	public List<Field<?>> fields() {
		return Collections.singletonList(switcher);
	}

	@Override
	public final void modified() {
		enableDependants();
		dependants.modified();
	}

	public Supplier<Optional<Boolean>> switcher() {
		return switcher::data;
	}

	private void enableDependants() {
		Boolean enable = switcher.data().get();
		dependants.fields().forEach(field -> field.enable(enable));
	}

}
