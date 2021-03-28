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
package org.eclipse.passage.lic.internal.jetty.interaction;

import java.util.Hashtable;

import org.eclipse.passage.lic.internal.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.internal.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.internal.base.diagnostic.NoErrors;
import org.osgi.framework.BundleContext;

public abstract class Command {

	protected final Scope scope;
	private final String[] names;

	public Command(Scope scope, String[] names) {
		this.scope = scope;
		this.names = names;
	}

	public final void register(BundleContext context) {
		Hashtable<String, Object> properties = new Hashtable<>();
		properties.put("osgi.command.scope", scope.id());//$NON-NLS-1$
		properties.put("osgi.command.function", names);//$NON-NLS-1$
		context.registerService(getClass().getName(), this, properties);
	}

	protected final void reportDiagnostic(Diagnostic diagnostic) {
		if (new NoErrors().test(diagnostic)) {
			return;
		}
		System.out.printf("\n%s\n", new DiagnosticExplained(diagnostic).get()); //$NON-NLS-1$
	}

}
