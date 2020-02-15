package org.eclipse.passage.loc.billing.internal.tests;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserLicenseDescriptor;
import org.eclipse.passage.loc.billing.internal.core.BillingService;
import org.eclipse.passage.loc.billing.internal.core.BillingServiceImpl;
import org.junit.Test;

public class BillingServiceTest {

	@Test
	public void getUserLicensesTest() {
		BillingService service = new BillingServiceImpl();
		
		String identifier1 = "foo"; //$NON-NLS-1$
		String identifier2 = "bar"; //$NON-NLS-1$
		String identifier3 = "baz"; //$NON-NLS-1$

		Set<UserLicenseDescriptor> licenses = new HashSet<UserLicenseDescriptor>();
		licenses.add(new FakeLicenseDescriptor(identifier1)); 
		licenses.add(new FakeLicenseDescriptor(identifier2)); 
		licenses.add(new FakeLicenseDescriptor(identifier3));
		
		Set<String> identifiers = new HashSet<String>();
		identifiers.add(identifier1);
		identifiers.add(identifier2);
		identifiers.add(identifier3);
		
		UserDescriptor user = new FakeUser(licenses);
		assertEquals(identifiers, service.getUserLicenses(user.getUserLicenses()));
	}

}
