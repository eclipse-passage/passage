/*******************************************************************************
 * Copyright (c) 2021, 2022 ArSysOp
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
package org.eclipse.passage.lbc.internal.jetty;

import org.eclipse.passage.lic.internal.net.connect.Storage;
import org.osgi.framework.BundleContext;

@SuppressWarnings("restriction")
public final class FlsCommands {

	public void register(BundleContext context, String name, Storage storage) {
		new UploadLicense(name, storage.get().get()).register(context);
	}

}
