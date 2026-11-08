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
package org.eclipse.passage.lic.internal.base.inspection;

import org.eclipse.passage.lic.api.EvaluationType;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionEvaluationException;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionTokenAssessmentService;
import org.eclipse.passage.lic.api.inspection.RuntimeEnvironment;
import org.eclipse.passage.lic.api.inspection.RuntimeEnvironmentRegistry;
import org.eclipse.passage.lic.base.inspection.BaseEnvironmentProperty;
import org.eclipse.passage.lic.internal.base.i18n.TrustedAssessmentMessages;

/**
 * @since 1.1
 */
public final class TrustedAssessmentService implements ExpressionTokenAssessmentService {

	private final RuntimeEnvironmentRegistry environments;

	public TrustedAssessmentService(RuntimeEnvironmentRegistry environments) {
		this.environments = environments;
	}

	@Override
	public EvaluationType id() {
		return new TrustedEvaluationType().get();
	}

	@Override
	public boolean equal(String key, String value) throws ExpressionEvaluationException {
		try {
			return env().isAssuptionTrue(new BaseEnvironmentProperty.Of(key), value);
		} catch (Exception e) {
			throw new ExpressionEvaluationException(//
					String.format(TrustedAssessmentMessages.getString("TrustedAssessmentService.errorOnAssessment"), // //$NON-NLS-1$
							key, value), //
					e);
		}
	}

	private RuntimeEnvironment env() throws LicensingException {
		EvaluationType type = id();
		if (environments.get().hasService(type)) {
			return environments.get().service(type);
		}
		throw new LicensingException(String.format(//
				TrustedAssessmentMessages.getString("TrustedAssessmentService.errorInConfiguration"), type)); //$NON-NLS-1$
	}

}
