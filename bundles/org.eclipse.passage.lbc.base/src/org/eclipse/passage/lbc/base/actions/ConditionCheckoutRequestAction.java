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

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.passage.lbc.base.BaseComponent;
import org.eclipse.passage.lbc.base.condition.ServerConditionsDistributor;
import org.eclipse.passage.lbc.runtime.ServerRequestAction;
import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.access.FeaturePermission;
import org.eclipse.passage.lic.runtime.access.PermissionEmitter;
import org.eclipse.passage.lic.runtime.access.PermissionTransport;
import org.eclipse.passage.lic.runtime.conditions.ConditionTransport;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.log.LoggerFactory;

/**
 * According to AccessManager specification implementation of
 * {@code Iterable<FeaturePermission> evaluateConditions()}
 * {@link org.eclipse.passage.lic.runtime.access.AccessManager}
 */
@Component
public class ConditionCheckoutRequestAction extends BaseComponent implements ServerRequestAction {

	private static final String CHARSET_UTF_8 = "UTF-8"; // NLS-$1
	private static final String APPLICATION_JSON = "application/json"; // NLS-$1

	private static final String CONDITIONS_FOR_EVALUATE_NOT_DEFINED_ERROR = "Conditions for evaluate not defined.";
	private static final String PASSAGE_EXECUTE_TXT = "[Passage] Execute action: %s ";
	private static final String LICENSING_CONDITION_TYPE_SERVER = "server";
	private static final String LICENSING_CONTENT_TYPE = "licensing.content.type"; // NLS-$1

	ServerConditionsDistributor conditionEvaluator;

	private Map<String, PermissionTransport> mapPermission2Transport = new HashMap<>();
	private Map<String, ConditionTransport> mapCondition2Transport = new HashMap<>();

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		logger.info(String.format(PASSAGE_EXECUTE_TXT, this.getClass().getName()));
		try {

			String contentType = request.getContentType();
			ConditionTransport transport = mapCondition2Transport.get(contentType);
			if (transport == null) {
				logger.error(String.format("LicensingConditionTransport not defined for contentType: %s", contentType));
				return false;
			}
			Iterable<LicensingCondition> descriptors = transport.readConditions(request.getInputStream());

			if (descriptors == null) {
				response.getWriter().println(CONDITIONS_FOR_EVALUATE_NOT_DEFINED_ERROR);
				logger.error(CONDITIONS_FOR_EVALUATE_NOT_DEFINED_ERROR);
				return false;
			}

			String productId = request.getParameter(PRODUCT);
			String productVersion = request.getParameter(VERSION);
			LicensingConfiguration configuration = LicensingConfigurations.create(productId, productVersion);
			Iterable<FeaturePermission> evaluatePermissions = conditionEvaluator.emitPermissions(descriptors,
					configuration);

			PermissionTransport transportPermission = mapPermission2Transport.get(contentType);
			if (transportPermission == null) {
				logger.error(String.format("FeaturePermissionTransport not defined for contentType: %s", contentType));
				return false;
			}
			transportPermission.writePermissions(evaluatePermissions, response.getOutputStream());

			response.setContentType(APPLICATION_JSON);
			response.setCharacterEncoding(CHARSET_UTF_8);
			PrintWriter printerWriter = response.getWriter();
			printerWriter.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
		return true;
	}

	@Override
	@Reference
	protected void bindLogger(LoggerFactory loggerFactory) {
		// TODO Auto-generated method stub
		super.bindLogger(loggerFactory);
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void bindServerConditionEvaluator(PermissionEmitter evaluator, Map<String, String> context) {
		String conditionType = context.get(LICENSING_CONTENT_TYPE);
		if (conditionType != null && conditionType.equals(LICENSING_CONDITION_TYPE_SERVER)) {
			if (evaluator instanceof ServerConditionsDistributor) {
				conditionEvaluator = (ServerConditionsDistributor) evaluator;
			}
		}
	}

	public void unbindServerConditionEvaluator(PermissionEmitter evaluator, Map<String, String> context) {
		String conditionType = context.get(LICENSING_CONTENT_TYPE);
		if (conditionType != null && conditionType.equals(LICENSING_CONDITION_TYPE_SERVER)) {
			if (evaluator instanceof ServerConditionsDistributor) {
				conditionEvaluator = null;
			}
		}
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void bindPermissionTransport(PermissionTransport transport, Map<String, String> context) {
		String conditionType = context.get(LICENSING_CONTENT_TYPE);
		if (conditionType != null) {
			mapPermission2Transport.put(conditionType, transport);
		}
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void bindLicensingConditionTransport(ConditionTransport transport, Map<String, String> context) {
		String conditionType = context.get(LICENSING_CONTENT_TYPE);
		if (conditionType != null) {
			mapCondition2Transport.put(conditionType, transport);
		}
	}
}
