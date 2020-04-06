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
package org.eclipse.passage.lic.equinox.tests.data.requirements;

import org.osgi.service.component.annotations.Component;

@Component(property = { //
		"licensing.feature.identifier=EvilWitch", //
		"licensing.feature.name=Evil Witch", //
		"licensing.restriction.level=info", //
		"licensing.feature.version=13.4.1", //
		"licensing.feature.provider=Universe" //
})
public final class EvilWitch implements PrinceToFrogMagic {

}
