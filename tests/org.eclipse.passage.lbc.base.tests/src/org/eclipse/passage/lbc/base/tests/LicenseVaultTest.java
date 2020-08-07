package org.eclipse.passage.lbc.base.tests;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.eclipse.passage.lbc.internal.base.BaseLicenseVault;
import org.junit.Test;

public class LicenseVaultTest {

	@Test
	public void availableLicenses() {
		new BaseLicenseVault().availableLicenses(new FakeMiningRequest()).forEach((conditionPack) -> {
			assertEquals("floating", conditionPack.origin()); //$NON-NLS-1$
			assertEquals(Collections.emptyList(), conditionPack.conditions());
		});
	}

}
