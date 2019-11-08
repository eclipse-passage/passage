package org.eclipse.passage.lic.api.inspector;

import org.eclipse.passage.lic.api.LicensingConfiguration;

/**
 * <p>
 * There are use cases, where all but the last step of <i>access cycle</i> are required.
 * For those cases {@code FeatureInspector} contract implements a subset of those steps,
 * called <i>inspection cycle</i>, in order to preliminary evaluate license coverage for a whole running product
 * or for a particular user scenario (that affects potentially several features under licensing).
 * </p>
 *
 * @since 0.4.0
 */
public interface FeatureInspector {

	/**
	 * General configuration of a running product
	 *
	 * @since 0.4.0
	 */
	LicensingConfiguration getLicensingConfiguration();

	/**
	 * Convenience method
	 *
	 * @see #inspectFeatures(Iterable)
	 * @since 0.4.0
	 */
	FeatureCase inspectFeatures(String... identifiers);

	/**
	 * Pre-evaluates license coverage for specified features.
	 *
	 * @since 0.4.0
	 */
	FeatureCase inspectFeatures(Iterable<String> identifiers);

}
