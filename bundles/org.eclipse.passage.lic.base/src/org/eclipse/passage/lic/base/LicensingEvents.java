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

import org.eclipse.passage.lic.runtime.ConfigurationRequirement;
import org.eclipse.passage.lic.runtime.FeaturePermission;
import org.eclipse.passage.lic.runtime.LicensingCondition;

public class LicensingEvents {
	
	private LicensingEvents() {
		//block
	}

	/**
	 * Topic separator character
	 */
	public static final String TOPIC_SEP = "/"; //$NON-NLS-1$

	/**
	 * Wild card character for matching all sub topics
	 */
	public static final String ALL_SUB_TOPICS = "*"; //$NON-NLS-1$

	/**
	 * Base name of all Licensing events
	 */
	public static final String LicensingTopicBase = "ru/arsysop/passage/lic/base"; //$NON-NLS-1$
	
	public static final class LicensingLifeCycle {
		
		private LicensingLifeCycle() {
			//block
		}

		/**
		 * Base name for all Licensing life cycle events
		 */
		public static final String TOPIC = LicensingTopicBase + TOPIC_SEP + "LifeCycle"; //$NON-NLS-1$

		/**
		 * Sent when {@link ConfigurationRequirement}(s) are resolved
		 */
		public static final String REQUIREMENTS_RESOLVED = TOPIC + TOPIC_SEP + "requirementsResolved"; //$NON-NLS-1$

		/**
		 * Sent when {@link LicensingCondition}(s) are extracted
		 */
		public static final String CONDITIONS_EXTRACTED = TOPIC + TOPIC_SEP + "conditionsExtracted"; //$NON-NLS-1$

		/**
		 * Sent when {@link LicensingCondition}(s) are evaluated
		 */
		public static final String CONDITIONS_EVALUATED = TOPIC + TOPIC_SEP + "conditionsEvaluated"; //$NON-NLS-1$

		/**
		 * Sent when {@link FeaturePermission}(s) are examined
		 */
		public static final String PERMISSIONS_EXAMINED = TOPIC + TOPIC_SEP + "permissionsExamined"; //$NON-NLS-1$

		/**
		 * Sent when restrictions are executed
		 */
		public static final String RESTRICTIONS_EXECUTED = TOPIC + TOPIC_SEP + "restrictionsExecuted"; //$NON-NLS-1$

	}
	
	public static final class LicensingConditionEvents {
	
		private LicensingConditionEvents() {
			//block
		}

		/**
		 * Base name for all Licensing condition events
		 */
		public static final String TOPIC = LicensingTopicBase + TOPIC_SEP + "ConditionEvents"; //$NON-NLS-1$

		/**
		 * Sent when conditions are rejected as invalid
		 */
		public static final String CONDITIONS_NOT_VALID = TOPIC + TOPIC_SEP + "conditionsNotValid"; //$NON-NLS-1$
		/**
		 * Sent when conditions are rejected as invalid
		 */
		public static final String CONDITIONS_NOT_EVALUATED = TOPIC + TOPIC_SEP + "conditionsNotEvaluated"; //$NON-NLS-1$
		/**
		 * Sent when conditions are leased
		 */
		public static final String CONDITIONS_LEASED = TOPIC + TOPIC_SEP + "conditionsLeased"; //$NON-NLS-1$

	}

}
