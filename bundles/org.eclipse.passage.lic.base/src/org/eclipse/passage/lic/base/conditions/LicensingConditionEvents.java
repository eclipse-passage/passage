package org.eclipse.passage.lic.base.conditions;

import org.eclipse.passage.lic.base.LicensingEvents;

public final class LicensingConditionEvents {

	private LicensingConditionEvents() {
		//block
	}

	/**
	 * Base name for all Licensing condition events
	 */
	public static final String TOPIC = LicensingEvents.LicensingTopicBase + LicensingEvents.TOPIC_SEP + "ConditionEvents"; //$NON-NLS-1$

	/**
	 * Sent when conditions are rejected as invalid
	 */
	public static final String CONDITIONS_NOT_VALID = TOPIC + LicensingEvents.TOPIC_SEP + "conditionsNotValid"; //$NON-NLS-1$
	/**
	 * Sent when conditions are rejected as invalid
	 */
	public static final String CONDITIONS_NOT_EVALUATED = TOPIC + LicensingEvents.TOPIC_SEP + "conditionsNotEvaluated"; //$NON-NLS-1$
	/**
	 * Sent when conditions are leased
	 */
	public static final String CONDITIONS_LEASED = TOPIC + LicensingEvents.TOPIC_SEP + "conditionsLeased"; //$NON-NLS-1$

}