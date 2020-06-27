/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.lic.net;

import org.eclipse.passage.lic.internal.net.LicensingServerHost;
import org.eclipse.passage.lic.internal.net.LicensingServerPort;

/**
 * 
 * @since 0.5.0
 *
 */
public class LicensingNet {

	/**
	 * @deprecated use {@link LicensingServerHost}
	 */
	@Deprecated
	public static final String LICENSING_SERVER_HOST = "licensing.server.host"; //$NON-NLS-1$
	/**
	 * @deprecated use {@link LicensingServerPort}
	 */
	@Deprecated
	public static final String LICENSING_SERVER_PORT = "licensing.server.port"; //$NON-NLS-1$

	public static final String ROLE = "role"; //$NON-NLS-1$
	public static final String ROLE_ADMIN = "admin"; //$NON-NLS-1$
	public static final String ROLE_LICENSEE = "licensee"; //$NON-NLS-1$
	public static final String ROLE_OPERATOR = "operator"; //$NON-NLS-1$

	public static final String ACTION = "action"; //$NON-NLS-1$

}
