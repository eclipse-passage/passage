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
package org.eclipse.passage.lic.internal.net;

import org.eclipse.passage.lic.base.BaseConditionEvaluator;
import org.eclipse.passage.lic.net.TimeConditions;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.log.LogService;

public class NtpConditionEvaluator extends BaseConditionEvaluator {

	private LogService logService;

	@Override
	protected boolean evaluateSegment(String key, String value) {
		switch (key) {
		case TimeConditions.PROPERTY_LOCALTIMESTAMP:
			return TimeConditions.isFutureLocalDateTime(value);
		default:
			return false;
		}
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
