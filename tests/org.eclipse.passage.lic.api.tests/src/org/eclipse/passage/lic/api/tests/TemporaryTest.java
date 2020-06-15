package org.eclipse.passage.lic.api.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public final class TemporaryTest {
	/**
	 * <p>
	 * There only contract (meaning abstract) tests in this bundle. Nonetheless,
	 * it's still recognized as 'tests' bundle. All the special treatments are
	 * applied: sure-fire plug-in is applied. This one fails on a bundle where is
	 * nothing to run.
	 * </p>
	 * 
	 * <p>
	 * Thus we add a single deadly stupid but workable test especially for sure-fire
	 * to make it go.
	 * </p>
	 */
	@Test
	public void singleWorkableTestInABundleFullOfAbstractTest() {
		assertTrue(System.currentTimeMillis() > 0);
	}

}
