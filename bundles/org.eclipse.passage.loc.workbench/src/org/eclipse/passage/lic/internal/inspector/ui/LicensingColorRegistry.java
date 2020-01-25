/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.inspector.ui;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.passage.lic.jface.resource.LicensingColorResolver;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component
public class LicensingColorRegistry implements LicensingColorResolver {

	private ColorRegistry colorRegistry;

	@Activate
	public void activate() {
		colorRegistry = new ColorRegistry();
		addValidationColors();
	}

	protected void addValidationColors() {
		register(COLOR_VALIDATION_OK, new RGB(250, 250, 250));
		register(COLOR_VALIDATION_ERROR, new RGB(250, 194, 180));
	}

	protected void register(String identifier, RGB rgb) {
		colorRegistry.put(identifier, rgb);
	}

	@Deactivate
	public void deactivate() {
		colorRegistry = null;
	}

	@Override
	public Color getColor(String identifier) {
		return colorRegistry.get(identifier);
	}

}
