package org.eclipse.passage.lic.internal.net.i18n;

import org.eclipse.osgi.util.NLS;

public class NetMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.internal.net.i18n.NetMessages"; //$NON-NLS-1$
	public static String LicensingServerCoordinates_settings_are_blank;
	public static String LicensingServerCoordinates_settings_not_found;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, NetMessages.class);
	}

	private NetMessages() {
	}
}
