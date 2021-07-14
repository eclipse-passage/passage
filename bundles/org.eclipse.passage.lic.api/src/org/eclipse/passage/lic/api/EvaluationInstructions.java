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
package org.eclipse.passage.lic.api;

/**
 * <p>
 * Condition definition contains sufficient amount of instructions on how
 * exactly can we say if this condition is satisfied or not.
 * </p>
 * <p>
 * For example, condition of {@code hardware} type requires actual workstation
 * hardware evaluation and assessing it against condition's expectations. These
 * ones can demand particular hardware part of have predefined serial id.
 * </p>
 * 
 * @since 2.1
 */
public interface EvaluationInstructions {

	/**
	 * Meta classifier of the way this condition can be evaluated
	 * 
	 * @see EvaluationType
	 */
	EvaluationType type();

	/**
	 * <p>
	 * Precise data for evaluation. Each evaluation type can expect it's own format
	 * here.
	 * </p>
	 * <p>
	 * {@code null} is prohibited. Use empty string to denote there is no
	 * expression.
	 * </p>
	 * 
	 * @return raw string mined from a license, can be blank, cannot be
	 *         {@code null}.
	 */
	String expression();

}
