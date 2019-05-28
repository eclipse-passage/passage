/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
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
package org.eclipse.passage.lic.licenses;

import org.eclipse.passage.lic.api.conditions.LicensingCondition;

/**
 * <p>
 * A <code>"License Pack Feature"</code> is a unit inside a
 * <code>"License Plan"</code> that describes a <code>"Feature Version"</code>
 * allowed to use. Used as a 'template' to create <code>"License Grant"</code>
 * <p>
 * 
 * @since 0.5.0
 * @see LicenseGrantDescriptor
 */
public interface LicensePlanFeatureDescriptor {

	/**
	 * Returns the <code>"Feature"</code> identifier of this license plan feature.
	 * This is the value of its <code>"featureIdentifier"</code> attribute.
	 *
	 * @return the feature identifier
	 * @see LicensingCondition#getFeatureIdentifier()
	 */
	String getFeatureIdentifier();

	/**
	 * Returns the <code>"Feature"</code> match version of this license plan
	 * feature. This is the value of its <code>"matchVersion"</code> attribute.
	 *
	 * @return the match version
	 * @see LicensingCondition#getMatchVersion()
	 */
	String getMatchVersion();

	/**
	 * Returns the <code>"Feature"</code> match rule of this license plan feature.
	 * This is the value of its <code>"matchRule"</code> attribute.
	 *
	 * @return the match rule
	 * @see LicensingCondition#getMatchRule()
	 */
	String getMatchRule();

	/**
	 * Returns the containing license plan of this license plan feature.
	 *
	 * @return the license plan
	 */
	LicensePlanDescriptor getLicensePlan();

}
