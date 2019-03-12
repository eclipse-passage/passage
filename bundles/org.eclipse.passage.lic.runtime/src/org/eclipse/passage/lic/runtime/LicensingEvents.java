/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.lic.runtime;

public class LicensingEvents {

	private LicensingEvents() {
		// block
	}

	/**
	 * Topic separator character
	 */
	public static final String TOPIC_SEP = "/"; //$NON-NLS-1$

	/**
	 * Wild card character for matching all sub topics
	 */
	public static final String ALL_SUB_TOPICS = "*"; //$NON-NLS-1$

	/**
	 * Base name of all Licensing events
	 */
	public static final String TOPIC_BASE = "org/eclipse/passage/lic/events"; //$NON-NLS-1$

}
