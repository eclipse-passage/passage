package org.eclipse.passage.loc.billing.internal.tests;

import java.util.Date;

import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserLicenseDescriptor;

public class FakeLicenseDescriptor implements UserLicenseDescriptor {

	private final String identifier;

	public FakeLicenseDescriptor(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public String getPlanIdentifier() {
		return identifier;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getValidUntil() {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public Date getIssueDate() {
		throw new UnsupportedOperationException();
	}

	@Override
	public UserDescriptor getUser() {
		throw new UnsupportedOperationException();
	}

}
