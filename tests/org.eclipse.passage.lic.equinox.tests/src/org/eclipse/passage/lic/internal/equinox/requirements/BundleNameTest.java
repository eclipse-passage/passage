package org.eclipse.passage.lic.internal.equinox.requirements;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public final class BundleNameTest {

	@Test
	public void readName() {
		assertEquals(//
				"Data for Passage LIC Equinox requirements tests", //$NON-NLS-1$
				new BundleName(new DataBundle().get()).get());
	}

	@Test(expected = NullPointerException.class)
	public void prohibitNull() {
		new BundleName(null);
	}

}
