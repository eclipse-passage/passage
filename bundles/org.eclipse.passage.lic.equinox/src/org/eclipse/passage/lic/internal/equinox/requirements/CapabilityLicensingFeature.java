package org.eclipse.passage.lic.internal.equinox.requirements;

import java.util.Map;

import org.eclipse.passage.lic.internal.base.StringNamedData;

@SuppressWarnings("restriction")
public final class CapabilityLicensingFeature extends StringNamedData {

	public CapabilityLicensingFeature(String identifier) {
		super(identifier);
	}

	public CapabilityLicensingFeature(Map<String, Object> container) {
		super(container);
	}

	@Override
	public String key() {
		return "licensing.feature"; //$NON-NLS-1$
	}

	@Override
	public String printed(String value) {
		return "\"" + value + "\""; //$NON-NLS-1$ //$NON-NLS-2$
	}

}
