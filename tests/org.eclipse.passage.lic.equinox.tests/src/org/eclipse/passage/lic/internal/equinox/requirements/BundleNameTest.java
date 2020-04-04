package org.eclipse.passage.lic.internal.equinox.requirements;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public final class BundleNameTest {

	@Test
	public void readName() {
		FakeBundle bundle = new FakeBundle();
		assertEquals(//
				bundle.name, // $NON-NLS-1$
				new BundleName(bundle).get());
	}

	@Test(expected = NullPointerException.class)
	public void prohibitNull() {
		new BundleName(null);
	}

}
