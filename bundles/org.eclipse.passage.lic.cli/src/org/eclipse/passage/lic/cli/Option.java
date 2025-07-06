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
package org.eclipse.passage.lic.cli;

public interface Option<K extends Option.Key, D extends Enum<?>> {

	K key();

	String documentation();

	D run();

	public static interface Key {

		/**
		 * a letter, not-localizable
		 */
		public char symbol();

		public static abstract class Base implements Key {

			private final char key;

			Base(char key) {
				this.key = key;
			}

			@Override
			public char symbol() {
				return key;
			}

		}

	}

}
