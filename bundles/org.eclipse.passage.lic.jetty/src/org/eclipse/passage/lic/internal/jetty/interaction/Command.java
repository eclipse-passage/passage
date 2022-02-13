/*******************************************************************************
 * Copyright (c) 2021, 2022 ArSysOp
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

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.base.diagnostic.NoErrors;
import org.osgi.framework.BundleContext;

public abstract class Command implements JettyCommands {

	protected final Scope scope;
	private final String[] names;

	protected Command(Scope scope, String[] names) {
		this.scope = scope;
		this.names = names;
	}

	protected Command(Scope scope, List<String> names) {
		this(scope, names.toArray(new String[names.size()]));
	}

	public final Scope scope() {
		return scope;
	}

	@Override
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

	@Override
	public final List<String> names() {
		return commands().stream() //
				.map(name -> scope.id() + ":" + name)// //$NON-NLS-1$
				.collect(Collectors.toList());
	}

	protected abstract List<String> commands();

	protected static abstract class Name implements Supplier<List<String>> {

		private final List<String> names;

		protected Name(String name) {
			this(Collections.singletonList(name));
		}

		protected Name(List<String> names) {
			this.names = names;
		}

		@Override
		public List<String> get() {
			return names;
		}

	}

}
