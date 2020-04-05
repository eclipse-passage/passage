package org.eclipse.passage.lic.internal.equinox.requirements;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.passage.lic.internal.base.StringNamedData;

@SuppressWarnings("restriction")
public final class CapabilityLicFeatureIdTest extends CapabilityLicFeatureInfoTest {

	@Override
	protected StringNamedData infoSupplier(Map<String, Object> attributes) {
		return new CapabilityLicFeatureId(attributes);
	}

	@Override
	protected Set<String> expectations() {
		return new HashSet<String>(Arrays.asList(//
				"PI", //$NON-NLS-1$
				"E" //$NON-NLS-1$
		));
	}

}
