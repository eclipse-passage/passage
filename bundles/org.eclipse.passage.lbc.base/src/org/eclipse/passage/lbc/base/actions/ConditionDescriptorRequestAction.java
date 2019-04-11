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

import org.eclipse.passage.lbc.base.BaseComponent;
import org.eclipse.passage.lbc.runtime.BackendActionExecutor;
import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.net.LicensingRequests;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.LicensingResult;
import org.eclipse.passage.lic.runtime.conditions.ConditionMiner;
import org.eclipse.passage.lic.runtime.conditions.ConditionTransport;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.log.LoggerFactory;

/**
 * According to AccessManager specification implementation of
 * {@code Iterable<ConditionDescriptor> extractConditions(Object configuration)}
 * {@link org.eclipse.passage.lic.runtime.access.AccessManager}
 */
@Component(property = LicensingRequests.ACTION + '=' + "extractConditions")
public class ConditionDescriptorRequestAction extends BaseComponent implements BackendActionExecutor {

	private static final String SERVER_MINER_TYPE = "server.miner"; // NLS-$1
	private static final String APPLICATION_JSON = "application/json"; // NLS-$1
	private static final String LICENSING_CONTENT_TYPE = "licensing.content.type"; // NLS-$1
	private static final String MINER_TYPE_KEY = "miner.type";// NLS-$1

	private List<ConditionMiner> licenseConditionMiners = new ArrayList<>();
	private Map<String, ConditionTransport> mapCondition2Transport = new HashMap<>();

	@Override
	public LicensingResult executeAction(HttpServletRequest request, HttpServletResponse response) {
		String source = getClass().getName();
		logger.info(String.format("Executing action request from class: %s", source));
		if (licenseConditionMiners.isEmpty()) {
			String error = "No condition miners available";
			logger.error(error);
			return LicensingResults.createError(error, source);
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
			ConditionTransport transport = mapCondition2Transport.get(contentType);
			if (transport == null) {
				String error = String.format("LicensingConditionTransport not defined for contentType: %s",
						contentType);
				logger.error(error);
				return LicensingResults.createError(error, source);
			}

			transport.writeConditions(resultConditions, response.getOutputStream());
			response.setContentType(APPLICATION_JSON);

			return LicensingResults.createOK("Conditions mined", source);
		} catch (IOException e) {
			logger.error(e.getMessage());
			String error = "Condition mining failed";
			return LicensingResults.createError(error, source, e);
		}
	}

	@Override
	@Reference
	protected void bindLogger(LoggerFactory loggerFactory) {
		super.bindLogger(loggerFactory);
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void bindConditionMiner(ConditionMiner conditionMiner, Map<String, String> context) {

		String minerType = context.get(MINER_TYPE_KEY);
		if (minerType != null && minerType.equals(SERVER_MINER_TYPE)) {
			this.licenseConditionMiners.add(conditionMiner);
		}
	}

	public void unbindConditionMiner(ConditionMiner conditionMiner, Map<String, String> context) {
		this.licenseConditionMiners.remove(conditionMiner);
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void bindConditionTransport(ConditionTransport transport, Map<String, String> context) {
		String conditionType = context.get(LICENSING_CONTENT_TYPE);
		if (conditionType != null) {
			mapCondition2Transport.put(conditionType, transport);
		}
	}

	public void unbindConditionTransport(ConditionTransport transport, Map<String, String> context) {
		String conditionType = context.get(LICENSING_CONTENT_TYPE);
		if (conditionType != null) {
			mapCondition2Transport.remove(conditionType, transport);
		}
	}
}
