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
package org.eclipse.passage.lic.jface.viewers;

import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_DEFAULT;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_ERROR;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_FATAL;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_INFO;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_WARN;
import static org.eclipse.passage.lic.jface.resource.LicensingColors.COLOR_LEVEL_ERROR;
import static org.eclipse.passage.lic.jface.resource.LicensingColors.COLOR_LEVEL_FATAL;
import static org.eclipse.passage.lic.jface.resource.LicensingColors.COLOR_LEVEL_INFO;
import static org.eclipse.passage.lic.jface.resource.LicensingColors.COLOR_LEVEL_WARN;
import static org.eclipse.passage.lic.jface.resource.LicensingImages.IMG_LEVEL_ERROR;
import static org.eclipse.passage.lic.jface.resource.LicensingImages.IMG_LEVEL_FATAL;
import static org.eclipse.passage.lic.jface.resource.LicensingImages.IMG_LEVEL_INFO;
import static org.eclipse.passage.lic.jface.resource.LicensingImages.IMG_LEVEL_OK;
import static org.eclipse.passage.lic.jface.resource.LicensingImages.IMG_LEVEL_WARN;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.passage.lic.api.restrictions.RestrictionVerdict;
import org.eclipse.passage.lic.base.restrictions.RestrictionVerdicts;
import org.eclipse.passage.lic.equinox.LicensingEquinox;
import org.eclipse.passage.lic.jface.resource.LicensingColors;
import org.eclipse.swt.graphics.RGB;

public class RestrictionRepresenters {

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
		case LICENSING_RESTRICTION_LEVEL_INFO:
			return IMG_LEVEL_INFO;
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

	public static String resolveColorKey(String level) {
		String restriction = level;
		if (restriction == null) {
			restriction = LICENSING_RESTRICTION_LEVEL_DEFAULT;
		}
		switch (restriction) {
		case LICENSING_RESTRICTION_LEVEL_INFO:
			return COLOR_LEVEL_INFO;
		case LICENSING_RESTRICTION_LEVEL_WARN:
			return COLOR_LEVEL_WARN;
		case LICENSING_RESTRICTION_LEVEL_ERROR:
			return COLOR_LEVEL_ERROR;
		case LICENSING_RESTRICTION_LEVEL_FATAL:
			return COLOR_LEVEL_FATAL;
		default:
			return COLOR_LEVEL_WARN;
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
		ColorRegistry colorRegistry = LicensingColors.getColorRegistry();
		switch (restriction) {
		case LICENSING_RESTRICTION_LEVEL_INFO:
			return colorRegistry.get(COLOR_LEVEL_INFO).getRGB();
		case LICENSING_RESTRICTION_LEVEL_WARN:
			return colorRegistry.get(COLOR_LEVEL_WARN).getRGB();
		case LICENSING_RESTRICTION_LEVEL_ERROR:
			return colorRegistry.get(COLOR_LEVEL_ERROR).getRGB();
		case LICENSING_RESTRICTION_LEVEL_FATAL:
			return colorRegistry.get(COLOR_LEVEL_FATAL).getRGB();
		default:
			return colorRegistry.get(COLOR_LEVEL_WARN).getRGB();
		}
	}

	public static String resolveSummary(Iterable<RestrictionVerdict> verdicts) {
		RestrictionRepresenter representer = LicensingEquinox.getLicensingService(RestrictionRepresenter.class);
		if (representer == null) {
			representer = new BaseRestrictionRepresenter();
		}
		return representer.getSummary(verdicts);
	}

	public static String resolveSummary(RestrictionVerdict verdict) {
		RestrictionRepresenter representer = LicensingEquinox.getLicensingService(RestrictionRepresenter.class);
		if (representer == null) {
			representer = new BaseRestrictionRepresenter();
		}
		return representer.getSummary(verdict);
	}

}
