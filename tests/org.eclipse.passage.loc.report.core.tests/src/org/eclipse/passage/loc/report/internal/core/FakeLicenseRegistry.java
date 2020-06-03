package org.eclipse.passage.loc.report.internal.core;

import java.util.List;

import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.registry.LicenseRegistry;

public final class FakeLicenseRegistry implements LicenseRegistry {

	private final List<LicensePlanDescriptor> plans;

	public FakeLicenseRegistry(List<LicensePlanDescriptor> plans) {
		this.plans = plans;
	}

	@Override
	public Iterable<? extends LicensePlanDescriptor> getLicensePlans() {
		return plans;
	}

	@Override
	public LicensePlanDescriptor getLicensePlan(String licensePlanId) {
		return plans.stream()//
				.filter(plan -> plan.getIdentifier().equals(licensePlanId)) //
				.findFirst() //
				.get();
	}

}
