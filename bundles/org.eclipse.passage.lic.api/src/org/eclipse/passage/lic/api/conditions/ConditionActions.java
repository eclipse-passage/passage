/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.lic.api.conditions;

import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;

/**
 * Actions to be performed with {@link LicensingCondition}(s)
 * 
 * @since 0.5.0
 * @deprecated use {@link ConditionAction}
 */
@Deprecated
public class ConditionActions {

	private ConditionActions() {
		// block
	}

	/**
	 * Acquire the {@link LicensingCondition} for the future use, the usage needs to
	 * be periodically confirmed with {@link #KEEP} action
	 *
	 * @since 0.5.0
	 * @deprecated use {@link ConditionAction.Aquire}
	 */
	@Deprecated
	public static final String ACQUIRE = "acquire"; //$NON-NLS-1$

	/**
	 * Keep the {@link LicensingCondition} for the usage
	 *
	 * @since 0.5.0
	 * @deprecated use {@link ConditionAction.Keep}
	 */
	@Deprecated
	public static final String KEEP = "keep"; //$NON-NLS-1$

	/**
	 * Release the {@link LicensingCondition} to be available for others
	 *
	 * @since 0.5.0
	 * @deprecated use {@link ConditionAction.Release}
	 */
	@Deprecated
	public static final String RELEASE = "release"; //$NON-NLS-1$

}
