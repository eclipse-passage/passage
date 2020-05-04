/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.loc.yars.internal.api;

import java.util.function.Consumer;

/**
 * Simple default handler of a {@linkplain ReportException} prints it's stack
 * trace to the system error stream.
 * 
 * @see DosHandleMedia
 * @since 0.1
 */
public final class DefaultDosHandler implements Consumer<ReportException> {

	@Override
	public void accept(ReportException t) {
		t.printStackTrace();
	}

}
