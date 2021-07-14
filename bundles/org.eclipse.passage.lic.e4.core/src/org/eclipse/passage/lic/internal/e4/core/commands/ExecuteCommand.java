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
package org.eclipse.passage.lic.internal.e4.core.commands;

import java.util.Map;
import java.util.function.Function;

import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.contexts.IEclipseContext;

/**
 * Execute the command with the given id and parameters using the given context
 * 
 */
public class ExecuteCommand implements Function<Map<String, Object>, Object> {

	private String id;
	private IEclipseContext context;

	public ExecuteCommand(String id, IEclipseContext context) {
		this.id = id;
		this.context = context;
	}

	@Override
	public Object apply(Map<String, Object> parameters) {
		return context.get(EHandlerService.class).executeHandler(//
				ParameterizedCommand.generateCommand(//
						context.get(ECommandService.class).getCommand(id), //
						parameters));
	}

}
