/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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

import org.eclipse.passage.lic.api.EvaluationInstructions;

/**
 * <p>
 * Regarding each feature under licensing there is a series of statements in a
 * license of some kind.
 * </p>
 * <p>
 * These statements demands the feature to be used under curtain
 * {@code condition}s, such as "only version older than 2.14.1 on a workstation
 * under Windows OS with hard disk of serial AB0123C4DEFJHI and only for two
 * years starting from the 1st of May, 2020." {@code Condition} represents such
 * a bundle of demands.
 * </p>
 * 
 * @since 2.1
 */
public interface Condition {

	/**
	 * @return persistent identifier for cross-environment interactions.
	 */
	String identifier();

	/**
	 * @return unique identifier of a feature under licensing.
	 */
	String feature();

	/**
	 * Define the actual {@code version} representing string that has been mined in
	 * the scope of this condition (typically from license of sorts).
	 * 
	 * @see VersionMatch
	 */
	VersionMatch versionMatch();

	/**
	 * Period of this condition applicability.
	 * 
	 * @see ValidityPeriod
	 */
	ValidityPeriod validityPeriod();

	/**
	 * <p>
	 * Defined how exactly the condition is to be evaluates.
	 * </p>
	 * <p>
	 * Type of a license dictates how Passage can evaluate the condition (check if
	 * all it's demands are met). For example, "hardware" license requires examining
	 * of the actual workstation hardware.
	 * </p>
	 * 
	 * @see EvaluationInstructions
	 */
	EvaluationInstructions evaluationInstructions();

}
