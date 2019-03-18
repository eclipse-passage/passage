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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.passage.lic.base.BaseLicensingResult;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.base.conditions.BaseConditionMinerRegistry;
import org.eclipse.passage.lic.base.restrictions.RestrictionVerdicts;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.LicensingException;
import org.eclipse.passage.lic.runtime.LicensingReporter;
import org.eclipse.passage.lic.runtime.LicensingResult;
import org.eclipse.passage.lic.runtime.access.AccessManager;
import org.eclipse.passage.lic.runtime.access.AccessManagerEvents;
import org.eclipse.passage.lic.runtime.access.FeaturePermission;
import org.eclipse.passage.lic.runtime.access.PermissionEmitter;
import org.eclipse.passage.lic.runtime.access.PermissionExaminer;
import org.eclipse.passage.lic.runtime.conditions.ConditionEvents;
import org.eclipse.passage.lic.runtime.conditions.ConditionMiner;
import org.eclipse.passage.lic.runtime.conditions.ConditionMinerRegistry;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.eclipse.passage.lic.runtime.requirements.LicensingRequirement;
import org.eclipse.passage.lic.runtime.requirements.RequirementResolver;
import org.eclipse.passage.lic.runtime.restrictions.RestrictionExecutor;
import org.eclipse.passage.lic.runtime.restrictions.RestrictionVerdict;

public abstract class BaseAccessManager implements AccessManager {

	private LicensingReporter licensingReporter;

	private final List<RequirementResolver> requirementResolvers = new ArrayList<>();
	private ConditionMinerRegistry conditionMinerRegistry = new BaseConditionMinerRegistry();
	private final Map<String, PermissionEmitter> permissionEmitters = new HashMap<>();
	private final List<RestrictionExecutor> restrictionExecutors = new ArrayList<>();

	private PermissionExaminer examiner;

	protected void bindLicensingReporter(LicensingReporter desk) {
		this.licensingReporter = desk;
	}

	protected void unbindLicensingReporter(LicensingReporter desk) {
		if (this.licensingReporter == desk) {
			this.licensingReporter = null;
		}
	}

	protected void bindRequirementResolver(RequirementResolver requirementResolver) {
		requirementResolvers.add(requirementResolver);
	}

	protected void unbindRequirementResolver(RequirementResolver configurationResolver) {
		requirementResolvers.remove(configurationResolver);
	}

	protected void bindConditionMinerRegistry(ConditionMinerRegistry registry) {
		this.conditionMinerRegistry = registry;
	}

	protected void unbindConditionMinerRegistry(ConditionMinerRegistry registry) {
		if (this.conditionMinerRegistry == registry) {
			this.conditionMinerRegistry = null;
		}
	}

	protected void bindPermissionEmitter(PermissionEmitter permissionEmitter, Map<String, Object> properties) {
		Object conditionType = properties.get(LICENSING_CONDITION_TYPE_ID);
		String type = String.valueOf(conditionType);
		// FIXME: check permissions
		permissionEmitters.put(type, permissionEmitter);
	}

	protected void unbindPermissionEmitter(PermissionEmitter permissionEmitter, Map<String, Object> properties) {
		Object conditionType = properties.get(LICENSING_CONDITION_TYPE_ID);
		String type = String.valueOf(conditionType);
		PermissionEmitter removed = permissionEmitters.remove(type);
		if (permissionEmitter != removed) {
			// FIXME: error
		}
	}

	protected void bindPermissionExaminer(PermissionExaminer permissionExaminer) {
		this.examiner = permissionExaminer;
	}

	protected void unbindPermissionExaminer(PermissionExaminer permissionExaminer) {
		if (permissionExaminer == examiner) {
			this.examiner = null;
		} else {
			// FIXME: error
			this.examiner = null;
		}
	}

	protected void bindRestrictionExecutor(RestrictionExecutor restrictionExecutor) {
		restrictionExecutors.add(restrictionExecutor);
	}

	protected void unbindRestrictionExecutor(RestrictionExecutor restrictionExecutor) {
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
		for (RequirementResolver configurationResolver : requirementResolvers) {
			Iterable<LicensingRequirement> requirements = configurationResolver
					.resolveLicensingRequirements(configuration);
			for (LicensingRequirement requirement : requirements) {
				result.add(requirement);
			}
		}
		List<LicensingRequirement> unmodifiable = Collections.unmodifiableList(result);
		postEvent(AccessManagerEvents.REQUIREMENTS_RESOLVED, unmodifiable);
		return unmodifiable;
	}

	@Override
	public Iterable<LicensingCondition> extractConditions(LicensingConfiguration configuration) {
		BaseLicensingResult holder = LicensingResults.createHolder("Extract conditions operation",
				getClass().getName());
		List<LicensingCondition> mined = new ArrayList<>();
		Iterable<ConditionMiner> conditionMiners = conditionMinerRegistry.getConditionMiners();
		for (ConditionMiner conditionMiner : conditionMiners) {
			Iterable<LicensingCondition> conditions = conditionMiner.extractLicensingConditions(configuration);
			if (conditions == null) {
				String source = conditionMiner.getClass().getName();
				String message = "Invalid condition miner";
				BaseLicensingResult error = LicensingResults.createError(message, source, new NullPointerException());
				holder.addChild(error);
				continue;
			}
			for (LicensingCondition condition : conditions) {
				mined.add(condition);
			}
		}
		Iterable<LicensingResult> children = holder.getChildren();
		postEvent(ConditionEvents.CONDITIONS_IGNORED, children);

		List<LicensingCondition> unmodifiable = Collections.unmodifiableList(mined);
		postEvent(AccessManagerEvents.CONDITIONS_EXTRACTED, unmodifiable);
		return unmodifiable;
	}

	@Override
	public Iterable<FeaturePermission> evaluateConditions(Iterable<LicensingCondition> conditions,
			LicensingConfiguration configuration) {
		List<FeaturePermission> result = new ArrayList<>();
		if (conditions == null) {
			String message = "Evaluation rejected for invalid conditions";
			logError(message, new IllegalArgumentException());
			List<FeaturePermission> empty = Collections.emptyList();
			postEvent(AccessManagerEvents.CONDITIONS_EVALUATED, empty);
			return empty;
		}
		List<FeaturePermission> unmodifiable = Collections.unmodifiableList(result);
		Map<String, List<LicensingCondition>> map = new HashMap<>();
		List<LicensingCondition> invalid = new ArrayList<>();
		for (LicensingCondition condition : conditions) {
			if (condition == null) {
				String message = "Evaluation rejected for invalid condition";
				logError(message, new NullPointerException());
				continue;
			}
			String validate = validate(condition);
			if (validate == null) {
				String type = condition.getConditionType();
				List<LicensingCondition> list = map.computeIfAbsent(type, key -> new ArrayList<>());
				list.add(condition);
			} else {
				logError(validate, null);
				invalid.add(condition);
			}
		}
		Set<String> types = map.keySet();
		for (String type : types) {
			PermissionEmitter emitter = permissionEmitters.get(type);
			if (emitter == null) {
				String message = String.format("No permission emitter available for type %s", type);
				logError(message, new NullPointerException());
				continue;
			}
			List<LicensingCondition> mappedConditions = map.get(type);
			try {
				Iterable<FeaturePermission> permissions = emitter.emitPermissions(mappedConditions, configuration);
				for (FeaturePermission permission : permissions) {
					result.add(permission);
				}
			} catch (LicensingException e) {
				logError("Failed to evaluate conditions", e);
				postEvent(ConditionEvents.CONDITIONS_NOT_VALID, mappedConditions);
			}
		}
		postEvent(AccessManagerEvents.CONDITIONS_EVALUATED, unmodifiable);
		return unmodifiable;
	}

	@Override
	public Iterable<RestrictionVerdict> examinePermissons(Iterable<LicensingRequirement> requirements,
			Iterable<FeaturePermission> permissions, LicensingConfiguration configuration) {
		if (configuration == null) {
			logError("Invalid configuration", new NullPointerException());
			List<RestrictionVerdict> examined = Collections.emptyList();
			postEvent(AccessManagerEvents.PERMISSIONS_EXAMINED, examined);
			return examined;
		}
		if (requirements == null) {
			logError("Invalid configuration requirements", new NullPointerException());
			List<RestrictionVerdict> examined = Collections.emptyList();
			postEvent(AccessManagerEvents.PERMISSIONS_EXAMINED, examined);
			return examined;
		}
		if (examiner == null) {
			String message = String.format("No permission examiner defined, rejecting all %s", requirements);
			logError(message, new NullPointerException());
			List<RestrictionVerdict> verdicts = new ArrayList<>();
			for (LicensingRequirement requirement : requirements) {
				if (requirement == null) {
					logError("Invalid configuration requirement ignored", new NullPointerException());
					continue;
				}
				RestrictionVerdict verdict = RestrictionVerdicts.createError(requirement,
						RestrictionVerdicts.CODE_CONFIGURATION_ERROR);
				verdicts.add(verdict);
			}
			postEvent(AccessManagerEvents.PERMISSIONS_EXAMINED, Collections.unmodifiableList(verdicts));
			return verdicts;
		}
		Iterable<RestrictionVerdict> examined = examiner.examine(requirements, permissions);
		postEvent(AccessManagerEvents.PERMISSIONS_EXAMINED, examined);
		return examined;
	}

	@Override
	public LicensingResult executeRestrictions(Iterable<RestrictionVerdict> restrictions) {
		String source = getClass().getName();
		BaseLicensingResult result = LicensingResults.createHolder("Executing restrinctions", source);
		for (RestrictionExecutor executor : restrictionExecutors) {
			try {
				executor.execute(restrictions);
			} catch (Exception e) {
				String message = String.format("%s failed to execute %s", executor, restrictions);
				BaseLicensingResult error = LicensingResults.createError(message, source, e);
				result.addChild(error);
			}
		}
		postEvent(AccessManagerEvents.RESTRICTIONS_EXECUTED, restrictions);
		return result;
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

	protected abstract void postEvent(String topic, Object data);

	protected abstract void logError(String message, Throwable e);

}
