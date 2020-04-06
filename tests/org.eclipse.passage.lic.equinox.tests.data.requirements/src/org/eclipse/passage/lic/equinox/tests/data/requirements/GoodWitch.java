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
		"licensing.feature.identifier=GoodWitch", //
		"licensing.restriction.level=fatal" //
})
public final class GoodWitch implements FrogToPrinceMagic {

}
