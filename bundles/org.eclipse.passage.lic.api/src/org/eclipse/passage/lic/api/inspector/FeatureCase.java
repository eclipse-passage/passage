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
package org.eclipse.passage.lic.api.inspector;

import org.eclipse.passage.lic.api.requirements.LicensingRequirement;
import org.eclipse.passage.lic.api.restrictions.RestrictionVerdict;

/**
 * Pre-evaluated license coverage for a particular features subset (affected, say, by one user scenario),
 * managed by {@code FeatureInspector}
 *
 * @see FeatureInspector
 * @since 0.4.0
 */
public interface FeatureCase extends AutoCloseable {

	/**
	 * Identifiers of the features under inspection.
	 *
	 * @since 0.4.0
	 */
	Iterable<String> getFeatureIdentifiers();

	/**
	 * {@code Requirement}s resolved during the inspection
	 *
	 * @since 0.4.0
	 */
	Iterable<LicensingRequirement> getRequirements();

	/**
	 * {@code Restriction}s evaluated as the result of the inspection
	 *
	 * @since 0.4.0
	 */
	Iterable<RestrictionVerdict> getRestrictions();

	/**
	 * The case is auto-closable
	 *
	 * @since 0.4.0
	 */
	@Override
	void close();

}
