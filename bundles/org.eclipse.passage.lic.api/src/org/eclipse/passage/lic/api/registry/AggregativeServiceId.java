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
package org.eclipse.passage.lic.api.registry;

import java.util.Objects;

/**
 * @since 2.1
 */
public final class AggregativeServiceId<I1 extends ServiceId, I2 extends ServiceId> implements ServiceId {

	private final I1 first;
	private final I2 second;

	public AggregativeServiceId(I1 first, I2 second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public boolean equals(Object object) {
		if (!AggregativeServiceId.class.isInstance(object)) {
			return false;
		}
		@SuppressWarnings("rawtypes")
		AggregativeServiceId another = (AggregativeServiceId) object;
		return first.equals(another.first) && second.equals(another.second);
	}

	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

	@Override
	public String toString() {
		return String.format("%s/%s", first, second); //$NON-NLS-1$
	}

}
