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
package org.eclipse.passage.lic.internal.equinox;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.inspection.RuntimeEnvironment;

public final class Environments extends FrameworkAware implements Supplier<Collection<RuntimeEnvironment>> {

	@Override
	public Collection<RuntimeEnvironment> get() {
		return withFramework(this::environments).orElseGet(Collections::emptySet);
	}

	private Collection<RuntimeEnvironment> environments(Framework framework) {
		return framework.accessCycleConfiguration().environments().get().services();
	}

}
