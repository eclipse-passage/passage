package org.eclipse.passage.lic.internal.equinox.requirements;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.passage.lic.internal.base.StringNamedData;

@SuppressWarnings("restriction")
public final class CapabilityLicFeatureRestrictionTest extends CapabilityLicFeatureInfoTest {

	@Override
	protected StringNamedData infoSupplier(Map<String, Object> attributes) {
		return new CapabilityLicFeatureLevel(attributes);
	}

	@Override
	protected Set<String> expectations() {
		return new HashSet<String>(Arrays.asList(//
				"error", //$NON-NLS-1$
				"info" //$NON-NLS-1$
		));
	}

}
