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

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.passage.lic.base.LicensingNamespaces;
import org.eclipse.passage.lic.base.restrictions.BaseRestrictionLevelDescriptor;
import org.eclipse.passage.lic.runtime.restrictions.RestrictionExecutorRegistry;
import org.eclipse.passage.lic.runtime.restrictions.RestrictionLevelDescriptor;
import org.osgi.service.component.annotations.Component;

@Component
public class EquinoxRestrictionExecutorRegistry implements RestrictionExecutorRegistry {

	private final Map<String, RestrictionLevelDescriptor> supportedLevels = new LinkedHashMap<>();

	public EquinoxRestrictionExecutorRegistry() {
		String warn = LicensingNamespaces.ATTRIBUTE_LEVEL_WARN;
		String error = LicensingNamespaces.ATTRIBUTE_LEVEL_ERROR;
		supportedLevels.put(warn, new BaseRestrictionLevelDescriptor(warn, "Warning",
				"Warn about issues with licensing, but allow to proceed without functionality blocking"));
		supportedLevels.put(error, new BaseRestrictionLevelDescriptor(error, "Error",
				"Block the restricted functionality, but allow to use other scenarios"));
		String fatal = LicensingNamespaces.ATTRIBUTE_LEVEL_FATAL;
		supportedLevels.put(fatal, new BaseRestrictionLevelDescriptor(fatal, "Fatal", "Terminate the application"));
	}

	@Override
	public Iterable<String> getRestrictionLevelIdentifiers() {
		return supportedLevels.keySet();
	}

	@Override
	public String getDefaultRestrictionLevelIdentifier() {
		return LicensingNamespaces.ATTRIBUTE_LEVEL_DEFAULT;
	}

	@Override
	public RestrictionLevelDescriptor getRestrictionLevel(String identifier) {
		return supportedLevels.get(identifier);
	}

	@Override
	public Iterable<? extends RestrictionLevelDescriptor> getRestrictionLevels() {
		return supportedLevels.values();
	}

}
