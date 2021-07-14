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
package org.eclipse.passage.lic.base.conditions.evaluation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionProtocol;
import org.eclipse.passage.lic.api.conditions.evaluation.ParsedExpression;

/**
 * @since 2.1
 */
public final class SimpleMapExpression implements ParsedExpression {

	private final Map<String, String> checks;
	private final ExpressionProtocol protocol;

	public SimpleMapExpression(ExpressionProtocol protocol, Map<String, String> checks) {
		Objects.requireNonNull(protocol);
		Objects.requireNonNull(checks);
		this.protocol = protocol;
		this.checks = new HashMap<>(checks);
	}

	@Override
	public ExpressionProtocol protocol() {
		return protocol;
	}

	public Collection<String> keys() {
		return checks.keySet();
	}

	public String expected(String key) {
		return checks.get(key);
	}

}
