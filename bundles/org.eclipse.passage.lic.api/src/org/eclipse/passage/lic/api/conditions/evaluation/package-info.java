/* *****************************************************************************
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
/**
 * <p>
 * After a {@code Condition} is mined and we know, what a license demands, we
 * can say - if these demands are satisfied in the current program runtime or
 * not. The process of finding this out is called {@code condition evaluation}
 * and it is the third phase of access cycle.
 * </p>
 * 
 * <h4>How the evaluation is done?</h4>
 * 
 * <ul>
 * <li>A {@code condition} has precise {@code evaluation instructions} :
 * {@code evaluation type} (what part of the program runtime is to be assessed)
 * and a string {@code expression} (what demands this part of runtime must
 * satisfy). For example, condition of {@code hardware} evaluation type can have
 * string {@code "hwdisk.serial=12345678"} as {@code expression}, which means
 * that a feature protected with this condition can only be executed on a
 * particular workstation.</li>
 * 
 * <li>{@code expression} can be written in different protocols, so we must have
 * proper {@linkplain ExpressionParsingService} to be able to {@code decompose}
 * the expression to tokens, and {@linkplain ExpressionEvaluationService} for
 * this protocol to combine all token assessment results into the final answer:
 * if the expression's demands are satisfied or not.</li>
 * 
 * <li>To define if a particular token (simplest question to the environment)
 * claim is met, we find an implementation of {@code RuntimeEnvironment}
 * interface, that correspond to our {@code evaluation type}. With it's help
 * {@linkplain ExpressionTokenAssessmentService} gets simplest {@code true} or
 * {@code false} answer for each token.</li>
 * 
 * </ul>
 * 
 * <h4>Where are extension points in this {@code evaluation} process?</h4>
 * <ul>
 * <li>To support new {@code expression protocol}, supply new implementation for
 * {@code ExpressionParsingService} and {@code ExpressionEvaluationService}</li>
 * 
 * <li>To invite new part of a program runtime for questioning, implement
 * {@code RuntimeEnvironment} and {@code ExpressionTokenAssessmentService}</li>
 * 
 * <li>To alternate the logic of evaluation, implement
 * {@code PermissionEmittingService}</li>
 * 
 * <li>Do not forget to extend all the {@code contract tests} (`lic.api.tests`)
 * for an extension</li>
 * 
 * </ul>
 * 
 * @see org.eclipse.passage.lic.api.conditions.Condition
 * @see org.eclipse.passage.lic.api.EvaluationType
 * @see org.eclipse.passage.lic.api.inspection.RuntimeEnvironment
 */
package org.eclipse.passage.lic.api.conditions.evaluation;
