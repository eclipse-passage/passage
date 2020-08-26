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
package org.eclipse.passage.loc.internal.api;

/**
 *
 * Codes of licensing events
 *
 * @since 0.4.0
 */
public final class LicensingEvents {

	public static final String PROPERTY_TOPIC = "org.eclipse.passage.lic.api.event.topic"; //$NON-NLS-1$
	public static final String PROPERTY_SOURCE = "org.eclipse.passage.lic.api.event.source"; //$NON-NLS-1$
	public static final String PROPERTY_DATA = "org.eclipse.passage.lic.api.event.data"; //$NON-NLS-1$
	public static final String PROPERTY_MESSAGE = "org.eclipse.passage.lic.api.event.message"; //$NON-NLS-1$

	private LicensingEvents() {
		// block
	}

	/**
	 * Segment for events of type <code>create</code>
	 *
	 * @since 0.4.0
	 */
	public static final String CREATE = "create"; //$NON-NLS-1$

	/**
	 * Segment for events of type <code>read</code>
	 *
	 * @since 0.4.0
	 */
	public static final String READ = "read"; //$NON-NLS-1$

	/**
	 * Segment for events of type <code>update</code>
	 *
	 * @since 0.4.0
	 */
	public static final String UPDATE = "update"; //$NON-NLS-1$

	/**
	 * Segment for events of type <code>delete</code>
	 *
	 * @since 0.4.0
	 */
	public static final String DELETE = "delete"; //$NON-NLS-1$

	/**
	 * Topic separator character
	 *
	 * @since 0.4.0
	 */
	public static final String TOPIC_SEP = "/"; //$NON-NLS-1$

	/**
	 * Wild card character for matching all sub topics
	 */
	public static final String ALL_SUB_TOPICS = "*"; //$NON-NLS-1$

	/**
	 * Base name of all Licensing events
	 *
	 * @since 0.4.0
	 */
	public static final String TOPIC_BASE = "org/eclipse/passage/lic/api"; //$NON-NLS-1$

}
