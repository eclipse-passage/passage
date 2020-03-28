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
package org.eclipse.passage.lic.internal.equinox;

import org.eclipse.passage.lic.internal.api.Passage;

@SuppressWarnings("restriction")
public final class EquinoxPassage implements Passage {

	@Override
	public boolean canUse(String featureId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void checkLicense(String featureId) {
		// accessManager.executeAccessRestrictions(configuration);
		throw new UnsupportedOperationException();
	}

}
