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
package org.eclipse.passage.lbc.base.condition;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.passage.lic.runtime.LicensingCondition;

public class ServerConditionsArbitr {

	List<LicensingCondition> reservedConditions = new ArrayList<>();
	List<LicensingCondition> leaseConditions = new ArrayList<>();
	List<ConditionTimerTask> reservedConditionTasks = new ArrayList<>();
	List<ConditionTimerTask> leasedConditionTasks = new ArrayList<>();
	Logger logger = Logger.getLogger(ServerConditionsArbitr.class.getName());

	public boolean addConditionToReserv(LicensingCondition condition) {
		boolean isReserved = false;
		if (leaseConditions.contains(condition)) {
			logger.info("Condition in lease");
			return isReserved;
		}
		if (reservedConditions.contains(condition)) {
			logger.info("Condition in reserv");
			return isReserved;
		}
		isReserved = createTaskForReserving(condition);
		if (isReserved) {
			isReserved = reservedConditions.add(condition);
		}
		return isReserved;

	}

	public boolean addConditionToLease(LicensingCondition condition) {
		boolean isLeased = false;
		if (leaseConditions.contains(condition)) {
			logger.info("Condition already in lease");
			return isLeased;
		}
		if (reservedConditions.contains(condition)) {
			isLeased = createTaskForLeasing(condition);
			if (isLeased) {
				isLeased = leaseConditions.add(condition);
			}
		} else {
			logger.info("Condition was not reserved");
		}
		return isLeased;
	}

	private boolean createTaskForLeasing(LicensingCondition condition) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nowPlusDay = now.plusDays(1l);
		ConditionTimerTask task = new ConditionTimerTask(nowPlusDay.toString()) {

			@Override
			void timeExpired() {
				leasedConditionTasks.remove(this);
				leaseConditions.remove(condition);

			}
		};
		return leasedConditionTasks.add(task);

	}

	private boolean createTaskForReserving(LicensingCondition condition) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nowPlusMinute = now.plusMinutes(1l);

		ConditionTimerTask task = new ConditionTimerTask(nowPlusMinute.toString()) {

			@Override
			void timeExpired() {
				reservedConditionTasks.remove(this);
				reservedConditions.remove(condition);
			}
		};
		return reservedConditionTasks.add(task);
	}

	public LicensingCondition checkoutReservedCondition(LicensingCondition condition) {
		if (reservedConditions.contains(condition)) {
			if (reservedConditions.remove(condition)) {
				leaseConditions.add(condition);
				return condition;
			}
		}
		return null;
	}
}
