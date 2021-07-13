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
package org.eclipse.passage.lic.internal.api.conditions.evaluation;

import java.util.function.Supplier;

import org.eclipse.passage.lic.api.registry.Registry;
import org.eclipse.passage.lic.api.registry.StringServiceId;

public interface PermissionEmittersRegistry extends Supplier<Registry<StringServiceId, PermissionEmittingService>> {

}
