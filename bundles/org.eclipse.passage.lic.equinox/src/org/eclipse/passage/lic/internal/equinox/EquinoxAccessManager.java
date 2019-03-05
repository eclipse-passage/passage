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
package org.eclipse.passage.lic.internal.equinox;

import java.util.Map;

import org.eclipse.passage.lic.base.BaseAccessManager;
import org.eclipse.passage.lic.equinox.requirements.EquinoxRequirements;
import org.eclipse.passage.lic.runtime.AccessManager;
import org.eclipse.passage.lic.runtime.ConditionEvaluator;
import org.eclipse.passage.lic.runtime.ConditionMiner;
import org.eclipse.passage.lic.runtime.RequirementResolver;
import org.eclipse.passage.lic.runtime.PermissionExaminer;
import org.eclipse.passage.lic.runtime.RestrictionExecutor;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.log.LogService;

public class EquinoxAccessManager extends BaseAccessManager implements AccessManager, BundleListener {

	private EventAdmin eventAdmin;
	
	private LogService logService;

	@Activate
	public void activate(BundleContext bundleContext) {
		bundleContext.addBundleListener(this);
		Bundle[] bundles = bundleContext.getBundles();
		for (Bundle bundle : bundles) {
			EquinoxRequirements.extractLicensingManagementRequirements(bundle);
		}
	}

	@Deactivate
	public void deactivate(BundleContext bundleContext) {
		bundleContext.removeBundleListener(this);
	}

	@Override
	public void bundleChanged(BundleEvent event) {
		//FIXME: consider event kind
		Bundle bundle = event.getBundle();
		EquinoxRequirements.extractLicensingManagementRequirements(bundle);
	}

	@Reference
	@Override
	public void bindConditionEvaluator(ConditionEvaluator conditionEvaluator, Map<String, Object> properties) {
		super.bindConditionEvaluator(conditionEvaluator, properties);
	}
	
	@Override
	public void unbindConditionEvaluator(ConditionEvaluator conditionEvaluator, Map<String, Object> properties) {
		super.unbindConditionEvaluator(conditionEvaluator, properties);
	}
	
	@Reference
	@Override
	public void bindConditionMiner(ConditionMiner conditionMiner) {
		super.bindConditionMiner(conditionMiner);
	}
	
	@Override
	public void unbindConditionMiner(ConditionMiner conditionMiner) {
		super.unbindConditionMiner(conditionMiner);
	}
	
	@Reference
	@Override
	public void bindConfigurationResolver(RequirementResolver configurationResolver) {
		super.bindConfigurationResolver(configurationResolver);
	}
	
	@Override
	public void unbindConfigurationResolver(RequirementResolver configurationResolver) {
		super.unbindConfigurationResolver(configurationResolver);
	}
	
	@Reference
	@Override
	public void bindPermissionExaminer(PermissionExaminer permissionExaminer) {
		super.bindPermissionExaminer(permissionExaminer);
	}
	
	@Override
	public void unbindPermissionExaminer(PermissionExaminer permissionExaminer) {
		super.unbindPermissionExaminer(permissionExaminer);
	}
	
	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	@Override
	public void bindRestrictionExecutor(RestrictionExecutor restrictionExecutor) {
		super.bindRestrictionExecutor(restrictionExecutor);
	}
	
	@Override
	public void unbindRestrictionExecutor(RestrictionExecutor restrictionExecutor) {
		super.unbindRestrictionExecutor(restrictionExecutor);
	}
	
	@Reference
	public void bindEventAdmin(EventAdmin eventAdmin) {
		this.eventAdmin = eventAdmin;
	}
	
	public void unbindEventAdmin(EventAdmin eventAdmin) {
		this.eventAdmin = eventAdmin;
	}

	@Override
	protected void postEvent(String topic, Object data) {
		Event event = EquinoxEvents.createEvent(topic, data);
		eventAdmin.postEvent(event);
	}

	@Reference
	public void bindLogService(LogService logService) {
		this.logService = logService;
	}
	
	public void unbindLogService(LogService logService) {
		this.logService = logService;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void logError(String message, Throwable e) {
		//FIXME: rework after removing Eclipse Mars support
		logService.log(LogService.LOG_ERROR, message, e);
	}

}
