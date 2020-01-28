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

import static org.eclipse.passage.lic.jface.resource.LicensingColors.COLOR_LEVEL_ERROR;
import static org.eclipse.passage.lic.jface.resource.LicensingColors.COLOR_LEVEL_FATAL;
import static org.eclipse.passage.lic.jface.resource.LicensingColors.COLOR_LEVEL_INFO;
import static org.eclipse.passage.lic.jface.resource.LicensingColors.COLOR_LEVEL_OK;
import static org.eclipse.passage.lic.jface.resource.LicensingColors.COLOR_LEVEL_WARN;
import static org.eclipse.passage.lic.jface.resource.LicensingImages.IMG_LEVEL_ERROR;
import static org.eclipse.passage.lic.jface.resource.LicensingImages.IMG_LEVEL_FATAL;
import static org.eclipse.passage.lic.jface.resource.LicensingImages.IMG_LEVEL_INFO;
import static org.eclipse.passage.lic.jface.resource.LicensingImages.IMG_LEVEL_OK;
import static org.eclipse.passage.lic.jface.resource.LicensingImages.IMG_LEVEL_WARN;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;
import org.eclipse.passage.lic.base.conditions.LicensingConditions;
import org.eclipse.passage.lic.jface.resource.LicensingColors;
import org.eclipse.swt.graphics.RGB;

public class ConditionRepresenters {

	private static String source = ConditionRepresenters.class.getName();

	public static String resolveImageKey(LicensingCondition condition) {
		LicensingResult result = LicensingConditions.validate(condition, source);
		switch (result.getSeverity()) {
		case LicensingResult.OK:
			return IMG_LEVEL_OK;
		case LicensingResult.INFO:
			return IMG_LEVEL_INFO;
		case LicensingResult.WARNING:
			return IMG_LEVEL_WARN;
		case LicensingResult.ERROR:
			return IMG_LEVEL_ERROR;
		case LicensingResult.CANCEL:
			return IMG_LEVEL_FATAL;
		default:
			return IMG_LEVEL_WARN;
		}
	}

	public static RGB resolveRGB(LicensingCondition condition) {
		LicensingResult result = LicensingConditions.validate(condition, source);
		ColorRegistry colorRegistry = LicensingColors.getColorRegistry();
		switch (result.getSeverity()) {
		case LicensingResult.OK:
			return colorRegistry.get(COLOR_LEVEL_OK).getRGB();
		case LicensingResult.INFO:
			return colorRegistry.get(COLOR_LEVEL_INFO).getRGB();
		case LicensingResult.WARNING:
			return colorRegistry.get(COLOR_LEVEL_WARN).getRGB();
		case LicensingResult.ERROR:
			return colorRegistry.get(COLOR_LEVEL_ERROR).getRGB();
		case LicensingResult.CANCEL:
			return colorRegistry.get(COLOR_LEVEL_FATAL).getRGB();
		default:
			return colorRegistry.get(COLOR_LEVEL_WARN).getRGB();
		}
	}

}
