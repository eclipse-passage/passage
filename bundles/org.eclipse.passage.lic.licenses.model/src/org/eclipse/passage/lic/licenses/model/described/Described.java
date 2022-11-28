/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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
package org.eclipse.passage.lic.licenses.model.described;

import java.util.function.Supplier;

abstract class Described implements Supplier<String> {

	protected final String nl = "\r\n"; //$NON-NLS-1$
	protected final String tab = "\t"; //$NON-NLS-1$
	protected final String tabs = "\t\t"; //$NON-NLS-1$

}
