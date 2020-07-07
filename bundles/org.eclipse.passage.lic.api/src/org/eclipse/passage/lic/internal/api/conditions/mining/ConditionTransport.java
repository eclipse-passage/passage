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
package org.eclipse.passage.lic.internal.api.conditions.mining;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.registry.Service;

/**
 * Persistence interface for {@link Condition}(s).
 */
public interface ConditionTransport extends Service<ContentType> {

	/**
	 * Reads {@link Condition}(s) from the given {@link InputStream}. Stream remains
	 * open.
	 */
	Collection<Condition> read(InputStream input) throws IOException;

}
