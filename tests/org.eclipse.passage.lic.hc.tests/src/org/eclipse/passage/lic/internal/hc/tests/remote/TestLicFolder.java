package org.eclipse.passage.lic.internal.hc.tests.remote;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;

final class TestLicFolder implements Supplier<Path> {

	@Override
	public Path get() {
		return Paths.get("resource").resolve("lics"); //$NON-NLS-1$ //$NON-NLS-2$
	}

}