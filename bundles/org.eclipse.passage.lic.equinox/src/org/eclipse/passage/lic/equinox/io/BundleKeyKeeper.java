/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.equinox.io;

import static org.eclipse.passage.lic.base.io.LicensingPaths.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.io.KeyKeeper;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;

public class BundleKeyKeeper implements KeyKeeper {

	protected static final String PATH_DEFAULT = "OSGI-INF/"; //$NON-NLS-1$
	
	private ComponentContext componentContext;
	
	@Activate
	public void activate(ComponentContext context) {
		this.componentContext = context;
	}

	@Override
	public InputStream openKeyStream(LicensingConfiguration configuration) throws IOException {
		String fileName = composeFileName(configuration, EXTENSION_PRODUCT_PUBLIC);
		String keypath = PATH_DEFAULT + fileName;
		Bundle bundle = getUsingBundle(componentContext);
		if (bundle == null) {
			throw new FileNotFoundException(keypath);
		}
		URL resource = bundle.getResource(keypath);
		if (resource == null) {
			throw new FileNotFoundException(keypath);
		}
		return resource.openStream();
	}

	protected Bundle getUsingBundle(ComponentContext context) {
		Bundle bundle = context.getUsingBundle();
		if (bundle == null) {
			return FrameworkUtil.getBundle(getClass());
		}
		return bundle;
	}

}
