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
package org.eclipse.passage.lic.base.conditions.evaluation;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.conditions.ConditionPack;
import org.eclipse.passage.lic.api.conditions.ValidityPeriod;
import org.eclipse.passage.lic.api.conditions.ValidityPeriodClosed;
import org.eclipse.passage.lic.api.conditions.evaluation.Emission;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionEvaluationException;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionEvaluatorsRegistry;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionParsingException;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionPasringRegistry;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionTokenAssessorsRegistry;
import org.eclipse.passage.lic.api.conditions.evaluation.PermissionEmittingService;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.api.diagnostic.TroubleCode;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.SumOfCollections;
import org.eclipse.passage.lic.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.base.diagnostic.code.LicenseDoesNotMatch;
import org.eclipse.passage.lic.base.diagnostic.code.LicenseInvalid;
import org.eclipse.passage.lic.base.diagnostic.code.ServiceFailedOnMorsel;
import org.eclipse.passage.lic.internal.base.i18n.ConditionsEvaluationMessages;

/**
 * 
 * @since 2.1
 */
public final class BasePermissionEmittingService implements PermissionEmittingService {

	private final StringServiceId id = new StringServiceId("default-emitter"); //$NON-NLS-1$
	private final Authentication authentification;

	public BasePermissionEmittingService(//
			ExpressionPasringRegistry parsers, //
			ExpressionTokenAssessorsRegistry assessors, //
			ExpressionEvaluatorsRegistry evaluators) {
		authentification = new Authentication(parsers, assessors, evaluators);
	}

	@Override
	public StringServiceId id() {
		return id;
	}

	@Override
	public ServiceInvocationResult<Collection<Emission>> emit(Collection<ConditionPack> conditions,
			LicensedProduct product) {
		return conditions.stream() //
				.map(pack -> emitFor(pack, product))//
				.reduce(new BaseServiceInvocationResult.Sum<Collection<Emission>>(new SumOfCollections<>()))//
				.orElse(new BaseServiceInvocationResult<>(new ArrayList<>()));
	}

	private ServiceInvocationResult<Collection<Emission>> emitFor(ConditionPack pack, LicensedProduct product) {
		return pack.conditions().stream() //
				.map(condition -> emitFor(condition, pack, product))//
				.reduce(new BaseServiceInvocationResult.Sum<Emission>(new SumOfEmissions()))//
				.map(this::singleton)//
				.orElse(new BaseServiceInvocationResult<>(Collections.emptyList()));
	}

	private ServiceInvocationResult<Collection<Emission>> singleton(ServiceInvocationResult<Emission> origin) {
		return new BaseServiceInvocationResult<>(//
				origin.diagnostic(), //
				origin.data().isPresent() //
						? Collections.singleton(origin.data().get())//
						: Collections.emptyList());
	}

	private ServiceInvocationResult<Emission> emitFor(Condition condition, ConditionPack pack,
			LicensedProduct product) {
		try {
			authentification.verify(condition.evaluationInstructions());
		} catch (ExpressionParsingException e) {
			return fail(pack, //
					new LicenseInvalid(), //
					String.format(ConditionsEvaluationMessages.getString("BasePermissionEmittingService.parse_failed"), //$NON-NLS-1$
							condition.evaluationInstructions().expression()), //
					e);
		} catch (LicensingException e) {
			return fail(pack, //
					new LicenseInvalid(), //
					String.format(
							ConditionsEvaluationMessages.getString("BasePermissionEmittingService.assessment_failed"), //$NON-NLS-1$
							condition.evaluationInstructions().expression()), //
					e);
		} catch (ExpressionEvaluationException e) {
			return fail(pack, //
					new LicenseDoesNotMatch(), //
					String.format(
							ConditionsEvaluationMessages.getString("BasePermissionEmittingService.evaluation_failed"), //$NON-NLS-1$
							condition.evaluationInstructions().expression()), //
					e);
		}
		return createFor(condition, pack, product);
	}

	private ServiceInvocationResult<Emission> createFor(Condition condition, ConditionPack pack,
			LicensedProduct product) {
		try {
			return new BaseServiceInvocationResult<>(//
					new Emission(//
							pack, //
							new BasePermission(//
									product, //
									condition, //
									// FIXME:#566015
									from(condition.validityPeriod()), expiration(condition.validityPeriod()), //
									pack.origin())//
					));
		} catch (Exception e) {
			return new BaseServiceInvocationResult<>(//
					new BaseDiagnostic(Collections.singletonList(//
							new Trouble(new ServiceFailedOnMorsel(),
									String.format(
											ConditionsEvaluationMessages.getString(
													"BasePermissionEmittingService.failed_create_permission"), //$NON-NLS-1$
											condition.feature(), condition.identifier()),
									e))));
		}
	}

	private ServiceInvocationResult<Emission> fail(ConditionPack pack, TroubleCode code, String explanation,
			Exception e) {
		return new BaseServiceInvocationResult<Emission>(//
				new BaseDiagnostic(Collections.singletonList(new Trouble(code, explanation, e))), //
				new Emission(pack));
	}

	private ZonedDateTime from(ValidityPeriod period) {
		if (ValidityPeriodClosed.class.isInstance(period)) {
			return ((ValidityPeriodClosed) period).from();
		}
		return ZonedDateTime.now();

	}

	private ZonedDateTime expiration(ValidityPeriod period) {
		if (ValidityPeriodClosed.class.isInstance(period)) {
			return ((ValidityPeriodClosed) period).to();
		}
		return ZonedDateTime.now().plusDays(1);
	}

}
