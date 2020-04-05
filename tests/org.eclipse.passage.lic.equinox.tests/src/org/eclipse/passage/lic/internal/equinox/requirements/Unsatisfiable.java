package org.eclipse.passage.lic.internal.equinox.requirements;

import java.util.function.Predicate;

import org.eclipse.passage.lic.internal.api.requirements.Requirement;

@SuppressWarnings("restriction")
final class Unsatisfiable implements Predicate<Requirement> {

	@Override
	public boolean test(Requirement requirement) {
		String identifier = requirement.feature().identifier();
		if (identifier.length() < 4) {
			return false;
		}
		return Long.toHexString(System.currentTimeMillis())//
				.startsWith(identifier.substring(0, 4));
	}

}
