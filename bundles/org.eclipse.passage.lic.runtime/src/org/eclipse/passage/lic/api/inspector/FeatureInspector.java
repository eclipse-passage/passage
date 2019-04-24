package org.eclipse.passage.lic.api.inspector;

import org.eclipse.passage.lic.runtime.LicensingConfiguration;

public interface FeatureInspector {
	
	LicensingConfiguration getLicensingConfiguration();
	
	FeatureCase inspectFeatures(String... identifiers);

	FeatureCase inspectFeatures(Iterable<String> identifiers);

}
