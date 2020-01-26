/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.features;

/**
 * <p>
 * A <code>"Feature"</code> corresponds to the scenario for your product, that
 * you want to restrict. For example, you may want to restrict export to some
 * proprietary format or other actions. You do not need to describe all the
 * functionality of your <code>"Product"</code>, but only the parts you want to
 * restrict. The <code>"identifier"</code> attribute of the
 * <code>"Feature"</code> is important for the
 * <code>"Licensing Configuration"</code>.
 * <p>
 *
 */
public interface FeatureDescriptor {

	/**
	 * Returns the identifier of this feature. This is the value of its
	 * <code>"identifier"</code> attribute.
	 *
	 * @return the identifier
	 */
	String getIdentifier();

	/**
	 * Returns the provider of this feature. This is the value of its
	 * <code>"provider"</code> attribute.
	 *
	 * @return the provider
	 * @since 0.5.0
	 */
	String getProvider();

	/**
	 * Returns the name of this feature. This is the value of its
	 * <code>"name"</code> attribute.
	 *
	 * @return the name
	 */
	String getName();

	/**
	 * Returns the description of this feature. This is the value of its
	 * <code>"description"</code> attribute.
	 *
	 * @return the description
	 */
	String getDescription();

	FeatureSetDescriptor getFeatureSet();

	Iterable<? extends FeatureVersionDescriptor> getFeatureVersions();

}
