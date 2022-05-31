/*******************************************************************************
 * Copyright (c) 2020, 2022 ArSysOp
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
package org.eclipse.passage.lic.oshi;

import org.eclipse.passage.lic.api.EvaluationType;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionEvaluationException;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionTokenAssessmentService;
import org.eclipse.passage.lic.api.inspection.RuntimeEnvironment;
import org.eclipse.passage.lic.api.inspection.RuntimeEnvironmentRegistry;
import org.eclipse.passage.lic.base.inspection.BaseEnvironmentProperty;
import org.eclipse.passage.lic.internal.oshi.i18n.AssessmentMessages;

/**
 * @since 1.1
 */
public final class HardwareAssessmentService implements ExpressionTokenAssessmentService {

	private final EvaluationType type = new EvaluationType.Hardware();
	private final RuntimeEnvironmentRegistry environments;

	public HardwareAssessmentService(RuntimeEnvironmentRegistry environments) {
		this.environments = environments;
	}

	@Override
	public EvaluationType id() {
		return type;
	}

	@Override
	public boolean equal(String key, String value) throws ExpressionEvaluationException {
		try {
			return hardware().isAssuptionTrue(new BaseEnvironmentProperty.Of(key), value);
		} catch (Exception e) {
			throw new ExpressionEvaluationException(//
					String.format(AssessmentMessages.HardwareAssessmentService_error_on_assessment, //
							key, value), //
					e);
		}
	}

	private RuntimeEnvironment hardware() throws LicensingException {
		if (environments.get().hasService(type)) {
			return environments.get().service(type);
		}
		throw new LicensingException(String.format(//
				AssessmentMessages.HardwareAssessmentService_no_hw_inspector, type));
	}

}
