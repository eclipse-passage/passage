/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.report.internal.core.license;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.loc.report.internal.core.FakeLicenseDescriptor;
import org.eclipse.passage.loc.report.internal.core.FakeLicensePlanDescriptor;
import org.eclipse.passage.loc.report.internal.core.FakeLicenseRegistry;
import org.eclipse.passage.loc.report.internal.core.FakeUserDescriptor;
import org.eclipse.passage.loc.report.internal.core.FakeUserRegistry;
import org.eclipse.passage.loc.report.internal.core.TestData;

abstract class TestLicenses implements TestData<LicenseStorage> {

	private final List<LicensePlanDescriptor> plans;
	protected final List<UserDescriptor> users;

	protected TestLicenses(List<LicensePlanDescriptor> plans, List<UserDescriptor> users) {
		this.plans = plans;
		this.users = users;
	}

	@Override
	public LicenseStorage storage() {
		Licenses storage = new Licenses();
		storage.installLicenseRegistry(new FakeLicenseRegistry(plans));
		storage.installUserRegistry(new FakeUserRegistry(users));
		return storage;
	}

	abstract LicensePlanReportParameters params();

	protected Optional<LicensePlanDescriptor> plan(String id) {
		return plans.stream()//
				.filter(plan -> id.equals(plan.getIdentifier()))//
				.findFirst();
	}

	protected Optional<UserDescriptor> user(String segment) {
		return users.stream()//
				.filter(user -> user.getEmail().contains(segment))//
				.findFirst();
	}

	protected String header(boolean explain) {
		return "License plan;Plan id;Amount of licenses" // //$NON-NLS-1$
				+ (explain ? ";Users" : ""); //$NON-NLS-1$ //$NON-NLS-2$
	}

	protected Date movedNow(Function<LocalDate, LocalDate> move) {
		return Date.from(//
				move.apply(LocalDate.now())//
						.atStartOfDay()//
						.atZone(ZoneId.systemDefault())//
						.toInstant());
	}

	static final class Empty extends TestLicenses {

		protected Empty() {
			super(Collections.emptyList(), Collections.emptyList());
		}

		@Override
		LicensePlanReportParameters params() {
			return new LicensePlanReportParameters(//
					Collections.emptySet(), //
					movedNow(date -> date.minus(2, ChronoUnit.MONTHS)), //
					movedNow(date -> date.minus(1, ChronoUnit.MONTHS)), //
					true);
		}

		@Override
		public Set<String> csv() {
			return Collections.singleton(header(true));
		}

	}

	static final class Some extends TestLicenses {

		protected Some() {
			super(//
					Arrays.asList(//
							new FakeLicensePlanDescriptor("plan-a", "Plan A"), //$NON-NLS-1$ //$NON-NLS-2$
							new FakeLicensePlanDescriptor("plan-b", "Plan B"), //$NON-NLS-1$ //$NON-NLS-2$
							new FakeLicensePlanDescriptor("plan-c", "Plan C") //$NON-NLS-1$ //$NON-NLS-2$
					), //
					Arrays.asList(//
							new FakeUserDescriptor("evan@universe.com", "Evan Almighty"), //$NON-NLS-1$ //$NON-NLS-2$
							new FakeUserDescriptor("dorothea-the-elder@nest.ant", "Dorothea Vollenhovia"), //$NON-NLS-1$ //$NON-NLS-2$
							new FakeUserDescriptor("zena-the-queen@kindom.com", "Zena") //$NON-NLS-1$ //$NON-NLS-2$
					)//
			);
			issueLicenses();
		}

		private void issueLicenses() {
			UserDescriptor evan = user("evan").get(); //$NON-NLS-1$
			UserDescriptor dorothea = user("dorothea").get(); //$NON-NLS-1$
			UserDescriptor zena = user("zena").get(); //$NON-NLS-1$
			LicensePlanDescriptor planA = plan("plan-a").get(); //$NON-NLS-1$
			LicensePlanDescriptor planB = plan("plan-b").get(); //$NON-NLS-1$
			Arrays.asList(//
					new FakeLicenseDescriptor(planA, evan, movedNow(date -> date.plus(2, ChronoUnit.MONTHS))), //
					new FakeLicenseDescriptor(planB, evan, new Date()), //
					new FakeLicenseDescriptor(planA, zena, new Date()), //
					new FakeLicenseDescriptor(planB, zena, new Date()), //
					new FakeLicenseDescriptor(planA, dorothea, movedNow(date -> date.minus(2, ChronoUnit.MONTHS))), //
					new FakeLicenseDescriptor(planB, dorothea, new Date()))//
					.forEach(lic -> {
						((FakeUserDescriptor) lic.getUser()).bindLicense(lic);
					});
		}

		@Override
		LicensePlanReportParameters params() {
			return new LicensePlanReportParameters(//
					new HashSet<>(Arrays.asList(//
							"plan-a", //$NON-NLS-1$
							"plan-b", //$NON-NLS-1$
							"plan-c" //$NON-NLS-1$
					)), //
					movedNow(date -> date.minus(1, ChronoUnit.MONTHS)), //
					movedNow(date -> date.plus(1, ChronoUnit.MONTHS)), //
					false);
		}

		/**
		 * tested: 1) date-range filtering 2) zero-rows absence
		 */
		@Override
		public Set<String> csv() {
			return new HashSet<>(Arrays.asList(//
					"Plan B;plan-b;3", //$NON-NLS-1$
					"Plan A;plan-a;1", //$NON-NLS-1$
					header(false)));
		}

	}

}
