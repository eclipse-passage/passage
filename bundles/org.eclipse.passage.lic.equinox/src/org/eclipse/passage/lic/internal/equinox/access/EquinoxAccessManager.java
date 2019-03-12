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
package org.eclipse.passage.lic.internal.equinox.access;

import java.util.Map;

import org.eclipse.passage.lic.base.access.BaseAccessManager;
import org.eclipse.passage.lic.equinox.requirements.EquinoxRequirements;
import org.eclipse.passage.lic.internal.equinox.EquinoxEvents;
import org.eclipse.passage.lic.runtime.access.AccessManager;
import org.eclipse.passage.lic.runtime.access.PermissionEmitter;
import org.eclipse.passage.lic.runtime.access.PermissionExaminer;
import org.eclipse.passage.lic.runtime.conditions.ConditionMinerRegistry;
import org.eclipse.passage.lic.runtime.requirements.RequirementResolver;
import org.eclipse.passage.lic.runtime.restrictions.RestrictionExecutor;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.log.LogService;

@Component
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
		// FIXME: consider event kind
		Bundle bundle = event.getBundle();
		EquinoxRequirements.extractLicensingManagementRequirements(bundle);
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	@Override
	public void bindRequirementResolver(RequirementResolver configurationResolver) {
		super.bindRequirementResolver(configurationResolver);
	}

	@Override
	public void unbindRequirementResolver(RequirementResolver configurationResolver) {
		super.unbindRequirementResolver(configurationResolver);
	}

	@Reference
	@Override
	public void bindConditionMinerRegistry(ConditionMinerRegistry registry) {
		super.bindConditionMinerRegistry(registry);
	}

	@Override
	public void unbindConditionMinerRegistry(ConditionMinerRegistry registry) {
		super.unbindConditionMinerRegistry(registry);
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	@Override
	public void bindPermissionEmitter(PermissionEmitter emitter, Map<String, Object> properties) {
		super.bindPermissionEmitter(emitter, properties);
	}

	@Override
	public void unbindPermissionEmitter(PermissionEmitter emitter, Map<String, Object> properties) {
		super.unbindPermissionEmitter(emitter, properties);
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
	public void bindEventAdmin(EventAdmin admin) {
		this.eventAdmin = admin;
	}

	public void unbindEventAdmin(EventAdmin admin) {
		this.eventAdmin = admin;
	}

	@Override
	protected void postEvent(String topic, Object data) {
		Event event = EquinoxEvents.createEvent(topic, data);
		eventAdmin.postEvent(event);
	}

	@Reference
	public void bindLogService(LogService log) {
		this.logService = log;
	}

	public void unbindLogService(LogService log) {
		if (this.logService == log) {
			this.logService = null;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void logError(String message, Throwable e) {
		// FIXME: rework after removing Eclipse Mars support
		logService.log(LogService.LOG_ERROR, message, e);
	}

}
