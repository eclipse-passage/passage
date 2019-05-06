/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.e4.core.commands;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.contexts.IEclipseContext;

/**
 * Provides utilities to work with commands
 * 
 * @see
 * @since 0.5.0
 */
@SuppressWarnings("restriction")
public final class E4CoreCommands {

	private E4CoreCommands() {
	}

	/**
	 * Executes the command with with single parameter.
	 * 
	 * @param context        {@link IEclipseContext} to obtain the services
	 * @param commandId      {@link Command} identifier
	 * @param parameterId    the parameter identifier
	 * @param parameterValue the parameter value
	 * @return The result of the execution; may be null.
	 */
	public static Object executeCommand(IEclipseContext context, String commandId, String parameterId,
			String parameterValue) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(parameterId, parameterValue);
		return executeCommand(context, commandId, parameters);
	}

	/**
	 * Executes the command with with its parameters.
	 * 
	 * @param context    {@link IEclipseContext} to obtain the services
	 * @param commandId  {@link Command} identifier
	 * @param parameters A map of String parameter ids to objects. May be null.
	 * @return The result of the execution; may be null.
	 */
	public static Object executeCommand(IEclipseContext context, String commandId, Map<String, Object> parameters) {
		ECommandService commandService = context.get(ECommandService.class);
		Command command = commandService.getCommand(commandId);
		ParameterizedCommand parametrizedCommand = ParameterizedCommand.generateCommand(command, parameters);
		EHandlerService eHandlerService = context.get(EHandlerService.class);
		return eHandlerService.executeHandler(parametrizedCommand);
	}

}
