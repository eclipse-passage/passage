package org.eclipse.passage.loc.report.internal.core;

import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor;

public final class FakeLicensePlanDescriptor implements LicensePlanDescriptor {

	private final String identifier;
	private final String name;

	public FakeLicensePlanDescriptor(String identifier, String name) {
		this.identifier = identifier;
		this.name = name;
	}

	@Override
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<? extends LicensePlanFeatureDescriptor> getLicensePlanFeatures() {
		throw new UnsupportedOperationException();
	}

}
