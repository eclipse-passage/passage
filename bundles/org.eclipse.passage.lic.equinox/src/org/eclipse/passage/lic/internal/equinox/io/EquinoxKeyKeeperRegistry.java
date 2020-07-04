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
package org.eclipse.passage.lic.internal.equinox.io;

import java.util.Map;

import org.eclipse.passage.lic.api.io.KeyKeeper;
import org.eclipse.passage.lic.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.base.io.BaseKeyKeeperRegistry;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

/**
 * @deprecated use internal.api.io StreamCodecRegistry
 */
@Deprecated
@Component
public class EquinoxKeyKeeperRegistry extends BaseKeyKeeperRegistry implements KeyKeeperRegistry {

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	@Override
	public void registerKeyKeeper(KeyKeeper keeper, Map<String, Object> properties) {
		super.registerKeyKeeper(keeper, properties);
	}

	@Override
	public void unregisterKeyKeeper(KeyKeeper keeper, Map<String, Object> properties) {
		super.unregisterKeyKeeper(keeper, properties);
	}

}
