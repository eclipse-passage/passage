/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.api.conditions;

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
 */
public interface EvaluationInstructions {

	/**
	 * Meta classifier of the way this condition can be evaluated
	 * 
	 * @see EvaluationType
	 */
	EvaluationType type();

	/**
	 * Precise data for evaluation. Each evaluation type can expect it's own format
	 * here.
	 * 
	 * @return raw string mined from a license
	 */
	String expression();

}
