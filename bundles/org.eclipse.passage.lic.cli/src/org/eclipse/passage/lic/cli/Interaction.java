/*******************************************************************************
 * Copyright (c) 2021, 2025 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation; further evolution
 *******************************************************************************/
package org.eclipse.passage.lic.cli;

public interface Interaction {

	void prompt(String information);

	void swear(Throwable thro);

	String input();

}
