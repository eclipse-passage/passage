/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.base.restrictions;

import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_ERROR;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_FATAL;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_INFO;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_WARN;

import java.util.Comparator;

import org.eclipse.passage.lic.api.restrictions.RestrictionVerdict;

public class RestrictionVerdictComparator implements Comparator<RestrictionVerdict> {

	private static final Integer VALUE_INFO = Integer.valueOf(1);
	private static final Integer VALUE_WARN = Integer.valueOf(2);
	private static final Integer VALUE_ERROR = Integer.valueOf(4);
	private static final Integer VALUE_FATAL = Integer.valueOf(8);
	private static final Integer VALUE_DEFAULT = VALUE_WARN;

	@Override
	public int compare(RestrictionVerdict o1, RestrictionVerdict o2) {
		if (o1 == null) {
			if (o2 == null) {
				return 0;
			}
			return -1;
		}
		if (o2 == null) {
			return 1;
		}
		String level1 = o1.getRestrictionLevel();
		String level2 = o2.getRestrictionLevel();
		return compareLevels(level1, level2);
	}

	public static int compareLevels(String level1, String level2) {
		Integer resolved1 = resolveLevel(level1);
		Integer resolved2 = resolveLevel(level2);
		return resolved1.compareTo(resolved2);
	}

	private static Integer resolveLevel(String level) {
		if (level == null) {
			return VALUE_DEFAULT;
		}
		switch (level) {
		case LICENSING_RESTRICTION_LEVEL_INFO:
			return VALUE_INFO;
		case LICENSING_RESTRICTION_LEVEL_WARN:
			return VALUE_WARN;
		case LICENSING_RESTRICTION_LEVEL_ERROR:
			return VALUE_ERROR;
		case LICENSING_RESTRICTION_LEVEL_FATAL:
			return VALUE_FATAL;
		default:
			return VALUE_DEFAULT;
		}
	}
}
