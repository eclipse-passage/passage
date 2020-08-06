package org.eclipse.passage.lbc.api;

import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;

/**
 * @since 1.0
 */
public class NoSuchTakenLicenseException extends Exception {

	public NoSuchTakenLicenseException(ConditionPack conditionPack) {
		super("No such taken license on the server: " + conditionPack.toString()); //$NON-NLS-1$
	}

}
