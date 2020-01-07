/*******************************************************************************
 * Copyright (c) 2018-2020 ArSysOp
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lbc.api.BackendActionExecutor;
import org.eclipse.passage.lbc.internal.equinox.i18n.EquinoxMessages;
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
import org.osgi.service.component.annotations.ReferencePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * According to AccessManager specification implementation of
 * {@code Iterable<ConditionDescriptor> extractConditions(Object configuration)}
 * {@link org.eclipse.passage.lic.api.access.AccessManager}
 */
@Component(property = LicensingNet.ACTION + '=' + ConditionActions.ACQUIRE)
public class AcquireConditionActionExecutor implements BackendActionExecutor {

	private static final Logger LOG = LoggerFactory.getLogger(AcquireConditionActionExecutor.class);

	private List<ConditionMiner> licenseConditionMiners = new ArrayList<>();
	private Map<String, ConditionTransport> mapCondition2Transport = new HashMap<>();

	@Override
	public LicensingResult executeAction(HttpServletRequest request, HttpServletResponse response) {
		String source = getClass().getName();
		LOG.info(NLS.bind(EquinoxMessages.AcquireConditionActionExecutor_i_execute_action, source));
		if (licenseConditionMiners.isEmpty()) {
			String error = EquinoxMessages.AcquireConditionActionExecutor_e_no_miners;
			LOG.error(error);
			return LicensingResults.createError(error, source);
		}
		try {
			String productId = request.getParameter(LicensingConfigurations.LICENSING_PRODUCT_IDENTIFIER);
			String productVersion = request.getParameter(LicensingConfigurations.LICENSING_PRODUCT_VERSION);
			LicensingConfiguration configuration = LicensingConfigurations.create(productId, productVersion);

			Collection<LicensingCondition> resultConditions = new ArrayList<>();

			for (ConditionMiner miner : licenseConditionMiners) {
				Iterable<LicensingCondition> descriptors = miner.extractLicensingConditions(configuration);
				resultConditions.addAll((Collection<? extends LicensingCondition>) descriptors);
			}
			String contentType = request.getParameter(LicensingProperties.LICENSING_CONTENT_TYPE);
			ConditionTransport transport = mapCondition2Transport.get(contentType);
			if (transport == null) {
				String error = NLS.bind(EquinoxMessages.AcquireConditionActionExecutor_e_transport_not_defined,
						contentType);
				LOG.error(error);
				return LicensingResults.createError(error, source);
			}

			transport.writeConditions(resultConditions, response.getOutputStream());
			response.setContentType(request.getContentType());

			return LicensingResults.createOK(EquinoxMessages.AcquireConditionActionExecutor_k_mined, source);
		} catch (IOException e) {
			LOG.error(e.getMessage());
			String error = EquinoxMessages.AcquireConditionActionExecutor_e_mining_failed;
			return LicensingResults.createError(error, source, e);
		}
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
	public void bindConditionMiner(ConditionMiner conditionMiner) {
		this.licenseConditionMiners.add(conditionMiner);
	}

	public void unbindConditionMiner(ConditionMiner conditionMiner) {
		this.licenseConditionMiners.remove(conditionMiner);
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
	public void bindConditionTransport(ConditionTransport transport, Map<String, String> context) {
		String conditionType = context.get(LicensingProperties.LICENSING_CONTENT_TYPE);
		mapCondition2Transport.put(conditionType, transport);
	}

	public void unbindConditionTransport(ConditionTransport transport, Map<String, String> context) {
		String conditionType = context.get(LicensingProperties.LICENSING_CONTENT_TYPE);
		mapCondition2Transport.remove(conditionType, transport);
	}
}
