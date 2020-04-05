package org.eclipse.passage.lic.internal.equinox.requirements;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.passage.lic.internal.base.StringNamedData;

@SuppressWarnings("restriction")
public final class CapabilityLicFeatureVersionTest extends CapabilityLicFeatureInfoTest {

	@Override
	protected StringNamedData infoSupplier(Map<String, Object> attributes) {
		return new CapabilityLicFeatureVersion(attributes);
	}

	@Override
	protected Set<String> expectations() {
		return new HashSet<String>(Arrays.asList(//
				"3.14.15", //$NON-NLS-1$
				"2.71.82" //$NON-NLS-1$
		));
	}

}
