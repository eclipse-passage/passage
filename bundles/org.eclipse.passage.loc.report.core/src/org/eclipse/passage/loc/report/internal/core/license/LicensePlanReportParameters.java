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
package org.eclipse.passage.loc.report.internal.core.license;

import java.util.Date;
import java.util.Set;

import org.eclipse.passage.loc.yars.internal.api.FetchParams;

/**
 * @since 0.2
 */
@SuppressWarnings("restriction")
public final class LicensePlanReportParameters implements FetchParams {

	private final Date from;
	private final Date to;
	private final Set<String> plans;
	private final boolean explain;

	public LicensePlanReportParameters(Set<String> plans, Date from, Date to, boolean explain) {
		this.from = from;
		this.to = to;
		this.plans = plans;
		this.explain = explain;
	}

	Set<String> plans() {
		return plans;
	}

	Date from() {
		return from;
	}

	Date to() {
		return to;
	}

	boolean explain() {
		return explain;
	}

}
