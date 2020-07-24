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
package org.eclipse.passage.lic.internal.api.observatory;

/**
 * <p>
 * Describes the entry that can expire after some conditions in the outer world
 * are met.
 * </p>
 * <p>
 * Say, {@code BottleOfMilk} can easily implement the interface
 * </p>
 * 
 * <pre>
 * <{@code
 * public final class BottleOfMilk implements Limited {
 * 
 * 	private final ZonedDateTime expirationDate;
 * 
 * 	public BottleOfMilk(ZonedDateTime expirationDate) {
 * 		this.expirationDate = expirationDate;
 * 	}
 * 
 * 	&#64;Override
 * 	public boolean expired() {
 * 		return ZonedDateTime.now().isBefore(expirationDate);
 * 	}
 * 
 * }
 * }
 * </pre>
 */
public interface Limited {

	boolean expired();

}
