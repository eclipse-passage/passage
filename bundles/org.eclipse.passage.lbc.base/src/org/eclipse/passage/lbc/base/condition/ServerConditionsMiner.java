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

import org.eclipse.passage.lbc.runtime.LicensingConditionStorage;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.conditions.ConditionMiner;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;;

@Component(service = ConditionMiner.class)
public class ServerConditionsMiner implements ConditionMiner {

	LoggerFactory factory;
	Logger logger;

	private final List<LicensingConditionStorage> conditionStorages = new ArrayList<>();

	@Reference
	void bindLogger(LoggerFactory loggerFactory) {
		this.factory = loggerFactory;
		this.logger = loggerFactory.getLogger(getClass());
	}

	void unbindLogger(LoggerFactory loggerFactory) {
		if (this.factory == loggerFactory) {
			this.factory = null;
			this.logger = null;
		}
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void bindLicensingConditionStorage(LicensingConditionStorage conditionStorage) {
		if (!conditionStorages.contains(conditionStorage)) {
			conditionStorages.add(conditionStorage);
		}
	}

	public void unbindLicensingConditionStorage(LicensingConditionStorage conditionStorage) {
		if (conditionStorages.contains(conditionStorage)) {
			conditionStorages.remove(conditionStorage);
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
			storage.getLicensingCondition(configuration).forEach(result::add);
		}
		return result;
	}
}
