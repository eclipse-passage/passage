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
package org.eclipse.passage.lbc.base.actions;


import static org.eclipse.passage.lic.net.LicensingRequests.PRODUCT;
import static org.eclipse.passage.lic.net.LicensingRequests.VERSION;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.net.LicensingRequests;
import org.eclipse.passage.lic.runtime.ConditionMiner;
import org.eclipse.passage.lic.runtime.LicensingCondition;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.io.LicensingConditionTransport;

import org.eclipse.passage.lbc.base.BaseComponent;
import org.eclipse.passage.lbc.runtime.ServerRequestAction;

/**
 * According to AccessManager specification implementation of
 * {@code Iterable<ConditionDescriptor> extractConditions(Object configuration)}
 * {@link org.eclipse.passage.lic.runtime.AccessManager}
 */
public class ConditionDescriptorRequestAction extends BaseComponent implements ServerRequestAction {

	private static final String SERVER_MINER_TYPE = "server.miner"; // NLS-$1
	private static final String APPLICATION_JSON = "application/json"; // NLS-$1
	private static final String LICENSING_CONTENT_TYPE = "licensing.content.type"; // NLS-$1
	private static final String MINER_TYPE_KEY = "miner.type";// NLS-$1

	private List<ConditionMiner> licenseConditionMiners = new ArrayList<>();
	private Map<String, LicensingConditionTransport> mapCondition2Transport = new HashMap<>();

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		if (logger == null) {
			return false;
		}
		logger.info(String.format("Executing action request from class: %s", this.getClass().getName()));
		if (licenseConditionMiners.isEmpty()) {
			logger.error("No condition miners available");
			return false;
		}
		try {
			String productId = request.getParameter(PRODUCT);
			String productVersion = request.getParameter(VERSION);
			LicensingConfiguration configuration = LicensingConfigurations.create(productId, productVersion);

			Collection<LicensingCondition> resultConditions = new ArrayList<>();

			for (ConditionMiner miner : licenseConditionMiners) {
				Iterable<LicensingCondition> descriptors = miner.extractLicensingConditions(configuration);

				resultConditions.addAll((Collection<? extends LicensingCondition>) descriptors);
			}
			String contentType = request.getParameter(LicensingRequests.CONTENT_TYPE);
			LicensingConditionTransport transport = mapCondition2Transport.get(contentType);
			if (transport == null) {
				logger.error(String.format("LicensingConditionTransport not defined for contentType: %s", contentType));
				return false;
			}

			transport.writeConditionDescriptors(resultConditions, response.getOutputStream());
			response.setContentType(APPLICATION_JSON);

			return true;
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	public void bindConditionMiner(ConditionMiner conditionMiner, Map<String, String> context) {

		String minerType = context.get(MINER_TYPE_KEY);
		if (minerType != null && minerType.equals(SERVER_MINER_TYPE)) {
			this.licenseConditionMiners.add(conditionMiner);
		}
	}

	public void unbindConditionMiner(ConditionMiner conditionMiner, Map<String, String> context) {
		this.licenseConditionMiners.remove(conditionMiner);
	}

	public void bindLicensingConditionTransport(LicensingConditionTransport transport, Map<String, String> context) {
		String conditionType = context.get(LICENSING_CONTENT_TYPE);
		if (conditionType != null) {
			mapCondition2Transport.put(conditionType, transport);
		}
	}

	public void unbindLicensingConditionTransport(LicensingConditionTransport transport, Map<String, String> context) {
		String conditionType = context.get(LICENSING_CONTENT_TYPE);
		if (conditionType != null) {
			mapCondition2Transport.remove(conditionType, transport);
		}
	}
}
