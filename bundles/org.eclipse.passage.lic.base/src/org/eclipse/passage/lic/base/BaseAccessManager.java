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
package org.eclipse.passage.lic.base;

import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_CONDITION_TYPE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.passage.lic.base.LicensingEvents.LicensingLifeCycle;
import org.eclipse.passage.lic.runtime.AccessManager;
import org.eclipse.passage.lic.runtime.ConditionEvaluator;
import org.eclipse.passage.lic.runtime.ConditionMiner;
import org.eclipse.passage.lic.runtime.ConfigurationRequirement;
import org.eclipse.passage.lic.runtime.ConfigurationResolver;
import org.eclipse.passage.lic.runtime.FeaturePermission;
import org.eclipse.passage.lic.runtime.LicensingCondition;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.PermissionExaminer;
import org.eclipse.passage.lic.runtime.RestrictionExecutor;
import org.eclipse.passage.lic.runtime.RestrictionVerdict;

public abstract class BaseAccessManager implements AccessManager {

	private final List<ConfigurationResolver> configurationResolvers = new ArrayList<>();
	private final List<ConditionMiner> conditionMiners = new ArrayList<>();
	private final Map<String, ConditionEvaluator> conditionEvaluators = new HashMap<>();
	private final List<RestrictionExecutor> restrictionExecutors = new ArrayList<>();

	private PermissionExaminer permissionExaminer;

	protected void bindConfigurationResolver(ConfigurationResolver configurationResolver) {
		configurationResolvers.add(configurationResolver);
	}

	protected void unbindConfigurationResolver(ConfigurationResolver configurationResolver) {
		configurationResolvers.remove(configurationResolver);
	}

	protected void bindConditionMiner(ConditionMiner conditionMiner) {
		conditionMiners.add(conditionMiner);
	}

	protected void unbindConditionMiner(ConditionMiner conditionMiner) {
		conditionMiners.remove(conditionMiner);
	}

	protected void bindConditionEvaluator(ConditionEvaluator conditionEvaluator, Map<String, Object> properties) {
		Object conditionType = properties.get(LICENSING_CONDITION_TYPE);
		String type = String.valueOf(conditionType);
		// FIXME: check permissions
		conditionEvaluators.put(type, conditionEvaluator);
	}

	protected void unbindConditionEvaluator(ConditionEvaluator conditionEvaluator, Map<String, Object> properties) {
		Object conditionType = properties.get(LICENSING_CONDITION_TYPE);
		String type = String.valueOf(conditionType);
		conditionEvaluators.remove(type);
	}

	protected void bindPermissionExaminer(PermissionExaminer permissionExaminer) {
		this.permissionExaminer = permissionExaminer;
	}

	protected void unbindPermissionExaminer(PermissionExaminer permissionExaminer) {
		this.permissionExaminer = null;
	}

	protected void bindRestrictionExecutor(RestrictionExecutor restrictionExecutor) {
		restrictionExecutors.add(restrictionExecutor);
	}

	protected void unbindRestrictionExecutor(RestrictionExecutor restrictionExecutor) {
		restrictionExecutors.remove(restrictionExecutor);
	}

	@Override
	public void executeAccessRestrictions(LicensingConfiguration configuration) {
		Iterable<ConfigurationRequirement> requirements = resolveRequirements(configuration);
		Iterable<LicensingCondition> conditions = extractConditions(configuration);
		Iterable<FeaturePermission> permissions = evaluateConditions(conditions, configuration);
		Iterable<RestrictionVerdict> verdicts = examinePermissons(requirements, permissions, configuration);
		executeRestrictions(verdicts);
	}

	@Override
	public Iterable<ConfigurationRequirement> resolveRequirements(LicensingConfiguration configuration) {
		List<ConfigurationRequirement> result = new ArrayList<>();
		for (ConfigurationResolver configurationResolver : configurationResolvers) {
			Iterable<ConfigurationRequirement> requirements = configurationResolver
					.resolveConfigurationRequirements(configuration);
			for (ConfigurationRequirement requirement : requirements) {
				result.add(requirement);
			}
		}
		List<ConfigurationRequirement> unmodifiable = Collections.unmodifiableList(result);
		postEvent(LicensingLifeCycle.REQUIREMENTS_RESOLVED, unmodifiable);
		return unmodifiable;
	}

	@Override
	public Iterable<ConfigurationRequirement> resolveFeatureRequirements(String featureId,
			LicensingConfiguration configuration) {
		if (featureId == null) {
			return Collections.emptyList();
		}
		List<ConfigurationRequirement> result = new ArrayList<>();
		for (ConfigurationResolver configurationResolver : configurationResolvers) {
			Iterable<ConfigurationRequirement> requirements = configurationResolver
					.resolveConfigurationRequirements(configuration);
			for (ConfigurationRequirement requirement : requirements) {
				if (featureId.equals(requirement.getFeatureIdentifier())) {
					result.add(requirement);
				}
			}
		}
		List<ConfigurationRequirement> unmodifiable = Collections.unmodifiableList(result);
		postEvent(LicensingLifeCycle.REQUIREMENTS_RESOLVED, unmodifiable);
		return unmodifiable;
	}

	@Override
	public Iterable<LicensingCondition> extractConditions(LicensingConfiguration configuration) {
		List<LicensingCondition> result = new ArrayList<>();
		for (ConditionMiner conditionMiner : conditionMiners) {
			Iterable<LicensingCondition> conditions = conditionMiner.extractLicensingConditions(configuration);
			for (LicensingCondition condition : conditions) {
				result.add(condition);
			}
		}
		List<LicensingCondition> unmodifiable = Collections.unmodifiableList(result);
		postEvent(LicensingLifeCycle.CONDITIONS_EXTRACTED, unmodifiable);
		return unmodifiable;
	}

	@Override
	public Iterable<FeaturePermission> evaluateConditions(Iterable<LicensingCondition> conditions,
			LicensingConfiguration configuration) {
		List<FeaturePermission> result = new ArrayList<>();
		if (conditions == null) {
			String message = "Evaluation rejected for invalid conditions";
			logError(message, new NullPointerException());
			List<FeaturePermission> empty = Collections.emptyList();
			postEvent(LicensingLifeCycle.CONDITIONS_EVALUATED, empty);
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
			ConditionEvaluator evaluator = conditionEvaluators.get(type);
			if (evaluator == null) {
				String message = String.format("No evaluator available for type %s", type);
				logError(message, new NullPointerException());
				continue;
			}
			Iterable<FeaturePermission> permissions = evaluator.evaluateConditions(map.get(type), configuration);
			for (FeaturePermission permission : permissions) {
				result.add(permission);
			}
		}
		postEvent(LicensingLifeCycle.CONDITIONS_EVALUATED, unmodifiable);
		return unmodifiable;
	}

	@Override
	public Iterable<RestrictionVerdict> examineFeaturePermissons(String featureId,
			LicensingConfiguration configuration) {
		List<ConfigurationRequirement> requirements = new ArrayList<ConfigurationRequirement>();
		Iterable<ConfigurationRequirement> resolved = resolveRequirements(configuration);
		for (ConfigurationRequirement requirement : resolved) {
			if (featureId.equals(requirement.getFeatureIdentifier())) {
				requirements.add(requirement);
			}
		}
		List<LicensingCondition> conditions = new ArrayList<LicensingCondition>();
		Iterable<LicensingCondition> extractConditions = extractConditions(configuration);
		for (LicensingCondition condition : extractConditions) {
			if (featureId.equals(condition.getFeatureIdentifier())) {
				conditions.add(condition);
			}
		}
		Iterable<FeaturePermission> permissions = evaluateConditions(conditions, configuration);

		List<RestrictionVerdict> verdicts = new ArrayList<RestrictionVerdict>();
		Iterable<RestrictionVerdict> examined = examinePermissons(requirements, permissions, configuration);
		examined.forEach(verdicts::add);
		return verdicts;
	}

	@Override
	public Iterable<RestrictionVerdict> examinePermissons(Iterable<ConfigurationRequirement> requirements,
			Iterable<FeaturePermission> permissions, LicensingConfiguration configuration) {
		if (configuration == null) {
			logError("Invalid configuration", new NullPointerException());
			List<RestrictionVerdict> examined = Collections.emptyList();
			postEvent(LicensingLifeCycle.PERMISSIONS_EXAMINED, examined);
			return examined;
		}
		if (requirements == null) {
			logError("Invalid configuration requirements", new NullPointerException());
			List<RestrictionVerdict> examined = Collections.emptyList();
			postEvent(LicensingLifeCycle.PERMISSIONS_EXAMINED, examined);
			return examined;
		}
		if (permissionExaminer == null) {
			String message = String.format("No permission examiner defined, rejecting all %s", requirements);
			logError(message, new NullPointerException());
			List<RestrictionVerdict> verdicts = new ArrayList<>();
			for (ConfigurationRequirement requirement : requirements) {
				if (requirement == null) {
					logError("Invalid configuration requirement ignored", new NullPointerException());
					continue;
				}
				RestrictionVerdict verdict = RestrictionVerdicts.createError(requirement, RestrictionVerdicts.CODE_CONFIGURATION_ERROR);
				verdicts.add(verdict);
			}
			postEvent(LicensingLifeCycle.PERMISSIONS_EXAMINED, Collections.unmodifiableList(verdicts));
			return verdicts;
		}
		Iterable<RestrictionVerdict> examined = permissionExaminer.examine(requirements, permissions);
		postEvent(LicensingLifeCycle.PERMISSIONS_EXAMINED, examined);
		return examined;
	}

	@Override
	public void executeRestrictions(Iterable<RestrictionVerdict> restrictions) {
		for (RestrictionExecutor executor : restrictionExecutors) {
			try {
				executor.execute(restrictions);
			} catch (Exception e) {
				String message = String.format("%s failed to execute %s", executor, restrictions);
				logError(message, e);
			}
		}
		postEvent(LicensingLifeCycle.RESTRICTIONS_EXECUTED, restrictions);
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
