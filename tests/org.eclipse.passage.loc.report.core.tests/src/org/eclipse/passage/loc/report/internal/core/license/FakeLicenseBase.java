package org.eclipse.passage.loc.report.internal.core.license;

import java.util.List;

import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.users.UserLicenseDescriptor;

final class FakeLicenseBase implements LicenseStorage {

	private final List<UserLicenseDescriptor> licenses;
	private final List<LicensePlanDescriptor> plans;

	public FakeLicenseBase(TestLicenses data) {
		this.licenses = data.licenses();
		this.plans = data.plans();
	}

	@Override
	public List<UserLicenseDescriptor> licenses(String plan) {
		return licenses;
	}

	@Override
	public List<LicensePlanDescriptor> plans() {
		return plans;
	}

	@Override
	public LicensePlanDescriptor plan(String plan) {
		return plans.stream()//
				.filter(p -> plan.equals(p.getIdentifier()))//
				.findFirst()//
				.get();
	}

}
