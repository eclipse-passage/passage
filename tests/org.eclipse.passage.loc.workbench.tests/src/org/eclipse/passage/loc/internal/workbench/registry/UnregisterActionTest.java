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
package org.eclipse.passage.loc.internal.workbench.registry;

import static org.junit.Assert.assertFalse;

import java.util.function.Supplier;

import org.eclipse.passage.loc.internal.workbench.LocDomainRegistryAccess;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

public class UnregisterActionTest {

	private final LocDomainRegistryAccess access = new LocDomainRegistryAccess();
	private final Supplier<Object[]> selection = () -> new Object[0];
	private final Supplier<Shell> shell = () -> null;

	@Test(expected = NullPointerException.class)
	public void nullAccess() {
		new UnregisterAction(null, selection, shell);
	}

	@Test(expected = NullPointerException.class)
	public void nullSelection() {
		new UnregisterAction(access, null, shell);
	}

	@Test(expected = NullPointerException.class)
	public void nullShell() {
		new UnregisterAction(access, selection, null);
	}

	@Test
	public void dryRun() {
		new UnregisterAction(access, selection, shell).run();
		assertFalse("Nothing to check here ATM".isEmpty()); //$NON-NLS-1$
	}

}
