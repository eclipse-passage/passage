/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.yars.internal.api;

import java.util.List;

/**
 * Aware of a particular {@linkplain Storage} structure, able to fetch data from
 * it according to encapsulated business logic.
 *
 * @since 0.1
 */
@SuppressWarnings("unused")
public interface FetchedData<B extends Storage<?>, R> {

	List<R> get();

}
