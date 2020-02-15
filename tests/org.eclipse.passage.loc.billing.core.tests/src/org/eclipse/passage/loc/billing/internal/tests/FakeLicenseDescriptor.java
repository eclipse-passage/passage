package org.eclipse.passage.loc.billing.internal.tests;

import java.util.Date;

import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserLicenseDescriptor;

public class FakeLicenseDescriptor implements UserLicenseDescriptor {

	private final String identifier;
	private final Date validFrom;
	private final Date validUntil;
	private final Date issueDate;

	public FakeLicenseDescriptor(String identifier, Date validFrom, Date validUntil, Date issueDate) {
		this.validUntil = validUntil;
		this.identifier = identifier;
		this.validFrom = validFrom;
		this.issueDate = issueDate;
	}

	public FakeLicenseDescriptor(String identifier, Date issueDate) {
		this(identifier, new Date(), new Date(), issueDate);
	}

	public FakeLicenseDescriptor(String identifier) {
		this(identifier, new Date());
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
		return validFrom;
	}

	@Override
	public Date getValidUntil() {
		return validUntil;
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
		return issueDate;
	}

	@Override
	public UserDescriptor getUser() {
		throw new UnsupportedOperationException();
	}

}
