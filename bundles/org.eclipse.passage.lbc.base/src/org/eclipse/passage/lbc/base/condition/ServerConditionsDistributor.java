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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.passage.lic.base.conditions.FeaturePermissions;
import org.eclipse.passage.lic.net.TimeConditions;
import org.eclipse.passage.lic.runtime.ConditionEvaluator;
import org.eclipse.passage.lic.runtime.ConditionMiner;
import org.eclipse.passage.lic.runtime.FeaturePermission;
import org.eclipse.passage.lic.runtime.LicensingCondition;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.LicensingException;
import org.eclipse.passage.lbc.base.BaseComponent;

public class ServerConditionsDistributor extends BaseComponent implements ConditionEvaluator {

	List<ConditionMiner> miners = new ArrayList<>();
	List<LicensingCondition> lockedConditions = new ArrayList<>();
	List<ConditionTimerTask> conditionTasks = new ArrayList<>();

	@Override
	public Iterable<FeaturePermission> evaluateConditions(Iterable<LicensingCondition> conditions, LicensingConfiguration configuration) {
		List<FeaturePermission> permissionsResult = new ArrayList<>();

		for (LicensingCondition condition : conditions) {
			boolean conditionExists = checkExistense(condition, configuration);
			if (conditionExists) {

				synchronized (condition) {

					// lease on time period
					if (condition.getConditionType() == TimeConditions.CONDITION_TYPE_TIME) {

						boolean conditionIsLocked = lockedConditions.contains(condition);
						if (!conditionIsLocked) {
							FeaturePermission createFeaturePermition = createFeaturePermission(condition, configuration);
							launchFeaturePermissionTask(condition, createFeaturePermition);
							lockCondition(condition);
							permissionsResult.add(createFeaturePermition);
						}
					}
				}

			}
		}
		return permissionsResult;
	}

	private void launchFeaturePermissionTask(LicensingCondition condition, FeaturePermission createFeaturePermition) {

		String conditionLeaseTime = condition.getConditionExpression();

		ConditionTimerTask task = new ConditionTimerTask(conditionLeaseTime) {

			@Override
			void timeExpired() {
				unlockCondition(condition);
			}
		};

		conditionTasks.add(task);

		task.run();
	}

	private synchronized void unlockCondition(LicensingCondition condition) {
		if (lockedConditions.contains(condition)) {
			lockedConditions.remove(condition);
		}
	}

	private synchronized void lockCondition(LicensingCondition condition) {
		lockedConditions.add(condition);
	}
	
	private FeaturePermission createFeaturePermission(LicensingCondition condition, LicensingConfiguration configuration) {
		long leaseTime = System.currentTimeMillis();
		long expireTime = leaseTime + 60 * 60 * 1000;
		Date lease = new Date(leaseTime);
		Date expire = new Date(expireTime);
		FeaturePermission permission = FeaturePermissions.create(condition, configuration, lease, expire);
		return permission;

	}

	private boolean checkExistense(LicensingCondition condition, LicensingConfiguration configuration) {
		for (ConditionMiner miner : miners) {
			try {
				Iterable<LicensingCondition> extracted = miner.extractLicensingConditions(configuration);
				for (LicensingCondition extractedCondition : extracted) {
					if (condition.equals(extractedCondition)) {
						return true;
					}
				}
			} catch (LicensingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public void bindConditionMiner(ConditionMiner miner) {
		if (!miners.contains(miner)) {
			miners.add(miner);
		}
	}

	public void unbindConditionMiner(ConditionMiner miner) {
		if (miners.contains(miner)) {
			miners.remove(miner);
		}
	}

}
