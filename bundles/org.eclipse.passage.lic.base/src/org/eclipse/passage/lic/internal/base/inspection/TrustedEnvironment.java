package org.eclipse.passage.lic.internal.base.inspection;

import org.eclipse.passage.lic.api.EvaluationType;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.inspection.EnvironmentProperty;
import org.eclipse.passage.lic.api.inspection.RuntimeEnvironment;

public final class TrustedEnvironment implements RuntimeEnvironment {

	@Override
	public EvaluationType id() {
		return new TrustedEvaluationType().get();
	}

	@Override
	public String state() throws LicensingException {
		return ""; //$NON-NLS-1$
	}

	@Override
	public boolean isAssuptionTrue(EnvironmentProperty property, String value) throws LicensingException {
		if (new Trusted.Scope().equals(property)) {
			return "*".equals(value); //$NON-NLS-1$
		}
		return false;
	}

}
