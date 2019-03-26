/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.base.SystemReporter;
import org.eclipse.passage.lic.base.conditions.BaseConditionMinerRegistry;
import org.eclipse.passage.lic.base.requirements.LicensingRequirements;
import org.eclipse.passage.lic.base.restrictions.RestrictionVerdicts;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.LicensingException;
import org.eclipse.passage.lic.runtime.LicensingReporter;
import org.eclipse.passage.lic.runtime.LicensingResult;
import org.eclipse.passage.lic.runtime.access.AccessEvents;
import org.eclipse.passage.lic.runtime.access.AccessManager;
import org.eclipse.passage.lic.runtime.access.FeaturePermission;
import org.eclipse.passage.lic.runtime.access.PermissionEmitter;
import org.eclipse.passage.lic.runtime.access.PermissionExaminer;
import org.eclipse.passage.lic.runtime.conditions.ConditionEvents;
import org.eclipse.passage.lic.runtime.conditions.ConditionMiner;
import org.eclipse.passage.lic.runtime.conditions.ConditionMinerRegistry;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.eclipse.passage.lic.runtime.requirements.LicensingRequirement;
import org.eclipse.passage.lic.runtime.requirements.RequirementEvents;
import org.eclipse.passage.lic.runtime.requirements.RequirementResolver;
import org.eclipse.passage.lic.runtime.restrictions.RestrictionExecutor;
import org.eclipse.passage.lic.runtime.restrictions.RestrictionVerdict;

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
		PermissionEmitter removed = permissionEmitters.remove(type);
		if (permissionEmitter != removed) {
			// FIXME: error
		}
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
		Iterable<FeaturePermission> permissions = evaluateConditions(conditions, configuration);
		Iterable<RestrictionVerdict> verdicts = examinePermissons(requirements, permissions, configuration);
		return executeRestrictions(verdicts);
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
		List<LicensingCondition> mined = new ArrayList<>();
		Iterable<ConditionMiner> conditionMiners = conditionMinerRegistry.getConditionMiners();
		List<LicensingResult> errors = new ArrayList<>();
		for (ConditionMiner conditionMiner : conditionMiners) {
			Iterable<LicensingCondition> conditions = conditionMiner.extractLicensingConditions(configuration);
			if (conditions == null) {
				String source = conditionMiner.getClass().getName();
				String message = "Invalid condition miner";
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
	public Iterable<FeaturePermission> evaluateConditions(Iterable<LicensingCondition> conditions,
			LicensingConfiguration configuration) {
		List<FeaturePermission> result = new ArrayList<>();
		String source = getClass().getName();
		if (conditions == null) {
			String message = "Evaluation rejected for invalid conditions";
			LicensingResult error = createError(message, source, new IllegalArgumentException());
			licensingReporter.logResult(error);
			List<FeaturePermission> empty = Collections.emptyList();
			licensingReporter.postResult(createEvent(AccessEvents.CONDITIONS_EVALUATED, empty, error));
			return empty;
		}
		List<FeaturePermission> unmodifiable = Collections.unmodifiableList(result);
		Map<String, List<LicensingCondition>> map = new HashMap<>();
		List<LicensingCondition> invalid = new ArrayList<>();
		for (LicensingCondition condition : conditions) {
			if (condition == null) {
				String message = "Evaluation rejected for invalid condition";
				LicensingResult error = createError(message, source, new NullPointerException());
				licensingReporter.logResult(error);
				continue;
			}
			String validate = validate(condition);
			if (validate == null) {
				String type = condition.getConditionType();
				List<LicensingCondition> list = map.computeIfAbsent(type, key -> new ArrayList<>());
				list.add(condition);
			} else {
				LicensingResult error = createError(validate, source, (Throwable) null);
				licensingReporter.logResult(error);
				invalid.add(condition);
			}
		}
		Set<String> types = map.keySet();
		for (String type : types) {
			PermissionEmitter emitter = permissionEmitters.get(type);
			if (emitter == null) {
				String message = String.format("No permission emitter available for type %s", type);
				LicensingResult error = createError(message, source, new NullPointerException());
				licensingReporter.logResult(error);
				continue;
			}
			List<LicensingCondition> mappedConditions = map.get(type);
			try {
				Iterable<FeaturePermission> permissions = emitter.emitPermissions(mappedConditions, configuration);
				for (FeaturePermission permission : permissions) {
					result.add(permission);
				}
			} catch (LicensingException e) {
				LicensingResult error = e.getResult();
				licensingReporter.logResult(error);
				licensingReporter
						.postResult(createEvent(ConditionEvents.CONDITIONS_NOT_VALID, mappedConditions, error));
			}
		}
		licensingReporter.postResult(createEvent(AccessEvents.CONDITIONS_EVALUATED, unmodifiable));
		return unmodifiable;
	}

	@Override
	public Iterable<RestrictionVerdict> examinePermissons(Iterable<LicensingRequirement> requirements,
			Iterable<FeaturePermission> permissions, LicensingConfiguration configuration) {
		String source = getClass().getName();
		if (configuration == null) {
			String message = "Invalid configuration";
			LicensingResult error = createError(message, source, new NullPointerException());
			licensingReporter.logResult(error);
			List<RestrictionVerdict> examined = Collections.emptyList();
			licensingReporter.postResult(createEvent(AccessEvents.PERMISSIONS_EXAMINED, examined));
			return examined;
		}
		if (requirements == null) {
			String message = "Invalid configuration requirements";
			LicensingResult error = createError(message, source, new NullPointerException());
			licensingReporter.logResult(error);
			List<RestrictionVerdict> examined = Collections.emptyList();
			licensingReporter.postResult(createEvent(AccessEvents.PERMISSIONS_EXAMINED, examined));
			return examined;
		}
		if (examiner == null) {
			String message = String.format("No permission examiner defined, rejecting all %s", requirements);
			LicensingResult error = createError(message, source, new NullPointerException());
			licensingReporter.logResult(error);
			List<RestrictionVerdict> verdicts = new ArrayList<>();
			for (LicensingRequirement requirement : requirements) {
				if (requirement == null) {
					// FIXME: rework to children?
					String message2 = "Invalid configuration requirement ignored";
					LicensingResult error2 = createError(message2, source, new NullPointerException());
					licensingReporter.logResult(error2);
					continue;
				}
				RestrictionVerdict verdict = RestrictionVerdicts.createError(requirement,
						RestrictionVerdicts.CODE_CONFIGURATION_ERROR);
				verdicts.add(verdict);
			}
			licensingReporter
					.postResult(createEvent(AccessEvents.PERMISSIONS_EXAMINED, Collections.unmodifiableList(verdicts)));
			return verdicts;
		}
		Iterable<RestrictionVerdict> examined = examiner.examine(requirements, permissions);
		licensingReporter.postResult(createEvent(AccessEvents.PERMISSIONS_EXAMINED, examined));
		return examined;
	}

	@Override
	public LicensingResult executeRestrictions(Iterable<RestrictionVerdict> restrictions) {
		String source = getClass().getName();
		String task = "Executing restrinctions";
		List<LicensingResult> errors = new ArrayList<>();
		for (RestrictionExecutor executor : restrictionExecutors) {
			try {
				executor.execute(restrictions);
			} catch (Exception e) {
				String message = String.format("%s failed to execute %s", executor, restrictions);
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

	protected String validate(LicensingCondition condition) {
		Date validFrom = condition.getValidFrom();
		if (validFrom == null) {
			String format = "Valid from not specified for condition %s";
			return String.format(format, condition);
		}
		Date now = new Date();
		if (validFrom.after(now)) {
			String format = "Valid from starts in the future for condition %s";
			return String.format(format, condition);
		}
		Date validUntil = condition.getValidUntil();
		if (validUntil == null) {
			String format = "Valid until not specified for condition %s";
			return String.format(format, condition);
		}
		if (validUntil.before(now)) {
			String format = "Valid until ends in the past for condition %s";
			return String.format(format, condition);
		}
		return null;
	}

}
