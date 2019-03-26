package org.eclipse.passage.lic.base.tests.conditions;

import static org.junit.Assert.fail;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.passage.lic.base.conditions.ConditionMiners;
import org.eclipse.passage.lic.base.io.LicensingPaths;
import org.eclipse.passage.lic.base.tests.LicensningBaseTests;
import org.eclipse.passage.lic.runtime.LicensingException;
import org.junit.Test;

public class ConditionMinersTest extends LicensningBaseTests {

	@Test
	public void testCollectPacksNegative() {
		collectPacksInvalid(null, (String) null);
		collectPacksInvalid(null, LicensingPaths.EXTENSION_LICENSE_DECRYPTED);
		collectPacksInvalid(null, LicensingPaths.EXTENSION_LICENSE_ENCRYPTED);
	}

	@SuppressWarnings("nls")
	private void collectPacksInvalid(Path path, String... extension) {
		try {
			ConditionMiners.collectPacks(path, extension);
			fail("Invalid arguments accepted");
		} catch (LicensingException e) {
			// expected
		}
	}

	@Test
	public void testCollectPacks() throws LicensingException {
		ConditionMiners.collectPacks(Paths.get(baseFolder.getRoot().getAbsolutePath()), (String) null);
		ConditionMiners.collectPacks(Paths.get(baseFolder.getRoot().getAbsolutePath()), (String[]) null);
	}

}
