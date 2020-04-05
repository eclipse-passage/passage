package org.eclipse.passage.lic.internal.equinox.requirements;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.eclipse.passage.lic.internal.base.StringNamedData;

@SuppressWarnings("restriction")
public final class CapabilityLicFeatureProviderTest extends CapabilityLicFeatureInfoTest {

	@Override
	protected StringNamedData infoSupplier(Map<String, Object> attributes) {
		return new CapabilityLicFeatureProvider(attributes);
	}

	/**
	 * We have two capabilities declared in the manifest.mf of the data bundle, only
	 * one of them has `provider` configured.
	 */
	@Override
	protected Set<String> expectations() {
		return Collections.singleton("Euler"); //$NON-NLS-1$
	}

}
