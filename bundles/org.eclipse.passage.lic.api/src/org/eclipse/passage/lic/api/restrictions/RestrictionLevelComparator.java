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
package org.eclipse.passage.lic.api.restrictions;

import java.util.Comparator;

/**
 * 
 * @since 2.1
 */
public final class RestrictionLevelComparator implements Comparator<RestrictionLevel> {

	@Override
	public int compare(RestrictionLevel left, RestrictionLevel right) {
		return Integer.compare(weight(left), weight(right));
	}

	private int weight(RestrictionLevel level) {
		if (new RestrictionLevel.Info().equals(level)) {
			return 1;
		}
		if (new RestrictionLevel.Warning().equals(level)) {
			return 2;
		}
		if (new RestrictionLevel.Error().equals(level)) {
			return 3;
		}
		if (new RestrictionLevel.Fatal().equals(level)) {
			return 4;
		}
		return 10;
	}

}
