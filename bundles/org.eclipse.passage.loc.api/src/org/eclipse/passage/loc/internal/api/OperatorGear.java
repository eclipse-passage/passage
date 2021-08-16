/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.loc.internal.api;

import java.util.Optional;

import org.eclipse.passage.lic.api.Gear;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.inspection.RuntimeEnvironmentRegistry;
import org.eclipse.passage.lic.api.io.HashesRegistry;
import org.eclipse.passage.lic.api.io.StreamCodec;
import org.eclipse.passage.loc.internal.api.workspace.OperatorWorkspace;

public interface OperatorGear extends Gear {

	Optional<StreamCodec> codec(LicensedProduct product);

	RuntimeEnvironmentRegistry environments();

	HashesRegistry hashes();

	OperatorWorkspace workspace();

}
