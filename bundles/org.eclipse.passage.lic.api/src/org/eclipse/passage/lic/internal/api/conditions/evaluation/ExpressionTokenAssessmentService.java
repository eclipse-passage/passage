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
package org.eclipse.passage.lic.internal.api.conditions.evaluation;

import org.eclipse.passage.lic.internal.api.conditions.EvaluationType;
import org.eclipse.passage.lic.internal.api.registry.Service;

/**
 * <p>
 * Condition expression parsing, depending on the protocol used, can boil down
 * to a sophisticated construction of predicates.
 * </p>
 * <p>
 * But at the bottom of the evaluation of such a construction there are quite
 * simple questions for the runtime environment: is current operating system is
 * Linux-like? is the hard disk serial equal to this precise value? - that kind
 * of asking.
 * </p>
 * <p>
 * Implementation of this interface for a particular {@linkplain EvaluationType}
 * must answer only these simple questions. the rest of the evaluation logic is
 * on {@linkplain ExpressionProtocol}-aware services.
 * </p>
 */
public interface ExpressionTokenAssessmentService extends Service<EvaluationType> {

	boolean equal(String key, String value);

	// contains ()

	// startsWith ()

	// all the things we will ever need -> slice to [Operation]s

}
