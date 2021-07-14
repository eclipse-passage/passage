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
package org.eclipse.passage.lic.base;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

/**
 * 
 * @since 2.1
 */
public final class SumOfMaps<K, V> implements BinaryOperator<Map<K, V>> {

	@Override
	public Map<K, V> apply(Map<K, V> first, Map<K, V> second) {
		Map<K, V> sum = new HashMap<>(first.size() + second.size());
		sum.putAll(first);
		sum.putAll(second);
		return sum;
	}

}
