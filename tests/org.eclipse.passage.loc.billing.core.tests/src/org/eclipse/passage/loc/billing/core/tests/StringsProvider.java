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
 *     Nikifor Fedorov <zelenyhleb@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.billing.core.tests;

import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class StringsProvider {

	private final List<String> strings = new LinkedList<String>();
	private final Random random = new Random();

	private boolean contains(String string) {
		return strings.contains(string);
	}

	private boolean add(String string) {
		return strings.add(string);
	}

	public final void clear() {
		strings.clear();
	}

	public final String randomString() {
		String string = new String(array(), Charset.forName("UTF-8")); //$NON-NLS-1$
		if (contains(string)) {
			return randomString();
		}
		add(string);
		return string;
	}

	private byte[] array() {
		byte[] array = new byte[5];
		random.nextBytes(array);
		return array;
	}

}
