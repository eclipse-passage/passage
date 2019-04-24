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
package org.eclipse.passage.lbc.internal.equinox.conditions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.passage.lbc.api.BackendActionExecutor;
import org.eclipse.passage.lbc.internal.equinox.EquinoxMessages;
import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.api.conditions.ConditionActions;
import org.eclipse.passage.lic.api.conditions.ConditionMiner;
import org.eclipse.passage.lic.api.conditions.ConditionTransport;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;
import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.net.LicensingNet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

/**
 * According to AccessManager specification implementation of
 * {@code Iterable<ConditionDescriptor> extractConditions(Object configuration)}
 * {@link org.eclipse.passage.lic.api.access.AccessManager}
 */
@Component(property = LicensingNet.ACTION + '=' + ConditionActions.ACQUIRE)
public class AcquireConditionActionExecutor implements BackendActionExecutor {

	protected Logger logger;

	private List<ConditionMiner> licenseConditionMiners = new ArrayList<>();
	private Map<String, ConditionTransport> mapCondition2Transport = new HashMap<>();

	@Override
	public LicensingResult executeAction(HttpServletRequest request, HttpServletResponse response) {
		String source = getClass().getName();
		logger.info(String.format(EquinoxMessages.AcquireConditionActionExecutor_log_execute_action, source));
		if (licenseConditionMiners.isEmpty()) {
			String error = "No condition miners available"; //$NON-NLS-1$
			logger.error(error);
			return LicensingResults.createError(error, source);
		}
		try {
			String productId = request.getParameter(LicensingConfigurations.LICENSING_PRODUCT_IDENTIFIER);
			String productVersion = request.getParameter(LicensingConfigurations.LICENSING_PRODUCT_IDENTIFIER);
			LicensingConfiguration configuration = LicensingConfigurations.create(productId, productVersion);

			Collection<LicensingCondition> resultConditions = new ArrayList<>();

			for (ConditionMiner miner : licenseConditionMiners) {
				Iterable<LicensingCondition> descriptors = miner.extractLicensingConditions(configuration);
				resultConditions.addAll((Collection<? extends LicensingCondition>) descriptors);
			}
			String contentType = request.getParameter(LicensingProperties.LICENSING_CONTENT_TYPE);
			ConditionTransport transport = mapCondition2Transport.get(contentType);
			if (transport == null) {
				String error = String.format("LicensingConditionTransport not defined for contentType: %s", //$NON-NLS-1$
						contentType);
				logger.error(error);
				return LicensingResults.createError(error, source);
			}

			transport.writeConditions(resultConditions, response.getOutputStream());
			response.setContentType(request.getContentType());

			return LicensingResults.createOK("Conditions mined", source); //$NON-NLS-1$
		} catch (IOException e) {
			logger.error(e.getMessage());
			String error = "Condition mining failed"; //$NON-NLS-1$
			return LicensingResults.createError(error, source, e);
		}
	}

	@Reference
	protected void bindLogger(LoggerFactory loggerFactory) {
		logger = loggerFactory.getLogger(this.getClass().getName());
	}

	protected void unbindLogger(LoggerFactory loggerFactory) {
		logger = null;
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void bindConditionMiner(ConditionMiner conditionMiner, Map<String, String> context) {
		this.licenseConditionMiners.add(conditionMiner);
	}

	public void unbindConditionMiner(ConditionMiner conditionMiner, Map<String, String> context) {
		this.licenseConditionMiners.remove(conditionMiner);
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void bindConditionTransport(ConditionTransport transport, Map<String, String> context) {
		String conditionType = context.get(LicensingProperties.LICENSING_CONTENT_TYPE);
		mapCondition2Transport.put(conditionType, transport);
	}

	public void unbindConditionTransport(ConditionTransport transport, Map<String, String> context) {
		String conditionType = context.get(LicensingProperties.LICENSING_CONTENT_TYPE);
		mapCondition2Transport.remove(conditionType, transport);
	}
}
