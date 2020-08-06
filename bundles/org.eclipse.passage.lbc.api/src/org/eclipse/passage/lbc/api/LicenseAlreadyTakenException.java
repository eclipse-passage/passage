package org.eclipse.passage.lbc.api;

import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;

/**
 * @since 1.0
 */
public class LicenseAlreadyTakenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 950020125519095982L;

	public LicenseAlreadyTakenException(ConditionPack conditionPack) {
		super("License is already taken: " + conditionPack.toString()); //$NON-NLS-1$
	}

}
