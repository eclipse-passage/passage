/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.jface;

import static org.eclipse.passage.lic.base.LicensingProperties.*;
import static org.eclipse.passage.lic.jface.resource.LicensingImages.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.passage.lic.base.restrictions.RestrictionVerdictComparator;
import org.eclipse.passage.lic.runtime.RestrictionVerdict;

public class RestrictionVerdictLabels {
	
	public static String resolveImageKey(Iterable<RestrictionVerdict> verdicts) {
		RestrictionVerdict last = resolveLastVerdict(verdicts);
		return resolveImageKey(last);
	}

	public static String resolveImageKey(RestrictionVerdict verdict) {
		if (verdict == null) {
			return IMG_LEVEL_OK;
		}
		return resolveImageKey(verdict.getRestrictionLevel());
	}

	public static String resolveImageKey(String level) {
		String restriction = level;
		if (restriction == null) {
			restriction = LICENSING_RESTRICTION_LEVEL_DEFAULT;
		}
		switch (restriction) {
		case LICENSING_RESTRICTION_LEVEL_WARN:
			return IMG_LEVEL_WARN;
		case LICENSING_RESTRICTION_LEVEL_ERROR:
			return IMG_LEVEL_ERROR;
		case LICENSING_RESTRICTION_LEVEL_FATAL:
			return IMG_LEVEL_FATAL;
		default:
			return IMG_LEVEL_WARN;
		}
	}

	public static String resolveLabel(Iterable<RestrictionVerdict> verdicts) {
		RestrictionVerdict last = resolveLastVerdict(verdicts);
		return resolveLabel(last);
	}

	public static String resolveLabel(RestrictionVerdict verdict) {
		if (verdict == null) {
			return "OK";
		}
		return resolveLabel(verdict.getRestrictionLevel());
	}

	public static String resolveLabel(String level) {
		String restriction = level;
		if (restriction == null) {
			restriction = LICENSING_RESTRICTION_LEVEL_DEFAULT;
		}
		switch (restriction) {
		case LICENSING_RESTRICTION_LEVEL_WARN:
			return "Warning";
		case LICENSING_RESTRICTION_LEVEL_ERROR:
			return "Error";
		case LICENSING_RESTRICTION_LEVEL_FATAL:
			return "Fatal";
		default:
			return "Warning";
		}
	}

	public static String resolveSummary(RestrictionVerdict verdict) {
		if (verdict == null) {
			return "The product in licensed properly";
		}
		return "There are issues with licensing";
	}

	public static RestrictionVerdict resolveLastVerdict(Iterable<RestrictionVerdict> verdicts) {
		if (verdicts == null) {
			return null;
		}
		List<RestrictionVerdict> list = new ArrayList<>();
		verdicts.forEach(list::add);
		if (list.isEmpty()) {
			return null;
		}
		Collections.sort(list, new RestrictionVerdictComparator());
		RestrictionVerdict last = list.get(list.size()-1);
		return last;
	}

}
