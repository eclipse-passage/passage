/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.licenses.model.EmptyPersonalLicensePack;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
import org.eclipse.passage.loc.report.internal.core.FakeLicenseRegistry;
import org.eclipse.passage.loc.report.internal.core.TestData;

@SuppressWarnings("restriction")
abstract class TestLicenses extends TestData<LicenseStorage> {

	protected List<LicensePlanDescriptor> plans = Collections.emptyList();
	protected List<UserDescriptor> users = Collections.emptyList();
	protected List<UserOriginDescriptor> companies = Collections.emptyList();
	protected final Date from;
	protected final Date to;

	protected TestLicenses(Date from, Date to) {
		this.from = from;
		this.to = to;
		initData();
	}

	protected abstract void initData();

	@Override
	public LicenseStorage storage() {
		Licenses storage = new Licenses();
		storage.installLicenseRegistry(new FakeLicenseRegistry(plans));
		return storage;
	}

	abstract LicensePlanReportParameters params();

	protected LicensePlanDescriptor plan(String id) {
		return plans.stream()//
				.filter(plan -> id.equals(plan.getIdentifier()))//
				.findFirst()//
				.get();
	}

	protected Optional<UserDescriptor> user(String segment) {
		return users.stream()//
				.filter(user -> user.getContact().getEmail().contains(segment))//
				.findFirst();
	}

	protected String header(boolean explain) {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.YYYY"); //$NON-NLS-1$
		return "License plan;Plan id;Personal licenses;Floating licenses" // //$NON-NLS-1$
				+ (explain //
						? String.format(//
								";Users (issue dates from %s to %s)", //$NON-NLS-1$
								format.format(from), //
								format.format(to)) //
						: "") //$NON-NLS-1$
				+ (explain ? ";Companies" : ""); //$NON-NLS-1$//$NON-NLS-2$
	}

	static final class Empty extends TestLicenses {

		protected Empty() {
			super(new MovedNow(date -> date.minus(2, ChronoUnit.MONTHS)).get(), //
					new MovedNow(date -> date.minus(1, ChronoUnit.MONTHS)).get());
		}

		@Override
		protected void initData() {
			// do nothing, all empty
		}

		@Override
		LicensePlanReportParameters params() {
			return new LicensePlanReportParameters(Collections.emptySet(), from, to, true);
		}

		@Override
		public Set<String> csv() {
			return Collections.singleton(header(true));
		}

	}

	static final class Some extends TestLicenses {

		protected Some() {
			super(new MovedNow(date -> date.minus(1, ChronoUnit.MONTHS)).get(), //
					new MovedNow(date -> date.plus(1, ChronoUnit.MONTHS)).get()//
			);

		}

		@Override
		protected void initData() {
			this.plans = Arrays.asList(//
					plan("plan-a", "Plan A"), //$NON-NLS-1$ //$NON-NLS-2$
					plan("plan-b", "Plan B"), //$NON-NLS-1$ //$NON-NLS-2$
					plan("plan-c", "Plan C") //$NON-NLS-1$ //$NON-NLS-2$
			);
			this.users = Arrays.asList(//
					user("evan", "evan@universe.com", "Evan Almighty"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					user("dorothea", "dorothea-the-elder@nest.ant", "Dorothea Vollenhovia"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					user("zena", "zena-the-queen@kindom.com", "Zena The queen") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			);
			this.companies = Arrays.asList(); // TODO
			issueLicenses();

		}

		private void issueLicenses() {
			UserDescriptor evan = user("evan").get(); //$NON-NLS-1$
			UserDescriptor dorothea = user("dorothea").get(); //$NON-NLS-1$
			UserDescriptor zena = user("zena").get(); //$NON-NLS-1$
			LicensePlanDescriptor planA = plan("plan-a"); //$NON-NLS-1$
			LicensePlanDescriptor planB = plan("plan-b"); //$NON-NLS-1$
			issuePersonal(planA, evan, new MovedNow(date -> date.plus(2, ChronoUnit.MONTHS)).get());
			issuePersonal(planB, evan, new Date());
			issuePersonal(planA, zena, new Date());
			issuePersonal(planB, zena, new Date());
			issuePersonal(planA, dorothea, new MovedNow(date -> date.minus(2, ChronoUnit.MONTHS)).get());
			issuePersonal(planB, dorothea, new Date());
		}

		@Override
		LicensePlanReportParameters params() {
			return new LicensePlanReportParameters(//
					new HashSet<>(Arrays.asList(//
							"plan-a", //$NON-NLS-1$
							"plan-b", //$NON-NLS-1$
							"plan-c" //$NON-NLS-1$
					)), //
					from, //
					to, //
					false);
		}

		@Override
		public Set<String> csv() {
			return new HashSet<>(Arrays.asList(//
					"Plan B;plan-b;3;0", //$NON-NLS-1$
					"Plan A;plan-a;1;0", //$NON-NLS-1$
					"Plan C;plan-c;0;0", //$NON-NLS-1$
					header(false)));
		}

		private void issuePersonal(LicensePlanDescriptor plan, UserDescriptor user, Date issued) {
			LicensedProduct product = new BaseLicensedProduct("pop", "1.0.1"); //$NON-NLS-1$ //$NON-NLS-2$
			PersonalLicensePack pack = new EmptyPersonalLicensePack().get();
			pack.getLicense().setPlan(plan.getIdentifier());
			pack.getLicense().getUser().setIdentifier(user.getIdentifier());
			pack.getLicense().getUser().setName(user.getContact().getName());
			pack.getLicense().getProduct().setIdentifier(product.identifier());
			pack.getLicense().getProduct().setVersion(product.version());
			pack.getLicense().setIssueDate(issued);
			@SuppressWarnings("unchecked")
			List<PersonalLicensePackDescriptor> personal = (List<PersonalLicensePackDescriptor>) plan.getPersonal();
			personal.add(pack);
		}
	}
}
