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

import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_DEFAULT;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_ERROR;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_FATAL;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_WARN;
import static org.eclipse.passage.lic.jface.resource.LicensingColors.COLOR_LEVEL_ERROR;
import static org.eclipse.passage.lic.jface.resource.LicensingColors.COLOR_LEVEL_FATAL;
import static org.eclipse.passage.lic.jface.resource.LicensingColors.COLOR_LEVEL_OK;
import static org.eclipse.passage.lic.jface.resource.LicensingColors.COLOR_LEVEL_WARN;
import static org.eclipse.passage.lic.jface.resource.LicensingImages.IMG_LEVEL_ERROR;
import static org.eclipse.passage.lic.jface.resource.LicensingImages.IMG_LEVEL_FATAL;
import static org.eclipse.passage.lic.jface.resource.LicensingImages.IMG_LEVEL_OK;
import static org.eclipse.passage.lic.jface.resource.LicensingImages.IMG_LEVEL_WARN;

import org.eclipse.passage.lic.base.restrictions.RestrictionVerdicts;
import org.eclipse.passage.lic.jface.resource.LicensingColors;
import org.eclipse.passage.lic.runtime.RestrictionVerdict;
import org.eclipse.swt.graphics.RGB;

public class RestrictionLabels {

	public static String resolveImageKey(Iterable<RestrictionVerdict> verdicts) {
		RestrictionVerdict last = RestrictionVerdicts.resolveLastVerdict(verdicts);
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

	public static String resolveColorKey(Iterable<RestrictionVerdict> verdicts) {
		RestrictionVerdict last = RestrictionVerdicts.resolveLastVerdict(verdicts);
		return resolveColorKey(last);
	}

	public static String resolveColorKey(RestrictionVerdict verdict) {
		if (verdict == null) {
			return COLOR_LEVEL_OK;
		}
		return resolveColorKey(verdict.getRestrictionLevel());
	}

	public static String resolveColorKey(String level) {
		String restriction = level;
		if (restriction == null) {
			restriction = LICENSING_RESTRICTION_LEVEL_DEFAULT;
		}
		switch (restriction) {
		case LICENSING_RESTRICTION_LEVEL_WARN:
			return COLOR_LEVEL_WARN;
		case LICENSING_RESTRICTION_LEVEL_ERROR:
			return COLOR_LEVEL_ERROR;
		case LICENSING_RESTRICTION_LEVEL_FATAL:
			return COLOR_LEVEL_FATAL;
		default:
			return IMG_LEVEL_WARN;
		}
	}

	public static String resolveLabel(Iterable<RestrictionVerdict> verdicts) {
		RestrictionVerdict last = RestrictionVerdicts.resolveLastVerdict(verdicts);
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

	public static RGB resolveRGB(Iterable<RestrictionVerdict> verdicts) {
		RestrictionVerdict last = RestrictionVerdicts.resolveLastVerdict(verdicts);
		return resolveRGB(last);
	}

	public static RGB resolveRGB(RestrictionVerdict verdict) {
		if (verdict == null) {
			return LicensingColors.RGB_LEVEL_OK;
		}
		return resolveRGB(verdict.getRestrictionLevel());
	}

	public static RGB resolveRGB(String level) {
		String restriction = level;
		if (restriction == null) {
			restriction = LICENSING_RESTRICTION_LEVEL_DEFAULT;
		}
		switch (restriction) {
		case LICENSING_RESTRICTION_LEVEL_WARN:
			return LicensingColors.RGB_LEVEL_WARN;
		case LICENSING_RESTRICTION_LEVEL_ERROR:
			return LicensingColors.RGB_LEVEL_ERROR;
		case LICENSING_RESTRICTION_LEVEL_FATAL:
			return LicensingColors.RGB_LEVEL_FATAL;
		default:
			return LicensingColors.RGB_LEVEL_WARN;
		}
	}

	public static String resolveSummary(RestrictionVerdict verdict) {
		if (verdict == null) {
			return "The product in licensed properly";
		}
		return "There are issues with licensing";
	}

}
