package org.eclipse.passage.lic.internal.equinox;

import org.eclipse.osgi.util.NLS;

public class EquinoxInternalMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.internal.equinox.EquinoxInternalMessages"; //$NON-NLS-1$
	public static String ComponentConfigurationResolver_error_invalid_bundle_context;
	public static String ComponentConfigurationResolver_error_invalid_component_rt;
	public static String BundleCapabilityResolver_error;
	public static String BundleCapabilityResolver_error_bundle_context;
	public static String EquinoxRestrictionExecutorRegistry_error_name;
	public static String EquinoxRestrictionExecutorRegistry_error_title;
	public static String EquinoxRestrictionExecutorRegistry_fatal_name;
	public static String EquinoxRestrictionExecutorRegistry_fatal_title;
	public static String EquinoxRestrictionExecutorRegistry_info_name;
	public static String EquinoxRestrictionExecutorRegistry_info_title;
	public static String EquinoxRestrictionExecutorRegistry_warning_name;
	public static String EquinoxRestrictionExecutorRegistry_warning_title;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, EquinoxInternalMessages.class);
	}

	private EquinoxInternalMessages() {
	}
}
