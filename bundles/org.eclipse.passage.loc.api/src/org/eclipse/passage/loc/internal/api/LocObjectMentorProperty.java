package org.eclipse.passage.loc.internal.api;

import java.util.function.Supplier;

public final class LocObjectMentorProperty implements Supplier<String> {

	@Override
	public String get() {
		return "locMentor"; //$NON-NLS-1$
	}

}
