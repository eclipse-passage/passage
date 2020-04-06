/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.moveto.lic.internal.emf.edit.i18n;

import org.eclipse.osgi.util.NLS;

public class EmfEditMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.moveto.lic.internal.emf.edit.i18n.EmfEditMessages"; //$NON-NLS-1$
	public static String EObjectDefaultName_new;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, EmfEditMessages.class);
	}

	private EmfEditMessages() {
	}
}
