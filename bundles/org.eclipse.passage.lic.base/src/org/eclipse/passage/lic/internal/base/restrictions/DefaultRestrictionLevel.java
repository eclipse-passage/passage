package org.eclipse.passage.lic.internal.base.restrictions;

import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.restrictions.RestrictionLevel;

@SuppressWarnings("restriction")
public final class DefaultRestrictionLevel implements Supplier<RestrictionLevel> {

	@Override
	public RestrictionLevel get() {
		return new RestrictionLevel.Warning();
	}

}
