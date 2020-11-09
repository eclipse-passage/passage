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
import org.eclipse.passage.lbc.internal.api.BackendRequestDispatcher;
import org.eclipse.passage.lbc.internal.api.chains.Chain;
import org.eclipse.passage.lbc.internal.base.BaseLicensingRequest;
import org.eclipse.passage.lbc.internal.base.BaseLicensingResponse;
import org.eclipse.passage.lbc.internal.base.BaseRequestDispatcher;
import org.eclipse.passage.lbc.internal.base.ReleaseReport;
import org.eclipse.passage.lbc.internal.base.chains.Acquire;
import org.eclipse.passage.lbc.internal.base.chains.CanTake;
import org.eclipse.passage.lbc.internal.base.chains.Operation;
import org.eclipse.passage.lbc.internal.base.chains.Release;
import org.eclipse.passage.lbc.internal.base.persistence.LockFolder;
import org.eclipse.passage.lbc.json.JsonDeserialization;
import org.eclipse.passage.lbc.json.JsonLoadedLicense;
import org.eclipse.passage.lbc.json.JsonSerialization;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;

@SuppressWarnings("restriction")
public final class JettyHandler extends AbstractHandler {

	private final BackendRequestDispatcher dispatcher;
	private final LockFolder lock;

	public JettyHandler() {
		lock = new LockFolder();
		dispatcher = new BaseRequestDispatcher(chains());

	}

	private Map<String, Chain> chains() {
		return Arrays.asList(acquire(), canTake(), release()) //
				.stream() //
				.collect(Collectors.toMap(this::key, Function.identity()));
	}

	private Acquire acquire() {
		return new Acquire(new JsonDeserialization<>(ExaminationCertificate.class),
				new JsonSerialization<ExaminationCertificate>(), new JsonLoadedLicense(lock));
	}

	private CanTake canTake() {
		return new CanTake(new JsonDeserialization<>(Condition.class), new JsonLoadedLicense(lock));
	}

	private Release release() {
		return new Release(new JsonDeserialization<>(ExaminationCertificate.class),
				new JsonSerialization<ReleaseReport>(), new JsonLoadedLicense(lock));
	}

	@Override
	public void handle(String target, Request request, HttpServletRequest wrapper, HttpServletResponse response)
			throws IOException, ServletException {
		dispatcher.dispatch(new BaseLicensingRequest(wrapper), new BaseLicensingResponse(response));
	}

	private String key(Operation<?, ?> operation) {
		return operation.action().get().get().name();
	}

}
