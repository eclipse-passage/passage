package org.eclipse.passage.lic.internal.hc.remote;

import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.base.StringNamedData;
import org.eclipse.passage.lic.internal.base.io.Settings;
import org.eclipse.passage.lic.internal.hc.i18n.HcMessages;
import org.eclipse.passage.lic.internal.net.LicensingServerHost;
import org.eclipse.passage.lic.internal.net.LicensingServerPort;

/**
 * <p>
 * On demand reads the available settings, retrieves {@code host} and
 * {@code port} coordinates of licensing server and forms corresponding part of
 * URL.
 * </p>
 */
@SuppressWarnings("restriction")
public final class LicensingServerCoordinates {

	private final Supplier<Path> settings;

	/**
	 * Instructed to look for settings files starting from the given directory
	 * 
	 * @param residence
	 */
	public LicensingServerCoordinates(Supplier<Path> residence) {
		this.settings = residence;
	}

	/**
	 * 
	 * @return licensing server location in a form {@code host:port}
	 * @throws LicensingException in case of errors during file reading or setting
	 *                            analysis
	 */
	public String get() throws LicensingException {
		Map<String, Object> properties = new Settings(settings, this::necessaryPropertiesExist).get();
		return String.format(//
				"%s:%s", //$NON-NLS-1$
				value(new LicensingServerHost(properties)), //
				value(new LicensingServerPort(properties)));
	}

	private String value(StringNamedData data) throws LicensingException {
		Optional<String> value = data.get();
		if (value.isPresent()) {
			throw new LicensingException(
					String.format(HcMessages.LicensingServerCoordinates_settings_not_found, data.key()));
		}
		return value.get();
	}

	private boolean necessaryPropertiesExist(Map<String, Object> properties) {
		return new LicensingServerHost(properties).get().isPresent() && //
				new LicensingServerPort(properties).get().isPresent();
	}

}
