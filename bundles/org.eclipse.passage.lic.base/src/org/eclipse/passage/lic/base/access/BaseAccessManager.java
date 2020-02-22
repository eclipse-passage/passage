/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.base.access;

import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_CONDITION_TYPE_ID;
import static org.eclipse.passage.lic.base.LicensingResults.createError;
import static org.eclipse.passage.lic.base.LicensingResults.createEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.LicensingReporter;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.api.access.AccessEvents;
import org.eclipse.passage.lic.api.access.AccessManager;
import org.eclipse.passage.lic.api.access.FeaturePermission;
import org.eclipse.passage.lic.api.access.PermissionEmitter;
import org.eclipse.passage.lic.api.access.PermissionExaminer;
import org.eclipse.passage.lic.api.conditions.ConditionEvents;
import org.eclipse.passage.lic.api.conditions.ConditionMiner;
import org.eclipse.passage.lic.api.conditions.ConditionMinerRegistry;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;
import org.eclipse.passage.lic.api.requirements.LicensingRequirement;
import org.eclipse.passage.lic.api.requirements.RequirementEvents;
import org.eclipse.passage.lic.api.requirements.RequirementResolver;
import org.eclipse.passage.lic.api.restrictions.RestrictionExecutor;
import org.eclipse.passage.lic.api.restrictions.RestrictionVerdict;
import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.base.SystemReporter;
import org.eclipse.passage.lic.base.conditions.BaseConditionMinerRegistry;
import org.eclipse.passage.lic.base.conditions.LicensingConditions;
import org.eclipse.passage.lic.base.requirements.LicensingRequirements;
import org.eclipse.passage.lic.base.restrictions.RestrictionVerdicts;
import org.eclipse.passage.lic.internal.base.i18n.BaseMessages;

public class BaseAccessManager implements AccessManager {

	private LicensingReporter licensingReporter = SystemReporter.INSTANCE;

	private final List<RequirementResolver> requirementResolvers = new ArrayList<>();
	private ConditionMinerRegistry conditionMinerRegistry = new BaseConditionMinerRegistry();
	private final Map<String, PermissionEmitter> permissionEmitters = new HashMap<>();
	private final List<RestrictionExecutor> restrictionExecutors = new ArrayList<>();

	private PermissionExaminer examiner;

	public void bindLicensingReporter(LicensingReporter reporter) {
		this.licensingReporter = reporter;
	}

	public void unbindLicensingReporter(LicensingReporter reporter) {
		if (this.licensingReporter == reporter) {
			this.licensingReporter = SystemReporter.INSTANCE;
		}
	}

	public LicensingReporter getLicensingReporter() {
		return licensingReporter;
	}

	public void bindRequirementResolver(RequirementResolver requirementResolver) {
		requirementResolvers.add(requirementResolver);
	}

	public void unbindRequirementResolver(RequirementResolver configurationResolver) {
		requirementResolvers.remove(configurationResolver);
	}

	public void bindConditionMinerRegistry(ConditionMinerRegistry registry) {
		this.conditionMinerRegistry = registry;
	}

	public void unbindConditionMinerRegistry(ConditionMinerRegistry registry) {
		if (this.conditionMinerRegistry == registry) {
			this.conditionMinerRegistry = null;
		}
	}

	public void bindPermissionEmitter(PermissionEmitter permissionEmitter, Map<String, Object> properties) {
		Object conditionType = properties.get(LICENSING_CONDITION_TYPE_ID);
		String type = String.valueOf(conditionType);
		// FIXME: check permissions
		permissionEmitters.put(type, permissionEmitter);
	}

	public void unbindPermissionEmitter(PermissionEmitter permissionEmitter, Map<String, Object> properties) {
		Object conditionType = properties.get(LICENSING_CONDITION_TYPE_ID);
		String type = String.valueOf(conditionType);
		permissionEmitters.remove(type, permissionEmitter);
	}

	public void bindPermissionExaminer(PermissionExaminer permissionExaminer) {
		this.examiner = permissionExaminer;
	}

	public void unbindPermissionExaminer(PermissionExaminer permissionExaminer) {
		if (permissionExaminer == examiner) {
			this.examiner = null;
		} else {
			// FIXME: error
			this.examiner = null;
		}
	}

	public void bindRestrictionExecutor(RestrictionExecutor restrictionExecutor) {
		restrictionExecutors.add(restrictionExecutor);
	}

	public void unbindRestrictionExecutor(RestrictionExecutor restrictionExecutor) {
		restrictionExecutors.remove(restrictionExecutor);
	}

	@Override
	public LicensingResult executeAccessRestrictions(LicensingConfiguration configuration) {
		Iterable<LicensingRequirement> requirements = resolveRequirements(configuration);
		Iterable<LicensingCondition> conditions = extractConditions(configuration);
		Iterable<FeaturePermission> permissions = evaluateConditions(configuration, conditions);
		Iterable<RestrictionVerdict> verdicts = examinePermissions(configuration, requirements, permissions);
		return executeRestrictions(configuration, verdicts);
	}

	@Override
	public Iterable<LicensingRequirement> resolveRequirements(LicensingConfiguration configuration) {
		List<LicensingRequirement> result = new ArrayList<>();
		String source = getClass().getName();
		if (configuration == null) {
			String featureId = LicensingConfigurations.IDENTIFIER_INVALID;
			result.add(LicensingRequirements.createConfigurationError(featureId, source));
		} else {
			if (requirementResolvers.isEmpty()) {
				String featureId = configuration.getProductIdentifier();
				result.add(LicensingRequirements.createConfigurationError(featureId, source));
			} else {
				for (RequirementResolver configurationResolver : requirementResolvers) {
					Iterable<LicensingRequirement> requirements = configurationResolver
							.resolveLicensingRequirements(configuration);
					for (LicensingRequirement requirement : requirements) {
						result.add(requirement);
					}
				}
			}
		}
		List<LicensingRequirement> unmodifiable = Collections.unmodifiableList(result);
		licensingReporter.postResult(createEvent(RequirementEvents.REQUIREMENTS_RESOLVED, unmodifiable));
		return unmodifiable;
	}

	@Override
	public Iterable<LicensingCondition> extractConditions(LicensingConfiguration configuration) {
		if (conditionMinerRegistry == null) {
			return Collections.emptyList();
		}
		List<LicensingCondition> mined = new ArrayList<>();
		Iterable<ConditionMiner> conditionMiners = conditionMinerRegistry.getConditionMiners();
		List<LicensingResult> errors = new ArrayList<>();
		for (ConditionMiner conditionMiner : conditionMiners) {
			Iterable<LicensingCondition> conditions = conditionMiner.extractLicensingConditions(configuration);
			if (conditions == null) {
				String source = conditionMiner.getClass().getName();
				String message = BaseMessages.getString("BaseAccessManager_invalid_condition_miner"); //$NON-NLS-1$
				LicensingResult error = createError(message, source, new NullPointerException());
				errors.add(error);
				continue;
			}
			for (LicensingCondition condition : conditions) {
				mined.add(condition);
			}
		}
		List<LicensingCondition> unmodifiable = Collections.unmodifiableList(mined);
		licensingReporter.postResult(createEvent(AccessEvents.CONDITIONS_EXTRACTED, unmodifiable));
		return unmodifiable;
	}

	@Override
	public Iterable<FeaturePermission> evaluateConditions(LicensingConfiguration configuration,
			Iterable<LicensingCondition> conditions) {
		List<FeaturePermission> result = new ArrayList<>();
		String source = getClass().getName();
		if (conditions == null) {
			String message = BaseMessages.getString("BaseAccessManager_evaluation_error_invalid_condition"); //$NON-NLS-1$
			LicensingResult error = createError(message, source, new IllegalArgumentException());
			licensingReporter.logResult(error);
			List<FeaturePermission> empty = Collections.emptyList();
			licensingReporter.postResult(createEvent(AccessEvents.CONDITIONS_EVALUATED, empty, error));
			return empty;
		}
		List<LicensingCondition> invalid = new ArrayList<>();
		for (LicensingCondition condition : conditions) {
			if (condition == null) {
				String message = BaseMessages.getString("BaseAccessManager_evaluation_error_invalid_condition"); //$NON-NLS-1$
				LicensingResult error = createError(message, source, new NullPointerException());
				licensingReporter.logResult(error);
				continue;
			}
			LicensingResult validate = LicensingConditions.validate(condition, source);
			if (validate.getSeverity() == LicensingResult.OK) {
				String type = condition.getConditionType();
				PermissionEmitter emitter = permissionEmitters.get(type);
				if (emitter == null) {
					String message = String
							.format(BaseMessages.getString("BaseAccessManager__evaluation_error_no_emitter"), type); //$NON-NLS-1$
					LicensingResult error = createError(message, source, new NullPointerException());
					licensingReporter.logResult(error);
					continue;
				}
				List<LicensingCondition> mappedConditions = Collections.singletonList(condition);
				try {
					Iterable<FeaturePermission> permissions = emitter.emitPermissions(configuration, mappedConditions);
					for (FeaturePermission permission : permissions) {
						result.add(permission);
					}
				} catch (LicensingException e) {
					LicensingResult error = e.getResult();
					licensingReporter.logResult(error);
					licensingReporter
							.postResult(createEvent(ConditionEvents.CONDITIONS_NOT_VALID, mappedConditions, error));
				}
			} else {
				licensingReporter.logResult(validate);
				invalid.add(condition);
			}
		}
		List<FeaturePermission> unmodifiable = Collections.unmodifiableList(result);
		licensingReporter.postResult(createEvent(AccessEvents.CONDITIONS_EVALUATED, unmodifiable));
		return unmodifiable;
	}

	@Override
	public Iterable<RestrictionVerdict> examinePermissions(LicensingConfiguration configuration,
			Iterable<LicensingRequirement> requirements, Iterable<FeaturePermission> permissions) {
		String source = getClass().getName();
		if (configuration == null) {
			String message = BaseMessages
					.getString("BaseAccessManager_permission_examine_error_invalida_configuration"); //$NON-NLS-1$
			LicensingResult error = createError(message, source, new NullPointerException());
			licensingReporter.logResult(error);
			List<RestrictionVerdict> examined = Collections.emptyList();
			licensingReporter.postResult(createEvent(AccessEvents.PERMISSIONS_EXAMINED, examined));
			return examined;
		}
		if (requirements == null) {
			String message = BaseMessages
					.getString("BaseAccessManager_permission_examine_error_invalid_configuration_requirements"); //$NON-NLS-1$
			LicensingResult error = createError(message, source, new NullPointerException());
			licensingReporter.logResult(error);
			List<RestrictionVerdict> examined = Collections.emptyList();
			licensingReporter.postResult(createEvent(AccessEvents.PERMISSIONS_EXAMINED, examined));
			return examined;
		}
		if (examiner == null) {
			String message = String.format(
					BaseMessages.getString("BaseAccessManager_permission_examine_error_no_examiner"), requirements); //$NON-NLS-1$
			LicensingResult error = createError(message, source, new NullPointerException());
			licensingReporter.logResult(error);
			List<RestrictionVerdict> verdicts = new ArrayList<>();
			for (LicensingRequirement requirement : requirements) {
				if (requirement == null) {
					// FIXME: rework to children?
					String message2 = BaseMessages
							.getString("BaseAccessManager_permission_examine_error_invalid_config_req_ingnored"); //$NON-NLS-1$
					LicensingResult error2 = createError(message2, source, new NullPointerException());
					licensingReporter.logResult(error2);
					continue;
				}
				RestrictionVerdict verdict = RestrictionVerdicts.createError(configuration, requirement,
						RestrictionVerdicts.CODE_CONFIGURATION_ERROR);
				verdicts.add(verdict);
			}
			licensingReporter
					.postResult(createEvent(AccessEvents.PERMISSIONS_EXAMINED, Collections.unmodifiableList(verdicts)));
			return verdicts;
		}
		Iterable<RestrictionVerdict> examined = examiner.examine(configuration, requirements, permissions);
		licensingReporter.postResult(createEvent(AccessEvents.PERMISSIONS_EXAMINED, examined));
		return examined;
	}

	@Override
	public Iterable<RestrictionVerdict> examinePermissons(LicensingConfiguration configuration,
			Iterable<LicensingRequirement> requirements, Iterable<FeaturePermission> permissions) {
		return examinePermissions(configuration, requirements, permissions);
	}

	@Override
	public LicensingResult executeRestrictions(LicensingConfiguration configuration,
			Iterable<RestrictionVerdict> restrictions) {
		String source = getClass().getName();
		String task = BaseMessages.getString("BaseAccessManager_execute_restriction_task_name"); //$NON-NLS-1$
		List<LicensingResult> errors = new ArrayList<>();
		for (RestrictionExecutor executor : restrictionExecutors) {
			try {
				executor.execute(restrictions);
			} catch (Exception e) {
				String message = String.format(
						BaseMessages.getString("BaseAccessManager_execute_restriction_execution_error"), executor, //$NON-NLS-1$
						restrictions);
				LicensingResult error = createError(message, source, e);
				errors.add(error);
			}
		}
		licensingReporter.postResult(createEvent(AccessEvents.RESTRICTIONS_EXECUTED, restrictions));
		if (errors.isEmpty()) {
			return LicensingResults.createOK(task, source);
		}
		return LicensingResults.createError(task, source, errors);
	}

}
