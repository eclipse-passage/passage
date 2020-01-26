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
package org.eclipse.passage.lbc.internal.equinox.conditions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.passage.lbc.api.conditions.ConditionArbiter;
import org.eclipse.passage.lbc.internal.equinox.i18n.EquinoxMessages;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;
import org.eclipse.passage.lic.base.LicensingResults;

public class ServerConditionArbiter implements ConditionArbiter {

	List<LicensingCondition> reservedConditions = new ArrayList<>();
	List<LicensingCondition> leaseConditions = new ArrayList<>();
	List<ConditionTimerTask> reservedConditionTasks = new ArrayList<>();
	List<ConditionTimerTask> leasedConditionTasks = new ArrayList<>();

	Logger logger = Logger.getLogger(ServerConditionArbiter.class.getName());

	public boolean addConditionToReserv(LicensingCondition condition) {
		boolean isReserved = false;
		if (leaseConditions.contains(condition)) {
			logger.info(EquinoxMessages.ServerConditionArbiter_i_lease);
			return isReserved;
		}
		if (reservedConditions.contains(condition)) {
			logger.info(EquinoxMessages.ServerConditionArbiter_i_reserv);
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
			logger.info(EquinoxMessages.ServerConditionArbiter_i_already_leased);
			return isLeased;
		}
		if (reservedConditions.contains(condition)) {
			isLeased = createTaskForLeasing(condition);
			if (isLeased) {
				isLeased = leaseConditions.add(condition);
			}
		} else {
			logger.info(EquinoxMessages.ServerConditionArbiter_i_not_reserved);
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

	@Override
	public LicensingResult acquireConditions(Iterable<LicensingCondition> conditions) {
		// TODO Auto-generated method stub
		return LicensingResults.createOK();
	}

	@Override
	public LicensingResult keepConditions(Iterable<LicensingCondition> conditions) {
		// TODO Auto-generated method stub
		return LicensingResults.createOK();
	}

	@Override
	public LicensingResult releaseConditions(Iterable<LicensingCondition> conditions) {
		// TODO Auto-generated method stub
		return LicensingResults.createOK();
	}
}
