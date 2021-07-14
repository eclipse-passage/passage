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

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BinaryOperator;

/**
 * 
 * @since 2.1
 */
public final class SumOfCollections<E> implements BinaryOperator<Collection<E>> {

	@Override
	public Collection<E> apply(Collection<E> first, Collection<E> second) {
		ArrayList<E> sum = new ArrayList<>(first.size() + second.size());
		sum.addAll(first);
		sum.addAll(second);
		return sum;
	}

}
