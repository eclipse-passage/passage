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
package org.eclipse.passage.lic.internal.base.conditions.evaluation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionProtocol;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ParsedExpression;

@SuppressWarnings("restriction")
final class SimpleMapExpression implements ParsedExpression {

	private final Map<String, String> checks;
	private final ExpressionProtocol format;

	SimpleMapExpression(ExpressionProtocol format, Map<String, String> checks) {
		Objects.requireNonNull(format);
		Objects.requireNonNull(checks);
		this.format = format;
		this.checks = new HashMap<>(checks);
	}

	@Override
	public ExpressionProtocol protocol() {
		return format;
	}

	public Collection<String> keys() {
		return checks.keySet();
	}

	public String expected(String key) {
		return checks.get(key);
	}

}
