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
package org.eclipse.passage.lic.licenses;

import java.util.List;

/**
 * <p>
 * A <code>"License Pack"</code> is a container for
 * <code>"License Grant"</code>(s). Created by <code>"Operator"</code> and
 * transferred to the <code>"User"</code> to be evaluated at runtime for the
 * given <code>"Licensing Configuration"</code>.
 * <p>
 * 
 * @since 2.0
 * @see PersonalFeatureGrantDescriptor
 */
public interface PersonalLicensePackDescriptor {

	/**
	 * @since 2.0
	 */
	PersonalLicenseRequisitesDescriptor getLicense();

	/**
	 * @since 2.0
	 */
	List<? extends PersonalFeatureGrantDescriptor> getGrants();

}
