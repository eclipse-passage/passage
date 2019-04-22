package org.eclipse.passage.lic.equinox;

import org.eclipse.osgi.util.NLS;

public class EquinoxMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.equinox.EquinoxMessages"; //$NON-NLS-1$
	public static String EquinoxPaths_uri_retrieval_error;
	public static String EquinoxRestrictions_feature_is_licensed;
	public static String EquinoxRestrictions_feature_is_not_licensed;
	public static String EquinoxRestrictions_title;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, EquinoxMessages.class);
	}

	private EquinoxMessages() {
	}
}
