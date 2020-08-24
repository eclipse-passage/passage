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
package org.eclipse.passage.lbc.internal.base;

import org.eclipse.passage.lbc.internal.api.Requester;

/**
 * @since 1.0
 */
public final class BaseRequester implements Requester {

	private final String feature;
	private final String hardware;
	private final String process;

	public BaseRequester(String process, String hardware, String feature) {
		this.feature = feature;
		this.hardware = hardware;
		this.process = process;
	}

	@Override
	public String feature() {
		return feature;
	}

	@Override
	public String hardware() {
		return hardware;
	}

	@Override
	public String process() {
		return process;
	}

}
