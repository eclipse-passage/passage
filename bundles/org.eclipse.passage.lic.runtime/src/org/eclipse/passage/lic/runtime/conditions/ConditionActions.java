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
package org.eclipse.passage.lic.runtime.conditions;

/**
 * Actions to be performed with {@link LicensingCondition}(s)
 * 
 * @since 0.5.0
 *
 */
public class ConditionActions {

	private ConditionActions() {
		// block
	}

	/**
	 * Acquire the {@link LicensingCondition} for the future use, the usage needs to
	 * be periodically confirmed with {@link #KEEP} action
	 */
	public static final String ACQUIRE = "acquire"; //$NON-NLS-1$

	/**
	 * Keep the {@link LicensingCondition} for the usage
	 */
	public static final String KEEP = "keep"; //$NON-NLS-1$

	/**
	 * Release the {@link LicensingCondition} to be available for others
	 */
	public static final String RELEASE = "release"; //$NON-NLS-1$

}
