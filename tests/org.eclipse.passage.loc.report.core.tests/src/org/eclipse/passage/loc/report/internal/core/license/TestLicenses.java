package org.eclipse.passage.loc.report.internal.core.license;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.users.UserLicenseDescriptor;
import org.eclipse.passage.loc.report.internal.core.TestData;

abstract class TestLicenses implements TestData<LicenseStorage> {

	private final List<UserLicenseDescriptor> licenses;
	private final List<LicensePlanDescriptor> plans;

	protected TestLicenses(List<UserLicenseDescriptor> licenses, List<LicensePlanDescriptor> plans) {
		this.licenses = licenses;
		this.plans = plans;
	}

	@Override
	public Set<String> csv() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LicenseStorage storage() {
		return new FakeLicenseBase(this);
	}

	List<UserLicenseDescriptor> licenses() {
		return licenses;
	}

	List<LicensePlanDescriptor> plans() {
		return plans;
	}

	static final class Empty extends TestLicenses {

		protected Empty() {
			super(Collections.emptyList(), Collections.emptyList());
		}

	}

}
