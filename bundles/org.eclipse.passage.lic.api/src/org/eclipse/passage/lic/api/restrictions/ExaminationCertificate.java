/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.api.restrictions;

import java.time.ZonedDateTime;
import java.util.Collection;

import org.eclipse.passage.lic.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.api.requirements.Requirement;

/**
 * <p>
 * {@linkplain Permission}s-against-{@linkplain Requirement}s examination boils
 * down to the instance of this certificate.
 * </p>
 * <p>
 * During the examinations some of the given {@code requirements} are satisfied,
 * and some can stay unsatisfied.
 * </p>
 * <p>
 * For the satisfied {@code requirements} here we keep all the
 * {@code permissions} that participated in this satisfaction. Use
 * {@code participants()} to get'em.
 * </p>
 * <p>
 * For each unsatisfied {@code requirement} a {@linkplain Restriction} is
 * produced. Here is {@code restrictions()} method to access these ones.
 * </p>
 * <p>
 * If the examination produced no {@linkplain Restriction}s, then it is treated
 * as successful ({@code examinationPassed()})
 * 
 * @since 2.1
 */
public interface ExaminationCertificate {

	Collection<Restriction> restrictions();

	Collection<Requirement> satisfied();

	Permission satisfaction(Requirement satisfied);

	ZonedDateTime stamp();

}
