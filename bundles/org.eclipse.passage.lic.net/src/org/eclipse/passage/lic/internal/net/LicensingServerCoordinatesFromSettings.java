/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.internal.net;

import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.base.StringNamedData;
import org.eclipse.passage.lic.base.io.Settings;
import org.eclipse.passage.lic.internal.net.i18n.NetMessages;

/**
 * <p>
 * On demand reads the available settings, retrieves {@code host} and
 * {@code port} coordinates of licensing server and forms corresponding part of
 * URL.
 * </p>
 */
public final class LicensingServerCoordinatesFromSettings implements LicensingServerCoordinates {

	private final Supplier<Path> settings;

	/**
	 * Instructed to look for settings files starting from the given directory
	 * 
	 * @param residence
	 */
	public LicensingServerCoordinatesFromSettings(Supplier<Path> residence) {
		this.settings = residence;
	}

	@Override
	public HostPort get() throws LicensingException {
		Map<String, Object> properties = new Settings(settings, this::necessaryPropertiesExist).get();
		return new HostPort(//
				value(new LicensingServerHost(properties)), //
				value(new LicensingServerPort(properties)));
	}

	private String value(StringNamedData data) throws LicensingException {
		Optional<String> value = data.get();
		if (!value.isPresent()) {
			throw new LicensingException(//
					String.format(NetMessages.getString("LicensingServerCoordinatesFromSettings.settings_not_found"), //$NON-NLS-1$
							data.key()));
		}
		if (value.get().trim().isEmpty()) {
			throw new LicensingException(//
					String.format(NetMessages.getString("LicensingServerCoordinatesFromSettings.settings_are_blank"), //$NON-NLS-1$
							data.key()));

		}
		return value.get();
	}

	private boolean necessaryPropertiesExist(Map<String, Object> properties) {
		return new LicensingServerHost(properties).get().isPresent() && //
				new LicensingServerPort(properties).get().isPresent();
	}
}
