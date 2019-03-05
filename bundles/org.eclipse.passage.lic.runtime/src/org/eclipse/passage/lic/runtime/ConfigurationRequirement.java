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
package org.eclipse.passage.lic.runtime;

/**
 * The usage constraint defined for specific feature identifier with given
 * version. Obtained from {@link RequirementResolver}
 *
 */
public interface ConfigurationRequirement {

	String getFeatureIdentifier();

	String getFeatureVersion();

	String getFeatureName();

	/**
	 * The default restriction level, as defined by component vendor, may be
	 * redefined by specific configuration. Used as a hint for
	 * <code>RestrictionExaminer</code>>
	 * 
	 * @return restriction level, <code>warn</code> by default
	 */
	String getRestrictionLevel();

	Object getRequirementSource();
	
	LicensingConfiguration getLicensingConfiguration();

}
