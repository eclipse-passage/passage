/* *****************************************************************************
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
/**
 * <p>
 * After the evaluation is done we obtained a <code>Permission</code> we need to
 * let others know that the license is currently in use. What for? To support
 * different back-end strategies like floating licensing, consumable tokens and
 * so on. The abstraction of "vault" is used to implement this behavior. The
 * "vault" tracks "acquired" and "released" conditions and decides how to deal
 * with it.
 * </p>
 * 
 * @see org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate
 */
package org.eclipse.passage.lic.internal.api.conditions.vault;
