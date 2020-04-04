package org.eclipse.passage.lic.internal.equinox.requirements;

import java.util.function.Supplier;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

final class DataBundle implements Supplier<Bundle> {

	@Override
	public Bundle get() {
		return Platform.getBundle("org.eclipse.passage.lic.equinox.tests.data.requirements"); //$NON-NLS-1$
	}

}
