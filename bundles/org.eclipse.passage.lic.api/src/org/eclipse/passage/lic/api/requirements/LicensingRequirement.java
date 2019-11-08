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
package org.eclipse.passage.lic.api.requirements;

/**
 * The usage constraint defined for specific feature identifier with given
 * version. It is declared at a program development phase during creation of a feature under licensing.
 * Accessed at the program runtime by {@link RequirementResolver}s.
 *
 * @see RequirementResolver
 * @since 0.4.0
 */
public interface LicensingRequirement {

	String getFeatureProvider();

	/**
	 * Name of the feature under requirement
	 *
	 * @since 0.4.0
	 */
	String getFeatureName();

	/**
	 * Version of the feature under requirement
	 *
	 * @since 0.4.0
	 */
	String getFeatureVersion();

	/**
	 * Id of the feature under licencing
	 *
	 * @since 0.4.0
	 * */
	String getFeatureIdentifier();

	/**
	 * The default restriction level, as defined by component vendor, may be
	 * redefined by specific configuration. Used as a hint for
	 * <code>RestrictionExaminer</code>>
	 * 
	 * @return restriction level, <code>warn</code> by default
	 * @since 0.4.0
	 */
	String getRestrictionLevel();

	/**
	 * The original physical source under the program installation,
	 * where this requirement has been read from by some {@code RequirementResolver}.
	 *
	 * @see RequirementResolver
	 * @return physical source (like file) defined by a program under protection
	 * @since 0.4.0
	 * */
	Object getRequirementSource();
	
}
