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
package org.eclipse.passage.loc.internal.agreements;

import static org.eclipse.passage.loc.internal.api.LicensingEvents.CREATE;
import static org.eclipse.passage.loc.internal.api.LicensingEvents.DELETE;
import static org.eclipse.passage.loc.internal.api.LicensingEvents.READ;
import static org.eclipse.passage.loc.internal.api.LicensingEvents.TOPIC_SEP;
import static org.eclipse.passage.loc.internal.api.LicensingEvents.UPDATE;

/**
 * Agreements registry events and event topic definitions.
 */
@SuppressWarnings("restriction")
public final class AgreementRegistryEvents {

	/**
	 * Base name of all Agreements events
	 */
	public static final String AGREEMENTS_TOPIC_BASE = "org/eclipse/passage/lic/agreements/registry"; //$NON-NLS-1$

	/**
	 * Base name of all AgreementsGroup events
	 */
	public static final String AGREEMENT_GROUP_TOPIC_BASE = AGREEMENTS_TOPIC_BASE + TOPIC_SEP + "AgreementGroup"; //$NON-NLS-1$

	/**
	 * AgreementsGroup <code>create</code> event
	 */
	public static final String AGREEMENT_GROUP_CREATE = AGREEMENT_GROUP_TOPIC_BASE + TOPIC_SEP + CREATE;

	/**
	 * AgreementsGroup <code>read</code> event
	 */
	public static final String AGREEMENT_GROUP_READ = AGREEMENT_GROUP_TOPIC_BASE + TOPIC_SEP + READ;

	/**
	 * AgreementsGroup<code>update</code> event
	 */
	public static final String AGREEMENT_GROUP_UPDATE = AGREEMENT_GROUP_TOPIC_BASE + TOPIC_SEP + UPDATE;

	/**
	 * AgreementsGroup<code>delete</code> event
	 */
	public static final String AGREEMENT_GROUP_DELETE = AGREEMENT_GROUP_TOPIC_BASE + TOPIC_SEP + DELETE;

	/**
	 * Base name of all Agreement events
	 */
	public static final String AGREEMENT_TOPIC_BASE = AGREEMENTS_TOPIC_BASE + TOPIC_SEP + "Agreement"; //$NON-NLS-1$

	/**
	 * Agreement <code>create</code> event
	 */
	public static final String AGREEMENT_CREATE = AGREEMENT_TOPIC_BASE + TOPIC_SEP + CREATE;

	/**
	 * Agreement <code>read</code> event
	 */
	public static final String AGREEMENT_READ = AGREEMENT_TOPIC_BASE + TOPIC_SEP + READ;

	/**
	 * Agreement <code>update</code> event
	 */
	public static final String AGREEMENT_UPDATE = AGREEMENT_TOPIC_BASE + TOPIC_SEP + UPDATE;

	/**
	 * Agreement <code>delete</code> event
	 */
	public static final String AGREEMENT_DELETE = AGREEMENT_TOPIC_BASE + TOPIC_SEP + DELETE;

	private AgreementRegistryEvents() {
		// block
	}

}
