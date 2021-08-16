/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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
 * The <code>"Feature Version"</code> is a state of your <code>"Feature"</code>
 * that included to the <code>"Product Version"</code>.The
 * <code>"version"</code> attribute of the <code>"Feature Version"</code> is
 * important for the <code>"Licensing Configuration"</code>.
 * <p>
 *
 */
public interface FeatureVersionDescriptor {

	/**
	 * Returns the version of this feature version. This is the value of its
	 * <code>"version"</code> attribute.
	 *
	 * @return the version
	 */
	String getVersion();

	/**
	 * Returns the "what's new" of this feature version. This is the value of its
	 * <code>"news"</code> attribute.
	 *
	 * @return the news
	 */
	String getNews();

	FeatureDescriptor getFeature();

}
