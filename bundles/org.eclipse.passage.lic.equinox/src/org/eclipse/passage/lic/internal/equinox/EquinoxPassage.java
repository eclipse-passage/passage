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

import java.util.Optional;

import org.eclipse.passage.lic.internal.api.Access;
import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.Passage;

@SuppressWarnings("restriction")
public final class EquinoxPassage implements Passage {

	@Override
	public boolean canUse(String feature) {
		Optional<Framework> framework = new FrameworkSupplier().get();
		if (!framework.isPresent()) {
			return false;
		}
		return new Access(framework.get()).canUse(feature);
	}

	@Override
	public void checkLicense(String feature) {
		// accessManager.executeAccessRestrictions(configuration);
		throw new UnsupportedOperationException();
	}

}
