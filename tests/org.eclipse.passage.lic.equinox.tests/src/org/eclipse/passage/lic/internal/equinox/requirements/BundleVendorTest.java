package org.eclipse.passage.lic.internal.equinox.requirements;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public final class BundleVendorTest {

	@Test
	public void readVendor() {
		assertEquals(//
				"Eclipse Passage", //$NON-NLS-1$
				new BundleVendor(new DataBundle().get()).get());
	}

	@Test(expected = NullPointerException.class)
	public void prohibitNull() {
		new BundleVendor(null);
	}

}
