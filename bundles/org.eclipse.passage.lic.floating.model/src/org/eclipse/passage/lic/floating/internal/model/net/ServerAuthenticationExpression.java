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
package org.eclipse.passage.lic.floating.internal.model.net;

import java.util.function.Function;

import org.eclipse.passage.lic.floating.model.api.FloatingServerConnection;
import org.eclipse.passage.lic.internal.base.StringNamedData;

public final class ServerAuthenticationExpression extends StringNamedData {

	public ServerAuthenticationExpression(String value) {
		super(value);
	}

	public ServerAuthenticationExpression(FloatingServerConnection server) {
		this(server.getAuthentication().getExpression());
	}

	public ServerAuthenticationExpression(Function<String, String> retrieve) {
		super(retrieve);
	}

	@Override
	public String key() {
		return "server.auth.expression"; //$NON-NLS-1$
	}

}