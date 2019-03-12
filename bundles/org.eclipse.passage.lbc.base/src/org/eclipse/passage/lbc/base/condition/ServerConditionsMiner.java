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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.conditions.ConditionMiner;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.eclipse.passage.lbc.base.BaseComponent;
import org.eclipse.passage.lbc.runtime.LicensingConditionStorage;;

public class ServerConditionsMiner extends BaseComponent implements ConditionMiner {
	
	private final List<LicensingConditionStorage> conditionStorages = new ArrayList<>();

	public boolean checkProductById(String productId) {
		return false;
	}

	public void bindLicensingConditionStorage(LicensingConditionStorage conditionStorage) {
		logger.debug(conditionStorage.getClass().getName());
		if (conditionStorage != null) {
			if (!conditionStorages.contains(conditionStorage)) {
				conditionStorages.add(conditionStorage);
			}

		}
	}

	public void unbindLicensingConditionStorage(LicensingConditionStorage conditionStorage) {
		logger.debug(conditionStorage.getClass().getName());

		if (conditionStorage != null) {
			if (conditionStorages.contains(conditionStorage)) {
				conditionStorages.remove(conditionStorage);
			}
		}
	}

	@Override
	public Iterable<LicensingCondition> extractLicensingConditions(LicensingConfiguration configuration) {

		List<LicensingCondition> result = new ArrayList<>();
		if (configuration == null) {
			logger.error("Licensing configuration not defined");
			return result;
		}
		String productIdentifier = configuration.getProductIdentifier();
		String productVersion = configuration.getProductVersion();
		if (productIdentifier == null || productIdentifier.isEmpty()) {
			logger.error("Product identifier not defined");
			return result;
		}
		if (productVersion == null || productVersion.isEmpty()) {
			logger.error("Product version not defined");
			return result;
		}
		for (LicensingConditionStorage storage : conditionStorages) {
			result.addAll(storage.getLicensingCondition(productIdentifier, productVersion));
		}
		return result;
	}
}
