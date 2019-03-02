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
package org.eclipse.passage.lic.runtime.registry;

public class RegistryEvents {

	/**
	 * Topic separator character
	 */
	public static final String TOPIC_SEP = "/"; //$NON-NLS-1$

	/**
	 * Wild card character for matching all sub topics
	 */
	public static final String ALL_SUB_TOPICS = "*"; //$NON-NLS-1$


	/**
	 * Segment for events of type <code>create</code> 
	 */
	public static final String CREATE = "create"; //$NON-NLS-1$

	/**
	 * Segment for events of type <code>read</code> 
	 */
	public static final String READ = "read"; //$NON-NLS-1$

	/**
	 * Segment for events of type <code>update</code> 
	 */
	public static final String UPDATE = "update"; //$NON-NLS-1$

	/**
	 * Segment for events of type <code>delete</code> 
	 */
	public static final String DELETE = "delete"; //$NON-NLS-1$

}
