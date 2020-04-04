package org.eclipse.passage.lic.internal.equinox.requirements;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public final class BundleVendorTest {

	@Test
	public void readVendor() {
		FakeBundle bundle = new FakeBundle();
		assertEquals(//
				bundle.vendor, // $NON-NLS-1$
				new BundleVendor(bundle).get());
	}

	@Test(expected = NullPointerException.class)
	public void prohibitNull() {
		new BundleVendor(null);
	}

}
