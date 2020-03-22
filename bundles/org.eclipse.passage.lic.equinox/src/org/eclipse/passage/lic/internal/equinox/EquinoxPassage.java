package org.eclipse.passage.lic.internal.equinox;

import org.eclipse.passage.lic.internal.api.Passage;

@SuppressWarnings("restriction")
public final class EquinoxPassage implements Passage {

	@Override
	public boolean canUse(String featureId) {
		return false;
	}

	@Override
	public void checkLicense(String featureId) {
		throw new UnsupportedOperationException();
	}

}
