package org.eclipse.passage.loc.report.internal.core;

import java.util.Date;

import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserLicenseDescriptor;

public final class FakeLicenseDescriptor implements UserLicenseDescriptor {

	private final LicensePlanDescriptor plan;
	private final UserDescriptor user;
	private final Date issue;

	public FakeLicenseDescriptor(LicensePlanDescriptor plan, UserDescriptor user, Date issue) {
		this.plan = plan;
		this.user = user;
		this.issue = issue;
	}

	@Override
	public String getPlanIdentifier() {
		return plan.getIdentifier();
	}

	@Override
	public Date getIssueDate() {
		return issue;
	}

	@Override
	public UserDescriptor getUser() {
		return user;
	}

	@Override
	public String getProductIdentifier() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getProductVersion() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Date getValidFrom() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Date getValidUntil() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getConditionType() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getConditionExpression() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getPackIdentifier() {
		throw new UnsupportedOperationException();
	}

}
