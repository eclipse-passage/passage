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
package org.eclipse.passage.loc.dashboard.ui.wizards.license;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.e4.core.contexts.IEclipseContext;

public final class ComposedPage implements Supplier<LicenseDataPage> {

	private final String name;
	private final String description;
	private final IEclipseContext context;
	private final List<Fields> blocks = new ArrayList<>();
	private LicenseDataPage page;

	public ComposedPage(String name, String description, IEclipseContext context) {
		this.name = name;
		this.description = description;
		this.context = context;
	}

	public PageFields withBlock() {
		PageFields block = new PageFields(this::page, context);
		blocks.add(block);
		return block;
	}

	public SwitchableFields withSwitchableBlock(String label, boolean value, Fields dependants) {
		SwitchableFields block = new SwitchableFields(context, label, value, dependants);
		blocks.add(blocks.indexOf(dependants), block);
		return block;
	}

	@Override
	public LicenseDataPage get() {
		page = new LicenseDataPage(name, description, units());
		return page;
	}

	private List<Field<?>> units() {
		return blocks.stream()//
				.flatMap(block -> block.fields().stream())//
				.collect(Collectors.toList());
	}

	private LicenseDataPage page() {
		return page;
	}

}
