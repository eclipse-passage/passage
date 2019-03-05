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
package org.eclipse.passage.lic.internal.oshi;

import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.base.conditions.BaseConditionEvaluator;
import org.eclipse.passage.lic.oshi.OshiHal;
import org.eclipse.passage.lic.runtime.ConditionEvaluator;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.log.LogService;

@Component(property = {
		LicensingProperties.LICENSING_CONDITION_TYPE + '=' + OshiHal.CONDITION_TYPE_HARDWARE })
public class OshiConditionEvaluator extends BaseConditionEvaluator implements ConditionEvaluator {

	private LogService logService;

	@Override
	protected boolean evaluateSegment(String key, String value) {
		return OshiHal.evaluateProperty(key, value);
	}

	@Reference
	public void bindLogService(LogService logService) {
		this.logService = logService;
	}
	
	public void unbindLogService(LogService logService) {
		this.logService = logService;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void logError(String message, Throwable e) {
		//FIXME: rework after removing Eclipse Mars support
		logService.log(LogService.LOG_ERROR, message, e);
	}

}
