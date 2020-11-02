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
package org.eclipse.passage.lbc.internal.jetty;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.passage.lbc.chains.Acquire;
import org.eclipse.passage.lbc.chains.CanTake;
import org.eclipse.passage.lbc.chains.Operation;
import org.eclipse.passage.lbc.chains.Release;
import org.eclipse.passage.lbc.internal.api.BackendRequestDispatcher;
import org.eclipse.passage.lbc.internal.api.Chain;
import org.eclipse.passage.lbc.internal.base.BackendAction;
import org.eclipse.passage.lbc.internal.base.BaseLicensingRequest;
import org.eclipse.passage.lbc.internal.base.BaseLicensingResponse;
import org.eclipse.passage.lbc.internal.base.BaseRequestDispatcher;
import org.eclipse.passage.lbc.internal.base.persistence.LockFolder;

@SuppressWarnings("restriction")
public final class JettyHandler extends AbstractHandler {

	private final BackendRequestDispatcher dispatcher;

	public JettyHandler() {
		dispatcher = new BaseRequestDispatcher(chains());
	}

	private Map<BackendAction, Chain> chains() {
		return Arrays
				.asList(new Acquire(new LockFolder()), new CanTake(new LockFolder()), new Release(new LockFolder())) //
				.stream() //
				.collect(Collectors.toMap(Operation::action, Function.identity()));
	}

	@Override
	public void handle(String target, Request request, HttpServletRequest wrapper, HttpServletResponse response)
			throws IOException, ServletException {
		dispatcher.dispatch(new BaseLicensingRequest(wrapper), new BaseLicensingResponse(response));
	}

}
