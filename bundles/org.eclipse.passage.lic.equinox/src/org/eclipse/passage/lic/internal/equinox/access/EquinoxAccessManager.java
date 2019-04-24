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

import org.eclipse.passage.lic.api.LicensingReporter;
import org.eclipse.passage.lic.api.access.AccessManager;
import org.eclipse.passage.lic.api.access.PermissionEmitter;
import org.eclipse.passage.lic.api.access.PermissionExaminer;
import org.eclipse.passage.lic.api.conditions.ConditionMinerRegistry;
import org.eclipse.passage.lic.api.requirements.RequirementResolver;
import org.eclipse.passage.lic.api.restrictions.RestrictionExecutor;
import org.eclipse.passage.lic.base.access.BaseAccessManager;
import org.eclipse.passage.lic.equinox.requirements.EquinoxRequirements;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

@Component
public class EquinoxAccessManager extends BaseAccessManager implements AccessManager, BundleListener {

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

	@Reference
	@Override
	public void bindLicensingReporter(LicensingReporter reporter) {
		super.bindLicensingReporter(reporter);
	}

	@Override
	public void unbindLicensingReporter(LicensingReporter reporter) {
		super.unbindLicensingReporter(reporter);
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

}
