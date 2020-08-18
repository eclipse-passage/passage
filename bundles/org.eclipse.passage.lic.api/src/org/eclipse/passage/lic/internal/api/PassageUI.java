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
package org.eclipse.passage.lic.internal.api;

/**
 * <p>
 * Framework-dependent {@linkplain Passage} service supplies a
 * functionally-sufficient flow of license coverage testing and on-the-fly
 * improving.
 * </p>
 * <p>
 * Which inevitably means end user involvement through dialogs exposing current
 * licensing status, failures diagnostic, import license facilities, etc
 * </p>
 * <p>
 * Thus, {@code canUse} implementation triggers a potentially heavy, long
 * running process, UI-dependent and user-dependent process aimed in checking
 * license coverage and asking user for improvements, if there it does not cover
 * the feature demanded.
 * </p>
 * <p>
 * Checks runtime-existing license coverage and if it sufficient - returns
 * {@code true}, otherwise <i>tries to solve this problem</i>. It exposes the
 * current licensing status to the end user and offers license import/request
 * facilities. If a new license has been imported on-the-fly, then re-runs
 * access cycle. Repeats these steps until either imported license covers the
 * {@code feature} demanded or the end user declines to improve the license
 * coverage.
 * </p>
 */
public interface PassageUI extends Passage {

}
